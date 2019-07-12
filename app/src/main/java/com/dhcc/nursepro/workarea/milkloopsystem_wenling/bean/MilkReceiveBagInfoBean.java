package com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.bean
 * <p>
 * author Q
 * Date: 2018/9/20
 * Time:10:16
 */
public class MilkReceiveBagInfoBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * patInfo : {"bedCode":"01","patName":"myh1502","regNo":"0000000188"}
     * recTyps : 常温^冷藏^冷冻
     * status : 0
     */

    private String msg;
    private String msgcode;
    private PatInfoBean patInfo;
    private String recTyps;
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

    public String getRecTyps() {
        return recTyps;
    }

    public void setRecTyps(String recTyps) {
        this.recTyps = recTyps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class PatInfoBean {
        /**
         * bedCode : 01
         * patName : myh1502
         * regNo : 0000000188
         */

        private String bedCode;
        private String patName;
        private String regNo;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
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
