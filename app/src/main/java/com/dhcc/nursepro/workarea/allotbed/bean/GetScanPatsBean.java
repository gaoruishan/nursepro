package com.dhcc.nursepro.workarea.allotbed.bean;

import com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.allotbed.bean
 * 扫腕带获取用户信息
 * author Q
 * Date: 2018/9/6
 * Time:15:21
 */
public class GetScanPatsBean {
        /**
         * msg : 成功
         * msgcode : 999999
         * patInfo : {"admReason":"全自费","age":"30岁","balance":"92691.45","bedCode":"02","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管裂孔疝,食管裂孔疝,肺占位性病变,咳嗽病,阳明实证","dischargeStatus":"","episodeID":"94","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"148","inDeptDateTime":"2018-03-26 08:46","inHospDateTime":"2018-03-26 08:46:30","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100056","motherTransLoc":"","name":"王伟测试","nation":"汉族","operation":"颈部血管治疗性超声","orderID":"91","patLinkman":"","patientID":"129","personID":"37078419880819641X ","regDateTime":"2018-03-26 08:45:24","regNo":"0000000129","roomDesc":"1病室","sex":"男","telphone":"15336465257","totalCost":"17308.55","wardDesc":"内分泌科护理单元"}
         * status : 0
         */

        private String msg;
        private String msgcode;
        private com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean.PatInfoBean patInfo;
        private String status;

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

        public com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean.PatInfoBean getPatInfo() {
            return patInfo;
        }

        public void setPatInfo(com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean.PatInfoBean patInfo) {
            this.patInfo = patInfo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class PatInfoBean {
            /**
             * admReason : 全自费
             * age : 30岁
             * balance : 92691.45
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
             * inDays : 148
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
             * totalCost : 17308.55
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
    }
