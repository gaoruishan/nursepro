package com.dhcc.nursepro.login.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.login.bean.GetScanActionBean;
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



    public static void getScan(final GetScanActionCallback callback) {
        LoginApiService.getScanAction(new LoginApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetScanActionBean getScanActionBean = gson.fromJson(jsonStr, GetScanActionBean.class);
                        if (ObjectUtils.isEmpty(getScanActionBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getScanActionBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getScanActionBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getScanActionBean.getMsgcode(), getScanActionBean.getMsg());
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




    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetLoginCallback extends CommonCallBack {
        void onSuccess(LoginBean loginBean);
    }

    public interface GetScanActionCallback extends CommonCallBack {
        void onSuccess(GetScanActionBean getScanActionBean);
    }
}
