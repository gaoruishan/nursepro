package com.dhcc.module.infusion.workarea.drugreceive;

import com.base.commlibs.http.CommResult;

import java.util.List;

public class IfOrdListBean extends CommResult {

    /**
     * OrdList : [{"OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","BedCode":"18","DoseQtyUnit":"500 ml","PatName":"王佳佳","PhOrdQtyUnit":"1500 ml","SttDateTime":"2020-03-11 12:00"}],"OeoreId":"75||268||740","Stkbin":"A01"},{"OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","BedCode":"18","DoseQtyUnit":"500 ml","PatName":"王佳佳","PhOrdQtyUnit":"1500 ml","SttDateTime":"2020-03-10 16:00"}],"OeoreId":"75||268||738","Stkbin":"A01"},{"OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","BedCode":"18","DoseQtyUnit":"500 ml","PatName":"王佳佳","PhOrdQtyUnit":"1500 ml","SttDateTime":"2020-03-11 08:00"}],"OeoreId":"75||268||739","Stkbin":"A01"}]
     * msg : 成功
     * msgcode :
     * status : 0
     */

    private List<OrdListBean> OrdList;

    public List<OrdListBean> getOrdList() {
        return OrdList;
    }

    public void setOrdList(List<OrdListBean> OrdList) {
        this.OrdList = OrdList;
    }

    public static class OrdListBean {
        /**
         * OeoreGroup : [{"ArcimDesc":"0.9%氯化钠注射液[500ml]","BedCode":"18","DoseQtyUnit":"500 ml","PatName":"王佳佳","PhOrdQtyUnit":"1500 ml","SttDateTime":"2020-03-11 12:00"}]
         * OeoreId : 75||268||740
         * Stkbin : A01
         */

        private String OeoreId;
        private String Stkbin;
        private List<OeoreGroupBean> OeoreGroup;

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
             * ArcimDesc : 0.9%氯化钠注射液[500ml]
             * BedCode : 18
             * DoseQtyUnit : 500 ml
             * PatName : 王佳佳
             * PhOrdQtyUnit : 1500 ml
             * SttDateTime : 2020-03-11 12:00
             */

            private String ArcimDesc;
            private String BedCode;
            private String DoseQtyUnit;
            private String PatName;
            private String PhOrdQtyUnit;
            private String SttDateTime;

            public String getArcimDesc() {
                return ArcimDesc;
            }

            public void setArcimDesc(String ArcimDesc) {
                this.ArcimDesc = ArcimDesc;
            }

            public String getBedCode() {
                return BedCode;
            }

            public void setBedCode(String BedCode) {
                this.BedCode = BedCode;
            }

            public String getDoseQtyUnit() {
                return DoseQtyUnit;
            }

            public void setDoseQtyUnit(String DoseQtyUnit) {
                this.DoseQtyUnit = DoseQtyUnit;
            }

            public String getPatName() {
                return PatName;
            }

            public void setPatName(String PatName) {
                this.PatName = PatName;
            }

            public String getPhOrdQtyUnit() {
                return PhOrdQtyUnit;
            }

            public void setPhOrdQtyUnit(String PhOrdQtyUnit) {
                this.PhOrdQtyUnit = PhOrdQtyUnit;
            }

            public String getSttDateTime() {
                return SttDateTime;
            }

            public void setSttDateTime(String SttDateTime) {
                this.SttDateTime = SttDateTime;
            }
        }
    }
}
