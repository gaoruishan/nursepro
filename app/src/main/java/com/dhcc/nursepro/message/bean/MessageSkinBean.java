package com.dhcc.nursepro.message.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.nursepro.R;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-05-13/14:55
 * @email:grs0515@163.com
 */
public class MessageSkinBean extends CommResult {
    /**
     * PatName : lh041101
     * PatRegNo : 0000000435
     * patSex : 女
     */
    public static int getPatSexDrawable(String patSex) {
        if ("男".equals(patSex)) {
            return R.drawable.sex_male;
        } else if ("女".equals(patSex)) {
            return R.drawable.sex_female;
        } else {
            return R.drawable.sex_male;
        }
    }

    private List<SkinTimeListBean> SkinTimeList;

    public List<SkinTimeListBean> getSkinTimeList() {
        return SkinTimeList;
    }

    public void setSkinTimeList(List<SkinTimeListBean> SkinTimeList) {
        this.SkinTimeList = SkinTimeList;
    }

    public static class SkinTimeListBean {
        /**
         * ObserveTime : 1200
         * OeoreGroup : [{"ArcimDesc":"氯化钠注射液[1g:10ml]","DoseQtyUnit":"1 g","PhOrdQtyUnit":"1 支"},{"ArcimDesc":"注射用头孢米诺钠[0.5G]()","DoseQtyUnit":"1 g","PhOrdQtyUnit":"2 支"}]
         * OeoreId :
         * OverTime : 1173
         * PatName : 井尔
         * PatRegNo : 0000000164
         * PatSex : 男
         * SkinResutl :
         * TestStartTime : 09:06:57
         */

        private String ObserveTime;//总用时间
        private String OeoreId;
        private String OverTime;//倒计时
        private String PatName;
        private String PatRegNo;
        private String PatSex;
        private String SkinResutl;
        private String TestStartTime;//
        private List<OeoreGroupBean> OeoreGroup;

        public String getObserveTime() {
            return ObserveTime;
        }

        public void setObserveTime(String ObserveTime) {
            this.ObserveTime = ObserveTime;
        }

        public String getOeoreId() {
            return OeoreId;
        }

        public void setOeoreId(String OeoreId) {
            this.OeoreId = OeoreId;
        }

        public String getOverTime() {
            return OverTime;
        }

        public void setOverTime(String OverTime) {
            this.OverTime = OverTime;
        }

        public String getPatName() {
            return PatName;
        }

        public void setPatName(String PatName) {
            this.PatName = PatName;
        }

        public String getPatRegNo() {
            return PatRegNo;
        }

        public void setPatRegNo(String PatRegNo) {
            this.PatRegNo = PatRegNo;
        }

        public String getPatSex() {
            return PatSex;
        }

        public void setPatSex(String PatSex) {
            this.PatSex = PatSex;
        }

        public String getSkinResutl() {
            return SkinResutl;
        }

        public void setSkinResutl(String SkinResutl) {
            this.SkinResutl = SkinResutl;
        }

        public String getTestStartTime() {
            return TestStartTime;
        }

        public void setTestStartTime(String TestStartTime) {
            this.TestStartTime = TestStartTime;
        }

        public List<OeoreGroupBean> getOeoreGroup() {
            return OeoreGroup;
        }

        public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
            this.OeoreGroup = OeoreGroup;
        }
    }
}
