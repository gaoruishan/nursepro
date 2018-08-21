package com.dhcc.nursepro.workarea.patevents.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class PatEventsApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getEventMsg(HashMap<String,String> map,String MethodName, final PatEventsApiService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
