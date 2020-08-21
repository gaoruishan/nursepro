package com.dhcc.module.nurse.bloodsugar.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.nurse.bloodsugar.bean
 * <p>
 * author Q
 * Date: 2020/8/19
 * Time:17:26
 */
public class BloodSugarPatsBean extends CommResult {
    /**
     * curDate : 2020-08-19
     * leftFilter : [{"code":"FBS","desc":"空腹血糖"},{"code":"BreakfastPBS","desc":"早餐后"},{"code":"LunchFBS","desc":"午餐前"},{"code":"LunchPBS","desc":"午餐后"},{"code":"DinnerFBS","desc":"晚餐前"},{"code":"DinnerPBS","desc":"晚餐后"},{"code":"BedtimeFBS","desc":"睡前"},{"code":"NightPBS","desc":"夜间"},{"code":"RBS","desc":"随机"}]
     * patInfoList : [{"age":"30岁","allOut":"0","arreag":"0","bedCode":"03","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"801","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"住院01","newPatient":"","operation":"0","regNo":"0000000417","seq":"1","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"0","bedCode":"07","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"421","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"MYHtest","newPatient":"","operation":"0","regNo":"0000000277","seq":"2","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"22岁","allOut":"0","arreag":"0","bedCode":"08","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"103","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"hoo02","newPatient":"","operation":"0","regNo":"0000000084","seq":"3","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"0","bedCode":"09","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1088","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"myh静配","newPatient":"","operation":"0","regNo":"0000000514","seq":"4","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"0","bedCode":"11","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"163","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"MYH入院1","newPatient":"","operation":"0","regNo":"0000000169","seq":"5","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"34岁","allOut":"0","arreag":"0","bedCode":"12","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"439","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"MYH小","newPatient":"","operation":"0","regNo":"0000000288","seq":"6","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"44岁","allOut":"0","arreag":"0","bedCode":"13","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"998","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"MYH入院5","newPatient":"","operation":"0","regNo":"0000000486","seq":"7","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"16","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1276","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"501001","newPatient":"","operation":"0","regNo":"0000000600","seq":"8","sex":"女","tempOrd":"","todayOut":"0","wait":"0"}]
     * topFilter : [{"code":"inBedAll","desc":"全区"},{"code":"manageInBed","desc":"管辖"},{"code":"todayOut","desc":"今日出院"},{"code":"allOut","desc":"已出院"},{"code":"wait","desc":"等候"}]
     */

    private String curDate;
    private List<LeftFilterBean> leftFilter;
    private List<PatInfoListBean> patInfoList;
    private List<TopFilterBean> topFilter;

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public List<LeftFilterBean> getLeftFilter() {
        return leftFilter;
    }

    public void setLeftFilter(List<LeftFilterBean> leftFilter) {
        this.leftFilter = leftFilter;
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

    public static class LeftFilterBean {
        /**
         * code : FBS
         * desc : 空腹血糖
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

    public static class PatInfoListBean {
        /**
         * age : 30岁
         * allOut : 0
         * arreag : 0
         * bedCode : 03
         * careLevel :
         * criticalValue : 0
         * dangerous : 0
         * docDisch :
         * epdFlag : 0
         * epdNotReport :
         * epdReport :
         * episodeId : 801
         * fever : 0
         * gotAllergy :
         * illState :
         * inBedAll : 1
         * longOrd :
         * manageInBed : 0
         * name : 住院01
         * newPatient :
         * operation : 0
         * regNo : 0000000417
         * seq : 1
         * sex : 女
         * tempOrd :
         * todayOut : 0
         * wait : 0
         */

        private String age;
        private String allOut;
        private String arreag;
        private String bedCode;
        private String careLevel;
        private String criticalValue;
        private String dangerous;
        private String docDisch;
        private String epdFlag;
        private String epdNotReport;
        private String epdReport;
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
        private String regNo;
        private String seq;
        private String sex;
        private String tempOrd;
        private String todayOut;
        private String wait;

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

        public String getEpdFlag() {
            return epdFlag;
        }

        public void setEpdFlag(String epdFlag) {
            this.epdFlag = epdFlag;
        }

        public String getEpdNotReport() {
            return epdNotReport;
        }

        public void setEpdNotReport(String epdNotReport) {
            this.epdNotReport = epdNotReport;
        }

        public String getEpdReport() {
            return epdReport;
        }

        public void setEpdReport(String epdReport) {
            this.epdReport = epdReport;
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
