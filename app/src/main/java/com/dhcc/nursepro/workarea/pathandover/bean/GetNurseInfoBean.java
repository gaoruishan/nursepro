package com.dhcc.nursepro.workarea.pathandover.bean;

public class GetNurseInfoBean {

    /**
     * msg : 成功
     * msgcode : 999999
     * nurseName : 护士01
     * status : 0
     * userId : 12177
     */

    private String msg;
    private String msgcode;
    private String nurseName;
    private String status;
    private String userId;

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

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
