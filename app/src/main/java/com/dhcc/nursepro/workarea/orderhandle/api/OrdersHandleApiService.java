package com.dhcc.nursepro.workarea.orderhandle.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class OrdersHandleApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getOrdersMsg(HashMap<String,String> map,String MethodName, final OrdersHandleApiService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
