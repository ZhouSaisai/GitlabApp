package com.zhouss.www.gitlabapp.activity;

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
        back_button.setOnClickListener(this);
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
            case R.id.back_button:
                finish();
                break;
        }
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
