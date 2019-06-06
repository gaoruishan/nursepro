package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean;

import java.util.List;

public class GetTakeOrdListBean {

    /**
     * OrdList : [{"OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","PhOrdQtyUnit":"25.2 mg"}],"OeoreId":"194||60||1","TakeAuditDateTime":"2019-05-29 20:25","TakeAuditUser":"Demo Group","TakeDateTime":"2019-05-29 20:23","TakeUser":"Demo Group"},{"OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","PhOrdQtyUnit":"25.2 mg"}],"OeoreId":"194||60||2","TakeAuditDateTime":"","TakeAuditUser":"","TakeDateTime":"2019-05-29 20:25","TakeUser":"Demo Group"}]
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
         * OeoreGroup : [{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","PhOrdQtyUnit":"25.2 mg"}]
         * OeoreId : 194||60||1
         * TakeAuditDateTime : 2019-05-29 20:25
         * TakeAuditUser : Demo Group
         * TakeDateTime : 2019-05-29 20:23
         * TakeUser : Demo Group
         */

        private String OeoreId;
        private String TakeAuditDateTime;
        private String TakeAuditUser;
        private String TakeDateTime;
        private String TakeUser;
        private List<OeoreGroupBean> OeoreGroup;

        public String getOeoreId() {
            return OeoreId;
        }

        public void setOeoreId(String OeoreId) {
            this.OeoreId = OeoreId;
        }

        public String getTakeAuditDateTime() {
            return TakeAuditDateTime;
        }

        public void setTakeAuditDateTime(String TakeAuditDateTime) {
            this.TakeAuditDateTime = TakeAuditDateTime;
        }

        public String getTakeAuditUser() {
            return TakeAuditUser;
        }

        public void setTakeAuditUser(String TakeAuditUser) {
            this.TakeAuditUser = TakeAuditUser;
        }

        public String getTakeDateTime() {
            return TakeDateTime;
        }

        public void setTakeDateTime(String TakeDateTime) {
            this.TakeDateTime = TakeDateTime;
        }

        public String getTakeUser() {
            return TakeUser;
        }

        public void setTakeUser(String TakeUser) {
            this.TakeUser = TakeUser;
        }

        public List<OeoreGroupBean> getOeoreGroup() {
            return OeoreGroup;
        }

        public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
            this.OeoreGroup = OeoreGroup;
        }

        public static class OeoreGroupBean {
            /**
             * ArcimDesc : 芬太尼透皮贴剂(多瑞吉)[8.4mg*5]
             * DoseQtyUnit : 8.4 mg
             * PhOrdQtyUnit : 25.2 mg
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
