package com.dhcc.module.nurse.task;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.nurse.task.bean.NormalOrdTaskBean;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.module.nurse.task.bean.TaskBean;
import com.dhcc.module.nurse.task.bean.TempTaskBean;

import java.util.HashMap;

/**
 * com.dhcc.module.nurse.task
 * <p>
 * author Q
 * Date: 2020/8/3
 * Time:14:40
 */
public class TaskViewApiManager {
    /**
     * Description:   获取任务总览列表
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getTaskList(String regNo,String startDate,String endDate ,String bedStr ,final CommonCallBack<TaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addGroupId(properties);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("startDate", startDate.substring(0,11));
        properties.put("startTime", startDate.substring(11,16));
        properties.put("endDate", endDate.substring(0,11));
        properties.put("endTime", endDate.substring(11,16));
        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);

        CommWebService.call("getExecuteSummaryData", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<TaskBean> parserUtil = new ParserUtil<>();
                TaskBean bean = parserUtil.parserResult(jsonStr, callBack, TaskBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * Description:   获取常规治疗列表
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getNormalTaskList(String regNo,String startDate,String endDate ,String bedStr ,final CommonCallBack<NormalOrdTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addGroupId(properties);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("startDate", startDate.substring(0,11));
        properties.put("startTime", startDate.substring(11,16));
        properties.put("endDate", endDate.substring(0,11));
        properties.put("endTime", endDate.substring(11,16));
        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);

        CommWebService.call("getNormalOrdTask", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NormalOrdTaskBean> parserUtil = new ParserUtil<>();
                NormalOrdTaskBean bean = parserUtil.parserResult(jsonStr, callBack, NormalOrdTaskBean.class);
                if (bean == null) {return;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * Description:   获取常规治疗列表
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getTempTaskList(String regNo,String needCodes,String date ,String bedStr ,final CommonCallBack<TempTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("date", "2020-08-10");
        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);
        properties.put("needCodes", needCodes);

        CommWebService.call("GetTempDateMeasureByDay", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<TempTaskBean> parserUtil = new ParserUtil<>();
                TempTaskBean bean = parserUtil.parserResult(jsonStr, callBack, TempTaskBean.class);
                if (bean == null) {return;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * Description:   获取扫码信息
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getScaninfo(String regNo,final CommonCallBack<ScanResultBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        properties.put("regNo", regNo);
        CommWebService.call("getPatWristInfo", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<ScanResultBean> parserUtil = new ParserUtil<>();
                ScanResultBean bean = parserUtil.parserResult(jsonStr, callBack, ScanResultBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
