package com.base.commlibs.voiceUtils.api;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;

/**
 * com.base.commlibs.http
 * <p>
 * author Q
 * Date: 2021/5/8
 * Time:14:32
 */
public class BaseWebApiService extends NurseAPI {
    public static void getPatListToVoice(final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));

        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        properties.put("locId", SPUtils.getInstance().getString(SharedPreference.LOCID));
        //GetPatListToVoice: 获取患者列表 语音用
        VoiceWebServiceUtils.callWebService(getWardPatList, properties, new VoiceWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    /**
     *获取生命体征病人列表及配置项
     * @param date 日期时间点
     * @param callback
     */
    public static void getVitalSignList(String date,String time,final BaseWebApiService.ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        //病区id
        properties.put("wardId", SPUtils.getInstance().getString("WARDID"));
        //用户id
        properties.put("userId", SPUtils.getInstance().getString("USERID"));
        //科室id
        properties.put("locId", SPUtils.getInstance().getString("LOCID"));
        //日期
        if ("".equals(date)){
            date = null;
        }
        if ("".equals(time)){
            time = null;
        }
        properties.put("date", date);
        properties.put("time", time);

        VoiceWebServiceUtils.callWebService("getTempPatList", properties, new VoiceWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
    public static void GetSoundScore(final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<String, String>();
        //GetPatListToVoice: 获取患者列表 语音用
        VoiceWebServiceUtils.callWebService("GetSoundScore", properties, new VoiceWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
    public static void getNursesByLoc(final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("locId", SPUtils.getInstance().getString(SharedPreference.LOCID));
        //GetPatListToVoice: 获取患者列表 语音用
        VoiceWebServiceUtils.callWebService("GetNursesByLoc", properties, new VoiceWebServiceUtils.WebServiceCallBack() {
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

