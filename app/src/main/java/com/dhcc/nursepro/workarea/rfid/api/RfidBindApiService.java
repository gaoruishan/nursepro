package com.dhcc.nursepro.workarea.rfid.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;
import com.dhcc.nursepro.workarea.plyout.api.PlyOutApiService;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.rfid.api
 * <p>
 * author Q
 * Date: 2021/6/23
 * Time:9:42
 */
public class RfidBindApiService {
    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
    public static void getRfidPatList  (HashMap<String,String> map, String MethodName, final PlyOutApiService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
}
