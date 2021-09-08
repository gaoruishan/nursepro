package com.dhcc.nursepro.workarea.bedmap.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.bean
 * <p>
 * author Q
 * Date: 2020/10/26
 * Time:17:05
 */
public class NurOrdListBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * ordList : [{"arcimDesc":"普通甲等病房(双人间)床位费1","phOrdQtyUnit":"1 ","totalAmount":"30.00"},{"arcimDesc":"住院诊查费","phOrdQtyUnit":"1 ","totalAmount":"9.00"},{"arcimDesc":"一次性注射器[10ml]","phOrdQtyUnit":"1 ","totalAmount":"0.80"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<OrdListBean> ordList;

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

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

    public static class OrdListBean {
        /**
         * arcimDesc : 普通甲等病房(双人间)床位费1
         * phOrdQtyUnit : 1
         * totalAmount : 30.00
         */

        private String arcimDesc;
        private String phOrdQtyUnit;
        private String totalAmount;

        public String getArcimDesc() {
            return arcimDesc;
        }

        public void setArcimDesc(String arcimDesc) {
            this.arcimDesc = arcimDesc;
        }

        public String getPhOrdQtyUnit() {
            return phOrdQtyUnit;
        }

        public void setPhOrdQtyUnit(String phOrdQtyUnit) {
            this.phOrdQtyUnit = phOrdQtyUnit;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }
    }
}
