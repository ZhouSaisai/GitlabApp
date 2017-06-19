package com.zhouss.www.gitlabapp.util;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by zs on 2017/6/16.
 */

public class ProgressUtil {

    private static ProgressDialog progressDialog;

    /**
     * 显示进度对话框
     */
    public static void showProgressDialog(Activity activity) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    public static void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
