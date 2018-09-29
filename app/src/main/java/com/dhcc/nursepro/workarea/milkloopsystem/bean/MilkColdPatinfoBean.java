package com.dhcc.nursepro.workarea.milkloopsystem.bean;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.bean
 * <p>
 * author Q
 * Date: 2018/9/27
 * Time:10:41
 */
public class MilkColdPatinfoBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * patInfo : {"amount":"67","bagNo":"91||233||1","bedCode":"01","collectDate":"2018-09-26","collectTime":"18:01","patName":"王伟测试","regNo":"0000000129"}
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
         * amount : 67
         * bagNo : 91||233||1
         * bedCode : 01
         * collectDate : 2018-09-26
         * collectTime : 18:01
         * patName : 王伟测试
         * regNo : 0000000129
         */

        private String amount;
        private String bagNo;
        private String bedCode;
        private String collectDate;
        private String collectTime;
        private String patName;
        private String regNo;

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
    }
}
