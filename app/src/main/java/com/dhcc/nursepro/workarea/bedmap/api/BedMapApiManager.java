package com.dhcc.nursepro.workarea.bedmap.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.bedmap.bean.DayPayListBean;
import com.dhcc.nursepro.workarea.bedmap.bean.ScanResultBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

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
                                    Map bedMapMap = gson.fromJson(jsonStr, Map.class);
                                    callback.onSuccess(bedMapBean, bedMapMap);
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

    public static void getScanInfo(String barCode, final GetScanInfoCallback callback) {
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

    public static void getDayPayList(HashMap<String, String> map, String method,final GetDayPayListCallback callback){
        BedMapApiService.getDayPayList(map, method, new BedMapApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DayPayListBean dayPayListBean = gson.fromJson(jsonStr, DayPayListBean.class);

                        if (ObjectUtils.isEmpty(dayPayListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(dayPayListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(dayPayListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(dayPayListBean.getMsgcode(), dayPayListBean.getMsg());
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
        void onSuccess(BedMapBean bedMapBean, Map bedMapMap);
    }

    public interface GetScanInfoCallback extends CommonCallBack {
        void onSuccess(ScanResultBean scanResultBean);
    }
    public interface GetDayPayListCallback extends CommonCallBack {
        void onSuccess(DayPayListBean dayPayListBean);
    }
}
