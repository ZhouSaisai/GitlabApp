package com.zhouss.www.gitlabapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.Question;

import java.util.List;

/**
 * Created by zs on 2017/7/2.
 */

public class QuestionAdapter extends ArrayAdapter<Question>{

    private int resourceId;

    public QuestionAdapter(Context context, int resource, List<Question> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        Question question_item = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else{
            view = convertView;
        }

        TextView q_id = (TextView) view.findViewById(R.id.q_id);
        TextView q_title = (TextView) view.findViewById(R.id.q_title);
        TextView q_level = (TextView) view.findViewById(R.id.q_level);

        q_id.setText(question_item.getqId()+"");
        q_title.setText(question_item.getTitle());

        q_level.setText(question_item.getDifficulty());
        return view;
    }
}
