package com.zhouss.www.gitlabapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.activity.THomeActivity;
import com.zhouss.www.gitlabapp.model.Class;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.JSONUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;
import com.zhouss.www.gitlabapp.util.ProgressUtil;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zs on 2017/6/16.
 */

public class TaskFragment extends Fragment {
    //信号区
    public static final int QUERY_FAIL = 1;
    public static final int QUERY_CLASS = 2;
    public static final int QUERY_STUDENT = 3;


    //组件区
    private TextView title_bar;
    private Button back_button;

//    private ListView listView;
    //list区
    private List<Class> classList = new ArrayList<>();

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case QUERY_CLASS:
                    ProgressUtil.closeProgressDialog();
                    queryAllClass();
                    break;
                case QUERY_FAIL:
                    Toast.makeText(MyApplication.getContext(), "数据拉取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof THomeActivity) {
            THomeActivity activity = (THomeActivity) context;
            title_bar = (TextView) activity.findViewById(R.id.title_bar);
            back_button = (Button) activity.findViewById(R.id.back_button);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.t_student_frag, container, false);

        queryAllClass();

        return view;
    }

    /**
     * 查询老师对应的全部班级
     */
    private void queryAllClass(){
        title_bar.setText("我的班级");
        classList = DataSupport.findAll(Class.class);
        if(classList.size()>0){
//            listView.setSelection(0);
            Log.d("size",classList.size()+"");
        } else {
            Log.d("info","getData");
            String address = HttpUtil.URL+"/group";
            queryFromServer(address,"class_icon");
        }

    }

    private void queryFromServer(String address, final String type) {
        ProgressUtil.showProgressDialog(getActivity());
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
                Log.d("data",resultData);
                boolean result = false;
                if(type.equals("class_icon")){
                    result = JSONUtil.handlClassResponse(resultData);
                }

                if(result){
                    Message message = new Message();
                    if(type.equals("class_icon")){
                        message.what=QUERY_CLASS;
                    }
                    handler.sendMessage(message);
                }

            }
        });
    }
}
