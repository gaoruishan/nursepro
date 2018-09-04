package com.dhcc.nursepro.workarea.labout.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.labout.bean.DelOrderBean;
import com.dhcc.nursepro.workarea.labout.bean.LabOutDetailBean;
import com.dhcc.nursepro.workarea.labout.bean.LabOutListAllBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class LabOutApiManager {

    public interface CommonCallBack{
        void onFail(String code,String msg);
    }


    //新建，查询检验交接单
    public interface getLabOutCallBack extends CommonCallBack{
        void onSuccess(LabOutListAllBean labOutListAllBean);
    }

    public static void getLabOutListMsg(HashMap<String,String> map, String method,final getLabOutCallBack getLabOutCallBack){

        LabOutApiService.getLabOutMsg(map, method, new LabOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getLabOutCallBack.onFail("-1", "网络请求失败");
                } else {

                    LabOutListAllBean labOutListAllBean = gson.fromJson(jsonStr, LabOutListAllBean.class);
                    if (ObjectUtils.isEmpty(labOutListAllBean)) {
                        getLabOutCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (labOutListAllBean.getStatus().equals("0")) {
                            if (getLabOutCallBack != null) {
                                getLabOutCallBack.onSuccess(labOutListAllBean);
                            }
                        } else {
                            if (getLabOutCallBack != null) {
                                getLabOutCallBack.onFail(labOutListAllBean.getMsgcode(), labOutListAllBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

//交接单详情，新增删除项目
    public interface getLabOutDetailCallBack extends CommonCallBack{
        void onSuccess(LabOutDetailBean labOutDetailBean);
    }

    public static void getLabOutDetailMsg(HashMap<String,String> map, String method,final getLabOutDetailCallBack getLabOutDetailCallBack){

        LabOutApiService.getLabOutMsg(map, method, new LabOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getLabOutDetailCallBack.onFail("-1", "网络请求失败");
                } else {

                    LabOutDetailBean labOutDetailBean = gson.fromJson(jsonStr, LabOutDetailBean.class);
                    if (ObjectUtils.isEmpty(labOutDetailBean)) {
                        getLabOutDetailCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (labOutDetailBean.getStatus().equals("0")) {
                            if (getLabOutDetailCallBack != null) {
                                getLabOutDetailCallBack.onSuccess(labOutDetailBean);
                            }
                        } else {
                            if (getLabOutDetailCallBack != null) {
                                getLabOutDetailCallBack.onFail(labOutDetailBean.getMsgcode(), labOutDetailBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

//发送，撤销发送
    public interface delOrdCallBack extends CommonCallBack{
        void onSuccess(DelOrderBean delOrderBean);
    }

    public static void delOrdMsg(HashMap<String,String> map, String method,final delOrdCallBack delOrdCallBack){

        LabOutApiService.getLabOutMsg(map, method, new LabOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    delOrdCallBack.onFail("-1", "网络请求失败");
                } else {

                    DelOrderBean delOrderBean = gson.fromJson(jsonStr, DelOrderBean.class);
                    if (ObjectUtils.isEmpty(delOrdCallBack)) {
                        delOrdCallBack.onFail("-1", "网络请求失败");
                    } else {
                        if (delOrderBean.getStatus().equals("0")) {
                            if (delOrdCallBack != null) {
                                delOrdCallBack.onSuccess(delOrderBean);
                            }
                        } else {
                            if (delOrdCallBack != null) {
                                delOrdCallBack.onFail(delOrderBean.getMsgcode(), delOrderBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }
}
