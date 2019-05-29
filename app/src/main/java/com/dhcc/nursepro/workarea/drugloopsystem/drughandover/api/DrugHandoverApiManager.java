package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.dosingreview.bean.PreparedVerifyOrdBean;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.DrugHandOverScanOrderList;
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

    public static void BatchSave(String parr, String carryUser, String barCode, final DrugHandoverScanOrderListCallback callback) {
        DrugHandoverApiService.BatchSave(parr, carryUser, barCode, new DrugHandoverApiService.ServiceCallBack() {
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


    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface DrugHandoverScanOrderListCallback extends CommonCallBack {
        void onSuccess(DrugHandOverScanOrderList scanOrderList);
    }

    public interface BatchSaveCallback extends CommonCallBack {
        void onSuccess(PreparedVerifyOrdBean preparedVerifyOrdBean);
    }
}
