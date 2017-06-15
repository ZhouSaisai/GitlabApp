package com.zhouss.www.gitlabapp.model;

import java.io.Serializable;

/**
 * Created by zs on 2017/6/12.
 */

public class Student implements Serializable {
    //用户基本信息
    private UserInfo info;
    //git账号
    private int gitId;
    //git用户名
    private String gitUsername;
    //学号，如"141250123"
    private String number;

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public int getGitId() {
        return gitId;
    }

    public void setGitId(int gitId) {
        this.gitId = gitId;
    }

    public String getGitUsername() {
        return gitUsername;
    }

    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "info=" + info.toString() +
                ", gitId=" + gitId +
                ", gitUsername=" + gitUsername +
                ", number='" + number + '\'' +
                '}';
    }
}
