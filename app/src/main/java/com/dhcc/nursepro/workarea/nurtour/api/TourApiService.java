package com.dhcc.nursepro.workarea.nurtour.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.nurtour.api
 * <p>
 * author Q
 * Date: 2019/4/20
 * Time:9:38
 */
public class TourApiService {

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
    public static void getTourListMsg(HashMap<String,String> map, String MethodName, final ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
}
