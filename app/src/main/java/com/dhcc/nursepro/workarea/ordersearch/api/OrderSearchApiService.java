package com.dhcc.nursepro.workarea.ordersearch.api;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * OrderSearchApiService
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderSearchApiService extends NurseAPI {

    public static void getOrder(String bedStr, String regNo, String sheetCode, String pageNo, String startDate, String startTime, String endDate, String endTime,String screenParts, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));
        properties.put("groupId", spUtils.getString(SharedPreference.GROUPID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);
        properties.put("sheetCode", sheetCode);
        properties.put("pageNo", pageNo);

        properties.put("startDate", startDate);
        properties.put("startTime", startTime);
        properties.put("endDate", endDate);
        properties.put("endTime", endTime);
        properties.put("screenParts", screenParts);

        WebServiceUtils.callWebService(getOrders, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
