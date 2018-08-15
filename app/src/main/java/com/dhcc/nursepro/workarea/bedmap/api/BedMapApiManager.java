package com.dhcc.nursepro.workarea.bedmap.api;

import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.google.gson.Gson;

public class BedMapApiManager {

    public static void getBedMap(String wardId, String userId, final GetBedMapCallback callback) {
        BedMapApiService.getBedMap(wardId, userId, new BedMapApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                BedMapBean bedMapBean = gson.fromJson(jsonStr, BedMapBean.class);
                if (bedMapBean != null) {
                    callback.onSuccess(bedMapBean);
                } else {
                    callback.onFail("", "");
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
