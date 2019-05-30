package com.dhcc.module.infusion.workarea.patrol.api;

import android.text.TextUtils;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-04-28/09:13
 * @email:grs0515@163.com
 */
public class PatrolApiService {
    /**
     *  Description:  按接单号查询医嘱
     *  Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID,barCode:条码号
     *  other:		  w ##class(Nur.OPPDA.Tour).getOrdList("","","1","","568-3-1")
     *
     */
    public static void getTourOrdList(String curRegNo , String curOeoreId , String barCode, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        if (!TextUtils.isEmpty(curRegNo)) {
            properties.put("curRegNo", curRegNo);
        }
        properties.put("curOeoreId", curOeoreId);
        properties.put("barCode", barCode);
        CommWebService.callUserIdLocId("getTourOrdList",properties,callBack);
    }
    /**
     * Description:  巡视
     * Input：       oeoreId:执行记录ID,userId:用户ID,locId:科室Id,distantTime:预计完成时间,ifSpeed:滴速,puncturePart:穿刺部位,tourContent:数据串(key|value^key|value)
     * other:		  w ##class(Nur.OPPDA.Tour).tourOrd("568-3-1","1",1)
     */
    public static void tourOrd(String oeoreId, String distantTime, String ifSpeed, String puncturePart, String tourContent, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        if (!TextUtils.isEmpty(oeoreId)) {
            properties.put("oeoreId", oeoreId);
        }
        properties.put("distantTime", distantTime);
        properties.put("ifSpeed", ifSpeed);
        if(!TextUtils.isEmpty(puncturePart)){
            properties.put("puncturePart", puncturePart);
        }
        properties.put("tourContent", tourContent);
        CommWebService.callUserIdLocId("tourOrd",properties,callBack);

    }
}
