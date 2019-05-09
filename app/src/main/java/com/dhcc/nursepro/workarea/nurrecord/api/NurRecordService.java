package com.dhcc.nursepro.workarea.nurrecord.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class NurRecordService {
    public static void getModelDetailMsg(HashMap<String, String> map, String MethodName, final ServiceCallBack callback) {

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
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
