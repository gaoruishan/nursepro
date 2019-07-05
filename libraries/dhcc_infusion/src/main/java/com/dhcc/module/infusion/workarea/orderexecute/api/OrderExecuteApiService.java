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
    //getScanInfo{barcode=0000000092; userId=10211; userDeptId=; wardId=1; }
    public static void getOrdersMsg(String episodeId, String scanInfo, ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
//        CommWebService.addWardId(properties);
        properties.put("wardId", "1");
        if (!"".equals(episodeId)) {
            properties.put("episodeId", episodeId);
        }
        properties.put("barcode", scanInfo);
        properties.put("userDeptId", "");
        CommWebService.callNurse("getScanInfo",properties,callBack);
    }
}
