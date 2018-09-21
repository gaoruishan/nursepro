package com.dhcc.nursepro.workarea.vitalsign.bean;

import java.util.List;

public class VitalSignSaveBean {

    /**
     * errorList : [{"errorDesc":"腋温（℃）应在:34到43之间"}]
     * msg : 保存失败
     * msgcode : 100211
     * status : -1
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<ErrorListBean> errorList;

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

    public List<ErrorListBean> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorListBean> errorList) {
        this.errorList = errorList;
    }

    public static class ErrorListBean {
        /**
         * errorDesc : 腋温（℃）应在:34到43之间
         */

        private String errorDesc;

        public String getErrorDesc() {
            return errorDesc;
        }

        public void setErrorDesc(String errorDesc) {
            this.errorDesc = errorDesc;
        }
    }
}
