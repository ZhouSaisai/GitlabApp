package com.zhouss.www.gitlabapp.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.enums.UserType;
import com.zhouss.www.gitlabapp.model.Creator;
import com.zhouss.www.gitlabapp.model.Question;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.JSONUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;
import com.zhouss.www.gitlabapp.util.TokenUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zs on 2017/7/2.
 */

public class TQuestionDetailActivity extends BaseActivity implements View.OnClickListener{
    //信号区
    public static final int QUERY_FAIL = 1;
    public static final int QUERY_SUCCESS = 2;

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
    private String content;

    //学生对应操作
    private Button read_me;
    private Button exam_analyse;
    //老师对应操作
    private Button look_score;

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

        read_me = (Button) findViewById(R.id.read_me);
        exam_analyse = (Button) findViewById(R.id.exam_analyse);
        look_score = (Button) findViewById(R.id.look_score);
        read_me.setOnClickListener(this);
        exam_analyse.setOnClickListener(this);
        look_score.setOnClickListener(this);

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

        if(TokenUtil.getType()== UserType.TEACHER){
            read_me.setVisibility(View.GONE);
            exam_analyse.setVisibility(View.GONE);
        }else if(TokenUtil.getType()==UserType.STUDENT){
            look_score.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
            case R.id.back_bottom_btn:
                finish();
                break;
            case R.id.read_me:
                queryReadMe();
                break;
            case R.id.exam_analyse:
                Intent intent = new Intent();
                intent.setClass(TQuestionDetailActivity.this,SScoreDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.look_score:
                Intent intent2 = new Intent(TQuestionDetailActivity.this, TTaskScoreActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void queryReadMe(){
        String address = HttpUtil.URL+"/assignment/98/student/227/question/26";
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
                content = JSONUtil.handlReadMeResponse(resultData);
                Message message = new Message();
                message.what=QUERY_SUCCESS;
                handler.sendMessage(message);
            }
        });
    }

    //提示信息
    public void showMsg(String msg) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(TQuestionDetailActivity.this);
        dlg.setTitle("README:");
        dlg.setMessage(msg);
        dlg.setPositiveButton("确定",null);
        dlg.show();
    }

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_SUCCESS:
                    showMsg(content);
                    break;
                case QUERY_FAIL:
                    Toast.makeText(MyApplication.getContext(), "数据拉取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
