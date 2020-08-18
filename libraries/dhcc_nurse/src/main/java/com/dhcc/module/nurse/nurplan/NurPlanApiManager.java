package com.dhcc.module.nurse.nurplan;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.nurse.nurplan.bean.NurPlanBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202020-08-17/11:27
 * @email:grs0515@163.com
 */
public class NurPlanApiManager {

    public static void getQuestionRecord(String episodeId, String startDate, String endDate, String status, CommonCallBack<NurPlanBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);
        properties.put("startDate", startDate);
        properties.put("endDate", endDate);
        properties.put("status", status);
        CommWebService.call("getQuestionRecord", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurPlanBean> parserUtil = new ParserUtil<>();
                NurPlanBean bean = parserUtil.parserResult(jsonStr, callBack, NurPlanBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
