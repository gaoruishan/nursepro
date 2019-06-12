package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean;

import java.io.Serializable;
import java.util.List;

public class GetOrdRecListBean implements Serializable{

    /**
     * RecList : [{"CarryUser":"","NoRecieveFlag":"","RecSubList":[{"NoRecieveDesc":"","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||1"},{"NoRecieveDesc":"","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||2"}],"RecieveDateTime":"05/28/2019 10:51","RecieveSum":"2","RecieveUser":"Demo Group"},{"CarryUser":"","NoRecieveFlag":"","RecSubList":[{"NoRecieveDesc":"","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||1||Y"},{"NoRecieveDesc":"","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||2||N"}],"RecieveDateTime":"05/30/2019 14:16","RecieveSum":"2","RecieveUser":"护士01"},{"CarryUser":"","NoRecieveFlag":"","RecSubList":[{"NoRecieveDesc":"","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||1||N"},{"NoRecieveDesc":"","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||2||Y"}],"RecieveDateTime":"05/30/2019 14:34","RecieveSum":"2","RecieveUser":"护士01"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<RecListBean> RecList;

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

    public List<RecListBean> getRecList() {
        return RecList;
    }

    public void setRecList(List<RecListBean> RecList) {
        this.RecList = RecList;
    }

    public static class RecListBean implements Serializable{
        /**
         * CarryUser :
         * NoRecieveFlag :
         * RecSubList : [{"NoRecieveDesc":"","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||1"},{"NoRecieveDesc":"","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||53||2"}]
         * RecieveDateTime : 05/28/2019 10:51
         * RecieveSum : 2
         * RecieveUser : Demo Group
         */

        private String CarryUser;
        private String NoRecieveFlag;
        private String RecieveDateTime;
        private String RecieveSum;
        private String RecieveUser;
        private List<RecSubListBean> RecSubList;

        public String getCarryUser() {
            return CarryUser;
        }

        public void setCarryUser(String CarryUser) {
            this.CarryUser = CarryUser;
        }

        public String getNoRecieveFlag() {
            return NoRecieveFlag;
        }

        public void setNoRecieveFlag(String NoRecieveFlag) {
            this.NoRecieveFlag = NoRecieveFlag;
        }

        public String getRecieveDateTime() {
            return RecieveDateTime;
        }

        public void setRecieveDateTime(String RecieveDateTime) {
            this.RecieveDateTime = RecieveDateTime;
        }

        public String getRecieveSum() {
            return RecieveSum;
        }

        public void setRecieveSum(String RecieveSum) {
            this.RecieveSum = RecieveSum;
        }

        public String getRecieveUser() {
            return RecieveUser;
        }

        public void setRecieveUser(String RecieveUser) {
            this.RecieveUser = RecieveUser;
        }

        public List<RecSubListBean> getRecSubList() {
            return RecSubList;
        }

        public void setRecSubList(List<RecSubListBean> RecSubList) {
            this.RecSubList = RecSubList;
        }

        public static class RecSubListBean implements Serializable{
            /**
             * NoRecieveDesc :
             * OeoreGroup : [{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[500ML]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"1500 ml"}]
             * OeoreId : 194||53||1
             */

            private String NoRecieveDesc;
            private String OeoreId;
            private List<OeoreGroupBean> OeoreGroup;

            public String getNoRecieveDesc() {
                return NoRecieveDesc;
            }

            public void setNoRecieveDesc(String NoRecieveDesc) {
                this.NoRecieveDesc = NoRecieveDesc;
            }

            public String getOeoreId() {
                return OeoreId;
            }

            public void setOeoreId(String OeoreId) {
                this.OeoreId = OeoreId;
            }

            public List<OeoreGroupBean> getOeoreGroup() {
                return OeoreGroup;
            }

            public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
                this.OeoreGroup = OeoreGroup;
            }

            public static class OeoreGroupBean implements Serializable{
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
}
