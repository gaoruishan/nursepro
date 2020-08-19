package com.dhcc.module.nurse.nurplan;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.nurse.nurplan.bean.NurPlanAddBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202020-08-17/11:27
 * @email:grs0515@163.com
 */
public class NurPlanApiManager {
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
}
