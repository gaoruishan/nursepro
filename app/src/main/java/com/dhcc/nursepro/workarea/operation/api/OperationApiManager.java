package com.dhcc.nursepro.workarea.operation.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.labresult.api.LabApiService;
import com.dhcc.nursepro.workarea.labresult.bean.LabReslutDetailBean;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;
import com.dhcc.nursepro.workarea.operation.bean.OperationBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class OperationApiManager {

    //获取病人列表
    public static void getOperationList(HashMap<String, String> map, String method, final GetOperationCallback callback) {

        OperationApiService.getOperationList(map, method, new OperationApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OperationBean operationBean = gson.fromJson(jsonStr, OperationBean.class);
                        if (ObjectUtils.isEmpty(operationBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(operationBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(operationBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(operationBean.getMsgcode(), operationBean.getMsg());
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

    public interface GetOperationCallback extends CommonCallBack {
        void onSuccess(OperationBean operationBean);
    }


}
