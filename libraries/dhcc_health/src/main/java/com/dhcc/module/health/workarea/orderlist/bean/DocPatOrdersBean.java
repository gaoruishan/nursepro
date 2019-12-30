package com.dhcc.module.health.workarea.orderlist.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.health.workarea.patlist.api
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:15:53
 */
public class DocPatOrdersBean extends CommResult {
    private List<CureInfoListBean> cureInfoList;

    public List<CureInfoListBean> getCureInfoList() {
        return cureInfoList;
    }

    public void setCureInfoList(List<CureInfoListBean> cureInfoList) {
        this.cureInfoList = cureInfoList;
    }

    public static class CureInfoListBean {
        /**
         * AdmType : 每日一次
         * AdmVisitStatus : 中医科门诊
         * ArcimDesc : 7
         * BodyType : 于淑英
         * CanDoNum : 38574
         * DCAOEORIDR : 临时医嘱
         * GroupName : V
         * HandelDesc :
         * OrderBillFlag : 23
         * OrderDoseQty : 后贵萍
         * OrderFreq : 312391||6
         * OrderPrice : 1
         * OrderPrior : A
         * OrderStatus : 隔物灸治疗
         * OrderStatusCode : 核实
         * OrderSttDate : 0
         * PatientName : O
         * UserAdd :
         * appdr : 1
         * reloc : 中医治疗
         * sumDose : 23
         * tradmesage : 未开启
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
