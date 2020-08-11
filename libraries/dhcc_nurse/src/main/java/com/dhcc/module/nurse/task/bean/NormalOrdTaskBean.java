package com.dhcc.module.nurse.task.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:15:01
 */
public class NormalOrdTaskBean extends CommResult {

    private List<BodyUnExecBean> bodyUnExec;

    public List<BodyUnExecBean> getBodyUnExec() {
        return bodyUnExec;
    }

    public void setBodyUnExec(List<BodyUnExecBean> bodyUnExec) {
        this.bodyUnExec = bodyUnExec;
    }

    public static class BodyUnExecBean {
        /**
         * code : 9738||1
         * name : 雾化吸入
         * orders : [[{"orderInfo":{"DspStat":"未发","Durat":"","EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"737||16||315","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"布地奈德气雾剂(吉舒)[20MG:20ML]","bedCode":"28","cancelReason":"","createDateTime":"2020-04-23 15:10:55","ctcpDesc":"智亚芹","disposeStatCode":"非新长嘱^#eead0e","disposeStatdesc":"LongUnnew","doseQtyUnit":"100 ml","examInfo":{},"exeStColor":"#02C874","exeStatus":"未","execCtcpDesc":"","execDateTime":"","execID":"737||16||315","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","flowSpeed":"","icuFlag":"1","labNo":"","medCareNo":"100158","no":"15","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"737||16","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"杨勤测三","phOrdQtyUnit":"300 ml","phcfrCode":"Tid","phcinDesc":"喷雾吸入","placerNo":"","preparedFlag":"","prescNo":"I200423000085","price":"57.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000411 ","seeOrderUserDateTime":"2020-07-07 08:47:00","seeOrderUserName":"护士01","seqNo":"737||16","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2020-08-10 08:00","sttTime":"08:00","totalAmount":"57.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"","Durat":"","EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"737||17||315","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"雾化吸入","bedCode":"28","cancelReason":"","createDateTime":"2020-04-23 15:10:55","ctcpDesc":"智亚芹","doseQtyUnit":"2 ","examInfo":{},"exeStColor":"#02C874","exeStatus":"未","execCtcpDesc":"","execDateTime":"","execID":"737||17||315","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","flowSpeed":"","icuFlag":"1","labNo":"","medCareNo":"100158","no":"15.1","notes":"","notifyClinician":"","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"737||17","ordStatDesc":"核实","ordTyp":"N","patIdentity":"全自费","patName":"杨勤测三","phOrdQtyUnit":"1 ","phcfrCode":"Tid","phcinDesc":"","placerNo":"","preparedFlag":"","prescNo":"","price":"2.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000411 ","seeOrderUserDateTime":"2020-07-07 08:47:00","seeOrderUserName":"护士01","seqNo":"737||16","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2020-08-10 08:00","sttTime":"08:00","totalAmount":"2.00","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"","EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"737||16||316","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"布地奈德气雾剂(吉舒)[20MG:20ML]","bedCode":"28","cancelReason":"","createDateTime":"2020-04-23 15:10:55","ctcpDesc":"智亚芹","disposeStatCode":"非新长嘱^#eead0e","disposeStatdesc":"LongUnnew","doseQtyUnit":"100 ml","examInfo":{},"exeStColor":"#02C874","exeStatus":"未","execCtcpDesc":"","execDateTime":"","execID":"737||16||316","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","flowSpeed":"","icuFlag":"1","labNo":"","medCareNo":"100158","no":"15","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"737||16","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"杨勤测三","phOrdQtyUnit":"300 ml","phcfrCode":"Tid","phcinDesc":"喷雾吸入","placerNo":"","preparedFlag":"","prescNo":"I200423000085","price":"57.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000411 ","seeOrderUserDateTime":"2020-07-07 08:47:00","seeOrderUserName":"护士01","seqNo":"737||16","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2020-08-10 12:00","sttTime":"12:00","totalAmount":"57.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"","Durat":"","EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"737||17||316","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"雾化吸入","bedCode":"28","cancelReason":"","createDateTime":"2020-04-23 15:10:55","ctcpDesc":"智亚芹","doseQtyUnit":"2 ","examInfo":{},"exeStColor":"#02C874","exeStatus":"未","execCtcpDesc":"","execDateTime":"","execID":"737||17||316","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","flowSpeed":"","icuFlag":"1","labNo":"","medCareNo":"100158","no":"15.1","notes":"","notifyClinician":"","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"737||17","ordStatDesc":"核实","ordTyp":"N","patIdentity":"全自费","patName":"杨勤测三","phOrdQtyUnit":"1 ","phcfrCode":"Tid","phcinDesc":"","placerNo":"","preparedFlag":"","prescNo":"","price":"2.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000411 ","seeOrderUserDateTime":"2020-07-07 08:47:00","seeOrderUserName":"护士01","seqNo":"737||16","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2020-08-10 12:00","sttTime":"12:00","totalAmount":"2.00","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"","EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"737||16||317","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"布地奈德气雾剂(吉舒)[20MG:20ML]","bedCode":"28","cancelReason":"","createDateTime":"2020-04-23 15:10:55","ctcpDesc":"智亚芹","disposeStatCode":"非新长嘱^#eead0e","disposeStatdesc":"LongUnnew","doseQtyUnit":"100 ml","examInfo":{},"exeStColor":"#02C874","exeStatus":"未","execCtcpDesc":"","execDateTime":"","execID":"737||16||317","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","flowSpeed":"","icuFlag":"1","labNo":"","medCareNo":"100158","no":"15","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"737||16","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"杨勤测三","phOrdQtyUnit":"300 ml","phcfrCode":"Tid","phcinDesc":"喷雾吸入","placerNo":"","preparedFlag":"","prescNo":"I200423000085","price":"57.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000411 ","seeOrderUserDateTime":"2020-07-07 08:47:00","seeOrderUserName":"护士01","seqNo":"737||16","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2020-08-10 16:00","sttTime":"16:00","totalAmount":"57.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"","Durat":"","EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"737||17||317","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"雾化吸入","bedCode":"28","cancelReason":"","createDateTime":"2020-04-23 15:10:55","ctcpDesc":"智亚芹","doseQtyUnit":"2 ","examInfo":{},"exeStColor":"#02C874","exeStatus":"未","execCtcpDesc":"","execDateTime":"","execID":"737||17||317","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","flowSpeed":"","icuFlag":"1","labNo":"","medCareNo":"100158","no":"15.1","notes":"","notifyClinician":"","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"737||17","ordStatDesc":"核实","ordTyp":"N","patIdentity":"全自费","patName":"杨勤测三","phOrdQtyUnit":"1 ","phcfrCode":"Tid","phcinDesc":"","placerNo":"","preparedFlag":"","prescNo":"","price":"2.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000411 ","seeOrderUserDateTime":"2020-07-07 08:47:00","seeOrderUserName":"护士01","seqNo":"737||16","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2020-08-10 16:00","sttTime":"16:00","totalAmount":"2.00","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"child"}]]
         * value : 3
         */

        private String code;
        private String name;
        private String value;
        private List<List<OrdersBean>> orders;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<List<OrdersBean>> getOrders() {
            return orders;
        }

        public void setOrders(List<List<OrdersBean>> orders) {
            this.orders = orders;
        }

        public static class OrdersBean {
            /**
             * orderInfo : {"DspStat":"未发","Durat":"","EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"737||16||315","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"布地奈德气雾剂(吉舒)[20MG:20ML]","bedCode":"28","cancelReason":"","createDateTime":"2020-04-23 15:10:55","ctcpDesc":"智亚芹","disposeStatCode":"非新长嘱^#eead0e","disposeStatdesc":"LongUnnew","doseQtyUnit":"100 ml","examInfo":{},"exeStColor":"#02C874","exeStatus":"未","execCtcpDesc":"","execDateTime":"","execID":"737||16||315","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","flowSpeed":"","icuFlag":"1","labNo":"","medCareNo":"100158","no":"15","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"737||16","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"杨勤测三","phOrdQtyUnit":"300 ml","phcfrCode":"Tid","phcinDesc":"喷雾吸入","placerNo":"","preparedFlag":"","prescNo":"I200423000085","price":"57.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000411 ","seeOrderUserDateTime":"2020-07-07 08:47:00","seeOrderUserName":"护士01","seqNo":"737||16","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2020-08-10 08:00","sttTime":"08:00","totalAmount":"57.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""}
             * type : main
             */

            private OrderInfoBean orderInfo;
            private String type;
            private Boolean select = false;

            public Boolean getSelect() {
                return select;
            }

            public void setSelect(Boolean select) {
                this.select = select;
            }

            public OrderInfoBean getOrderInfo() {
                return orderInfo;
            }

            public void setOrderInfo(OrderInfoBean orderInfo) {
                this.orderInfo = orderInfo;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class OrderInfoBean {
                /**
                 * DspStat : 未发
                 * Durat :
                 * EncryptLevel :
                 * FuHeDate :
                 * FuHeTime :
                 * FuHeUser :
                 * ID : 737||16||315
                 * PatLevel :
                 * PeiYeDate :
                 * PeiYeTime :
                 * PeiYeUser :
                 * PreDisDateTime :
                 * addOrdBtn : 补费用
                 * admLoc : 内分泌科
                 * age : 22岁
                 * arcimDesc : 布地奈德气雾剂(吉舒)[20MG:20ML]
                 * bedCode : 28
                 * cancelReason :
                 * createDateTime : 2020-04-23 15:10:55
                 * ctcpDesc : 智亚芹
                 * disposeStatCode : 非新长嘱^#eead0e
                 * disposeStatdesc : LongUnnew
                 * doseQtyUnit : 100 ml
                 * examInfo : {}
                 * exeStColor : #02C874
                 * exeStatus : 未
                 * execCtcpDesc :
                 * execDateTime :
                 * execID : 737||16||315
                 * execXDateTime :
                 * execXUserDesc :
                 * filteFlagExtend :
                 * flowSpeed :
                 * icuFlag : 1
                 * labNo :
                 * medCareNo : 100158
                 * no : 15
                 * notes :
                 * notifyClinician : N
                 * oecprDesc : 长期医嘱
                 * orcatDesc : 西药
                 * ordDep : 内分泌科
                 * ordID : 737||16
                 * ordStatDesc : 核实
                 * ordTyp : R
                 * patIdentity : 全自费
                 * patName : 杨勤测三
                 * phOrdQtyUnit : 300 ml
                 * phcfrCode : Tid
                 * phcinDesc : 喷雾吸入
                 * placerNo :
                 * preparedFlag :
                 * prescNo : I200423000085
                 * price : 57.70
                 * printFlag :
                 * reclocDesc : 住院药房
                 * regNo : 0000000411
                 * seeOrderUserDateTime : 2020-07-07 08:47:00
                 * seeOrderUserName : 护士01
                 * seqNo : 737||16
                 * skinTestInfo :
                 * skinTestNumber :
                 * specCollDateTime :
                 * specDesc :
                 * sttDate : 08-10
                 * sttDateTime : 2020-08-10 08:00
                 * sttTime : 08:00
                 * totalAmount : 57.70
                 * tubeColor :
                 * tubeColorCode :
                 * tubeColorDesc :
                 * verifyFlag :
                 * xCtcpDesc :
                 * xDateTime :
                 */

                private String DspStat;
                private String Durat;
                private String EncryptLevel;
                private String FuHeDate;
                private String FuHeTime;
                private String FuHeUser;
                private String ID;
                private String PatLevel;
                private String PeiYeDate;
                private String PeiYeTime;
                private String PeiYeUser;
                private String PreDisDateTime;
                private String addOrdBtn;
                private String admLoc;
                private String age;
                private String arcimDesc;
                private String bedCode;
                private String cancelReason;
                private String createDateTime;
                private String ctcpDesc;
                private String disposeStatCode;
                private String disposeStatdesc;
                private String doseQtyUnit;
                private ExamInfoBean examInfo;
                private String exeStColor;
                private String exeStatus;
                private String execCtcpDesc;
                private String execDateTime;
                private String execID;
                private String execXDateTime;
                private String execXUserDesc;
                private String filteFlagExtend;
                private String flowSpeed;
                private String icuFlag;
                private String labNo;
                private String medCareNo;
                private String no;
                private String notes;
                private String notifyClinician;
                private String oecprDesc;
                private String orcatDesc;
                private String ordDep;
                private String ordID;
                private String ordStatDesc;
                private String ordTyp;
                private String patIdentity;
                private String patName;
                private String phOrdQtyUnit;
                private String phcfrCode;
                private String phcinDesc;
                private String placerNo;
                private String preparedFlag;
                private String prescNo;
                private String price;
                private String printFlag;
                private String reclocDesc;
                private String regNo;
                private String seeOrderUserDateTime;
                private String seeOrderUserName;
                private String seqNo;
                private String skinTestInfo;
                private String skinTestNumber;
                private String specCollDateTime;
                private String specDesc;
                private String sttDate;
                private String sttDateTime;
                private String sttTime;
                private String totalAmount;
                private String tubeColor;
                private String tubeColorCode;
                private String tubeColorDesc;
                private String verifyFlag;
                private String xCtcpDesc;
                private String xDateTime;

                public String getDspStat() {
                    return DspStat;
                }

                public void setDspStat(String DspStat) {
                    this.DspStat = DspStat;
                }

                public String getDurat() {
                    return Durat;
                }

                public void setDurat(String Durat) {
                    this.Durat = Durat;
                }

                public String getEncryptLevel() {
                    return EncryptLevel;
                }

                public void setEncryptLevel(String EncryptLevel) {
                    this.EncryptLevel = EncryptLevel;
                }

                public String getFuHeDate() {
                    return FuHeDate;
                }

                public void setFuHeDate(String FuHeDate) {
                    this.FuHeDate = FuHeDate;
                }

                public String getFuHeTime() {
                    return FuHeTime;
                }

                public void setFuHeTime(String FuHeTime) {
                    this.FuHeTime = FuHeTime;
                }

                public String getFuHeUser() {
                    return FuHeUser;
                }

                public void setFuHeUser(String FuHeUser) {
                    this.FuHeUser = FuHeUser;
                }

                public String getID() {
                    return ID;
                }

                public void setID(String ID) {
                    this.ID = ID;
                }

                public String getPatLevel() {
                    return PatLevel;
                }

                public void setPatLevel(String PatLevel) {
                    this.PatLevel = PatLevel;
                }

                public String getPeiYeDate() {
                    return PeiYeDate;
                }

                public void setPeiYeDate(String PeiYeDate) {
                    this.PeiYeDate = PeiYeDate;
                }

                public String getPeiYeTime() {
                    return PeiYeTime;
                }

                public void setPeiYeTime(String PeiYeTime) {
                    this.PeiYeTime = PeiYeTime;
                }

                public String getPeiYeUser() {
                    return PeiYeUser;
                }

                public void setPeiYeUser(String PeiYeUser) {
                    this.PeiYeUser = PeiYeUser;
                }

                public String getPreDisDateTime() {
                    return PreDisDateTime;
                }

                public void setPreDisDateTime(String PreDisDateTime) {
                    this.PreDisDateTime = PreDisDateTime;
                }

                public String getAddOrdBtn() {
                    return addOrdBtn;
                }

                public void setAddOrdBtn(String addOrdBtn) {
                    this.addOrdBtn = addOrdBtn;
                }

                public String getAdmLoc() {
                    return admLoc;
                }

                public void setAdmLoc(String admLoc) {
                    this.admLoc = admLoc;
                }

                public String getAge() {
                    return age;
                }

                public void setAge(String age) {
                    this.age = age;
                }

                public String getArcimDesc() {
                    return arcimDesc;
                }

                public void setArcimDesc(String arcimDesc) {
                    this.arcimDesc = arcimDesc;
                }

                public String getBedCode() {
                    return bedCode;
                }

                public void setBedCode(String bedCode) {
                    this.bedCode = bedCode;
                }

                public String getCancelReason() {
                    return cancelReason;
                }

                public void setCancelReason(String cancelReason) {
                    this.cancelReason = cancelReason;
                }

                public String getCreateDateTime() {
                    return createDateTime;
                }

                public void setCreateDateTime(String createDateTime) {
                    this.createDateTime = createDateTime;
                }

                public String getCtcpDesc() {
                    return ctcpDesc;
                }

                public void setCtcpDesc(String ctcpDesc) {
                    this.ctcpDesc = ctcpDesc;
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

                public String getDoseQtyUnit() {
                    return doseQtyUnit;
                }

                public void setDoseQtyUnit(String doseQtyUnit) {
                    this.doseQtyUnit = doseQtyUnit;
                }

                public ExamInfoBean getExamInfo() {
                    return examInfo;
                }

                public void setExamInfo(ExamInfoBean examInfo) {
                    this.examInfo = examInfo;
                }

                public String getExeStColor() {
                    return exeStColor;
                }

                public void setExeStColor(String exeStColor) {
                    this.exeStColor = exeStColor;
                }

                public String getExeStatus() {
                    return exeStatus;
                }

                public void setExeStatus(String exeStatus) {
                    this.exeStatus = exeStatus;
                }

                public String getExecCtcpDesc() {
                    return execCtcpDesc;
                }

                public void setExecCtcpDesc(String execCtcpDesc) {
                    this.execCtcpDesc = execCtcpDesc;
                }

                public String getExecDateTime() {
                    return execDateTime;
                }

                public void setExecDateTime(String execDateTime) {
                    this.execDateTime = execDateTime;
                }

                public String getExecID() {
                    return execID;
                }

                public void setExecID(String execID) {
                    this.execID = execID;
                }

                public String getExecXDateTime() {
                    return execXDateTime;
                }

                public void setExecXDateTime(String execXDateTime) {
                    this.execXDateTime = execXDateTime;
                }

                public String getExecXUserDesc() {
                    return execXUserDesc;
                }

                public void setExecXUserDesc(String execXUserDesc) {
                    this.execXUserDesc = execXUserDesc;
                }

                public String getFilteFlagExtend() {
                    return filteFlagExtend;
                }

                public void setFilteFlagExtend(String filteFlagExtend) {
                    this.filteFlagExtend = filteFlagExtend;
                }

                public String getFlowSpeed() {
                    return flowSpeed;
                }

                public void setFlowSpeed(String flowSpeed) {
                    this.flowSpeed = flowSpeed;
                }

                public String getIcuFlag() {
                    return icuFlag;
                }

                public void setIcuFlag(String icuFlag) {
                    this.icuFlag = icuFlag;
                }

                public String getLabNo() {
                    return labNo;
                }

                public void setLabNo(String labNo) {
                    this.labNo = labNo;
                }

                public String getMedCareNo() {
                    return medCareNo;
                }

                public void setMedCareNo(String medCareNo) {
                    this.medCareNo = medCareNo;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getNotes() {
                    return notes;
                }

                public void setNotes(String notes) {
                    this.notes = notes;
                }

                public String getNotifyClinician() {
                    return notifyClinician;
                }

                public void setNotifyClinician(String notifyClinician) {
                    this.notifyClinician = notifyClinician;
                }

                public String getOecprDesc() {
                    return oecprDesc;
                }

                public void setOecprDesc(String oecprDesc) {
                    this.oecprDesc = oecprDesc;
                }

                public String getOrcatDesc() {
                    return orcatDesc;
                }

                public void setOrcatDesc(String orcatDesc) {
                    this.orcatDesc = orcatDesc;
                }

                public String getOrdDep() {
                    return ordDep;
                }

                public void setOrdDep(String ordDep) {
                    this.ordDep = ordDep;
                }

                public String getOrdID() {
                    return ordID;
                }

                public void setOrdID(String ordID) {
                    this.ordID = ordID;
                }

                public String getOrdStatDesc() {
                    return ordStatDesc;
                }

                public void setOrdStatDesc(String ordStatDesc) {
                    this.ordStatDesc = ordStatDesc;
                }

                public String getOrdTyp() {
                    return ordTyp;
                }

                public void setOrdTyp(String ordTyp) {
                    this.ordTyp = ordTyp;
                }

                public String getPatIdentity() {
                    return patIdentity;
                }

                public void setPatIdentity(String patIdentity) {
                    this.patIdentity = patIdentity;
                }

                public String getPatName() {
                    return patName;
                }

                public void setPatName(String patName) {
                    this.patName = patName;
                }

                public String getPhOrdQtyUnit() {
                    return phOrdQtyUnit;
                }

                public void setPhOrdQtyUnit(String phOrdQtyUnit) {
                    this.phOrdQtyUnit = phOrdQtyUnit;
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

                public String getPlacerNo() {
                    return placerNo;
                }

                public void setPlacerNo(String placerNo) {
                    this.placerNo = placerNo;
                }

                public String getPreparedFlag() {
                    return preparedFlag;
                }

                public void setPreparedFlag(String preparedFlag) {
                    this.preparedFlag = preparedFlag;
                }

                public String getPrescNo() {
                    return prescNo;
                }

                public void setPrescNo(String prescNo) {
                    this.prescNo = prescNo;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getPrintFlag() {
                    return printFlag;
                }

                public void setPrintFlag(String printFlag) {
                    this.printFlag = printFlag;
                }

                public String getReclocDesc() {
                    return reclocDesc;
                }

                public void setReclocDesc(String reclocDesc) {
                    this.reclocDesc = reclocDesc;
                }

                public String getRegNo() {
                    return regNo;
                }

                public void setRegNo(String regNo) {
                    this.regNo = regNo;
                }

                public String getSeeOrderUserDateTime() {
                    return seeOrderUserDateTime;
                }

                public void setSeeOrderUserDateTime(String seeOrderUserDateTime) {
                    this.seeOrderUserDateTime = seeOrderUserDateTime;
                }

                public String getSeeOrderUserName() {
                    return seeOrderUserName;
                }

                public void setSeeOrderUserName(String seeOrderUserName) {
                    this.seeOrderUserName = seeOrderUserName;
                }

                public String getSeqNo() {
                    return seqNo;
                }

                public void setSeqNo(String seqNo) {
                    this.seqNo = seqNo;
                }

                public String getSkinTestInfo() {
                    return skinTestInfo;
                }

                public void setSkinTestInfo(String skinTestInfo) {
                    this.skinTestInfo = skinTestInfo;
                }

                public String getSkinTestNumber() {
                    return skinTestNumber;
                }

                public void setSkinTestNumber(String skinTestNumber) {
                    this.skinTestNumber = skinTestNumber;
                }

                public String getSpecCollDateTime() {
                    return specCollDateTime;
                }

                public void setSpecCollDateTime(String specCollDateTime) {
                    this.specCollDateTime = specCollDateTime;
                }

                public String getSpecDesc() {
                    return specDesc;
                }

                public void setSpecDesc(String specDesc) {
                    this.specDesc = specDesc;
                }

                public String getSttDate() {
                    return sttDate;
                }

                public void setSttDate(String sttDate) {
                    this.sttDate = sttDate;
                }

                public String getSttDateTime() {
                    return sttDateTime;
                }

                public void setSttDateTime(String sttDateTime) {
                    this.sttDateTime = sttDateTime;
                }

                public String getSttTime() {
                    return sttTime;
                }

                public void setSttTime(String sttTime) {
                    this.sttTime = sttTime;
                }

                public String getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(String totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public String getTubeColor() {
                    return tubeColor;
                }

                public void setTubeColor(String tubeColor) {
                    this.tubeColor = tubeColor;
                }

                public String getTubeColorCode() {
                    return tubeColorCode;
                }

                public void setTubeColorCode(String tubeColorCode) {
                    this.tubeColorCode = tubeColorCode;
                }

                public String getTubeColorDesc() {
                    return tubeColorDesc;
                }

                public void setTubeColorDesc(String tubeColorDesc) {
                    this.tubeColorDesc = tubeColorDesc;
                }

                public String getVerifyFlag() {
                    return verifyFlag;
                }

                public void setVerifyFlag(String verifyFlag) {
                    this.verifyFlag = verifyFlag;
                }

                public String getXCtcpDesc() {
                    return xCtcpDesc;
                }

                public void setXCtcpDesc(String xCtcpDesc) {
                    this.xCtcpDesc = xCtcpDesc;
                }

                public String getXDateTime() {
                    return xDateTime;
                }

                public void setXDateTime(String xDateTime) {
                    this.xDateTime = xDateTime;
                }

                public static class ExamInfoBean {
                }
            }
        }
    }
}
