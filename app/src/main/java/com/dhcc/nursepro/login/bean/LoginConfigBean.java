package com.dhcc.nursepro.login.bean;

import com.base.commlibs.bean.BroadcastListBean;

import java.util.List;
import java.util.Map;

public class LoginConfigBean {

    /**
     * broadcastList : [{"Action":"ACTION_CONTENT_NOTIFY_MOTO","Decode":"com.motorolasolutions.emdk.datawedge.data_string","Name":"摩托"},{"Action":"com.scanner.broadcast","Decode":"data","Name":"成为"},{"Action":"lachesis_barcode_value_notice_broadcast","Decode":"lachesis_barcode_value_notice_broadcast_data_string","Name":"新联"}]
     * msg :
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<BroadcastListBean> broadcastList;
    private Map<String, String> loginFlags; // EH 20222-04-08 登录前设置放在广播

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

    public List<BroadcastListBean> getBroadcastList() {
        return broadcastList;
    }

    public void setBroadcastList(List<BroadcastListBean> broadcastList) {
        this.broadcastList = broadcastList;
    }

    public Map<String, String> getLoginFlags() {
        return loginFlags;
    }

    public void setLoginFlags(Map<String, String> optionFlags) {
        this.loginFlags = optionFlags;
    }

    public String getLoginFlag(String key) {
        if (loginFlags == null || !loginFlags.containsKey(key)) return "";
        return loginFlags.get(key);
    }
}
