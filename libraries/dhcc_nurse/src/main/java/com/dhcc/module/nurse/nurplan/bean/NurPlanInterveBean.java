package com.dhcc.module.nurse.nurplan.bean;

import com.base.commlibs.bean.CommBean;
import com.base.commlibs.http.CommResult;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-20/14:05
 * @email:grs0515@163.com
 */
public class NurPlanInterveBean extends CommResult {

    private List<InterventionListBean> interventionList;

    /**
     * 获取勾选
     * @param data
     * @return
     */
    public static String getSelectBean(List<InterventionListBean> data) {
        //[{"freqdr":1,"rowID":"","startDateTime":"","intervdr":217}
        // ,{"freqdr":1,"rowID":"","startDateTime":"","intervdr":102}]
        List<InterventionParam> mStList = new ArrayList<>();
        for (InterventionListBean datum : data) {
            if (datum.isSelect()) {
                mStList.add(new InterventionParam(datum.freqDR,datum.getRowID(),datum.getStartDatetime(),datum.intervDR));
            }
        }
        if (mStList.size() > 0) {
            return new Gson().toJson(mStList);
        }
        return null;
    }
    public static String getSelectBeanById(List<InterventionListBean> data) {
        //15||22||4^15||22||1
        String rowId = "";
        for (InterventionListBean datum : data) {
            if (datum.isSelect()) {
                rowId += datum.getRowID() + "^";
            }
        }
        if (rowId.length() > 0) {
            return rowId.substring(0,rowId.length()-1);
        }
        return null;
    }

    public List<InterventionListBean> getInterventionList() {
        return interventionList;
    }

    public void setInterventionList(List<InterventionListBean> interventionList) {
        this.interventionList = interventionList;
    }

    /**
     * 请求参数
     */
    public static class InterventionParam {
        public String freqdr;
        public String rowID;
        public String startDateTime;
        public String intervdr;

        public InterventionParam(String freqdr, String rowID, String startDateTime, String intervdr) {
            this.freqdr = freqdr;
            this.rowID = rowID;
            this.startDateTime = startDateTime;
            this.intervdr = intervdr;
        }
    }

    public static class InterventionListBean extends CommBean {
        /**
         * createdA :
         * dataSource : 手动加入
         * exectime : 8:00:00
         * freqDR : 2
         * intCode : A000
         * intervDR : 1
         * intervName : 评估活动时的活动耐力
         * recordID : 4
         * sign : 护士01
         * startDatetime : 2020-08-19 21:24
         * statusId : 1
         * statusName : 停止
         */

        private String createdA;
        private String dataSource;
        private String exectime;
        private String freqDR;
        private String rowID;
        private String intCode;
        private String intervDR;
        private String intervName;
        private String recordID;
        private String sign;
        private String startDatetime;
        private String statusId;
        private String statusName;

        public String getRowID() {
            return rowID == null ? "" : rowID;
        }

        public void setRowID(String rowID) {
            this.rowID = rowID;
        }

        public String getCreatedA() {
            return createdA;
        }

        public void setCreatedA(String createdA) {
            this.createdA = createdA;
        }

        public String getDataSource() {
            return dataSource;
        }

        public void setDataSource(String dataSource) {
            this.dataSource = dataSource;
        }

        public String getExectime() {
            return exectime;
        }

        public void setExectime(String exectime) {
            this.exectime = exectime;
        }

        public String getFreqDR() {
            return freqDR;
        }

        public void setFreqDR(String freqDR) {
            this.freqDR = freqDR;
        }

        public String getIntCode() {
            return intCode;
        }

        public void setIntCode(String intCode) {
            this.intCode = intCode;
        }

        public String getIntervDR() {
            return intervDR;
        }

        public void setIntervDR(String intervDR) {
            this.intervDR = intervDR;
        }

        public String getIntervName() {
            return intervName;
        }

        public void setIntervName(String intervName) {
            this.intervName = intervName;
        }

        public String getRecordID() {
            return recordID;
        }

        public void setRecordID(String recordID) {
            this.recordID = recordID;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getStartDatetime() {
            return startDatetime;
        }

        public void setStartDatetime(String startDatetime) {
            this.startDatetime = startDatetime;
        }

        public String getStatusId() {
            return statusId;
        }

        public void setStatusId(String statusId) {
            this.statusId = statusId;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }
}
