package com.dhcc.nursepro.workarea.Infusionsituation.bean;

import java.util.List;

public class ScanResultBean {
    /**
     * btnDesc : 已执行
     * btnMth : execOrSeeOrder
     * btnType : exed
     * canExeFlag : 0
     * diagFlag : 1
     * flag : ORD
     * ifState : Puncture
     * msg : 医嘱已执行
     * msgcode : 999999
     * orders : [{"DspStat":"未发","Durat":"1天","EncryptLevel":"","FuHeDate":"1840-12-31","FuHeTime":"00:00","FuHeUser":"","ID":"871282||2||1","PatLevel":"","PeiYeDate":"2019-11-26","PeiYeTime":"11:20","PeiYeUser":"nurse","PreDisDateTime":"","age":"60岁","arcimDesc":"谷红注射液(5ml/支)","bedCode":"01","createDateTime":"2019-11-26 09:54:50","ctcpDesc":"doctor","doseQtyUnit":"5 ml","execCtcpDesc":"nurse","execDateTime":"11-26 11:28","execID":"871282||2||1","execXDateTime":"","execXUserDesc":"","labNo":"","notes":"","oecprDesc":"长期医嘱","orcatDesc":"西药","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"入院测试1","phOrdQtyUnit":"5 ml","phcfrCode":"Qd(8:00)","phcinDesc":"静滴","placerNo":"","preparedFlag":"Y","prescNo":"I191126000001","price":"57.80","printFlag":"T","reclocDesc":"医专住院药房","regNo":"0001186245 ","seqNo":"871282||2","specCollDateTime":"","specDesc":"","sttDate":"11-27","sttDateTime":"2019-11-27 08:00","sttTime":"08:00","totalAmount":"57.80","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"N","xCtcpDesc":"","xDateTime":""},{"DspStat":"未发","Durat":"1天","EncryptLevel":"","FuHeDate":"1840-12-31","FuHeTime":"00:00","FuHeUser":"","ID":"871282||3||1","PatLevel":"","PeiYeDate":"2019-11-26","PeiYeTime":"11:20","PeiYeUser":"nurse","PreDisDateTime":"","age":"60岁","arcimDesc":"0.9%氯化钠注射液(0.9% 250ml/袋)","bedCode":"01","createDateTime":"2019-11-26 09:54:50","ctcpDesc":"doctor","doseQtyUnit":"250 ml","execCtcpDesc":"nurse","execDateTime":"11-26 11:28","execID":"871282||3||1","execXDateTime":"","execXUserDesc":"","labNo":"","notes":"","oecprDesc":"长期医嘱","orcatDesc":"西药","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"入院测试1","phOrdQtyUnit":"250 ml","phcfrCode":"Qd(8:00)","phcinDesc":"静滴","placerNo":"","preparedFlag":"Y","prescNo":"I191126000001","price":"6.50","printFlag":"T","reclocDesc":"医专住院药房","regNo":"0001186245 ","seqNo":"871282||2","specCollDateTime":"","specDesc":"","sttDate":"11-27","sttDateTime":"2019-11-27 08:00","sttTime":"08:00","totalAmount":"6.50","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"N","xCtcpDesc":"","xDateTime":""}]
     * patInfo : {"admReason":"全自费","age":"60岁","balance":"10000.00","bedCode":"01","careLevel":"","ctLocDesc":"内分泌代谢病科","currWardID":"73","diagnosis":"","dischargeStatus":"","episodeID":"924275","homeAddres":"","ifNewBaby":"N","illState":"病危","inDays":"2","inDeptDateTime":"2019-11-25 11:38","inHospDateTime":"2019-11-25 11:39:00","insuranceCard":"","mainDoctor":"于欣","mainDoctorID":["1334"],"mainNurse":"","mainNurseID":[],"medicareNo":"1057249","motherTransLoc":"","name":"入院测试1","nation":"汉族","operation":"","orderID":"871282","patLinkman":"","patientID":"1186250","personID":"","regDateTime":"2019-11-25 11:20:35","regNo":"0001186245","roomDesc":"1","sex":"男","telphone":"","totalCost":"0","wardDesc":"内分泌代谢病房"}
     * status : 0
     */

    private String btnDesc;
    private String btnMth;
    private String btnType;
    private String canExeFlag;
    private String diagFlag;
    private String flag;
    private String ifState;
    private String msg;
    private String msgcode;
    private PatInfoBean patInfo;
    private String status;
    private List<OrdersBean> orders;
    private List<SpeedUnitListBean> speedUnitList;
    private String flowSpeed;
    private String flowSpeedUnit;
    private List<StopNoteListBean> StopNoteList;
    private List<SuspendNoteListBean> SuspendNoteList;
    private List<TourNoteListBean> TourNoteList;
    private List<WayNoList> wayNoList;

    public List<WayNoList> getWayNoList() {
        return wayNoList;
    }

    public void setWayNoList(List<WayNoList> wayNoList) {
        this.wayNoList = wayNoList;
    }

    public List<TourNoteListBean> getTourNoteList() {
        return TourNoteList;
    }

    public void setTourNoteList(List<TourNoteListBean> tourNoteList) {
        TourNoteList = tourNoteList;
    }

    public List<StopNoteListBean> getStopNoteList() {
        return StopNoteList;
    }

    public void setStopNoteList(List<StopNoteListBean> StopNoteList) {
        this.StopNoteList = StopNoteList;
    }

    public List<SuspendNoteListBean> getSuspendNoteList() {
        return SuspendNoteList;
    }

    public void setSuspendNoteList(List<SuspendNoteListBean> SuspendNoteList) {
        this.SuspendNoteList = SuspendNoteList;
    }

    public String getFlowSpeed() {
        return flowSpeed;
    }

    public void setFlowSpeed(String flowSpeed) {
        this.flowSpeed = flowSpeed;
    }

    public String getFlowSpeedUnit() {
        return flowSpeedUnit;
    }

    public void setFlowSpeedUnit(String flowSpeedUnit) {
        this.flowSpeedUnit = flowSpeedUnit;
    }

    public List<SpeedUnitListBean> getSpeedUnitList() {
        return speedUnitList;
    }

    public void setSpeedUnitList(List<SpeedUnitListBean> speedUnitList) {
        this.speedUnitList = speedUnitList;
    }


    public static class SpeedUnitListBean {
        /**
         * unitDesc : 滴/秒
         * unitId : 1
         */

        private String unitDesc;
        private String unitId;

        public String getUnitDesc() {
            return unitDesc;
        }

        public void setUnitDesc(String unitDesc) {
            this.unitDesc = unitDesc;
        }

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }
    }


    public String getBtnDesc() {
        return btnDesc;
    }

    public void setBtnDesc(String btnDesc) {
        this.btnDesc = btnDesc;
    }

    public String getBtnMth() {
        return btnMth;
    }

    public void setBtnMth(String btnMth) {
        this.btnMth = btnMth;
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;
    }

    public String getCanExeFlag() {
        return canExeFlag;
    }

    public void setCanExeFlag(String canExeFlag) {
        this.canExeFlag = canExeFlag;
    }

    public String getDiagFlag() {
        return diagFlag;
    }

    public void setDiagFlag(String diagFlag) {
        this.diagFlag = diagFlag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIfState() {
        return ifState;
    }

    public void setIfState(String ifState) {
        this.ifState = ifState;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class PatInfoBean {
        /**
         * admReason : 全自费
         * age : 60岁
         * balance : 10000.00
         * bedCode : 01
         * careLevel :
         * ctLocDesc : 内分泌代谢病科
         * currWardID : 73
         * diagnosis :
         * dischargeStatus :
         * episodeID : 924275
         * homeAddres :
         * ifNewBaby : N
         * illState : 病危
         * inDays : 2
         * inDeptDateTime : 2019-11-25 11:38
         * inHospDateTime : 2019-11-25 11:39:00
         * insuranceCard :
         * mainDoctor : 于欣
         * mainDoctorID : ["1334"]
         * mainNurse :
         * mainNurseID : []
         * medicareNo : 1057249
         * motherTransLoc :
         * name : 入院测试1
         * nation : 汉族
         * operation :
         * orderID : 871282
         * patLinkman :
         * patientID : 1186250
         * personID :
         * regDateTime : 2019-11-25 11:20:35
         * regNo : 0001186245
         * roomDesc : 1
         * sex : 男
         * telphone :
         * totalCost : 0
         * wardDesc : 内分泌代谢病房
         */

        private String admReason;
        private String age;
        private String balance;
        private String bedCode;
        private String careLevel;
        private String ctLocDesc;
        private String currWardID;
        private String diagnosis;
        private String dischargeStatus;
        private String episodeID;
        private String homeAddres;
        private String ifNewBaby;
        private String illState;
        private String inDays;
        private String inDeptDateTime;
        private String inHospDateTime;
        private String insuranceCard;
        private String mainDoctor;
        private String mainNurse;
        private String medicareNo;
        private String motherTransLoc;
        private String name;
        private String nation;
        private String operation;
        private String orderID;
        private String patLinkman;
        private String patientID;
        private String personID;
        private String regDateTime;
        private String regNo;
        private String roomDesc;
        private String sex;
        private String telphone;
        private String totalCost;
        private String wardDesc;
        private List<String> mainDoctorID;
        private List<String> mainNurseID;

        public String getAdmReason() {
            return admReason;
        }

        public void setAdmReason(String admReason) {
            this.admReason = admReason;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getCareLevel() {
            return careLevel;
        }

        public void setCareLevel(String careLevel) {
            this.careLevel = careLevel;
        }

        public String getCtLocDesc() {
            return ctLocDesc;
        }

        public void setCtLocDesc(String ctLocDesc) {
            this.ctLocDesc = ctLocDesc;
        }

        public String getCurrWardID() {
            return currWardID;
        }

        public void setCurrWardID(String currWardID) {
            this.currWardID = currWardID;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public String getDischargeStatus() {
            return dischargeStatus;
        }

        public void setDischargeStatus(String dischargeStatus) {
            this.dischargeStatus = dischargeStatus;
        }

        public String getEpisodeID() {
            return episodeID;
        }

        public void setEpisodeID(String episodeID) {
            this.episodeID = episodeID;
        }

        public String getHomeAddres() {
            return homeAddres;
        }

        public void setHomeAddres(String homeAddres) {
            this.homeAddres = homeAddres;
        }

        public String getIfNewBaby() {
            return ifNewBaby;
        }

        public void setIfNewBaby(String ifNewBaby) {
            this.ifNewBaby = ifNewBaby;
        }

        public String getIllState() {
            return illState;
        }

        public void setIllState(String illState) {
            this.illState = illState;
        }

        public String getInDays() {
            return inDays;
        }

        public void setInDays(String inDays) {
            this.inDays = inDays;
        }

        public String getInDeptDateTime() {
            return inDeptDateTime;
        }

        public void setInDeptDateTime(String inDeptDateTime) {
            this.inDeptDateTime = inDeptDateTime;
        }

        public String getInHospDateTime() {
            return inHospDateTime;
        }

        public void setInHospDateTime(String inHospDateTime) {
            this.inHospDateTime = inHospDateTime;
        }

        public String getInsuranceCard() {
            return insuranceCard;
        }

        public void setInsuranceCard(String insuranceCard) {
            this.insuranceCard = insuranceCard;
        }

        public String getMainDoctor() {
            return mainDoctor;
        }

        public void setMainDoctor(String mainDoctor) {
            this.mainDoctor = mainDoctor;
        }

        public String getMainNurse() {
            return mainNurse;
        }

        public void setMainNurse(String mainNurse) {
            this.mainNurse = mainNurse;
        }

        public String getMedicareNo() {
            return medicareNo;
        }

        public void setMedicareNo(String medicareNo) {
            this.medicareNo = medicareNo;
        }

        public String getMotherTransLoc() {
            return motherTransLoc;
        }

        public void setMotherTransLoc(String motherTransLoc) {
            this.motherTransLoc = motherTransLoc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getPatLinkman() {
            return patLinkman;
        }

        public void setPatLinkman(String patLinkman) {
            this.patLinkman = patLinkman;
        }

        public String getPatientID() {
            return patientID;
        }

        public void setPatientID(String patientID) {
            this.patientID = patientID;
        }

        public String getPersonID() {
            return personID;
        }

        public void setPersonID(String personID) {
            this.personID = personID;
        }

        public String getRegDateTime() {
            return regDateTime;
        }

        public void setRegDateTime(String regDateTime) {
            this.regDateTime = regDateTime;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getRoomDesc() {
            return roomDesc;
        }

        public void setRoomDesc(String roomDesc) {
            this.roomDesc = roomDesc;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public String getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(String totalCost) {
            this.totalCost = totalCost;
        }

        public String getWardDesc() {
            return wardDesc;
        }

        public void setWardDesc(String wardDesc) {
            this.wardDesc = wardDesc;
        }

        public List<String> getMainDoctorID() {
            return mainDoctorID;
        }

        public void setMainDoctorID(List<String> mainDoctorID) {
            this.mainDoctorID = mainDoctorID;
        }

        public List<String> getMainNurseID() {
            return mainNurseID;
        }

        public void setMainNurseID(List<String> mainNurseID) {
            this.mainNurseID = mainNurseID;
        }
    }

    public static class OrdersBean {
        /**
         * DspStat : 未发
         * Durat : 1天
         * EncryptLevel :
         * FuHeDate : 1840-12-31
         * FuHeTime : 00:00
         * FuHeUser :
         * ID : 871282||2||1
         * PatLevel :
         * PeiYeDate : 2019-11-26
         * PeiYeTime : 11:20
         * PeiYeUser : nurse
         * PreDisDateTime :
         * age : 60岁
         * arcimDesc : 谷红注射液(5ml/支)
         * bedCode : 01
         * createDateTime : 2019-11-26 09:54:50
         * ctcpDesc : doctor
         * doseQtyUnit : 5 ml
         * execCtcpDesc : nurse
         * execDateTime : 11-26 11:28
         * execID : 871282||2||1
         * execXDateTime :
         * execXUserDesc :
         * labNo :
         * notes :
         * oecprDesc : 长期医嘱
         * orcatDesc : 西药
         * ordStatDesc : 核实
         * ordTyp : R
         * patIdentity : 全自费
         * patName : 入院测试1
         * phOrdQtyUnit : 5 ml
         * phcfrCode : Qd(8:00)
         * phcinDesc : 静滴
         * placerNo :
         * preparedFlag : Y
         * prescNo : I191126000001
         * price : 57.80
         * printFlag : T
         * reclocDesc : 医专住院药房
         * regNo : 0001186245
         * seqNo : 871282||2
         * specCollDateTime :
         * specDesc :
         * sttDate : 11-27
         * sttDateTime : 2019-11-27 08:00
         * sttTime : 08:00
         * totalAmount : 57.80
         * tubeColorCode :
         * tubeColorDesc :
         * verifyFlag : N
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
        private String age;
        private String arcimDesc;
        private String bedCode;
        private String createDateTime;
        private String ctcpDesc;
        private String doseQtyUnit;
        private String execCtcpDesc;
        private String execDateTime;
        private String execID;
        private String execXDateTime;
        private String execXUserDesc;
        private String labNo;
        private String notes;
        private String oecprDesc;
        private String orcatDesc;
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
        private String seqNo;
        private String specCollDateTime;
        private String specDesc;
        private String sttDate;
        private String sttDateTime;
        private String sttTime;
        private String totalAmount;
        private String tubeColorCode;
        private String tubeColorDesc;
        private String verifyFlag;
        private String xCtcpDesc;
        private String xDateTime;
        private String exeStColor;
        private String exeStatus;
        private String speedFlowRate;
        private String filteFlagExtend;

        public String getFilteFlagExtend() {
            return filteFlagExtend;
        }

        public void setFilteFlagExtend(String filteFlagExtend) {
            this.filteFlagExtend = filteFlagExtend;
        }

        public String getSpeedFlowRate() {
            return speedFlowRate;
        }

        public void setSpeedFlowRate(String speedFlowRate) {
            this.speedFlowRate = speedFlowRate;
        }

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

        public String getDoseQtyUnit() {
            return doseQtyUnit;
        }

        public void setDoseQtyUnit(String doseQtyUnit) {
            this.doseQtyUnit = doseQtyUnit;
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

        public String getLabNo() {
            return labNo;
        }

        public void setLabNo(String labNo) {
            this.labNo = labNo;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
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

        public String getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(String seqNo) {
            this.seqNo = seqNo;
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

        public String getExeStColor() {
            return exeStColor == null ? "" : exeStColor;
        }

        public void setExeStColor(String exeStColor) {
            this.exeStColor = exeStColor;
        }

        public String getExeStatus() {
            return exeStatus == null ? "" : exeStatus;
        }

        public void setExeStatus(String exeStatus) {
            this.exeStatus = exeStatus;
        }
    }
    public static class StopNoteListBean {
        /**
         * noteData : 鼓针
         * noteId : 鼓针
         */

        private String noteData;
        private String noteId;

        public String getNoteData() {
            return noteData;
        }

        public void setNoteData(String noteData) {
            this.noteData = noteData;
        }

        public String getNoteId() {
            return noteId;
        }

        public void setNoteId(String noteId) {
            this.noteId = noteId;
        }
    }

    public static class SuspendNoteListBean {
        /**
         * noteData : 鼓针
         * noteId : 鼓针
         */

        private String noteData;
        private String noteId;

        public String getNoteData() {
            return noteData;
        }

        public void setNoteData(String noteData) {
            this.noteData = noteData;
        }

        public String getNoteId() {
            return noteId;
        }

        public void setNoteId(String noteId) {
            this.noteId = noteId;
        }
    }
    public static class TourNoteListBean {
        /**
         * noteData : 鼓针
         * noteId : 鼓针
         */

        private String noteData;
        private String noteId;

        public String getNoteData() {
            return noteData;
        }

        public void setNoteData(String noteData) {
            this.noteData = noteData;
        }

        public String getNoteId() {
            return noteId;
        }

        public void setNoteId(String noteId) {
            this.noteId = noteId;
        }
    }

    public static class WayNoList {

        /**
         * wayNo : New
         * wayNum : 新通道
         */

        private String wayNo;
        private String wayNum;

        public String getWayNo() {
            return wayNo;
        }

        public void setWayNo(String wayNo) {
            this.wayNo = wayNo;
        }

        public String getWayNum() {
            return wayNum;
        }

        public void setWayNum(String wayNum) {
            this.wayNum = wayNum;
        }
    }
}
