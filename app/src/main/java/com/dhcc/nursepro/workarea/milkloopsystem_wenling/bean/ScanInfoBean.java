package com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean
 * <p>
 * author Q
 * Date: 2019/7/8
 * Time:16:38
 */
public class ScanInfoBean {
    /**
     * diagFlag : 1
     * flag : PAT
     * msg : 成功
     * msgcode : 999999
     * orders : [{"DspStat":"未发","Durat":"","EncryptLevel":"","ID":"300||1||1","PatLevel":"","PreDisDateTime":"","addOrdBtn":"补费用","admLoc":"产科","age":"4月23天","arcimDesc":"一次性采血器(无抗凝)","bedCode":"01婴儿1","cancelReason":"","createDateTime":"2019-02-15 10:39:27","ctcpDesc":"医生01","doseQtyUnit":"","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"300||1||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"材料","ordDep":"产科","ordID":"300||1","ordStatDesc":"核实","ordTyp":"M","patIdentity":"全自费","patName":"myh1502之婴1","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","preparedFlag":"","prescNo":"","price":"1.10","printFlag":"","reclocDesc":"产科护理单元","regNo":"0000000193 ","seeOrderUserDateTime":"2019-02-15 11:47:00","seeOrderUserName":"护士01","seqNo":"300||1","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"02-15","sttDateTime":"2019-02-15 10:39","sttTime":"10:39","totalAmount":"1.10","tubeColor":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""}]
     * patInfo : {"IPAppBedID":"","IPBookID":"","admReason":"全自费","age":"33岁","balance":"99038.00","bedCode":"02","careLevel":"","careLevelColor":"","ctLocDesc":"呼吸内科","currLocID":"93","currWardID":"1","diagnosis":"乳腺肿瘤,伤寒,功能性咳嗽","dischargeStatus":"医嘱出院 ","episodeID":"120","homeAddres":"","ifFirstToBed":"N","ifNewBaby":"N","illState":"普通","inDays":"17","inDeptDateTime":"2019-02-02 15:06","inHospDateTime":"2019-02-02 15:06:28","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100037","motherTransLoc":"","name":"lc2019020201","nation":"汉族","operLaterDays":"","operation":"","orderID":"126","patLinkman":"","patientID":"92","personID":"","regDateTime":"2019-02-02 15:01:26","regNo":"0000000092","roomDesc":"呼吸1病室","sex":"女","telphone":"13112345678","totalCost":"962","wardDesc":"呼吸内科护理单元","weekDays":"第2周/第3天"}
     * status : 0
     */

    private String diagFlag;
    private String flag;
    private String msg;
    private String msgcode;
    private PatInfoBean patInfo;
    private String status;
    private List<OrdersBean> orders;
    private String startFlag;

    public String getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(String startFlag) {
        this.startFlag = startFlag;
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
         * IPAppBedID :
         * IPBookID :
         * admReason : 全自费
         * age : 33岁
         * balance : 99038.00
         * bedCode : 02
         * careLevel :
         * careLevelColor :
         * ctLocDesc : 呼吸内科
         * currLocID : 93
         * currWardID : 1
         * diagnosis : 乳腺肿瘤,伤寒,功能性咳嗽
         * dischargeStatus : 医嘱出院
         * episodeID : 120
         * homeAddres :
         * ifFirstToBed : N
         * ifNewBaby : N
         * illState : 普通
         * inDays : 17
         * inDeptDateTime : 2019-02-02 15:06
         * inHospDateTime : 2019-02-02 15:06:28
         * insuranceCard :
         * mainDoctor : 医生01
         * mainDoctorID : ["1"]
         * mainNurse :
         * mainNurseID : []
         * medicareNo : 100037
         * motherTransLoc :
         * name : lc2019020201
         * nation : 汉族
         * operLaterDays :
         * operation :
         * orderID : 126
         * patLinkman :
         * patientID : 92
         * personID :
         * regDateTime : 2019-02-02 15:01:26
         * regNo : 0000000092
         * roomDesc : 呼吸1病室
         * sex : 女
         * telphone : 13112345678
         * totalCost : 962
         * wardDesc : 呼吸内科护理单元
         * weekDays : 第2周/第3天
         */

        private String IPAppBedID;
        private String IPBookID;
        private String admReason;
        private String age;
        private String balance;
        private String bedCode;
        private String careLevel;
        private String careLevelColor;
        private String ctLocDesc;
        private String currLocID;
        private String currWardID;
        private String diagnosis;
        private String dischargeStatus;
        private String episodeID;
        private String homeAddres;
        private String ifFirstToBed;
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
        private String operLaterDays;
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
        private String weekDays;
        private List<String> mainDoctorID;
        private List<?> mainNurseID;

        public String getIPAppBedID() {
            return IPAppBedID;
        }

        public void setIPAppBedID(String IPAppBedID) {
            this.IPAppBedID = IPAppBedID;
        }

        public String getIPBookID() {
            return IPBookID;
        }

        public void setIPBookID(String IPBookID) {
            this.IPBookID = IPBookID;
        }

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

        public String getCareLevelColor() {
            return careLevelColor;
        }

        public void setCareLevelColor(String careLevelColor) {
            this.careLevelColor = careLevelColor;
        }

        public String getCtLocDesc() {
            return ctLocDesc;
        }

        public void setCtLocDesc(String ctLocDesc) {
            this.ctLocDesc = ctLocDesc;
        }

        public String getCurrLocID() {
            return currLocID;
        }

        public void setCurrLocID(String currLocID) {
            this.currLocID = currLocID;
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

        public String getIfFirstToBed() {
            return ifFirstToBed;
        }

        public void setIfFirstToBed(String ifFirstToBed) {
            this.ifFirstToBed = ifFirstToBed;
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

        public String getOperLaterDays() {
            return operLaterDays;
        }

        public void setOperLaterDays(String operLaterDays) {
            this.operLaterDays = operLaterDays;
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

        public String getWeekDays() {
            return weekDays;
        }

        public void setWeekDays(String weekDays) {
            this.weekDays = weekDays;
        }

        public List<String> getMainDoctorID() {
            return mainDoctorID;
        }

        public void setMainDoctorID(List<String> mainDoctorID) {
            this.mainDoctorID = mainDoctorID;
        }

        public List<?> getMainNurseID() {
            return mainNurseID;
        }

        public void setMainNurseID(List<?> mainNurseID) {
            this.mainNurseID = mainNurseID;
        }
    }

    public static class OrdersBean {
        /**
         * DspStat : 未发
         * Durat :
         * EncryptLevel :
         * ID : 300||1||1
         * PatLevel :
         * PreDisDateTime :
         * addOrdBtn : 补费用
         * admLoc : 产科
         * age : 4月23天
         * arcimDesc : 一次性采血器(无抗凝)
         * bedCode : 01婴儿1
         * cancelReason :
         * createDateTime : 2019-02-15 10:39:27
         * ctcpDesc : 医生01
         * doseQtyUnit :
         * examInfo : {}
         * execCtcpDesc :
         * execDateTime :
         * execID : 300||1||1
         * execXDateTime :
         * execXUserDesc :
         * flowSpeed :
         * labNo :
         * medCareNo :
         * no : 1
         * notes :
         * notifyClinician : N
         * oecprDesc : 临时医嘱
         * orcatDesc : 材料
         * ordDep : 产科
         * ordID : 300||1
         * ordStatDesc : 核实
         * ordTyp : M
         * patIdentity : 全自费
         * patName : myh1502之婴1
         * phOrdQtyUnit : 1
         * phcfrCode :
         * phcinDesc :
         * placerNo :
         * preparedFlag :
         * prescNo :
         * price : 1.10
         * printFlag :
         * reclocDesc : 产科护理单元
         * regNo : 0000000193
         * seeOrderUserDateTime : 2019-02-15 11:47:00
         * seeOrderUserName : 护士01
         * seqNo : 300||1
         * skinTestInfo :
         * skinTestNumber :
         * specCollDateTime :
         * specDesc :
         * sttDate : 02-15
         * sttDateTime : 2019-02-15 10:39
         * sttTime : 10:39
         * totalAmount : 1.10
         * tubeColor :
         * verifyFlag :
         * xCtcpDesc :
         * xDateTime :
         */

        private String DspStat;
        private String Durat;
        private String EncryptLevel;
        private String ID;
        private String PatLevel;
        private String PreDisDateTime;
        private String addOrdBtn;
        private String admLoc;
        private String age;
        private String arcimDesc;
        private String bedCode;
        private String cancelReason;
        private String createDateTime;
        private String ctcpDesc;
        private String doseQtyUnit;
        private ExamInfoBean examInfo;
        private String execCtcpDesc;
        private String execDateTime;
        private String execID;
        private String execXDateTime;
        private String execXUserDesc;
        private String flowSpeed;
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

        public String getFlowSpeed() {
            return flowSpeed;
        }

        public void setFlowSpeed(String flowSpeed) {
            this.flowSpeed = flowSpeed;
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
