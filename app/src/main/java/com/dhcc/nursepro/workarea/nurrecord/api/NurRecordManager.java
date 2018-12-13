package com.dhcc.nursepro.workarea.nurrecord.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class NurRecordManager {

    public static void getModelDetailListMsg(HashMap<String, String> map, String method, final getLabOutCallBack callback) {

        NurRecordService.getModelDetailMsg(map, method, new NurRecordService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        NurRecordBean modelDetailBean = gson.fromJson(jsonStr, NurRecordBean.class);
                        if (ObjectUtils.isEmpty(modelDetailBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(modelDetailBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(modelDetailBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(modelDetailBean.getMsgcode(), modelDetailBean.getMsg());
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

    //新建，查询检验交接单
    public interface getLabOutCallBack extends CommonCallBack {
        void onSuccess(NurRecordBean modelDetailBean);
    }
}
