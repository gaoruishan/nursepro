package com.dhcc.nursepro.workarea.bedmap.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.bedmap.bean.ScanResultBean;
import com.google.gson.Gson;

public class BedMapApiManager {

    public static void getBedMap(final GetBedMapCallback callback) {
        BedMapApiService.getBedMap(new BedMapApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BedMapBean bedMapBean = gson.fromJson(jsonStr, BedMapBean.class);
                        if (ObjectUtils.isEmpty(bedMapBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bedMapBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bedMapBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bedMapBean.getMsgcode(), bedMapBean.getMsg());
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

    public static void getScanInfo(String barCode,final GetScanInfoCallback callback) {
        BedMapApiService.getScanInfo(barCode, new BedMapApiService.ServiceCallBack() {
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

    public interface GetBedMapCallback extends CommonCallBack {
        void onSuccess(BedMapBean bedMapBean);
    }

    public interface GetScanInfoCallback extends CommonCallBack {
        void onSuccess(ScanResultBean scanResultBean);
    }

}
