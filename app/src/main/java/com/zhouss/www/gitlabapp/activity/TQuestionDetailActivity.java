package com.zhouss.www.gitlabapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.Creator;
import com.zhouss.www.gitlabapp.model.Question;

/**
 * Created by zs on 2017/7/2.
 */

public class TQuestionDetailActivity extends BaseActivity implements View.OnClickListener{

    //组件区
    private TextView title_bar;
    private Button edit_button;
    private Button back_button;
    private Button back_bottom_btn;

    //问题详情
    private TextView q_title;
    private TextView q_level;
    private TextView q_url;
    private TextView q_description;
    private TextView q_creator_username;
    private TextView q_creator_name;
    private TextView q_creator_email;

    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.t_question_detail);

        title_bar = (TextView) findViewById(R.id.title_bar);
        back_button = (Button) findViewById(R.id.back_button);
        edit_button = (Button)  findViewById(R.id.edit_button);
        back_bottom_btn = (Button) findViewById(R.id.back_bottom_btn);
        back_bottom_btn.setOnClickListener(this);
        back_button.setOnClickListener(this);
        edit_button.setOnClickListener(this);

        q_title = (TextView) findViewById(R.id.q_title);
        q_level = (TextView) findViewById(R.id.q_level);
        q_url = (TextView) findViewById(R.id.q_url);
        q_description = (TextView) findViewById(R.id.q_description);
        q_creator_username = (TextView) findViewById(R.id.q_creator_username);
        q_creator_name = (TextView) findViewById(R.id.q_creator_name);
        q_creator_email = (TextView) findViewById(R.id.q_creator_email);

        Intent intent = getIntent();
        question = (Question) intent.getSerializableExtra("question");
        title_bar.setText(question.getTitle());
        q_title.setText(question.getTitle());
        q_level.setText(question.getDifficulty());
        q_url.setText(question.getGitUrl());
        q_description.setText(question.getDescription());
        Creator creator = question.getCreator();
        q_creator_username.setText(creator.getUsername());
        q_creator_name.setText(creator.getName());
        q_creator_email.setText(creator.getEmail());

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.edit_button:
                Toast.makeText(this, "后台没有接口哦！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back_button:
            case R.id.back_bottom_btn:
                finish();
                break;
        }
    }
}
