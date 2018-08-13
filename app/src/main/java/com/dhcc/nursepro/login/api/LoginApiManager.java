package com.dhcc.nursepro.login.api;

import com.dhcc.nursepro.login.bean.LoginBean;
import com.google.gson.Gson;

/**
 * LoginApiManager
 *
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginApiManager {

    public static void getLogin(final GetLoginCallback callback) {
        LoginApiService.getLogin(new LoginApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(jsonStr, LoginBean.class);
                if (loginBean != null) {
                    callback.onSuccess(loginBean);
                } else {
                    callback.onFail("", "");
                }
            }
        });
    }

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetLoginCallback extends CommonCallBack {
        void onSuccess(LoginBean loginBean);
    }
}
