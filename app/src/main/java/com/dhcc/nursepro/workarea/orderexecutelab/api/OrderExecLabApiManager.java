package com.dhcc.nursepro.workarea.orderexecutelab.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.OrderSearchBean;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.ScanResultBean;
import com.google.gson.Gson;

public class OrderExecLabApiManager {
    public static void getOrder(String bedStr, String regNo, String sheetCode, String pageNo, String startDate, String startTime, String endDate, String endTime, String screenParts, final GetOrderCallback callback) {
        OrderExecLabApiService.getOrder(bedStr, regNo, sheetCode, pageNo, startDate, startTime, endDate, endTime, screenParts, new OrderExecLabApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OrderSearchBean orderSearchBean = gson.fromJson(jsonStr, OrderSearchBean.class);
                        if (ObjectUtils.isEmpty(orderSearchBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(orderSearchBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(orderSearchBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(orderSearchBean.getMsgcode(), orderSearchBean.getMsg());
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

    public static void execOrSeeOrder(String speed, String scanFlag, String batch, String auditUserCode, String auditUserPass, String oeoreId, String execStatusCode, String reason, String barCode, final ExecOrSeeOrderCallback callback) {
        OrderExecLabApiService.execOrSeeOrder(speed, scanFlag, batch, auditUserCode, auditUserPass, oeoreId, execStatusCode, reason, barCode, new OrderExecLabApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
//                SPUtils spUtils = SPUtils.getInstance();
//                String strLocReruest = spUtils.getString(SharedPreference.LOCALREQUEST,"");
//                String strOrdId = "|"+oeoreId+"!";
//                if (strLocReruest.contains(strOrdId)){
//                    int startIndex = strLocReruest.indexOf(strOrdId);
//                    int idLength = strOrdId.length();
//                    String strIdTime = strLocReruest.substring(startIndex,startIndex+idLength+16);
//                    strLocReruest = strLocReruest.replace(strIdTime,"");
//                }

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
//                    strLocReruest = strLocReruest+"|"+oeoreId+"!"+ DateUtils.getDateTimeFromSystem();
//                    spUtils.put(SharedPreference.LOCALREQUEST,strLocReruest);
                } else {
                    try {
                        OrderExecResultBean orderExecResultBean = gson.fromJson(jsonStr, OrderExecResultBean.class);
                        if (ObjectUtils.isEmpty(orderExecResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
//                            strLocReruest = strLocReruest+"|"+oeoreId+"!"+ DateUtils.getDateTimeFromSystem();
//                            spUtils.put(SharedPreference.LOCALREQUEST,strLocReruest);
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
//                        strLocReruest = strLocReruest+"|"+oeoreId+"!"+ DateUtils.getDateTimeFromSystem();
//                        spUtils.put(SharedPreference.LOCALREQUEST,strLocReruest);
                    }
                }
            }
        });
    }

    public static void getScanMsg(String episodeId, String scanInfo, final GetScanCallBack callback) {

        OrderExecLabApiService.getOrdersMsg(episodeId, scanInfo, new OrderExecLabApiService.ServiceCallBack() {
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

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    //扫描腕带,医嘱码
    public interface GetScanCallBack extends CommonCallBack {
        void onSuccess(ScanResultBean scanResultBean);

    }

    public interface ExecOrSeeOrderCallback extends CommonCallBack {
        void onSuccess(OrderExecResultBean orderExecResultBean);

    }

    public interface GetOrderCallback extends CommonCallBack {
        void onSuccess(OrderSearchBean orderSearchBean);
    }
}
