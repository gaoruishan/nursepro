package com.base.commlibs.http;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;

/**
 * 公共的方法
 * @author:gaoruishan
 * @date:202019-04-29/13:57
 * @email:grs0515@163.com
 */
public class CommWebService {

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
     * 统一(门诊输液)
     * @param methodName
     * @param properties
     */
    public static void call(String methodName, HashMap<String, String> properties, final ServiceCallBack callBack) {
        BaseWebServiceUtils.callWebOPPDAService(methodName, properties, new BaseWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
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
     * 解析CommResult方法
     * @param jsonStr
     * @param callback
     */
    public static void parserCommResult(String jsonStr, CommonCallBack callback) {
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


}