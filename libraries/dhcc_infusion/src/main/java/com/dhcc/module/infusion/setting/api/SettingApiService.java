package com.dhcc.module.infusion.setting.api;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.InfusionAPI;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-05-20/16:08
 * @email:grs0515@163.com
 */
public class SettingApiService extends InfusionAPI {
    /**
     * Description: 工作量统计
     * Input： locId:科室ID,sttDate:开始日期,endDate:结束日期
     * other:	w ##class(Nur.OPPDA.Workload).getWorkload(266,"2019-05-01","2019-05-13")
     */
    public static void getWorkload(String sttDate, String  endDate, com.base.commlibs.http.ServiceCallBack callBack) {
        HashMap<String, String> properties = new HashMap<>();
        SPUtils spUtils = SPUtils.getInstance();
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("sttDate", sttDate);
        properties.put("endDate", endDate);
        CommWebService.call(GetWorkload,properties, callBack);
    }
}
