package com.dhcc.module.nurse.nurplan.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-17/11:43
 * @email:grs0515@163.com
 */
public class NurPlanBean extends CommResult {

    private List<QuestionListBean> questionList;
    private List<StatusReasonListBean> revokeReasonList;
    private List<StatusReasonListBean> statusList;
    private List<StatusReasonListBean> stopReasonList;

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
