package com.zhouss.www.gitlabapp.model;

import java.io.Serializable;

/**
 * Created by zs on 2017/7/3.
 */

public class QuestionInfo implements Serializable{
    //"id": 1,
    private int id;

    //"title": "题目1",
    private String title;

    //"description": "题目1",
    private String description;

    //"type": "exam"
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
