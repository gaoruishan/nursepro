package com.dhcc.nursepro.workarea.workareabean;

import java.util.List;

public class MainConfigBean {
    /**
     * mainConfig : ["BEDMAP","VITALSIGN","EVENTS","ORDERSEARCH","ORDEREXECUTE","CHECK","LAB","OPERATION","LABOUT","DOSINGREVIEW","ALLOTBED","DOCORDERLIST","BLOOD","MILK","MOTHERBABYLINK"]
     * msg :
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<String> mainConfig;
    private String scantimes;

    public String getScantimes(){
        return scantimes;
    }

    public void setScantimes(String scantimes){
        this.scantimes = scantimes;
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

    public List<String> getMainConfig() {
        return mainConfig;
    }

    public void setMainConfig(List<String> mainConfig) {
        this.mainConfig = mainConfig;
    }
}
