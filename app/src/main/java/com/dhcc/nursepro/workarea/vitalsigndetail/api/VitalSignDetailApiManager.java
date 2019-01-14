package com.dhcc.nursepro.workarea.vitalsigndetail.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.vitalsigndetail.bean.VitalSignDetailBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class VitalSignDetailApiManager {

    //查询病人生命体征
    public static void getVitalSignDetail(HashMap<String, String> map, final GetEventsResultMsgCallBack callback) {

        VitalSignDetailApiService.getEventMsg(map, "getPatTempDetail", new VitalSignDetailApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        VitalSignDetailBean vitalSignDetailBean = gson.fromJson(jsonStr, VitalSignDetailBean.class);
                        Map JsonMap = gson.fromJson(jsonStr,Map.class);
                        vitalSignDetailBean.setMap(JsonMap);
                        if (ObjectUtils.isEmpty(vitalSignDetailBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(vitalSignDetailBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(vitalSignDetailBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(vitalSignDetailBean.getMsgcode(), vitalSignDetailBean.getMsg());
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

    //    //病人事件services数据
    //    public static void getPatEventsList(HashMap<String, String> map, final GetEventsSelectCallBack callback) {
    //
    //
    //        VitalSignDetailApiService.getEventMsg(map, "getPatEvents", new VitalSignDetailApiService.ServiceCallBack() {
    //            @Override
    //            public void onResult(String jsonStr) {
    //
    //                //                callback.onSuccess(null);
    //
    //                Gson gson = new Gson();
    //                if (jsonStr.isEmpty()) {
    //                    callback.onFail("-1", "网络错误，请求数据为空");
    //                } else {
    //
    //                    PatEventsBean patEventsBean = gson.fromJson(jsonStr, PatEventsBean.class);
    //                    if (ObjectUtils.isEmpty(patEventsBean)) {
    //                        callback.onFail("-1", "网络错误，请求数据为空");
    //                    } else {
    //                        if (patEventsBean.getStatus().equals("0")) {
    //                            if (callback != null) {
    //                                callback.onSuccess(patEventsBean);
    //                            }
    //                        } else {
    //                            if (callback != null) {
    //                                callback.onFail(patEventsBean.getMsgcode(), patEventsBean.getMsg());
    //                            }
    //                        }
    //                    }
    //                }
    //            }
    //        });
    //    }
    //
    //    //事件types services数据
    //    public static void getEventsTypes(HashMap<String, String> map, final GetEventsTypesCallBack callback) {
    //
    //        VitalSignDetailApiService.getEventMsg(map, "getEventItem", new VitalSignDetailApiService.ServiceCallBack() {
    //            @Override
    //            public void onResult(String jsonStr) {
    //                Gson gson = new Gson();
    //
    //                if (jsonStr.isEmpty()) {
    //                    callback.onFail("-1", "网络错误，请求数据为空");
    //                } else {
    //
    //                    EventItemBean eventItemBean = gson.fromJson(jsonStr, EventItemBean.class);
    //                    if (ObjectUtils.isEmpty(eventItemBean)) {
    //                        callback.onFail("-1", "网络错误，请求数据为空");
    //                    } else {
    //                        if (eventItemBean.getStatus().equals("0")) {
    //                            if (callback != null) {
    //                                callback.onSuccess(eventItemBean);
    //                            }
    //                        } else {
    //                            if (callback != null) {
    //                                callback.onFail(eventItemBean.getMsgcode(), eventItemBean.getMsg());
    //                            }
    //                        }
    //                    }
    //                }
    //            }
    //        });
    //    }
    //
    //    //事件types services数据
    //    public static void getUserMsg(HashMap<String, String> map, final GetUserMsgCallBack callback) {
    //
    //        VitalSignDetailApiService.getEventMsg(map, "getPatWristInfo", new VitalSignDetailApiService.ServiceCallBack() {
    //            @Override
    //            public void onResult(String jsonStr) {
    //                Gson gson = new Gson();
    //
    //                if (jsonStr.isEmpty()) {
    //                    callback.onFail("-1", "网络错误，请求数据为空");
    //                } else {
    //                    try {
    //                        ScanGetUserMsgBean scanGetUserMsgBean = gson.fromJson(jsonStr, ScanGetUserMsgBean.class);
    //                        if (ObjectUtils.isEmpty(scanGetUserMsgBean)) {
    //                            callback.onFail("-3", "网络错误，数据解析为空");
    //                        } else {
    //                            if ("0".equals(scanGetUserMsgBean.getStatus())) {
    //                                if (callback != null) {
    //                                    callback.onSuccess(scanGetUserMsgBean);
    //                                }
    //                            } else {
    //                                if (callback != null) {
    //                                    callback.onFail(scanGetUserMsgBean.getMsgcode(), scanGetUserMsgBean.getMsg());
    //                                }
    //                            }
    //                        }
    //                    } catch (Exception e) {
    //                        callback.onFail("-2", "网络错误，数据解析失败");
    //                    }
    //                }
    //            }
    //        });
    //    }

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetEventsResultMsgCallBack extends CommonCallBack {
        void onSuccess(VitalSignDetailBean vitalSignDetailBean);
    }

    //    //病人事件查询接口
    //    public interface GetEventsSelectCallBack extends CommonCallBack {
    //        void onSuccess(PatEventsBean patEventsBean);
    //    }
    //
    //    //事件types查询接口
    //    public interface GetEventsTypesCallBack extends CommonCallBack {
    //        void onSuccess(EventItemBean eventItemBean);
    //    }
    //
    //    //用户信息
    //    public interface GetUserMsgCallBack extends CommonCallBack {
    //        void onSuccess(ScanGetUserMsgBean scanGetUserMsgBean);
    //    }

}
