package com.dhcc.nursepro.workarea.milkloopsystem.bean;

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
     * patInfo : {"bedCode":"01","patName":"王伟测试","regNo":"0000000129"}
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
         * bedCode : 01
         * patName : 王伟测试
         * regNo : 0000000129
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
