package com.dhcc.module.infusion.workarea.continues.api;

import android.text.TextUtils;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.InfusionAPI;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-04-29/10:12
 * @email:grs0515@163.com
 */
public class ContinueApiService extends InfusionAPI {

    /**
     * Description:  按接单号查询医嘱
     * Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID,barCode:条码号
     * other:		  w ##class(Nur.OPPDA.Change).getOrdList("","","1","","568-3-1")
     * note: 		  如果走接单流程:查询接单任务医嘱;如果不走:查询当日医嘱
     */
    public static void getChangeOrdList(String curRegNo, String curOeoreId, String barCode, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        if (!TextUtils.isEmpty(curRegNo)) {
            properties.put("curRegNo", curRegNo);
        }
        if (!TextUtils.isEmpty(curOeoreId)) {
            properties.put("curOeoreId", curOeoreId);
        }
        properties.put("barCode", barCode);

        CommWebService.callUserIdLocId(GetChangeOrdList, properties, callBack);
    }


    /**
     * Description:  执行
     * Input：       oeoreId:执行记录ID,userId:用户ID,locId:科室Id,distantTime:预计完成时间,ifSpeed:滴速,puncturePart:穿刺部位
     * other:		  w ##class(Nur.OPPDA.Change).changeOrd("568-3-1","4636",266,"2019-04-28 17:05:00",45,"脑后")
     */
    public static void changeOrd(String oeoreId, String distantTime, String ifSpeed, String puncturePart,String wayNo,String newWayFlag,ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        if (!TextUtils.isEmpty(oeoreId)) {
            properties.put("oeoreId", oeoreId);
        }
        if (!TextUtils.isEmpty(distantTime)) {
            properties.put("distantTime", distantTime);
        }
        if (!TextUtils.isEmpty(puncturePart)) {
            properties.put("puncturePart", puncturePart);
        }
        if (!TextUtils.isEmpty(wayNo)) {
            properties.put("wayNo", wayNo);
        }
        if (!TextUtils.isEmpty(newWayFlag)) {
            properties.put("newWayFlag", newWayFlag);
        }
        properties.put("ifSpeed", ifSpeed);
        CommWebService.callUserIdLocId(ChangeOrd, properties, callBack);
    }
}
