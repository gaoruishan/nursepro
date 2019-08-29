package com.dhcc.nursepro.message.api;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.dhcc.nursepro.message.bean.MessageSkinBean;
import com.dhcc.nursepro.message.bean.ReadMessageBean;
import com.google.gson.Gson;

public class MessageApiManager {
    /**
     * Description: 获取皮试列表  阳性:Y/+ 阴性:N/-
     * Input： locId:科室ID
     * other:	w ##class(Nur.OPPDA.Message).getSkinTestMessage("7")
     */
    public static void getSkinTestMessage(final com.base.commlibs.http.CommonCallBack<MessageSkinBean> callBack) {
        MessageApiService.getSkinTestMessage(new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<MessageSkinBean> parserUtil = new ParserUtil<>();
                MessageSkinBean bean = parserUtil.parserResult(jsonStr, callBack, MessageSkinBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * Description: 置皮试结果
     * Input： oeoreId:执行记录ID
     * other:	w ##class(Nur.OPPDA.Order).setSkinTestResult("656||4||1","Y",1)
     */
    public static void setSkinTestResult(String oeoreId, String skinTest,String auditUserId,String auditPassword, final com.base.commlibs.http.CommonCallBack<CommResult> callBack) {
        MessageApiService.setSkinTestResult(oeoreId, skinTest, auditUserId,auditPassword,new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, callBack);
            }
        });
    }
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
