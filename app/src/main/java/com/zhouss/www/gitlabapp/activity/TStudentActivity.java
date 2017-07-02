package com.zhouss.www.gitlabapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.adapter.ClassStudentAdapter;
import com.zhouss.www.gitlabapp.model.ClassStudent;
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
 * Created by zs on 2017/6/30.
 */

public class TStudentActivity extends BaseActivity implements View.OnClickListener{
    //信号区
    private static final int QUERY_FAIL = 1;
    private static final int QUERY_SUCCESS = 2;

    //组件区
    private TextView title_bar;
    private Button add_button;
    private Button back_button;
    private TextView empty_text;

    //list区
    private ListView studentListView;
    private List<ClassStudent> studentList = new ArrayList<>();
    private ClassStudentAdapter studentAdapter;

    //对应班级
    private int classId = 1;
    private String cName = "";

    //选中的学生
    private ClassStudent selectStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.t_student);

        title_bar = (TextView) findViewById(R.id.title_bar);
        back_button = (Button) findViewById(R.id.back_button);
        add_button = (Button)  findViewById(R.id.add_button);
        empty_text = (TextView) findViewById(R.id.empty_text);

        back_button.setOnClickListener(this);
        add_button.setOnClickListener(this);

        Intent intent = getIntent();
        classId = intent.getIntExtra("classId",1);
        cName = intent.getStringExtra("className");
        title_bar.setText(cName);

        studentListView =  (ListView) findViewById(R.id.students_list);
        queryStudents();
        studentAdapter = new ClassStudentAdapter(MyApplication.getContext(),R.layout.student_item,studentList);
        studentListView.setAdapter(studentAdapter);

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectStudent = studentList.get(position);

                Intent intent = new Intent(TStudentActivity.this, TStudentDetailActivity.class);
                intent.putExtra("selectStudent",selectStudent);
                startActivity(intent);
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

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_SUCCESS:
                    List<ClassStudent> nls  = DataSupport.where("groupId = ?",classId+"").order("csId").find(ClassStudent.class);
                    if(nls.isEmpty()){
                        empty_text.setVisibility(View.VISIBLE);
                    }else{
                        empty_text.setVisibility(View.GONE);
                        studentList.clear();
                        studentList.addAll(nls);
                        studentAdapter.notifyDataSetChanged();
                    }
                    break;
                case QUERY_FAIL:
                    Toast.makeText(MyApplication.getContext(), "数据拉取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.add_button:
                Toast.makeText(this, "后台没有接口哦！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back_button:
                finish();
                break;
        }
    }
}
