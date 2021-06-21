package com.dhcc.nursepro.workarea.vitalsign.api;

import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.vitalsign.bean.GetTempByPatListBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignRecordBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignSaveBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VitalSignApiManager {

    /**
     * 获取生命体征病人列表及配置项
     *
     * @param date     日期时间点
     * @param callback
     */
    public static void getVitalSignList(String date, String time, final VitalSignApiManager.GetVitalSignListCallback callback) {
        VitalSignApiService.getVitalSignList(date, time, new VitalSignApiService.ServiceCallBack() {

            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {

                    try {
                        VitalSignBean vitalSignBean = gson.fromJson(jsonStr, VitalSignBean.class);

                        if (ObjectUtils.isEmpty(vitalSignBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(vitalSignBean.getStatus())) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map = (Map<String, Object>) gson.fromJson(jsonStr, map.getClass());
                                vitalSignBean.setMapAll(map);
                                List patList = (List) map.get("patInfoList");
                                for (int i = 0; i < patList.size(); i++) {
                                    Map map1 = (Map) patList.get(i);
                                    String patEpi = (String) map1.get("episodeId");
                                    List list = (List) map1.get("needMeasureInfo");
                                    for (int j = 0; j < vitalSignBean.getPatInfoList().size(); j++) {
                                        if (vitalSignBean.getPatInfoList().get(j).getEpisodeId().equals(patEpi)) {
                                            vitalSignBean.getPatInfoList().get(j).setPatMap(map1);
                                        }
                                    }

                                    if (list.size() > 0) {
                                        Map map2 = (Map) list.get(0);
                                        for (int j = 0; j < vitalSignBean.getPatInfoList().size(); j++) {
                                            if (vitalSignBean.getPatInfoList().get(j).getEpisodeId().equals(patEpi)) {
                                                vitalSignBean.getPatInfoList().get(j).setNeedMeasureInfoMap(map2);
                                            }
                                        }
                                    }

                                }

                                for (int i = 0; i < vitalSignBean.getTopFilter().size(); i++) {
                                    for (int j = 0; j < vitalSignBean.getLeftFilter().size(); j++) {
                                        VitalSignBean.LeftFilterBean leftFilterBean = new VitalSignBean.LeftFilterBean();
                                        leftFilterBean.setCode(vitalSignBean.getLeftFilter().get(j).getCode());
                                        leftFilterBean.setDesc(vitalSignBean.getLeftFilter().get(j).getDesc());
                                        leftFilterBean.setTemNum(vitalSignBean.getLeftFilter().get(j).getTemNum());
                                        vitalSignBean.getTopFilter().get(i).getLeftFilter().add(leftFilterBean);
                                    }

                                    for (int j = 0; j < vitalSignBean.getPatInfoList().size(); j++) {
                                        String strCode = vitalSignBean.getTopFilter().get(i).getCode();
                                        Map map1 = vitalSignBean.getPatInfoList().get(j).getPatMap();
                                        if (map1 != null && map1.size() > 0 && map1.get(strCode).equals("1")) {
                                            VitalSignBean.PatInfoListBean patInfoListBean = new VitalSignBean.PatInfoListBean();
                                            patInfoListBean = vitalSignBean.getPatInfoList().get(j);
                                            vitalSignBean.getTopFilter().get(i).getPatInfoList().add(patInfoListBean);
                                        }
                                    }
                                }

                                for (int i = 0; i < vitalSignBean.getTopFilter().size(); i++) {
                                    for (int j = 0; j < vitalSignBean.getTopFilter().get(i).getPatInfoList().size(); j++) {
                                        for (int k = 0; k < vitalSignBean.getTopFilter().get(i).getLeftFilter().size(); k++) {
                                            String strCode = vitalSignBean.getTopFilter().get(i).getLeftFilter().get(k).getCode();
                                            Map map1 = vitalSignBean.getTopFilter().get(i).getPatInfoList().get(j).getNeedMeasureInfoMap();
                                            if (map1 != null && map1.size() > 0) {
                                                String strValue = vitalSignBean.getTopFilter().get(i).getPatInfoList().get(j).getNeedMeasureInfoMap().get(strCode).toString();
                                                if (strValue.equals("1")) {
                                                    vitalSignBean.getTopFilter().get(i).getLeftFilter().get(k).setTemNum(vitalSignBean.getTopFilter().get(i).getLeftFilter().get(k).getTemNum() + 1);
                                                }
                                            }
                                        }

                                    }
                                }

                                callback.onSuccess(vitalSignBean);
                            } else {
                                callback.onFail(vitalSignBean.getMsgcode(), vitalSignBean.getMsg());
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
                        Log.v("111111", e.toString());
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

    /**
     * 获取生命体征多人录入病人列表及配置项
     *
     * @param date     日期时间点
     * @param callback
     */
    public static void getTempByPatList(String date, String time, final VitalSignApiManager.GetTempByPatListCallback callback) {
        VitalSignApiService.getTempByPatList(date, time, new VitalSignApiService.ServiceCallBack() {

            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetTempByPatListBean getTempByPatListBean = gson.fromJson(jsonStr, GetTempByPatListBean.class);

                        if (ObjectUtils.isEmpty(getTempByPatListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getTempByPatListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getTempByPatListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getTempByPatListBean.getMsgcode(), getTempByPatListBean.getMsg());
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
        void onSuccess(VitalSignBean vitalSignBean);
    }

    public interface GetTempByPatListCallback extends VitalSignApiManager.CommonCallBack {
        void onSuccess(GetTempByPatListBean getTempByPatListBean);
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
