package com.dhcc.nursepro.workarea.bedmap.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.google.gson.Gson;

public class BedMapApiManager {

    public static void getBedMap(String wardId, String userId, final GetBedMapCallback callback) {
        BedMapApiService.getBedMap(wardId, userId, new BedMapApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    BedMapBean bedMapBean = gson.fromJson(jsonStr, BedMapBean.class);
                    if (ObjectUtils.isEmpty(bedMapBean)) {
                        callback.onFail("-1", "网络请求失败");
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
