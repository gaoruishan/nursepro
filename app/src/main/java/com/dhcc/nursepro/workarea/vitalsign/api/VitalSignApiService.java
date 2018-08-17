package com.dhcc.nursepro.workarea.vitalsign.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class VitalSignApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getVitalSignList(String date, final VitalSignApiService.ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        //病区id
        properties.put("wardId", "5");
        //用户id
        properties.put("userId", "3");
        //科室id
        properties.put("locId", "192");
        //日期
        properties.put("date", date);
        WebServiceUtils.callWebService("getTempPatList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
