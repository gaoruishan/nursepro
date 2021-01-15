package com.dhcc.nursepro.workarea.pathandover.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.pathandover.bean.GetConnectAndPatBean;
import com.dhcc.nursepro.workarea.pathandover.bean.GetConnectListBean;
import com.dhcc.nursepro.workarea.pathandover.bean.GetNurseInfoBean;
import com.dhcc.nursepro.workarea.pathandover.bean.GetScanConnectBean;
import com.dhcc.nursepro.workarea.pathandover.bean.SaveConnectBean;
import com.dhcc.nursepro.workarea.pathandover.bean.SaveConnectSubBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class PatHandoverApiManager {

    public static void getConnectList(String startDate, String endDate, String type, final GetConnectListCallback callback) {
        PatHandoverApiService.getConnectList(startDate, endDate, type, new PatHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    GetConnectListBean getConnectListBean = null;
                    try {
                        getConnectListBean = gson.fromJson(jsonStr, GetConnectListBean.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                    if (ObjectUtils.isEmpty(getConnectListBean)) {
                        callback.onFail("-3", "网络错误，数据解析为空");
                    } else {
                        if ("0".equals(getConnectListBean.getStatus())) {
                            if (callback != null) {
                                callback.onSuccess(getConnectListBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(getConnectListBean.getMsgcode(), getConnectListBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }


    public static void getScanConnect(String regNo, final GetScanConnectCallback callback) {
        PatHandoverApiService.getScanConnect(regNo, new PatHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    GetScanConnectBean getScanConnectBean = null;
                    try {
                        getScanConnectBean = gson.fromJson(jsonStr, GetScanConnectBean.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                    if (ObjectUtils.isEmpty(getScanConnectBean)) {
                        callback.onFail("-3", "网络错误，数据解析为空");
                    } else {
                        if ("0".equals(getScanConnectBean.getStatus())) {
                            if (callback != null) {
                                callback.onSuccess(getScanConnectBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(getScanConnectBean.getMsgcode(), getScanConnectBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }

    public static void saveConnect(String regNo, String type, final SaveConnectCallback callback) {
        PatHandoverApiService.saveConnect(regNo, type, new PatHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    SaveConnectBean saveConnectBean = null;
                    try {
                        saveConnectBean = gson.fromJson(jsonStr, SaveConnectBean.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                    if (ObjectUtils.isEmpty(saveConnectBean)) {
                        callback.onFail("-3", "网络错误，数据解析为空");
                    } else {
                        if ("0".equals(saveConnectBean.getStatus())) {
                            if (callback != null) {
                                callback.onSuccess(saveConnectBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(saveConnectBean.getMsgcode(), saveConnectBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }

    public static void getConnectAndPat(String regNo, String type, final GetConnectAndPatCallback callback) {
        PatHandoverApiService.getConnectAndPat(regNo, type, new PatHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    GetConnectAndPatBean getConnectAndPatBean = null;
                    try {
                        getConnectAndPatBean = gson.fromJson(jsonStr, GetConnectAndPatBean.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                    if (ObjectUtils.isEmpty(getConnectAndPatBean)) {
                        callback.onFail("-3", "网络错误，数据解析为空");
                    } else {
                        if ("0".equals(getConnectAndPatBean.getStatus())) {
                            if (callback != null) {
                                callback.onSuccess(getConnectAndPatBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(getConnectAndPatBean.getMsgcode(), getConnectAndPatBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }


    public static void getNurseInfo(String userCode, String password, final getNurseInfoCallback callback) {
        PatHandoverApiService.getNurseInfo(userCode, password, new PatHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    GetNurseInfoBean getNurseInfoBean = null;
                    try {
                        getNurseInfoBean = gson.fromJson(jsonStr, GetNurseInfoBean.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                    if (ObjectUtils.isEmpty(getNurseInfoBean)) {
                        callback.onFail("-3", "网络错误，数据解析为空");
                    } else {
                        if ("0".equals(getNurseInfoBean.getStatus())) {
                            if (callback != null) {
                                callback.onSuccess(getNurseInfoBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(getNurseInfoBean.getMsgcode(), getNurseInfoBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }

    public static void saveConnectSub(String parentId, String regNo, String type, String firstUser, String secondUser, String thirdUser, final SaveConnectSubCallback callback) {
        PatHandoverApiService.saveConnectSub(parentId, regNo, type, firstUser, secondUser, thirdUser, new PatHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    SaveConnectSubBean saveConnectSubBean = null;
                    try {
                        saveConnectSubBean = gson.fromJson(jsonStr, SaveConnectSubBean.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                    if (ObjectUtils.isEmpty(saveConnectSubBean)) {
                        callback.onFail("-3", "网络错误，数据解析为空");
                    } else {
                        if ("0".equals(saveConnectSubBean.getStatus())) {
                            if (callback != null) {
                                callback.onSuccess(saveConnectSubBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(saveConnectSubBean.getMsgcode(), saveConnectSubBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }


    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetConnectListCallback extends CommonCallBack {
        void onSuccess(GetConnectListBean getConnectListBean);
    }

    public interface GetScanConnectCallback extends CommonCallBack {
        void onSuccess(GetScanConnectBean getScanConnectBean);
    }

    public interface SaveConnectCallback extends CommonCallBack {
        void onSuccess(SaveConnectBean saveConnectBean);
    }

    public interface GetConnectAndPatCallback extends CommonCallBack {
        void onSuccess(GetConnectAndPatBean getConnectAndPatBean);
    }

    public interface getNurseInfoCallback extends CommonCallBack {
        void onSuccess(GetNurseInfoBean getNurseInfoBean);
    }

    public interface SaveConnectSubCallback extends CommonCallBack {
        void onSuccess(SaveConnectSubBean saveConnectSubBean);
    }

}
