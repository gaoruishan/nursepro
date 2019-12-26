package com.dhcc.nursepro.workarea.workareaapi;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;
import com.dhcc.nursepro.workarea.workareabean.OperateResultBean;
import com.dhcc.nursepro.workarea.workareabean.PreparedVerifyOrdBean;
import com.dhcc.nursepro.workarea.workareabean.ScanResultBean;
import com.google.gson.Gson;

public class WorkareaApiManager {

    //获取主页配置列表
    public static void getMainConfig(final GetMainconfigCallback callback) {

        WorkareaApiService.getMainConfig(new WorkareaApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MainConfigBean mainConfigBean = gson.fromJson(jsonStr, MainConfigBean.class);
                        if (ObjectUtils.isEmpty(mainConfigBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(mainConfigBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(mainConfigBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(mainConfigBean.getMsgcode(), mainConfigBean.getMsg());
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

    public static void getScanMsgByMain(String episodeId, String curOeordId, String scanInfo, final GetScanCallBack callback) {

        WorkareaApiService.getOrdersMsgByMain(episodeId, curOeordId, scanInfo, new WorkareaApiService.ServiceCallBack() {
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

    public static void preparedVerifyOrd(String oeoriId, String status, final PrepareVeriftyCallback callback) {
        WorkareaApiService.preparedVerifyOrd(oeoriId, status, new WorkareaApiService.ServiceCallBack() {

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

    public static void tourOrd(String oeoreId, final OperateResultCallBack callback) {

        WorkareaApiService.tourOrd(oeoreId, new WorkareaApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OperateResultBean operateResultBean = gson.fromJson(jsonStr, OperateResultBean.class);
                        if (ObjectUtils.isEmpty(operateResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(operateResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(operateResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(operateResultBean.getMsgcode(), operateResultBean.getMsg());
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

    public static void suspendOrd(String oeoreId, String infusionState, String infusionReason, final OperateResultCallBack callback) {

        WorkareaApiService.suspendOrd(oeoreId, infusionState, infusionReason, new WorkareaApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OperateResultBean operateResultBean = gson.fromJson(jsonStr, OperateResultBean.class);
                        if (ObjectUtils.isEmpty(operateResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(operateResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(operateResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(operateResultBean.getMsgcode(), operateResultBean.getMsg());
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

    public static void continueOrd(String oeoreId, final OperateResultCallBack callback) {

        WorkareaApiService.continueOrd(oeoreId, new WorkareaApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OperateResultBean operateResultBean = gson.fromJson(jsonStr, OperateResultBean.class);
                        if (ObjectUtils.isEmpty(operateResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(operateResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(operateResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(operateResultBean.getMsgcode(), operateResultBean.getMsg());
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

    public static void stopOrd(String oeoreId, String infusionState, String ResidualQty, String infusionReason, final OperateResultCallBack callback) {

        WorkareaApiService.stopOrd(oeoreId, infusionState, ResidualQty, infusionReason, new WorkareaApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OperateResultBean operateResultBean = gson.fromJson(jsonStr, OperateResultBean.class);
                        if (ObjectUtils.isEmpty(operateResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(operateResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(operateResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(operateResultBean.getMsgcode(), operateResultBean.getMsg());
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

    public static void endOrd(String oeoreId, final OperateResultCallBack callback) {

        WorkareaApiService.endOrd(oeoreId, new WorkareaApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OperateResultBean operateResultBean = gson.fromJson(jsonStr, OperateResultBean.class);
                        if (ObjectUtils.isEmpty(operateResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(operateResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(operateResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(operateResultBean.getMsgcode(), operateResultBean.getMsg());
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

    public interface GetMainconfigCallback extends CommonCallBack {
        void onSuccess(MainConfigBean mainConfigBean);
    }

    //扫描腕带,医嘱码
    public interface GetScanCallBack extends CommonCallBack {
        void onSuccess(ScanResultBean scanResultBean);
    }

    //配液复核
    public interface PrepareVeriftyCallback extends CommonCallBack {
        void onSuccess(PreparedVerifyOrdBean preparedVerifyOrdBean);
    }

    //操作结果
    public interface OperateResultCallBack extends CommonCallBack {
        void onSuccess(OperateResultBean operateResultBean);
    }

}
