package com.dhcc.module.health.login.api;

import com.base.commlibs.bean.LoginBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-10-21/16:46
 * @email:grs0515@163.com
 */
public class LoginApiManager {

    public static void getLogin(String userCode, String password, final String logonWardId, String scanFlag, final CommonCallBack<LoginBean> callBack) {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("userCode", userCode);
        properties.put("password", password);
        properties.put("logonWardId", logonWardId);
        properties.put("scanFlag", scanFlag);
        properties.put("logonLocType", SPUtils.getInstance().getString(SharedPreference.LOGONLOCTYPE));
        CommWebService.callHealth("OTLogon", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<LoginBean> parserUtil = new ParserUtil<>();
                LoginBean bean = parserUtil.parserResult(jsonStr, callBack, LoginBean.class, "");
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

//    public static void getBroadcastList(final CommonCallBack<ScanCodeBean> callBack) {
//        CommWebService.call("getBroadcastConfig", null, new ServiceCallBack() {
//            @Override
//            public void onResult(String jsonStr) {
//                ParserUtil<ScanCodeBean> parserUtil = new ParserUtil<>();
//                ScanCodeBean bean = parserUtil.parserResult(jsonStr, callBack, ScanCodeBean.class,"");
//                if (bean == null) return;
//                parserUtil.parserStatus(bean, callBack);
//            }
//        });
//    }

}
