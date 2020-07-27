package com.dhcc.nursepro.workarea.ordersearch.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderSearchApiManager
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderSearchApiManager {
    public static void getOrder(String bedStr, String regNo, String sheetCode, String pageNo, String startDate, String startTime, String endDate, String endTime, String screenParts,final GetOrderCallback callback) {
        OrderSearchApiService.getOrder(bedStr, regNo, sheetCode, pageNo, startDate, startTime, endDate, endTime, screenParts,new OrderSearchApiService.ServiceCallBack() {
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
                                    Map bedMapMap = gson.fromJson(jsonStr, Map.class);

                                    List<Map<String, String>> patInfoMapList =new ArrayList<>();
                                    patInfoMapList = (List<Map<String, String>>) bedMapMap.get("orders");
                                    for (int i = 0; i <patInfoMapList.size() ; i++) {
                                        Map patMap = patInfoMapList.get(i);
                                        List<Map<String, String>> patInfoMapListOrder=new ArrayList<>();
                                        patInfoMapListOrder = (List<Map<String, String>>) patMap.get("patOrds");
                                        for (int j = 0; j <patInfoMapListOrder.size() ; j++) {
                                            List<Map<String, Map>> patInfoMapList1 =new ArrayList<>();
                                            patInfoMapList1 = (List<Map<String, Map>>) patInfoMapListOrder.get(j);
                                            Map<String, String> patOrdMap = new HashMap<>();
                                            for (int k = 0; k < patInfoMapList1.size(); k++) {
                                                patOrdMap = patInfoMapList1.get(k).get("orderInfo");
                                                orderSearchBean.getOrders().get(i).getPatOrds().get(j).get(k).setOrderInfoMap(patOrdMap);
                                            }
                                        }
                                    }


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
