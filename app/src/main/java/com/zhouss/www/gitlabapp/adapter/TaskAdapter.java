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

        TextView class_id = (TextView) view.findViewById(R.id.task_item_id);
        TextView class_name = (TextView) view.findViewById(R.id.task_item_name);
        class_id.setText(task_item.gettId()+"");
        class_name.setText(task_item.getTitle());

        return view;
    }
}
