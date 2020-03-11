package com.dhcc.nursepro.workarea.infusiondrugreceive.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.infusiondrugreceive.api
 * <p>
 * author Q
 * Date: 2020/3/11
 * Time:11:06
 */
public class DrugReceiveApiService {
    public interface serveCallback{
        void onResult(String result);
    }
    public static void getIfOrdList(HashMap<String,String> map,String method,final serveCallback callback){
        WebServiceUtils.callWebService(method, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getSaveResult(HashMap<String,String> map,String method,final serveCallback callback){
        WebServiceUtils.callWebService(method, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

}
