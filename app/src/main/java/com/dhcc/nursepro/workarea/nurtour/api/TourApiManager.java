package com.dhcc.nursepro.workarea.nurtour.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.nurtour.bean.AllTourListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.BloodListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.DeleteTourBean;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeModelBean;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeTourListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.InfusionListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.ModelDataBean;
import com.dhcc.nursepro.workarea.nurtour.bean.TourSaveBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.nurtour.api
 * <p>
 * author Q
 * Date: 2019/4/20
 * Time:9:37
 */
public class TourApiManager {

    //获取全部巡视
    public static void getPatsList(HashMap<String, String> map, String method, final getPatsListCallback callback) {

        TourApiService.getTourListMsg(map, method, new TourApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        AllTourListBean allTourListBean = gson.fromJson(jsonStr, AllTourListBean.class);
                        if (ObjectUtils.isEmpty(allTourListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(allTourListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(allTourListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(allTourListBean.getMsgcode(), allTourListBean.getMsg());
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


    //获取分级巡视
    public static void getGradeTourList(HashMap<String, String> map, String method, final getGradeTourListcall callback) {

        TourApiService.getTourListMsg(map, method, new TourApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GradeTourListBean gradeTourListBean = gson.fromJson(jsonStr, GradeTourListBean.class);
                        if (ObjectUtils.isEmpty(gradeTourListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(gradeTourListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(gradeTourListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(gradeTourListBean.getMsgcode(), gradeTourListBean.getMsg());
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

    //获取分级巡视模板
    public static void getGradeModel(HashMap<String, String> map, String method, final getGradeModelcall callback) {

        TourApiService.getTourListMsg(map, method, new TourApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GradeModelBean gradeModelBean = gson.fromJson(jsonStr, GradeModelBean.class);
                        if (ObjectUtils.isEmpty(gradeModelBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(gradeModelBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(gradeModelBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(gradeModelBean.getMsgcode(), gradeModelBean.getMsg());
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


    //获取巡视模板
    public static void getModelData(HashMap<String, String> map, String method, final getModelDatacall callback) {

        TourApiService.getTourListMsg(map, method, new TourApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ModelDataBean modelDataBean = gson.fromJson(jsonStr, ModelDataBean.class);
                        if (ObjectUtils.isEmpty(modelDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(modelDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(modelDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(modelDataBean.getMsgcode(), modelDataBean.getMsg());
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


    //获取输液巡视列表
    public static void getInfusionList(HashMap<String, String> map, String method, final getInfusionlcall callback) {

        TourApiService.getTourListMsg(map, method, new TourApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        InfusionListBean infusionListBean = gson.fromJson(jsonStr, InfusionListBean.class);
                        if (ObjectUtils.isEmpty(infusionListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(infusionListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(infusionListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(infusionListBean.getMsgcode(), infusionListBean.getMsg());
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


    //获取保存结果
    public static void getTourSaveMsg(HashMap<String, String> map, String method, final getTourSavecall callback) {

        TourApiService.getTourListMsg(map, method, new TourApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        TourSaveBean tourSaveBean = gson.fromJson(jsonStr, TourSaveBean.class);
                        if (ObjectUtils.isEmpty(tourSaveBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(tourSaveBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(tourSaveBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(tourSaveBean.getMsgcode(), tourSaveBean.getMsg());
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

    //删除巡视记录
    public static void getTourDeleteMsg(HashMap<String, String> map, String method, final getTourDeleteCall callback) {

        TourApiService.getTourListMsg(map, method, new TourApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DeleteTourBean deleteTourBean = gson.fromJson(jsonStr, DeleteTourBean.class);
                        if (ObjectUtils.isEmpty(deleteTourBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(deleteTourBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(deleteTourBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(deleteTourBean.getMsgcode(), deleteTourBean.getMsg());
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

    //获取输血巡视列表
//    public static void getBloodList(HashMap<String, String> map, String method, final getBloodcall callback) {
//
//        TourApiService.getTourListMsg(map, method, new TourApiService.ServiceCallBack() {
//            @Override
//            public void onResult(String jsonStr) {
//                Gson gson = new Gson();
//
//                if (jsonStr.isEmpty()) {
//                    callback.onFail("-1", "网络错误，请求数据为空");
//                } else {
//                    try {
//                        BloodListBean bloodListBean = gson.fromJson(jsonStr, BloodListBean.class);
//                        if (ObjectUtils.isEmpty(bloodListBean)) {
//                            callback.onFail("-3", "网络错误，数据解析为空");
//                        } else {
//                            if ("0".equals(bloodListBean.getStatus())) {
//                                if (callback != null) {
//                                    callback.onSuccess(bloodListBean);
//                                }
//                            } else {
//                                if (callback != null) {
//                                    callback.onFail(bloodListBean.getMsgcode(), bloodListBean.getMsg());
//                                }
//                            }
//                        }
//                    } catch (Exception e) {
//                        callback.onFail("-2", "网络错误，数据解析失败");
//                    }
//
//                }
//            }
//        });
//    }

    public interface getPatsListCallback extends CommonCallBack {
        void onSuccess(AllTourListBean allTourListBean);
    }
    public interface getGradeTourListcall extends CommonCallBack {
        void onSuccess(GradeTourListBean gradeTourListBean);
    }
    public interface getGradeModelcall extends CommonCallBack {
        void onSuccess(GradeModelBean gradeModelBean);
    }
    public interface getModelDatacall extends CommonCallBack {
        void onSuccess(ModelDataBean modelDataBean);
    }
    public interface getInfusionlcall extends CommonCallBack {
        void onSuccess(InfusionListBean infusionListBean);
    }
    public interface getBloodcall extends CommonCallBack {
        void onSuccess(BloodListBean bloodListBean);
    }

    public interface getTourSavecall extends CommonCallBack {
        void onSuccess(TourSaveBean tourSaveBean);
    }

    public interface getTourDeleteCall extends CommonCallBack {
        void onSuccess(DeleteTourBean deleteTourBean);
    }

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

}
