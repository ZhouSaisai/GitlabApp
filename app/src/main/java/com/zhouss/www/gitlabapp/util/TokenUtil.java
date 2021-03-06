package com.zhouss.www.gitlabapp.util;

import android.util.Base64;

import com.zhouss.www.gitlabapp.enums.UserType;

/**
 * Created by zs on 2017/6/12.
 */

public class TokenUtil {
    private static final String PRE = "Basic ";

    private static String token = "";
    private static UserType type = UserType.STUDENT;
    private static int u_id = 1;

    public static void setToken(String username,String password){
        String str = username+":"+password;
        String s=Base64.encodeToString(str.getBytes(),Base64.DEFAULT);
        token=PRE+s;
    }


    public static String getToken() {
        return token.trim();
    }

    public static UserType getType() {
        return type;
    }

    public static void setType(UserType type) {
        TokenUtil.type = type;
    }

    public static int getU_id() {
        return u_id;
    }

    public static void setU_id(int u_id) {
        TokenUtil.u_id = u_id;
    }
}
