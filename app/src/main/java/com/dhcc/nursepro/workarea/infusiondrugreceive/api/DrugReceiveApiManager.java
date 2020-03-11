package com.dhcc.nursepro.workarea.infusiondrugreceive.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.infusiondrugreceive.bean.BatchIFSaveResultBean;
import com.dhcc.nursepro.workarea.infusiondrugreceive.bean.IfOrdListBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.infusiondrugreceive.api
 * <p>
 * author Q
 * Date: 2020/3/11
 * Time:11:06
 */
public class DrugReceiveApiManager {

    public static void getIfOrDList(HashMap<String, String> map, String method, final ifOrdLIstCallback callback) {

        DrugReceiveApiService.getIfOrdList(map, method, new DrugReceiveApiService.serveCallback() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        IfOrdListBean ifOrdListBean = gson.fromJson(jsonStr, IfOrdListBean.class);
                        if (ObjectUtils.isEmpty(ifOrdListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(ifOrdListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(ifOrdListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(ifOrdListBean.getMsgcode(), ifOrdListBean.getMsg());
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

    public static void ordSaveMsg(HashMap<String, String> map, String method, final ordSaveResultCallback callback) {

        DrugReceiveApiService.getSaveResult(map, method, new DrugReceiveApiService.serveCallback() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BatchIFSaveResultBean batchIFSaveResultBean = gson.fromJson(jsonStr, BatchIFSaveResultBean.class);
                        if (ObjectUtils.isEmpty(batchIFSaveResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(batchIFSaveResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(batchIFSaveResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(batchIFSaveResultBean.getMsgcode(), batchIFSaveResultBean.getMsg());
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


    public interface ifOrdLIstCallback extends CommonCallBack{
        void onSuccess(IfOrdListBean ifOrdListBean);
    }

    public interface ordSaveResultCallback extends CommonCallBack{
        void onSuccess(BatchIFSaveResultBean batchIFSaveResultBean);
    }
    public interface CommonCallBack {
        void onFail(String code, String msg);
    }
}
