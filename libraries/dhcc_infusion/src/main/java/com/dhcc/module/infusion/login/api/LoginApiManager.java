package com.dhcc.module.infusion.login.api;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.bean.LoginBean;
import com.dhcc.module.infusion.login.bean.ScanCodeBean;

/**
 * LoginApiManager
 *
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginApiManager {

    public static void getLogin(String userCode, String password, final String logonWardId, String scanFlag, final CommonCallBack<LoginBean> callBack) {
        LoginApiService.getLogin(userCode, password, logonWardId, scanFlag,new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<LoginBean> parserUtil = new ParserUtil<>();
                LoginBean bean = parserUtil.parserResult(jsonStr, callBack, LoginBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public static void getBroadcastList(final CommonCallBack<ScanCodeBean> callBack) {
        LoginApiService.getBroadcastList(new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<ScanCodeBean> parserUtil = new ParserUtil<>();
                ScanCodeBean bean = parserUtil.parserResult(jsonStr, callBack, ScanCodeBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }


}
