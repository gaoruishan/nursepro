package com.dhcc.module.nurse.task.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/12
 * Time:14:43
 */
public class NurOrdTaskBean extends CommResult {
    private List<TaskDataListBean> taskDataList;

    public List<TaskDataListBean> getTaskDataList() {
        return taskDataList;
    }

    public void setTaskDataList(List<TaskDataListBean> taskDataList) {
        this.taskDataList = taskDataList;
    }

    public static class TaskDataListBean {
        /**
         * bedCode :
         * emrRecordId :
         * episodeID : 1471
         * executeConten :
         * executeDateTime :
         * executeItemName : 评估能够诱发患者发生感染的原因及因素，如受寒、肺部感染、导管相关性感染等。
         * executeUserName :
         * freqName : Prn
         * insertDateTime : 2020-08-04 17:27
         * interventionDR : 2254
         * interventionTypeId : 2
         * interventionTypeName : 评估观察
         * itemInfo : {"itemExecutedCount":0,"itemTotalCount":0}
         * name :
         * planDateTime : 2020-08-04 17:27
         * recordId : 15||2||1||1
         * sex : 女
         * stopDateTime :
         * tkStatus : 0
         * tkStatusName : 未执行
         * transferDateTime :
         */

        private String bedCode;
        private String emrRecordId;
        private String episodeID;
        private String executeConten;
        private String executeDateTime;
        private String executeItemName;
        private String executeUserName;
        private String freqName;
        private String insertDateTime;
        private String interventionDR;
        private String interventionTypeId;
        private String interventionTypeName;
        private String itemInfo;
        private String name;
        private String planDateTime;
        private String recordId;
        private String sex;
        private String stopDateTime;
        private String tkStatus;
        private String tkStatusName;
        private String transferDateTime;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getEmrRecordId() {
            return emrRecordId;
        }

        public void setEmrRecordId(String emrRecordId) {
            this.emrRecordId = emrRecordId;
        }

        public String getEpisodeID() {
            return episodeID;
        }

        public void setEpisodeID(String episodeID) {
            this.episodeID = episodeID;
        }

        public String getExecuteConten() {
            return executeConten;
        }

        public void setExecuteConten(String executeConten) {
            this.executeConten = executeConten;
        }

        public String getExecuteDateTime() {
            return executeDateTime;
        }

        public void setExecuteDateTime(String executeDateTime) {
            this.executeDateTime = executeDateTime;
        }

        public String getExecuteItemName() {
            return executeItemName;
        }

        public void setExecuteItemName(String executeItemName) {
            this.executeItemName = executeItemName;
        }

        public String getExecuteUserName() {
            return executeUserName;
        }

        public void setExecuteUserName(String executeUserName) {
            this.executeUserName = executeUserName;
        }

        public String getFreqName() {
            return freqName;
        }

        public void setFreqName(String freqName) {
            this.freqName = freqName;
        }

        public String getInsertDateTime() {
            return insertDateTime;
        }

        public void setInsertDateTime(String insertDateTime) {
            this.insertDateTime = insertDateTime;
        }

        public String getInterventionDR() {
            return interventionDR;
        }

        public void setInterventionDR(String interventionDR) {
            this.interventionDR = interventionDR;
        }

        public String getInterventionTypeId() {
            return interventionTypeId;
        }

        public void setInterventionTypeId(String interventionTypeId) {
            this.interventionTypeId = interventionTypeId;
        }

        public String getInterventionTypeName() {
            return interventionTypeName;
        }

        public void setInterventionTypeName(String interventionTypeName) {
            this.interventionTypeName = interventionTypeName;
        }

        public String getItemInfo() {
            return itemInfo;
        }

        public void setItemInfo(String itemInfo) {
            this.itemInfo = itemInfo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlanDateTime() {
            return planDateTime;
        }

        public void setPlanDateTime(String planDateTime) {
            this.planDateTime = planDateTime;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStopDateTime() {
            return stopDateTime;
        }

        public void setStopDateTime(String stopDateTime) {
            this.stopDateTime = stopDateTime;
        }

        public String getTkStatus() {
            return tkStatus;
        }

        public void setTkStatus(String tkStatus) {
            this.tkStatus = tkStatus;
        }

        public String getTkStatusName() {
            return tkStatusName;
        }

        public void setTkStatusName(String tkStatusName) {
            this.tkStatusName = tkStatusName;
        }

        public String getTransferDateTime() {
            return transferDateTime;
        }

        public void setTransferDateTime(String transferDateTime) {
            this.transferDateTime = transferDateTime;
        }
    }
}
