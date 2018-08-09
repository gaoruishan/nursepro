package com.dhcc.nursepro.message.api;

import com.dhcc.nursepro.message.bean.MessageBean;
import com.google.gson.Gson;

public class MessageApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }

    public interface GetMessageCallback extends CommonCallBack{
        void onSuccess(MessageBean msgs);
    }

    public static void getMessage(final GetMessageCallback callback){
        MessageApiService.getMessage(new MessageApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                MessageBean message = gson.fromJson(jsonStr,MessageBean.class);
                if (message.getStatus().equals("0")){
                    if (callback != null){
                        callback.onSuccess(message);
                    }
                }else{
                    if (callback != null){
                        callback.onFail(message.getMsgcode(),message.getMsg());
                    }
                }
            }
        });
    }


}
