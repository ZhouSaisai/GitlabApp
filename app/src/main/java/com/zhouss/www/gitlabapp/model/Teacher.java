package com.zhouss.www.gitlabapp.model;

import java.io.Serializable;

/**
 * Created by zs on 2017/6/12.
 */

public class Teacher implements Serializable {
    //用户基本信息
    private UserInfo info;
    //是否认证
    private boolean authority;

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }
}
