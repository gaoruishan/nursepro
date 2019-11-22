package com.dhcc.module.health.workarea.orderexecute.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-10-30/16:00
 * @email:grs0515@163.com
 */
public class OrdExecuteBean extends CommResult {

    /**
     * ordList : [{"ArcimDescList":[{"ArcimDesc":"血常规"}],"AuditLabDateTime":"2019-11-15 15:16","AuditLabUser":"Demo Group","CreateDateTime":"","CtcpDesc":"医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"","EpisodeID":"165","LabColor":"#8e7cc3","LabNo":"1000004049","Notes":" ","OeoriId":"144||13||1","PhOrdQtyUnit":"总量1 ","PhcinDesc":"","SpecDesc":"全血"},{"ArcimDescList":[{"ArcimDesc":"肝功能"}],"AuditLabDateTime":"2019-11-15 15:16","AuditLabUser":"Demo Group","CreateDateTime":"","CtcpDesc":"医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"","EpisodeID":"165","LabColor":"#8e7cc3","LabNo":"1000004050","Notes":" ","OeoriId":"144||14||1","PhOrdQtyUnit":"总量1 ","PhcinDesc":"","SpecDesc":"血清"},{"ArcimDescList":[{"ArcimDesc":"血糖测定(1h)"}],"AuditLabDateTime":"2019-11-15 17:05","AuditLabUser":"护士01","CreateDateTime":"","CtcpDesc":"医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"","EpisodeID":"165","LabColor":"#0000ff","LabNo":"1000004052","Notes":" ","OeoriId":"144||18||1","PhOrdQtyUnit":"总量1 ","PhcinDesc":"","SpecDesc":"血浆"},{"ArcimDescList":[{"ArcimDesc":"血常规"}],"AuditLabDateTime":"2019-11-15 17:05","AuditLabUser":"护士01","CreateDateTime":"","CtcpDesc":"急诊医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"","EpisodeID":"139","LabColor":"","LabNo":"1000003770","Notes":" ","OeoriId":"131||3||1","PhOrdQtyUnit":"总量1 ","PhcinDesc":"","SpecDesc":"引流液"}]
     * patInfo : {"PatAge":"31岁","PatName":"宇宇宇001","PatRegNo":"0000000097","PatSex":"未知性别"}
     */

    private PatInfoBean patInfo;
    private List<OrdListBean> ordList;

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

    public static class PatInfoBean {
        /**
         * PatAge : 31岁
         * PatName : 宇宇宇001
         * PatRegNo : 0000000097
         * PatSex : 未知性别
         */

        private String PatAge;
        private String PatName;
        private String PatRegNo;
        private String PatSex;

        public String getPatAge() {
            return PatAge;
        }

        public void setPatAge(String PatAge) {
            this.PatAge = PatAge;
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

    public static class OrdListBean {
        /**
         * ArcimDescList : [{"ArcimDesc":"血常规"}]
         * AuditLabDateTime : 2019-11-15 15:16
         * AuditLabUser : Demo Group
         * CreateDateTime :
         * CtcpDesc : 医生01
         * DisposeStatDesc : 需处理临嘱
         * DoseQtyUnit :
         * EpisodeID : 165
         * LabColor : #8e7cc3
         * LabNo : 1000004049
         * Notes :
         * OeoriId : 144||13||1
         * PhOrdQtyUnit : 总量1
         * PhcinDesc :
         * SpecDesc : 全血
         */

        private List<ArcimDescListBean> ArcimDescList; //医嘱名称
        private String OeoriId;//执行记录ID

        private String appdr;// 申请单ID
        private String CanDoNum;// 当日可执行次数
        private String ArcimDesc;// 项目描述
        private String OrderStatus;//医嘱状态
        private String OrderStatusCode;//医嘱状态的CODE
        private String GroupName;//服务组
        private String reloc;//接收科室
        private String AdmVisitStatus;//就诊状态
        private String OrderPrior;// 医嘱类型
        private String OE_OrdItem;// 医嘱ID
        private String OrderFreq;// 治疗频次
        private String AdmType;//就诊类型
        private String PatientName;//姓名
        private String BodyType;//部位
        private String HandelDesc;//开启状态
        private String tradmesage;//分配信息
        private String UserAdd;//申请人
        private String OrderDoseQty;//单次数量
        private String OrderPrice;//单次金额,
        private String sumDose;//单次总金额,
        private String OrderBillFlag;// 计费状态信息
        private String OrderSttDate;//开始日期

        public String getAppdr() {
            return appdr == null ? "" : appdr;
        }

        public void setAppdr(String appdr) {
            this.appdr = appdr;
        }

        public String getCanDoNum() {
            return CanDoNum == null ? "" : CanDoNum;
        }

        public void setCanDoNum(String canDoNum) {
            CanDoNum = canDoNum;
        }

        public String getArcimDesc() {
            return ArcimDesc == null ? "" : ArcimDesc;
        }

        public void setArcimDesc(String arcimDesc) {
            ArcimDesc = arcimDesc;
        }

        public String getOrderStatus() {
            return OrderStatus == null ? "" : OrderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            OrderStatus = orderStatus;
        }

        public String getOrderStatusCode() {
            return OrderStatusCode == null ? "" : OrderStatusCode;
        }

        public void setOrderStatusCode(String orderStatusCode) {
            OrderStatusCode = orderStatusCode;
        }

        public String getGroupName() {
            return GroupName == null ? "" : GroupName;
        }

        public void setGroupName(String groupName) {
            GroupName = groupName;
        }

        public String getReloc() {
            return reloc == null ? "" : reloc;
        }

        public void setReloc(String reloc) {
            this.reloc = reloc;
        }

        public String getAdmVisitStatus() {
            return AdmVisitStatus == null ? "" : AdmVisitStatus;
        }

        public void setAdmVisitStatus(String admVisitStatus) {
            AdmVisitStatus = admVisitStatus;
        }

        public String getOrderPrior() {
            return OrderPrior == null ? "" : OrderPrior;
        }

        public void setOrderPrior(String orderPrior) {
            OrderPrior = orderPrior;
        }

        public String getOE_OrdItem() {
            return OE_OrdItem == null ? "" : OE_OrdItem;
        }

        public void setOE_OrdItem(String OE_OrdItem) {
            this.OE_OrdItem = OE_OrdItem;
        }

        public String getOrderFreq() {
            return OrderFreq == null ? "" : OrderFreq;
        }

        public void setOrderFreq(String orderFreq) {
            OrderFreq = orderFreq;
        }

        public String getAdmType() {
            return AdmType == null ? "" : AdmType;
        }

        public void setAdmType(String admType) {
            AdmType = admType;
        }

        public String getPatientName() {
            return PatientName == null ? "" : PatientName;
        }

        public void setPatientName(String patientName) {
            PatientName = patientName;
        }

        public String getBodyType() {
            return BodyType == null ? "" : BodyType;
        }

        public void setBodyType(String bodyType) {
            BodyType = bodyType;
        }

        public String getHandelDesc() {
            return HandelDesc == null ? "" : HandelDesc;
        }

        public void setHandelDesc(String handelDesc) {
            HandelDesc = handelDesc;
        }

        public String getTradmesage() {
            return tradmesage == null ? "" : tradmesage;
        }

        public void setTradmesage(String tradmesage) {
            this.tradmesage = tradmesage;
        }

        public String getUserAdd() {
            return UserAdd == null ? "" : UserAdd;
        }

        public void setUserAdd(String userAdd) {
            UserAdd = userAdd;
        }

        public String getOrderDoseQty() {
            return OrderDoseQty == null ? "" : OrderDoseQty;
        }

        public void setOrderDoseQty(String orderDoseQty) {
            OrderDoseQty = orderDoseQty;
        }

        public String getOrderPrice() {
            return OrderPrice == null ? "" : OrderPrice;
        }

        public void setOrderPrice(String orderPrice) {
            OrderPrice = orderPrice;
        }

        public String getSumDose() {
            return sumDose == null ? "" : sumDose;
        }

        public void setSumDose(String sumDose) {
            this.sumDose = sumDose;
        }

        public String getOrderBillFlag() {
            return OrderBillFlag == null ? "" : OrderBillFlag;
        }

        public void setOrderBillFlag(String orderBillFlag) {
            OrderBillFlag = orderBillFlag;
        }

        public String getOrderSttDate() {
            return OrderSttDate == null ? "" : OrderSttDate;
        }

        public void setOrderSttDate(String orderSttDate) {
            OrderSttDate = orderSttDate;
        }

        public String getOeoriId() {
            return OeoriId;
        }

        public void setOeoriId(String OeoriId) {
            this.OeoriId = OeoriId;
        }

        public List<ArcimDescListBean> getArcimDescList() {
            return ArcimDescList;
        }

        public void setArcimDescList(List<ArcimDescListBean> ArcimDescList) {
            this.ArcimDescList = ArcimDescList;
        }

        public static class ArcimDescListBean {
            /**
             * ArcimDesc : 血常规
             */

            private String ArcimDesc;

            public String getArcimDesc() {
                return ArcimDesc;
            }

            public void setArcimDesc(String ArcimDesc) {
                this.ArcimDesc = ArcimDesc;
            }
        }
    }
}
