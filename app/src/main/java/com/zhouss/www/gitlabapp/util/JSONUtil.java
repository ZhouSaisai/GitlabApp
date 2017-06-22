package com.zhouss.www.gitlabapp.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.zhouss.www.gitlabapp.model.Class;
import com.zhouss.www.gitlabapp.model.ClassStudent;
import com.zhouss.www.gitlabapp.model.Student;
import com.zhouss.www.gitlabapp.model.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2017/6/16.
 */

public class JSONUtil {

    /**
     *  解析服务器返回的班级数据
     */
    public static boolean handlClassResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allClasses = new JSONArray(response);
                for(int i=0;i<allClasses.length();i++){
                    JSONObject object = allClasses.getJSONObject(i);
                    Class cla = new Class();
                    cla.setName(object.getString("name"));
                    cla.setcId(object.getInt("id"));
                    cla.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     *  解析服务器返回的学生数据
     */
    public static boolean handlStudentResponse(String response) {
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray students = new JSONArray(response);
                for(int i=0;i<students.length();i++){
                    JSONObject object = students.getJSONObject(i);
                    ClassStudent student = new ClassStudent();
                    student.setName(object.getString("name"));
                    student.setCsId(object.getInt("id"));
                    student.setUsername(object.getString("username"));
                    student.setType(object.getString("type"));
                    student.setAvatar(object.getString("avatar"));
                    student.setGender(object.getString("gender"));
                    student.setEmail(object.getString("email"));
                    student.setSchoolId(object.getInt("schoolId"));
                    student.setGitId(object.getInt("gitId"));
                    student.setNumber(object.getString("number"));
                    student.setGroupId(object.getInt("groupId"));
                    student.setGitUsername(object.getString("gitUsername"));
                    student.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static List<Task> handlTaskResponse(String content) {
        List<Task> lists = new ArrayList<>();
        if(!TextUtils.isEmpty(content)){
            try {
                JSONArray arrays = new JSONArray(content);
                for(int i=0;i<arrays.length();i++){
                    String object = arrays.getJSONObject(i).toString();
                    Task task = new Gson().fromJson(object,Task.class);
                    lists.add(task);
                }
                return lists;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }
}
