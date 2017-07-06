package com.zhouss.www.gitlabapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.AnalyseData;
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

public class TTaskScoreAnalyseActivity extends BaseActivity implements View.OnClickListener {

    //组件区
    private Button back_button;
    private Button back_bottom_btn;

    private TextView max_score;
    private TextView min_score;
    private TextView ave_score;
    private TextView s_count;

    private TextView s90;
    private TextView s80;
    private TextView s60;
    private TextView s0;
    private TextView sno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.t_score_analyse);

        back_button = (Button) findViewById(R.id.back_button);
        back_bottom_btn = (Button) findViewById(R.id.back_bottom_btn);
        back_bottom_btn.setOnClickListener(this);
        back_button.setOnClickListener(this);

        max_score = (TextView) findViewById(R.id.max_score);
        min_score = (TextView) findViewById(R.id.min_score);
        ave_score = (TextView) findViewById(R.id.ave_score);
        s_count = (TextView) findViewById(R.id.s_count);

        s90 = (TextView) findViewById(R.id.s90);
        s80 = (TextView) findViewById(R.id.s80);
        s60 = (TextView) findViewById(R.id.s60);
        s0 = (TextView) findViewById(R.id.s0);
        sno = (TextView) findViewById(R.id.sno);

        Intent intent = getIntent();
        AnalyseData data = (AnalyseData) intent.getSerializableExtra("data");
        max_score.setText(data.getMax_score()+"");
        min_score.setText(data.getMin_score()+"");
        ave_score.setText((data.getAve_score()+"").substring(0,5));
        s_count.setText(data.getS_count()+"");
        s90.setText(data.getS90()+"");
        s80.setText(data.getS80()+"");
        s60.setText(data.getS60()+"");
        s0.setText(data.getS0()+"");
        sno.setText(data.getSno()+"");
    }


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
