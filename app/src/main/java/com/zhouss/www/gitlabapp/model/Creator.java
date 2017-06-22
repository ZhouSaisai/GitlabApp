package com.zhouss.www.gitlabapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zs on 2017/6/12.
 */

public class Creator implements Serializable {
    //id
    @SerializedName("id")
    private int teacherId;
    //用户名
    private String username;
    //姓名
    private String name;
    //类型：student|teacher|admin
    private String type;
    //image url
    private String avatar;
    //性别
    private String gender;
    //邮箱："xxx@xxx.com"
    private String email;
    //学校id
    private int schoolId;


    public Creator() {
    }

    public Creator(int teacherId, String username, String name, String type, String avatar, String gender, String email, int schoolId) {
        this.teacherId = teacherId;
        this.username = username;
        this.name = name;
        this.type = type;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.schoolId = schoolId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }


    @Override
    public String toString() {
        return "Creator{" +
                "teacherId=" + teacherId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }
}
