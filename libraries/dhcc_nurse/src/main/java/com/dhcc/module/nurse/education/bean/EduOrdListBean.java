package com.dhcc.module.nurse.education.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-28/09:07
 * @email:grs0515@163.com
 */
public class EduOrdListBean extends CommResult {

    private List<OrdListBean> ordList;

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

    public static class OrdListBean {
        /**
         * arcimDesc : 阿司匹林肠溶片(拜阿司匹灵)[100mg*30]
         * itmMastDr : 786||1
         * ordCat : 西药片剂
         * ordDateTime : 2020-02-21 17:50
         * phcfrCode : Qn
         * phcinDesc : 口服
         */

        private String arcimDesc;
        private String itmMastDr;
        private String ordCat;
        private String ordDateTime;
        private String phcfrCode;
        private String phcinDesc;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getArcimDesc() {
            return arcimDesc;
        }

        public void setArcimDesc(String arcimDesc) {
            this.arcimDesc = arcimDesc;
        }

        public String getItmMastDr() {
            return itmMastDr;
        }

        public void setItmMastDr(String itmMastDr) {
            this.itmMastDr = itmMastDr;
        }

        public String getOrdCat() {
            return ordCat;
        }

        public void setOrdCat(String ordCat) {
            this.ordCat = ordCat;
        }

        public String getOrdDateTime() {
            return ordDateTime;
        }

        public void setOrdDateTime(String ordDateTime) {
            this.ordDateTime = ordDateTime;
        }

        public String getPhcfrCode() {
            return phcfrCode;
        }

        public void setPhcfrCode(String phcfrCode) {
            this.phcfrCode = phcfrCode;
        }

        public String getPhcinDesc() {
            return phcinDesc;
        }

        public void setPhcinDesc(String phcinDesc) {
            this.phcinDesc = phcinDesc;
        }
    }
}
