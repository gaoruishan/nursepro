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
    //是否结束 1 关闭
    private String isClose;

    public boolean getIsClose() {
        String s = isClose == null ? "" : isClose;
        return "1".equals(s);
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgcode() {
        return msgcode == null ? "" : msgcode;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}
