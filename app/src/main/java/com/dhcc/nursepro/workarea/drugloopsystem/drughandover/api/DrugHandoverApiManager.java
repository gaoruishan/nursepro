package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.BatchSaveResult;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.DrugHandOverScanOrderList;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.GetOrdRecListBean;
import com.google.gson.Gson;

/**
 * DrugHandoverApiManager
 *
 * @author Devlix126
 * created at 2019/5/28 11:29
 */
public class DrugHandoverApiManager {
    public static void getOrdListByBarCode(String barCode, final DrugHandoverScanOrderListCallback callback) {
        DrugHandoverApiService.getOrdListByBarCode(barCode, new DrugHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DrugHandOverScanOrderList scanOrderList = gson.fromJson(jsonStr, DrugHandOverScanOrderList.class);
                        if (ObjectUtils.isEmpty(scanOrderList)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(scanOrderList.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(scanOrderList);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(scanOrderList.getMsgcode(), scanOrderList.getMsg());
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

    public static void batchSave(String parr, String carryUser, String barCode, String type, final BatchSaveCallback callback) {
        DrugHandoverApiService.batchSave(parr, carryUser, barCode, type, new DrugHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BatchSaveResult batchSaveResult = gson.fromJson(jsonStr, BatchSaveResult.class);
                        if (ObjectUtils.isEmpty(batchSaveResult)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(batchSaveResult.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(batchSaveResult);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(batchSaveResult.getMsgcode(), batchSaveResult.getMsg());
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

    public static void getOrdRecieveList(String startdate, String enddate, final GetOrdReceiveListCallback callback) {
        DrugHandoverApiService.getOrdRecieveList(startdate, enddate, new DrugHandoverApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetOrdRecListBean getOrdRecListBean = gson.fromJson(jsonStr, GetOrdRecListBean.class);
                        if (ObjectUtils.isEmpty(getOrdRecListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getOrdRecListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getOrdRecListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getOrdRecListBean.getMsgcode(), getOrdRecListBean.getMsg());
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

    public interface DrugHandoverScanOrderListCallback extends CommonCallBack {
        void onSuccess(DrugHandOverScanOrderList scanOrderList);
    }

    public interface BatchSaveCallback extends CommonCallBack {
        void onSuccess(BatchSaveResult batchSaveResult);
    }

    public interface GetOrdReceiveListCallback extends CommonCallBack {
        void onSuccess(GetOrdRecListBean getOrdRecListBean);
    }

}
