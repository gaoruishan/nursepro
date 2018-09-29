package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean;

import java.util.List;

/**
 * BloodOperationListBean
 *
 * @author DevLix126
 * @date 2018/9/20
 */
public class BloodOperationListBean {

    /**
     * bloodList : [{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"6","bloodPref":"6","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"6","bloodRowId":"6","endType":"Z","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"09:03:53","reciveUser":"护士01","sex":"男","status":"输血结束","stopReason":"其他","tStartReason":"","transEdDate":"2018-09-20","transEdTime":"09:04:37","transEdUser":"innurse","transEndFlag":"","transFirstUser":"护士01","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"护士02","transStFlag":"","transStartDate":"2018-09-20","transStartTime":"09:04:19"},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"7","bloodPref":"7","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"7","bloodRowId":"7","endType":"Z","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"09:11:19","reciveUser":"护士01","sex":"男","status":"输血结束","stopReason":"其他","tStartReason":"","transEdDate":"2018-09-20","transEdTime":"09:13:23","transEdUser":"innurse","transEndFlag":"","transFirstUser":"护士01","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"innurse","transStFlag":"","transStartDate":"2018-09-20","transStartTime":"09:12:26"},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"8","bloodPref":"8","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"8","bloodRowId":"8","endType":"","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"09:11:26","reciveUser":"护士01","sex":"男","status":"正在输血","stopReason":"","tStartReason":"","transEdDate":"","transEdTime":"","transEdUser":"","transEndFlag":"","transFirstUser":"innurse","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"护士01","transStFlag":"","transStartDate":"2018-09-20","transStartTime":"09:12:40"},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"9","bloodPref":"9","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"9","bloodRowId":"9","endType":"","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"09:11:31","reciveUser":"护士01","sex":"男","status":"签收","stopReason":"","tStartReason":"","transEdDate":"","transEdTime":"","transEdUser":"","transEndFlag":"","transFirstUser":"","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"","transStFlag":"","transStartDate":"","transStartTime":""},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"10","bloodPref":"10","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"10","bloodRowId":"10","endType":"","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"09:11:37","reciveUser":"护士01","sex":"男","status":"签收","stopReason":"","tStartReason":"","transEdDate":"","transEdTime":"","transEdUser":"","transEndFlag":"","transFirstUser":"","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"","transStFlag":"","transStartDate":"","transStartTime":""},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"11","bloodPref":"11","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"11","bloodRowId":"11","endType":"","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"09:11:44","reciveUser":"护士01","sex":"男","status":"签收","stopReason":"","tStartReason":"","transEdDate":"","transEdTime":"","transEdUser":"","transEndFlag":"","transFirstUser":"","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"","transStFlag":"","transStartDate":"","transStartTime":""},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"12","bloodPref":"12","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"12","bloodRowId":"12","endType":"E","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"11:47:56","reciveUser":"护士01","sex":"男","status":"输血结束","stopReason":"","tStartReason":"","transEdDate":"2018-09-20","transEdTime":"11:50:07","transEdUser":"innurse","transEndFlag":"","transFirstUser":"innurse","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"护士01","transStFlag":"","transStartDate":"2018-09-20","transStartTime":"11:49:09"},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"13","bloodPref":"13","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"13","bloodRowId":"13","endType":"Z","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"15:00:12","reciveUser":"护士01","sex":"男","status":"输血结束","stopReason":"其他","tStartReason":"","transEdDate":"2018-09-20","transEdTime":"15:01:24","transEdUser":"innurse","transEndFlag":"","transFirstUser":"innurse","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"护士01","transStFlag":"","transStartDate":"2018-09-20","transStartTime":"15:00:30"},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"14","bloodPref":"14","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"14","bloodRowId":"14","endType":"E","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"15:20:50","reciveUser":"护士01","sex":"男","status":"输血结束","stopReason":"","tStartReason":"","transEdDate":"2018-09-20","transEdTime":"16:21:22","transEdUser":"innurse","transEndFlag":"","transFirstUser":"","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"","transStFlag":"","transStartDate":"","transStartTime":""},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"15","bloodPref":"15","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"15","bloodRowId":"15","endType":"","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"15:27:21","reciveUser":"护士01","sex":"男","status":"正在输血","stopReason":"","tStartReason":"","transEdDate":"","transEdTime":"","transEdUser":"","transEndFlag":"","transFirstUser":"护士01","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"护士02","transStFlag":"","transStartDate":"2018-09-21","transStartTime":"16:42:57"},{"bedCode":"01","bldTyp":"AB型","bloodBagNo":"16","bloodPref":"16","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"16","bloodRowId":"16","endType":"E","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"16:29:20","reciveUser":"护士01","sex":"男","status":"已回收","stopReason":"","tStartReason":"","transEdDate":"2018-09-20","transEdTime":"16:30:27","transEdUser":"innurse","transEndFlag":"","transFirstUser":"innurse","transRecFlag":"","transRecycleDate":"2018-09-21","transRecycleTime":"16:47:01","transRecycleUser":"护士01","transSecondUser":"护士01","transStFlag":"","transStartDate":"2018-09-20","transStartTime":"16:29:38"}]
     * msg : 成功!
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<BloodListBean> bloodList;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BloodListBean> getBloodList() {
        return bloodList;
    }

    public void setBloodList(List<BloodListBean> bloodList) {
        this.bloodList = bloodList;
    }

    public static class BloodListBean {
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
        private String bldTyp;
        private String bloodBagNo;
        private String bloodPref;
        private String bloodProducDesc;
        private String bloodProductNo;
        private String bloodRowId;
        private String endType;
        private String episodeId;
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
    }
}
