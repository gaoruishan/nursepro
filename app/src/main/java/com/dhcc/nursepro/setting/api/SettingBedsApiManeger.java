package com.dhcc.nursepro.setting.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.setting.bean.SettingBedListBean;
import com.dhcc.nursepro.workarea.bedselect.api.BedListApiService;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.setting.api
 * <p>
 * author Q
 * Date: 2018/9/14
 * Time:9:43
 */
public class SettingBedsApiManeger {
    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetBedListCallback extends CommonCallBack {
        void onSuccess(SettingBedListBean settingBedListBean);
    }

    public static void getBedList(final HashMap<String, String> properties, String MethodName, final GetBedListCallback callback) {
        SettingBedsApiService.getBedList(properties, MethodName, new BedListApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        SettingBedListBean settingBedListBean = gson.fromJson(jsonStr, SettingBedListBean.class);
                        if (ObjectUtils.isEmpty(settingBedListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(settingBedListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(settingBedListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(settingBedListBean.getMsgcode(), settingBedListBean.getMsg());
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


    public interface SettingBedsCallback extends CommonCallBack {
        void onSuccess(String msg);
    }


    public static void getSettingMsgList(final HashMap<String, String> properties, String MethodName, final SettingBedsCallback settingBedsCallback) {
        SettingBedsApiService.getBedList(properties, MethodName, new BedListApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                if (jsonStr.isEmpty()) {
                    settingBedsCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    settingBedsCallback.onSuccess(jsonStr);
                }
            }
        });
    }
}
