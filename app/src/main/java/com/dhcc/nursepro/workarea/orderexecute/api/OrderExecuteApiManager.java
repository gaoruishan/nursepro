package com.dhcc.nursepro.workarea.orderexecute.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.google.gson.Gson;

/**
 * OrderSearchApiManager
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderExecuteApiManager {
    public static void getOrder(String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime, final GetOrderCallback callback) {
        OrderExecuteApiService.getOrder(regNo, sheetCode, startDate, startTime, endDate, endTime, new OrderExecuteApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    OrderExecuteBean orderExecuteBean = gson.fromJson(jsonStr, OrderExecuteBean.class);
                    if (ObjectUtils.isEmpty(orderExecuteBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (orderExecuteBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(orderExecuteBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(orderExecuteBean.getMsgcode(), orderExecuteBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }

    public static void ExecOrSeeOrder(String oeoreId, String execStatusCode, final ExecOrSeeOrderCallback callback) {
        OrderExecuteApiService.execOrSeeOrder(oeoreId, execStatusCode, new OrderExecuteApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    OrderExecResultBean orderExecResultBean = gson.fromJson(jsonStr, OrderExecResultBean.class);
                    if (ObjectUtils.isEmpty(orderExecResultBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (orderExecResultBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(orderExecResultBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(orderExecResultBean.getMsgcode(), orderExecResultBean.getMsg());
                            }
                        }
                    }
                }
            }
        });
    }


    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetOrderCallback extends CommonCallBack {
        void onSuccess(OrderExecuteBean orderExecuteBean);
    }

    public interface ExecOrSeeOrderCallback extends CommonCallBack {
        void onSuccess(OrderExecResultBean orderExecResultBean);
    }
}
