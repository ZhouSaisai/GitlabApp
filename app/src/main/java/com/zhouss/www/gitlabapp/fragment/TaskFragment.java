package com.zhouss.www.gitlabapp.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.activity.THomeActivity;
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
 * Created by zs on 2017/6/16.
 */

public class TaskFragment extends Fragment implements View.OnClickListener{
    //信号区
    public static final int QUERY_FAIL = 1;
    public static final int QUERY_SUCCESS = 2;

    //组件区
    private TextView title_bar;
    private Button add_button;
    private Button back_button;

    private Button exam_tab;
    private Button homework_tab;
    private Button exercise_tab;

    private ListView listView;
    private List<Task> taskList = new ArrayList<>();
    private TaskAdapter taskAdapter;

    private int courseId = 2;

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_SUCCESS:
                    taskAdapter.notifyDataSetChanged();
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
            title_bar = (TextView) activity.findViewById(R.id.title_bar);
            add_button = (Button) activity.findViewById(R.id.add_button);
            back_button = (Button) activity.findViewById(R.id.back_button);
        }
        title_bar.setText("发布的任务");
        add_button.setVisibility(View.VISIBLE);
        back_button.setVisibility(View.INVISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.t_task_frag, container, false);

        exam_tab = (Button) view.findViewById(R.id.exam_tab);
        homework_tab = (Button) view.findViewById(R.id.homework_tab);
        exercise_tab = (Button) view.findViewById(R.id.exercise_tab);
        exam_tab.setTextColor(Color.parseColor("#FFFFFF"));
        exam_tab.setBackgroundResource(R.drawable.btn_bg_pressed_l);
        exam_tab.setOnClickListener(this);
        homework_tab.setOnClickListener(this);
        exercise_tab.setOnClickListener(this);

        listView = (ListView) view.findViewById(R.id.task_list);
        listView.addHeaderView(new ViewStub(getContext()));
//        listView.addFooterView(new ViewStub(getContext()));

        taskAdapter = new TaskAdapter(MyApplication.getContext(),R.layout.task_item,taskList);
        listView.setAdapter(taskAdapter);
        queryAllTask("exam");
        return view;
    }

    /**
     * 查询老师对应的全部班级
     */
    private void queryAllTask(String type){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        String content = prefs.getString(type,null);
        if(content!=null){
            List<Task> nl = JSONUtil.handlTaskResponse(content);
            taskList.clear();
            taskList.addAll(nl);
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

                List<Task> nl = JSONUtil.handlTaskResponse(resultData);
                taskList.clear();
                taskList.addAll(nl);

                Message message = new Message();
                message.what=QUERY_SUCCESS;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
        }
    }
}
