package com.zhouss.www.gitlabapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.Task;

import java.util.List;

/**
 * Created by zs on 2017/6/22.
 */

public class TaskAdapter extends ArrayAdapter<Task>{

    private int resourceId;

    public TaskAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task_item = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else{
            view = convertView;
        }

        TextView t_id = (TextView) view.findViewById(R.id.t_id);
        TextView t_title = (TextView) view.findViewById(R.id.t_title);
        TextView t_status = (TextView) view.findViewById(R.id.t_status);

        t_id.setText(task_item.gettId()+"");
        t_title.setText(task_item.getTitle());
        String status = task_item.getStatus();
        String result = "";
        switch (status){
            case "newly":
                result = "新建态";
                break;
            case "initing":
                result = "初始化";
                break;
            case "initFail":
                result = "初始化失败";
                break;
            case "initSuccess":
                result = "初始化成功";
                break;
            case "ongoing":
                result = "考试进行中";
                break;
            case "timeup":
                result = "考试时间到";
                break;
            case "analyzing":
                result = "分析结果中";
                break;
            case "analyzingFinish":
                result = "分析完成";
                break;

        }
        t_status.setText(result);
        return view;
    }
}
