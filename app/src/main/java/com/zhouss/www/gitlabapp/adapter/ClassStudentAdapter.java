package com.zhouss.www.gitlabapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.ClassStudent;

import java.util.List;

/**
 * Created by zs on 2017/6/19.
 */

public class ClassStudentAdapter extends ArrayAdapter<ClassStudent> {
    private int resourceId;

    public ClassStudentAdapter(Context context, int textViewResourceId, List<ClassStudent> objects) {
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassStudent student_item = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else{
            view = convertView;
        }

        TextView class_id = (TextView) view.findViewById(R.id.class_id);
        TextView class_name = (TextView) view.findViewById(R.id.class_name);
        class_id.setText(student_item.getUsername()+"");
        class_name.setText(student_item.getName());

        return view;
    }
}
