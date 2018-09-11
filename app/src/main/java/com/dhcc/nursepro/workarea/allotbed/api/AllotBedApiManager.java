package com.dhcc.nursepro.workarea.allotbed.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.allotbed.bean.AllotBedInfoBean;
import com.dhcc.nursepro.workarea.allotbed.bean.GetScanPatsBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.allotbed.api
 * <p>
 * author Q
 * Date: 2018/9/6
 * Time:10:17
 */
public class AllotBedApiManager {


    public interface CommonCallBack{
        void onFail(String code,String msg);
    }


    //查询空床 主治医生 主管护士
    public interface getAllotBedCallBack extends CommonCallBack{
        void onSuccess(AllotBedInfoBean allotBedInfoBean);
    }

    public static void getAllotBed(HashMap<String,String> map, String method, final getAllotBedCallBack getAllotBedCallBack){

        AllotBedApiService.getAllotBedMsg(map, method, new AllotBedApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getAllotBedCallBack.onFail("-1", "网络请求失败");
                } else {

                    AllotBedInfoBean allotBedInfoBean = gson.fromJson(jsonStr, AllotBedInfoBean.class);
                    if (ObjectUtils.isEmpty(allotBedInfoBean)) {
                        getAllotBedCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (allotBedInfoBean.getStatus().equals("0")) {
                            if (getAllotBedCallBack != null) {
                                getAllotBedCallBack.onSuccess(allotBedInfoBean);
                            }
                        } else {
                            if (allotBedInfoBean != null) {
                                getAllotBedCallBack.onFail(allotBedInfoBean.getMsgcode(), allotBedInfoBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

    //分床并返回空床 主治医生 主管护士
    public interface getAllotBedResultCallBack extends CommonCallBack{
        void onSuccess(AllotBedInfoBean allotBedResultBean);
    }

    public static void getAllotBedResult(HashMap<String,String> map, String method, final getAllotBedResultCallBack getAllotBedResultCallBack){

        AllotBedApiService.getAllotBedMsg(map, method, new AllotBedApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getAllotBedResultCallBack.onFail("-1", "网络请求失败");
                } else {

                    AllotBedInfoBean allotBedInfoBean = gson.fromJson(jsonStr, AllotBedInfoBean.class);
                    if (ObjectUtils.isEmpty(allotBedInfoBean)) {
                        getAllotBedResultCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (allotBedInfoBean.getStatus().equals("0")) {
                            if (getAllotBedResultCallBack != null) {
                                getAllotBedResultCallBack.onSuccess(allotBedInfoBean);
                            }
                        } else {
                            if (allotBedInfoBean != null) {
                                getAllotBedResultCallBack.onFail(allotBedInfoBean.getMsgcode(), allotBedInfoBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }





    //用户信息
    public interface GetUserMsgCallBack extends CommonCallBack{
        void onSuccess(GetScanPatsBean getScanPatsBean);
    }

    public static void GetUserMsg(HashMap<String,String> map,String method, final GetUserMsgCallBack callback) {

        AllotBedApiService.getAllotBedMsg(map, method, new AllotBedApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

                Gson gson = new Gson();


                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    GetScanPatsBean getScanPatsBean = gson.fromJson(jsonStr, GetScanPatsBean.class);
                    if (ObjectUtils.isEmpty(getScanPatsBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (getScanPatsBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(getScanPatsBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(getScanPatsBean.getMsgcode(), getScanPatsBean.getMsg());
                            }
                        }
                    }
                }


            }
        });
    }

}
