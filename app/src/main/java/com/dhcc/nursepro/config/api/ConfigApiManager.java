package com.dhcc.nursepro.config.api;

import com.dhcc.nursepro.config.bean.ConfigBean;
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
                ConfigBean config = gson.fromJson(jsonStr,ConfigBean.class);
                if (config.getStatus().equals("0")){
                    if (callback != null){
                        callback.onSuccess(config);
                    }
                }else{
                    if (callback != null){
                        callback.onFail(config.getMsgcode(),config.getMsg());
                    }
                }
            }
        });
    }

}
