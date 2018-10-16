package com.dhcc.nursepro.workarea.bedmap.api;

import android.widget.Toast;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.google.gson.Gson;

public class BedMapApiManager {

    public static void getBedMap(final GetBedMapCallback callback) {
        BedMapApiService.getBedMap(new BedMapApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    BedMapBean bedMapBean = new BedMapBean();
                    try {
                        bedMapBean = gson.fromJson(jsonStr, BedMapBean.class);
                    } catch (Exception e) {
                        callback.onFail("-2","网络错误，数据解析失败");
                    }

                    if (ObjectUtils.isEmpty(bedMapBean)) {
                        callback.onFail("-3", "网络错误，数据解析为空");
                    } else {
                        if (bedMapBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(bedMapBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(bedMapBean.getMsgcode(), bedMapBean.getMsg());
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

    public interface GetBedMapCallback extends CommonCallBack {
        void onSuccess(BedMapBean bedMapBean);
    }

}
