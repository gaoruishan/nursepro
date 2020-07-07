package com.dhcc.module.infusion.workarea.orderexecute.api;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-07-04/15:20
 * @email:grs0515@163.com
 */
public class OrderExecuteApiService {


    //getOrders(userId, regNo = "", sheetCode = "", hospitalId, groupId, startDate, startTime, endDate, endTime, barCode As %String = "", locId, pageNo = "", exeFlag = "") As %String [ WebMethod ]
    public static void getOrder(String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime, ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        CommWebService.addHospitalId(properties);
        CommWebService.addGroupId(properties);
        properties.put("regNo", regNo);
        properties.put("sheetCode", sheetCode);
        properties.put("startDate", startDate);
        properties.put("startTime",startTime);
        properties.put("endDate", endDate);
        properties.put("endTime", endTime);

        CommWebService.call("getOrders",properties,callBack);

    }
}
