package com.dhcc.module.nurse.task;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.nurse.task.bean.NormalOrdTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdRecordTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdTaskBean;
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
        properties.put("startDate", startDate.substring(0,10));
        properties.put("startTime", startDate.substring(11,16));
        properties.put("endDate", endDate.substring(0,10));
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
        properties.put("startDate", startDate.substring(0,10));
        properties.put("startTime", startDate.substring(11,16));
        properties.put("endDate", endDate.substring(0,10));
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
     * Description:   获取护理计划列表
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getNurOrdTaskList(String regNo,String needCodes,String date ,String bedStr ,final CommonCallBack<NurOrdTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);
        properties.put("taskStatus", "0");
        properties.put("intType", "");
        String startDate="2020-07-11 00:00:00";
        String endDate="2020-08-11 23:59:00";
        properties.put("startDate", startDate.substring(0,10));
        properties.put("startTime", startDate.substring(11,16));
        properties.put("endDate", endDate.substring(0,10));
        properties.put("endTime", endDate.substring(11,16));
        CommWebService.call("getNurPlanTaskList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurOrdTaskBean> parserUtil = new ParserUtil<>();
                NurOrdTaskBean bean = parserUtil.parserResult(jsonStr, callBack, NurOrdTaskBean.class);
                if (bean == null) {return;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * Description:   获取护理计划录入配置
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getExecuteTaskList(String recordId,String interventionDR,final CommonCallBack<NurOrdRecordTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        properties.put("recordId", recordId);
        properties.put("interventionDR", interventionDR);
        CommWebService.call("getExecuteTaskList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurOrdRecordTaskBean> parserUtil = new ParserUtil<>();
                NurOrdRecordTaskBean bean = parserUtil.parserResult(jsonStr, callBack, NurOrdRecordTaskBean.class);
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
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
