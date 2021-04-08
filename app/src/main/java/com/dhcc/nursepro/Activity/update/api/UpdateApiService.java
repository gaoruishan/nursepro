package com.dhcc.nursepro.Activity.update.api;

import com.base.commlibs.NurseAPI;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * UpdateApiService
 *
 * @author DevLix126
 * @date 2018/10/29
 */
public class UpdateApiService extends NurseAPI {

    public static void getNewVersion(String wardId, String ip, String curVersion, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("wardId", wardId);
        properties.put("ip", ip);
        properties.put("curVersion", curVersion);
        WebServiceUtils.callWebService(GetNewVersion, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
