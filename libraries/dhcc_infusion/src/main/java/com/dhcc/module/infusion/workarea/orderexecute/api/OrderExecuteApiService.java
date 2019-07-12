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

    public static void getScanInfo(String episodeId, String scanInfo, ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addWardId(properties);
        properties.put("wardId", "1");//测试
        if (!"".equals(episodeId)) {
            properties.put("episodeId", episodeId);
        }
        properties.put("barcode", scanInfo);
        properties.put("userDeptId", "");
        CommWebService.call("getScanInfo",properties,callBack);
    }
    //{sheetCode=; locId=149; userId=10211; hospitalId=0; endTime=23:59; startTime=00:00; bedStr=; pageNo=1; wardId=1; regNo=0000000148; groupId=23; startDate=2019-07-03; endDate=2019-07-03; }
    public static void getOrder(String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime, ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
//        CommWebService.addLocId(properties);
//        CommWebService.addWardId(properties);
//        CommWebService.addHospitalId(properties);
//        CommWebService.addGroupId(properties);
        properties.put("groupId", "23");
        properties.put("bedStr", "");
        properties.put("pageNo", "1");
        properties.put("regNo", regNo);
        properties.put("sheetCode", sheetCode);
//        properties.put("startDate", startDate);
//        properties.put("startTime", startTime);
//        properties.put("endDate", endDate);
//        properties.put("endTime", endTime);

        properties.put("locId", "149");
        properties.put("wardId", "1");
        properties.put("hospitalId", "0");
        properties.put("startDate", "2019-02-03");
        properties.put("startTime", "00:00");
        properties.put("endDate", "2019-07-13");
        properties.put("endTime", "23:59");


        CommWebService.call("getOrders",properties,callBack);

    }
}
