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


}
