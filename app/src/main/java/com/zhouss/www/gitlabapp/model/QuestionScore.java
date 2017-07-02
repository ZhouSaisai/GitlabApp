package com.zhouss.www.gitlabapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zs on 2017/7/3.
 */

public class QuestionScore implements Serializable{
    private QuestionInfo questionInfo;

    private List<Score> students;

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }

    public List<Score> getStudents() {
        return students;
    }

    public void setStudents(List<Score> students) {
        this.students = students;
    }
}
