package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean;

import java.util.List;

public class DrugHandOverScanOrderList {

    /**
     * OrdList : [{"OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||1","Stkbin":"A01"},{"OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||2","Stkbin":"A01"}]
     * msg : 成功
     * msgcode :
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<OrdListBean> OrdList;

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
        return OrdList;
    }

    public void setOrdList(List<OrdListBean> OrdList) {
        this.OrdList = OrdList;
    }

    public static class OrdListBean {
        /**
         * OeoreGroup : [{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}]
         * OeoreId : 194||53||1
         * Stkbin : A01
         */

        private String OeoreId;
        private String Stkbin;
        private List<OeoreGroupBean> OeoreGroup;

        //是否扫小码
        private Boolean isScan = false;
        //问题
        private String error = "";

        public Boolean getScan() {
            return isScan;
        }

        public void setScan(Boolean scan) {
            isScan = scan;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getOeoreId() {
            return OeoreId;
        }

        public void setOeoreId(String OeoreId) {
            this.OeoreId = OeoreId;
        }

        public String getStkbin() {
            return Stkbin;
        }

        public void setStkbin(String Stkbin) {
            this.Stkbin = Stkbin;
        }

        public List<OeoreGroupBean> getOeoreGroup() {
            return OeoreGroup;
        }

        public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
            this.OeoreGroup = OeoreGroup;
        }

        public static class OeoreGroupBean {
            /**
             * ArcimDesc : 10%葡萄糖注射液(塑瓶)[500ML]
             * DoseQtyUnit : 500 ml
             * PhOrdQtyUnit : 1500 ml
             */

            private String ArcimDesc;
            private String DoseQtyUnit;
            private String PhOrdQtyUnit;

            public String getArcimDesc() {
                return ArcimDesc;
            }

            public void setArcimDesc(String ArcimDesc) {
                this.ArcimDesc = ArcimDesc;
            }

            public String getDoseQtyUnit() {
                return DoseQtyUnit;
            }

            public void setDoseQtyUnit(String DoseQtyUnit) {
                this.DoseQtyUnit = DoseQtyUnit;
            }

            public String getPhOrdQtyUnit() {
                return PhOrdQtyUnit;
            }

            public void setPhOrdQtyUnit(String PhOrdQtyUnit) {
                this.PhOrdQtyUnit = PhOrdQtyUnit;
            }
        }
    }
}
