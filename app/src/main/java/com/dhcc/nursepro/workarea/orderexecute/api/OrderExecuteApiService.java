package com.dhcc.nursepro.workarea.orderexecute.api;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * OrderSearchApiService
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderExecuteApiService {

    public static void getOrder(String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));
        properties.put("groupId", spUtils.getString(SharedPreference.GROUPID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("bedStr", "");
        properties.put("pageNo", "1");

        properties.put("regNo", regNo);
        properties.put("sheetCode", sheetCode);

        properties.put("startDate", startDate);
        properties.put("startTime", startTime);
        properties.put("endDate", endDate);
        properties.put("endTime", endTime);

        Log.i("OrderExecute", "getOrder: "+properties.toString());

        WebServiceUtils.callWebService("getOrders", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public static void execOrSeeOrder(String oeoreId,String execStatusCode, final ServiceCallBack callBack){
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("oeoreId",oeoreId);
        properties.put("execStatusCode",execStatusCode);
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId", spUtils.getString(SharedPreference.LOCID));
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));


        Log.i("OrderExecute", "execOrSeeOrder: "+properties.toString());

        WebServiceUtils.callWebService("execOrSeeOrder", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
