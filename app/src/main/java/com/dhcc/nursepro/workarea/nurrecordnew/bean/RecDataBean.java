package com.dhcc.nursepro.workarea.nurrecordnew.bean;

public class RecDataBean {

    /**
     * RetData : 281
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String RetData;
    private String msg;
    private String msgcode;
    private String status;
    private String caFlag;

    public String getCaFlag() {
        return caFlag == null ? "" : caFlag;
    }

    public void setCaFlag(String caFlag) {
        this.caFlag = caFlag;
    }

    public String getRetData() {
        return RetData;
    }

    public void setRetData(String RetData) {
        this.RetData = RetData;
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
}
