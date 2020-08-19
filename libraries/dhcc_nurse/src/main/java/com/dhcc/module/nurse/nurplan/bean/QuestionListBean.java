package com.dhcc.module.nurse.nurplan.bean;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class QuestionListBean {

    /**
     * assessList : []
     * cStatus : 未评价
     * cancelDateTime : 2020-08-14 14:57:35
     * cancelReason : 误操作
     * cancelUser : 护士01
     * color : #FF0000
     * createDateTime : 2020-08-13 20:34:36
     * expireFlag : 计划到期
     * planStatus : 计划未制定
     * queName : 心跳过慢
     * queRowId : 15
     * queStatus : 2
     * recordId : 15||9
     * result :
     * source : 手动新增
     * status : 已撤销
     * stopDateTime :
     * stopReason :
     * stopUser :
     */

    private String  assessList;
    private String cStatus;
    private String cancelDateTime;
    private String cancelReason;
    private String cancelUser;
    private String color;
    private String createDateTime;
    private String createUser;
    private String expireFlag;
    private String planStatus;
    private String queName;
    private String queRowId;
    private String queStatus;
    private String recordId;
    private String result;
    private String source;
    private String status;
    private String stopDateTime;
    private String stopReason;
    private String stopUser;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getCreateUser() {
        return createUser == null ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public List<AssessListBean> getAssessList() {
        if(!TextUtils.isEmpty(assessList)){
            Type type = new TypeToken<List<AssessListBean>>() {
            }.getType();
            return new Gson().fromJson(assessList, type);
        }
        return null;
    }



    public String getCStatus() {
        return cStatus;
    }

    public void setCStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getCancelDateTime() {
        return cancelDateTime;
    }

    public void setCancelDateTime(String cancelDateTime) {
        this.cancelDateTime = cancelDateTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(String cancelUser) {
        this.cancelUser = cancelUser;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getExpireFlag() {
        return expireFlag;
    }

    public void setExpireFlag(String expireFlag) {
        this.expireFlag = expireFlag;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getQueName() {
        return queName;
    }

    public void setQueName(String queName) {
        this.queName = queName;
    }

    public String getQueRowId() {
        return queRowId;
    }

    public void setQueRowId(String queRowId) {
        this.queRowId = queRowId;
    }

    public String getQueStatus() {
        return queStatus;
    }

    public void setQueStatus(String queStatus) {
        this.queStatus = queStatus;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStopDateTime() {
        return stopDateTime;
    }

    public void setStopDateTime(String stopDateTime) {
        this.stopDateTime = stopDateTime;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public String getStopUser() {
        return stopUser;
    }

    public void setStopUser(String stopUser) {
        this.stopUser = stopUser;
    }

    public static class AssessListBean {

        private String assessSystemName;
        private String assessContent;

        public String getAssessSystemName() {
            return assessSystemName == null ? "" : assessSystemName;
        }

        public void setAssessSystemName(String assessSystemName) {
            this.assessSystemName = assessSystemName;
        }

        public String getAssessContent() {
            return assessContent == null ? "" : assessContent;
        }

        public void setAssessContent(String assessContent) {
            this.assessContent = assessContent;
        }
    }
}