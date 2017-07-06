package com.zhouss.www.gitlabapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.TestCase;

import java.util.List;

/**
 * Created by zs on 2017/7/6.
 */

public class TestAdapter extends ArrayAdapter<TestCase> {
    private int resourceId;

    public TestAdapter(Context context, int resource, List<TestCase> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        TestCase testcase = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else{
            view = convertView;
        }

        TextView name = (TextView) view.findViewById(R.id.test_name);
        ImageView state = (ImageView) view.findViewById(R.id.test_state);
        name.setText(testcase.getName());
        if(testcase.isPassed()){
            state.setImageResource(R.drawable.yes);
        }else{
            state.setImageResource(R.drawable.no);
        }
        return view;
    }
}
