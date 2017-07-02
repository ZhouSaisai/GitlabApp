package com.zhouss.www.gitlabapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zs on 2017/7/3.
 */

public class ScoreResult implements Serializable{

    @SerializedName("assignmentId")
    private int tId;

    private List<QuestionScore> questions;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public List<QuestionScore> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionScore> questions) {
        this.questions = questions;
    }
}
