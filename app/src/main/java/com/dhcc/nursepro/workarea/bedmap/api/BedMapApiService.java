package com.dhcc.nursepro.workarea.bedmap.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class BedMapApiService {

    public static void getBedMap(String wardId, String userId, final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("wardId", wardId);
        properties.put("userId", userId);
        WebServiceUtils.callWebService("getWardPatList", properties, new WebServiceUtils.WebServiceCallBack() {
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
