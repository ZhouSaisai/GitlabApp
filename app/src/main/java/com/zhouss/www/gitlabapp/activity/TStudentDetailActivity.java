package com.zhouss.www.gitlabapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.ClassStudent;

/**
 * Created by zs on 2017/7/1.
 */

public class TStudentDetailActivity extends BaseActivity implements View.OnClickListener{
    //组件区
    private TextView title_bar;
    private Button edit_button;
    private Button back_button;
    private Button back_bottom_btn;

    private TextView s_name;
    private TextView s_email;
    private TextView s_username;
    private TextView s_number;
    private TextView s_git;
    private TextView s_class;
    private TextView s_school;

    private ImageView s_sex;

    //选中的学生
    private ClassStudent selectStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.t_student_detail);

        title_bar = (TextView) findViewById(R.id.title_bar);
        back_button = (Button) findViewById(R.id.back_button);
        edit_button = (Button)  findViewById(R.id.edit_button);
        back_bottom_btn = (Button)  findViewById(R.id.back_bottom_btn);
        back_button.setOnClickListener(this);
        edit_button.setOnClickListener(this);
        back_bottom_btn.setOnClickListener(this);

        s_name = (TextView) findViewById(R.id.s_name);
        s_email = (TextView) findViewById(R.id.s_email);
        s_username = (TextView) findViewById(R.id.s_username);
        s_number = (TextView) findViewById(R.id.s_number);
        s_git = (TextView) findViewById(R.id.s_git);
        s_class = (TextView) findViewById(R.id.s_class);
        s_school = (TextView) findViewById(R.id.s_school);
        s_sex = (ImageView) findViewById(R.id.s_sex);

        Intent intent = getIntent();
        selectStudent = (ClassStudent) intent.getSerializableExtra("selectStudent");
        title_bar.setText(selectStudent.getUsername());

        s_name.setText(selectStudent.getName());
        s_email.setText(selectStudent.getEmail());
        s_username.setText(selectStudent.getUsername());
        s_number.setText(selectStudent.getNumber());
        s_git.setText(selectStudent.getGitUsername());
        s_class.setText(selectStudent.getGroupId()+"");
        s_school.setText(selectStudent.getSchoolId()+"");

        String sex = selectStudent.getGender();
        switch (sex){
            case "male":
                s_sex.setImageResource(R.drawable.boy);
                break;
            case "female":
                s_sex.setImageResource(R.drawable.girl);
        }


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.edit_button:
                Toast.makeText(this, "后台没有接口哦！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back_bottom_btn:
            case R.id.back_button:
                finish();
                break;
        }
    }
}
