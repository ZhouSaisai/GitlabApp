package com.zhouss.www.gitlabapp.activity;

import android.content.Intent;
import android.os.Bundle;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.model.Task;

/**
 * Created by zs on 2017/7/2.
 */

public class TTaskDetailActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.t_task_detail);

        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra("task");
    }
}
