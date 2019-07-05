package com.dhcc.nursepro.workarea.vitalsign.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignRecordBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignSaveBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class VitalSignApiManager {

    /**
     * 获取生命体征病人列表及配置项
     *
     * @param date     日期时间点
     * @param callback
     */
    public static void getVitalSignList(String date,String time, final VitalSignApiManager.GetVitalSignListCallback callback) {
        VitalSignApiService.getVitalSignList(date,time, new VitalSignApiService.ServiceCallBack() {

            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map = (Map<String, Object>) gson.fromJson(jsonStr, map.getClass());
                        if (ObjectUtils.isEmpty(map)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            String status = (String) map.get("status");
                            if ("0".equals(status)) {
                                callback.onSuccess(map);
                            } else {
                                callback.onFail((String) map.get("msgcode"), (String) map.get("msg"));
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }
                }


            }
        });
    }

    /**
     * 获取当前病人及时间点需要录入的生命体征项目
     *
     * @param episodeId 就诊号
     * @param date      日期时间点
     * @param callback
     */
    public static void getVitalSignItems(String episodeId, String date, final VitalSignApiManager.GetVitalSignItemCallback callback) {

        VitalSignApiService.getVitalSignItems(episodeId, date, new VitalSignApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        VitalSignRecordBean vitalSignRecordBean = gson.fromJson(jsonStr, VitalSignRecordBean.class);
                        if (ObjectUtils.isEmpty(vitalSignRecordBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(vitalSignRecordBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(vitalSignRecordBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(vitalSignRecordBean.getMsgcode(), vitalSignRecordBean.getMsg());
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

    /**
     * 查看病人体温单
     *
     * @param episodeId
     * @param callback
     */

    public static void gePatientTempImages(String episodeId, final GetPatientTempImagesCallback callback) {
        VitalSignApiService.getPatientTempImages(episodeId, new VitalSignApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map = gson.fromJson(jsonStr, map.getClass());
                        if (ObjectUtils.isEmpty(map)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            String status = (String) map.get("status");
                            if ("0".equals(status)) {
                                callback.onSuccess(map);
                            } else {
                                callback.onFail((String) map.get("msgcode"), (String) map.get("msg"));
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }
                }
            }
        });
    }

    /**
     * 保存病人生命体征数据
     *
     * @param episodeId 病人id
     * @param date      日期时间点
     * @param result    生命体征数据（格式化为字符串的 array）
     * @param callback
     */
    public static void saveTempData(String episodeId, String date, String result, final SaveTempDataCallback callback) {
        VitalSignApiService.saveTempData(episodeId, date, result, new VitalSignApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        VitalSignSaveBean vitalSignSaveBean = gson.fromJson(jsonStr, VitalSignSaveBean.class);

                        if (ObjectUtils.isEmpty(vitalSignSaveBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(vitalSignSaveBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(vitalSignSaveBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(vitalSignSaveBean.getMsgcode(), vitalSignSaveBean.getMsg());
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

    public interface GetVitalSignListCallback extends VitalSignApiManager.CommonCallBack {
        //void onSuccess(VitalSignBean vitalSignBean);
        void onSuccess(Map<String, Object> map);
    }

    public interface GetVitalSignItemCallback extends VitalSignApiManager.CommonCallBack {
        void onSuccess(VitalSignRecordBean bean);
    }

    public interface GetPatientTempImagesCallback extends VitalSignApiManager.CommonCallBack {
        void onSuccess(Map<String, Object> map);
    }

    public interface SaveTempDataCallback extends VitalSignApiManager.CommonCallBack {
        void onSuccess(VitalSignSaveBean bean);
    }


}
