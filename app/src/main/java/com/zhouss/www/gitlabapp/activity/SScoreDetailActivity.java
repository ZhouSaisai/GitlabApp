package com.zhouss.www.gitlabapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.adapter.TestAdapter;
import com.zhouss.www.gitlabapp.model.MetricData;
import com.zhouss.www.gitlabapp.model.TestCase;
import com.zhouss.www.gitlabapp.model.TestResult;
import com.zhouss.www.gitlabapp.model.TestScore;
import com.zhouss.www.gitlabapp.model.TestScoreResult;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.JSONUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zs on 2017/7/6.
 */

public class SScoreDetailActivity extends BaseActivity implements View.OnClickListener{
    //信号区
    public static final int QUERY_FAIL = 1;
    public static final int QUERY_SUCCESS = 2;

    //组件区
    private Button back_button;
    private Button back_bottom_btn;

    private TextView s_state;
    private TextView s_url;
    private TextView s_score;

    private TextView m_state;
    private TextView m_total_line;
    private TextView m_comment_line;
    private TextView m_field_count;
    private TextView m_method_count;
    private TextView m_max_coc;

    private ListView test_list_view;
    private List<TestCase> testList = new ArrayList<>();
    private TestAdapter testAdapter;

    private TestScore testScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.s_score_detail);

        back_button = (Button) findViewById(R.id.back_button);
        back_bottom_btn = (Button) findViewById(R.id.back_bottom_btn);
        back_bottom_btn.setOnClickListener(this);
        back_button.setOnClickListener(this);

        s_state = (TextView) findViewById(R.id.s_state);
        s_url = (TextView) findViewById(R.id.s_url);
        s_score = (TextView) findViewById(R.id.s_score);

        m_state = (TextView) findViewById(R.id.m_state);
        m_total_line = (TextView) findViewById(R.id.m_total_line);
        m_comment_line = (TextView) findViewById(R.id.m_comment_line);
        m_field_count = (TextView) findViewById(R.id.m_field_count);
        m_method_count = (TextView) findViewById(R.id.m_method_count);
        m_max_coc = (TextView) findViewById(R.id.m_max_coc);

        test_list_view = (ListView) findViewById(R.id.test_list);
        testAdapter = new TestAdapter(MyApplication.getContext(),R.layout.test_item,testList);
        test_list_view.setAdapter(testAdapter);

        queryFromServer();

    }

    private void updateUI(){
        TestScoreResult scoreResult = testScore.getScoreResult();
        MetricData metric = testScore.getMetricData();
        TestResult testResult = testScore.getTestResult();

        if(scoreResult.isScored()) {
            s_state.setText("已评分");
        }else{
            s_state.setText("未评分");
        }
        s_url.setText(scoreResult.getGit_url());
        s_score.setText(scoreResult.getScore()+"分");


        if(metric.isMeasured()) {
            m_state.setText("已度量");
        }else{
            m_state.setText("未度量");
        }
        m_total_line.setText(metric.getTotal_line_count()+"");
        m_comment_line.setText(metric.getComment_line_count()+"");
        m_field_count.setText(metric.getField_count()+"");
        m_method_count.setText(metric.getMethod_count()+"");
        m_max_coc.setText(metric.getMax_coc()+"");

        List<TestCase> cases = testResult.getTestcases();
        testList.clear();
        testList.addAll(cases);
        testAdapter.notifyDataSetChanged();
    }

    private void queryFromServer() {
        String address = HttpUtil.URL+"/assignment/38/student/254/analysis";
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
                testScore = JSONUtil.handlTestScoreResponse(resultData).get(1);
                Message message = new Message();
                message.what=QUERY_SUCCESS;
                handler.sendMessage(message);
            }
        });
    }

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_SUCCESS:
                    updateUI();
                    break;
                case QUERY_FAIL:
                    Toast.makeText(MyApplication.getContext(), "数据拉取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.back_button:
            case R.id.back_bottom_btn:
                finish();
                break;
        }
    }
}
