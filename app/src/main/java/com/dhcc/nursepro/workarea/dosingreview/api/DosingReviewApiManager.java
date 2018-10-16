package com.dhcc.nursepro.workarea.dosingreview.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.dosingreview.bean.DosingReViewBean;
import com.dhcc.nursepro.workarea.dosingreview.bean.PreparedVerifyOrdBean;
import com.google.gson.Gson;

/**
 * DosingReviewApiManager
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class DosingReviewApiManager {
    public static void getInfusionOrdList(String infusionFlag, String startDate, String endDate, String pageNo, final DosingReviewCallback callback) {
        DosingReviewApiService.getInfusionOrdList(infusionFlag, startDate, endDate, pageNo, new DosingReviewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DosingReViewBean dosingReViewBean = gson.fromJson(jsonStr, DosingReViewBean.class);
                        if (ObjectUtils.isEmpty(dosingReViewBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(dosingReViewBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(dosingReViewBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(dosingReViewBean.getMsgcode(), dosingReViewBean.getMsg());
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

    public static void getInfusionOrdListAfterCancel(String infusionFlag, String oeorild, String startDate, String endDate, String pageNo, final DosingReviewCallback callback) {
        DosingReviewApiService.getInfusionOrdListAfterCancel(infusionFlag, oeorild, startDate, endDate, pageNo, new DosingReviewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DosingReViewBean dosingReViewBean = gson.fromJson(jsonStr, DosingReViewBean.class);
                        if (ObjectUtils.isEmpty(dosingReViewBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(dosingReViewBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(dosingReViewBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(dosingReViewBean.getMsgcode(), dosingReViewBean.getMsg());
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

    public static void preparedVerifyOrd(String oeoriId, String status, final PrepareVeriftyCallback callback) {
        DosingReviewApiService.preparedVerifyOrd(oeoriId, status, new DosingReviewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        PreparedVerifyOrdBean preparedVerifyOrdBean = gson.fromJson(jsonStr, PreparedVerifyOrdBean.class);
                        if (ObjectUtils.isEmpty(preparedVerifyOrdBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(preparedVerifyOrdBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(preparedVerifyOrdBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(preparedVerifyOrdBean.getMsgcode(), preparedVerifyOrdBean.getMsg());
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

    public interface DosingReviewCallback extends CommonCallBack {
        void onSuccess(DosingReViewBean dosingReViewBean);
    }

    public interface PrepareVeriftyCallback extends CommonCallBack {
        void onSuccess(PreparedVerifyOrdBean preparedVerifyOrdBean);
    }
}
