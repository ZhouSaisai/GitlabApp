package com.zhouss.www.gitlabapp.util;

/**
 * Created by zs on 2017/7/2.
 */

public class TaskStatusUtil {
    public static String getTypeString(String status){
        String result = "";
        switch (status){
            case "newly":
                result = "新建态";
                break;
            case "initing":
                result = "初始化";
                break;
            case "initFail":
                result = "初始化失败";
                break;
            case "initSuccess":
                result = "初始化成功";
                break;
            case "ongoing":
                result = "考试进行中";
                break;
            case "timeup":
                result = "考试时间到";
                break;
            case "analyzing":
                result = "分析结果中";
                break;
            case "analyzingFinish":
                result = "分析完成";
                break;
        }
        return result;
    }
}
