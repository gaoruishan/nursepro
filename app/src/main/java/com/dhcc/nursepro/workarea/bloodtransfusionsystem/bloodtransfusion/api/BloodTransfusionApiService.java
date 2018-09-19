package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.api
 * <p>
 * author Q
 * Date: 2018/9/18
 * Time:11:29
 */
public class BloodTransfusionApiService {
    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
    public static void getTransfusionMsg(HashMap<String,String> map, String MethodName, final ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
}
