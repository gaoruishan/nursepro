package com.dhcc.nursepro.workarea.labresult.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.labresult.bean.LabReslutDetailBean;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class LabApiManager {

    //获取病人列表
    public static void getPatsList(HashMap<String, String> map, String method, final GetCheckResultCallback callback) {

        LabApiService.getCheckLabMsg(map, method, new LabApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        PatsListBean patsListBean = gson.fromJson(jsonStr, PatsListBean.class);
                        if (ObjectUtils.isEmpty(patsListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(patsListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(patsListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(patsListBean.getMsgcode(), patsListBean.getMsg());
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

    public static void getLabListMsg(HashMap<String, String> map, String method, final GeLabListCallback callback) {

        LabApiService.getCheckLabMsg(map, method, new LabApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        LabResultListBean labResultListBean = gson.fromJson(jsonStr, LabResultListBean.class);
                        if (ObjectUtils.isEmpty(labResultListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(labResultListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(labResultListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(labResultListBean.getMsgcode(), labResultListBean.getMsg());
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

    public static void getLabDetailMsg(HashMap<String, String> map, String method, final GeLabDetailCallback callback) {

        LabApiService.getCheckLabMsg(map, method, new LabApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        LabReslutDetailBean labReslutDetailBean = gson.fromJson(jsonStr, LabReslutDetailBean.class);
                        if (ObjectUtils.isEmpty(labReslutDetailBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(labReslutDetailBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(labReslutDetailBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(labReslutDetailBean.getMsgcode(), labReslutDetailBean.getMsg());
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

    public interface GetCheckResultCallback extends CommonCallBack {
        void onSuccess(PatsListBean patsListBean);
    }

    public interface GeLabListCallback extends CommonCallBack {
        void onSuccess(LabResultListBean labResultListBean);
    }

    public interface GeLabDetailCallback extends CommonCallBack {
        void onSuccess(LabReslutDetailBean labReslutDetailBean);
    }

}
