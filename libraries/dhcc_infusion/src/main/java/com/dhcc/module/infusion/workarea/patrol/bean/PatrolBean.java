package com.dhcc.module.infusion.workarea.patrol.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-28/09:09
 * @email:grs0515@163.com
 */
public class PatrolBean extends CommResult {

    public static final String State_Pause ="暂停";
    public static final String State_Finsh ="结束";

    /**
     * CurOeoreId : 568-3-1
     * CurRegNo :
     * DefautSpeed : 45
     * DistantDate : 2019-04-28
     * DistantTime : 10:09:13
     * PatInfo : {"PatName":"lh041101","PatRegNo":"0000000435","PatSex":"女"}
     * infusionReasonList : [{"InfusionReason":"鼓针"},{"InfusionReason":"原因1"},{"InfusionReason":"原因2"},{"InfusionReason":"原因3"}]
     * infusionStateList : [{"InfusionState":"正常"},{"InfusionState":"暂停"},{"InfusionState":"结束"},{"InfusionState":"其他"}]
     * infusionTourList : [{"InfusionTourDate":"2019-04-28","InfusionTourMeasure":"","InfusionTourReason":"","InfusionTourState":"","InfusionTourTime":"09/24/29","InfusionTourUser":"Demo Group"}]
     * msg : 成功
     * msgcode :
     * ordList : [{"Notes":"","OeoreId":"568||3||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"待续液"},{"Notes":"","OeoreId":"568||3||3","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"待续液"},{"Notes":"","OeoreId":"568||3||5","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"未配液"}]
     * status : 0
     */

    private String CurOeoreId;
    private String CurRegNo;
    private String DefautSpeed;
    private String DistantDate;
    private String DistantTime;
    private PatInfoBean PatInfo;
    private List<InfusionReasonListBean> infusionReasonList;
    private List<InfusionStateListBean> infusionStateList;
    private List<InfusionTourListBean> infusionTourList;
    private List<OrdListBean> ordList;

    public String getCurOeoreId() {
        return CurOeoreId;
    }

    public void setCurOeoreId(String CurOeoreId) {
        this.CurOeoreId = CurOeoreId;
    }

    public String getCurRegNo() {
        return CurRegNo;
    }

    public void setCurRegNo(String CurRegNo) {
        this.CurRegNo = CurRegNo;
    }

    public String getDefautSpeed() {
        return DefautSpeed;
    }

    public void setDefautSpeed(String DefautSpeed) {
        this.DefautSpeed = DefautSpeed;
    }

    public String getDistantDate() {
        return DistantDate;
    }

    public void setDistantDate(String DistantDate) {
        this.DistantDate = DistantDate;
    }

    public String getDistantTime() {
        return DistantTime;
    }

    public void setDistantTime(String DistantTime) {
        this.DistantTime = DistantTime;
    }

    public PatInfoBean getPatInfo() {
        return PatInfo;
    }

    public void setPatInfo(PatInfoBean PatInfo) {
        this.PatInfo = PatInfo;
    }


    public List<InfusionReasonListBean> getInfusionReasonList() {
        return infusionReasonList;
    }

    public void setInfusionReasonList(List<InfusionReasonListBean> infusionReasonList) {
        this.infusionReasonList = infusionReasonList;
    }

    public List<InfusionStateListBean> getInfusionStateList() {
        return infusionStateList;
    }

    public void setInfusionStateList(List<InfusionStateListBean> infusionStateList) {
        this.infusionStateList = infusionStateList;
    }

    public List<InfusionTourListBean> getInfusionTourList() {
        return infusionTourList;
    }

    public void setInfusionTourList(List<InfusionTourListBean> infusionTourList) {
        this.infusionTourList = infusionTourList;
    }

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

    public static class InfusionReasonListBean {
        /**
         * InfusionReason : 鼓针
         */

        private String InfusionReason;

        public String getInfusionReason() {
            return InfusionReason;
        }

        public void setInfusionReason(String InfusionReason) {
            this.InfusionReason = InfusionReason;
        }
    }

    public static class InfusionStateListBean {
        /**
         * InfusionState : 正常
         */

        private String InfusionState;
        private String InfusionStateFlag;

        public String getInfusionStateFlag() {
            return InfusionStateFlag == null ? "" : InfusionStateFlag;
        }

        public void setInfusionStateFlag(String infusionStateFlag) {
            InfusionStateFlag = infusionStateFlag;
        }

        public String getInfusionState() {
            return InfusionState;
        }

        public void setInfusionState(String InfusionState) {
            this.InfusionState = InfusionState;
        }
    }


}
