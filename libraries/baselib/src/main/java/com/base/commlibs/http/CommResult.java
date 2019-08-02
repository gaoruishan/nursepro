package com.base.commlibs.http;

/**
 * @author:gaoruishan
 * @date:202019-04-25/14:25
 * @email:grs0515@163.com
 */
public class CommResult {

    /**
     * msg : err:请勿重复配液
     * msgcode : 199992
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