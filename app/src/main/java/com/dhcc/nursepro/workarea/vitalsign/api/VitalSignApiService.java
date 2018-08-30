package com.dhcc.nursepro.workarea.vitalsign.api;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class VitalSignApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    /**
     *获取生命体征病人列表及配置项
     * @param date 日期时间点
     * @param callback
     */
    public static void getVitalSignList(String date, final VitalSignApiService.ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        //病区id
        properties.put("wardId", SPUtils.getInstance().getString("WARDID"));
        //用户id
        properties.put("userId", SPUtils.getInstance().getString("USERID"));
        //科室id
        properties.put("locId", SPUtils.getInstance().getString("LOCID"));
        //日期
        properties.put("date", date);
        WebServiceUtils.callWebService("getTempPatList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    /**
     * 获取当前病人及时间点需要录入的生命体征项目
     * @param episodeId 就诊号
     * @param date 日期时间点
     * @param callback
     */
    public static void getVitalSignItems(String episodeId, String date, final VitalSignApiService.ServiceCallBack callback){
        HashMap<String, String> properties = new HashMap<String, String>();
        //就诊号
        properties.put("episodeId", episodeId);
        //日期时间点
        properties.put("dateTimePoint", date);

        WebServiceUtils.callWebService("getTempValue", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getPatientTempImages(String episodeId, final  ServiceCallBack callback){
        HashMap<String, String> properties = new HashMap<String, String>();
        //就诊号
        properties.put("episodeId", episodeId);

        WebServiceUtils.callWebService("getPatTempImage", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

}
