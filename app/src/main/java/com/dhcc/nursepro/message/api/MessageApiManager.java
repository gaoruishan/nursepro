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
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MessageBean message = gson.fromJson(jsonStr, MessageBean.class);
                        if (ObjectUtils.isEmpty(message)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(message.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(message);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(message.getMsgcode(), message.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2","网络错误，数据解析失败");
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
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ReadMessageBean readMessageBean = gson.fromJson(jsonStr, ReadMessageBean.class);
                        if (ObjectUtils.isEmpty(readMessageBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(readMessageBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(readMessageBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(readMessageBean.getMsgcode(), readMessageBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2","网络错误，数据解析失败");
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
