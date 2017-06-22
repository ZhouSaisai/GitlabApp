package com.zhouss.www.gitlabapp.model;

import org.litepal.crud.DataSupport;

/**
 * Created by zs on 2017/6/19.
 */

public class ClassStudent extends DataSupport{
    //学生id
    private int csId;
    //用户名: "131250106",
    private String username;
    //姓名: "lsf"
    private String name;
    //类型：student|teacher|admin
    private String type;
    //image url: null
    private String avatar;
    //性别: "female"
    private String gender;
    //邮箱："xxx@xxx.com"
    private String email;
    //学校id:1
    private int schoolId;
    //git账号: 367
    private int gitId;
    //git用户名 "gitUsername": "131250106"
    private String gitUsername;
    //学号，如"141250123"
    private String number;
    //班级id:1
    private int groupId;

    public ClassStudent() {
    }

    public ClassStudent(int csId, String username, String name, String type, String avatar, String gender, String email, int schoolId, int gitId, String gitUsername, String number, int groupId) {
        this.csId = csId;
        this.username = username;
        this.name = name;
        this.type = type;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.schoolId = schoolId;
        this.gitId = gitId;
        this.gitUsername = gitUsername;
        this.number = number;
        this.groupId = groupId;
    }

    public int getCsId() {
        return csId;
    }

    public void setCsId(int csId) {
        this.csId = csId;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
