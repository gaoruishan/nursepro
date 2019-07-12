package com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean
 * <p>
 * author Q
 * Date: 2019/7/5
 * Time:15:51
 */
public class MilkWarmingBaginfoBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * patInfo : {"amount":"68","bagNo":"BAG:11","bedCode":"02","collectDate":"2019-07-05","collectTime":"14:54","heatEndDateTime":"","heatOverTime":"","heatSttDateTime":"","patName":"myh1506","regNo":"0000000195","status":"cold"}
     * status : 0
     */

    private String msg;
    private String msgcode;
    private PatInfoBean patInfo;
    private String status;

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

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class PatInfoBean {
        /**
         * amount : 68
         * bagNo : BAG:11
         * bedCode : 02
         * collectDate : 2019-07-05
         * collectTime : 14:54
         * heatEndDateTime :
         * heatOverTime :
         * heatSttDateTime :
         * patName : myh1506
         * regNo : 0000000195
         * status : cold
         */

        private String amount;
        private String bagNo;
        private String bedCode;
        private String collectDate;
        private String collectTime;
        private String heatEndDateTime;
        private String heatOverTime;
        private String heatSttDateTime;
        private String patName;
        private String regNo;
        private String status;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBagNo() {
            return bagNo;
        }

        public void setBagNo(String bagNo) {
            this.bagNo = bagNo;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getCollectDate() {
            return collectDate;
        }

        public void setCollectDate(String collectDate) {
            this.collectDate = collectDate;
        }

        public String getCollectTime() {
            return collectTime;
        }

        public void setCollectTime(String collectTime) {
            this.collectTime = collectTime;
        }

        public String getHeatEndDateTime() {
            return heatEndDateTime;
        }

        public void setHeatEndDateTime(String heatEndDateTime) {
            this.heatEndDateTime = heatEndDateTime;
        }

        public String getHeatOverTime() {
            return heatOverTime;
        }

        public void setHeatOverTime(String heatOverTime) {
            this.heatOverTime = heatOverTime;
        }

        public String getHeatSttDateTime() {
            return heatSttDateTime;
        }

        public void setHeatSttDateTime(String heatSttDateTime) {
            this.heatSttDateTime = heatSttDateTime;
        }

        public String getPatName() {
            return patName;
        }

        public void setPatName(String patName) {
            this.patName = patName;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
