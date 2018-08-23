package com.dhcc.nursepro.workarea.checkandlab.bean;

import java.util.List;

public class LabResultListBean {
    /**
     * labOrderList : [{"labNo":"1000000027","oeordId":"91||141","orderName":"血常规","reportId":"","resultDateTime":"","resultStatus":"N"},{"labNo":"1000000026","oeordId":"91||137","orderName":"血常规","reportId":"","resultDateTime":"","resultStatus":"N"},{"labNo":"18040300021","oeordId":"91||100","orderName":"血常规","reportId":"","resultDateTime":"","resultStatus":"N"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<LabOrderListBean> labOrderList;

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

    public List<LabOrderListBean> getLabOrderList() {
        return labOrderList;
    }

    public void setLabOrderList(List<LabOrderListBean> labOrderList) {
        this.labOrderList = labOrderList;
    }

    public static class LabOrderListBean {
        /**
         * labNo : 1000000027
         * oeordId : 91||141
         * orderName : 血常规
         * reportId :
         * resultDateTime :
         * resultStatus : N
         */

        private String labNo;
        private String oeordId;
        private String orderName;
        private String reportId;
        private String resultDateTime;
        private String resultStatus;

        public String getLabNo() {
            return labNo;
        }

        public void setLabNo(String labNo) {
            this.labNo = labNo;
        }

        public String getOeordId() {
            return oeordId;
        }

        public void setOeordId(String oeordId) {
            this.oeordId = oeordId;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public String getReportId() {
            return reportId;
        }

        public void setReportId(String reportId) {
            this.reportId = reportId;
        }

        public String getResultDateTime() {
            return resultDateTime;
        }

        public void setResultDateTime(String resultDateTime) {
            this.resultDateTime = resultDateTime;
        }

        public String getResultStatus() {
            return resultStatus;
        }

        public void setResultStatus(String resultStatus) {
            this.resultStatus = resultStatus;
        }
    }
}
