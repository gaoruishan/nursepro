package com.dhcc.nursepro.workarea.ordersearch.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;
import com.google.gson.Gson;

/**
 * OrderSearchApiManager
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderSearchApiManager {
    public static void getOrder(String bedStr, String regNo, String sheetCode, String pageNo, String startDate, String startTime, String endDate, String endTime, final GetOrderCallback callback) {
        OrderSearchApiService.getOrder(bedStr, regNo, sheetCode, pageNo, startDate, startTime, endDate, endTime, new OrderSearchApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        OrderSearchBean orderSearchBean = gson.fromJson(jsonStr, OrderSearchBean.class);
                        if (ObjectUtils.isEmpty(orderSearchBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(orderSearchBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(orderSearchBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(orderSearchBean.getMsgcode(), orderSearchBean.getMsg());
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

    public interface GetOrderCallback extends CommonCallBack {
        void onSuccess(OrderSearchBean orderSearchBean);
    }
}
