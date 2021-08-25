package com.dhcc.nursepro.workarea.bloodtransfusionsystem.api;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * BloodTSApiService
 *
 * @author DevLix126
 * @date 2018/9/19
 */
public class BloodTSApiService extends NurseAPI {

    /**
     * 获取病人信息
     */
    public static void getPatWristInfo(String regNo, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("regNo", regNo);
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));

        WebServiceUtils.callWebService(getPatWristInfo, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 获取血液信息---扫描 血袋号、血制品号
     */
    public static void getBloodInfo(String bloodbagId, String bloodProductId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodbagId", bloodbagId);
        properties.put("bloodProductId", bloodProductId);

        WebServiceUtils.callWebService(getBloodInfo, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 血袋签收
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

        WebServiceUtils.callWebService(bloodRecive, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    /**
     * 获取已签收血液信息
     */
    public static void getInfusionBloodInfo(String episodeId, String bloodbagId, String bloodProductId, String type, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);
        properties.put("bloodbagId", bloodbagId);
        properties.put("bloodProductId", bloodProductId);
        properties.put("type", type);

        WebServiceUtils.callWebService(getInfusionBloodInfo, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    /**
     * 输血开始
     */
    public static void bloodTransStart(String bloodRowId, String userId1, String userId2, String type,String pass1,String pass2, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodRowId", bloodRowId);
        properties.put("userId1", userId1);
        properties.put("userId2", userId2);
        properties.put("pass1", pass1.replace(" ",""));
        properties.put("pass2",  pass2.replace(" ",""));
        properties.put("type", type);

        WebServiceUtils.callWebService(startTransfusion, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    /**
     * 输血巡视
     *
     * @param effect    是否存在不良反应
     * @param situation 反应状况
     */
    public static void bloodPatrol(String bloodRowId, String recdate, String rectime, String speed, String effect, String situation,String dataArr, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodRowId", bloodRowId);
        properties.put("recdate", recdate);
        properties.put("rectime", rectime);
        properties.put("speed", speed);
        properties.put("effect", effect);
        properties.put("situation", situation);
        SPUtils spUtils = SPUtils.getInstance();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("dataArr",dataArr);

        WebServiceUtils.callWebService(bloodPatrol, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 输血结束
     */
    public static void bloodTransEnd(String bloodRowId, String userId, String StopReasonDesc, String endType, String type,String pass, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodRowId", bloodRowId);
        properties.put("userId1", userId);
        properties.put("pass", pass.replace(" ",""));
        properties.put("StopReasonDesc", StopReasonDesc);
        properties.put("endType", endType);
        properties.put("type", type);

        WebServiceUtils.callWebService(endTransfusion, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 血袋回收
     */
    public static void recycleBloodbag(String finalWhere,String bloodRowId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("finalWhere", finalWhere);
        properties.put("bloodRowId", bloodRowId);
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));

        WebServiceUtils.callWebService(recycleBloodbag, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 输血查询
     */
    public static void getBloodList(String type, String date, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        properties.put("type", type);
        properties.put("date", date);

        WebServiceUtils.callWebService(getBloodList, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 输血详情
     */
    public static void getBloodPatrolRecord(String bloodRowId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bloodRowId", bloodRowId);

        WebServiceUtils.callWebService(getBloodPatrolRecord, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    /**
     * 巡视模板
     */
    public static void getBloodTemData(String episodeId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);

        WebServiceUtils.callWebService(getBloodTemData, properties, new WebServiceUtils.WebServiceCallBack() {
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
