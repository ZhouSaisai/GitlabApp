package com.zhouss.www.gitlabapp.model;

import com.zhouss.www.gitlabapp.enums.TaskType;

import java.util.List;

/**
 * Created by zs on 2017/6/12.
 */

public class Task {
    //"id": 3,
    private int id;

    //"title": "考试1",
    private String title;

    //"description": "考试1",
    private String description;

    //"startAt": "2017-04-25 16:22:47.0",
    private String startAt;

    //"endAt": "2017-04-25 16:46:47.0",
    private String endAt;

    //"questions":
    private List<Question> questions;

    //"course": 1,
    private int course;

    //"status":
    private TaskType status;

    //"currentTime": "2017-05-26 11:24:12"
    private String currentTime;

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

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public TaskType getStatus() {
        return status;
    }

    public void setStatus(TaskType status) {
        this.status = status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
