package com.dhcc.nursepro.workarea.milkloopsystem.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkBottlingInfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkColdPatinfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkFreezingBagInfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkOperatResultBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkReceiveBagInfoBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class MilkLoopApiManager {

    //获取奶袋信息
    public static void getMilkReceiveBagInfo(HashMap<String, String> map, String method, final MilkReceiveBagInfoCallback milkReceiveBagInfoCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkReceiveBagInfoCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkReceiveBagInfoBean milkReceiveBagInfoBean = gson.fromJson(jsonStr, MilkReceiveBagInfoBean.class);
                        if (ObjectUtils.isEmpty(milkReceiveBagInfoBean)) {
                            milkReceiveBagInfoCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(milkReceiveBagInfoBean.getStatus())) {
                                if (milkReceiveBagInfoCallback != null) {
                                    milkReceiveBagInfoCallback.onSuccess(milkReceiveBagInfoBean);
                                }
                            } else {
                                if (milkReceiveBagInfoCallback != null) {
                                    milkReceiveBagInfoCallback.onFail(milkReceiveBagInfoBean.getMsgcode(), milkReceiveBagInfoBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        milkReceiveBagInfoCallback.onFail("-2", "网络错误，数据解析失败");
                    }


                }

            }
        });
    }

    //获取奶袋信息
    public static void getMilkFreezingBagInfo(HashMap<String, String> map, String method, final MilkFreezingBagInfoCallback milkFreezingBagInfoCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkFreezingBagInfoCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkFreezingBagInfoBean milkFreezingBagInfoBean = gson.fromJson(jsonStr, MilkFreezingBagInfoBean.class);
                        if (ObjectUtils.isEmpty(milkFreezingBagInfoBean)) {
                            milkFreezingBagInfoCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(milkFreezingBagInfoBean.getStatus())) {
                                if (milkFreezingBagInfoCallback != null) {
                                    milkFreezingBagInfoCallback.onSuccess(milkFreezingBagInfoBean);
                                }
                            } else {
                                if (milkFreezingBagInfoCallback != null) {
                                    milkFreezingBagInfoCallback.onFail(milkFreezingBagInfoBean.getMsgcode(), milkFreezingBagInfoBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        milkFreezingBagInfoCallback.onFail("-2", "网络错误，数据解析失败");
                    }
                }
            }
        });
    }

    //获取奶瓶信息
    public static void getMilkBottlingInfo(HashMap<String, String> map, String method, final MilkBottlingInfoCallback milkColdBottlingInfoCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();


                if (jsonStr.isEmpty()) {
                    milkColdBottlingInfoCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkBottlingInfoBean milkBottlingInfoBean = gson.fromJson(jsonStr, MilkBottlingInfoBean.class);
                        if (ObjectUtils.isEmpty(milkBottlingInfoBean)) {
                            milkColdBottlingInfoCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(milkBottlingInfoBean.getStatus())) {
                                if (milkColdBottlingInfoCallback != null) {
                                    milkColdBottlingInfoCallback.onSuccess(milkBottlingInfoBean);
                                }
                            } else {
                                if (milkColdBottlingInfoCallback != null) {
                                    milkColdBottlingInfoCallback.onFail(milkBottlingInfoBean.getMsgcode(), milkBottlingInfoBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        milkColdBottlingInfoCallback.onFail("-2", "网络错误，数据解析失败");
                    }
                }
            }
        });
    }

    //冷藏  获取奶瓶和患者信息
    public static void getMilkColdstorageInfo(HashMap<String, String> map, String method, final MilkColdstorageInfoCallback milkColdstorageInfoCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkColdstorageInfoCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkColdPatinfoBean milkColdPatinfoBean = gson.fromJson(jsonStr, MilkColdPatinfoBean.class);
                        if (ObjectUtils.isEmpty(milkColdPatinfoBean)) {
                            milkColdstorageInfoCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(milkColdPatinfoBean.getStatus())) {
                                if (milkColdstorageInfoCallback != null) {
                                    milkColdstorageInfoCallback.onSuccess(milkColdPatinfoBean);
                                }
                            } else {
                                if (milkColdstorageInfoCallback != null) {
                                    milkColdstorageInfoCallback.onFail(milkColdPatinfoBean.getMsgcode(), milkColdPatinfoBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        milkColdstorageInfoCallback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    //获取采奶 冷冻 装瓶 冷藏操作成功或失败结果
    public static void getMilkOperateResult(HashMap<String, String> map, String method, final MilkOperateCallback milkOperateCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkOperateCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkOperatResultBean milkOperatResultBean = gson.fromJson(jsonStr, MilkOperatResultBean.class);
                        if (ObjectUtils.isEmpty(milkOperatResultBean)) {
                            milkOperateCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if (milkOperatResultBean.getStatus().equals("0")) {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onSuccess(milkOperatResultBean);
                                }
                            } else {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onFail(milkOperatResultBean.getMsgcode(), milkOperatResultBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        milkOperateCallback.onFail("-2", "网络错误，数据解析失败");
                    }

                }

            }
        });
    }


    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface MilkReceiveBagInfoCallback extends CommonCallBack {
        void onSuccess(MilkReceiveBagInfoBean milkReceiveBagInfoBean);
    }

    public interface MilkFreezingBagInfoCallback extends CommonCallBack {
        void onSuccess(MilkFreezingBagInfoBean milkFreezingBagInfoBean);
    }

    public interface MilkBottlingInfoCallback extends CommonCallBack {
        void onSuccess(MilkBottlingInfoBean milkBottlingInfoBean);
    }

    public interface MilkColdstorageInfoCallback extends CommonCallBack {
        void onSuccess(MilkColdPatinfoBean milkColdPatinfoBean);
    }

    public interface MilkOperateCallback extends CommonCallBack {
        void onSuccess(MilkOperatResultBean milkOperatResultBean);
    }

}
