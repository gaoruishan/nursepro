package com.dhcc.nursepro.workarea.plyout.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.plyout.bean.DelOrderBean;
import com.dhcc.nursepro.workarea.plyout.bean.PlyOutDetailBean;
import com.dhcc.nursepro.workarea.plyout.bean.PlyOutListAllBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class PlyOutApiManager {

    public static void getLabOutListMsg(HashMap<String, String> map, String method, final getLabOutCallBack callback) {

        PlyOutApiService.getLabOutMsg(map, method, new PlyOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        PlyOutListAllBean labOutListAllBean = gson.fromJson(jsonStr, PlyOutListAllBean.class);
                        if (ObjectUtils.isEmpty(labOutListAllBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(labOutListAllBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(labOutListAllBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(labOutListAllBean.getMsgcode(), labOutListAllBean.getMsg());
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

    public static void getLabOutDetailMsg(HashMap<String, String> map, String method, final getLabOutDetailCallBack callback) {

        PlyOutApiService.getLabOutMsg(map, method, new PlyOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        PlyOutDetailBean labOutDetailBean = gson.fromJson(jsonStr, PlyOutDetailBean.class);
                        if (ObjectUtils.isEmpty(labOutDetailBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(labOutDetailBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(labOutDetailBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(labOutDetailBean.getMsgcode(), labOutDetailBean.getMsg());
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

    public static void delOrdMsg(HashMap<String, String> map, String method, final delOrdCallBack callback) {

        PlyOutApiService.getLabOutMsg(map, method, new PlyOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DelOrderBean delOrderBean = gson.fromJson(jsonStr, DelOrderBean.class);
                        if (ObjectUtils.isEmpty(delOrderBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(delOrderBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(delOrderBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(delOrderBean.getMsgcode(), delOrderBean.getMsg());
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

    //新建，查询检验交接单
    public interface getLabOutCallBack extends CommonCallBack {
        void onSuccess(PlyOutListAllBean labOutListAllBean);
    }

    //交接单详情，新增删除项目
    public interface getLabOutDetailCallBack extends CommonCallBack {
        void onSuccess(PlyOutDetailBean labOutDetailBean);
    }

    //发送，撤销发送
    public interface delOrdCallBack extends CommonCallBack {
        void onSuccess(DelOrderBean delOrderBean);
    }
}
