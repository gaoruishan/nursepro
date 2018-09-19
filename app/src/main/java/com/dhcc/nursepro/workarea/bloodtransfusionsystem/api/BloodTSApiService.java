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

    public static void bloodReceive(String bloodbagId, String bloodProductId,String bloodGroup, String patBldGroup,String episodeId, String productDesc,String rowId, final ServiceCallBack callback) {
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

    //bloodRowId	String	血袋接收表Id
    //userId	String	巡视人Id
    //recdate	String	巡视日期
    //rectime	String	巡视时间
    //speed	String	滴速
    //effect	String	有无不良反应
    //situation String	不良反应描述
    public static void bloodPatrol(String bloodRowId, String recdate,String rectime, String speed,String effect, String situation, final ServiceCallBack callback) {
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

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
