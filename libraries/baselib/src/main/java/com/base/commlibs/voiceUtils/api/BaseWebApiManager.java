package com.base.commlibs.voiceUtils.api;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.voiceUtils.bean.VoicePatListBean;
import com.base.commlibs.voiceUtils.bean.VoiceVisalBean;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.base.commlibs.http
 * <p>
 * author Q
 * Date: 2021/5/8
 * Time:14:32
 */
public class BaseWebApiManager {
    public static void getPatListToVoiceBean(final GetVoicePatListCallback callback) {
        BaseWebApiService.getPatListToVoice(new BaseWebApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        VoicePatListBean voicePatListBean = gson.fromJson(jsonStr, VoicePatListBean.class);
                        if (ObjectUtils.isEmpty(voicePatListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(voicePatListBean.getStatus())) {
                                if (callback != null) {
                                    SPUtils.getInstance().put(SharedPreference.VOICE_PATINFO_JSON,jsonStr);
                                    callback.onSuccess(jsonStr);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(voicePatListBean.getMsgcode(), voicePatListBean.getMsg());
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
     * 获取生命体征病人列表及配置项
     *
     * @param date     日期时间点
     * @param callback
     */
    public static void getVitalSignList(String date,String time, final BaseWebApiManager.GetVitalSignListCallback callback) {
        BaseWebApiService.getVitalSignList(date,time, new BaseWebApiService.ServiceCallBack() {

            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {

                    try {
                        VoiceVisalBean vitalSignBean = gson.fromJson(jsonStr, VoiceVisalBean.class);

                        SPUtils.getInstance().put(SharedPreference.VOICE_VISAL_LIST,jsonStr);
                        if (ObjectUtils.isEmpty(vitalSignBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(vitalSignBean.getStatus())) {
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

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetVitalSignListCallback extends BaseWebApiManager.CommonCallBack {
        //void onSuccess(VitalSignBean vitalSignBean);
        void onSuccess(VoiceVisalBean vitalSignBean);
    }

    public interface GetVoicePatListCallback extends CommonCallBack {
        void onSuccess(String patListJson);
    }
}
