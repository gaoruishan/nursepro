package com.dhcc.nursepro.workarea.bloodtransfusionsystem.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationListBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationResultBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodTemDataBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodTransDetailBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.PatWristInfoBean;
import com.google.gson.Gson;

/**
 * BloodTSApiManager
 *
 * @author DevLix126
 * @date 2018/9/19
 */
public class BloodTSApiManager {

    public static void getPatWristInfo(String regNo, final GetPatWristInfoCallback callback) {
        BloodTSApiService.getPatWristInfo(regNo, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        PatWristInfoBean patWristInfoBean = gson.fromJson(jsonStr, PatWristInfoBean.class);
                        if (ObjectUtils.isEmpty(patWristInfoBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(patWristInfoBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(patWristInfoBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(patWristInfoBean.getMsgcode(), patWristInfoBean.getMsg());
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

    public static void getBloodInfo(String bloodbagId, String bloodProductId, final GetBloodInfoCallback callback) {
        BloodTSApiService.getBloodInfo(bloodbagId, bloodProductId, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodInfoBean bloodInfoBean = gson.fromJson(jsonStr, BloodInfoBean.class);
                        if (ObjectUtils.isEmpty(bloodInfoBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if (bloodInfoBean.getStatus().equals("0")) {
                                if (callback != null) {
                                    callback.onSuccess(bloodInfoBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodInfoBean.getMsgcode(), bloodInfoBean.getMsg());
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

    public static void bloodReceive(String bloodbagId, String bloodProductId, String bloodGroup, String patBldGroup, String episodeId, String productDesc, String rowId, final BloodOperationResultCallback callback) {
        BloodTSApiService.bloodReceive(bloodbagId, bloodProductId, bloodGroup, patBldGroup, episodeId, productDesc, rowId, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodOperationResultBean bloodOperationResult = gson.fromJson(jsonStr, BloodOperationResultBean.class);
                        if (ObjectUtils.isEmpty(bloodOperationResult)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodOperationResult.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodOperationResult);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodOperationResult.getMsgcode(), bloodOperationResult.getMsg());
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

    public static void getInfusionBloodInfo(String episodeId, String bloodbagId, String bloodProductId, String type, final GetBloodInfoCallback callback) {
        BloodTSApiService.getInfusionBloodInfo(episodeId, bloodbagId, bloodProductId, type, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodInfoBean bloodInfoBean = gson.fromJson(jsonStr, BloodInfoBean.class);
                        if (ObjectUtils.isEmpty(bloodInfoBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodInfoBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodInfoBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodInfoBean.getMsgcode(), bloodInfoBean.getMsg());
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

    public static void bloodTransStart(String bloodRowId, String userId1, String userId2, String type,String pass1,String pass2, final BloodOperationResultCallback callback) {
        BloodTSApiService.bloodTransStart(bloodRowId, userId1, userId2, type,pass1,pass2, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodOperationResultBean bloodOperationResult = gson.fromJson(jsonStr, BloodOperationResultBean.class);
                        if (ObjectUtils.isEmpty(bloodOperationResult)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodOperationResult.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodOperationResult);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodOperationResult.getMsgcode(), bloodOperationResult.getMsg());
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

    public static void bloodPatrol(String bloodRowId, String recdate, String rectime, String speed, String effect, String situation,String dataArr, final BloodOperationResultCallback callback) {
        BloodTSApiService.bloodPatrol(bloodRowId, recdate, rectime, speed, effect, situation,dataArr, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodOperationResultBean bloodOperationResult = gson.fromJson(jsonStr, BloodOperationResultBean.class);
                        if (ObjectUtils.isEmpty(bloodOperationResult)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodOperationResult.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodOperationResult);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodOperationResult.getMsgcode(), bloodOperationResult.getMsg());
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

    public static void bloodTransEnd(String bloodRowId, String userId, String StopReasonDesc, String endType, String type,String pass, final BloodOperationResultCallback callback) {
        BloodTSApiService.bloodTransEnd(bloodRowId, userId, StopReasonDesc, endType, type,pass, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodOperationResultBean bloodOperationResult = gson.fromJson(jsonStr, BloodOperationResultBean.class);
                        if (ObjectUtils.isEmpty(bloodOperationResult)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodOperationResult.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodOperationResult);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodOperationResult.getMsgcode(), bloodOperationResult.getMsg());
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

    public static void recycleBloodbag(String finalWhere,String bloodRowId, final BloodOperationResultCallback callback) {
        BloodTSApiService.recycleBloodbag(finalWhere,bloodRowId, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodOperationResultBean bloodOperationResult = gson.fromJson(jsonStr, BloodOperationResultBean.class);
                        if (ObjectUtils.isEmpty(bloodOperationResult)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodOperationResult.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodOperationResult);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodOperationResult.getMsgcode(), bloodOperationResult.getMsg());
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

    public static void getBloodList(String type, String date, final BloodOperationListCallback callback) {
        BloodTSApiService.getBloodList(type, date, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodOperationListBean bloodOperationListBean = gson.fromJson(jsonStr, BloodOperationListBean.class);
                        if (ObjectUtils.isEmpty(bloodOperationListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodOperationListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodOperationListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodOperationListBean.getMsgcode(), bloodOperationListBean.getMsg());
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

    public static void getBloodPatrolRecord(String bloodRowId, final BloodTransDetailCallback callback) {
        BloodTSApiService.getBloodPatrolRecord(bloodRowId, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodTransDetailBean bloodTransDetailBean = gson.fromJson(jsonStr, BloodTransDetailBean.class);
                        if (ObjectUtils.isEmpty(bloodTransDetailBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodTransDetailBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodTransDetailBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodTransDetailBean.getMsgcode(), bloodTransDetailBean.getMsg());
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

    //巡视模板
    public static void getBloodTemData(String episodeId, final BloodTemDataCallback callback) {
        BloodTSApiService.getBloodTemData(episodeId, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BloodTemDataBean bloodTemDataBean = gson.fromJson(jsonStr, BloodTemDataBean.class);
                        if (ObjectUtils.isEmpty(bloodTemDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bloodTemDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bloodTemDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bloodTemDataBean.getMsgcode(), bloodTemDataBean.getMsg());
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

    public interface GetPatWristInfoCallback extends CommonCallBack {
        void onSuccess(PatWristInfoBean patWristInfoBean);
    }

    public interface GetBloodInfoCallback extends CommonCallBack {
        void onSuccess(BloodInfoBean bloodInfoBean);
    }

    public interface BloodOperationResultCallback extends CommonCallBack {
        void onSuccess(BloodOperationResultBean bloodOperationResult);
    }

    public interface BloodOperationListCallback extends CommonCallBack {
        void onSuccess(BloodOperationListBean bloodOperationListBean);
    }

    public interface BloodTransDetailCallback extends CommonCallBack {
        void onSuccess(BloodTransDetailBean bloodTransDetailBean);
    }

    public interface BloodTemDataCallback extends CommonCallBack {
        void onSuccess(BloodTemDataBean bloodTemDataBean);
    }
}
