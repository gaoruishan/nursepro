package com.dhcc.nursepro.workarea.nurtour.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.bean
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:16:46
 */
public class ModelDataBean {
    /**
     * infusionOrdInfo : [{"ArcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"500 ml"},{"ArcimDesc":"注射用青霉素钠[400万U]","DoseQtyUnit":"400 万IU","PhOrdQtyUnit":"800 万IU"},{"ArcimDesc":"一次性注射器[10ml]","DoseQtyUnit":" 万IU","PhOrdQtyUnit":"1 个"},{"ArcimDesc":"一次性输液器[7＃]","DoseQtyUnit":"1 万IU","PhOrdQtyUnit":"1 "}]
     * lastTourInfo : {"DHCNurTourDate":"2019-05-09","DHCNurTourTime":"09:13:00","DHCNurTourUser":"护士01","TourDetailList":[{"TourDataName":"巡视情况","TourDataValue":"正常"},{"TourDataName":"滴速","TourDataValue":""}],"TourTypeCode":"Infusion","TourTypeDesc":"输液巡视"}
     * modelList : [{"LinkInfo":[],"ModelSort":"1","PatInfo":"","Unit":"","editCondition":"","editFlag":"true","editItem":"","editType":"","fontSize":"12","height":"18","imageName":"","itemCode":"DHCNurTourDate","itemDesc":"巡视日期","itemType":"D","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"false","singleCheck":"true","subRowId":"","titleHiddeFlag":"true","toastStr":"","value":"测试","width":"128"},{"LinkInfo":[],"ModelSort":"2","PatInfo":"","Unit":"","editCondition":"","editFlag":"true","editItem":"","editType":"","fontSize":"12","height":"20","imageName":"","itemCode":"DHCNurTourTime","itemDesc":"巡视时间","itemType":"Ti","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"false","singleCheck":"true","subRowId":"","titleHiddeFlag":"0","toastStr":"","value":"测试","width":"115"},{"LinkInfo":[],"ModelSort":"3","PatInfo":"","Unit":"","editCondition":"","editFlag":"true","editItem":"","editType":"","fontSize":"12","height":"20","imageName":"","itemCode":"DHCNurTourInspectHandle","itemDesc":"巡视操作","itemType":"R","itemValue":"穿刺!巡视!续液!拔针!","itemdeValue":"","jModelKey":"","mustFill":"false","singleCheck":"true","subRowId":"","titleHiddeFlag":"true","toastStr":"","value":"测试","width":"110"},{"LinkInfo":[],"ModelSort":"4","PatInfo":"","Unit":"","editCondition":"","editFlag":"true","editItem":"","editType":"","fontSize":"12","height":"20","imageName":"","itemCode":"DHCNurTourInspectSTAT","itemDesc":"输液情况","itemType":"R","itemValue":"正常!鼓针!发冷!","itemdeValue":"","jModelKey":"","mustFill":"false","singleCheck":"true","subRowId":"","titleHiddeFlag":"true","toastStr":"","value":"测试","width":"110"},{"LinkInfo":[],"ModelSort":"5","PatInfo":"","Unit":"","editCondition":"","editFlag":"true","editItem":"","editType":"","fontSize":"12","height":"20","imageName":"","itemCode":"DHCNurTourInspectDeal","itemDesc":"异常处理措施","itemType":"R","itemValue":"重新穿刺!调整滴速!","itemdeValue":"","jModelKey":"","mustFill":"false","singleCheck":"true","subRowId":"","titleHiddeFlag":"true","toastStr":"","value":"测试","width":"110"},{"LinkInfo":[],"ModelSort":"6","PatInfo":"","Unit":"","editCondition":"","editFlag":"true","editItem":"","editType":"","fontSize":"12","height":"21","imageName":"","itemCode":"DHCNurTourInspectSP","itemDesc":"滴速","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"false","singleCheck":"true","subRowId":"","titleHiddeFlag":"true","toastStr":"","value":"测试","width":"106"}]
     * modelType : Infusion
     * msg : 成功
     * msgcode : 999999
     * patInfo : {"Barthel":"11.1.1","Bedsore":"11.1.1","Fallbed":"11.1.1","Fallrisk":"11.1.1","Item34":"11.1.1","Item34_Title":"心包引流(ml)","PatBMI":"22.87,超过中等","Reason":"11.1.1","admReason":"全自费","age":"30岁","balance":"89490.41","bedCode":"02","breath":"20","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","defFreq":"1","degrBlood":"95","diaPressure":"70","diagnosis":"食管裂孔疝,食管裂孔疝,肺占位性病变,咳嗽病,阳明实证","dischargeStatus":"","episodeID":"94","heartbeat":"60","height":"11.1.1","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"409","inDeptDateTime":"2018-03-26 08:46","inHospDateTime":"2018-03-26 08:46:30","insuranceCard":"","liquidOut":"11.1.1","mainDoctor":"智亚芹","mainDoctorID":["1671"],"mainNurse":"关畅","mainNurseID":["830"],"medicareNo":"100056","motherTransLoc":"","name":"王伟测试","nation":"汉族","operation":"颈部血管治疗性超声","orderID":"91","painInten":"11.1.1","patLinkman":"","patientID":"129","personID":"37078419880819641X ","phyCooling":"11.1.1","pulse":"50","rectemperature":"85","regDateTime":"2018-03-26 08:45:24","regNo":"0000000129","roomDesc":"1病室","sex":"男","sysPressure":"120","telphone":"15336465257","temperature":"36","totalCost":"20509.59","uriVolume":"11.1.1","wardDesc":"内分泌科护理单元","weight":"56"}
     * status : 0
     */

    private LastTourInfoBean lastTourInfo;
    private String modelType;
    private String msg;
    private String msgcode;
    private PatInfoBean patInfo;
    private String status;
    private List<InfusionOrdInfoBean> infusionOrdInfo;
    private List<ModelListBean> modelList;

    public LastTourInfoBean getLastTourInfo() {
        return lastTourInfo;
    }

    public void setLastTourInfo(LastTourInfoBean lastTourInfo) {
        this.lastTourInfo = lastTourInfo;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
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

    public List<InfusionOrdInfoBean> getInfusionOrdInfo() {
        return infusionOrdInfo;
    }

    public void setInfusionOrdInfo(List<InfusionOrdInfoBean> infusionOrdInfo) {
        this.infusionOrdInfo = infusionOrdInfo;
    }

    public List<ModelListBean> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelListBean> modelList) {
        this.modelList = modelList;
    }

    public static class LastTourInfoBean {
        /**
         * DHCNurTourDate : 2019-05-09
         * DHCNurTourTime : 09:13:00
         * DHCNurTourUser : 护士01
         * TourDetailList : [{"TourDataName":"巡视情况","TourDataValue":"正常"},{"TourDataName":"滴速","TourDataValue":""}]
         * TourTypeCode : Infusion
         * TourTypeDesc : 输液巡视
         */

        private String DHCNurTourDate;
        private String DHCNurTourTime;
        private String DHCNurTourUser;
        private String TourTypeCode;
        private String TourTypeDesc;
        private List<TourDetailListBean> TourDetailList;

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

        public String getTourTypeCode() {
            return TourTypeCode;
        }

        public void setTourTypeCode(String TourTypeCode) {
            this.TourTypeCode = TourTypeCode;
        }

        public String getTourTypeDesc() {
            return TourTypeDesc;
        }

        public void setTourTypeDesc(String TourTypeDesc) {
            this.TourTypeDesc = TourTypeDesc;
        }

        public List<TourDetailListBean> getTourDetailList() {
            return TourDetailList;
        }

        public void setTourDetailList(List<TourDetailListBean> TourDetailList) {
            this.TourDetailList = TourDetailList;
        }

        public static class TourDetailListBean {
            /**
             * TourDataName : 巡视情况
             * TourDataValue : 正常
             */

            private String TourDataName;
            private String TourDataValue;

            public String getTourDataName() {
                return TourDataName;
            }

            public void setTourDataName(String TourDataName) {
                this.TourDataName = TourDataName;
            }

            public String getTourDataValue() {
                return TourDataValue;
            }

            public void setTourDataValue(String TourDataValue) {
                this.TourDataValue = TourDataValue;
            }
        }
    }

    public static class PatInfoBean {
        /**
         * Barthel : 11.1.1
         * Bedsore : 11.1.1
         * Fallbed : 11.1.1
         * Fallrisk : 11.1.1
         * Item34 : 11.1.1
         * Item34_Title : 心包引流(ml)
         * PatBMI : 22.87,超过中等
         * Reason : 11.1.1
         * admReason : 全自费
         * age : 30岁
         * balance : 89490.41
         * bedCode : 02
         * breath : 20
         * careLevel :
         * ctLocDesc : 内分泌科
         * currWardID : 5
         * defFreq : 1
         * degrBlood : 95
         * diaPressure : 70
         * diagnosis : 食管裂孔疝,食管裂孔疝,肺占位性病变,咳嗽病,阳明实证
         * dischargeStatus :
         * episodeID : 94
         * heartbeat : 60
         * height : 11.1.1
         * homeAddres :
         * ifNewBaby : N
         * illState : 普通
         * inDays : 409
         * inDeptDateTime : 2018-03-26 08:46
         * inHospDateTime : 2018-03-26 08:46:30
         * insuranceCard :
         * liquidOut : 11.1.1
         * mainDoctor : 智亚芹
         * mainDoctorID : ["1671"]
         * mainNurse : 关畅
         * mainNurseID : ["830"]
         * medicareNo : 100056
         * motherTransLoc :
         * name : 王伟测试
         * nation : 汉族
         * operation : 颈部血管治疗性超声
         * orderID : 91
         * painInten : 11.1.1
         * patLinkman :
         * patientID : 129
         * personID : 37078419880819641X
         * phyCooling : 11.1.1
         * pulse : 50
         * rectemperature : 85
         * regDateTime : 2018-03-26 08:45:24
         * regNo : 0000000129
         * roomDesc : 1病室
         * sex : 男
         * sysPressure : 120
         * telphone : 15336465257
         * temperature : 36
         * totalCost : 20509.59
         * uriVolume : 11.1.1
         * wardDesc : 内分泌科护理单元
         * weight : 56
         */

        private String Barthel;
        private String Bedsore;
        private String Fallbed;
        private String Fallrisk;
        private String Item34;
        private String Item34_Title;
        private String PatBMI;
        private String Reason;
        private String admReason;
        private String age;
        private String balance;
        private String bedCode;
        private String breath;
        private String careLevel;
        private String ctLocDesc;
        private String currWardID;
        private String defFreq;
        private String degrBlood;
        private String diaPressure;
        private String diagnosis;
        private String dischargeStatus;
        private String episodeID;
        private String heartbeat;
        private String height;
        private String homeAddres;
        private String ifNewBaby;
        private String illState;
        private String inDays;
        private String inDeptDateTime;
        private String inHospDateTime;
        private String insuranceCard;
        private String liquidOut;
        private String mainDoctor;
        private String mainNurse;
        private String medicareNo;
        private String motherTransLoc;
        private String name;
        private String nation;
        private String operation;
        private String orderID;
        private String painInten;
        private String patLinkman;
        private String patientID;
        private String personID;
        private String phyCooling;
        private String pulse;
        private String rectemperature;
        private String regDateTime;
        private String regNo;
        private String roomDesc;
        private String sex;
        private String sysPressure;
        private String telphone;
        private String temperature;
        private String totalCost;
        private String uriVolume;
        private String wardDesc;
        private String weight;
        private List<String> mainDoctorID;
        private List<String> mainNurseID;

        public String getBarthel() {
            return Barthel;
        }

        public void setBarthel(String Barthel) {
            this.Barthel = Barthel;
        }

        public String getBedsore() {
            return Bedsore;
        }

        public void setBedsore(String Bedsore) {
            this.Bedsore = Bedsore;
        }

        public String getFallbed() {
            return Fallbed;
        }

        public void setFallbed(String Fallbed) {
            this.Fallbed = Fallbed;
        }

        public String getFallrisk() {
            return Fallrisk;
        }

        public void setFallrisk(String Fallrisk) {
            this.Fallrisk = Fallrisk;
        }

        public String getItem34() {
            return Item34;
        }

        public void setItem34(String Item34) {
            this.Item34 = Item34;
        }

        public String getItem34_Title() {
            return Item34_Title;
        }

        public void setItem34_Title(String Item34_Title) {
            this.Item34_Title = Item34_Title;
        }

        public String getPatBMI() {
            return PatBMI;
        }

        public void setPatBMI(String PatBMI) {
            this.PatBMI = PatBMI;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
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

        public String getBreath() {
            return breath;
        }

        public void setBreath(String breath) {
            this.breath = breath;
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

        public String getDefFreq() {
            return defFreq;
        }

        public void setDefFreq(String defFreq) {
            this.defFreq = defFreq;
        }

        public String getDegrBlood() {
            return degrBlood;
        }

        public void setDegrBlood(String degrBlood) {
            this.degrBlood = degrBlood;
        }

        public String getDiaPressure() {
            return diaPressure;
        }

        public void setDiaPressure(String diaPressure) {
            this.diaPressure = diaPressure;
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

        public String getHeartbeat() {
            return heartbeat;
        }

        public void setHeartbeat(String heartbeat) {
            this.heartbeat = heartbeat;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
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

        public String getLiquidOut() {
            return liquidOut;
        }

        public void setLiquidOut(String liquidOut) {
            this.liquidOut = liquidOut;
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

        public String getPainInten() {
            return painInten;
        }

        public void setPainInten(String painInten) {
            this.painInten = painInten;
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

        public String getPhyCooling() {
            return phyCooling;
        }

        public void setPhyCooling(String phyCooling) {
            this.phyCooling = phyCooling;
        }

        public String getPulse() {
            return pulse;
        }

        public void setPulse(String pulse) {
            this.pulse = pulse;
        }

        public String getRectemperature() {
            return rectemperature;
        }

        public void setRectemperature(String rectemperature) {
            this.rectemperature = rectemperature;
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

        public String getSysPressure() {
            return sysPressure;
        }

        public void setSysPressure(String sysPressure) {
            this.sysPressure = sysPressure;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(String totalCost) {
            this.totalCost = totalCost;
        }

        public String getUriVolume() {
            return uriVolume;
        }

        public void setUriVolume(String uriVolume) {
            this.uriVolume = uriVolume;
        }

        public String getWardDesc() {
            return wardDesc;
        }

        public void setWardDesc(String wardDesc) {
            this.wardDesc = wardDesc;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
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

    public static class InfusionOrdInfoBean {
        /**
         * ArcimDesc : 0.9%氯化钠注射液(塑瓶)[250ml]
         * DoseQtyUnit : 250 ml
         * PhOrdQtyUnit : 500 ml
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

    public static class ModelListBean {
        /**
         * LinkInfo : []
         * ModelSort : 1
         * PatInfo :
         * Unit :
         * editCondition :
         * editFlag : true
         * editItem :
         * editType :
         * fontSize : 12
         * height : 18
         * imageName :
         * itemCode : DHCNurTourDate
         * itemDesc : 巡视日期
         * itemType : D
         * itemValue :
         * itemdeValue :
         * jModelKey :
         * mustFill : false
         * singleCheck : true
         * subRowId :
         * titleHiddeFlag : true
         * toastStr :
         * value : 测试
         * width : 128
         */

        private String ModelSort;
        private String PatInfo;
        private String Unit;
        private String editCondition;
        private String editFlag;
        private String editItem;
        private String editType;
        private String fontSize;
        private String height;
        private String imageName;
        private String itemCode;
        private String itemDesc;
        private String itemType;
        private String itemValue;
        private String itemdeValue;
        private String jModelKey;
        private String mustFill;
        private String singleCheck;
        private String subRowId;
        private String titleHiddeFlag;
        private String toastStr;
        private String value;
        private String width;
        private List<?> LinkInfo;
        private String sendValue;

        public String getSendValue() {
            return sendValue;
        }

        public void setSendValue(String sendValue) {
            this.sendValue = sendValue;
        }

        public String getModelSort() {
            return ModelSort;
        }

        public void setModelSort(String ModelSort) {
            this.ModelSort = ModelSort;
        }

        public String getPatInfo() {
            return PatInfo;
        }

        public void setPatInfo(String PatInfo) {
            this.PatInfo = PatInfo;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String Unit) {
            this.Unit = Unit;
        }

        public String getEditCondition() {
            return editCondition;
        }

        public void setEditCondition(String editCondition) {
            this.editCondition = editCondition;
        }

        public String getEditFlag() {
            return editFlag;
        }

        public void setEditFlag(String editFlag) {
            this.editFlag = editFlag;
        }

        public String getEditItem() {
            return editItem;
        }

        public void setEditItem(String editItem) {
            this.editItem = editItem;
        }

        public String getEditType() {
            return editType;
        }

        public void setEditType(String editType) {
            this.editType = editType;
        }

        public String getFontSize() {
            return fontSize;
        }

        public void setFontSize(String fontSize) {
            this.fontSize = fontSize;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemDesc() {
            return itemDesc;
        }

        public void setItemDesc(String itemDesc) {
            this.itemDesc = itemDesc;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getItemValue() {
            return itemValue;
        }

        public void setItemValue(String itemValue) {
            this.itemValue = itemValue;
        }

        public String getItemdeValue() {
            return itemdeValue;
        }

        public void setItemdeValue(String itemdeValue) {
            this.itemdeValue = itemdeValue;
        }

        public String getJModelKey() {
            return jModelKey;
        }

        public void setJModelKey(String jModelKey) {
            this.jModelKey = jModelKey;
        }

        public String getMustFill() {
            return mustFill;
        }

        public void setMustFill(String mustFill) {
            this.mustFill = mustFill;
        }

        public String getSingleCheck() {
            return singleCheck;
        }

        public void setSingleCheck(String singleCheck) {
            this.singleCheck = singleCheck;
        }

        public String getSubRowId() {
            return subRowId;
        }

        public void setSubRowId(String subRowId) {
            this.subRowId = subRowId;
        }

        public String getTitleHiddeFlag() {
            return titleHiddeFlag;
        }

        public void setTitleHiddeFlag(String titleHiddeFlag) {
            this.titleHiddeFlag = titleHiddeFlag;
        }

        public String getToastStr() {
            return toastStr;
        }

        public void setToastStr(String toastStr) {
            this.toastStr = toastStr;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public List<?> getLinkInfo() {
            return LinkInfo;
        }

        public void setLinkInfo(List<?> LinkInfo) {
            this.LinkInfo = LinkInfo;
        }
    }
}
