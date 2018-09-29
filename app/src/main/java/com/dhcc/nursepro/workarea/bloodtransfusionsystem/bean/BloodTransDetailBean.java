package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean;

import java.util.List;

/**
 * BloodTransDetailBean
 *
 * @author DevLix126
 * @date 2018/9/29
 */
public class BloodTransDetailBean {

    /**
     * bedCode : 01
     * bldTyp : AB型
     * bloodBagNo : 30
     * bloodPref : 30
     * bloodProducDesc : 新鲜冰冻血浆 400cc
     * bloodProductNo : 30
     * endType : Z
     * episodeId : 94
     * msg : 成功!
     * msgcode : 999999
     * operateFlag :
     * patBldTyp : AB型
     * patName : 王伟测试
     * patWard : 5
     * patrolRecord : [{"date":"2018-09-29","effect":"无","id":"16","situation":"","speed":"20","time":"10:50","user":"护士01"},{"date":"2018-09-29","effect":"有","id":"17","situation":"qertyy","speed":"21","time":"10:51","user":"护士01"}]
     * reciveDate : 2018-09-26
     * reciveTime : 16:59:30
     * reciveUser : 护士01
     * sex : 男
     * status : 0
     * stopReason : 过敏
     * tStartReason :
     * transEdDate : 2018-09-29
     * transEdTime : 11:08:07
     * transEdUser :
     * transEndFlag :
     * transFirstUser : 护士01
     * transRecFlag :
     * transRecycleDate : 2018-09-29
     * transRecycleTime : 11:08:15
     * transRecycleUser : 护士01
     * transSecondUser : 护士02
     * transStFlag :
     * transStartDate : 2018-09-29
     * transStartTime : 10:29:39
     */

    private String bedCode;
    private String bldTyp;
    private String bloodBagNo;
    private String bloodPref;
    private String bloodProducDesc;
    private String bloodProductNo;
    private String endType;
    private String episodeId;
    private String msg;
    private String msgcode;
    private String operateFlag;
    private String patBldTyp;
    private String patName;
    private String patWard;
    private String reciveDate;
    private String reciveTime;
    private String reciveUser;
    private String sex;
    private String status;
    private String stopReason;
    private String tStartReason;
    private String transEdDate;
    private String transEdTime;
    private String transEdUser;
    private String transEndFlag;
    private String transFirstUser;
    private String transRecFlag;
    private String transRecycleDate;
    private String transRecycleTime;
    private String transRecycleUser;
    private String transSecondUser;
    private String transStFlag;
    private String transStartDate;
    private String transStartTime;
    private List<PatrolRecordBean> patrolRecord;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
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
        return reciveDate;
    }

    public void setReciveDate(String reciveDate) {
        this.reciveDate = reciveDate;
    }

    public String getReciveTime() {
        return reciveTime;
    }

    public void setReciveTime(String reciveTime) {
        this.reciveTime = reciveTime;
    }

    public String getReciveUser() {
        return reciveUser;
    }

    public void setReciveUser(String reciveUser) {
        this.reciveUser = reciveUser;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
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
        return transEdDate;
    }

    public void setTransEdDate(String transEdDate) {
        this.transEdDate = transEdDate;
    }

    public String getTransEdTime() {
        return transEdTime;
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
        return transStartDate;
    }

    public void setTransStartDate(String transStartDate) {
        this.transStartDate = transStartDate;
    }

    public String getTransStartTime() {
        return transStartTime;
    }

    public void setTransStartTime(String transStartTime) {
        this.transStartTime = transStartTime;
    }

    public List<PatrolRecordBean> getPatrolRecord() {
        return patrolRecord;
    }

    public void setPatrolRecord(List<PatrolRecordBean> patrolRecord) {
        this.patrolRecord = patrolRecord;
    }

    public static class PatrolRecordBean {
        /**
         * date : 2018-09-29
         * effect : 无
         * id : 16
         * situation :
         * speed : 20
         * time : 10:50
         * user : 护士01
         */

        private String date;
        private String effect;
        private String id;
        private String situation;
        private String speed;
        private String time;
        private String user;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSituation() {
            return situation;
        }

        public void setSituation(String situation) {
            this.situation = situation;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
