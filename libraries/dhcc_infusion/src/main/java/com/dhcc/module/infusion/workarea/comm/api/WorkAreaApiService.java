package com.dhcc.module.infusion.workarea.comm.api;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.ServerAPI;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-05-20/16:52
 * @email:grs0515@163.com
 */
public class WorkAreaApiService extends ServerAPI {
    /**
     * Description:  获取医嘱详情
     * Input：       oeoreId:执行记录ID
     * other:		  w ##class(Nur.OPPDA.Order).getOrdInfo("568-3-1")
     */
    public static void getOrdInfo(String oeoreId, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("oeoreId", oeoreId);
        CommWebService.call(getOrdInfo, properties, callBack);
    }

    /**
     * CreatDate:    2019-05-09
     * Description:  获取患者信息
     * Input：       regNo:登记号
     * other:		 w ##class(Nur.OPPDA.Patient).getPatInfo("0000000164")
     */
    public static void getPatInfo(String regNo, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("regNo", regNo);
        CommWebService.call(getPatInfo, properties, callBack);
    }

}
