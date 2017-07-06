package com.zhouss.www.gitlabapp.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.enums.Sex;
import com.zhouss.www.gitlabapp.enums.UserType;
import com.zhouss.www.gitlabapp.model.Student;
import com.zhouss.www.gitlabapp.model.Teacher;
import com.zhouss.www.gitlabapp.model.UserInfo;
import com.zhouss.www.gitlabapp.util.HttpUtil;
import com.zhouss.www.gitlabapp.util.MyApplication;
import com.zhouss.www.gitlabapp.util.TokenUtil;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zs on 2017/6/14.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAIL = 2;
    public static final int LOGIN_ERROR = 3;

    // 声明控件对象-用户，密码
    private EditText et_name, et_pass;
    //登录，忘记密码，注册
    private Button mLoginButton,mForget,mRegister;
    //清除*2，查看密码
    private Button bt_username_clear;
    private Button bt_pwd_clear;
    private Button bt_pwd_eye;

    //编辑监听器
    private TextWatcher username_watcher;
    private TextWatcher password_watcher;

    //异步更新UI-hander
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOGIN_SUCCESS:
                    Intent intent = new Intent();
                    if(TokenUtil.getType()==UserType.TEACHER){
                        intent.setClass(MyApplication.getContext(),THomeActivity.class);
                    }else{
                        intent.setClass(MyApplication.getContext(),SHomeActivity.class);
                    }
                    startActivity(intent);
                    finish();
                    break;
                case LOGIN_FAIL:
                    Toast.makeText(MyApplication.getContext(), "用户名和密码都要输入哦！", Toast.LENGTH_SHORT).show();
                    break;
                case LOGIN_ERROR:
                    Toast.makeText(MyApplication.getContext(), "用户名或密码输入错误！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.login);

        et_name = (EditText) findViewById(R.id.username);
        et_pass = (EditText) findViewById(R.id.password);

        bt_username_clear = (Button)findViewById(R.id.bt_username_clear);
        bt_pwd_clear = (Button)findViewById(R.id.bt_pwd_clear);
        bt_pwd_eye = (Button)findViewById(R.id.bt_pwd_eye);
        bt_username_clear.setOnClickListener(this);
        bt_pwd_clear.setOnClickListener(this);
        bt_pwd_eye.setOnClickListener(this);

        initWatcher();
        et_name.addTextChangedListener(username_watcher);
        et_pass.addTextChangedListener(password_watcher);

        mLoginButton = (Button) findViewById(R.id.login);
        mForget  = (Button) findViewById(R.id.login_forget);
        mRegister    = (Button) findViewById(R.id.register);

        mLoginButton.setOnClickListener(this);
        mForget.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    private void initWatcher() {
        username_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                et_pass.setText("");
                if(s.toString().length()>0){
                    bt_username_clear.setVisibility(View.VISIBLE);
                }else{
                    bt_username_clear.setVisibility(View.INVISIBLE);
                }
            }
        };

        password_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    bt_pwd_clear.setVisibility(View.VISIBLE);
                }else{
                    bt_pwd_clear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:  //登陆
                login();
                break;
            case R.id.login_forget: //无法登陆(忘记密码)
                Toast.makeText(this, "后台没有接口哦！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register:    //注册新的用户
                Toast.makeText(this, "这个也没有接口哦！", Toast.LENGTH_SHORT).show();
                break;

           //清除系列
            case R.id.bt_username_clear:
                et_name.setText("");
                et_pass.setText("");
                break;
            case R.id.bt_pwd_clear:
                et_pass.setText("");
                break;
            case R.id.bt_pwd_eye:
                if(et_pass.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                    bt_pwd_eye.setBackgroundResource(R.drawable.eye);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                }else{
                    bt_pwd_eye.setBackgroundResource(R.drawable.eye);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                et_pass.setSelection(et_pass.getText().toString().length());
                break;
        }
    }

    /**
     * 登陆
     */
    private void login() {
        //构造参数
        final String username = et_name.getText().toString();
        final String password = et_pass.getText().toString();
        if(username.equals("") || password.equals("")){
            Toast.makeText(this, "用户名和密码都要输入哦！", Toast.LENGTH_SHORT).show();
            return;
        }
        TokenUtil.setToken(username,password);

        String json = "{\n" +
                "  \"username\":\""+username+"\",\n" +
                "  \"password\":\""+password+"\"\n" +
                "}";

        final RequestBody requestBody = RequestBody.create(HttpUtil.MEDIA_TYPE_JSON,json);
        String url = HttpUtil.URL+"/user/auth";

        HttpUtil.sendPostOkHttpRequest(url,requestBody,new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what=LOGIN_FAIL;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData  = response.body().string();
                if(responseData==null || responseData.equals("")){
                    Message message = new Message();
                    message.what=LOGIN_ERROR;
                    handler.sendMessage(message);
                }else{
                    parseJSON(responseData);
                    Message message = new Message();
                    message.what=LOGIN_SUCCESS;
                    handler.sendMessage(message);
                }
            }
        });

    }

    private void parseJSON(String responseData) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user",responseData);
        editor.apply();

        try{
            JSONObject json = new JSONObject(responseData);
            String type = json.getString("type");
            //构造用户对象
            UserInfo info = new UserInfo();
            info.setUsername(json.getString("username"));
            info.setName(json.getString("name"));
            info.setAvatar(json.getString("avatar"));
            info.setGender(Sex.valueOf(json.getString("gender").toUpperCase()));
            info.setEmail(json.getString("email"));
            info.setSchoolId(json.getInt("schoolId"));
            //细化类型
            if(type.equals("teacher")){
                TokenUtil.setType(UserType.TEACHER);
                info.setType(UserType.TEACHER);
                Teacher teacher = new Teacher();
                teacher.setAuthority(json.getInt("authority"));
                teacher.setInfo(info);
            }else if(type.equals("student")){
                TokenUtil.setType(UserType.STUDENT);
                info.setType(UserType.STUDENT);
                Student student = new Student();
                student.setInfo(info);
                student.setGitId(json.getInt("gitId"));
                student.setGitUsername(json.getString("gitUsername"));
                student.setNumber(json.getString("number"));
                Log.d("student",student.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
