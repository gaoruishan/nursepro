package com.dhcc.module.infusion.login.api;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.InfusionAPI;

import java.util.HashMap;

/**
 * LoginApiService
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginApiService extends InfusionAPI {

    public static void getLogin(String userCode, String password, String logonWardId, String scanFlag, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("userCode", userCode);
        properties.put("password", password);
        properties.put("logonWardId", logonWardId);
        properties.put("scanFlag", scanFlag);
        properties.put("logonLocType", SPUtils.getInstance().getString(SharedPreference.LOGONLOCTYPE));
        CommWebService.call(Logon, properties, callback);
    }


}
