package com.zhouss.www.gitlabapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.adapter.QuestionAdapter;
import com.zhouss.www.gitlabapp.enums.UserType;
import com.zhouss.www.gitlabapp.model.Question;
import com.zhouss.www.gitlabapp.model.Task;
import com.zhouss.www.gitlabapp.util.MyApplication;
import com.zhouss.www.gitlabapp.util.TaskStatusUtil;
import com.zhouss.www.gitlabapp.util.TokenUtil;

import java.util.List;

/**
 * Created by zs on 2017/7/2.
 */

public class TTaskDetailActivity extends BaseActivity implements View.OnClickListener{
    //组件区
    private TextView title_bar;
    private Button edit_button;
    private Button back_button;

    private Button score_analyse;
    private Button bottom_back_btn;

    //任务详情
    private TextView t_title;
    private TextView t_item_id;
    private TextView t_start;
    private TextView t_end;
    private TextView t_description;
    private TextView t_status;

    private ListView questionListView;
    private List<Question> questionList;
    private QuestionAdapter questionAdapter;

    private Task task;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.t_task_detail);

        title_bar = (TextView) findViewById(R.id.title_bar);
        back_button = (Button) findViewById(R.id.back_button);
        edit_button = (Button)  findViewById(R.id.edit_button);
        back_button.setOnClickListener(this);
        edit_button.setOnClickListener(this);

        score_analyse = (Button) findViewById(R.id.score_analyse);
        bottom_back_btn = (Button) findViewById(R.id.back_bottom_btn);
        score_analyse.setOnClickListener(this);
        bottom_back_btn.setOnClickListener(this);

        t_title = (TextView) findViewById(R.id.t_title);
        t_item_id = (TextView) findViewById(R.id.t_item_id);
        t_start = (TextView) findViewById(R.id.t_start);
        t_end = (TextView) findViewById(R.id.t_end);
        t_description = (TextView) findViewById(R.id.t_description);
        t_status = (TextView) findViewById(R.id.t_status);

        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task");
        title_bar.setText(task.getTitle());
        t_title.setText(task.getTitle());
        t_item_id.setText(task.gettId()+"");
        t_status.setText(TaskStatusUtil.getTypeString(task.getStatus()));
        t_start.setText(task.getStartAt().substring(0,16));
        t_end.setText(task.getEndAt().substring(0,16));
        t_description.setText(task.getDescription());

        questionListView = (ListView) findViewById(R.id.question_list);
        questionList = task.getQuestions();
        questionAdapter = new QuestionAdapter(MyApplication.getContext(),R.layout.question_item,questionList);
        questionListView.setAdapter(questionAdapter);

        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                question = questionList.get(position);
                Intent intent = new Intent(TTaskDetailActivity.this, TQuestionDetailActivity.class);
                intent.putExtra("question",question);
                startActivity(intent);
            }
        });

        if(TokenUtil.getType()== UserType.TEACHER){
            score_analyse.setVisibility(View.GONE);
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
            case R.id.score_analyse:
                Intent intent = new Intent(TTaskDetailActivity.this, STaskScoreAnalyseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
