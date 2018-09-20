package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.bean.GetInfusionBloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.bean.GetPatWristInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.bean.StartTransfusionBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend.bean.EndTransfusionBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.api
 * <p>
 * author Q
 * Date: 2018/9/18
 * Time:11:29
 */
public class BloodTransfusionApiManager {

    public interface CommonCallBack{
        void onFail(String code,String msg);
    }


    public interface getScanPatsInfoCallBack extends CommonCallBack{
        void onSuccess(GetPatWristInfoBean getPatWristInfoBean);
    }

    public static void getScanPatsInfo(HashMap<String,String> map, String method, final getScanPatsInfoCallBack getScanPatsInfoCallBack){

        BloodTransfusionApiService.getTransfusionMsg(map, method, new BloodTransfusionApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getScanPatsInfoCallBack.onFail("-1", "网络请求失败");
                } else {

                    GetPatWristInfoBean getPatWristInfoBean = gson.fromJson(jsonStr, GetPatWristInfoBean.class);
                    if (ObjectUtils.isEmpty(getPatWristInfoBean)) {
                        getScanPatsInfoCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (getPatWristInfoBean.getStatus().equals("0")) {
                            if (getScanPatsInfoCallBack != null) {
                                getScanPatsInfoCallBack.onSuccess(getPatWristInfoBean);
                            }
                        } else {
                            if (getPatWristInfoBean != null) {
                                getScanPatsInfoCallBack.onFail(getPatWristInfoBean.getMsgcode(), getPatWristInfoBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }


    public interface getScanInfusionInfoCallBack extends CommonCallBack{
        void onSuccess(GetInfusionBloodInfoBean getInfusionBloodInfoBean);
    }

    public static void getScanInfusionInfo(HashMap<String,String> map, String method, final getScanInfusionInfoCallBack getScanInfusionInfoCallBack){

        BloodTransfusionApiService.getTransfusionMsg(map, method, new BloodTransfusionApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getScanInfusionInfoCallBack.onFail("-1", "网络请求失败");
                } else {

                    GetInfusionBloodInfoBean getInfusionBloodInfoBean = gson.fromJson(jsonStr, GetInfusionBloodInfoBean.class);
                    if (ObjectUtils.isEmpty(getInfusionBloodInfoBean)) {
                        getScanInfusionInfoCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (getInfusionBloodInfoBean.getStatus().equals("0")) {
                            if (getScanInfusionInfoCallBack != null) {
                                getScanInfusionInfoCallBack.onSuccess(getInfusionBloodInfoBean);
                            }
                        } else {
                            if (getInfusionBloodInfoBean != null) {
                                getScanInfusionInfoCallBack.onFail(getInfusionBloodInfoBean.getMsgcode(), getInfusionBloodInfoBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }


    //start
    public interface getScanStartCallBack extends CommonCallBack{
        void onSuccess(StartTransfusionBean startTransfusionBean);
    }

    public static void getScanStartInfo(HashMap<String,String> map, String method, final getScanStartCallBack getScanStartCallBack){

        BloodTransfusionApiService.getTransfusionMsg(map, method, new BloodTransfusionApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getScanStartCallBack.onFail("-1", "网络请求失败");
                } else {

                    StartTransfusionBean startTransfusionBean = gson.fromJson(jsonStr, StartTransfusionBean.class);
                    if (ObjectUtils.isEmpty(startTransfusionBean)) {
                        getScanStartCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (startTransfusionBean.getStatus().equals("0")) {
                            if (getScanStartCallBack != null) {
                                getScanStartCallBack.onSuccess(startTransfusionBean);
                            }
                        } else {
                            if (startTransfusionBean != null) {
                                getScanStartCallBack.onFail(startTransfusionBean.getMsgcode(), startTransfusionBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

    //start
    public interface getScanEndCallBack extends CommonCallBack{
        void onSuccess(EndTransfusionBean endTransfusionBean);
    }

    public static void getScanEndInfo(HashMap<String,String> map, String method, final getScanEndCallBack getScanEndCallBack){

        BloodTransfusionApiService.getTransfusionMsg(map, method, new BloodTransfusionApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getScanEndCallBack.onFail("-1", "网络请求失败");
                } else {

                    EndTransfusionBean endTransfusionBean = gson.fromJson(jsonStr, EndTransfusionBean.class);
                    if (ObjectUtils.isEmpty(endTransfusionBean)) {
                        getScanEndCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (endTransfusionBean.getStatus().equals("0")) {
                            if (getScanEndCallBack != null) {
                                getScanEndCallBack.onSuccess(endTransfusionBean);
                            }
                        } else {
                            if (endTransfusionBean != null) {
                                getScanEndCallBack.onFail(endTransfusionBean.getMsgcode(), endTransfusionBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }
}
