package com.zhouss.www.gitlabapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zs on 2017/6/11.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
