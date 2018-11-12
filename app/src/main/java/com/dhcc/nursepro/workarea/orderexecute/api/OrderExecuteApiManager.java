package com.dhcc.nursepro.workarea.orderexecute.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;
import com.google.gson.Gson;

import java.util.HashMap;

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
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OrderExecuteBean orderExecuteBean = gson.fromJson(jsonStr, OrderExecuteBean.class);
                        if (ObjectUtils.isEmpty(orderExecuteBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(orderExecuteBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(orderExecuteBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(orderExecuteBean.getMsgcode(), orderExecuteBean.getMsg());
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

    public static void execOrSeeOrder(String oeoreId, String execStatusCode, final ExecOrSeeOrderCallback callback) {
        OrderExecuteApiService.execOrSeeOrder(oeoreId, execStatusCode, new OrderExecuteApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OrderExecResultBean orderExecResultBean = gson.fromJson(jsonStr, OrderExecResultBean.class);
                        if (ObjectUtils.isEmpty(orderExecResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(orderExecResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(orderExecResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(orderExecResultBean.getMsgcode(), orderExecResultBean.getMsg());
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

    public static void getScanMsg(HashMap<String, String> map, final GetScanCallBack callback) {

        OrderExecuteApiService.getOrdersMsg(map, "getScanInfo", new OrderExecuteApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ScanResultBean scanResultBean = gson.fromJson(jsonStr, ScanResultBean.class);
                        if (ObjectUtils.isEmpty(scanResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(scanResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(scanResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(scanResultBean.getMsgcode(), scanResultBean.getMsg());
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

    //扫描腕带,医嘱码
    public interface GetScanCallBack extends CommonCallBack {
        void onSuccess(ScanResultBean scanResultBean);
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
