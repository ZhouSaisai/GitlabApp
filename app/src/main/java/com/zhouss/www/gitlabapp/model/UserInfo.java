package com.zhouss.www.gitlabapp.model;

import com.zhouss.www.gitlabapp.enums.Sex;
import com.zhouss.www.gitlabapp.enums.UserType;

/**
 * Created by zs on 2017/6/12.
 */

public class UserInfo {
    //用户名
    private String username;
    //姓名
    private String name;
    //类型：student|teacher|admin
    private UserType type;
    //image url
    private String avatar;
    //性别
    private Sex gender;
    //邮箱："xxx@xxx.com"
    private String email;
    //TODO 学校id
    private int schoolId;


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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
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
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }
}
