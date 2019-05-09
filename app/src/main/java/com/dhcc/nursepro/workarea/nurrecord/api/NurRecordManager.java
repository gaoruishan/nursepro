package com.dhcc.nursepro.workarea.nurrecord.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordModelListBean;
import com.dhcc.nursepro.workarea.nurrecord.bean.ScanResultBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class NurRecordManager {

    public static void getModelDetailListMsg(HashMap<String, String> map, String method, final getModelDetailCallBack callback) {

        NurRecordService.getModelDetailMsg(map, method, new NurRecordService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        NurRecordBean modelDetailBean = gson.fromJson(jsonStr, NurRecordBean.class);
                        if (ObjectUtils.isEmpty(modelDetailBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(modelDetailBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(modelDetailBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(modelDetailBean.getMsgcode(), modelDetailBean.getMsg());
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

    public static void getModelList(HashMap<String, String> map, String method, final getModelListCallBack callback) {

        NurRecordService.getModelDetailMsg(map, method, new NurRecordService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        NurRecordModelListBean nurRecordModelListBean = gson.fromJson(jsonStr, NurRecordModelListBean.class);
                        if (ObjectUtils.isEmpty(nurRecordModelListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(nurRecordModelListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(nurRecordModelListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(nurRecordModelListBean.getMsgcode(), nurRecordModelListBean.getMsg());
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

    public static void getScanInfo(HashMap<String, String> map, String method, final GetScanInfoCallback callback) {
        NurRecordService.getModelDetailMsg(map, method, new NurRecordService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ScanResultBean scanResultBean = gson.fromJson(jsonStr, ScanResultBean.class);

                        if (ObjectUtils.isEmpty(scanResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(scanResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(scanResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(scanResultBean.getMsgcode(), scanResultBean.getMsg());
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


    public static void saveNurRecord(HashMap<String, String> map, String method, final GetScanInfoCallback callback) {
        NurRecordService.getModelDetailMsg(map, method, new NurRecordService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ScanResultBean scanResultBean = gson.fromJson(jsonStr, ScanResultBean.class);

                        if (ObjectUtils.isEmpty(scanResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(scanResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(scanResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(scanResultBean.getMsgcode(), scanResultBean.getMsg());
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

    //病历模板详情
    public interface GetScanInfoCallback extends CommonCallBack {
        void onSuccess(ScanResultBean scanResultBean);
    }

    //病历模板详情
    public interface getModelDetailCallBack extends CommonCallBack {
        void onSuccess(NurRecordBean modelDetailBean);
    }

    //病历模板列表
    public interface getModelListCallBack extends CommonCallBack {
        void onSuccess(NurRecordModelListBean nurRecordModelListBean);
    }
}
