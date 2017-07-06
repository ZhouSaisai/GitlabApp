package com.zhouss.www.gitlabapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.adapter.ScoreListAdapter;
import com.zhouss.www.gitlabapp.model.AnalyseData;
import com.zhouss.www.gitlabapp.model.QuestionInfo;
import com.zhouss.www.gitlabapp.model.QuestionScore;
import com.zhouss.www.gitlabapp.model.Score;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.JSONUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zs on 2017/7/2.
 */

public class TTaskScoreActivity extends BaseActivity implements View.OnClickListener{
    //信号区
    public static final int QUERY_FAIL = 1;
    public static final int QUERY_SUCCESS = 2;

    //组件区
    private Button back_button;
    private Button back_bottom_btn;
    private Button scores_analyse;

    private TextView st_name;
    private TextView st_id;
    private TextView st_description;

    private ListView scoreListView;
    private List<Score> scoreList = new ArrayList<>();
    private List<Score> tempList;
    private ScoreListAdapter scoreAdpter;

    private QuestionScore result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_task_score);

        back_button = (Button) findViewById(R.id.back_button);
        back_bottom_btn = (Button) findViewById(R.id.back_bottom_btn);
        scores_analyse = (Button) findViewById(R.id.scores_analyse);
        back_bottom_btn.setOnClickListener(this);
        back_button.setOnClickListener(this);
        scores_analyse.setOnClickListener(this);

        st_name = (TextView) findViewById(R.id.st_name);
        st_id = (TextView) findViewById(R.id.st_id);
        st_description = (TextView) findViewById(R.id.st_description);

        scoreListView = (ListView) findViewById(R.id.score_list);
        scoreAdpter = new ScoreListAdapter(MyApplication.getContext(),R.layout.score_item,scoreList);
        scoreListView.setAdapter(scoreAdpter);

        int i = new Random().nextInt(2);
        if(i==1){
            queryScores(93);
        }else{
            queryScores(38);
        }
    }

    /**
     * 查询老师对应的全部班级
     */
    private void queryScores(int type){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        String content = prefs.getString("score"+type,null);
        if(content!=null){
            List<QuestionScore> questionScores = JSONUtil.handlScoreResponse(content);
            if(!questionScores.isEmpty()){
                result = questionScores.get(0);
                showInfo();
            }
        }
        else {
            String address = HttpUtil.URL+"/assignment/"+type+"/score";
            queryFromServer(address,type);
        }

    }

    private void queryFromServer(String address, final int type) {
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
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("score"+type,resultData);
                editor.apply();

                Message message = new Message();
                message.what=QUERY_FAIL;

                List<QuestionScore> questionScores = JSONUtil.handlScoreResponse(resultData);
                if(!questionScores.isEmpty()){
                    result = questionScores.get(0);
                    message.what=QUERY_SUCCESS;
                }
                handler.sendMessage(message);
            }
        });
    }

    private void showInfo() {
        QuestionInfo info = result.getQuestionInfo();
        tempList = result.getStudents();

        st_id.setText(info.getId()+"");
        st_name.setText(info.getTitle());
        st_description.setText(info.getDescription());

        scoreList.clear();
        scoreList.addAll(tempList);
        scoreAdpter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.back_bottom_btn:
            case R.id.back_button:
                finish();
                break;
            case R.id.scores_analyse:
                AnalyseData data = analyseList();
                Intent intent = new Intent(TTaskScoreActivity.this,TTaskScoreAnalyseActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
                break;
        }
    }

    private AnalyseData analyseList() {
        int max = 0;
        int min = 100;
        int sum = 0;
        int count = 0;
        int noCount = 0;
        int m90 = 0;
        int m80 = 0;
        int m60 = 0;
        int m0 = 0;
        double ave =0.0;
        for(Score score:scoreList){
            if(!score.isScored()){
                noCount++;
                continue;
            }
            int s = score.getScore();
            if(max<s) max=s;
            if(min>s) min=s;
            sum+=s;
            count++;
            if(s<60){
                m0++;
            }else if(s<80){
                m60++;
            }else if(s<90){
                m80++;
            }else{
                m90++;
            }
        }
        if(count!=0) {
            ave = sum * 1.0 / count;
        }
        AnalyseData data = new AnalyseData();
        data.setMax_score(max);
        data.setMin_score(min);
        data.setAve_score(ave);
        data.setS_count(scoreList.size());
        data.setS0(m0);
        data.setS60(m60);
        data.setS80(m80);
        data.setS90(m90);
        data.setSno(noCount);
        return data;
    }

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_SUCCESS:
                    showInfo();
                    break;
                case QUERY_FAIL:
                    Toast.makeText(MyApplication.getContext(), "数据拉取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
