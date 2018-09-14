package com.dhcc.nursepro.message.api;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class MessageApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getMessage(final ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        WebServiceUtils.callWebService("getNotifyList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public static void readMessage(String episodeId, final ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        properties.put("episodeId", episodeId);
        WebServiceUtils.callWebService("readMessage", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
