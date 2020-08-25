package com.dhcc.module.nurse;

import android.os.Bundle;

import com.base.commlibs.utils.ReflectUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-20/10:02
 * @email:grs0515@163.com
 */
public class BaseBundleData {

    public Bundle bundle;

    public BaseBundleData() {
    }

    public BaseBundleData(Bundle bundle) {
        this.bundle = bundle;
    }

    protected String getString(String key) {
        if (bundle != null) {
            return bundle.getString(key);
        }
        return "";
    }

    /**
     * 注意返回是List包含的map,需要调用getJsonMapToBean
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    private final <T> List<T> getTypeTokenList(String key , Class<T>  t) {
        String list = bundle.getString(key);
        Type type = new TypeToken<List<T>>() {
        }.getType();
        return new Gson().fromJson(list, type);
    }

    /**
     * 获取List包含Bean数据
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public final <T> List<T> getTypeTokenListBean(String key , Class<T>  t) {
        List<T> mList = new ArrayList<>();
        List list = getTypeTokenList(key, t);
        for (int i = 0; i < list.size(); i++) {
            T bean = getJsonMapToBean(list.get(i), t);
            mList.add(bean);
        }
        return mList;
    }

    /**
     * 将Map转Bean
     * @param obj
     * @param tClass
     * @param <T>
     * @return
     */
    public  <T> T getJsonMapToBean(Object obj, Class<T> tClass) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String jsonString = gson.toJson(obj);
        return gson.fromJson(jsonString,tClass);
    }

    public Bundle build() {
        return ReflectUtil.getPublicFieldsToBundle(this);
    }
}
