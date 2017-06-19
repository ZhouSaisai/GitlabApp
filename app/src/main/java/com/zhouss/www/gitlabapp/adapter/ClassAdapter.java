package com.zhouss.www.gitlabapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.Class;

import java.util.List;

/**
 * Created by zs on 2017/6/19.
 */

public class ClassAdapter extends ArrayAdapter<Class> {
    private int resourceId;

    public ClassAdapter(Context context, int textViewResourceId,List<Class> objects) {
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Class class_item = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else{
            view = convertView;
        }

        TextView class_id = (TextView) view.findViewById(R.id.class_id);
        TextView class_name = (TextView) view.findViewById(R.id.class_name);
        class_id.setText(class_item.getcId()+"");
        class_name.setText(class_item.getName());

        return view;
    }
}
