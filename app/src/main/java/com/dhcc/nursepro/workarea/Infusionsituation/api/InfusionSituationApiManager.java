package com.dhcc.nursepro.workarea.Infusionsituation.api;

import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionDetailByWardBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.OperateResultBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.PreparedVerifyOrdBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.ScanResultBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * InfusionSituationApiManager
 *
 *
 * @author DevLix126
 * created at 2021/6/30 10:03
 */
public class InfusionSituationApiManager {

    public static void GetInfusionByWard(HashMap<String, String> map, String method, final GetInfusionByWardCallback callback) {

        InfusionSituationApiService.GetInfusionByWard(map, method, new InfusionSituationApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetInfusionByWardBean getInfusionByWardBean = gson.fromJson(jsonStr, GetInfusionByWardBean.class);
                        if (ObjectUtils.isEmpty(getInfusionByWardBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getInfusionByWardBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getInfusionByWardBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getInfusionByWardBean.getMsgcode(), getInfusionByWardBean.getMsg());
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

    public static void GetInfusionDetailByWard(HashMap<String, String> map, String method, final GetInfusionDetailByWardCallback callback) {

        InfusionSituationApiService.GetInfusionDetailByWard(map, method, new InfusionSituationApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetInfusionDetailByWardBean getInfusionDetailByWardBean = gson.fromJson(jsonStr, GetInfusionDetailByWardBean.class);
                        if (ObjectUtils.isEmpty(getInfusionDetailByWardBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getInfusionDetailByWardBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getInfusionDetailByWardBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getInfusionDetailByWardBean.getMsgcode(), getInfusionDetailByWardBean.getMsg());
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

        InfusionSituationApiService.getOrdersMsgByMain(episodeId, curOeordId, scanInfo, new InfusionSituationApiService.ServiceCallBack() {
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
                        Log.e("baocuo", "onResult: " + e.toString());
                    }
                }

            }
        });
    }

    public static void preparedVerifyOrd(String oeoriId, String status, final PrepareVeriftyCallback callback) {
        InfusionSituationApiService.preparedVerifyOrd(oeoriId, status, new InfusionSituationApiService.ServiceCallBack() {

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

    public static void tourOrd(String speed, String reason, String oeoreId, final OperateResultCallBack callback) {

        InfusionSituationApiService.tourOrd(speed, reason, oeoreId, new InfusionSituationApiService.ServiceCallBack() {
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

    public static void suspendOrd(String speed, String oeoreId, String infusionState, String infusionReason, final OperateResultCallBack callback) {

        InfusionSituationApiService.suspendOrd(speed, oeoreId, infusionState, infusionReason, new InfusionSituationApiService.ServiceCallBack() {
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

    public static void continueOrd(String speed, String oeoreId, final OperateResultCallBack callback) {

        InfusionSituationApiService.continueOrd(speed, oeoreId, new InfusionSituationApiService.ServiceCallBack() {
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

    public static void stopOrd(String speed, String oeoreId, String infusionState, String ResidualQty, String infusionReason, final OperateResultCallBack callback) {

        InfusionSituationApiService.stopOrd(speed, oeoreId, infusionState, ResidualQty, infusionReason, new InfusionSituationApiService.ServiceCallBack() {
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

    public static void endOrd(String speed, String reason, String oeoreId, final OperateResultCallBack callback) {

        InfusionSituationApiService.endOrd(speed, oeoreId, reason, new InfusionSituationApiService.ServiceCallBack() {
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
    
    public interface GetInfusionByWardCallback extends CommonCallBack {
        void onSuccess(GetInfusionByWardBean getInfusionByWardBean);
    }

    public interface GetInfusionDetailByWardCallback extends CommonCallBack {
        void onSuccess(GetInfusionDetailByWardBean getInfusionDetailByWardBean);
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
