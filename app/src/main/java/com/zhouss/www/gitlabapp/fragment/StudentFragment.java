package com.zhouss.www.gitlabapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.activity.THomeActivity;
import com.zhouss.www.gitlabapp.adapter.ClassStudentAdapter;
import com.zhouss.www.gitlabapp.model.ClassStudent;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.JSONUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;
import com.zhouss.www.gitlabapp.util.ProgressUtil;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zs on 2017/6/16.
 */

public class StudentFragment extends Fragment {
    //信号区
    private static final int QUERY_FAIL = 1;
    private static final int QUERY_SUCCESS = 2;

    //组件区
    private TextView title_bar;
    private Button back_button;
    private Button add_button;

    //list区
    private ListView studentListView;
    private List<ClassStudent> studentList = new ArrayList<>();
    private ClassStudentAdapter studentAdapter;

    //对应班级
    private int classId = 1;
    private String cName = "";

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_SUCCESS:
                    List<ClassStudent> nls  = DataSupport.where("groupId = ?",classId+"").order("csId").find(ClassStudent.class);
                    studentList.clear();
                    studentList.addAll(nls);
                    studentAdapter.notifyDataSetChanged();
                    ProgressUtil.closeProgressDialog();
                    break;
                case QUERY_FAIL:
                    ProgressUtil.closeProgressDialog();
                    Toast.makeText(MyApplication.getContext(), "数据拉取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof THomeActivity) {
            THomeActivity activity = (THomeActivity) context;
            title_bar = (TextView) activity.findViewById(R.id.title_bar);
            back_button = (Button) activity.findViewById(R.id.back_button);
            add_button = (Button) activity.findViewById(R.id.add_button);

            title_bar.setText("学生列表");
            add_button.setVisibility(View.VISIBLE);
            back_button.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.t_student_frag, container, false);

        Bundle bundle = getArguments();
        classId = bundle.getInt("classId");
        cName = bundle.getString("cName");
        title_bar.setText(cName);

        studentListView =  (ListView)view.findViewById(R.id.students_list);
        queryStudents();
        studentAdapter = new ClassStudentAdapter(MyApplication.getContext(),R.layout.class_item,studentList);
        studentListView.setAdapter(studentAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selectClass = classList.get(position);
//                classListView.setVisibility(View.INVISIBLE);
//                queryStudents();
            }
        });
    }

    /**
     * 查询班级对应的全部学生
     */
    private void queryStudents(){
        studentList = DataSupport.where("groupId = ?",classId+"").order("csId").find(ClassStudent.class);
        if(studentList.size()>0){
            studentListView.setSelection(0);
        } else {
            String address = HttpUtil.URL+"/group/"+classId+"/students";
            queryFromServer(address);
        }

    }



    private void queryFromServer(String address) {
        ProgressUtil.showProgressDialog(getActivity());
        HttpUtil.sendGetOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what=QUERY_FAIL;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultData = response.body().string();
                boolean result = JSONUtil.handlStudentResponse(resultData);
                if(result){
                    Message message = new Message();
                    message.what=QUERY_SUCCESS;
                    handler.sendMessage(message);
                }

            }
        });
    }
}
