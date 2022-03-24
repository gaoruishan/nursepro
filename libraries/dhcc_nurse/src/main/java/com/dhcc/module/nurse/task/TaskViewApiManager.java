package com.dhcc.module.nurse.task;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.nurse.task.bean.NormalOrdTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdRecordTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdTaskBean;
import com.dhcc.module.nurse.task.bean.NurRateTaskBean;
import com.dhcc.module.nurse.task.bean.NurTaskSchBean;
import com.dhcc.module.nurse.task.bean.OrderExecResultBean;
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
public class TaskViewApiManager extends NurseAPI {
    /**
     * Description:   获取任务总览列表
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getTaskList(String regNo, String startDate, String endDate, String bedStr, final CommonCallBack<TaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addGroupId(properties);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("startDate", startDate.substring(0, 10));
        properties.put("startTime", startDate.substring(11, 16));
        properties.put("endDate", endDate.substring(0, 10));
        properties.put("endTime", endDate.substring(11, 16));
        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);

        CommWebService.call(getExecuteSummaryData, properties, new ServiceCallBack() {
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
    public static void getNormalTaskList(String regNo, String startDate, String endDate, String bedStr, final CommonCallBack<NormalOrdTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addGroupId(properties);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("startDate", startDate.substring(0, 10));
        properties.put("startTime", startDate.substring(11, 16));
        properties.put("endDate", endDate.substring(0, 10));
        properties.put("endTime", endDate.substring(11, 16));
        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);

        CommWebService.call(getNormalOrdTask, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NormalOrdTaskBean> parserUtil = new ParserUtil<>();
                NormalOrdTaskBean bean = parserUtil.parserResult(jsonStr, callBack, NormalOrdTaskBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:   获取常规治疗执行
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getNormalTaskExecResult(String oeoreId, final CommonCallBack<OrderExecResultBean> callBack) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("speed", "");
        properties.put("scanFlag", "0");
        properties.put("batch", "");
        properties.put("auditUserCode", "");
        properties.put("auditUserPass", "");
        properties.put("oeoreId", oeoreId);
        properties.put("execStatusCode", "F");
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId", spUtils.getString(SharedPreference.LOCID));
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("barCode", "");


        CommWebService.call(execOrSeeOrder, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OrderExecResultBean> parserUtil = new ParserUtil<>();
                OrderExecResultBean bean = parserUtil.parserResult(jsonStr, callBack, OrderExecResultBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:   获取体征任务列表
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getTempTaskList(String regNo, String needCodes, String startDate, String endDate, String bedStr, final CommonCallBack<TempTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("startDate", startDate.substring(0, 10));
        properties.put("startTime", startDate.substring(11, 16));
        properties.put("endDate", endDate.substring(0, 10));
        properties.put("endTime", endDate.substring(11, 16));
        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);
        properties.put("needCodes", needCodes);

        CommWebService.call(GetTempDateMeasureByDay, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<TempTaskBean> parserUtil = new ParserUtil<>();
                TempTaskBean bean = parserUtil.parserResult(jsonStr, callBack, TempTaskBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:   获取护嘱任务列表
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getNurOrdTaskList(String startDate, String endDate, String regNo, String taskStatus, String intType, String bedStr, final CommonCallBack<NurOrdTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);
        properties.put("taskStatus", taskStatus);
        properties.put("intType", intType);
        properties.put("startDate", startDate.substring(0, 10));
        properties.put("startTime", startDate.substring(11, 16));
        properties.put("endDate", endDate.substring(0, 10));
        properties.put("endTime", endDate.substring(11, 16));
        CommWebService.call(getNurPlanTaskList, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurOrdTaskBean> parserUtil = new ParserUtil<>();
                NurOrdTaskBean bean = parserUtil.parserResult(jsonStr, callBack, NurOrdTaskBean.class);
                if (bean == null) {
                    return;
                }
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
    public static void getExecuteTaskList(String recordId, String interventionDR, final CommonCallBack<NurOrdRecordTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        properties.put("recordId", recordId);
        properties.put("interventionDR", interventionDR);
        CommWebService.call(getExecuteTaskList, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurOrdRecordTaskBean> parserUtil = new ParserUtil<>();
                NurOrdRecordTaskBean bean = parserUtil.parserResult(jsonStr, callBack, NurOrdRecordTaskBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:   获取护理计划筛选配置
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void getNurTaskSch(final CommonCallBack<NurTaskSchBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        CommWebService.call(getNurTaskSch, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurTaskSchBean> parserUtil = new ParserUtil<>();
                NurTaskSchBean bean = parserUtil.parserResult(jsonStr, callBack, NurTaskSchBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:   获取护理计划执行
     * Input：
     * Output：
     * Others：
     * @param callBack
     */
    public static void executeNurTask(String taskRecordId, String executedContent, String taskJson, String transferFlag, String recordData, final CommonCallBack<NurTaskSchBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("taskRecordId", taskRecordId);
        properties.put("executedContent", executedContent);
        properties.put("taskJson", taskJson);
        properties.put("transferFlag", transferFlag);
        String sData = recordData.replaceAll("\\\\", "");
        properties.put("recordData", sData);
        CommWebService.call(executeNurTask, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurTaskSchBean> parserUtil = new ParserUtil<>();
                NurTaskSchBean bean = parserUtil.parserResult(jsonStr, callBack, NurTaskSchBean.class);
                if (bean == null) {
                    return;
                }
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
    public static void getScaninfo(String regNo, final CommonCallBack<ScanResultBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        properties.put("regNo", regNo);
        CommWebService.call(getPatWristInfo, properties, new ServiceCallBack() {
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

    /**
     * 获取评估任务
     */
    public static void getNeedEmr(String bedStr, String schDate, CommonCallBack<NurRateTaskBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("regNo", "");
        properties.put("bedStr", bedStr);
        properties.put("schDate", schDate);
        CommWebService.call(getNeedEmr, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurRateTaskBean> parserUtil = new ParserUtil<>();
                NurRateTaskBean bean = parserUtil.parserResult(jsonStr, callBack, NurRateTaskBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
