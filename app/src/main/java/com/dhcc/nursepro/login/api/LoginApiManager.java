package com.dhcc.nursepro.login.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.login.bean.LoginBean;
import com.google.gson.Gson;

/**
 * LoginApiManager
 *
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginApiManager {

    public static void getLogin(String userCode, String password, String logonWardId, final GetLoginCallback callback) {
        LoginApiService.getLogin(userCode, password, logonWardId, new LoginApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    LoginBean loginBean = gson.fromJson(jsonStr, LoginBean.class);
                    if (ObjectUtils.isEmpty(loginBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if ("0".equals(loginBean.getStatus())) {
                            if (callback != null) {
                                callback.onSuccess(loginBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(loginBean.getMsgcode(), loginBean.getMsg());
                            }
                        }
                    }
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
