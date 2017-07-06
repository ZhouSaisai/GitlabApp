package com.zhouss.www.gitlabapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.adapter.TaskAdapter;
import com.zhouss.www.gitlabapp.model.Task;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.JSONUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zs on 2017/6/14.
 */

public class SHomeActivity extends BaseActivity implements View.OnClickListener{
    //信号区
    public static final int QUERY_FAIL = 1;
    public static final int QUERY_SUCCESS = 2;

    //组件区
    private Button add_button;
    private Button login_out;
    private Button exam_tab;
    private Button homework_tab;
    private Button exercise_tab;

    private ListView listView;
    private List<Task> taskList = new ArrayList<>();
    private List<Task> tempList = new ArrayList<>();

    private TaskAdapter taskAdapter;

    private int courseId = 2;
    private Task selectTask;

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_SUCCESS:
                    taskList.clear();
                    taskList.addAll(tempList);
                    taskAdapter.notifyDataSetChanged();
                    break;
                case QUERY_FAIL:
                    Toast.makeText(MyApplication.getContext(), "数据拉取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.s_home);

        add_button = (Button) findViewById(R.id.add_button);
        add_button.setVisibility(View.VISIBLE);
        add_button.setOnClickListener(this);
        login_out = (Button) findViewById(R.id.login_out);
        login_out.setOnClickListener(this);

        exam_tab = (Button) findViewById(R.id.exam_tab);
        homework_tab = (Button) findViewById(R.id.homework_tab);
        exercise_tab = (Button) findViewById(R.id.exercise_tab);
        exam_tab.setTextColor(Color.parseColor("#FFFFFF"));
        exam_tab.setBackgroundResource(R.drawable.btn_bg_pressed_l);
        exam_tab.setOnClickListener(this);
        homework_tab.setOnClickListener(this);
        exercise_tab.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.task_list);
        taskAdapter = new TaskAdapter(MyApplication.getContext(),R.layout.task_item,taskList);
        listView.setAdapter(taskAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectTask = taskList.get(position);

                Intent intent = new Intent();
                intent.putExtra("task",selectTask);
                intent.setClass(SHomeActivity.this, TTaskDetailActivity.class);
                startActivity(intent);
            }
        });

        queryAllTask("exam");
    }

    /**
     * 查询对应的我的任务
     */
    private void queryAllTask(String type){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        String content = prefs.getString(type,null);
        if(content!=null){
            tempList = JSONUtil.handlTaskResponse(content);
            taskList.clear();
            taskList.addAll(tempList);
            taskAdapter.notifyDataSetChanged();
        }
        else {
            String address = HttpUtil.URL+"/course/"+courseId+"/"+type;
            queryFromServer(address,type);
        }

    }

    private void queryFromServer(String address, final String type) {
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
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(type,resultData);
                editor.apply();
                tempList = JSONUtil.handlTaskResponse(resultData);
                Message message = new Message();
                message.what=QUERY_SUCCESS;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exam_tab:
                exam_tab.setTextColor(Color.parseColor("#FFFFFF"));
                exam_tab.setBackgroundResource(R.drawable.btn_bg_pressed_l);
                homework_tab.setTextColor(Color.parseColor("#e03f30"));
                homework_tab.setBackgroundResource(R.drawable.btn_bg_normal_m);
                exercise_tab.setTextColor(Color.parseColor("#e03f30"));
                exercise_tab.setBackgroundResource(R.drawable.btn_bg_normal_r);
                queryAllTask("exam");
                break;
            case R.id.homework_tab:
                homework_tab.setTextColor(Color.parseColor("#FFFFFF"));
                homework_tab.setBackgroundResource(R.drawable.btn_bg_pressed_m);
                exam_tab.setTextColor(Color.parseColor("#e03f30"));
                exam_tab.setBackgroundResource(R.drawable.btn_bg_normal_l);
                exercise_tab.setTextColor(Color.parseColor("#e03f30"));
                exercise_tab.setBackgroundResource(R.drawable.btn_bg_normal_r);
                queryAllTask("homework");
                break;
            case R.id.exercise_tab:
                exercise_tab.setTextColor(Color.parseColor("#FFFFFF"));
                exercise_tab.setBackgroundResource(R.drawable.btn_bg_pressed_r);
                homework_tab.setTextColor(Color.parseColor("#e03f30"));
                homework_tab.setBackgroundResource(R.drawable.btn_bg_normal_m);
                exam_tab.setTextColor(Color.parseColor("#e03f30"));
                exam_tab.setBackgroundResource(R.drawable.btn_bg_normal_l);
                queryAllTask("exercise");
                break;
            case R.id.add_button:
                Toast.makeText(this, "后台没有接口哦！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_out:
                ActivityCollector.finishAll();
                Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
                startActivity(intent);
        }
    }
}
