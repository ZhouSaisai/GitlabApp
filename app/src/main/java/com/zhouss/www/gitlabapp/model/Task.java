package com.zhouss.www.gitlabapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zs on 2017/6/12.
 */

public class Task {
    //"id": 3,
    @SerializedName("id")
    private int tId;

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
    private String status;

    //"currentTime": "2017-05-26 11:24:12"
    private String currentTime;

    public Task() {
    }

    public Task(int tId, String title, String description, String startAt, String endAt, List<Question> questions, int course, String status, String currentTime) {
        this.tId = tId;
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.questions = questions;
        this.course = course;
        this.status = status;
        this.currentTime = currentTime;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tId=" + tId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startAt='" + startAt + '\'' +
                ", endAt='" + endAt + '\'' +
                ", questions=" + questions.toString() +
                ", course=" + course +
                ", status='" + status + '\'' +
                ", currentTime='" + currentTime + '\'' +
                '}';
    }
}
