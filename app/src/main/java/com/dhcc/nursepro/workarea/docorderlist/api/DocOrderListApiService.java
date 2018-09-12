package com.dhcc.nursepro.workarea.docorderlist.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.docorderlist.api
 * <p>
 * author Q
 * Date: 2018/9/11
 * Time:10:03
 */
public class DocOrderListApiService {


    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
    public static void getDocOrderListMsg(HashMap<String,String> map, String MethodName, final ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
}
