package com.zhouss.www.gitlabapp.model;

/**
 * Created by zs on 2017/7/6.
 */

public class TestScore {
    //"questionId": 12,
    private int questionId;
    //"questionTitle": "Examination-01.git",
    private String questionTitle;

    private MetricData metricData;

    private TestResult testResult;

    private TestScoreResult scoreResult;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public MetricData getMetricData() {
        return metricData;
    }

    public void setMetricData(MetricData metricData) {
        this.metricData = metricData;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public TestScoreResult getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(TestScoreResult scoreResult) {
        this.scoreResult = scoreResult;
    }
}
