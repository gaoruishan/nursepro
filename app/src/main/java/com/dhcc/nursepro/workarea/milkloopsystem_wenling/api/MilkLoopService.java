package com.dhcc.nursepro.workarea.milkloopsystem_wenling.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class MilkLoopService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getMilkLoopMsg(HashMap<String,String> map,String MethodName, final MilkLoopService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
