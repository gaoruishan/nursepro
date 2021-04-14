package com.dhcc.module.infusion.workarea.needles.api;

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
public class NeedlesApiService extends InfusionAPI {

    /**
     * Description:  按接单号查询医嘱
     * Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID,barCode:条码号
     * other:		 w ##class(Nur.OPPDA.Finish).getFinishOrdList("","","1","","568-3-1")
     * note: 		 如果走接单流程:查询接单任务医嘱;如果不走:查询当日医嘱
     */
    public static void getFinishOrdList(String curRegNo, String curOeoreId, String barCode, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        if (!TextUtils.isEmpty(curRegNo)) {
            properties.put("curRegNo", curRegNo);
        }
        if (!TextUtils.isEmpty(curOeoreId)) {
            properties.put("curOeoreId", curOeoreId);
        }
        properties.put("barCode", barCode);

        CommWebService.callUserIdLocId(GetExtractOrdList, properties,callBack);

    }


    /**
     * Description:  执行
     * Input：       oeoreId:执行记录ID,userId:用户ID,locId:科室Id
     * other:		w ##class(Nur.OPPDA.Finish).extractOrd("568-3-1","4636",266)
     */
    public static void extractOrd(String oeoreId , String wayNo,String finishWayFlag,ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        if (!TextUtils.isEmpty(oeoreId)) {
            properties.put("oeoreId", oeoreId);
        }
        if (!TextUtils.isEmpty(wayNo)) {
            properties.put("wayNo", wayNo);
        }
        if (!TextUtils.isEmpty(finishWayFlag)) {
            properties.put("finishWayFlag", finishWayFlag);
        }
        CommWebService.callUserIdLocId(ExtractOrd, properties,callBack);

    }
}
