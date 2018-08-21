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

    public interface CommonCallBack{
        void onFail(String code,String msg);
    }

    //增加，删除，改动 成功返回接口
    public interface GetEventsResultMsgCallBack extends CommonCallBack{
        void onSuccess(String msgs);
    }

    //增加，删除，改动 请求services数据
    public static void GetEventsResultMsg(HashMap<String,String> map,String methodName,final GetEventsResultMsgCallBack callback) {

        PatEventsApiService.getEventMsg(map, methodName, new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    ;EventResultBean eventResultBean = gson.fromJson(jsonStr, EventResultBean.class);
                    if (ObjectUtils.isEmpty(eventResultBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (eventResultBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(eventResultBean.getMsg());
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(eventResultBean.getMsgcode(), eventResultBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }



    //病人事件查询接口
    public interface GetEventsSelectCallBack extends CommonCallBack{
        void onSuccess(PatEventsBean patEventsBean);
    }

    //病人事件services数据
    public static void GetPatEventsList(HashMap<String,String> map, final GetEventsSelectCallBack callback) {


        PatEventsApiService.getEventMsg(map, "getPatEvents", new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

//                callback.onSuccess(null);

                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    PatEventsBean patEventsBean = gson.fromJson(jsonStr, PatEventsBean.class);
                    if (ObjectUtils.isEmpty(patEventsBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (patEventsBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(patEventsBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(patEventsBean.getMsgcode(), patEventsBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }



    //事件types查询接口
    public interface GetEventsTypesCallBack extends CommonCallBack{
        void onSuccess(EventItemBean eventItemBean);
    }

    //事件types services数据
    public static void GetEventsTypes(HashMap<String,String> map, final GetEventsTypesCallBack callback) {

        PatEventsApiService.getEventMsg(map, "getEventItem", new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

//                callback.onSuccess(null);
                Gson gson = new Gson();


                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    EventItemBean eventItemBean = gson.fromJson(jsonStr, EventItemBean.class);
                    if (ObjectUtils.isEmpty(eventItemBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (eventItemBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(eventItemBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(eventItemBean.getMsgcode(), eventItemBean.getMsg());
                            }
                        }
                    }
                }


            }
        });
    }


    //用户信息
    public interface GetUserMsgCallBack extends CommonCallBack{
        void onSuccess(ScanGetUserMsgBean scanGetUserMsgBean);
    }

    //事件types services数据
    public static void GetUserMsg(HashMap<String,String> map, final GetUserMsgCallBack callback) {

        PatEventsApiService.getEventMsg(map, "getPatWristInfo", new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

                Gson gson = new Gson();


                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    ScanGetUserMsgBean scanGetUserMsgBean = gson.fromJson(jsonStr, ScanGetUserMsgBean.class);
                    if (ObjectUtils.isEmpty(scanGetUserMsgBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (scanGetUserMsgBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(scanGetUserMsgBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(scanGetUserMsgBean.getMsgcode(), scanGetUserMsgBean.getMsg());
                            }
                        }
                    }
                }


            }
        });
    }

}
