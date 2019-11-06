package com.base.commlibs.utils;

import android.text.TextUtils;

import com.base.commlibs.BuildConfig;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载本地测试json
 * @author:gaoruishan
 * @date:202019-10-11/16:04
 * @email:grs0515@163.com
 */
public class LocalTestManager {
    // 是否开启测试
    private final static boolean TEST = BuildConfig.DEBUG;
    private static List<String> l = new ArrayList<>();

    static {
        //对应的方法名
//        l.add("getInfusionMessage");
//        l.add("getSkinTestMessage");
//        l.add("getOrdList");
    }

    /**
     * 添加方法名的集合
     * @param l
     */
    public static void setMethodNameList(List<String> l) {
        l.clear();
        l.addAll(l);
    }

    /**
     * 加载本地json
     * @param methodName
     * @param webServiceCallBack
     */
    public static void callLocalJson(String methodName, BaseWebServiceUtils.WebServiceCallBack webServiceCallBack) {
        CommRes.readJson(methodName + ".json", new CommRes.CallRes<String>() {
            @Override
            public void call(String s, String json) {
                webServiceCallBack.callBack(json);
            }
        });

    }

    /**
     * 判断是否是测试的methodName
     * @param methodName
     * @return
     */
    public static boolean isTest(String methodName) {
        if (TEST) {
            return l.contains(methodName);
        }
        return false;
    }

    /**
     * 是否保存json
     * @param methodName
     * @param obj
     */
    public static void isSave(String methodName, String obj) {
        String logFlag = SPUtils.getInstance().getString(SharedPreference.LOG_FLAG);
        if(!TextUtils.isEmpty(logFlag)){
        	CommFile.write(methodName,obj);
        }
    }
}
