package com.zhouss.www.gitlabapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.Score;

import java.util.List;

/**
 * Created by zs on 2017/7/3.
 */

public class ScoreListAdapter extends ArrayAdapter<Score>{

    private int resourceId;

    public ScoreListAdapter(Context context, int resource, List<Score> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Score score = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else{
            view = convertView;
        }

        TextView s_id = (TextView) view.findViewById(R.id.s_id);
        TextView s_number = (TextView) view.findViewById(R.id.s_number);
        TextView s_name = (TextView) view.findViewById(R.id.s_name);
        TextView s_score = (TextView) view.findViewById(R.id.s_score);

        s_id.setText(score.getStudentId()+"");
        s_number.setText(score.getStudentNumber());
        s_name.setText(score.getStudentName());
        if(score.isScored()){
            s_score.setText(score.getScore()+"分");
        }else{
            s_score.setText("未评分");
        }
        return view;
    }
}

