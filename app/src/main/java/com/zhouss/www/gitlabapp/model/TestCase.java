package com.zhouss.www.gitlabapp.model;

/**
 * Created by zs on 2017/7/6.
 */

public class TestCase {
    private String name;
    private boolean passed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
