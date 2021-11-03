package com.base.commlibs.http;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共的方法
 * @author:gaoruishan
 * @date:202019-04-29/13:57
 * @email:grs0515@163.com
 */
public class CommWebService {

    private static final String TAG = CommWebService.class.getSimpleName();
    //包名
    public static final String COM_DHCC_INFUSION = "com.dhcc.infusion";
    public static final String COM_DHCC_NURSEPRO = "com.dhcc.nursepro";
    public static final String COM_DHCC_HEALTH = "com.dhcc.health";
    public static final float OPPDA_VERSION = 3.1f;

    /**
     * 添加用户id和科室id
     * @param methodName
     * @param properties
     * @param callBack
     */
    public static void callUserIdLocId(String methodName, HashMap<String, String> properties, ServiceCallBack callBack) {
        SPUtils spUtils = SPUtils.getInstance();
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        call(methodName, properties, callBack);
    }

    /**
     * 统一(兼容)
     * @param methodName
     * @param properties
     */
    public static void call(String methodName, HashMap<String, String> properties, final ServiceCallBack callBack) {
        String appPackageName = AppUtils.getAppPackageName();
        Log.e(TAG, "(CommWebService.java:44) " + appPackageName);
        // 检查properties
        checkProperties(properties);

        if (COM_DHCC_INFUSION.equals(appPackageName)) {
            callInfusion(methodName, properties, callBack);
        }
        if (COM_DHCC_NURSEPRO.equals(appPackageName)) {
            callNurse(methodName, properties, callBack);
        }
        if (COM_DHCC_HEALTH.equals(appPackageName)) {
            callHealth(methodName, properties, callBack);
        }
    }

    public static void checkProperties(HashMap<String, String> properties) {
        if (properties != null) {
            HashMap<String, String> propertiesNew = new HashMap<>();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                //去除 空Value
                if (entry != null && !TextUtils.isEmpty(entry.getValue())) {
                    propertiesNew.put(entry.getKey(), entry.getValue());
                }
            }
            properties.clear();
            properties.putAll(propertiesNew);
        } else {
            properties = new HashMap<>();
        }

    }

    /**
     * 统一(门诊输液)
     * @param methodName
     * @param properties
     */
    public static void callInfusion(String methodName, HashMap<String, String> properties, ServiceCallBack callBack) {
        BaseWebServiceUtils.callWebOPPDAService(methodName, properties, new BaseWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    private static boolean getAppVersion(float v) {
        String versionName = AppUtils.getAppVersionName();
        if (!TextUtils.isEmpty(versionName)) {
            try {
                return Float.valueOf(versionName) > v;
            } catch (Exception e) {
                return false;
            }

        }
        return false;
    }

    /**
     * 统一(护士站)
     * @param methodName
     * @param properties
     * @param callBack
     */
    public static void callNurse(String methodName, HashMap<String, String> properties, final ServiceCallBack callBack) {
        BaseWebServiceUtils.callWebPDAService(methodName, properties, new BaseWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    /**
     * 统一(CA)
     * @param methodName
     * @param properties
     * @param callBack
     */
    public static void callCa(String methodName, HashMap<String, String> properties, final ServiceCallBack callBack) {
        BaseWebServiceUtils.callWebCAService(methodName, properties, new BaseWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    /**
     * 统一(康复)
     * @param methodName
     * @param properties
     * @param callBack
     */
    public static void callHealth(String methodName, HashMap<String, String> properties, final ServiceCallBack callBack) {
        BaseWebServiceUtils.callWebPDAService(methodName, properties, new BaseWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    /**
     * 解析CommResult方法
     * @param jsonStr
     * @param callback
     */
    public static void parserCommResult(String jsonStr, CommonCallBack<CommResult> callback) {
        ParserUtil<CommResult> parserUtil = new ParserUtil<>();
        CommResult bean = parserUtil.parserResult(jsonStr, callback, CommResult.class);
        if (bean == null) {
            return;
        }
        parserUtil.parserStatus(bean, callback);
    }

    /**
     * 添加科室ID
     * @param properties
     * @return
     */
    public static HashMap<String, String> addLocId(HashMap<String, String> properties) {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put("locId", SPUtils.getInstance().getString(SharedPreference.LOCID));
        return properties;
    }

    /**
     * 添加用户ID
     * @param properties
     * @return
     */
    public static HashMap<String, String> addUserId(HashMap<String, String> properties) {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        return properties;
    }

    /**
     * 添加病房ID
     * @param properties
     * @return
     */
    public static HashMap<String, String> addWardId(HashMap<String, String> properties) {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        return properties;
    }

    /**
     * 添加医院ID
     * @param properties
     * @return
     */
    public static HashMap<String, String> addHospitalId(HashMap<String, String> properties) {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put("hospitalId", SPUtils.getInstance().getString(SharedPreference.HOSPITALROWID));
        return properties;
    }

    /**
     * 添加组ID
     * @param properties
     * @return
     */
    public static HashMap<String, String> addGroupId(HashMap<String, String> properties) {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put("groupId", SPUtils.getInstance().getString(SharedPreference.GROUPID));
        return properties;
    }

    /**
     * 添加当前Fragment
     * @param properties
     * @return
     */
    public static HashMap<String, String> curFragment(HashMap<String, String> properties) {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put("fragment", AppUtil.getCurActivityFragmentSimpleName());
        return properties;
    }

    /**
     * 添加当前设备ID
     * @param properties
     * @return
     */
    public static HashMap<String, String> curDevice(HashMap<String, String> properties) {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put("deviceId", DeviceUtils.getAndroidID()+"^"+ Build.MODEL);
        return properties;
    }


}
