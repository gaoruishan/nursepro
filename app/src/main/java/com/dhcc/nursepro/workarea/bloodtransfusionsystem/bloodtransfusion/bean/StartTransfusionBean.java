package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.bean;

/**
 * com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.bean
 * <p>
 * author Q
 * Date: 2018/9/19
 * Time:9:16
 */
public class StartTransfusionBean {
    /**
     * msg : 成功!
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
