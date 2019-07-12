package com.dhcc.module.infusion.workarea.orderexecute.api;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrdScanInfoBean;

/**
 * @author:gaoruishan
 * @date:202019-07-04/15:20
 * @email:grs0515@163.com
 */
public class OrderExecuteApiManager {

    public static void getScanInfo(String episodeId, String scanInfo, final CommonCallBack<OrdScanInfoBean> callBack) {
        OrderExecuteApiService.getScanInfo(episodeId, scanInfo, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OrdScanInfoBean> parserUtil = new ParserUtil<>();
                OrdScanInfoBean bean = parserUtil.parserResult(jsonStr, callBack, OrdScanInfoBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public static void getOrder(String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime, final CommonCallBack<OrderExecuteBean> callBack) {
        OrderExecuteApiService.getOrder(regNo, sheetCode, startDate, startTime, endDate, endTime, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OrderExecuteBean> parserUtil = new ParserUtil<>();
                OrderExecuteBean bean = parserUtil.parserResult(jsonStr, callBack, OrderExecuteBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
