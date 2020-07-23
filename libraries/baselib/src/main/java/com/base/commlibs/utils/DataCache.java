package com.base.commlibs.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;

/**
 * 保存json数据
 * @author:gaoruishan
 * @date:202019-08-19/11:12
 * @email:grs0515@163.com
 */
public class DataCache {

    private static SPUtils SP(String spName) {
        return SPUtils.getInstance(spName);
    }

    /**
     * 保存json对象
     * @param t ObjectBean对象
     * @param key SharedPreference中的key
     * @param <T>
     */
    public static <T> void saveJson(T t,String key) {
        Gson gson = new Gson();
        String json = gson.toJson(t);
        SP(key).put(key, json);
    }

    /**
     * 取出json对象
     * @param t ObjectBean对象
     * @param key SharedPreference中的key
     * @param <T>
     * @return
     */
    public static <T> T getJson(Class<T> t,String key) {
        String json =  SP(key).getString(key);
        if(!TextUtils.isEmpty(json)){
            Gson gson = new Gson();
            return gson.fromJson(json,t);
        }
        return null;
    }
}
