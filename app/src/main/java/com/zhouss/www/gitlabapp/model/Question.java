package com.zhouss.www.gitlabapp.model;

import com.zhouss.www.gitlabapp.enums.TaskType;

/**
 * Created by zs on 2017/6/12.
 */

class Question {
    //"id": 1,
    private int id;

    //"title": "题目1",
    private String title;

    //"description": "题目1",
    private String description;

    //"difficulty": "3",
    private String difficulty;

    //"gitUrl": "http://115.29.184.56:10080/kenny67nju/Homework12-Curriculum.git",
    private String gitUrl;

    //"type": "exam",
    private TaskType type;

    //创建者
    private Teacher creator;

    //"duration": 0,
    private int duration;

    //"link": -1,
    private int link;

    //TODO "knowledgeVos": null
    private String knowledgeVos;

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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Teacher getCreator() {
        return creator;
    }

    public void setCreator(Teacher creator) {
        this.creator = creator;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public String getKnowledgeVos() {
        return knowledgeVos;
    }

    public void setKnowledgeVos(String knowledgeVos) {
        this.knowledgeVos = knowledgeVos;
    }
}

