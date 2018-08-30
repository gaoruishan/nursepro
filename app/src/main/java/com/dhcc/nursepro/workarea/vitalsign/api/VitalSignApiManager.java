package com.dhcc.nursepro.workarea.vitalsign.api;

import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class VitalSignApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }

    public interface GetVitalSignListCallback extends VitalSignApiManager.CommonCallBack {
        //void onSuccess(VitalSignBean vitalSignBean);
        void onSuccess(Map<String ,Object> map);
    }

    /**
     *获取生命体征病人列表及配置项
     * @param date 日期时间点
     * @param callback
     */
    public static void getVitalSignList(String date, final VitalSignApiManager.GetVitalSignListCallback callback){
        VitalSignApiService.getVitalSignList(date, new VitalSignApiService.ServiceCallBack(){

            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                Map<String, Object> map = new HashMap<String, Object>();
                map = (Map<String, Object>)gson.fromJson(jsonStr, map.getClass());

                if (map != null) {
                    String status = (String)map.get("status");
                    if (status.equals("0")){
                        callback.onSuccess(map);
                    }else{
                        callback.onFail((String)map.get("msgcode"),(String)map.get("msg"));
                    }
                } else {
                    callback.onFail("","");
                }
            }
        });
    }

    public interface GetVitalSignItemCallback extends VitalSignApiManager.CommonCallBack{
        void onSuccess(Map<String ,Object> map);
    }

    /**
     * 获取当前病人及时间点需要录入的生命体征项目
     * @param episodeId 就诊号
     * @param date 日期时间点
     * @param callback
     */
    public static void getVitalSignItems(String episodeId, String date, final VitalSignApiManager.GetVitalSignItemCallback callback){

        VitalSignApiService.getVitalSignItems(episodeId, date, new VitalSignApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                Map<String, Object> map = new HashMap<String, Object>();
                map = (Map<String, Object>)gson.fromJson(jsonStr, map.getClass());

                if (map != null) {
                    String status = (String)map.get("status");
                    if (status.equals("0")){
                        callback.onSuccess(map);
                    }else{
                        callback.onFail((String)map.get("msgcode"),(String)map.get("msg"));
                    }
                } else {
                    callback.onFail("","");
                }
            }
        });

    }

    public interface GetPatientTempImagesCallback extends VitalSignApiManager.CommonCallBack{
        void onSuccess(Map<String ,Object> map);
    }

    /**
     * 查看病人体温单
     * @param episodeId
     * @param callback
     */

    public static void gePatientTempImages(String episodeId, final  GetPatientTempImagesCallback callback){
        VitalSignApiService.getPatientTempImages(episodeId, new VitalSignApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                Map<String, Object> map = new HashMap<String, Object>();
                map = (Map<String, Object>)gson.fromJson(jsonStr, map.getClass());

                if (map != null) {
                    String status = (String)map.get("status");
                    if (status.equals("0")){
                        callback.onSuccess(map);
                    }else{
                        callback.onFail((String)map.get("msgcode"),(String)map.get("msg"));
                    }
                } else {
                    callback.onFail("","");
                }
            }
        });
    }


}
