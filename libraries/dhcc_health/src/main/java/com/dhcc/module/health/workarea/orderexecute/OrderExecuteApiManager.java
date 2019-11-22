package com.dhcc.module.health.workarea.orderexecute;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.health.workarea.orderexecute.bean.OrdExecuteBean;

import java.util.HashMap;

/**
 * 医嘱执行
 * @author:gaoruishan
 * @date:202019-11-22/11:18
 * @email:grs0515@163.com
 */
public class OrderExecuteApiManager {

    /**
     * 获取扫码信息
     */
    public static void getHealthOrdInfo(String regNo, String stDate, String enDate,String exeFlag, final CommonCallBack<OrdExecuteBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("regNo", regNo);
      //  properties.put("exeFlag", exeFlag);
        properties.put("startDate", stDate);
        properties.put("endDate", enDate);
        CommWebService.call("getHealthOrdInfo", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OrdExecuteBean> parserUtil = new ParserUtil<>();
                OrdExecuteBean bean = parserUtil.parserResult(jsonStr, callBack, OrdExecuteBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
