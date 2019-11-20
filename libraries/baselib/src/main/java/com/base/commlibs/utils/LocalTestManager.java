package com.base.commlibs.utils;

import android.os.Build;
import android.text.TextUtils;

import com.base.commlibs.BuildConfig;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 加载本地测试json
 * @author:gaoruishan
 * @date:202019-10-11/16:04
 * @email:grs0515@163.com
 */
public class LocalTestManager {
    // 是否开启测试
    private final static boolean TEST = BuildConfig.DEBUG;

    //请求次数
    private static final int REQ_NUM = 2;
    private static final Format FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Format FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    private static final int APP_VERSION_CODE = AppUtils.getAppVersionCode();
    private static List<String> l = new ArrayList<>();
    private static Map<String, Integer> errNum = new WeakHashMap<>();

    static {
        //对应的方法名
//        l.add("getInfusionMessage");
//        l.add("getSkinTestMessage");
//        l.add("getOrdList");
//        l.add("getSkinOrdList");
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

    public static void clear() {
        errNum.clear();//清空
    }

    /**
     * 判断是否再次请求
     * @param methodName
     * @param obj
     * @return
     */
    public static boolean isRequest(String methodName, Object obj) {
        //有数据直接返回
        if (!ObjectUtils.isEmpty(obj)) {
            return false;
        }
        Integer integer = errNum.get(methodName);
        if (integer == null) {
            integer = 1;
        }
        //请求次数==2 返回; 并置1
        if (integer >= REQ_NUM) {
            errNum.put(methodName, 1);
            return false;
        }
        integer += 1;
        errNum.put(methodName, integer);
        //保存
        saveLog(methodName, (String) obj);
        return true;
    }

    /**
     * 是否保存json-日志
     * @param methodName
     * @param obj
     */
    public static void saveLog(String methodName, String obj) {
        if (isLogFlag()) {
            String date = FORMAT.format(new Date(System.currentTimeMillis()));
            String dhc = date + "/v" + APP_VERSION_CODE + "_" + methodName+"_"+System.currentTimeMillis();
            CommFile.write(dhc, getCommLog() + obj);
        }
    }

    /**
     * 是否开启log
     * @return
     */
    public static boolean isLogFlag() {
        return !TextUtils.isEmpty(SPUtils.getInstance().getString(SharedPreference.LOG_FLAG));
    }

    /**
     * log的公共部分
     * @return
     */
    private static String getCommLog() {
        final String time = FORMAT_1.format(new Date(System.currentTimeMillis()));
        final StringBuilder sb = new StringBuilder();
        final String head =
                "\n************* Log Head ****************" +
                        "\nTime       : " + time +
                        "\nDevice Manufacturer: " + Build.MANUFACTURER +
                        "\nDevice Model       : " + Build.MODEL +
                        "\nAndroid Version    : " + Build.VERSION.RELEASE +
                        "\nAndroid SDK        : " + Build.VERSION.SDK_INT +
                        "\nApp VersionName    : " + AppUtils.getAppVersionName() +
                        "\nApp VersionCode    : " + AppUtils.getAppVersionCode() +
                        "\nApp isConnected    : " + NetworkUtils.isConnected() +
                        "\nApp isMobileData    : " + NetworkUtils.isMobileData() +
                        "\nApp isWifiConnected    : " + NetworkUtils.isWifiConnected() +
                        "\nApp getNetworkType    : " + NetworkUtils.getNetworkType() +
                        "\n************* Log Head ****************\n\n";
        sb.append(head);
        return sb.toString();
    }

    /**
     * 保存spUtils日志
     */
    public static void saveSpUtils() {
        if (isLogFlag()) {
            CommFile.write("spUtils", SPUtils.getInstance().getAll().toString());
        }
    }
}
