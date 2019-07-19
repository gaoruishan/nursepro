package com.dhcc.nursepro.workarea.nurrecordold.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecDataBean;
import com.google.gson.Gson;

/**
 * NurRecordOldApiManager
 *
 * @author Devlix126
 * created at 2019/7/5 10:35
 */
public class NurRecordOldApiManager {

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

    public static void getEmrPatinfo(String episodeID, final RecDataCallback callback) {
        NurRecordOldApiService.getEmrPatinfo(episodeID, new NurRecordOldApiService.ServiceCallBack() {
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

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface RecDataCallback extends CommonCallBack {
        void onSuccess(RecDataBean recDataBean);
    }
}
