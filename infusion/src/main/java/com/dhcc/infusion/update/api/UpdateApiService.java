package com.dhcc.infusion.update.api;

/**
 * UpdateApiService
 *
 * @author DevLix126
 * @date 2018/10/29
 */
public class UpdateApiService {

    public static void getNewVersion(String wardId, String ip, String curVersion, final ServiceCallBack callback) {
//        HashMap<String, String> properties = new HashMap<String, String>();
//        if(!TextUtils.isEmpty(wardId)){// 传""报错
//            properties.put("wardId", wardId);
//        }
//        properties.put("ip", ip);
//        properties.put("curVersion", curVersion);
//        WebServiceUtils.callWebService("GetNewVersion", properties, new WebServiceUtils.WebServiceCallBack() {
//            @Override
//            public void callBack(String result) {
//                callback.onResult(result);
//            }
//        });
    }

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
