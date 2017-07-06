package com.zhouss.www.gitlabapp.model;

import java.io.Serializable;

/**
 * Created by zs on 2017/7/6.
 */

public class AnalyseData implements Serializable{
    private int max_score;
    private int min_score;
    private double ave_score;
    private int s_count;

    private int s90;
    private int s80;
    private int s60;
    private int s0;
    private int sno;

    public int getMax_score() {
        return max_score;
    }

    public void setMax_score(int max_score) {
        this.max_score = max_score;
    }

    public int getMin_score() {
        return min_score;
    }

    public void setMin_score(int min_score) {
        this.min_score = min_score;
    }

    public double getAve_score() {
        return ave_score;
    }

    public void setAve_score(double ave_score) {
        this.ave_score = ave_score;
    }

    public int getS_count() {
        return s_count;
    }

    public void setS_count(int s_count) {
        this.s_count = s_count;
    }

    public int getS90() {
        return s90;
    }

    public void setS90(int s90) {
        this.s90 = s90;
    }

    public int getS80() {
        return s80;
    }

    public void setS80(int s80) {
        this.s80 = s80;
    }

    public int getS60() {
        return s60;
    }

    public void setS60(int s60) {
        this.s60 = s60;
    }

    public int getS0() {
        return s0;
    }

    public void setS0(int s0) {
        this.s0 = s0;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
}
