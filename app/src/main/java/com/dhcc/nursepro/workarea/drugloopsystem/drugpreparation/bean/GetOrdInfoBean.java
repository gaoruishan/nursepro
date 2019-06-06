package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean;

import java.util.List;

public class GetOrdInfoBean {

    /**
     * OeoreGroup : [{"ArcimCode":"XWY000122","ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","PhOrdQtyUnit":"25.2 mg"}]
     * TakeDateTime : 2019-05-29 20:23
     * TakeUser : Demo Group
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String TakeDateTime;
    private String TakeUser;
    private String msg;
    private String msgcode;
    private String status;
    private List<OeoreGroupBean> OeoreGroup;

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

    public List<OeoreGroupBean> getOeoreGroup() {
        return OeoreGroup;
    }

    public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
        this.OeoreGroup = OeoreGroup;
    }

    public static class OeoreGroupBean {
        /**
         * ArcimCode : XWY000122
         * ArcimDesc : 芬太尼透皮贴剂(多瑞吉)[8.4mg*5]
         * DoseQtyUnit : 8.4 mg
         * PhOrdQtyUnit : 25.2 mg
         */

        private String ArcimCode;
        private String ArcimDesc;
        private String DoseQtyUnit;
        private String PhOrdQtyUnit;

        //是否扫描小码
        private boolean isScan;

        public boolean isScan() {
            return isScan;
        }

        public void setScan(boolean scan) {
            isScan = scan;
        }

        public String getArcimCode() {
            return ArcimCode;
        }

        public void setArcimCode(String ArcimCode) {
            this.ArcimCode = ArcimCode;
        }

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
