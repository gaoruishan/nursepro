package com.dhcc.module.nurse.outmanage.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/8/18/16:27
 * @email:grs0515@163.com
 */
public class OutManageBean extends CommResult {

    public static final String TYPE_IN = "In";
    public static final String TYPE_OUT = "Out";
    public static final String TYPE_EDIT = "Edit";
    /**
     * event : 1
     * iconFlag : 1
     * type : 入院
     */

    private List<ConfigTraceBean> configTrace;
    /**
     * age : 26岁
     * allOut : 0
     * arreag : 0
     * bedCode : 01
     * careLevel :
     * criticalValue : 0
     * dangerous : 0
     * docDisch : 0
     * epdFlag : 0
     * epdNotReport : 0
     * epdReport : 0
     * episodeId : 2887
     * fever : 0
     * gotAllergy :
     * illState :
     * inBedAll : 1
     * longOrd :
     * manageInBed : 0
     * name : 市医保15
     * newPatient : 0
     * operation : 0
     * regNo : 0000001271
     * seq : 1
     * sex : 女
     * tempOrd :
     * todayOut : 0
     * wait : 0
     */

    private List<PatInfoListBean> patInfoList;

    public List<ConfigTraceBean> getConfigTrace() {
        return configTrace;
    }

    public void setConfigTrace(List<ConfigTraceBean> configTrace) {
        this.configTrace = configTrace;
    }

    public List<PatInfoListBean> getPatInfoList() {
        return patInfoList;
    }

    public void setPatInfoList(List<PatInfoListBean> patInfoList) {
        this.patInfoList = patInfoList;
    }


    public static class PatInfoListBean {

        /**
         * age : 22岁
         * allOut : 1
         * arreag : 0
         * bedCode : 42
         * careLevel : 一级护理
         * criticalValue : 0
         * dangerous : 0
         * docDisch : 0
         * epdFlag : 0
         * epdNotReport : 0
         * epdReport : 0
         * episodeId : 3265
         * fever : 0
         * gotAllergy :
         * illState :
         * inBedAll : 0
         * longOrd :
         * manageInBed : 0
         * name : 玉柱
         * newPatient : 0
         * operation : 0
         * regNo : 0000001387
         * seq : 59
         * sex : 女
         * tempOrd :
         * todayOut : 0
         * wait : 0
         */

        private String age;
        private String allOut;
        private String arreag;
        private String bedCode;
        private String careLevel;
        private String criticalValue;
        private String dangerous;
        private String docDisch;
        private String epdFlag;
        private String epdNotReport;
        private String epdReport;
        private String episodeId;
        private String fever;
        private String gotAllergy;
        private String illState;
        private String inBedAll;
        private String longOrd;
        private String manageInBed;
        private String name;
        private String newPatient;
        private String operation;
        private String regNo;
        private String seq;
        private String sex;
        private String tempOrd;
        private String todayOut;
        private String wait;
        //是否外出
        private String outManageFlag;
        private String outDateTime;
        private String inDateTime;

        public String getOutDateTime() {
            return outDateTime == null ? "" : outDateTime;
        }

        public void setOutDateTime(String outDateTime) {
            this.outDateTime = outDateTime;
        }

        public String getInDateTime() {
            return inDateTime == null ? "" : inDateTime;
        }

        public void setInDateTime(String inDateTime) {
            this.inDateTime = inDateTime;
        }

        public String getOutManageFlag() {
            return outManageFlag == null ? "" : outManageFlag;
        }

        public void setOutManageFlag(String outManageFlag) {
            this.outManageFlag = outManageFlag;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAllOut() {
            return allOut;
        }

        public void setAllOut(String allOut) {
            this.allOut = allOut;
        }

        public String getArreag() {
            return arreag;
        }

        public void setArreag(String arreag) {
            this.arreag = arreag;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getCareLevel() {
            return careLevel;
        }

        public void setCareLevel(String careLevel) {
            this.careLevel = careLevel;
        }

        public String getCriticalValue() {
            return criticalValue;
        }

        public void setCriticalValue(String criticalValue) {
            this.criticalValue = criticalValue;
        }

        public String getDangerous() {
            return dangerous;
        }

        public void setDangerous(String dangerous) {
            this.dangerous = dangerous;
        }

        public String getDocDisch() {
            return docDisch;
        }

        public void setDocDisch(String docDisch) {
            this.docDisch = docDisch;
        }

        public String getEpdFlag() {
            return epdFlag;
        }

        public void setEpdFlag(String epdFlag) {
            this.epdFlag = epdFlag;
        }

        public String getEpdNotReport() {
            return epdNotReport;
        }

        public void setEpdNotReport(String epdNotReport) {
            this.epdNotReport = epdNotReport;
        }

        public String getEpdReport() {
            return epdReport;
        }

        public void setEpdReport(String epdReport) {
            this.epdReport = epdReport;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
        }

        public String getFever() {
            return fever;
        }

        public void setFever(String fever) {
            this.fever = fever;
        }

        public String getGotAllergy() {
            return gotAllergy;
        }

        public void setGotAllergy(String gotAllergy) {
            this.gotAllergy = gotAllergy;
        }

        public String getIllState() {
            return illState;
        }

        public void setIllState(String illState) {
            this.illState = illState;
        }

        public String getInBedAll() {
            return inBedAll;
        }

        public void setInBedAll(String inBedAll) {
            this.inBedAll = inBedAll;
        }

        public String getLongOrd() {
            return longOrd;
        }

        public void setLongOrd(String longOrd) {
            this.longOrd = longOrd;
        }

        public String getManageInBed() {
            return manageInBed;
        }

        public void setManageInBed(String manageInBed) {
            this.manageInBed = manageInBed;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNewPatient() {
            return newPatient;
        }

        public void setNewPatient(String newPatient) {
            this.newPatient = newPatient;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getSeq() {
            return seq;
        }

        public void setSeq(String seq) {
            this.seq = seq;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTempOrd() {
            return tempOrd;
        }

        public void setTempOrd(String tempOrd) {
            this.tempOrd = tempOrd;
        }

        public String getTodayOut() {
            return todayOut;
        }

        public void setTodayOut(String todayOut) {
            this.todayOut = todayOut;
        }

        public String getWait() {
            return wait;
        }

        public void setWait(String wait) {
            this.wait = wait;
        }
    }
}
