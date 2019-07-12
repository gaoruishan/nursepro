package com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.bean
 * <p>
 * author Q
 * Date: 2018/9/26
 * Time:9:47
 */
public class MilkBottlingInfoBean {
    /**
     * bagInfo : {"bagNo":"BAG:1","storeAmount":"300","validDate":"10/25/2018","validTime":"14:51:00"}
     * bottleInfo : {"arcimDesc":"母乳喂养","bedCode":"01","doseQty":"","patName":"王伟测试","phcfrCode":"","regNo":"0000000129"}
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private BagInfoBean bagInfo;
    private BottleInfoBean bottleInfo;
    private String msg;
    private String msgcode;
    private String status;

    public BagInfoBean getBagInfo() {
        return bagInfo;
    }

    public void setBagInfo(BagInfoBean bagInfo) {
        this.bagInfo = bagInfo;
    }

    public BottleInfoBean getBottleInfo() {
        return bottleInfo;
    }

    public void setBottleInfo(BottleInfoBean bottleInfo) {
        this.bottleInfo = bottleInfo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class BagInfoBean {
        /**
         * bagNo : BAG:1
         * storeAmount : 300
         * validDate : 10/25/2018
         * validTime : 14:51:00
         */

        private String bagNo;
        private String storeAmount;
        private String validDate;
        private String validTime;

        public String getBagNo() {
            return bagNo;
        }

        public void setBagNo(String bagNo) {
            this.bagNo = bagNo;
        }

        public String getStoreAmount() {
            return storeAmount;
        }

        public void setStoreAmount(String storeAmount) {
            this.storeAmount = storeAmount;
        }

        public String getValidDate() {
            return validDate;
        }

        public void setValidDate(String validDate) {
            this.validDate = validDate;
        }

        public String getValidTime() {
            return validTime;
        }

        public void setValidTime(String validTime) {
            this.validTime = validTime;
        }
    }

    public static class BottleInfoBean {
        /**
         * arcimDesc : 母乳喂养
         * bedCode : 01
         * doseQty :
         * patName : 王伟测试
         * phcfrCode :
         * regNo : 0000000129
         */

        private String arcimDesc;
        private String bedCode;
        private String doseQty;
        private String patName;
        private String phcfrCode;
        private String regNo;

        public String getArcimDesc() {
            return arcimDesc;
        }

        public void setArcimDesc(String arcimDesc) {
            this.arcimDesc = arcimDesc;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getDoseQty() {
            return doseQty;
        }

        public void setDoseQty(String doseQty) {
            this.doseQty = doseQty;
        }

        public String getPatName() {
            return patName;
        }

        public void setPatName(String patName) {
            this.patName = patName;
        }

        public String getPhcfrCode() {
            return phcfrCode;
        }

        public void setPhcfrCode(String phcfrCode) {
            this.phcfrCode = phcfrCode;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }
    }
}
