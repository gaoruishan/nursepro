package com.dhcc.nursepro.config.api;

import com.dhcc.nursepro.config.NurseConfig;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class ConfigApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getConfig(final ConfigApiService.ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("groupId", "5");
        properties.put("userId", "2");
//        properties.put("groupId", NurseConfig.getInstance().getGroupId());
//        properties.put("userId", NurseConfig.getInstance().getUserId());
        WebServiceUtils.callWebService("getConfig", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
