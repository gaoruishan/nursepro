package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.GetOrdInfoBean;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.GetTakeOrdListBean;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.TakeOrdBean;
import com.google.gson.Gson;

/**
 * DrugPreApiManager
 *
 * @author Devlix126
 * created at 2019/6/6 10:42
 */
public class DrugPreApiManager {
    public static void getOrdInfo(String oeoreId, final GetOrdInfoCallback callback) {
        DrugPreApiService.getOrdInfo(oeoreId, new DrugPreApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetOrdInfoBean getOrdInfoBean = gson.fromJson(jsonStr, GetOrdInfoBean.class);
                        if (ObjectUtils.isEmpty(getOrdInfoBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getOrdInfoBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getOrdInfoBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getOrdInfoBean.getMsgcode(), getOrdInfoBean.getMsg());
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

    public static void takeOrd(String oeoreId, String type, final TakeOrdCallback callback) {
        DrugPreApiService.takeOrd(oeoreId, type, new DrugPreApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        TakeOrdBean takeOrdBean = gson.fromJson(jsonStr, TakeOrdBean.class);
                        if (ObjectUtils.isEmpty(takeOrdBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(takeOrdBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(takeOrdBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(takeOrdBean.getMsgcode(), takeOrdBean.getMsg());
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

    public static void getTakeOrdList(String startDate, String endDate, String type, final GetTakeOrdListCallback callback) {
        DrugPreApiService.getTakeOrdList(startDate, endDate, type, new DrugPreApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetTakeOrdListBean getTakeOrdListBean = gson.fromJson(jsonStr, GetTakeOrdListBean.class);
                        if (ObjectUtils.isEmpty( getTakeOrdListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals( getTakeOrdListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess( getTakeOrdListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail( getTakeOrdListBean.getMsgcode(),  getTakeOrdListBean.getMsg());
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

    public interface GetOrdInfoCallback extends CommonCallBack {
        void onSuccess(GetOrdInfoBean getOrdInfoBean);
    }

    public interface TakeOrdCallback extends CommonCallBack {
        void onSuccess(TakeOrdBean takeOrdBean);
    }

    public interface GetTakeOrdListCallback extends CommonCallBack {
        void onSuccess(GetTakeOrdListBean getTakeOrdListBean);
    }
}
