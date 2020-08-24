package com.dhcc.module.nurse.nurplan;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.utils.DataCache;
import com.dhcc.module.nurse.nurplan.bean.InterveFreqBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanAddBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanAddInterveBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanGoalBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanInterveBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202020-08-17/11:27
 * @email:grs0515@163.com
 */
public class NurPlanApiManager {

    public static final String saveGoalRecord = "saveGoalRecord";
    public static final String saveIntervRecord = "saveIntervRecord";
    /**
    * 护理问题记录
    */
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
    /**
     * 撤销护理问题
     */
    public static void cancelStopQuestion(String method,String episodeId, String reason, String recordId, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        properties.put("episodeId", episodeId);
        properties.put("reason", reason);
        properties.put("recordId", recordId);
        CommWebService.call(method, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
               CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
    /**
     * 护理问题列表
     */
    public static void getQuestionList(String episodeId, String qustionName, String qustionTypes, CommonCallBack<NurPlanAddBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        properties.put("episodeId", episodeId);
        properties.put("qustionName", qustionName);
        properties.put("qustionTypes", qustionTypes);
        CommWebService.call("getQuestionList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurPlanAddBean> parserUtil = new ParserUtil<>();
                NurPlanAddBean bean = parserUtil.parserResult(jsonStr, callBack, NurPlanAddBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 保存护理问题
     */
    public static void saveQuestionRecord(String episodeId, String dataArr, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addWardId(properties);
        properties.put("episodeId", episodeId);
        properties.put("dataArr", dataArr);
        CommWebService.call("saveQuestionRecord", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
    /**
     * 评价问题
     */
    public static void saveQuestionComments(String episodeId, String questionSub, String status, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        properties.put("episodeId", episodeId);
        properties.put("questionSub", questionSub);
        properties.put("status", status);
        CommWebService.call("saveQuestionComments", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
    /**
     * 目标列表
     */
    public static void getGoalByQestId(String planId, String questSub, CommonCallBack<NurPlanGoalBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("planId", planId);
        properties.put("questSub", questSub);
        CommWebService.call("getGoalByQestId", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurPlanGoalBean> parserUtil = new ParserUtil<>();
                NurPlanGoalBean bean = parserUtil.parserResult(jsonStr, callBack, NurPlanGoalBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 措施列表
     */
    public static void getInterventionByQestId(String planId, String questSub, CommonCallBack<NurPlanInterveBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("planId", planId);
        properties.put("questSub", questSub);
        CommWebService.call("getInterventionByQestId", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurPlanInterveBean> parserUtil = new ParserUtil<>();
                NurPlanInterveBean bean = parserUtil.parserResult(jsonStr, callBack, NurPlanInterveBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * 保存目的/措施
     */
    public static void saveGoalIntervRecord(String method,String planId, String questSub, String dataArr, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addWardId(properties);
        CommWebService.addLocId(properties);
        properties.put("planId", planId);
        properties.put("questSub", questSub);
        properties.put("dataArr", dataArr);
        CommWebService.call(method, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }

    /**
     * 获取频次
     */
    public static void getInterventionFreq() {
        CommonCallBack<InterveFreqBean> callBack = new CommonCallBack<InterveFreqBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(InterveFreqBean bean, String type) {
                //保存
                DataCache.saveJson(bean,InterveFreqBean.class.getSimpleName());
            }
        };
        CommWebService.call("getInterventionFreq", null, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<InterveFreqBean> parserUtil = new ParserUtil<>();
                InterveFreqBean bean = parserUtil.parserResult(jsonStr, callBack, InterveFreqBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * 停止措施
     */
    public static void stopInterventions(String intRecordIds, String stopDate, String stopTime, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        properties.put("intRecordIds", intRecordIds);
        properties.put("stopDate", stopDate);
        properties.put("stopTime", stopTime);
        CommWebService.call("stopInterventions", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
    /**
     * 撤销措施
     */
    public static void cancelInterventions(String intRecordIds, String cancelReason, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        properties.put("intRecordIds", intRecordIds);
        properties.put("cancelReason", cancelReason);
        CommWebService.call("cancelInterventions", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
    /**
     * 获取新增措施列表
     */
    public static void getInterventionList(String planId, String questionSub, String searchName, CommonCallBack<NurPlanAddInterveBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("planId", planId);
        properties.put("questionSub", questionSub);
        properties.put("searchName", searchName);
        CommWebService.call("getInterventionList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NurPlanAddInterveBean> parserUtil = new ParserUtil<>();
                NurPlanAddInterveBean bean = parserUtil.parserResult(jsonStr, callBack, NurPlanAddInterveBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * 保存新增措施
     */
    public static void saveNewInterventionList(String planId, String questionSub, String inRowId, String freqDr, String weekStr, String startDate, String startTime, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        CommWebService.addWardId(properties);
        properties.put("planId", planId);
        properties.put("questionSub", questionSub);
        properties.put("inRowId", inRowId);
        properties.put("freqDr", freqDr);
        properties.put("weekStr", weekStr);
        properties.put("startDate", startDate);
        properties.put("startTime", startTime);
        CommWebService.call("saveNewInterventionList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
}
