package com.zhouss.www.gitlabapp.util;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by zs on 2017/6/9.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext() {
        return context;
    }
}
