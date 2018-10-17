package com.dhcc.nursepro.workarea.motherbabylink.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.motherbabylink.bean.LinkResultBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.motherbabylink.api
 * <p>
 * author Q
 * Date: 2018/10/17
 * Time:15:50
 */
public class LinkApiManager {

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }
    public interface GetLinkMsgCallback extends CommonCallBack {
        void onSuccess(LinkResultBean linkResultBean);
    }

    public static void getLinkResultMsg(HashMap<String, String> map, String method, final GetLinkMsgCallback getLinkMsgCallback) {

        LinkApiService.getLinkMsg(map, method, new LinkApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    getLinkMsgCallback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        LinkResultBean linkResultBean = gson.fromJson(jsonStr, LinkResultBean.class);
                        if (ObjectUtils.isEmpty(linkResultBean)) {
                            getLinkMsgCallback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if (linkResultBean.getStatus().equals("0")) {
                                if (getLinkMsgCallback != null) {
                                    getLinkMsgCallback.onSuccess(linkResultBean);
                                }
                            } else {
                                if (getLinkMsgCallback != null) {
                                    getLinkMsgCallback.onFail(linkResultBean.getMsgcode(), linkResultBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        getLinkMsgCallback.onFail("-2", "网络错误，数据解析失败");
                    }

                }

            }
        });
    }

}
