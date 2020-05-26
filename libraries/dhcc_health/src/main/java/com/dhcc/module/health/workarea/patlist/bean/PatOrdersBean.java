package com.dhcc.module.health.workarea.patlist.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.health.workarea.patlist.api
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:15:53
 */
public class PatOrdersBean extends CommResult {
    private List<CureInfoListBean> cureInfoList;

    public List<CureInfoListBean> getCureInfoList() {
        return cureInfoList;
    }

    public void setCureInfoList(List<CureInfoListBean> cureInfoList) {
        this.cureInfoList = cureInfoList;
    }

    public static class CureInfoListBean {
        /**
         * AdmType : I
         * AdmVisitStatus : A
         * ArcimDesc : 日常生活动作训练
         * BodyType :
         * CanDoNum : 2
         * DCAOEORIDR : 843111||19||1
         * GroupName : 康复治疗
         * HandelDesc :
         * OrderBillFlag : 3
         * OrderDoseQty : 1
         * OrderFreq : 每日两次
         * OrderPrice : 37
         * OrderPrior : 长期医嘱
         * OrderStatus : 核实
         * OrderStatusCode : V
         * OrderSttDate : 2020-05-25 08:00:00
         * PatientName : 刘玉龙
         * UserAdd : 宋朝霞
         * appdr : 567089
         * disposeStatCode : LongNew
         * disposeStatdesc : 新开长嘱^#eeee00
         * reloc : 运动治疗科1（PT1）
         * sumDose : 37
         * tradmesage : 运动治疗科1（PT1） 刘畅
         */

        private String AdmType;
        private String AdmVisitStatus;
        private String ArcimDesc;
        private String BodyType;
        private String CanDoNum;
        private String DCAOEORIDR;
        private String GroupName;
        private String HandelDesc;
        private String OrderBillFlag;
        private String OrderDoseQty;
        private String OrderFreq;
        private String OrderPrice;
        private String OrderPrior;
        private String OrderStatus;
        private String OrderStatusCode;
        private String OrderSttDate;
        private String PatientName;
        private String UserAdd;
        private String appdr;
        private String disposeStatCode;
        private String disposeStatdesc;
        private String reloc;
        private String sumDose;
        private String tradmesage;
        private String select="0";

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }
        public String getAdmType() {
            return AdmType;
        }

        public void setAdmType(String AdmType) {
            this.AdmType = AdmType;
        }

        public String getAdmVisitStatus() {
            return AdmVisitStatus;
        }

        public void setAdmVisitStatus(String AdmVisitStatus) {
            this.AdmVisitStatus = AdmVisitStatus;
        }

        public String getArcimDesc() {
            return ArcimDesc;
        }

        public void setArcimDesc(String ArcimDesc) {
            this.ArcimDesc = ArcimDesc;
        }

        public String getBodyType() {
            return BodyType;
        }

        public void setBodyType(String BodyType) {
            this.BodyType = BodyType;
        }

        public String getCanDoNum() {
            return CanDoNum;
        }

        public void setCanDoNum(String CanDoNum) {
            this.CanDoNum = CanDoNum;
        }

        public String getDCAOEORIDR() {
            return DCAOEORIDR;
        }

        public void setDCAOEORIDR(String DCAOEORIDR) {
            this.DCAOEORIDR = DCAOEORIDR;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String GroupName) {
            this.GroupName = GroupName;
        }

        public String getHandelDesc() {
            return HandelDesc;
        }

        public void setHandelDesc(String HandelDesc) {
            this.HandelDesc = HandelDesc;
        }

        public String getOrderBillFlag() {
            return OrderBillFlag;
        }

        public void setOrderBillFlag(String OrderBillFlag) {
            this.OrderBillFlag = OrderBillFlag;
        }

        public String getOrderDoseQty() {
            return OrderDoseQty;
        }

        public void setOrderDoseQty(String OrderDoseQty) {
            this.OrderDoseQty = OrderDoseQty;
        }

        public String getOrderFreq() {
            return OrderFreq;
        }

        public void setOrderFreq(String OrderFreq) {
            this.OrderFreq = OrderFreq;
        }

        public String getOrderPrice() {
            return OrderPrice;
        }

        public void setOrderPrice(String OrderPrice) {
            this.OrderPrice = OrderPrice;
        }

        public String getOrderPrior() {
            return OrderPrior;
        }

        public void setOrderPrior(String OrderPrior) {
            this.OrderPrior = OrderPrior;
        }

        public String getOrderStatus() {
            return OrderStatus;
        }

        public void setOrderStatus(String OrderStatus) {
            this.OrderStatus = OrderStatus;
        }

        public String getOrderStatusCode() {
            return OrderStatusCode;
        }

        public void setOrderStatusCode(String OrderStatusCode) {
            this.OrderStatusCode = OrderStatusCode;
        }

        public String getOrderSttDate() {
            return OrderSttDate;
        }

        public void setOrderSttDate(String OrderSttDate) {
            this.OrderSttDate = OrderSttDate;
        }

        public String getPatientName() {
            return PatientName;
        }

        public void setPatientName(String PatientName) {
            this.PatientName = PatientName;
        }

        public String getUserAdd() {
            return UserAdd;
        }

        public void setUserAdd(String UserAdd) {
            this.UserAdd = UserAdd;
        }

        public String getAppdr() {
            return appdr;
        }

        public void setAppdr(String appdr) {
            this.appdr = appdr;
        }

        public String getDisposeStatCode() {
            return disposeStatCode;
        }

        public void setDisposeStatCode(String disposeStatCode) {
            this.disposeStatCode = disposeStatCode;
        }

        public String getDisposeStatdesc() {
            return disposeStatdesc;
        }

        public void setDisposeStatdesc(String disposeStatdesc) {
            this.disposeStatdesc = disposeStatdesc;
        }

        public String getReloc() {
            return reloc;
        }

        public void setReloc(String reloc) {
            this.reloc = reloc;
        }

        public String getSumDose() {
            return sumDose;
        }

        public void setSumDose(String sumDose) {
            this.sumDose = sumDose;
        }

        public String getTradmesage() {
            return tradmesage;
        }

        public void setTradmesage(String tradmesage) {
            this.tradmesage = tradmesage;
        }
    }
}
