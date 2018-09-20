package com.dhcc.nursepro.workarea.bloodtransfusionsystem.api;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * BloodTSApiService
 *
 * @author DevLix126
 * @date 2018/9/19
 */
public class BloodTSApiService {

    /**
     * 获取病人信息
     *
     * @param regNo
     * @param callback
     */
    public static void getPatWristInfo(String regNo, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("regNo", regNo);
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));

        WebServiceUtils.callWebService("getPatWristInfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 获取血液信息---扫描 血袋号、血制品号
     *
     * @param bloodbagId
     * @param bloodProductId
     * @param callback
     */
    public static void getBloodInfo(String bloodbagId, String bloodProductId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodbagId", bloodbagId);
        properties.put("bloodProductId", bloodProductId);

        WebServiceUtils.callWebService("getBloodInfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 血袋签收
     *
     * @param bloodbagId
     * @param bloodProductId
     * @param bloodGroup
     * @param patBldGroup
     * @param episodeId
     * @param productDesc
     * @param rowId
     * @param callback
     */
    public static void bloodReceive(String bloodbagId, String bloodProductId, String bloodGroup, String patBldGroup, String episodeId, String productDesc, String rowId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodbagId", bloodbagId);
        properties.put("bloodProductId", bloodProductId);
        properties.put("bloodGroup", bloodGroup);
        properties.put("patBldGroup", patBldGroup);
        properties.put("episodeId", episodeId);
        properties.put("productDesc", productDesc);
        properties.put("rowId", rowId);
        SPUtils spUtils = SPUtils.getInstance();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));

        WebServiceUtils.callWebService("bloodRecive", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    /**
     * 获取已签收血液信息
     *
     * @param episodeId
     * @param bloodbagId
     * @param bloodProductId
     * @param type
     * @param callback
     */
    public static void getInfusionBloodInfo(String episodeId, String bloodbagId, String bloodProductId, String type, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);
        properties.put("bloodbagId", bloodbagId);
        properties.put("bloodProductId", bloodProductId);
        properties.put("type", type);

        WebServiceUtils.callWebService("getInfusionBloodInfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    /**
     * 输血开始
     *
     * @param bloodRowId
     * @param userId1
     * @param userId2
     * @param type
     * @param callback
     */
    public static void bloodTransStart(String bloodRowId, String userId1, String userId2, String type, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodRowId", bloodRowId);
        properties.put("userId1", userId1);
        properties.put("userId2", userId2);
        properties.put("type", type);

        WebServiceUtils.callWebService("startTransfusion", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    /**
     * 输血巡视
     *
     * @param bloodRowId
     * @param recdate
     * @param rectime
     * @param speed
     * @param effect     是否存在不良反应
     * @param situation  反应状况
     * @param callback
     */
    public static void bloodPatrol(String bloodRowId, String recdate, String rectime, String speed, String effect, String situation, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodRowId", bloodRowId);
        properties.put("recdate", recdate);
        properties.put("rectime", rectime);
        properties.put("speed", speed);
        properties.put("effect", effect);
        properties.put("situation", situation);
        SPUtils spUtils = SPUtils.getInstance();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));

        WebServiceUtils.callWebService("bloodPatrol", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 输血结束
     *
     * @param bloodRowId
     * @param userId
     * @param StopReasonDesc
     * @param endType
     * @param type
     * @param callback
     */
    public static void bloodTransEnd(String bloodRowId, String userId, String StopReasonDesc, String endType, String type, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodRowId", bloodRowId);
        properties.put("userId", userId);
        properties.put("StopReasonDesc", StopReasonDesc);
        properties.put("endType", endType);
        properties.put("type", type);

        WebServiceUtils.callWebService("endTransfusion", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 血袋回收
     *
     * @param bloodRowId
     * @param callback
     */
    public static void recycleBloodbag(String bloodRowId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodRowId", bloodRowId);
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));

        WebServiceUtils.callWebService("recycleBloodbag", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
