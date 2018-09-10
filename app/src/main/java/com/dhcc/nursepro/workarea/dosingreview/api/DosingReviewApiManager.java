package com.dhcc.nursepro.workarea.dosingreview.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.dosingreview.bean.DosingReViewBean;
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
                    callback.onFail("-1", "网络请求失败");
                } else {
                    DosingReViewBean dosingReViewBean = gson.fromJson(jsonStr, DosingReViewBean.class);
                    if (ObjectUtils.isEmpty(dosingReViewBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (dosingReViewBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(dosingReViewBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(dosingReViewBean.getMsgcode(), dosingReViewBean.getMsg());
                            }
                        }
                    }

                }
            }
        });
    }

    public static void preparedVerifyOrd(String oeorild, String flag, final DosingReviewCallback callback) {
        DosingReviewApiService.preparedVerifyOrd(oeorild, flag, new DosingReviewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {
//                    DosingReViewBean dosingReViewBean = gson.fromJson(jsonStr, DosingReViewBean.class);
//                    if (ObjectUtils.isEmpty(dosingReViewBean)) {
//                        callback.onFail("-1", "网络请求失败");
//                    } else {
//                        if (dosingReViewBean.getStatus().equals("0")) {
//                            if (callback != null) {
//                                callback.onSuccess(dosingReViewBean);
//                            }
//                        } else {
//                            if (callback != null) {
//                                callback.onFail(dosingReViewBean.getMsgcode(), dosingReViewBean.getMsg());
//                            }
//                        }
//                    }

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
}
