package com.dhcc.res.item.bean;

import com.dhcc.res.CustomBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020/12/10/09:16
 * @email:grs0515@163.com
 */
public class CustomOrdItem extends CustomBean {

    private String tvTag;
    private String tvNote;
    private List<OeoreGroupBean> list;


    public String getTvTag() {
        return tvTag == null ? "" : tvTag;
    }

    public void setTvTag(String tvTag) {
        this.tvTag = tvTag;
    }

    public String getTvNote() {
        return tvNote == null ? "" : tvNote;
    }

    public void setTvNote(String tvNote) {
        this.tvNote = tvNote;
    }

    public List<OeoreGroupBean> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List<OeoreGroupBean> list) {
        this.list = list;
    }

    public static class OeoreGroupBean {
        /**
         * ArcimDesc : 5%葡萄糖氯化钠注射液(塑瓶)[250ml]
         * DoseQtyUnit : 250 ml
         * PhOrdQtyUnit : 18 瓶
         */
        private String ArcimDesc;
        private String DoseQtyUnit;
        private String PhOrdQtyUnit;

        public OeoreGroupBean(String arcimDesc, String doseQtyUnit, String phOrdQtyUnit) {
            ArcimDesc = arcimDesc;
            DoseQtyUnit = doseQtyUnit;
            PhOrdQtyUnit = phOrdQtyUnit;
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
