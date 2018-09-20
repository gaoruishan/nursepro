package com.dhcc.nursepro.workarea.milkloopsystem.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckPatsListBean;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckResultListBean;
import com.dhcc.nursepro.workarea.labresult.api.LabApiService;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkReceiveBagBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkReceiveBagInfoBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class MilkLoopApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }

    public interface MilkReceiveBagInfoCallback extends CommonCallBack{
        void onSuccess(MilkReceiveBagInfoBean milkReceiveBagInfoBean);
    }

    //获取奶袋信息
    public static void getMilkReceiveBagInfo(HashMap<String,String> map,String method,final MilkReceiveBagInfoCallback milkReceiveBagInfoCallback) {

        MilkLoopService.getMilkLoopMsg(map,method,new MilkLoopService.ServiceCallBack(){
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkReceiveBagInfoCallback.onFail("-1", "网络请求失败");
                } else {

                    MilkReceiveBagInfoBean milkReceiveBagInfoBean = gson.fromJson(jsonStr, MilkReceiveBagInfoBean.class);
                    if (ObjectUtils.isEmpty(milkReceiveBagInfoBean)) {
                        milkReceiveBagInfoCallback.onFail("-1", "网络请求失败");
                    } else {
                        if (milkReceiveBagInfoBean.getStatus().equals("0")) {
                            if (milkReceiveBagInfoCallback != null) {
                                milkReceiveBagInfoCallback.onSuccess(milkReceiveBagInfoBean);
                            }
                        } else {
                            if (milkReceiveBagInfoCallback != null) {
                                milkReceiveBagInfoCallback.onFail(milkReceiveBagInfoBean.getMsgcode(), milkReceiveBagInfoBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }



    public interface MilkReceiveBagCallback extends CommonCallBack{
        void onSuccess(MilkReceiveBagBean milkReceiveBagBean);
    }

    //获取奶袋信息
    public static void getMilkReceiveBagResult(HashMap<String,String> map,String method,final MilkReceiveBagCallback milkReceiveBagCallback) {

        MilkLoopService.getMilkLoopMsg(map,method,new MilkLoopService.ServiceCallBack(){
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkReceiveBagCallback.onFail("-1", "网络请求失败");
                } else {

                    MilkReceiveBagBean milkReceiveBagBean = gson.fromJson(jsonStr, MilkReceiveBagBean.class);
                    if (ObjectUtils.isEmpty(milkReceiveBagBean)) {
                        milkReceiveBagCallback.onFail("-1", "网络请求失败");
                    } else {
                        if (milkReceiveBagBean.getStatus().equals("0")) {
                            if (milkReceiveBagCallback != null) {
                                milkReceiveBagCallback.onSuccess(milkReceiveBagBean);
                            }
                        } else {
                            if (milkReceiveBagCallback != null) {
                                milkReceiveBagCallback.onFail(milkReceiveBagBean.getMsgcode(), milkReceiveBagBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

}
