package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean;

public class TakeOrdBean {

    /**
     * msg : err:请勿重复配液!
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
