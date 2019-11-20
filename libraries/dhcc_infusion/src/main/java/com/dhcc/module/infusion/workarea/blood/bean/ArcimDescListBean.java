package com.dhcc.module.infusion.workarea.blood.bean;

public class ArcimDescListBean {
        /**
         * ArcimDesc : 血常规
         */

        private String ArcimDesc;
        private String DoseQtyUnit;
        private String PhOrdQtyUnit;

        public String getDoseQtyUnit() {
            return DoseQtyUnit == null ? "" : DoseQtyUnit;
        }

        public void setDoseQtyUnit(String doseQtyUnit) {
            DoseQtyUnit = doseQtyUnit;
        }

        public String getPhOrdQtyUnit() {
            return PhOrdQtyUnit == null ? "" : PhOrdQtyUnit;
        }

        public void setPhOrdQtyUnit(String phOrdQtyUnit) {
            PhOrdQtyUnit = phOrdQtyUnit;
        }

        public String getArcimDesc() {
            return ArcimDesc;
        }

        public void setArcimDesc(String ArcimDesc) {
            this.ArcimDesc = ArcimDesc;
        }
    }