package com.dhcc.nursepro.message.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class MessageApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getMessage(final ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("wardId", "5");
        properties.put("userId", "2");
        WebServiceUtils.callWebService("getNotifyList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
