package com.dhcc.module.infusion.workarea.dosing.api;

import android.text.TextUtils;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.ServerAPI;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-04-24/14:53
 * @email:grs0515@163.com
 */
public class DosingApiService extends ServerAPI {
    /**
     * Description:  按接单号查询医嘱
     * Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID
     * other:		  w ##class(Nur.OPPDA.Despensing).getOrdList("0000000435","","1","")
     * note: 		  如果走接单流程:查询接单任务医嘱;如果不走:查询当日医嘱
     */

    public static void getOrdList(String regNo, String oeoreId, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        if (!TextUtils.isEmpty(regNo)) {
            properties.put("regNo", regNo);
        }
        properties.put("oeoreId", oeoreId);

        CommWebService.callUserIdLocId(getOrdList, properties, callBack);

    }

    /**
     * Description:  配液/复核
     * Input：       oeoreId:执行记录ID,userId:用户ID,type:操作类型(Despensing,Audit),locId:科室Id
     * other:		  w ##class(Nur.OPPDA.Despensing).despensingOrd("568-3-1","1","Audit",1)
     */
    public static void despensingOrd(String oeoreId, String type, String userCode, String password, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("oeoreId", oeoreId);
        properties.put("type", type);
        if (!TextUtils.isEmpty(userCode)) {
            properties.put("userCode", userCode);
        }
        if (!TextUtils.isEmpty(password)) {
            properties.put("password", password);
        }
        CommWebService.callUserIdLocId(despensingOrd, properties, callBack);
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
