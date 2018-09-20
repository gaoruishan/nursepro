package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend.bean;

/**
 * com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend.bean
 * <p>
 * author Q
 * Date: 2018/9/19
 * Time:15:29
 */
public class EndTransfusionBean {
    /**
     * msg : 护士信息有误!
     * msgcode : 120181
     * status : -1
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
