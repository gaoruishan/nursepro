package com.dhcc.nursepro.workarea.Infusionsituation.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * InfusionSituationApiService
 *
 *
 * @author DevLix126
 * created at 2021/4/19 14:34
 */
public class InfusionSituationApiService {
    public interface serveCallback{
        void onResult(String result);
    }
    public static void GetInfusionByWard(HashMap<String,String> map,String method,final serveCallback callback){
        WebServiceUtils.callWebService(method, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void GetInfusionDetailByWard(HashMap<String,String> map,String method,final serveCallback callback){
        WebServiceUtils.callWebService(method, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

}
