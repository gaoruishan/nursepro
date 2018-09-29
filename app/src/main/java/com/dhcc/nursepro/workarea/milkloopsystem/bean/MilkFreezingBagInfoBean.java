package com.dhcc.nursepro.workarea.milkloopsystem.bean;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.bean
 * <p>
 * author Q
 * Date: 2018/9/25
 * Time:8:44
 */
public class MilkFreezingBagInfoBean {
    /**
     * msg : 冷冻状态,不允许重复冷冻！
     * msgcode : 120386
     * patInfo : {"amount":"25","bagNo":"BAG:1","bedCode":"01","collectDate":"2018-09-25","collectTime":"14:51","patName":"王伟测试","regNo":"0000000129"}
     * status : -1
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
         * amount : 25
         * bagNo : BAG:1
         * bedCode : 01
         * collectDate : 2018-09-25
         * collectTime : 14:51
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
