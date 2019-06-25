package com.dhcc.module.infusion.login.api;

import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.module.infusion.login.bean.LoginBean;
import com.dhcc.module.infusion.login.bean.ScanCodeBean;
import com.google.gson.Gson;

/**
 * LoginApiManager
 *
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginApiManager {

    public static void getLogin(String userCode, String password, String logonWardId,String scanFlag, final GetLoginCallback callback) {
        LoginApiService.getLogin(userCode, password, logonWardId, scanFlag,new LoginApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        LoginBean loginBean = gson.fromJson(jsonStr, LoginBean.class);
                        if (ObjectUtils.isEmpty(loginBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
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
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }


                }
            }
        });
    }

    public static void getBroadcastList(final com.base.commlibs.http.CommonCallBack<ScanCodeBean> callBack) {
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


    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetLoginCallback extends CommonCallBack {
        void onSuccess(LoginBean loginBean);
    }

}
