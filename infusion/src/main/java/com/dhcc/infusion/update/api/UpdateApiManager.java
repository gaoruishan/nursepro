package com.dhcc.infusion.update.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.infusion.update.bean.UpdateBean;
import com.google.gson.Gson;

/**
 * UpdateApiManager
 *
 * @author DevLix126
 * @date 2018/10/29
 */
public class UpdateApiManager {

    public static void getNewVersion(String wardId, String ip, String curVersion, final GetNewVersionCallback callback) {
        UpdateApiService.getNewVersion(wardId, ip, curVersion, new UpdateApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        UpdateBean updateBean = gson.fromJson(jsonStr, UpdateBean.class);
                        if (ObjectUtils.isEmpty(updateBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(updateBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(updateBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(updateBean.getMsgcode(), updateBean.getMsg());
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

    public interface GetNewVersionCallback extends CommonCallBack {
        void onSuccess(UpdateBean updateBean);
    }


}
