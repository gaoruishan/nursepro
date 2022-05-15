package com.dhcc.nursepro.workarea.bedmap.bean;

import java.util.Map;

public class PatInfoDetailBean {
    private String msg;
    private String msgcode;
    private String status;
    private Map<String, Object> patInfoDetail;

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

    public Map<String, Object> getPatInfoDetail() {
        return patInfoDetail;
    }

    public void setPatInfoDetail(Map<String, Object> patInfoDetail) {
        this.patInfoDetail = patInfoDetail;
    }
}
