package com.dhcc.module.health.workarea.orderexecute.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.health.workarea.orderexecute.bean
 * <p>
 * author Q
 * Date: 2019/11/25
 * Time:17:10
 */
public class OrdListBean extends CommResult {
    private List<CureInfoListBean> cureInfoList;
    private List<PatInfoListBean> patInfoList;

    public List<CureInfoListBean> getCureInfoList() {
        return cureInfoList;
    }

    public void setCureInfoList(List<CureInfoListBean> cureInfoList) {
        this.cureInfoList = cureInfoList;
    }

    public List<PatInfoListBean> getPatInfoList() {
        return patInfoList;
    }

    public void setPatInfoList(List<PatInfoListBean> patInfoList) {
        this.patInfoList = patInfoList;
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

    public static class PatInfoListBean {
        /**
         * AdmLocdesc : I
         * AdmType : 住院
         * AdmTypeDesc : 有效
         * AdmVisitStatus : 38574
         * AgeDesc : 女
         * AllNum :
         * Anum : 1
         * Appstr : 2
         * BedCode : 064021
         * BillFlag : 20
         * Fnum :
         * GroupName : 理疗科
         * MrNumGet : N
         * Num : 1
         * OPenFlagDesc : 神经康复二病区护理单元
         * OrderDateStr : 0
         * PapmiNo : 127526
         * Reloc : 普通医保
         * Tnum : 康复治疗
         * admdr : 394
         * admreasondesc : 76岁
         * admward : 神经康复中心二病区
         * name : 0000127379
         * papmidr : 695482
         * sex : 郭凤云
         */

        private String AdmLocdesc;
        private String AdmType;
        private String AdmTypeDesc;
        private String AdmVisitStatus;
        private String AgeDesc;
        private String AllNum;
        private String Anum;
        private String Appstr;
        private String BedCode;
        private String BillFlag;
        private String Fnum;
        private String GroupName;
        private String MrNumGet;
        private String Num;
        private String OPenFlagDesc;
        private String OrderDateStr;
        private String PapmiNo;
        private String Reloc;
        private String Tnum;
        private String admdr;
        private String admreasondesc;
        private String admward;
        private String name;
        private String papmidr;
        private String sex;

        public String getAdmLocdesc() {
            return AdmLocdesc;
        }

        public void setAdmLocdesc(String AdmLocdesc) {
            this.AdmLocdesc = AdmLocdesc;
        }

        public String getAdmType() {
            return AdmType;
        }

        public void setAdmType(String AdmType) {
            this.AdmType = AdmType;
        }

        public String getAdmTypeDesc() {
            return AdmTypeDesc;
        }

        public void setAdmTypeDesc(String AdmTypeDesc) {
            this.AdmTypeDesc = AdmTypeDesc;
        }

        public String getAdmVisitStatus() {
            return AdmVisitStatus;
        }

        public void setAdmVisitStatus(String AdmVisitStatus) {
            this.AdmVisitStatus = AdmVisitStatus;
        }

        public String getAgeDesc() {
            return AgeDesc;
        }

        public void setAgeDesc(String AgeDesc) {
            this.AgeDesc = AgeDesc;
        }

        public String getAllNum() {
            return AllNum;
        }

        public void setAllNum(String AllNum) {
            this.AllNum = AllNum;
        }

        public String getAnum() {
            return Anum;
        }

        public void setAnum(String Anum) {
            this.Anum = Anum;
        }

        public String getAppstr() {
            return Appstr;
        }

        public void setAppstr(String Appstr) {
            this.Appstr = Appstr;
        }

        public String getBedCode() {
            return BedCode;
        }

        public void setBedCode(String BedCode) {
            this.BedCode = BedCode;
        }

        public String getBillFlag() {
            return BillFlag;
        }

        public void setBillFlag(String BillFlag) {
            this.BillFlag = BillFlag;
        }

        public String getFnum() {
            return Fnum;
        }

        public void setFnum(String Fnum) {
            this.Fnum = Fnum;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String GroupName) {
            this.GroupName = GroupName;
        }

        public String getMrNumGet() {
            return MrNumGet;
        }

        public void setMrNumGet(String MrNumGet) {
            this.MrNumGet = MrNumGet;
        }

        public String getNum() {
            return Num;
        }

        public void setNum(String Num) {
            this.Num = Num;
        }

        public String getOPenFlagDesc() {
            return OPenFlagDesc;
        }

        public void setOPenFlagDesc(String OPenFlagDesc) {
            this.OPenFlagDesc = OPenFlagDesc;
        }

        public String getOrderDateStr() {
            return OrderDateStr;
        }

        public void setOrderDateStr(String OrderDateStr) {
            this.OrderDateStr = OrderDateStr;
        }

        public String getPapmiNo() {
            return PapmiNo;
        }

        public void setPapmiNo(String PapmiNo) {
            this.PapmiNo = PapmiNo;
        }

        public String getReloc() {
            return Reloc;
        }

        public void setReloc(String Reloc) {
            this.Reloc = Reloc;
        }

        public String getTnum() {
            return Tnum;
        }

        public void setTnum(String Tnum) {
            this.Tnum = Tnum;
        }

        public String getAdmdr() {
            return admdr;
        }

        public void setAdmdr(String admdr) {
            this.admdr = admdr;
        }

        public String getAdmreasondesc() {
            return admreasondesc;
        }

        public void setAdmreasondesc(String admreasondesc) {
            this.admreasondesc = admreasondesc;
        }

        public String getAdmward() {
            return admward;
        }

        public void setAdmward(String admward) {
            this.admward = admward;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPapmidr() {
            return papmidr;
        }

        public void setPapmidr(String papmidr) {
            this.papmidr = papmidr;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
