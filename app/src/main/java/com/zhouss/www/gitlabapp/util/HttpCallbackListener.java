package com.zhouss.www.gitlabapp.util;

/**
 * Created by zs on 2017/6/8.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
