package com.dhcc.module.nurse.nurplan.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-17/11:43
 * @email:grs0515@163.com
 */
public class NurPlanBean extends CommResult {

    //问题列表
    private List<QuestionListBean> questionList;
    //撤回原因
    private List<StatusReasonListBean> revokeReasonList;
    //筛选
    private List<StatusReasonListBean> statusList;
    //停止原因
    private List<StatusReasonListBean> stopReasonList;
    //评价原因
    private List<StatusReasonListBean> resultList;

    public static QuestionListBean getSaveQuestionComments(List<QuestionListBean> data) {
        for (QuestionListBean datum : data) {
            if (datum.isSelect()) {
                return datum;
            }
        }
        return null;
    }

    public List<StatusReasonListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<StatusReasonListBean> resultList) {
        this.resultList = resultList;
    }

    public List<QuestionListBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionListBean> questionList) {
        this.questionList = questionList;
    }

    public List<StatusReasonListBean> getRevokeReasonList() {
        return revokeReasonList;
    }

    public void setRevokeReasonList(List<StatusReasonListBean> revokeReasonList) {
        this.revokeReasonList = revokeReasonList;
    }

    public List<StatusReasonListBean> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusReasonListBean> statusList) {
        this.statusList = statusList;
    }

    public List<StatusReasonListBean> getStopReasonList() {
        return stopReasonList;
    }

    public void setStopReasonList(List<StatusReasonListBean> stopReasonList) {
        this.stopReasonList = stopReasonList;
    }

}
