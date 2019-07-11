package com.dhcc.nursepro.workarea.milkloopsystem_wenling.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkBindOrderBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkBottlingInfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkColdPatinfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkFeedExeListBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkFreezingBagInfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkOperatResultBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkReceiveBagInfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkWarmingBaginfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkWarmingSttBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.ScanInfoBean;
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

    //获取温奶时候奶袋信息
    public static void getMilkWarminginfo(HashMap<String, String> map, String method, final MilkWarmingBagInfoCallback milkOperateCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkOperateCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkWarmingBaginfoBean milkWarmingBaginfoBean = gson.fromJson(jsonStr, MilkWarmingBaginfoBean.class);
                        if (ObjectUtils.isEmpty(milkWarmingBaginfoBean)) {
                            milkOperateCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if (milkWarmingBaginfoBean.getStatus().equals("0")) {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onSuccess(milkWarmingBaginfoBean);
                                }
                            } else {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onFail(milkWarmingBaginfoBean.getMsgcode(), milkWarmingBaginfoBean.getMsg());
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


    //温奶开始
    public static void getMilkWarmingStt(HashMap<String, String> map, String method, final MilkWarmingSttCallback milkOperateCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkOperateCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkWarmingSttBean milkWarmingSttBean = gson.fromJson(jsonStr, MilkWarmingSttBean.class);
                        if (ObjectUtils.isEmpty(milkWarmingSttBean)) {
                            milkOperateCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if (milkWarmingSttBean.getStatus().equals("0")) {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onSuccess(milkWarmingSttBean);
                                }
                            } else {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onFail(milkWarmingSttBean.getMsgcode(), milkWarmingSttBean.getMsg());
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

    //绑定医嘱
    public static void getMilkBindOrder(HashMap<String, String> map, String method, final MilkBindOrderCallback milkOperateCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkOperateCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkBindOrderBean milkBindOrderBean = gson.fromJson(jsonStr, MilkBindOrderBean.class);
                        if (ObjectUtils.isEmpty(milkBindOrderBean)) {
                            milkOperateCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if (milkBindOrderBean.getStatus().equals("0")) {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onSuccess(milkBindOrderBean);
                                }
                            } else {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onFail(milkBindOrderBean.getMsgcode(), milkBindOrderBean.getMsg());
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

    //获取喂养列表
    public static void getMilkFeedExeList(HashMap<String, String> map, String method, final MilkFeedExeListCallback milkOperateCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkOperateCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MilkFeedExeListBean milkFeedExeListBean = gson.fromJson(jsonStr, MilkFeedExeListBean.class);
                        if (ObjectUtils.isEmpty(milkFeedExeListBean)) {
                            milkOperateCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if (milkFeedExeListBean.getStatus().equals("0")) {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onSuccess(milkFeedExeListBean);
                                }
                            } else {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onFail(milkFeedExeListBean.getMsgcode(), milkFeedExeListBean.getMsg());
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

    //获取扫描信息
    public static void getScanInfo(HashMap<String, String> map, String method, final MilkFeedGetScanInfoCallback milkOperateCallback) {

        MilkLoopService.getMilkLoopMsg(map, method, new MilkLoopService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    milkOperateCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ScanInfoBean scanInfoBean = gson.fromJson(jsonStr, ScanInfoBean.class);
                        if (ObjectUtils.isEmpty(scanInfoBean)) {
                            milkOperateCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if (scanInfoBean.getStatus().equals("0")) {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onSuccess(scanInfoBean);
                                }
                            } else {
                                if (milkOperateCallback != null) {
                                    milkOperateCallback.onFail(scanInfoBean.getMsgcode(), scanInfoBean.getMsg());
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
    public interface MilkWarmingBagInfoCallback extends CommonCallBack {
        void onSuccess(MilkWarmingBaginfoBean milkWarmingBaginfoBean);
    }
    public interface MilkWarmingSttCallback extends CommonCallBack {
        void onSuccess(MilkWarmingSttBean milkWarmingSttBean);
    }
    public interface MilkBindOrderCallback extends CommonCallBack {
        void onSuccess(MilkBindOrderBean milkBindOrderBean);
    }
    public interface MilkFeedExeListCallback extends CommonCallBack {
        void onSuccess(MilkFeedExeListBean milkFeedExeListBean);
    }
    public interface MilkFeedGetScanInfoCallback extends CommonCallBack {
        void onSuccess(ScanInfoBean scanInfoBean);
    }
}
