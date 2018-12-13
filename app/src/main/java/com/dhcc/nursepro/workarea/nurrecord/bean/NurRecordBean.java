package com.dhcc.nursepro.workarea.nurrecord.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurrecord.bean
 * <p>
 * author Q
 * Date: 2018/12/3
 * Time:9:50
 */
public class NurRecordBean {
    /**
     * modelList : [{"ModelSort":"1","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item1","itemDesc":"姓名","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"81"},{"ModelSort":"2","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item2","itemDesc":"年龄","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"57"},{"ModelSort":"3","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item3","itemDesc":"科别","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"141"},{"ModelSort":"4","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item4","itemDesc":"床号","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"48"},{"ModelSort":"5","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item5","itemDesc":"住院号","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"76"},{"ModelSort":"6","PatInfo":"","fontSize":"10.5","height":"19","itemCode":"_Label12","itemDesc":"一、一般资料","itemType":"T","itemValue":"一、一般资料","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"151"},{"ModelSort":"7","PatInfo":"","fontSize":"12","height":"22","itemCode":"Item77","itemDesc":"职业","itemType":"O","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"204"},{"ModelSort":"8","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item53","itemDesc":"文化程度","itemType":"R","itemValue":"小学!初中!高中!大专!本科!文盲!硕士!博士!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"106"},{"ModelSort":"9","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item7","itemDesc":"婚姻状况","itemType":"R","itemValue":"未婚!已婚!初婚!再婚!复婚!丧偶!离婚!未说明的婚姻状况!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"131"},{"ModelSort":"10","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item8","itemDesc":"联系地址","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"229"},{"ModelSort":"11","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item9","itemDesc":"联系人及电话","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"275"},{"ModelSort":"12","PatInfo":"","fontSize":"12","height":"26","itemCode":"Item10","itemDesc":"入院日期","itemType":"D","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"121"},{"ModelSort":"13","PatInfo":"","fontSize":"12","height":"27","itemCode":"Item11","itemDesc":"入院时间","itemType":"T","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"97"},{"ModelSort":"14","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item12","itemDesc":"入院方式","itemType":"R","itemValue":"步行!轮椅!平车!扶入!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"65"},{"ModelSort":"15","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item14","itemDesc":"入院原因","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"629"},{"ModelSort":"16","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item15","itemDesc":"入院诊断","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"629"},{"ModelSort":"17","PatInfo":"","fontSize":"10.5","height":"21","itemCode":"_Label37","itemDesc":"二、护理评估","itemType":"T","itemValue":"二、护理评估","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"128"},{"ModelSort":"18","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item76","itemDesc":"插入体温单时间点","itemType":"R","itemValue":"02:00!06:00!10:00!14:00!18:00!22:00!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"91"},{"ModelSort":"19","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item70","itemDesc":"T（℃）","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"41"},{"ModelSort":"20","PatInfo":"","fontSize":"12","height":"16","itemCode":"_Label187","itemDesc":"℃","itemType":"T","itemValue":"℃","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"17"},{"ModelSort":"21","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item71","itemDesc":"P（次/分）","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"29"},{"ModelSort":"22","PatInfo":"","fontSize":"12","height":"17","itemCode":"_Label188","itemDesc":"次/分","itemType":"T","itemValue":"次/分","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"35"},{"ModelSort":"23","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item72","itemDesc":"R（次/分）","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"32"},{"ModelSort":"24","PatInfo":"","fontSize":"12","height":"17","itemCode":"_Label189","itemDesc":"次/分","itemType":"T","itemValue":"次/分","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"36"},{"ModelSort":"25","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item73","itemDesc":"BP（mmHg）","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"41"},{"ModelSort":"26","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item74","itemDesc":"/","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"38"},{"ModelSort":"27","PatInfo":"","fontSize":"12","height":"19","itemCode":"_Label190","itemDesc":"mmHg","itemType":"T","itemValue":"mmHg","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"32"},{"ModelSort":"28","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item75","itemDesc":"体重","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"40"},{"ModelSort":"29","PatInfo":"","fontSize":"12","height":"17","itemCode":"_Label191","itemDesc":"kg","itemType":"T","itemValue":"kg","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"21"},{"ModelSort":"30","PatInfo":"","fontSize":"12","height":"26","itemCode":"_Label192","itemDesc":"（注:自动生成体温单入院生命体征）","itemType":"T","itemValue":"（注:自动生成体温单入院生命体征）","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"107"},{"ModelSort":"31","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item16","itemDesc":"神志：","itemType":"R","itemValue":"清楚!嗜睡!意识模糊!昏睡!浅昏迷!深昏迷!痴呆!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"86"},{"ModelSort":"32","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item17","itemDesc":"语言能力：","itemType":"R","itemValue":"正常!沟通障碍!失语!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"72"},{"ModelSort":"33","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item18","itemDesc":"皮肤：","itemType":"R","itemValue":"完整!不完整!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"70"},{"ModelSort":"34","PatInfo":"","fontSize":"12","height":"19","itemCode":"_Label112","itemDesc":"（不完整见压疮风险评估单）","itemType":"T","itemValue":"（不完整见压疮风险评估单）","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"168"},{"ModelSort":"35","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item19","itemDesc":"情绪状态：","itemType":"R","itemValue":"稳定!焦虑!紧张!恐惧!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"76"},{"ModelSort":"36","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item21","itemDesc":"自理能力","itemType":"R","itemValue":"完全自理!部分依赖!完全依赖!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"71"},{"ModelSort":"37","PatInfo":"","fontSize":"12","height":"20","itemCode":"_Label113","itemDesc":"（不能自理见跌倒坠床风险评估单）","itemType":"T","itemValue":"（不能自理见跌倒坠床风险评估单）","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"200"},{"ModelSort":"38","PatInfo":"","fontSize":"12","height":"31","itemCode":"Item22","itemDesc":"既往史：","itemType":"C","itemValue":"高血压;心脏病;糖尿病;脑血管病;手术史;精神病;","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"414"},{"ModelSort":"39","PatInfo":"","fontSize":"12","height":"14","itemCode":"_Label114","itemDesc":"其他","itemType":"T","itemValue":"其他","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"33"},{"ModelSort":"40","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item24","itemDesc":"过敏史：","itemType":"R","itemValue":"无!有!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"73"},{"ModelSort":"41","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item25","itemDesc":"药物","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"149"},{"ModelSort":"42","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item26","itemDesc":"食物","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"82"},{"ModelSort":"43","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item27","itemDesc":"其他","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"149"},{"ModelSort":"44","PatInfo":"","fontSize":"10.5","height":"17","itemCode":"_Label107","itemDesc":"三、专科情况","itemType":"T","itemValue":"三、专科情况","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"116"},{"ModelSort":"45","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item28","itemDesc":"孕产史：孕","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"36"},{"ModelSort":"46","PatInfo":"","fontSize":"12","height":"14","itemCode":"_Label109","itemDesc":"次","itemType":"T","itemValue":"次","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"20"},{"ModelSort":"47","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item29","itemDesc":"产","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"39"},{"ModelSort":"48","PatInfo":"","fontSize":"12","height":"16","itemCode":"_Label110","itemDesc":"次","itemType":"T","itemValue":"次","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"17"},{"ModelSort":"49","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item30","itemDesc":"人流","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"47"},{"ModelSort":"50","PatInfo":"","fontSize":"12","height":"15","itemCode":"_Label111","itemDesc":"次","itemType":"T","itemValue":"次","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"19"},{"ModelSort":"51","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item31","itemDesc":"母乳喂养知识：","itemType":"R","itemValue":"掌握!未掌握!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"65"},{"ModelSort":"52","PatInfo":"","fontSize":"12","height":"22","itemCode":"Item32","itemDesc":"末次月经：","itemType":"D","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"127"},{"ModelSort":"53","PatInfo":"","fontSize":"12","height":"25","itemCode":"Item33","itemDesc":"预产期","itemType":"D","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"136"},{"ModelSort":"54","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item34","itemDesc":"孕期出血：","itemType":"R","itemValue":"无!有!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"55"},{"ModelSort":"55","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item36","itemDesc":"孕期用药：","itemType":"R","itemValue":"无!有!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"52"},{"ModelSort":"56","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item38","itemDesc":"胎位","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"90"},{"ModelSort":"57","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item39","itemDesc":"胎心","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"60"},{"ModelSort":"58","PatInfo":"","fontSize":"12","height":"17","itemCode":"_Label115","itemDesc":"次/分","itemType":"T","itemValue":"次/分","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"39"},{"ModelSort":"59","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item40","itemDesc":"胎动","itemType":"R","itemValue":"正常!异常!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"64"},{"ModelSort":"60","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item42","itemDesc":"阴道出血：","itemType":"R","itemValue":"无!有!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"68"},{"ModelSort":"61","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item43","itemDesc":"胎膜","itemType":"R","itemValue":"未破!已破!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"63"},{"ModelSort":"62","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item44","itemDesc":"宫缩：","itemType":"R","itemValue":"无!有：!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"64"},{"ModelSort":"63","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item46","itemDesc":"乳房发育：","itemType":"R","itemValue":"正常!异常：乳头凹陷!异常：乳头肥大!异常：乳头过小!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"132"},{"ModelSort":"64","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item47","itemDesc":"乳房发育其他","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"293"},{"ModelSort":"65","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item48","itemDesc":"水肿情况","itemType":"R","itemValue":"无!有（+）!有（++）!有（+++）!有（++++）!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"103"},{"ModelSort":"66","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item49","itemDesc":"尿蛋白","itemType":"R","itemValue":"无!有（+）!有（++）!有（+++）!有（++++）!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"94"},{"ModelSort":"67","PatInfo":"","fontSize":"12","height":"20","itemCode":"Item50","itemDesc":"尿糖","itemType":"R","itemValue":"无!有（+）!有（++）!有（+++）!有（++++）!","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"107"},{"ModelSort":"68","PatInfo":"","fontSize":"10.5","height":"21","itemCode":"_Label108","itemDesc":"四、入院宣教","itemType":"T","itemValue":"四、入院宣教","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"102"},{"ModelSort":"69","PatInfo":"","fontSize":"12","height":"32","itemCode":"Item51","itemDesc":"入院宣教","itemType":"C","itemValue":"床位医生;责任护士;病房环境;病房制度;探视规定及时间;膳食安排;心理疏导;","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"576"},{"ModelSort":"70","PatInfo":"","fontSize":"12","height":"19","itemCode":"_Label105","itemDesc":"其他：","itemType":"T","itemValue":"其他：","itemdeValue":"","mustFill":"0","titleHiddeFlag":"0","width":"47"},{"ModelSort":"71","PatInfo":"","fontSize":"12","height":"21","itemCode":"Item55","itemDesc":"护理计划","itemType":"E","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"645"},{"ModelSort":"72","PatInfo":"","fontSize":"12","height":"25","itemCode":"Item56","itemDesc":"评估时间","itemType":"D","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"99"},{"ModelSort":"73","PatInfo":"","fontSize":"12","height":"24","itemCode":"Item54","itemDesc":"护士签名","itemType":"O","itemValue":"","itemdeValue":"","mustFill":"0","titleHiddeFlag":"1","width":"63"}]
     * msg : 成功
     * msgcode : 999999
     * patInfo : {"Barthel":"0","Bedsore":"0","Fallbed":"0","Fallrisk":"0","Item34":"0","Item34_Title":"0","Reason":"0","admReason":"全自费","age":"45岁","balance":"-953.22","bedCode":"","breath":"55","careLevel":"","ctLocDesc":"呼吸内科一","currWardID":"1","defFreq":"0","degrBlood":"0","diaPressure":"130","diagnosis":"肋软骨脱位,肺占位性病变","dischargeStatus":"","episodeID":"11","heartbeat":"0","height":"0","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"255","inDeptDateTime":"","inHospDateTime":"2018-03-23 14:00:53","insuranceCard":"","liquidOut":"0","mainDoctor":"","mainDoctorID":[],"mainNurse":"","mainNurseID":[],"medicareNo":"100005","motherTransLoc":"","name":"tyu01","nation":"汉族","operation":"","orderID":"9","painInten":"0","patLinkman":"","patientID":"52","personID":"111111111111111 ","phyCooling":"0","pulse":"100","rectemperature":"38","regDateTime":"2018-03-23 13:59:26","regNo":"0000000052","roomDesc":"转移中","sex":"女","sysPressure":"110","telphone":"13234567890","temperature":"36","totalCost":"5953.22","uriVolume":"0","wardDesc":"呼吸内科一二护理单元","weight":"50"}
     * status : 0
     */

    private String msg;
    private String msgcode;
    private PatInfoBean patInfo;
    private String status;
    private List<ModelListBean> modelList;

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

    public List<ModelListBean> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelListBean> modelList) {
        this.modelList = modelList;
    }

    public static class PatInfoBean {
        /**
         * Barthel : 0
         * Bedsore : 0
         * Fallbed : 0
         * Fallrisk : 0
         * Item34 : 0
         * Item34_Title : 0
         * Reason : 0
         * admReason : 全自费
         * age : 45岁
         * balance : -953.22
         * bedCode :
         * breath : 55
         * careLevel :
         * ctLocDesc : 呼吸内科一
         * currWardID : 1
         * defFreq : 0
         * degrBlood : 0
         * diaPressure : 130
         * diagnosis : 肋软骨脱位,肺占位性病变
         * dischargeStatus :
         * episodeID : 11
         * heartbeat : 0
         * height : 0
         * homeAddres :
         * ifNewBaby : N
         * illState : 普通
         * inDays : 255
         * inDeptDateTime :
         * inHospDateTime : 2018-03-23 14:00:53
         * insuranceCard :
         * liquidOut : 0
         * mainDoctor :
         * mainDoctorID : []
         * mainNurse :
         * mainNurseID : []
         * medicareNo : 100005
         * motherTransLoc :
         * name : tyu01
         * nation : 汉族
         * operation :
         * orderID : 9
         * painInten : 0
         * patLinkman :
         * patientID : 52
         * personID : 111111111111111
         * phyCooling : 0
         * pulse : 100
         * rectemperature : 38
         * regDateTime : 2018-03-23 13:59:26
         * regNo : 0000000052
         * roomDesc : 转移中
         * sex : 女
         * sysPressure : 110
         * telphone : 13234567890
         * temperature : 36
         * totalCost : 5953.22
         * uriVolume : 0
         * wardDesc : 呼吸内科一二护理单元
         * weight : 50
         */

        private String Barthel;
        private String Bedsore;
        private String Fallbed;
        private String Fallrisk;
        private String Item34;
        private String Item34_Title;
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
        private List<?> mainDoctorID;
        private List<?> mainNurseID;

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

        public List<?> getMainDoctorID() {
            return mainDoctorID;
        }

        public void setMainDoctorID(List<?> mainDoctorID) {
            this.mainDoctorID = mainDoctorID;
        }

        public List<?> getMainNurseID() {
            return mainNurseID;
        }

        public void setMainNurseID(List<?> mainNurseID) {
            this.mainNurseID = mainNurseID;
        }
    }

    public static class ModelListBean {
        /**
         * ModelSort : 1
         * PatInfo :
         * fontSize : 12
         * height : 21
         * itemCode : Item1
         * itemDesc : 姓名
         * itemType : E
         * itemValue :
         * itemdeValue :
         * mustFill : 0
         * titleHiddeFlag : 1
         * width : 81
         */

        private String ModelSort;
        private String PatInfo;
        private String fontSize;
        private String height;
        private String itemCode;
        private String itemDesc;
        private String itemType;
        private String itemValue;
        private String itemdeValue;
        private String mustFill;
        private String titleHiddeFlag;
        private String width;

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

        public String getMustFill() {
            return mustFill;
        }

        public void setMustFill(String mustFill) {
            this.mustFill = mustFill;
        }

        public String getTitleHiddeFlag() {
            return titleHiddeFlag;
        }

        public void setTitleHiddeFlag(String titleHiddeFlag) {
            this.titleHiddeFlag = titleHiddeFlag;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }
    }
}
