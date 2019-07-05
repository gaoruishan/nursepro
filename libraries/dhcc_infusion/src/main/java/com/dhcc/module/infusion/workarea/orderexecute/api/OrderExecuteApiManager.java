package com.dhcc.module.infusion.workarea.orderexecute.api;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.orderexecute.OrdExecuteBean;

/**
 * @author:gaoruishan
 * @date:202019-07-04/15:20
 * @email:grs0515@163.com
 */
public class OrderExecuteApiManager {

    public static void getScanMsg(String episodeId, String scanInfo, final CommonCallBack<OrdExecuteBean> callBack) {
        OrderExecuteApiService.getOrdersMsg(episodeId, scanInfo, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OrdExecuteBean> parserUtil = new ParserUtil<>();
                OrdExecuteBean bean = parserUtil.parserResult(jsonStr, callBack, OrdExecuteBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public static void getOrder(String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime, CommonCallBack callBack) {

    }
}
