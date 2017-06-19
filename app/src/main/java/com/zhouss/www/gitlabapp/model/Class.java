package com.zhouss.www.gitlabapp.model;

import org.litepal.crud.DataSupport;

/**
 * Created by zs on 2017/6/12.
 */

public class Class extends DataSupport{
    //班级id,如1
    private int cId;

    //班级名称：2013级1班"
    private String name;

    public Class() {
    }

    public Class(int cId, String name) {
        this.cId = cId;
        this.name = name;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
