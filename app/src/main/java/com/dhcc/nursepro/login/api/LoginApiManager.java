package com.dhcc.nursepro.login.api;

import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.login.bean.BroadCastListBean;
import com.dhcc.nursepro.login.bean.LoginBean;
import com.google.gson.Gson;

/**
 * LoginApiManager
 *
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginApiManager {


    public static void getBroadcastConfig(final GetBroadCastConfigCallback callback) {
        LoginApiService.getBroadcastConfig(new LoginApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BroadCastListBean broadCastListBean = gson.fromJson(jsonStr, BroadCastListBean.class);
                        if (ObjectUtils.isEmpty(broadCastListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(broadCastListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(broadCastListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(broadCastListBean.getMsgcode(), broadCastListBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
//                        callback.onFail("-2", "网络错误，数据解析失败");
                        Log.e("TAG","(LoginApiManager.java:45) "+e.toString());
                    }


                }
            }
        });
    }

    public static void GetUserPwd(String userCode, final GetUserPwdCallback callback) {
        LoginApiService.GetUserPwd(userCode, new LoginApiService.ServiceCallBack() {
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

    public static void ScanLogon(String userCode, final ScanLogonCallback callback) {
        LoginApiService.ScanLogon(userCode, new LoginApiService.ServiceCallBack() {
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

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetBroadCastConfigCallback extends CommonCallBack {
        void onSuccess(BroadCastListBean broadCastListBean);
    }

    public interface GetUserPwdCallback extends CommonCallBack {
        void onSuccess(LoginBean loginBean);
    }

    public interface ScanLogonCallback extends CommonCallBack {
        void onSuccess(LoginBean loginBean);
    }

    public interface GetLoginCallback extends CommonCallBack {
        void onSuccess(LoginBean loginBean);
    }

}
