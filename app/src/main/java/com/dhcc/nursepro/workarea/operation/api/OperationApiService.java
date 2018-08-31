package com.dhcc.nursepro.workarea.operation.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class OperationApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getOperationList(HashMap<String,String> map,String MethodName, final OperationApiService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
