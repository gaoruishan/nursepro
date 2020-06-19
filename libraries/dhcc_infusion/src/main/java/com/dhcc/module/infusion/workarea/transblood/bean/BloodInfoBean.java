package com.dhcc.module.infusion.workarea.transblood.bean;

import android.text.TextUtils;

import com.dhcc.module.infusion.workarea.OrdState;

public class BloodInfoBean extends BloodConfig{
    /**
     * bedCode : 01
     * bldTyp : AB型
     * bloodBagNo : 6
     * bloodPref : 6
     * bloodProducDesc : 新鲜冰冻血浆 400cc
     * bloodProductNo : 6
     * bloodRowId : 6
     * endType : Z
     * episodeId : 94
     * operateFlag :
     * patBldTyp : AB型
     * patName : 王伟测试
     * patWard : 5
     * reciveDate : 2018-09-20
     * reciveTime : 09:03:53
     * reciveUser : 护士01
     * sex : 男
     * status : 输血结束
     * stopReason : 其他
     * tStartReason :
     * transEdDate : 2018-09-20
     * transEdTime : 09:04:37
     * transEdUser : innurse
     * transEndFlag :
     * transFirstUser : 护士01
     * transRecFlag :
     * transRecycleDate :
     * transRecycleTime :
     * transRecycleUser :
     * transSecondUser : 护士02
     * transStFlag :
     * transStartDate : 2018-09-20
     * transStartTime : 09:04:19
     */

    private String bedCode;
    //同意书
    private String agreeStatus;
    //血制品类型
    private String bldTyp;
    //血袋号
    private String bloodBagNo;
    private String bloodPref;
    //血制品描述
    private String bloodProducDesc;
    //血制品号
    private String bloodProductNo;
    //有效时间
    private String validTime;
    //血量
    private String bloodMl;
    //配血结果
    private String bloodMatchResults;
    private String bloodRowId;
    private String endType;
    private String episodeId;
    private String operateFlag;
    //病人血类型
    private String patBldTyp;
    private String patName;
    private String patWard;
    //签收时间/人
    private String reciveDate;
    private String reciveTime;
    private String reciveFirstUser;
    private String reciveSecondUser;
    private String sex;
    private String status;
    private String stopReason;
    private String tStartReason;
    //结束时间/人
    private String transEdDate;
    private String transEdTime;
    private String transEdUser;
    private String transEndFlag;
    private String transRecFlag;
    private String transRecycleDate;
    private String transRecycleTime;
    private String transRecycleUser;
    private String transStFlag;
    //开始时间/人
    private String transFirstUser;
    private String transSecondUser;
    private String transStartDate;
    private String transStartTime;

    public String getReciveFirstUser() {
        return reciveFirstUser == null ? "" : reciveFirstUser;
    }

    public void setReciveFirstUser(String reciveFirstUser) {
        this.reciveFirstUser = reciveFirstUser;
    }

    public String getReciveSecondUser() {
        return reciveSecondUser == null ? "" : reciveSecondUser;
    }

    public void setReciveSecondUser(String reciveSecondUser) {
        this.reciveSecondUser = reciveSecondUser;
    }

    public String getValidTime() {
        return validTime == null ? "" : validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getBloodMl() {
        return bloodMl == null ? "" : bloodMl;
    }

    public void setBloodMl(String bloodMl) {
        this.bloodMl = bloodMl;
    }

    public String getBloodMatchResults() {
        return bloodMatchResults == null ? "" : bloodMatchResults;
    }

    public void setBloodMatchResults(String bloodMatchResults) {
        this.bloodMatchResults = bloodMatchResults;
    }

    public String getAgreeStatus() {
        return agreeStatus == null ? "" : agreeStatus;
    }

    public void setAgreeStatus(String agreeStatus) {
        this.agreeStatus = agreeStatus;
    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getBldTyp() {
        return bldTyp;
    }

    public void setBldTyp(String bldTyp) {
        this.bldTyp = bldTyp;
    }

    public String getBloodBagNo() {
        return bloodBagNo;
    }

    public void setBloodBagNo(String bloodBagNo) {
        this.bloodBagNo = bloodBagNo;
    }

    public String getBloodPref() {
        return bloodPref;
    }

    public void setBloodPref(String bloodPref) {
        this.bloodPref = bloodPref;
    }

    public String getBloodProducDesc() {
        return bloodProducDesc;
    }

    public void setBloodProducDesc(String bloodProducDesc) {
        this.bloodProducDesc = bloodProducDesc;
    }

    public String getBloodProductNo() {
        return bloodProductNo;
    }

    public void setBloodProductNo(String bloodProductNo) {
        this.bloodProductNo = bloodProductNo;
    }

    public String getBloodRowId() {
        return bloodRowId;
    }

    public void setBloodRowId(String bloodRowId) {
        this.bloodRowId = bloodRowId;
    }

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getOperateFlag() {
        return operateFlag;
    }

    public void setOperateFlag(String operateFlag) {
        this.operateFlag = operateFlag;
    }

    public String getPatBldTyp() {
        return patBldTyp;
    }

    public void setPatBldTyp(String patBldTyp) {
        this.patBldTyp = patBldTyp;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatWard() {
        return patWard;
    }

    public void setPatWard(String patWard) {
        this.patWard = patWard;
    }

    public String getReciveDate() {
        return reciveDate == null ? "" : reciveDate;
    }

    public void setReciveDate(String reciveDate) {
        this.reciveDate = reciveDate;
    }

    public String getReciveTime() {
        return reciveTime == null ? "" : reciveTime;
    }

    public void setReciveTime(String reciveTime) {
        this.reciveTime = reciveTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        //结束人和日期
        if (!TextUtils.isEmpty(transEdUser) && !TextUtils.isEmpty(transEdDate)) {
            status = OrdState.STATE_BLOOD_E[0];
            endType = OrdState.TYPE_BLOOD_E;
            return status;
        }
        //输液中
        if (!TextUtils.isEmpty(transFirstUser) && !TextUtils.isEmpty(transStartDate)) {
            status = OrdState.STATE_BLOOD_S[0];
            return status;
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public String getTStartReason() {
        return tStartReason;
    }

    public void setTStartReason(String tStartReason) {
        this.tStartReason = tStartReason;
    }

    public String getTransEdDate() {
        return transEdDate == null ? "" : transEdDate;
    }

    public void setTransEdDate(String transEdDate) {
        this.transEdDate = transEdDate;
    }

    public String getTransEdTime() {
        return transEdTime == null ? "" : transEdTime;
    }

    public void setTransEdTime(String transEdTime) {
        this.transEdTime = transEdTime;
    }

    public String getTransEdUser() {
        return transEdUser;
    }

    public void setTransEdUser(String transEdUser) {
        this.transEdUser = transEdUser;
    }

    public String getTransEndFlag() {
        return transEndFlag;
    }

    public void setTransEndFlag(String transEndFlag) {
        this.transEndFlag = transEndFlag;
    }

    public String getTransFirstUser() {
        return transFirstUser;
    }

    public void setTransFirstUser(String transFirstUser) {
        this.transFirstUser = transFirstUser;
    }

    public String getTransRecFlag() {
        return transRecFlag;
    }

    public void setTransRecFlag(String transRecFlag) {
        this.transRecFlag = transRecFlag;
    }

    public String getTransRecycleDate() {
        return transRecycleDate;
    }

    public void setTransRecycleDate(String transRecycleDate) {
        this.transRecycleDate = transRecycleDate;
    }

    public String getTransRecycleTime() {
        return transRecycleTime;
    }

    public void setTransRecycleTime(String transRecycleTime) {
        this.transRecycleTime = transRecycleTime;
    }

    public String getTransRecycleUser() {
        return transRecycleUser;
    }

    public void setTransRecycleUser(String transRecycleUser) {
        this.transRecycleUser = transRecycleUser;
    }

    public String getTransSecondUser() {
        return transSecondUser;
    }

    public void setTransSecondUser(String transSecondUser) {
        this.transSecondUser = transSecondUser;
    }

    public String getTransStFlag() {
        return transStFlag;
    }

    public void setTransStFlag(String transStFlag) {
        this.transStFlag = transStFlag;
    }

    public String getTransStartDate() {
        return transStartDate == null ? "" : transStartDate;
    }

    public void setTransStartDate(String transStartDate) {
        this.transStartDate = transStartDate;
    }

    public String getTransStartTime() {
        return transStartTime == null ? "" : transStartTime;
    }

    public void setTransStartTime(String transStartTime) {
        this.transStartTime = transStartTime;
    }
}