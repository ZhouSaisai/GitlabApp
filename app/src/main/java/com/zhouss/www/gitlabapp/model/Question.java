package com.zhouss.www.gitlabapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zs on 2017/6/12.
 */

public class Question implements Serializable{
    //"id": 1,
    @SerializedName("id")
    private int qId;

    //"title": "题目1",
    private String title;

    //"description": "题目1",
    private String description;

    //"difficulty": "3",
    private String difficulty;

    //"gitUrl": "http://115.29.184.56:10080/kenny67nju/Homework12-Curriculum.git",
    private String gitUrl;

    //"type": "exam",
    private String type;

    //创建者
    private Creator creator;

    //"duration": 0,
    private int duration;

    //"link": -1,
    private int link;

    //"knowledgeVos": null
    private String knowledgeVos;


    public Question() {
    }

    public Question(int qId, String title, String description, String difficulty, String gitUrl, String type, Creator creator, int duration, int link, String knowledgeVos) {
        this.qId = qId;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.gitUrl = gitUrl;
        this.type = type;
        this.creator = creator;
        this.duration = duration;
        this.link = link;
        this.knowledgeVos = knowledgeVos;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
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

    @Override
    public String toString() {
        return "Question{" +
                "qId=" + qId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", type='" + type + '\'' +
                ", creator=" + creator.toString() +
                ", duration=" + duration +
                ", link=" + link +
                ", knowledgeVos='" + knowledgeVos + '\'' +
                '}';
    }
}

