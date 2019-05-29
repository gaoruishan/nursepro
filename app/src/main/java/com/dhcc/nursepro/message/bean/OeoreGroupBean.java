package com.dhcc.nursepro.message.bean;

public class OeoreGroupBean {
        /**
         * ArcimDesc : 5%葡萄糖氯化钠注射液(塑瓶)[250ml]
         * DoseQtyUnit : 250 ml
         * PhOrdQtyUnit : 18 瓶
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