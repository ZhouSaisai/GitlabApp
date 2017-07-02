package com.zhouss.www.gitlabapp.model;

import java.io.Serializable;

/**
 * Created by zs on 2017/7/3.
 */

public class Score implements Serializable {
    //"studentId": 235,
    private int studentId;

    //"studentName": "学生279",
    private String studentName;

    //"studentNumber": "121250137",
    private String studentNumber;

    //"score": 0,
    private int score;

    //"scored": false
    private boolean scored;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
