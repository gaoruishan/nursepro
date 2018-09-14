package com.dhcc.nursepro.message.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.dhcc.nursepro.message.bean.ReadMessageBean;
import com.google.gson.Gson;

public class MessageApiManager {

    public static void getMessage(final GetMessageCallback callback) {
        MessageApiService.getMessage(new MessageApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    MessageBean message = gson.fromJson(jsonStr, MessageBean.class);
                    if (ObjectUtils.isEmpty(message)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (message.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(message);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(message.getMsgcode(), message.getMsg());
                            }
                        }
                    }
                }


            }
        });
    }

    public static void readMessage(String episodeId, final ReadMessageCallback callback) {
        MessageApiService.readMessage(episodeId,new MessageApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    ReadMessageBean readMessageBean = gson.fromJson(jsonStr, ReadMessageBean.class);
                    if (ObjectUtils.isEmpty(readMessageBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (readMessageBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(readMessageBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(readMessageBean.getMsgcode(), readMessageBean.getMsg());
                            }
                        }
                    }
                }


            }
        });
    }

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetMessageCallback extends CommonCallBack {
        void onSuccess(MessageBean msgs);
    }

    public interface ReadMessageCallback extends CommonCallBack {
        void onSuccess(ReadMessageBean readMessageBean);
    }


}
