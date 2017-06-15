package com.zhouss.www.gitlabapp.util;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zs on 2017/6/8.
 */

public class HttpUtil {

    //发送GET请求
//    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
//        if (!isNetworkAvailable()) {
//            Toast.makeText(MyApplication.getContext(), "network is unavailable", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection = null;
//                try {
//                    URL url = new URL(address);
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    connection.setDoInput(true);
//                    connection.setDoOutput(true);
//                    InputStream in = connection.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                    if (listener != null) {
//                        // 回调onFinish()方法
//                        listener.onFinish(response.toString());
//                    }
//                } catch (Exception e) {
//                    if (listener != null) {
//                        // 回调onError()方法
//                        listener.onError(e);
//                    }
//                } finally {
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//
//    }
//
//    private static boolean isNetworkAvailable() {
//        return true;
//    }

//    HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
//        @Override
//        public void onFinish(String response) {
//            // 在这里根据返回内容执行具体的逻辑
//        }
//        @Override
//        public void onError(Exception e) {
//            // 在这里对异常情况进行处理
//        }
//    });

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String URL = "http://115.29.184.56:8090/api/";

    public static void sendGetOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendPostOkHttpRequest(String address, RequestBody body,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
