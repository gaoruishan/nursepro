package com.dhcc.nursepro.workarea.pathandover.bean;

public class GetScanConnectBean {
    /**
     * connectType : 手术:1^转科:2^分娩:3^治疗:4
     * ifEndConnect : N
     * msg : 成功
     * msgcode : 999999
     * nowNode : 手术:1
     * nowSubNode : 术前
     * parentRec : 17
     * status : 0
     */

    private String connectType;
    private String ifEndConnect;
    private String msg;
    private String msgcode;
    private String nowNode;
    private String nowSubNode;
    private String parentRec;
    private String status;

    public String getConnectType() {
        return connectType;
    }

    public void setConnectType(String connectType) {
        this.connectType = connectType;
    }

    public String getIfEndConnect() {
        return ifEndConnect;
    }

    public void setIfEndConnect(String ifEndConnect) {
        this.ifEndConnect = ifEndConnect;
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

    public String getNowNode() {
        return nowNode;
    }

    public void setNowNode(String nowNode) {
        this.nowNode = nowNode;
    }

    public String getNowSubNode() {
        return nowSubNode;
    }

    public void setNowSubNode(String nowSubNode) {
        this.nowSubNode = nowSubNode;
    }

    public String getParentRec() {
        return parentRec;
    }

    public void setParentRec(String parentRec) {
        this.parentRec = parentRec;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
