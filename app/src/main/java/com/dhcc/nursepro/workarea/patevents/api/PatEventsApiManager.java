package com.dhcc.nursepro.workarea.patevents.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.patevents.bean.EventItemBean;
import com.dhcc.nursepro.workarea.patevents.bean.EventResultBean;
import com.dhcc.nursepro.workarea.patevents.bean.PatEventsBean;
import com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class PatEventsApiManager {

    //增加，删除，改动 请求services数据
    public static void getEventsResultMsg(HashMap<String, String> map, String methodName, final GetEventsResultMsgCallBack callback) {

        PatEventsApiService.getEventMsg(map, methodName, new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        EventResultBean eventResultBean = gson.fromJson(jsonStr, EventResultBean.class);
                        if (ObjectUtils.isEmpty(eventResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(eventResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(eventResultBean.getMsg());
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(eventResultBean.getMsgcode(), eventResultBean.getMsg());
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

    //病人事件services数据
    public static void getPatEventsList(HashMap<String, String> map, final GetEventsSelectCallBack callback) {


        PatEventsApiService.getEventMsg(map, "getPatEvents", new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        PatEventsBean patEventsBean = gson.fromJson(jsonStr, PatEventsBean.class);
                        if (ObjectUtils.isEmpty(patEventsBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(patEventsBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(patEventsBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(patEventsBean.getMsgcode(), patEventsBean.getMsg());
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

    //事件types services数据
    public static void getEventsTypes(HashMap<String, String> map, final GetEventsTypesCallBack callback) {

        PatEventsApiService.getEventMsg(map, "getEventItem", new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        EventItemBean eventItemBean = gson.fromJson(jsonStr, EventItemBean.class);
                        if (ObjectUtils.isEmpty(eventItemBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(eventItemBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(eventItemBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(eventItemBean.getMsgcode(), eventItemBean.getMsg());
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

    //事件types services数据
    public static void getUserMsg(HashMap<String, String> map, final GetUserMsgCallBack callback) {

        PatEventsApiService.getEventMsg(map, "getPatWristInfo", new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ScanGetUserMsgBean scanGetUserMsgBean = gson.fromJson(jsonStr, ScanGetUserMsgBean.class);
                        if (ObjectUtils.isEmpty(scanGetUserMsgBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(scanGetUserMsgBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(scanGetUserMsgBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(scanGetUserMsgBean.getMsgcode(), scanGetUserMsgBean.getMsg());
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

    //增加，删除，改动 成功返回接口
    public interface GetEventsResultMsgCallBack extends CommonCallBack {
        void onSuccess(String msgs);
    }

    //病人事件查询接口
    public interface GetEventsSelectCallBack extends CommonCallBack {
        void onSuccess(PatEventsBean patEventsBean);
    }

    //事件types查询接口
    public interface GetEventsTypesCallBack extends CommonCallBack {
        void onSuccess(EventItemBean eventItemBean);
    }

    //用户信息
    public interface GetUserMsgCallBack extends CommonCallBack {
        void onSuccess(ScanGetUserMsgBean scanGetUserMsgBean);
    }

}
