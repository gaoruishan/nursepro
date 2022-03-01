package com.base.commlibs.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.BuildConfig;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.json.JSONObject;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    //日志
    private static final int APP_VERSION_CODE = AppUtils.getAppVersionCode();
    private static final String APP_VERSION_NAME = AppUtils.getAppVersionName();
    private static final String APP_PACKAGE_NAME = AppUtils.getAppPackageName();
    //当前时间
    public static final String CUR_DATE_TIME = "curDateTime";
    //提示
    public static final String TIP_FLAG = "tipFlag";
    private static final String TAG = "LocalTestManager";

    private static List<String> l = new ArrayList<>();
    private static Map<String, Integer> errNum = new WeakHashMap<>();
    private static String curMethodName;

    static {
        //对应的方法名
//        l.add("getInfusionMessage");//消息-输液
        l.add("getSkinTestMessage");//消息-皮试
//        l.add("GetDispensingOrdList");//配液
//        l.add("getSkinOrdList");
//        l.add("GetPunctureOrdList");//穿刺
//        l.add("punctureOrd");//穿刺
//        l.add("getTourOrdList");//巡视
//        l.add("tourOrd");//巡视
//        l.add("getChangeOrdList");//续液
//        l.add("getFinishOrdList");//拔针
//        l.add("extractOrd");//拔针

//        l.add("getInjectOrdList");//注射
//        l.add("exeInjectOrd");//注射

//        l.add("GetMainConfig");//主页配置
//        l.add("getOrderTasks");//主页配置
        //        l.add("getTransBloodList");
//        l.add("getEducationList");
//        l.add("getEduContents");
//        l.add("getInterventionList");
//        l.add("getNeedEmr");
//        l.add("getNotifyList");
//        l.add("getQuestionRecord");
//        l.add("getQuestionList");
//        l.add("getInterventionFreq");
//        l.add("getGoalByQestId");
//        l.add("getInterventionByQestId");
//
//        l.add("getIFOrdListByBarCode");
//        l.add("GetBarcodeFlag");
//        l.add("GetXmlValues");
//        l.add("getInfusionOrdList");
        l.add("getLabOutList");
        l.add("GetNCPAccompanyList");
        l.add("GetNCPAccompanySub");
        l.add("GetRfidPatList");
//        l.add("GetLoginQR");
//        l.add("GetLoginQRResult");
//        l.add("GetUIConfig");
        l.add("GetListData");
        l.add("GetNormalOrdTask");
        l.add("getOrders");
        l.add("getScanInfo");
        l.add("getIFOrdListByBarCode");

    }

    /**
     * 添加方法名的集合
     * @param list
     */
    public static void setMethodNameList(List<String> list) {
        l.addAll(list);
    }

    /**
     * 加载本地json
     * @param methodName
     * @param webServiceCallBack
     */
    public static void callLocalJson(String methodName, BaseWebServiceUtils.WebServiceCallBack webServiceCallBack) {
        boolean exitMethod = isExitMethod(methodName);
        if (!exitMethod) {
            return;
        }
        CommRes.readJson(curMethodName + ".json", new CommRes.CallRes<String>() {
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
        DataCache.putLog(methodName);
        if (TEST) {
            //方法首字母大写
            return isExitMethod(methodName);
        }
        return false;
    }

    private static boolean isExitMethod(String methodName) {
        String methodNameU = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        String methodNameL = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
        if (l.contains(methodNameU)){
            curMethodName = methodNameU;
        }
        if (l.contains(methodNameL)){
            curMethodName = methodNameL;
        }
        return l.contains(methodNameU) || l.contains(methodNameL);
    }

    public static boolean isTest() {
//        if (TEST) {
//            return UserUtil.isExistUserId();
//        }
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
    public static boolean isRequest(String methodName, HashMap<String, String> properties, Object obj, String url) {

        //有数据直接返回
        if (!ObjectUtils.isEmpty(obj)) {
            //处理公共数据
            saveCommResult((String) obj);
            //保存数据不为null
            saveLog(methodName + "_data", url + "\n\n" + properties + "\n\n" + obj);
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
        saveLog(methodName + "_null", url + "\n\n" + properties + "\n\n" + obj);
        return true;
    }

    /**
     * 处理公共数据
     * @param res
     */
    protected static void saveCommResult(String res) {
        if (res.contains(CUR_DATE_TIME)) {
            try { //当前时间
                JSONObject jsonObject = new JSONObject(res);
                String curTime = jsonObject.getString(CUR_DATE_TIME);
                if (!TextUtils.isEmpty(curTime)) {
                    SPStaticUtils.put(SharedPreference.CURDATETIME, curTime);
                }
            } catch (Exception e) {
                Log.e(TAG, "(LocalTestManager.java:151) " + e.toString());
            }
        }
        if (res.contains(TIP_FLAG)) {
            try { //toast 提示
                JSONObject jsonObject = new JSONObject(res);
                String tipFlag = jsonObject.getString(TIP_FLAG);
                if (!TextUtils.isEmpty(tipFlag)) {
                    ToastUtils.showShort(tipFlag + "");
                }
            } catch (Exception e) {
                Log.e(TAG, "(LocalTestManager.java:205) " + e.toString());
            }
        }
    }

    /**
     * 保存json-日志
     * @param methodName
     * @param obj
     */
    public static void saveLog(String methodName, String obj) {
        if (isLogFlag()) {
            saveLogTest(methodName, obj);
        }
    }

    public static void saveLogTest(String methodName, String obj) {
        String packName = getSimplePackageName();
        String date = FORMAT.format(new Date(System.currentTimeMillis()));
        String dir = packName + "/" + date + "/" + SPStaticUtils.getString(SharedPreference.USERCODE) + "/v" + APP_VERSION_CODE + "_" + methodName + "/";
        String dhc = dir + methodName + "_" + System.currentTimeMillis() + ".log";
        CommFile.write(dhc, getCommLog() + obj);
    }


    protected static String getSimplePackageName() {
        String[] split = APP_PACKAGE_NAME.split("\\.");
        return split[split.length - 1];
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
                        "\nApp VersionName    : " + APP_VERSION_NAME +
                        "\nApp VersionCode    : " + APP_VERSION_CODE +
                        "\nApp isConnected    : " + NetworkUtils.isConnected() +
                        "\nApp isMobileData    : " + NetworkUtils.isMobileData() +
                        "\nApp isWifiConnected    : " + NetworkUtils.isWifiConnected() +
                        "\nApp getNetworkType    : " + NetworkUtils.getNetworkType() +
                        "\nUser USER_CODE    : " + SPUtils.getInstance().getString(SharedPreference.USERCODE) +
                        "\nUser USER_NAME    : " + SPUtils.getInstance().getString(SharedPreference.USERNAME) +
                        "\nUser USER_USERID    : " + SPUtils.getInstance().getString(SharedPreference.USERID) +
                        "\nUser USER_LOCID    : " + SPUtils.getInstance().getString(SharedPreference.LOCID) +
                        "\nUser USER_LOCDESC    : " + SPUtils.getInstance().getString(SharedPreference.LOCDESC) +
                        "\nUser USER_WARDID    : " + SPUtils.getInstance().getString(SharedPreference.WARDID) +
                        "\n************* Log Head ****************\n\n";
        sb.append(head);
        return sb.toString();
    }

    /**
     * 保存spUtils日志
     */
    public static void saveSpUtils() {
        if (isLogFlag()) {
            CommFile.write("spUtils_" + getSimplePackageName(), SPUtils.getInstance().getAll().toString());
        }
    }

    /**
     * 获取指定路线下文件列表
     * @param callBack
     */
    public static void getLogFilesInDir(String dir, SimpleCallBack<List<File>> callBack) {
        Log.e(TAG, "(LocalTestManager.java:272) " + dir);
        CommFile.readFilesInDir(dir, callBack);
    }

    public static String getLogPath() {
        String packName = getSimplePackageName();
        String date = FORMAT.format(new Date(System.currentTimeMillis()));
        return packName + "/" + date + "/" + SPStaticUtils.getString(SharedPreference.USERCODE);
    }
}
