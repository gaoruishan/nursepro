package com.dhcc.nursepro.config.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.config.bean.ConfigBean;
import com.dhcc.nursepro.login.bean.LoginBean;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.google.gson.Gson;

public class ConfigApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }

    public interface GetConfigCallback extends ConfigApiManager.CommonCallBack {
        void onSuccess(ConfigBean config);
    }

    public static void getConfig(final ConfigApiManager.GetConfigCallback callback){
        ConfigApiService.getConfig(new ConfigApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ConfigBean config = gson.fromJson(jsonStr,ConfigBean.class);
                        if (ObjectUtils.isEmpty(config)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(config.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(config);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(config.getMsgcode(), config.getMsg());
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

}
