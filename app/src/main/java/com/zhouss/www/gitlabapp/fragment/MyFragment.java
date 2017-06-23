package com.zhouss.www.gitlabapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.activity.ActivityCollector;
import com.zhouss.www.gitlabapp.activity.LoginActivity;
import com.zhouss.www.gitlabapp.activity.THomeActivity;
import com.zhouss.www.gitlabapp.util.MyApplication;

/**
 * Created by zs on 2017/6/16.
 */

public class MyFragment extends Fragment {
    //组件区
    private TextView title_bar;
    private Button add_button;
    private Button back_button;

    private Button login_out;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof THomeActivity) {
            THomeActivity activity = (THomeActivity) context;
            title_bar = (TextView) activity.findViewById(R.id.title_bar);
            add_button = (Button) activity.findViewById(R.id.add_button);
            back_button = (Button) activity.findViewById(R.id.back_button);
        }
        title_bar.setText("个人空间");
        add_button.setVisibility(View.INVISIBLE);
        back_button.setVisibility(View.INVISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.t_my_frag, container, false);

        login_out = (Button) view.findViewById(R.id.login_out);
        login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
