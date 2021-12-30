package com.dhcc.nursepro.workarea.taskmanage;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPStaticUtils;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.taskmanage.bean.TaskManageBean;
import com.dhcc.nursepro.workarea.taskmanage.bean.TaskManageRequest;
import com.dhcc.res.infusion.CustomDateTimeView;

/**
 * @author:gaoruishan
 * @date:202020-05-11/15:37
 * @email:grs0515@163.com
 */
public class TaskManageApi extends NurseAPI {

    /// Description:     获取医嘱任务
    public static void getOrderTasks(TaskManageRequest.GetOrderTasks request, CommonCallBack<TaskManageBean> callBack) {

        CommWebService.call(getOrderTasks, request.getProperties(), new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<TaskManageBean> parserUtil = new ParserUtil<>();
                TaskManageBean bean = parserUtil.parserResult(jsonStr, callBack, TaskManageBean.class);
                if (bean == null)
                    return;
                parserUtil.parserStatus(bean, callBack);
            }
        });

    }

    public static void getOrder(TaskManageRequest.GetOrder request, OrderExecuteApiManager.GetOrderCallback callback) {

        String s = SPStaticUtils.getString(CustomDateTimeView.SP_CUSTOM_DATE_START);
        if (s.contains(" ")) {
            request.startDate = s.split(" ")[0];
            request.startTime = s.split(" ")[1];
        }
        String s1 = SPStaticUtils.getString(CustomDateTimeView.SP_CUSTOM_DATE_END);
        if (s1.contains(" ")) {
            request.endDate = s1.split(" ")[0];
            request.endTime = s1.split(" ")[1];
        }
        request.screenParts = "";
        request.bedStr = "";
        request.pageNo = "1";

        OrderExecuteApiManager.getOrder("1", request.regNo, request.sheetCode, request.startDate, request.startTime, request.endDate, request.endTime, request.screenParts, callback);
    }

    public static void execOrSeeOrder(TaskManageRequest.ExecOrSeeOrder request, final OrderExecuteApiManager.ExecOrSeeOrderCallback callback) {
        request.speed = "";
        request.barCode = "";
        OrderExecuteApiManager.execOrSeeOrder(request.speed, request.barCode, request.scanFlag, request.batch, request.auditUserCode, request.auditUserPass, request.oeoreId, request.execStatusCode, "","", callback, request.getProperties());
    }
}
