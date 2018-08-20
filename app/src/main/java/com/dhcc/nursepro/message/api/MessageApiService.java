package com.dhcc.nursepro.message.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class MessageApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getMessage(String wardId, String userId,final ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("wardId", wardId);
        properties.put("userId", userId);
        WebServiceUtils.callWebService("getNotifyList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
