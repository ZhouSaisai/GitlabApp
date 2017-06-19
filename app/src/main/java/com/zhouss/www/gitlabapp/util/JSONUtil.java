package com.zhouss.www.gitlabapp.util;

import android.text.TextUtils;
import android.util.Log;

import com.zhouss.www.gitlabapp.model.Class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
}
