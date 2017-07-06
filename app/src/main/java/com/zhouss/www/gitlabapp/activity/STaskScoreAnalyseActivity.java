package com.zhouss.www.gitlabapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.TestScore;
import com.zhouss.www.gitlabapp.model.TestScoreResult;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.JSONUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zs on 2017/7/6.
 */

public class STaskScoreAnalyseActivity extends BaseActivity implements View.OnClickListener {
    //信号区
    public static final int QUERY_FAIL = 1;
    public static final int QUERY_SUCCESS = 2;

    //组件区
    private Button back_button;
    private Button back_bottom_btn;

    private TextView max_score;
    private TextView min_score;
    private TextView ave_score;
    private TextView q_count;

    private List<TestScore> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.s_score_analyse);

        back_button = (Button) findViewById(R.id.back_button);
        back_bottom_btn = (Button) findViewById(R.id.back_bottom_btn);
        back_bottom_btn.setOnClickListener(this);
        back_button.setOnClickListener(this);

        max_score = (TextView) findViewById(R.id.max_score);
        min_score = (TextView) findViewById(R.id.min_score);
        ave_score = (TextView) findViewById(R.id.ave_score);
        q_count = (TextView) findViewById(R.id.q_count);

        queryFromServer();

    }

    public void updateUI(){
        int max = 0;
        int min = 100;
        int sum = 0;
        int count = 0;
        for(TestScore score:scores){
            TestScoreResult result = score.getScoreResult();
            int s = result.getScore();
            if(max<s) max=s;
            if(min>s) min=s;
            sum+=s;
            count++;
        }
        if(count!=0) {
            double ave = sum*1.0/count;
            max_score.setText(max+"");
            min_score.setText(min+"");
            ave_score.setText(ave+"");
            q_count.setText(count+"");
        }
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
                scores = JSONUtil.handlTestScoreResponse(resultData);
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
