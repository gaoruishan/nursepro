package com.dhcc.infusion.update.bean;

public class UpdateBean {

    /**
     * msg :
     * msgcode : 999999
     * newVersion : 1.2.0
     * status : 0
     */

    private String appAddress;
    private String msg;
    private String msgcode;
    private String newVersion;
    private String status;

    public String getAppAddress() {
        return appAddress;
    }

    public void setAppAddress(String appAddress) {
        this.appAddress = appAddress;
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

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
