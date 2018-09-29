package com.dhcc.nursepro.workarea.milkloopsystem.bean;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.bean
 * <p>
 * author Q
 * Date: 2018/9/20
 * Time:10:56
 */
public class MilkOperatResultBean {
    /**
     * msg : 不存在该奶袋标签
     * msgcode : 120386
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
