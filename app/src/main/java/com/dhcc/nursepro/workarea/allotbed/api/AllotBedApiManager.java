package com.dhcc.nursepro.workarea.allotbed.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.allotbed.bean.AllotBedInfoBean;
import com.dhcc.nursepro.workarea.allotbed.bean.FirstBeHosTemDataBean;
import com.dhcc.nursepro.workarea.allotbed.bean.GetScanPatsBean;
import com.dhcc.nursepro.workarea.allotbed.bean.IsFirstToBedBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.allotbed.api
 * <p>
 * author Q
 * Date: 2018/9/6
 * Time:10:17
 */
public class AllotBedApiManager {


    public static void getAllotBed(HashMap<String, String> map, String method, final getAllotBedCallBack callback) {

        AllotBedApiService.getAllotBedMsg(map, method, new AllotBedApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        AllotBedInfoBean allotBedInfoBean = gson.fromJson(jsonStr, AllotBedInfoBean.class);
                        if (ObjectUtils.isEmpty(allotBedInfoBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(allotBedInfoBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(allotBedInfoBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(allotBedInfoBean.getMsgcode(), allotBedInfoBean.getMsg());
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

    public static void getAllotBedResult(HashMap<String, String> map, String method, final getAllotBedResultCallBack callback) {

        AllotBedApiService.getAllotBedMsg(map, method, new AllotBedApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        AllotBedInfoBean allotBedInfoBean = gson.fromJson(jsonStr, AllotBedInfoBean.class);
                        if (ObjectUtils.isEmpty(allotBedInfoBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(allotBedInfoBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(allotBedInfoBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(allotBedInfoBean.getMsgcode(), allotBedInfoBean.getMsg());
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

    public static void getUserMsg(HashMap<String, String> map, String method, final GetUserMsgCallBack callback) {

        AllotBedApiService.getAllotBedMsg(map, method, new AllotBedApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetScanPatsBean getScanPatsBean = gson.fromJson(jsonStr, GetScanPatsBean.class);
                        if (ObjectUtils.isEmpty(getScanPatsBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getScanPatsBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getScanPatsBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getScanPatsBean.getMsgcode(), getScanPatsBean.getMsg());
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

    public static void isFirstToBed(String episodeId, final isFirstToBedCallBack callback) {

        AllotBedApiService.isFirstToBed(episodeId, new AllotBedApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        IsFirstToBedBean isFirstToBedBean = gson.fromJson(jsonStr, IsFirstToBedBean.class);
                        if (ObjectUtils.isEmpty(isFirstToBedBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(isFirstToBedBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(isFirstToBedBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(isFirstToBedBean.getMsgcode(), isFirstToBedBean.getMsg());
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

    public static void firstBeHosTemData(String episodeId, final FirstBeHosTemDataBeanCallBack callback) {

        AllotBedApiService.firstBeHosTemData(episodeId, new AllotBedApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        FirstBeHosTemDataBean firstBeHosTemDataBean = gson.fromJson(jsonStr, FirstBeHosTemDataBean.class);
                        if (ObjectUtils.isEmpty(firstBeHosTemDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(firstBeHosTemDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(firstBeHosTemDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(firstBeHosTemDataBean.getMsgcode(), firstBeHosTemDataBean.getMsg());
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

    //用户信息
    public interface GetUserMsgCallBack extends CommonCallBack {
        void onSuccess(GetScanPatsBean getScanPatsBean);
    }

    //查询空床 主治医生 主管护士
    public interface getAllotBedCallBack extends CommonCallBack {
        void onSuccess(AllotBedInfoBean allotBedInfoBean);
    }

    //分床并返回空床 主治医生 主管护士
    public interface getAllotBedResultCallBack extends CommonCallBack {
        void onSuccess(AllotBedInfoBean allotBedResultBean);
    }

    //判断患者是否第一次入院分床或者本科室第一次拖床
    public interface isFirstToBedCallBack extends CommonCallBack {
        void onSuccess(IsFirstToBedBean isFirstToBedBean);
    }
//判断患者是否第一次入院分床或者本科室第一次拖床
    public interface FirstBeHosTemDataBeanCallBack extends CommonCallBack {
        void onSuccess(FirstBeHosTemDataBean firstBeHosTemDataBean);
    }


}
