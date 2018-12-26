package com.dhcc.nursepro.workarea.workareaapi;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.labresult.bean.LabReslutDetailBean;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class WorkareaApiManager {

    //获取主页配置列表
    public static void getMainConfig(HashMap<String, String> map, String method, final GetMainconfigCallback callback) {

        WorkareaApiService.getCheckLabMsg(map, method, new WorkareaApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        MainConfigBean mainConfigBean = gson.fromJson(jsonStr, MainConfigBean.class);
                        if (ObjectUtils.isEmpty(mainConfigBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(mainConfigBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(mainConfigBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(mainConfigBean.getMsgcode(), mainConfigBean.getMsg());
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

    public interface GetMainconfigCallback extends CommonCallBack {
        void onSuccess(MainConfigBean mainConfigBean);
    }

}
