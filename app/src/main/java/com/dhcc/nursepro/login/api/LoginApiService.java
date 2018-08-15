package com.dhcc.nursepro.login.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * LoginApiService
 *
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginApiService {
    public static void getLogin(String userCode, String password, String logonWardId, final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("userCode", userCode);
        properties.put("password", password);
        properties.put("logonWardId", logonWardId);
        WebServiceUtils.callWebService("Logon", properties, new WebServiceUtils.WebServiceCallBack() {
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
