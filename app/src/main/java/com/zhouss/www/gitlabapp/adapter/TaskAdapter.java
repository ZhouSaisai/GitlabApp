package com.zhouss.www.gitlabapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.Task;
import com.zhouss.www.gitlabapp.util.TaskStatusUtil;

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
        String result = TaskStatusUtil.getTypeString(status);
        t_status.setText(result);
        return view;
    }
}
