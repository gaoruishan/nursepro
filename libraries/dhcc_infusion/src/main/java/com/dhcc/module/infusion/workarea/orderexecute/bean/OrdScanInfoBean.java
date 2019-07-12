package com.dhcc.module.infusion.workarea.orderexecute.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

public class OrdScanInfoBean extends CommResult {
    /**
     * flag : ORD
     * msg : 非置皮试的从医嘱不执行
     * msgcode : 100121
     * orders : [{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||3","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","xCtcpDesc":"","xDateTime":""}]
     * patInfo : {"admReason":"全自费","age":"30岁","balance":"92626.43","bedCode":"02","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管裂孔疝,食管裂孔疝,肺占位性病变,咳嗽病,阳明实证","dischargeStatus":"","episodeID":"94","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"158","inDeptDateTime":"2018-03-26 08:46","inHospDateTime":"2018-03-26 08:46:30","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100056","motherTransLoc":"","name":"王伟测试","nation":"汉族","operation":"颈部血管治疗性超声","orderID":"91","patLinkman":"","patientID":"129","personID":"37078419880819641X ","regDateTime":"2018-03-26 08:45:24","regNo":"0000000129","roomDesc":"1病室","sex":"男","telphone":"15336465257","totalCost":"17373.57","wardDesc":"内分泌科护理单元"}
     * status : 0
     */

    private String flag;
    private PatInfoBean patInfo;
    private List<OrdersBean> orders;
    /**
     * diagFlag : 1
     * orders : [{"DspStat":"","Durat":"","EncryptLevel":"","ID":"91||6||1","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"手术申请：","bedCode":"14","cancelReason":"药物过敏","createDateTime":"2018-03-26 09:33:29","ctcpDesc":"医生01","doseQtyUnit":"","examInfo":{},"execCtcpDesc":"医生01","execDateTime":"03-27 13:40","execID":"91||6||1","execXDateTime":"2018-05-03 19:32:46","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"5","notes":"拟于2018-03-26 03:30:00,在下行一氧化氮疗法","notifyClinician":"","oecprDesc":"临时医嘱","orcatDesc":"嘱托","ordDep":"内分泌科","ordID":"91||6","ordStatDesc":"撤销","ordTyp":"N","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","preparedFlag":"N","prescNo":"","price":"0.00","printFlag":"","reclocDesc":"内分泌科","regNo":"0000000129 ","seeOrderUserDateTime":"2018-09-12 10:45:09","seeOrderUserName":"护士01","seqNo":"91||6","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"03-26","sttDateTime":"2018-03-26 09:33:29","sttTime":"09:33","totalAmount":"0.00","tubeColor":"","verifyFlag":"N","xCtcpDesc":"医生01","xDateTime":"2018-03-27 13:40:09"}]
     */

    private String diagFlag;

    public String getDiagFlag() {
        return diagFlag == null ? "" : diagFlag;
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
        private List<?> mainNurseID;

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
         * Durat : 1天
         * EncryptLevel :
         * ID : 91||159||3
         * PatLevel :
         * PreDisDateTime :
         * abnorm :
         * admLoc : 内分泌科
         * age : 30岁
         * arcimDesc : 0.9%氯化钠注射液(塑瓶)[250ml]
         * bedCode : 02
         * cancelReason :
         * createDateTime : 2018-06-01 16:29:52
         * ctcpDesc : 医生01
         * doseQtyUnit : 250 ml
         * examInfo : {}
         * execCtcpDesc :
         * execDateTime :
         * execID : 91||159||3
         * execXDateTime :
         * execXUserDesc :
         * flowSpeed :
         * labNo :
         * medCareNo : 100056
         * no : 3.1
         * notes :
         * notifyClinician : N
         * oecprDesc : 长期医嘱
         * orcatDesc : 西药
         * ordDep : 内分泌科
         * ordID : 91||159
         * ordStatDesc : 核实
         * ordTyp : R
         * patIdentity : 全自费
         * patName : 王伟测试
         * phOrdQtyUnit : 500 ml
         * phcfrCode : Bid
         * phcinDesc : 静脉滴注
         * placerNo :
         * prescNo : I180601000011
         * price : 1.30
         * printFlag :
         * reclocDesc : 静脉药物配置中心
         * regNo : 0000000129
         * seeOrderUserDateTime : 2018-06-01 16:30:00
         * seeOrderUserName : 护士01
         * seqNo : 91||158
         * skinTestInfo :
         * skinTestNumber :
         * specCollDateTime :
         * specDesc :
         * sttDate : 06-02
         * sttDateTime : 2018-06-01 16:33:38
         * sttTime : 08:00
         * totalAmount : 2.60
         * tubeColor :
         * xCtcpDesc :
         * xDateTime :
         */

        private String DspStat;
        private String Durat;
        private String EncryptLevel;
        private String ID;
        private String PatLevel;
        private String PreDisDateTime;
        private String abnorm;
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

        public String getAbnorm() {
            return abnorm;
        }

        public void setAbnorm(String abnorm) {
            this.abnorm = abnorm;
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
