package com.dhcc.module.infusion.login.api;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPUtils;

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
        properties.put("logonLocType", SPUtils.getInstance().getString(SharedPreference.LOGONLOCTYPE));
        BaseWebServiceUtils.callWebOPPDAService("Logon", properties, new BaseWebServiceUtils.WebServiceCallBack() {
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
