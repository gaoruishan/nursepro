package com.dhcc.nursepro.workarea.operation.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.labresult.api.LabApiService;
import com.dhcc.nursepro.workarea.labresult.bean.LabReslutDetailBean;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class OperationApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }

    public interface GetOperationCallback extends CommonCallBack{
        void onSuccess(PatsListBean patsListBean);
    }

    //获取病人列表
    public static void getOperationList(HashMap<String,String> map,String method,final GetOperationCallback callback) {

        OperationApiService.getOperationList(map,method,new OperationApiService.ServiceCallBack(){
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    PatsListBean patsListBean = gson.fromJson(jsonStr, PatsListBean.class);
                    if (ObjectUtils.isEmpty(patsListBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (patsListBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(patsListBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(patsListBean.getMsgcode(), patsListBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

    public interface GeLabListCallback extends CommonCallBack{
        void onSuccess(LabResultListBean labResultListBean);
    }

    public static void getLabListMsg(HashMap<String,String> map,String method,final GeLabListCallback callback) {

        LabApiService.getCheckLabMsg(map,method,new LabApiService.ServiceCallBack(){
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    LabResultListBean labResultListBean = gson.fromJson(jsonStr, LabResultListBean.class);
                    if (ObjectUtils.isEmpty(labResultListBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (labResultListBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(labResultListBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(labResultListBean.getMsgcode(), labResultListBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

    public interface GeLabDetailCallback extends CommonCallBack{
        void onSuccess(LabReslutDetailBean labReslutDetailBean);
    }

    public static void getLabDetailMsg(HashMap<String,String> map,String method,final GeLabDetailCallback callback) {

        LabApiService.getCheckLabMsg(map,method,new LabApiService.ServiceCallBack(){
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    LabReslutDetailBean labReslutDetailBean = gson.fromJson(jsonStr, LabReslutDetailBean.class);
                    if (ObjectUtils.isEmpty(labReslutDetailBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (labReslutDetailBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(labReslutDetailBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(labReslutDetailBean.getMsgcode(), labReslutDetailBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

}
