package com.dhcc.nursepro.workarea.bedmap.api;

import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.google.gson.Gson;

public class BedMapApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }

    public interface GetBedMapCallback extends CommonCallBack{
        void onSuccess(BedMapBean bedMapBean);
    }

    public static void getBedMap(final GetBedMapCallback callback){
        BedMapApiService.getBedMap(new BedMapApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                BedMapBean bedMapBean = gson.fromJson(jsonStr,BedMapBean.class);
                if (bedMapBean != null) {

                    callback.onSuccess(bedMapBean);
                } else {
                    callback.onFail("","");
                }


//                if (result.getStatus().equals("0")){
//                    if (callback != null){
//                        callback.onSuccess(result);
//                    }
//                }else{
//                    if (callback != null){
//                        callback.onFail(result.getMsgcode(),result.getMsg());
//                    }
//                }
            }
        });
    }


}
