package com.dhcc.module.infusion.workarea.puncture.api;

import android.text.TextUtils;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.utils.UserUtil;

import java.util.HashMap;

/**
 * com.dhcc.module.infusion.workarea.puncture.api
 * <p>
 * author Q
 * Date: 2019/3/7
 * Time:9:24
 */
public class PunctureApiService {
    /**
     * Description:  按接单号查询医嘱
     * Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID,barCode:条码号
     * other:		 w ##class(Nur.OPPDA.Puncture).getOrdList("","","1","","568-3-1")
     * note: 		 如果走接单流程:查询接单任务医嘱;如果不走:查询当日医嘱
     */
    public static void getOrdList(String curRegNo, String curOeoreId, String barCode, ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        if (!TextUtils.isEmpty(curRegNo)) {
            properties.put("curRegNo", curRegNo);
        }
        if(!TextUtils.isEmpty(curOeoreId)){
            properties.put("curOeoreId", curOeoreId);
        }
        properties.put("barCode", barCode);

        String windowName = UserUtil.getWindowName();
        properties.put("winCode", windowName);

        CommWebService.callUserIdLocId("getPunctOrdList",properties,callBack);
    }


    /**
     * Description:  执行
     * Input：       oeoreId:执行记录ID,userId:用户ID,locId:科室Id,distantTime:预计完成时间,ifSpeed:滴速,puncturePart:穿刺部位
     * other:		 w ##class(Nur.OPPDA.Execute).punctureOrd("568-3-1","1",1)
     */
    public static void punctureOrd(String oeoreId, String distantTime, String ifSpeed, String puncturePart, String puntureTool,String wayNo,String newWayFlag,ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("oeoreId", oeoreId);
        properties.put("distantTime", distantTime);
        properties.put("ifSpeed", ifSpeed);
        properties.put("puncturePart", puncturePart);
        if(!TextUtils.isEmpty(puntureTool)){
            properties.put("puntureTool", puntureTool);
        }
        if(!TextUtils.isEmpty(wayNo)){
            properties.put("wayNo", wayNo);
        }
        if(!TextUtils.isEmpty(newWayFlag)){
            properties.put("newWayFlag", newWayFlag);
        }
        CommWebService.callUserIdLocId("punctureOrd",properties,callBack);
    }

}
