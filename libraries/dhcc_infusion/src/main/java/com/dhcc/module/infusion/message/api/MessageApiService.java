package com.dhcc.module.infusion.message.api;

import android.text.TextUtils;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-05-20/16:27
 * @email:grs0515@163.com
 */
public class MessageApiService {


    /**
     * Description:  获取输液列表
     * Input：       locId:科室ID
     * other:		  w ##class(Nur.OPPDA.Message).getInfusionMessage("266")
     */
    public static void getInfusionMessage(com.base.commlibs.http.ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addLocId(null);
        CommWebService.call("getInfusionMessage", properties, callBack);
    }

    /**
     * Description: 获取皮试列表  阳性:Y/+ 阴性:N/-
     * Input： locId:科室ID
     * other:	w ##class(Nur.OPPDA.Message).getSkinTestMessage("7")
     */
    public static void getSkinTestMessage(com.base.commlibs.http.ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addLocId(null);
        CommWebService.call("getSkinTestMessage", properties, callBack);
    }
    /**
     * Description: 置皮试结果
     * Input： oeoreId:执行记录ID
     * other:	w ##class(Nur.OPPDA.Order).setSkinTestResult("656||4||1","Y",1)
     */
    public static void setSkinTestResult(String oeoreId, String skinTest,String auditUserId,String auditPassword, com.base.commlibs.http.ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        if(!TextUtils.isEmpty(oeoreId)){
            properties.put("oeoreId",oeoreId);
            properties.put("skinTest", skinTest);
            if(!TextUtils.isEmpty(auditUserId)){
                properties.put("auditUserId", auditUserId);
            }
            if(!TextUtils.isEmpty(auditPassword)){
                properties.put("auditPassword", auditPassword);
            }
            CommWebService.call("setSkinTestResult", properties, callBack);
        }
    }

    public static void getNotifyMessage(ServiceCallBack callBack) {
        CommWebService.callUserIdLocId("getNotifyMessage",null,callBack);
    }
}
