package com.dhcc.nursepro.workarea.nurtour.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.bean
 * <p>
 * author Q
 * Date: 2019/4/24
 * Time:16:47
 */
public class DosingListBean {

    /**
     * msg : 成功
     * msgcode : 999999
     * patInfoList : [{"age":"59岁","allOut":"0","arreag":"1","bedCode":"01","careLevel":"","careLevelDesc":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"35","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","longOrd":"0","manageInBed":"1","name":"zxw01","newPatient":"0","operation":"","ordList":[{"OeoreId":" 568||3||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"TourList":[{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"11:43:30","DHCNurTourUser":"Demo Group","DetailId":"2","TourType":"Infusion"},{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"08:56:50","DHCNurTourUser":"Demo Group","DetailId":"4","TourType":"Infusion"}]},{"OeoreId":" 568||3||3","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"TourList":[{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"11:43:30","DHCNurTourUser":"Demo Group","DetailId":"3","TourType":"Infusion"},{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"08:56:50","DHCNurTourUser":"Demo Group","DetailId":"5","TourType":"Infusion"}]}],"patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"59岁","balance":"8870.26","bedCode":"01","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"发热","dischargeStatus":"","episodeID":"35","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"399","inDeptDateTime":"2018-03-23 14:38","inHospDateTime":"2018-03-23 14:38:00","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100025","motherTransLoc":"","name":"zxw01","nation":"汉族","operation":"","orderID":"16","patLinkman":"","patientID":"75","personID":"","regDateTime":"2018-03-23 14:30:22","regNo":"0000000075","roomDesc":"1病室","sex":"女","telphone":"13119325130","totalCost":"1129.74","wardDesc":"内分泌科护理单元"},"regNo":"0000000075","seq":"1","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"}]
     * status : 0
     * topFilter : [{"code":"inBedAll","desc":"全区"},{"code":"manageInBed","desc":"管辖"}]
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<PatInfoListBean> patInfoList;
    private List<TopFilterBean> topFilter;

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

    public List<PatInfoListBean> getPatInfoList() {
        return patInfoList;
    }

    public void setPatInfoList(List<PatInfoListBean> patInfoList) {
        this.patInfoList = patInfoList;
    }

    public List<TopFilterBean> getTopFilter() {
        return topFilter;
    }

    public void setTopFilter(List<TopFilterBean> topFilter) {
        this.topFilter = topFilter;
    }

    public static class PatInfoListBean {
        /**
         * age : 59岁
         * allOut : 0
         * arreag : 1
         * bedCode : 01
         * careLevel :
         * careLevelDesc :
         * criticalValue : 0
         * dangerous : 0
         * docDisch : 0
         * episodeId : 35
         * fever : 0
         * gotAllergy : 1
         * illState :
         * inBedAll : 1
         * longOrd : 0
         * manageInBed : 1
         * name : zxw01
         * newPatient : 0
         * operation :
         * ordList : [{"OeoreId":" 568||3||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"TourList":[{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"11:43:30","DHCNurTourUser":"Demo Group","DetailId":"2","TourType":"Infusion"},{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"08:56:50","DHCNurTourUser":"Demo Group","DetailId":"4","TourType":"Infusion"}]},{"OeoreId":" 568||3||3","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"TourList":[{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"11:43:30","DHCNurTourUser":"Demo Group","DetailId":"3","TourType":"Infusion"},{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"08:56:50","DHCNurTourUser":"Demo Group","DetailId":"5","TourType":"Infusion"}]}]
         * patInfoDetail : {"PatBMI":"22.87,超过中等","admReason":"全自费","age":"59岁","balance":"8870.26","bedCode":"01","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"发热","dischargeStatus":"","episodeID":"35","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"399","inDeptDateTime":"2018-03-23 14:38","inHospDateTime":"2018-03-23 14:38:00","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100025","motherTransLoc":"","name":"zxw01","nation":"汉族","operation":"","orderID":"16","patLinkman":"","patientID":"75","personID":"","regDateTime":"2018-03-23 14:30:22","regNo":"0000000075","roomDesc":"1病室","sex":"女","telphone":"13119325130","totalCost":"1129.74","wardDesc":"内分泌科护理单元"}
         * regNo : 0000000075
         * seq : 1
         * sex : 女
         * tempOrd : 0
         * todayOut : 0
         * wait : 0
         */

        private String age;
        private String allOut;
        private String arreag;
        private String bedCode;
        private String careLevel;
        private String careLevelDesc;
        private String criticalValue;
        private String dangerous;
        private String docDisch;
        private String episodeId;
        private String fever;
        private String gotAllergy;
        private String illState;
        private String inBedAll;
        private String longOrd;
        private String manageInBed;
        private String name;
        private String newPatient;
        private String operation;
        private PatInfoDetailBean patInfoDetail;
        private String regNo;
        private String seq;
        private String sex;
        private String tempOrd;
        private String todayOut;
        private String wait;
        private List<OrdListBean> ordList;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAllOut() {
            return allOut;
        }

        public void setAllOut(String allOut) {
            this.allOut = allOut;
        }

        public String getArreag() {
            return arreag;
        }

        public void setArreag(String arreag) {
            this.arreag = arreag;
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

        public String getCareLevelDesc() {
            return careLevelDesc;
        }

        public void setCareLevelDesc(String careLevelDesc) {
            this.careLevelDesc = careLevelDesc;
        }

        public String getCriticalValue() {
            return criticalValue;
        }

        public void setCriticalValue(String criticalValue) {
            this.criticalValue = criticalValue;
        }

        public String getDangerous() {
            return dangerous;
        }

        public void setDangerous(String dangerous) {
            this.dangerous = dangerous;
        }

        public String getDocDisch() {
            return docDisch;
        }

        public void setDocDisch(String docDisch) {
            this.docDisch = docDisch;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
        }

        public String getFever() {
            return fever;
        }

        public void setFever(String fever) {
            this.fever = fever;
        }

        public String getGotAllergy() {
            return gotAllergy;
        }

        public void setGotAllergy(String gotAllergy) {
            this.gotAllergy = gotAllergy;
        }

        public String getIllState() {
            return illState;
        }

        public void setIllState(String illState) {
            this.illState = illState;
        }

        public String getInBedAll() {
            return inBedAll;
        }

        public void setInBedAll(String inBedAll) {
            this.inBedAll = inBedAll;
        }

        public String getLongOrd() {
            return longOrd;
        }

        public void setLongOrd(String longOrd) {
            this.longOrd = longOrd;
        }

        public String getManageInBed() {
            return manageInBed;
        }

        public void setManageInBed(String manageInBed) {
            this.manageInBed = manageInBed;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNewPatient() {
            return newPatient;
        }

        public void setNewPatient(String newPatient) {
            this.newPatient = newPatient;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public PatInfoDetailBean getPatInfoDetail() {
            return patInfoDetail;
        }

        public void setPatInfoDetail(PatInfoDetailBean patInfoDetail) {
            this.patInfoDetail = patInfoDetail;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getSeq() {
            return seq;
        }

        public void setSeq(String seq) {
            this.seq = seq;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTempOrd() {
            return tempOrd;
        }

        public void setTempOrd(String tempOrd) {
            this.tempOrd = tempOrd;
        }

        public String getTodayOut() {
            return todayOut;
        }

        public void setTodayOut(String todayOut) {
            this.todayOut = todayOut;
        }

        public String getWait() {
            return wait;
        }

        public void setWait(String wait) {
            this.wait = wait;
        }

        public List<OrdListBean> getOrdList() {
            return ordList;
        }

        public void setOrdList(List<OrdListBean> ordList) {
            this.ordList = ordList;
        }

        public static class PatInfoDetailBean {
            /**
             * PatBMI : 22.87,超过中等
             * admReason : 全自费
             * age : 59岁
             * balance : 8870.26
             * bedCode : 01
             * careLevel :
             * ctLocDesc : 内分泌科
             * currWardID : 5
             * diagnosis : 发热
             * dischargeStatus :
             * episodeID : 35
             * homeAddres :
             * ifNewBaby : N
             * illState : 普通
             * inDays : 399
             * inDeptDateTime : 2018-03-23 14:38
             * inHospDateTime : 2018-03-23 14:38:00
             * insuranceCard :
             * mainDoctor : 医生01
             * mainDoctorID : ["1"]
             * mainNurse :
             * mainNurseID : []
             * medicareNo : 100025
             * motherTransLoc :
             * name : zxw01
             * nation : 汉族
             * operation :
             * orderID : 16
             * patLinkman :
             * patientID : 75
             * personID :
             * regDateTime : 2018-03-23 14:30:22
             * regNo : 0000000075
             * roomDesc : 1病室
             * sex : 女
             * telphone : 13119325130
             * totalCost : 1129.74
             * wardDesc : 内分泌科护理单元
             */

            private String PatBMI;
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

            public String getPatBMI() {
                return PatBMI;
            }

            public void setPatBMI(String PatBMI) {
                this.PatBMI = PatBMI;
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

        public static class OrdListBean {
            /**
             * OeoreId :  568||3||1
             * OeoreSubList : [{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}]
             * TourList : [{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"11:43:30","DHCNurTourUser":"Demo Group","DetailId":"2","TourType":"Infusion"},{"DHCNurTourDate":"2019-04-26","DHCNurTourTime":"08:56:50","DHCNurTourUser":"Demo Group","DetailId":"4","TourType":"Infusion"}]
             */

            private String OeoreId;
            private List<OeoreSubListBean> OeoreSubList;
            private List<TourListBean> TourList;

            public String getOeoreId() {
                return OeoreId;
            }

            public void setOeoreId(String OeoreId) {
                this.OeoreId = OeoreId;
            }

            public List<OeoreSubListBean> getOeoreSubList() {
                return OeoreSubList;
            }

            public void setOeoreSubList(List<OeoreSubListBean> OeoreSubList) {
                this.OeoreSubList = OeoreSubList;
            }

            public List<TourListBean> getTourList() {
                return TourList;
            }

            public void setTourList(List<TourListBean> TourList) {
                this.TourList = TourList;
            }

            public static class OeoreSubListBean {
                /**
                 * ArcimDesc : 5%葡萄糖氯化钠注射液(塑瓶)[250ml]
                 * DoseQtyUnit : 250 ml
                 * PhOrdQtyUnit : 18 瓶
                 */

                private String ArcimDesc;
                private String DoseQtyUnit;
                private String PhOrdQtyUnit;

                public String getArcimDesc() {
                    return ArcimDesc;
                }

                public void setArcimDesc(String ArcimDesc) {
                    this.ArcimDesc = ArcimDesc;
                }

                public String getDoseQtyUnit() {
                    return DoseQtyUnit;
                }

                public void setDoseQtyUnit(String DoseQtyUnit) {
                    this.DoseQtyUnit = DoseQtyUnit;
                }

                public String getPhOrdQtyUnit() {
                    return PhOrdQtyUnit;
                }

                public void setPhOrdQtyUnit(String PhOrdQtyUnit) {
                    this.PhOrdQtyUnit = PhOrdQtyUnit;
                }
            }

            public static class TourListBean {
                /**
                 * DHCNurTourDate : 2019-04-26
                 * DHCNurTourTime : 11:43:30
                 * DHCNurTourUser : Demo Group
                 * DetailId : 2
                 * TourType : Infusion
                 */

                private String DHCNurTourDate;
                private String DHCNurTourTime;
                private String DHCNurTourUser;
                private String DetailId;
                private String TourType;

                public String getDHCNurTourDate() {
                    return DHCNurTourDate;
                }

                public void setDHCNurTourDate(String DHCNurTourDate) {
                    this.DHCNurTourDate = DHCNurTourDate;
                }

                public String getDHCNurTourTime() {
                    return DHCNurTourTime;
                }

                public void setDHCNurTourTime(String DHCNurTourTime) {
                    this.DHCNurTourTime = DHCNurTourTime;
                }

                public String getDHCNurTourUser() {
                    return DHCNurTourUser;
                }

                public void setDHCNurTourUser(String DHCNurTourUser) {
                    this.DHCNurTourUser = DHCNurTourUser;
                }

                public String getDetailId() {
                    return DetailId;
                }

                public void setDetailId(String DetailId) {
                    this.DetailId = DetailId;
                }

                public String getTourType() {
                    return TourType;
                }

                public void setTourType(String TourType) {
                    this.TourType = TourType;
                }
            }
        }
    }

    public static class TopFilterBean {
        /**
         * code : inBedAll
         * desc : 全区
         */

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
