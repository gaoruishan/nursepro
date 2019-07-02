package com.dhcc.module.infusion.workarea.comm.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-05-09/17:24
 * @email:grs0515@163.com
 */
public class PatDetailBean extends CommResult {


    /**
     * LeftTreatNum : 12
     * PatInfo : {"PatName":"井尔","PatRegNo":"0000000164","PatSex":"男"}
     * RecOrdList : [{"OrdList":[{"Notes":"","OeoreId":"653||3||1","OeoreSubList":[{"ArcimDesc":"0.9%氯化钠注射液(袋装)[3000ML]","DoseQtyUnit":"3000 ml","PhOrdQtyUnit":"2 袋"},{"ArcimDesc":"复方氨基酸注射液(11.4%乐凡命)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"2 瓶"}],"OrdState":"已输完"},{"Notes":"","OeoreId":"653||5||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖注射液(袋装)[100ml]","DoseQtyUnit":"100 ml","PhOrdQtyUnit":"2 袋"},{"ArcimDesc":"维生素B1注射液[0.1g:2ml]","DoseQtyUnit":"1 支","PhOrdQtyUnit":"2 支"}],"OrdState":"已输完"},{"Notes":"","OeoreId":"653||7||1","OeoreSubList":[{"ArcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"2 瓶"},{"ArcimDesc":"复方氨基酸(15)双肽(2)注射液(漠宜林)[500ML:67G]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"2 瓶"}],"OrdState":"输液中"}],"RecOrdDateTime":"2019-05-11 15:49","RecOrdState":"开始","TreatNum":"2"},{"OrdList":[{"Notes":"","OeoreId":"652||5||1","OeoreSubList":[{"ArcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"2 瓶"},{"ArcimDesc":"维生素B6注射液[100mg:2ml]","DoseQtyUnit":"100 mg","PhOrdQtyUnit":"2 支"},{"ArcimDesc":"复方氨基酸注射液(11.4%乐凡命)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"2 瓶"}],"OrdState":"已复核"},{"Notes":"","OeoreId":"552||3||1","OeoreSubList":[{"ArcimDesc":"迈之灵片[150MG*20]","DoseQtyUnit":"150 mg","PhOrdQtyUnit":"1 盒(20)"}],"OrdState":"未配液"},{"Notes":"","OeoreId":"653||3||1","OeoreSubList":[{"ArcimDesc":"0.9%氯化钠注射液(袋装)[3000ML]","DoseQtyUnit":"3000 ml","PhOrdQtyUnit":"2 袋"},{"ArcimDesc":"复方氨基酸注射液(11.4%乐凡命)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"2 瓶"}],"OrdState":"已输完"},{"Notes":"","OeoreId":"653||5||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖注射液(袋装)[100ml]","DoseQtyUnit":"100 ml","PhOrdQtyUnit":"2 袋"},{"ArcimDesc":"维生素B1注射液[0.1g:2ml]","DoseQtyUnit":"1 支","PhOrdQtyUnit":"2 支"}],"OrdState":"已输完"},{"Notes":"","OeoreId":"653||7||1","OeoreSubList":[{"ArcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"2 瓶"},{"ArcimDesc":"复方氨基酸(15)双肽(2)注射液(漠宜林)[500ML:67G]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"2 瓶"}],"OrdState":"输液中"}],"RecOrdDateTime":"2019-05-11 15:44","RecOrdState":"完成","TreatNum":"1"}]
     */

    private String LeftTreatNum;
    private PatInfoBean PatInfo;
    private List<RecOrdListBean> RecOrdList;

    public String getLeftTreatNum() {
        return LeftTreatNum;
    }

    public void setLeftTreatNum(String LeftTreatNum) {
        this.LeftTreatNum = LeftTreatNum;
    }

    public PatInfoBean getPatInfo() {
        return PatInfo;
    }

    public void setPatInfo(PatInfoBean PatInfo) {
        this.PatInfo = PatInfo;
    }

    public List<RecOrdListBean> getRecOrdList() {
        return RecOrdList;
    }

    public void setRecOrdList(List<RecOrdListBean> RecOrdList) {
        this.RecOrdList = RecOrdList;
    }

    public static class PatInfoBean {
        /**
         * PatName : 井尔
         * PatRegNo : 0000000164
         * PatSex : 男
         */

        private String PatName;
        private String PatRegNo;
        private String PatSex;
        private String PatAge;
        //诊断
        private String PatDiag;

        public String getPatAge() {
            return PatAge == null ? "" : PatAge;
        }

        public void setPatAge(String patAge) {
            PatAge = patAge;
        }

        public String getPatDiag() {
            return PatDiag == null ? "" : PatDiag;
        }

        public void setPatDiag(String patDiag) {
            PatDiag = patDiag;
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
    }

    public static class RecOrdListBean {
        /**
         * OrdList : [{"Notes":"","OeoreId":"653||3||1","OeoreSubList":[{"ArcimDesc":"0.9%氯化钠注射液(袋装)[3000ML]","DoseQtyUnit":"3000 ml","PhOrdQtyUnit":"2 袋"},{"ArcimDesc":"复方氨基酸注射液(11.4%乐凡命)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"2 瓶"}],"OrdState":"已输完"},{"Notes":"","OeoreId":"653||5||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖注射液(袋装)[100ml]","DoseQtyUnit":"100 ml","PhOrdQtyUnit":"2 袋"},{"ArcimDesc":"维生素B1注射液[0.1g:2ml]","DoseQtyUnit":"1 支","PhOrdQtyUnit":"2 支"}],"OrdState":"已输完"},{"Notes":"","OeoreId":"653||7||1","OeoreSubList":[{"ArcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"2 瓶"},{"ArcimDesc":"复方氨基酸(15)双肽(2)注射液(漠宜林)[500ML:67G]","DoseQtyUnit":"500 ml","PhOrdQtyUnit":"2 瓶"}],"OrdState":"输液中"}]
         * RecOrdDateTime : 2019-05-11 15:49
         * RecOrdState : 开始
         * TreatNum : 2
         */

        private String RecOrdDateTime;
        private String RecOrdState;
        private String TreatNum;
        private List<OrdListBean> OrdList;

        public String getRecOrdDateTime() {
            return RecOrdDateTime;
        }

        public void setRecOrdDateTime(String RecOrdDateTime) {
            this.RecOrdDateTime = RecOrdDateTime;
        }

        public String getRecOrdState() {
            return RecOrdState;
        }

        public void setRecOrdState(String RecOrdState) {
            this.RecOrdState = RecOrdState;
        }

        public String getTreatNum() {
            return TreatNum;
        }

        public void setTreatNum(String TreatNum) {
            this.TreatNum = TreatNum;
        }

        public List<OrdListBean> getOrdList() {
            return OrdList;
        }

        public void setOrdList(List<OrdListBean> OrdList) {
            this.OrdList = OrdList;
        }

    }
}
