package com.zhouss.www.gitlabapp.enums;

/**
 * Created by zs on 2017/6/12.
 */

public enum Status {
    NEWLY, //新建态
    INITING, //正在初始化
    INITFAIL, //初始化失败
    INITSUCCESS, //初始化成功
    ONGOING, //考试正在进行
    TIMEUP, //考试时间到
    ANALYZING, //正在分析结果
    ANALYZINGFINISH //结果分析完毕
}
