package com.dhcc.nursepro.workarea.vitalsigndetail.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.patevents.bean.EventItemBean;
import com.dhcc.nursepro.workarea.patevents.bean.PatEventsBean;
import com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean;
import com.dhcc.nursepro.workarea.vitalsigndetail.bean.VitalSignDetailBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class VitalSignDetailApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }


    public interface GetEventsResultMsgCallBack extends CommonCallBack{
        void onSuccess(VitalSignDetailBean vitalSignDetailBean);
    }

    //查询病人生命体征
    public static void GetVitalSignDetail(HashMap<String,String> map,final GetEventsResultMsgCallBack callback) {

        VitalSignDetailApiService.getEventMsg(map, "getPatTempDetail", new VitalSignDetailApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    VitalSignDetailBean vitalSignDetailBean = gson.fromJson(jsonStr, VitalSignDetailBean.class);
                    if (ObjectUtils.isEmpty(vitalSignDetailBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (vitalSignDetailBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(vitalSignDetailBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(vitalSignDetailBean.getMsgcode(), vitalSignDetailBean.getMsg());
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


        VitalSignDetailApiService.getEventMsg(map, "getPatEvents", new VitalSignDetailApiService.ServiceCallBack() {
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

        VitalSignDetailApiService.getEventMsg(map, "getEventItem", new VitalSignDetailApiService.ServiceCallBack() {
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

        VitalSignDetailApiService.getEventMsg(map, "getPatWristInfo", new VitalSignDetailApiService.ServiceCallBack() {
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
