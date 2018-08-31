package com.dhcc.nursepro.workarea.orderhandle.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.orderhandle.bean.OrderHandleBean;
import com.dhcc.nursepro.workarea.patevents.api.PatEventsApiService;
import com.dhcc.nursepro.workarea.patevents.bean.EventItemBean;
import com.dhcc.nursepro.workarea.patevents.bean.EventResultBean;
import com.dhcc.nursepro.workarea.patevents.bean.PatEventsBean;
import com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class OrdersHandleApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }

    public interface GetOrdersMsgCallBack extends CommonCallBack{
        void onSuccess(OrderHandleBean orderHandleBean);
    }

    public static void GetOrdersMsg(HashMap<String,String> map, final GetOrdersMsgCallBack callback) {

        PatEventsApiService.getEventMsg(map, "getOrders", new PatEventsApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

                Gson gson = new Gson();


                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    OrderHandleBean orderHandleBean = gson.fromJson(jsonStr, OrderHandleBean.class);
                    if (ObjectUtils.isEmpty(orderHandleBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (orderHandleBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(orderHandleBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(orderHandleBean.getMsgcode(), orderHandleBean.getMsg());
                            }
                        }
                    }
                }


            }
        });
    }

}
