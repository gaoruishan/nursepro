package com.dhcc.nursepro.workarea.pathandover.bean;

import java.io.Serializable;
import java.util.List;

public class GetConnectAndPatBean implements Serializable {

    /**
     * allChildRec : 病房移交手术室:1^手术室移交病房:2^手术室移交监护室:3^监护室移交病房:4
     * childRec : 1
     * ifEndConnect : Y
     * msg : 成功
     * msgcode : 999999
     * patInfo : {"IPAppBedID":"","IPBookID":"","admReason":"全自费","age":"34岁","balance":"39895.58","bedCode":"01","careLevel":"二级护理","careLevelColor":"","ctLocDesc":"内分泌科","currLocID":"113","currWardID":"21","diagnosis":"","dischargeStatus":"","episodeID":"112","homeAddres":"","ifFirstToBed":"N","ifNewBaby":"N","illState":"普通","inDays":"191","inDeptDateTime":"2020-07-08 08:30","inHospDateTime":"2020-07-08 08:30:24","insuranceCard":"","mainDoctor":"智亚芹","mainDoctorID":["1023"],"mainNurse":"","mainNurseID":[],"medicareNo":"100040","motherTransLoc":"","name":"刘新雨（演示勿动）","nation":"汉族","operLaterDays":"","operation":"","orderID":"146","patLinkman":"","patientID":"90","personID":"360222198607088553 ","regDateTime":"2020-07-08 08:23:18","regNo":"0000000089","roomDesc":"内分泌1病室","sex":"男","telphone":"13134567890","totalCost":"11920.25","wardDesc":"内分泌科护理单元","weekDays":"第27周/第2天"}
     * status : 0
     */

    private String allChildRec;
    private String childRec;
    private String ifEndConnect;
    private String msg;
    private String msgcode;
    /**
     * IPAppBedID :
     * IPBookID :
     * admReason : 全自费
     * age : 34岁
     * balance : 39895.58
     * bedCode : 01
     * careLevel : 二级护理
     * careLevelColor :
     * ctLocDesc : 内分泌科
     * currLocID : 113
     * currWardID : 21
     * diagnosis :
     * dischargeStatus :
     * episodeID : 112
     * homeAddres :
     * ifFirstToBed : N
     * ifNewBaby : N
     * illState : 普通
     * inDays : 191
     * inDeptDateTime : 2020-07-08 08:30
     * inHospDateTime : 2020-07-08 08:30:24
     * insuranceCard :
     * mainDoctor : 智亚芹
     * mainDoctorID : ["1023"]
     * mainNurse :
     * mainNurseID : []
     * medicareNo : 100040
     * motherTransLoc :
     * name : 刘新雨（演示勿动）
     * nation : 汉族
     * operLaterDays :
     * operation :
     * orderID : 146
     * patLinkman :
     * patientID : 90
     * personID : 360222198607088553
     * regDateTime : 2020-07-08 08:23:18
     * regNo : 0000000089
     * roomDesc : 内分泌1病室
     * sex : 男
     * telphone : 13134567890
     * totalCost : 11920.25
     * wardDesc : 内分泌科护理单元
     * weekDays : 第27周/第2天
     */

    private PatInfoBean patInfo;
    private String status;

    private String NurEmrId;
    private String emrCode;
    private String guid;

    public String getNurEmrId() {
        return NurEmrId;
    }

    public void setNurEmrId(String nurEmrId) {
        NurEmrId = nurEmrId;
    }

    public String getEmrCode() {
        return emrCode;
    }

    public void setEmrCode(String emrCode) {
        this.emrCode = emrCode;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAllChildRec() {
        return allChildRec;
    }

    public void setAllChildRec(String allChildRec) {
        this.allChildRec = allChildRec;
    }

    public String getChildRec() {
        return childRec;
    }

    public void setChildRec(String childRec) {
        this.childRec = childRec;
    }

    public String getIfEndConnect() {
        return ifEndConnect;
    }

    public void setIfEndConnect(String ifEndConnect) {
        this.ifEndConnect = ifEndConnect;
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

    public static class PatInfoBean implements Serializable {
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
        private List<String> mainNurseID;

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

        public List<String> getMainNurseID() {
            return mainNurseID;
        }

        public void setMainNurseID(List<String> mainNurseID) {
            this.mainNurseID = mainNurseID;
        }
    }
}
