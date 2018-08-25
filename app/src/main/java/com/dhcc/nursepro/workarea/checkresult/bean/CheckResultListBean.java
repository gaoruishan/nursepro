package com.dhcc.nursepro.workarea.checkresult.bean;

import java.util.List;

public class CheckResultListBean {
    /**
     * msg :
     * msgcode : 999999
     * risOrdList : [{"orderName":"上肢软组织超声","reportDateTime":"2018-03-28 00:00:00","reportDoc":"ris","reportNote":"报告:1111\r22222\r","reportStat":"Y"},{"orderName":"复查单脏器","reportDateTime":"2018-03-27 00:00:00","reportDoc":"ris","reportNote":"报告:qqqqq\rwwwww\r","reportStat":"Y"},{"orderName":"下肢软组织超声","reportDateTime":"","reportDoc":"","reportNote":"","reportStat":"N"},{"orderName":"体表包块超声","reportDateTime":"","reportDoc":"","reportNote":"","reportStat":"N"},{"orderName":"双涎腺及颈部淋巴结超声","reportDateTime":"","reportDoc":"","reportNote":"","reportStat":"N"},{"orderName":"阑尾下腹部超声","reportDateTime":"","reportDoc":"","reportNote":"","reportStat":"N"},{"orderName":"阑尾下腹部超声","reportDateTime":"2018-03-28 00:00:00","reportDoc":"ris","reportNote":"报告:11\r11\r","reportStat":"Y"},{"orderName":"上肢软组织超声","reportDateTime":"2018-03-28 00:00:00","reportDoc":"ris","reportNote":"报告:1\r2\r","reportStat":"Y"},{"orderName":"下肢软组织超声","reportDateTime":"","reportDoc":"","reportNote":"","reportStat":"N"},{"orderName":"颈部动脉超声","reportDateTime":"","reportDoc":"","reportNote":"","reportStat":"N"},{"orderName":"下肢软组织超声","reportDateTime":"2018-03-28 00:00:00","reportDoc":"ris","reportNote":"报告:1\r2\r","reportStat":"Y"},{"orderName":"阑尾下腹部超声","reportDateTime":"2018-03-28 00:00:00","reportDoc":"ris","reportNote":"报告:1\r2\r","reportStat":"Y"},{"orderName":"双眼及附属器超声","reportDateTime":"2018-03-30 00:00:00","reportDoc":"ris","reportNote":"报告:1\r2\r","reportStat":"Y"},{"orderName":"下肢软组织超声","reportDateTime":"2018-03-30 00:00:00","reportDoc":"","reportNote":"报告:5\r6\r","reportStat":"Y"},{"orderName":"下肢软组织超声","reportDateTime":"","reportDoc":"","reportNote":"","reportStat":"N"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<RisOrdListBean> risOrdList;

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

    public List<RisOrdListBean> getRisOrdList() {
        return risOrdList;
    }

    public void setRisOrdList(List<RisOrdListBean> risOrdList) {
        this.risOrdList = risOrdList;
    }

    public static class RisOrdListBean {
        /**
         * orderName : 上肢软组织超声
         * reportDateTime : 2018-03-28 00:00:00
         * reportDoc : ris
         * reportNote : 报告:111122222
         * reportStat : Y
         */

        private String orderName;
        private String reportDateTime;
        private String reportDoc;
        private String reportNote;
        private String reportStat;

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public String getReportDateTime() {
            return reportDateTime;
        }

        public void setReportDateTime(String reportDateTime) {
            this.reportDateTime = reportDateTime;
        }

        public String getReportDoc() {
            return reportDoc;
        }

        public void setReportDoc(String reportDoc) {
            this.reportDoc = reportDoc;
        }

        public String getReportNote() {
            return reportNote;
        }

        public void setReportNote(String reportNote) {
            this.reportNote = reportNote;
        }

        public String getReportStat() {
            return reportStat;
        }

        public void setReportStat(String reportStat) {
            this.reportStat = reportStat;
        }
    }
}
