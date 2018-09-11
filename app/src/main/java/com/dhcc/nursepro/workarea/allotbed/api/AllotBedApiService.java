package com.dhcc.nursepro.workarea.allotbed.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.allotbed.api
 * <p>
 * author Q
 * Date: 2018/9/6
 * Time:10:17
 */
public class AllotBedApiService {

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
    public static void getAllotBedMsg(HashMap<String,String> map, String MethodName, final ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
