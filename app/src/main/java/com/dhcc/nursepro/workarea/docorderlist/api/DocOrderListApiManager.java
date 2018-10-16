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

    public static void getDocOrderList(HashMap<String, String> map, String method, final getDocOrderListCallBack callback) {

        DocOrderListApiService.getDocOrderListMsg(map, method, new DocOrderListApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DocOrderListBean docOrderListBean = gson.fromJson(jsonStr, DocOrderListBean.class);
                        if (ObjectUtils.isEmpty(docOrderListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(docOrderListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(docOrderListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(docOrderListBean.getMsgcode(), docOrderListBean.getMsg());
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

    //获取病人列表
    public static void getPatsList(HashMap<String, String> map, String method, final getPatsListCallback callback) {

        DocOrderListApiService.getDocOrderListMsg(map, method, new DocOrderListApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DocOrdersPatsListBean docOrdersPatsListBean = gson.fromJson(jsonStr, DocOrdersPatsListBean.class);
                        if (ObjectUtils.isEmpty(docOrdersPatsListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(docOrdersPatsListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(docOrdersPatsListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(docOrdersPatsListBean.getMsgcode(), docOrdersPatsListBean.getMsg());
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

    public interface getDocOrderListCallBack extends CommonCallBack {
        void onSuccess(DocOrderListBean docOrderListBean);
    }

    public interface getPatsListCallback extends CommonCallBack {
        void onSuccess(DocOrdersPatsListBean docOrdersPatsListBean);
    }
}
