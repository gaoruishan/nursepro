package com.dhcc.nursepro.workarea.plyout.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class PlyOutApiService {
    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
    public static void getLabOutMsg(HashMap<String,String> map, String MethodName, final ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
