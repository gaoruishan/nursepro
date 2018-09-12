package com.dhcc.nursepro.workarea.docorderlist.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrderListBean;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrdersPatsListBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.docorderlist.api
 * <p>
 * author Q
 * Date: 2018/9/11
 * Time:10:03
 */
public class DocOrderListApiManager {

    public interface CommonCallBack{
        void onFail(String code,String msg);
    }


    public interface getDocOrderListCallBack extends CommonCallBack{
        void onSuccess(DocOrderListBean docOrderListBean);
    }

    public static void getDocOrderList(HashMap<String,String> map, String method, final getDocOrderListCallBack getAllotBedCallBack){

        DocOrderListApiService.getDocOrderListMsg(map, method, new DocOrderListApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getAllotBedCallBack.onFail("-1", "网络请求失败");
                } else {

                    DocOrderListBean docOrderListBean = gson.fromJson(jsonStr, DocOrderListBean.class);
                    if (ObjectUtils.isEmpty(docOrderListBean)) {
                        getAllotBedCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (docOrderListBean.getStatus().equals("0")) {
                            if (getAllotBedCallBack != null) {
                                getAllotBedCallBack.onSuccess(docOrderListBean);
                            }
                        } else {
                            if (docOrderListBean != null) {
                                getAllotBedCallBack.onFail(docOrderListBean.getMsgcode(), docOrderListBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }



    public interface getPatsListCallback extends CommonCallBack{
        void onSuccess(DocOrdersPatsListBean docOrdersPatsListBean);
    }

    //获取病人列表
    public static void getPatsList(HashMap<String,String> map,String method,final getPatsListCallback callback) {

        DocOrderListApiService.getDocOrderListMsg(map,method,new DocOrderListApiService.ServiceCallBack(){
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    DocOrdersPatsListBean docOrdersPatsListBean = gson.fromJson(jsonStr, DocOrdersPatsListBean.class);
                    if (ObjectUtils.isEmpty(docOrdersPatsListBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (docOrdersPatsListBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(docOrdersPatsListBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(docOrdersPatsListBean.getMsgcode(), docOrdersPatsListBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }
}
