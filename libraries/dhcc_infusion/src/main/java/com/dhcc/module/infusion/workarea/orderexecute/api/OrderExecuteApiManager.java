package com.dhcc.module.infusion.workarea.orderexecute.api;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.comm.BaseApiManager;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrderExecuteBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-07-04/15:20
 * @email:grs0515@163.com
 */
public class OrderExecuteApiManager extends BaseApiManager {

    public static void getOrder(String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime, final CommonCallBack<OrderExecuteBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        CommWebService.addHospitalId(properties);
        CommWebService.addGroupId(properties);
        properties.put("regNo", regNo);
        properties.put("sheetCode", sheetCode);
        properties.put("startDate", startDate);
        properties.put("startTime", startTime);
        properties.put("endDate", endDate);
        properties.put("endTime", endTime);

        CommWebService.call(GetOrdList, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OrderExecuteBean> parserUtil = new ParserUtil<>();
                OrderExecuteBean bean = parserUtil.parserResult(jsonStr, callBack, OrderExecuteBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });

    }

    public static void execOrder(String oeoreId, String exeCode, String sheetCode, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("oeoreId", oeoreId);
        properties.put("sheetCode", sheetCode);
        properties.put("exeCode", exeCode);

        CommWebService.call(execOrder, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, callBack);
            }
        });
    }
}
