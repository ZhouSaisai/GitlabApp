package com.zhouss.www.gitlabapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.zhouss.www.gitlabapp.activity.TStudentActivity;
import com.zhouss.www.gitlabapp.adapter.ClassAdapter;
import com.zhouss.www.gitlabapp.model.Class;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.JSONUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;

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


public class ClassFragment extends Fragment {
    //信号区
    private static final int QUERY_FAIL = 1;
    private static final int QUERY_SUCCESS = 2;

    //list区
    private ListView classListView;
    private List<Class> classList = new ArrayList<>();
    private ClassAdapter classArrayAdapter;

    //选中的班级
    private Class selectClass;

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_SUCCESS:
                    List<Class> newList = DataSupport.order("cId").find(Class.class);
                    classList.clear();
                    classList.addAll(newList);
                    classArrayAdapter.notifyDataSetChanged();
                    break;
                case QUERY_FAIL:
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
            TextView title_bar = (TextView) activity.findViewById(R.id.title_bar);
            Button add_button = (Button) activity.findViewById(R.id.add_button);
            Button back_button = (Button) activity.findViewById(R.id.back_button);
            title_bar.setText("我的班级");
            add_button.setVisibility(View.VISIBLE);
            back_button.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.t_class_frag, container, false);
        classListView = (ListView) view.findViewById(R.id.class_list);
        queryAllClass();
        classArrayAdapter = new ClassAdapter(MyApplication.getContext(),R.layout.class_item,classList);
        classListView.setAdapter(classArrayAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectClass = classList.get(position);

                Intent intent = new Intent();
                intent.putExtra("classId",selectClass.getcId());
                intent.putExtra("className",selectClass.getName());
                intent.setClass(getActivity(), TStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 查询老师对应的全部班级
     */
    private void queryAllClass(){
        classList = DataSupport.order("cId").find(Class.class);
        if(classList.size()>0){
            classListView.setSelection(0);
        } else {
            String address = HttpUtil.URL+"/group";
            queryFromServer(address);
        }

    }

    private void queryFromServer(String address) {
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
                boolean result = JSONUtil.handlClassResponse(resultData);
                if(result){
                    Message message = new Message();
                    message.what= QUERY_SUCCESS;
                    handler.sendMessage(message);
                }

            }
        });
    }
}
