package com.base.commlibs.utils;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 反射工具类
 * @author:gaoruishan
 * @date:202020-03-09/15:47
 * @email:grs0515@163.com
 */
public class ReflectUtil {

    private static final String TAG = ReflectUtil.class.getSimpleName();

    /**
     * 将类中public 变量中有值得 转为Map
     * @param obj
     * @return
     */
    public static HashMap<String, String> getPublicFieldsToMap(Object obj) {
        HashMap<String, String> map = new HashMap<>();
        //获取类中public类型的属性
        Field[] declaredFields = obj.getClass().getFields();
        for (Field field : declaredFields) {
            try {
                Object value = field.get(obj);
                if (value instanceof String) {
                    if (!TextUtils.isEmpty((String) value)) {
                        map.put(field.getName(), (String) value);
                    }
                }
            } catch (IllegalAccessException e) {
                Log.e(TAG, "(ReflectUtil.java:24) " + e.toString());
            }
        }
        return map;
    }

    public static void clearPublicFields(Object obj) {
        //获取类中public类型的属性
        Field[] declaredFields = obj.getClass().getFields();
        for (Field field : declaredFields) {
            try {
                field.setAccessible(true);
                field.set(obj, "");
            } catch (IllegalAccessException e) {
                Log.e(TAG, "(ReflectUtil.java:24) " + e.toString());
            }
        }
    }
}
