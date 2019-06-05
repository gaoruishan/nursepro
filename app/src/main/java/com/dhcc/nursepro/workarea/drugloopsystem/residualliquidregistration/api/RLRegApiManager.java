package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean.RLPatOrdBean;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean.RLRegResultBean;
import com.google.gson.Gson;

/**
 * RLRegApiManager
 *
 * @author Devlix126
 * created at 2019/5/29 17:09
 */
public class RLRegApiManager {
    public static void getResidualQtyList(String startDate, String endDate, final RLPatOrdCallback callback) {
        RLRegApiService.getResidualQtyList(startDate, endDate, new RLRegApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RLPatOrdBean rlPatOrdBean = gson.fromJson(jsonStr, RLPatOrdBean.class);
                        if (ObjectUtils.isEmpty(rlPatOrdBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(rlPatOrdBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(rlPatOrdBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(rlPatOrdBean.getMsgcode(), rlPatOrdBean.getMsg());
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

    public static void residualQtyReg(String oeoreId, String barCode, String type, String regQty, String regQtyUnit, String wayDesc, final RLRegResultCallback callback) {
        RLRegApiService.residualQtyReg(oeoreId, barCode, type, regQty, regQtyUnit, wayDesc, new RLRegApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RLRegResultBean rlRegResultBean = gson.fromJson(jsonStr, RLRegResultBean.class);
                        if (ObjectUtils.isEmpty(rlRegResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(rlRegResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(rlRegResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(rlRegResultBean.getMsgcode(), rlRegResultBean.getMsg());
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

    public interface RLPatOrdCallback extends CommonCallBack {
        void onSuccess(RLPatOrdBean rlPatOrdBean);
    }

    public interface RLRegResultCallback extends CommonCallBack {
        void onSuccess(RLRegResultBean rlRegResultBean);
    }
}
