package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean;

/**
 * BloodOperationResultBean
 *
 * @author DevLix126
 * @date 2018/9/19
 */
public class BloodOperationResultBean {


    /**
     * msg : 已签收的血袋，请勿重复签收
     * msgcode : 102332
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
