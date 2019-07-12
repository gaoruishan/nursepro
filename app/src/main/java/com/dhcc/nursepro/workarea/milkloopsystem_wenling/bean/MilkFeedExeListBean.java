package com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean
 * <p>
 * author Q
 * Date: 2019/7/8
 * Time:15:34
 */
public class MilkFeedExeListBean {
    /**
     * exeList : [{"StartDateTime":"2019-07-08 15:21","patInfoDetail":{"IPAppBedID":"","IPBookID":"","admReason":"全自费","age":"4月23天","balance":"-1.10","bedCode":"01婴儿1","careLevel":"","careLevelColor":"","ctLocDesc":"产科","currLocID":"124","currWardID":"30","diagnosis":"","dischargeStatus":"","episodeID":"324","homeAddres":"","ifFirstToBed":"N","ifNewBaby":"Y","illState":"普通","inDays":"143","inDeptDateTime":"2019-02-15 10:35","inHospDateTime":"2019-02-15 10:35:06","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"","motherTransLoc":"","name":"myh1502之婴1","nation":"","operLaterDays":"","operation":"","orderID":"300","patLinkman":"","patientID":"193","personID":"","regDateTime":"2019-02-15 10:34:00","regNo":"0000000193","roomDesc":"产科1病室","sex":"男","telphone":"","totalCost":"1.1","wardDesc":"产科护理单元","weekDays":"第20周/第3天"},"startUser":"innurse"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<ExeListBean> exeList;

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

    public List<ExeListBean> getExeList() {
        return exeList;
    }

    public void setExeList(List<ExeListBean> exeList) {
        this.exeList = exeList;
    }

    public static class ExeListBean {
        /**
         * StartDateTime : 2019-07-08 15:21
         * patInfoDetail : {"IPAppBedID":"","IPBookID":"","admReason":"全自费","age":"4月23天","balance":"-1.10","bedCode":"01婴儿1","careLevel":"","careLevelColor":"","ctLocDesc":"产科","currLocID":"124","currWardID":"30","diagnosis":"","dischargeStatus":"","episodeID":"324","homeAddres":"","ifFirstToBed":"N","ifNewBaby":"Y","illState":"普通","inDays":"143","inDeptDateTime":"2019-02-15 10:35","inHospDateTime":"2019-02-15 10:35:06","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"","motherTransLoc":"","name":"myh1502之婴1","nation":"","operLaterDays":"","operation":"","orderID":"300","patLinkman":"","patientID":"193","personID":"","regDateTime":"2019-02-15 10:34:00","regNo":"0000000193","roomDesc":"产科1病室","sex":"男","telphone":"","totalCost":"1.1","wardDesc":"产科护理单元","weekDays":"第20周/第3天"}
         * startUser : innurse
         */

        private String StartDateTime;
        private PatInfoDetailBean patInfoDetail;
        private String startUser;

        public String getStartDateTime() {
            return StartDateTime;
        }

        public void setStartDateTime(String StartDateTime) {
            this.StartDateTime = StartDateTime;
        }

        public PatInfoDetailBean getPatInfoDetail() {
            return patInfoDetail;
        }

        public void setPatInfoDetail(PatInfoDetailBean patInfoDetail) {
            this.patInfoDetail = patInfoDetail;
        }

        public String getStartUser() {
            return startUser;
        }

        public void setStartUser(String startUser) {
            this.startUser = startUser;
        }

        public static class PatInfoDetailBean {
            /**
             * IPAppBedID :
             * IPBookID :
             * admReason : 全自费
             * age : 4月23天
             * balance : -1.10
             * bedCode : 01婴儿1
             * careLevel :
             * careLevelColor :
             * ctLocDesc : 产科
             * currLocID : 124
             * currWardID : 30
             * diagnosis :
             * dischargeStatus :
             * episodeID : 324
             * homeAddres :
             * ifFirstToBed : N
             * ifNewBaby : Y
             * illState : 普通
             * inDays : 143
             * inDeptDateTime : 2019-02-15 10:35
             * inHospDateTime : 2019-02-15 10:35:06
             * insuranceCard :
             * mainDoctor : 医生01
             * mainDoctorID : ["1"]
             * mainNurse :
             * mainNurseID : []
             * medicareNo :
             * motherTransLoc :
             * name : myh1502之婴1
             * nation :
             * operLaterDays :
             * operation :
             * orderID : 300
             * patLinkman :
             * patientID : 193
             * personID :
             * regDateTime : 2019-02-15 10:34:00
             * regNo : 0000000193
             * roomDesc : 产科1病室
             * sex : 男
             * telphone :
             * totalCost : 1.1
             * wardDesc : 产科护理单元
             * weekDays : 第20周/第3天
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
    }
}
