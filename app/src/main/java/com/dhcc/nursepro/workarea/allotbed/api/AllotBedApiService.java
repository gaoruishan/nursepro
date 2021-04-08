package com.dhcc.nursepro.workarea.allotbed.api;

import com.base.commlibs.NurseAPI;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.allotbed.api
 * <p>
 * author Q
 * Date: 2018/9/6
 * Time:10:17
 */
public class AllotBedApiService extends NurseAPI {

    public static void getAllotBedMsg(HashMap<String, String> map, String MethodName, final ServiceCallBack callback) {

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public static void isFirstToBed(String episodeId, final ServiceCallBack callBack) {

        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeID", episodeId);

        WebServiceUtils.callWebService(isFirstToBed, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    public static void firstBeHosTemData(String episodeId, final ServiceCallBack callBack) {

        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);

        WebServiceUtils.callWebService(firstBeHosTemData, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }

}
