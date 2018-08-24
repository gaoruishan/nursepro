package com.dhcc.nursepro.workarea.checkresult.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class LabApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getCheckLabMsg(HashMap<String,String> map,String MethodName, final LabApiService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
