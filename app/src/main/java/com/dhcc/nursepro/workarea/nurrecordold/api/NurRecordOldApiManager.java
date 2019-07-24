package com.dhcc.nursepro.workarea.nurrecordold.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.nurrecordold.bean.CareRecCommListBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.InWardPatListBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecDataBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecModelListBean;
import com.google.gson.Gson;

import java.util.Map;

/**
 * NurRecordOldApiManager
 *
 * @author Devlix126
 * created at 2019/7/5 10:35
 */
public class NurRecordOldApiManager {

    public static void getInWardPatList(final InWardPatListCallback callback) {
        NurRecordOldApiService.getInWardPatList(new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        InWardPatListBean inWardPatListBean = gson.fromJson(jsonStr, InWardPatListBean.class);
                        if (ObjectUtils.isEmpty(inWardPatListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(inWardPatListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(inWardPatListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(inWardPatListBean.getMsgcode(), inWardPatListBean.getMsg());
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

    public static void getModelList(String episodeID, final RecModelListCallback callback) {
        NurRecordOldApiService.getModelList(episodeID, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecModelListBean recModelListBean = gson.fromJson(jsonStr, RecModelListBean.class);
                        if (ObjectUtils.isEmpty(recModelListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recModelListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recModelListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recModelListBean.getMsgcode(), recModelListBean.getMsg());
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


    public static void getPGDId(String episodeID, String emrCode, final RecDataCallback callback) {
        NurRecordOldApiService.getPGDId(episodeID, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void getPGDVal(String pgdId, final RecDataCallback callback) {
        NurRecordOldApiService.getPGDVal(pgdId, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void getEmrXML(String episodeID, String emrCode, final RecDataCallback callback) {
        NurRecordOldApiService.getEmrXML(episodeID, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void getDateTime(final RecDataCallback callback) {
        NurRecordOldApiService.getDateTime(new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void getEmrPatinfo(String episodeID, String emrCode, final RecDataCallback callback) {
        NurRecordOldApiService.getEmrPatinfo(episodeID, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void savePGDData(String parr, String pgdId, final RecDataCallback callback) {
        NurRecordOldApiService.savePGDData(parr, pgdId, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void getCareRecComm(String parr, final CareRecCommCallback callback) {
        NurRecordOldApiService.getCareRecComm(parr, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        CareRecCommListBean careRecCommListBean = gson.fromJson(jsonStr, CareRecCommListBean.class);
                        Map JsonMap = gson.fromJson(jsonStr, Map.class);
                        careRecCommListBean.setMap(JsonMap);
                        if (ObjectUtils.isEmpty(careRecCommListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(careRecCommListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(careRecCommListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(careRecCommListBean.getMsgcode(), careRecCommListBean.getMsg());
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

    public static void getJLDVal(String par, String rw, final RecDataCallback callback) {
        NurRecordOldApiService.getJLDVal(par, rw, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void saveJLDData(String parr, String episodeID, String emrCode, final RecDataCallback callback) {
        NurRecordOldApiService.saveJLDData(parr, episodeID, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void getMPGDList(String parr, final CareRecCommCallback callback) {
        NurRecordOldApiService.getMPGDList(parr, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        CareRecCommListBean careRecCommListBean = gson.fromJson(jsonStr, CareRecCommListBean.class);
                        Map JsonMap = gson.fromJson(jsonStr, Map.class);
                        careRecCommListBean.setMap(JsonMap);
                        if (ObjectUtils.isEmpty(careRecCommListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(careRecCommListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(careRecCommListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(careRecCommListBean.getMsgcode(), careRecCommListBean.getMsg());
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

    public static void getMPGDVal(String par, String rw, final RecDataCallback callback) {
        NurRecordOldApiService.getMPGDVal(par, rw, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public static void saveMPGDData(String parr, String episodeID, final RecDataCallback callback) {
        NurRecordOldApiService.saveMPGDData(parr, episodeID, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
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

    public interface InWardPatListCallback extends CommonCallBack {
        void onSuccess(InWardPatListBean inWardPatListBean);
    }

    public interface RecModelListCallback extends CommonCallBack {
        void onSuccess(RecModelListBean recModelListBean);
    }

    public interface RecDataCallback extends CommonCallBack {
        void onSuccess(RecDataBean recDataBean);
    }

    public interface CareRecCommCallback extends CommonCallBack {
        void onSuccess(CareRecCommListBean careRecCommListBean);
    }
}
