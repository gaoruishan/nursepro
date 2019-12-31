package com.dhcc.module.health.workarea.vitalsign.api;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.health.workarea.vitalsign.bean.VitalSignDetailBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:gaoruishan
 * @date:202019-10-29/11:06
 * @email:grs0515@163.com
 */
public class VitalSignsApiManager {
    /**
     * Description: 获取患者体征明细
     * Creator: JYW
     * Input： episodeId 就诊号,stDate开始日期,endDate 结束日期
     * others: ("94","2018-03-01","2018-08-12") 首次调用传空
     */
    public static void getPatTempDetail(String episodId, String stDate,String enDate,final CommonCallBack<VitalSignDetailBean> callBack) {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("episodeId", episodId);
        properties.put("stDate", stDate);
        properties.put("endDate", enDate);
        CommWebService.callHealth("getPatTempDetail", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<VitalSignDetailBean> parserUtil = new ParserUtil<>();
                VitalSignDetailBean bean = parserUtil.parserResult(jsonStr, callBack, VitalSignDetailBean.class);
                Gson gson = new Gson();
                Map JsonMap = gson.fromJson(jsonStr,Map.class);
                bean.setMap(JsonMap);
                if (bean==null) return;
                parserUtil.parserStatus(bean,callBack);
            }
        });
    }
}
