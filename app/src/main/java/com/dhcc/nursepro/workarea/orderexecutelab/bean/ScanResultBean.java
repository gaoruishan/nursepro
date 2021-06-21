package com.dhcc.nursepro.workarea.orderexecutelab.bean;

import java.util.List;

public class ScanResultBean {

    /**
     * StopNoteList : []
     * SuspendNoteList : []
     * TourNoteList : []
     * btnDesc : 配液
     * btnMth : preparedVerifyOrd
     * btnType : exe
     * canExeFlag : 0
     * curTime : 2021-05-11 19:12:37
     * diagFlag : 1
     * flag : ORD
     * flowSpeed : 44
     * flowSpeedUnit : 滴/秒
     * ifState :
     * msg : 医嘱未配液
     * msgcode : 100121
     * orders : [{"DspStat":"未发","Durat":"","EncryptLevel":"","FuHeDate":"12-31","FuHeTime":"00:00","FuHeUser":"","ID":"178||229||1","PatLevel":"","PeiYeDate":"12-31","PeiYeTime":"00:00","PeiYeUser":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"48岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[500ml] ","attOrdBtn":"绑费用","bedCode":"02","cancelReason":"","createDateTime":"2021-04-14 15:39:17","ctcpDesc":"医生01","doseQtyUnit":"500 ml","examInfo":{},"exeStColor":"#02C874","exeStatus":"未","execCtcpDesc":"护士01","execDateTime":"04-22 14:27","execID":"178||229||1","execXDateTime":"","execXUserDesc":"","feeFlag":"否","filteFlagExtend":"","flowSpeed":"44 滴/秒","icuFlag":"1","labNo":"","medCareNo":"100063","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"178||229","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"闭环演示(勿动)","phOrdQtyUnit":"500 ml","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"N","prescNo":"I210414000002","price":"1.60","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000134 ","seeOrderUserDateTime":"2021-04-14 15:40:00","seeOrderUserName":"护士01","seqNo":"178||229","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"44","speedFlowUnit":"滴/秒","sttDate":"04-14","sttDateTime":"2021-04-14 15:39","sttTime":"15:39","totalAmount":"1.60","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"N","xCtcpDesc":"","xDateTime":""}]
     * patInfo : {}
     * speedUnitList : [{"unitDesc":"滴/秒","unitId":"1"},{"unitDesc":"滴/分","unitId":"2"},{"unitDesc":"滴/小时","unitId":"4"}]
     * status : -1
     */

    private String btnDesc;
    private String btnMth;
    private String btnType;
    private String canExeFlag;
    private String curTime;
    private String diagFlag;
    private String flag;
    private String barCodeType;
    private String flowSpeed;
    private String flowSpeedUnit;
    private String ifState;
    private String msg;
    private String msgcode;
    private PatInfoBean patInfo;
    private String status;

    public static class PatInfoBean {
        /**
         * admReason : 全自费
         * age : 30岁
         * balance : 92626.43
         * bedCode : 02
         * careLevel :
         * ctLocDesc : 内分泌科
         * currWardID : 5
         * diagnosis : 食管裂孔疝,食管裂孔疝,肺占位性病变,咳嗽病,阳明实证
         * dischargeStatus :
         * episodeID : 94
         * homeAddres :
         * ifNewBaby : N
         * illState : 普通
         * inDays : 158
         * inDeptDateTime : 2018-03-26 08:46
         * inHospDateTime : 2018-03-26 08:46:30
         * insuranceCard :
         * mainDoctor : 医生01
         * mainDoctorID : ["1"]
         * mainNurse :
         * mainNurseID : []
         * medicareNo : 100056
         * motherTransLoc :
         * name : 王伟测试
         * nation : 汉族
         * operation : 颈部血管治疗性超声
         * orderID : 91
         * patLinkman :
         * patientID : 129
         * personID : 37078419880819641X
         * regDateTime : 2018-03-26 08:45:24
         * regNo : 0000000129
         * roomDesc : 1病室
         * sex : 男
         * telphone : 15336465257
         * totalCost : 17373.57
         * wardDesc : 内分泌科护理单元
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

    /**
     * DspStat : 未发
     * Durat :
     * EncryptLevel :
     * FuHeDate : 12-31
     * FuHeTime : 00:00
     * FuHeUser :
     * ID : 178||229||1
     * PatLevel :
     * PeiYeDate : 12-31
     * PeiYeTime : 00:00
     * PeiYeUser :
     * PreDisDateTime :
     * addOrdBtn : 补费用
     * admLoc : 内分泌科
     * age : 48岁
     * arcimDesc : 0.9%氯化钠注射液(塑瓶)[500ml]
     * attOrdBtn : 绑费用
     * bedCode : 02
     * cancelReason :
     * createDateTime : 2021-04-14 15:39:17
     * ctcpDesc : 医生01
     * doseQtyUnit : 500 ml
     * examInfo : {}
     * exeStColor : #02C874
     * exeStatus : 未
     * execCtcpDesc : 护士01
     * execDateTime : 04-22 14:27
     * execID : 178||229||1
     * execXDateTime :
     * execXUserDesc :
     * feeFlag : 否
     * filteFlagExtend :
     * flowSpeed : 44 滴/秒
     * icuFlag : 1
     * labNo :
     * medCareNo : 100063
     * no : 1
     * notes :
     * notifyClinician : N
     * oecprDesc : 长期医嘱
     * orcatDesc : 西药
     * ordDep : 内分泌科
     * ordID : 178||229
     * ordStatDesc : 核实
     * ordTyp : R
     * patIdentity : 全自费
     * patName : 闭环演示(勿动)
     * phOrdQtyUnit : 500 ml
     * phcfrCode : Qd
     * phcinDesc : 静脉滴注
     * placerNo :
     * preparedFlag : N
     * prescNo : I210414000002
     * price : 1.60
     * printFlag :
     * reclocDesc : 静脉药物配置中心
     * regNo : 0000000134
     * seeOrderUserDateTime : 2021-04-14 15:40:00
     * seeOrderUserName : 护士01
     * seqNo : 178||229
     * skinTestInfo :
     * skinTestNumber :
     * specCollDateTime :
     * specDesc :
     * speedFlowRate : 44
     * speedFlowUnit : 滴/秒
     * sttDate : 04-14
     * sttDateTime : 2021-04-14 15:39
     * sttTime : 15:39
     * totalAmount : 1.60
     * tubeColor :
     * tubeColorCode :
     * tubeColorDesc :
     * verifyFlag : N
     * xCtcpDesc :
     * xDateTime :
     */

    private List<OrdersBean> orders;
    /**
     * unitDesc : 滴/秒
     * unitId : 1
     */

    private List<SpeedUnitListBean> speedUnitList;

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

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
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

    public String getBarCodeType() {
        return barCodeType;
    }

    public void setBarCodeType(String barCodeType) {
        this.barCodeType = barCodeType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public List<SpeedUnitListBean> getSpeedUnitList() {
        return speedUnitList;
    }

    public void setSpeedUnitList(List<SpeedUnitListBean> speedUnitList) {
        this.speedUnitList = speedUnitList;
    }

    public static class OrdersBean {
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
        private String attOrdBtn;
        private String bedCode;
        private String cancelReason;
        private String createDateTime;
        private String ctcpDesc;
        private String doseQtyUnit;
        private String exeStColor;
        private String exeStatus;
        private String execCtcpDesc;
        private String execDateTime;
        private String execID;
        private String execXDateTime;
        private String execXUserDesc;
        private String feeFlag;
        private String filteFlagExtend;
        private String flowSpeed;
        private String icuFlag;
        private String labNo;
        private String labNote;
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
        private String speedFlowRate;
        private String speedFlowUnit;
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

        public String getAttOrdBtn() {
            return attOrdBtn;
        }

        public void setAttOrdBtn(String attOrdBtn) {
            this.attOrdBtn = attOrdBtn;
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

        public String getDoseQtyUnit() {
            return doseQtyUnit;
        }

        public void setDoseQtyUnit(String doseQtyUnit) {
            this.doseQtyUnit = doseQtyUnit;
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

        public String getFeeFlag() {
            return feeFlag;
        }

        public void setFeeFlag(String feeFlag) {
            this.feeFlag = feeFlag;
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

        public String getLabNote() {
            return labNote;
        }

        public void setLabNote(String labNote) {
            this.labNote = labNote;
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

        public String getSpeedFlowRate() {
            return speedFlowRate;
        }

        public void setSpeedFlowRate(String speedFlowRate) {
            this.speedFlowRate = speedFlowRate;
        }

        public String getSpeedFlowUnit() {
            return speedFlowUnit;
        }

        public void setSpeedFlowUnit(String speedFlowUnit) {
            this.speedFlowUnit = speedFlowUnit;
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
    }

    public static class SpeedUnitListBean {
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
}
