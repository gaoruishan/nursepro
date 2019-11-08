package com.dhcc.nursepro.workarea.bedmap.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.bean
 * <p>
 * author Q
 * Date: 2019/11/7
 * Time:9:16
 */
public class DayPayListBean {
    /**
     * PatInfo : {"IPAppBedID":"","IPBookID":"71","Infee":"96.37(其中已结算)0","Prepay":"1000.00","TotalSum":"96.37","admReason":"全自费","age":"20岁","balance":"903.63","bedCode":"02","careLevel":"","careLevelColor":"","ctLocDesc":"内分泌科","currLocID":"95","currWardID":"3","diagnosis":"高血压1级,脑内出血","dischargeStatus":"医嘱出院 ","episodeID":"640","homeAddres":"","ifFirstToBed":"N","ifNewBaby":"N","illState":"普通","inDays":"1","inDeptDateTime":"2019-07-18 09:54","inHospDateTime":"2019-07-18 09:54:26","insuranceCard":"","mainDoctor":"陆玉梅","mainDoctorID":["150"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100144","motherTransLoc":"","name":"张三","nation":"汉族","operLaterDays":"","operation":"","orderID":"616","patLinkman":"","patientID":"303","personID":"","regDateTime":"2019-07-18 09:34:54","regNo":"0000000303","roomDesc":"内分泌1病室","sex":"男","telphone":"13215151515","totalCost":"96.89","wardDesc":"内分泌科护理单元","weekDays":"第1天"}
     * PriceList : [{"SubPriceList":[{"ArcDesc":"骨化三醇胶丸(盖三醇)[0.25ug*10]","Qty":"3","Sum":"15.00","Unitprice":"5.0000","Uom":"粒"},{"ArcDesc":"维生素AD滴剂/(绿伊可新胶丸)[CO*20]","Qty":"20","Sum":"17.80","Unitprice":"0.8900","Uom":"粒"},{"ArcDesc":"10%葡萄糖注射液(塑瓶)[250ML]","Qty":"2","Sum":"2.60","Unitprice":"1.3000","Uom":"瓶"}],"SubTotal":"35.4","Type":"药品费"},{"SubPriceList":[{"ArcDesc":"大换药","Qty":"1","Sum":"15.00","Unitprice":"15.0000","Uom":"个"}],"SubTotal":"15","Type":"治疗费"},{"SubPriceList":[{"ArcDesc":"雾化吸入","Qty":"1","Sum":"2.00","Unitprice":"2.0000","Uom":"次"}],"SubTotal":"2","Type":"护理费"},{"SubPriceList":[{"ArcDesc":"普通病房床位费(甲等医院)","Qty":"1","Sum":"23.00","Unitprice":"23.0000","Uom":"每床日"},{"ArcDesc":"普通甲等病房(双人间)床位费加收","Qty":"1","Sum":"7.00","Unitprice":"7.0000","Uom":"每床日"}],"SubTotal":"30","Type":"床位费"},{"SubPriceList":[{"ArcDesc":"住院诊查费","Qty":"1","Sum":"9.00","Unitprice":"9.0000","Uom":"天"}],"SubTotal":"9","Type":"诊察费"},{"SubPriceList":[{"ArcDesc":"维生素B1注射液[0.1g:2ml]","Qty":"2","Sum":"0.20","Unitprice":".1","Uom":"支"},{"ArcDesc":"碳酸钙片(钙尔奇D)[CO*30]","Qty":"3","Sum":"3.12","Unitprice":"1.04","Uom":"片"},{"ArcDesc":"左甲状腺素钠片(优甲乐)[50ug*100]","Qty":"5","Sum":"1.65","Unitprice":".33","Uom":"片"}],"SubTotal":"4.97","Type":"西药"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private PatInfoBean PatInfo;
    private String msg;
    private String msgcode;
    private String status;
    private List<PriceListBean> PriceList;

    public PatInfoBean getPatInfo() {
        return PatInfo;
    }

    public void setPatInfo(PatInfoBean PatInfo) {
        this.PatInfo = PatInfo;
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

    public List<PriceListBean> getPriceList() {
        return PriceList;
    }

    public void setPriceList(List<PriceListBean> PriceList) {
        this.PriceList = PriceList;
    }

    public static class PatInfoBean {
        /**
         * IPAppBedID :
         * IPBookID : 71
         * Infee : 96.37(其中已结算)0
         * Prepay : 1000.00
         * TotalSum : 96.37
         * admReason : 全自费
         * age : 20岁
         * balance : 903.63
         * bedCode : 02
         * careLevel :
         * careLevelColor :
         * ctLocDesc : 内分泌科
         * currLocID : 95
         * currWardID : 3
         * diagnosis : 高血压1级,脑内出血
         * dischargeStatus : 医嘱出院
         * episodeID : 640
         * homeAddres :
         * ifFirstToBed : N
         * ifNewBaby : N
         * illState : 普通
         * inDays : 1
         * inDeptDateTime : 2019-07-18 09:54
         * inHospDateTime : 2019-07-18 09:54:26
         * insuranceCard :
         * mainDoctor : 陆玉梅
         * mainDoctorID : ["150"]
         * mainNurse : 护士01
         * mainNurseID : ["3"]
         * medicareNo : 100144
         * motherTransLoc :
         * name : 张三
         * nation : 汉族
         * operLaterDays :
         * operation :
         * orderID : 616
         * patLinkman :
         * patientID : 303
         * personID :
         * regDateTime : 2019-07-18 09:34:54
         * regNo : 0000000303
         * roomDesc : 内分泌1病室
         * sex : 男
         * telphone : 13215151515
         * totalCost : 96.89
         * wardDesc : 内分泌科护理单元
         * weekDays : 第1天
         */

        private String IPAppBedID;
        private String IPBookID;
        private String Infee;
        private String Prepay;
        private String TotalSum;
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

        public String getInfee() {
            return Infee;
        }

        public void setInfee(String Infee) {
            this.Infee = Infee;
        }

        public String getPrepay() {
            return Prepay;
        }

        public void setPrepay(String Prepay) {
            this.Prepay = Prepay;
        }

        public String getTotalSum() {
            return TotalSum;
        }

        public void setTotalSum(String TotalSum) {
            this.TotalSum = TotalSum;
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

    public static class PriceListBean {
        /**
         * SubPriceList : [{"ArcDesc":"骨化三醇胶丸(盖三醇)[0.25ug*10]","Qty":"3","Sum":"15.00","Unitprice":"5.0000","Uom":"粒"},{"ArcDesc":"维生素AD滴剂/(绿伊可新胶丸)[CO*20]","Qty":"20","Sum":"17.80","Unitprice":"0.8900","Uom":"粒"},{"ArcDesc":"10%葡萄糖注射液(塑瓶)[250ML]","Qty":"2","Sum":"2.60","Unitprice":"1.3000","Uom":"瓶"}]
         * SubTotal : 35.4
         * Type : 药品费
         */

        private String SubTotal;
        private String Type;
        private List<SubPriceListBean> SubPriceList;

        public String getSubTotal() {
            return SubTotal;
        }

        public void setSubTotal(String SubTotal) {
            this.SubTotal = SubTotal;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public List<SubPriceListBean> getSubPriceList() {
            return SubPriceList;
        }

        public void setSubPriceList(List<SubPriceListBean> SubPriceList) {
            this.SubPriceList = SubPriceList;
        }

        public static class SubPriceListBean {
            /**
             * ArcDesc : 骨化三醇胶丸(盖三醇)[0.25ug*10]
             * Qty : 3
             * Sum : 15.00
             * Unitprice : 5.0000
             * Uom : 粒
             */

            private String ArcDesc;
            private String Qty;
            private String Sum;
            private String Unitprice;
            private String Uom;

            public String getArcDesc() {
                return ArcDesc;
            }

            public void setArcDesc(String ArcDesc) {
                this.ArcDesc = ArcDesc;
            }

            public String getQty() {
                return Qty;
            }

            public void setQty(String Qty) {
                this.Qty = Qty;
            }

            public String getSum() {
                return Sum;
            }

            public void setSum(String Sum) {
                this.Sum = Sum;
            }

            public String getUnitprice() {
                return Unitprice;
            }

            public void setUnitprice(String Unitprice) {
                this.Unitprice = Unitprice;
            }

            public String getUom() {
                return Uom;
            }

            public void setUom(String Uom) {
                this.Uom = Uom;
            }
        }
    }
}
