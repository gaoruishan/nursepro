package com.dhcc.module.nurse.accompany.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/8/16/11:08
 * @email:grs0515@163.com
 */
public class AccompanyBean extends CommResult {


    /**
     * field : NCPARRowID
     * value : 5
     */

    private List<AccompanyListBean> accompanyList;
    /**
     * checkbox : 1
     * field : ck
     * title : sel
     */

    private List<AccompanyConfigBean> configACP;
    /**
     * checkbox : 1
     * field : ck
     * title : sel
     */

    private List<AccompanyConfigBean> configTEMP;

    public List<AccompanyListBean> getAccompanyList() {
        return accompanyList;
    }

    public void setAccompanyList(List<AccompanyListBean> accompanyList) {
        this.accompanyList = accompanyList;
    }

    public List<AccompanyConfigBean> getConfigACP() {
        return configACP;
    }

    public void setConfigACP(List<AccompanyConfigBean> configACP) {
        this.configACP = configACP;
    }

    public List<AccompanyConfigBean> getConfigTEMP() {
        return configTEMP;
    }

    public void setConfigTEMP(List<AccompanyConfigBean> configTEMP) {
        this.configTEMP = configTEMP;
    }

    public static class AccompanyListBean {

        /**
         * InsertDateTime :
         * InsertUser :
         * NCPAInfo1 :
         * NCPAInfo10 :
         * NCPAInfo11 :
         * NCPAInfo12 :
         * NCPAInfo13 :
         * NCPAInfo14 :
         * NCPAInfo15 :
         * NCPAInfo16 :
         * NCPAInfo17 :
         * NCPAInfo18 :
         * NCPAInfo19 :
         * NCPAInfo2 :
         * NCPAInfo20 :
         * NCPAInfo21 :
         * NCPAInfo22 :
         * NCPAInfo23 :
         * NCPAInfo24 :
         * NCPAInfo25 :
         * NCPAInfo26 :
         * NCPAInfo27 :
         * NCPAInfo28 :
         * NCPAInfo29 :
         * NCPAInfo3 :
         * NCPAInfo30 :
         * NCPAInfo4 :
         * NCPAInfo5 :
         * NCPAInfo6 :
         * NCPAInfo7 :
         * NCPAInfo8 :
         * NCPAInfo9 :
         * NCPARAccompanyStatus :
         * NCPARIdNo :
         * NCPARRowID :
         * bedCode : 0701床
         * episodeID : 73270017
         * patAge : 87岁
         * patName : 戴自英
         * regNo : 0000201916
         */

        private String InsertDateTime;
        private String InsertUser;
        private String NCPAInfo1;
        private String NCPAInfo10;
        private String NCPAInfo11;
        private String NCPAInfo12;
        private String NCPAInfo13;
        private String NCPAInfo14;
        private String NCPAInfo15;
        private String NCPAInfo16;
        private String NCPAInfo17;
        private String NCPAInfo18;
        private String NCPAInfo19;
        private String NCPAInfo2;
        private String NCPAInfo20;
        private String NCPAInfo21;
        private String NCPAInfo22;
        private String NCPAInfo23;
        private String NCPAInfo24;
        private String NCPAInfo25;
        private String NCPAInfo26;
        private String NCPAInfo27;
        private String NCPAInfo28;
        private String NCPAInfo29;
        private String NCPAInfo3;
        private String NCPAInfo30;
        private String NCPAInfo4;
        private String NCPAInfo5;
        private String NCPAInfo6;
        private String NCPAInfo7;
        private String NCPAInfo8;
        private String NCPAInfo9;
        private String NCPARAccompanyStatus;
        private String NCPARIdNo;
        private String NCPARRowID;
        private String bedCode;
        private String episodeID;
        private String patAge;
        private String patName;
        private String regNo;
        //全区
        private String inBedAll;
        //管辖
        private String manageInBed;

        public String getInBedAll() {
            return inBedAll == null ? "" : inBedAll;
        }

        public void setInBedAll(String inBedAll) {
            this.inBedAll = inBedAll;
        }

        public String getManageInBed() {
            return manageInBed == null ? "" : manageInBed;
        }

        public void setManageInBed(String manageInBed) {
            this.manageInBed = manageInBed;
        }

        public String getInsertDateTime() {
            return InsertDateTime;
        }

        public void setInsertDateTime(String InsertDateTime) {
            this.InsertDateTime = InsertDateTime;
        }

        public String getInsertUser() {
            return InsertUser;
        }

        public void setInsertUser(String InsertUser) {
            this.InsertUser = InsertUser;
        }

        public String getNCPAInfo1() {
            return NCPAInfo1;
        }

        public void setNCPAInfo1(String NCPAInfo1) {
            this.NCPAInfo1 = NCPAInfo1;
        }

        public String getNCPAInfo10() {
            return NCPAInfo10;
        }

        public void setNCPAInfo10(String NCPAInfo10) {
            this.NCPAInfo10 = NCPAInfo10;
        }

        public String getNCPAInfo11() {
            return NCPAInfo11;
        }

        public void setNCPAInfo11(String NCPAInfo11) {
            this.NCPAInfo11 = NCPAInfo11;
        }

        public String getNCPAInfo12() {
            return NCPAInfo12;
        }

        public void setNCPAInfo12(String NCPAInfo12) {
            this.NCPAInfo12 = NCPAInfo12;
        }

        public String getNCPAInfo13() {
            return NCPAInfo13;
        }

        public void setNCPAInfo13(String NCPAInfo13) {
            this.NCPAInfo13 = NCPAInfo13;
        }

        public String getNCPAInfo14() {
            return NCPAInfo14;
        }

        public void setNCPAInfo14(String NCPAInfo14) {
            this.NCPAInfo14 = NCPAInfo14;
        }

        public String getNCPAInfo15() {
            return NCPAInfo15;
        }

        public void setNCPAInfo15(String NCPAInfo15) {
            this.NCPAInfo15 = NCPAInfo15;
        }

        public String getNCPAInfo16() {
            return NCPAInfo16;
        }

        public void setNCPAInfo16(String NCPAInfo16) {
            this.NCPAInfo16 = NCPAInfo16;
        }

        public String getNCPAInfo17() {
            return NCPAInfo17;
        }

        public void setNCPAInfo17(String NCPAInfo17) {
            this.NCPAInfo17 = NCPAInfo17;
        }

        public String getNCPAInfo18() {
            return NCPAInfo18;
        }

        public void setNCPAInfo18(String NCPAInfo18) {
            this.NCPAInfo18 = NCPAInfo18;
        }

        public String getNCPAInfo19() {
            return NCPAInfo19;
        }

        public void setNCPAInfo19(String NCPAInfo19) {
            this.NCPAInfo19 = NCPAInfo19;
        }

        public String getNCPAInfo2() {
            return NCPAInfo2==null?"":NCPAInfo2;
        }

        public void setNCPAInfo2(String NCPAInfo2) {
            this.NCPAInfo2 = NCPAInfo2;
        }

        public String getNCPAInfo20() {
            return NCPAInfo20;
        }

        public void setNCPAInfo20(String NCPAInfo20) {
            this.NCPAInfo20 = NCPAInfo20;
        }

        public String getNCPAInfo21() {
            return NCPAInfo21;
        }

        public void setNCPAInfo21(String NCPAInfo21) {
            this.NCPAInfo21 = NCPAInfo21;
        }

        public String getNCPAInfo22() {
            return NCPAInfo22;
        }

        public void setNCPAInfo22(String NCPAInfo22) {
            this.NCPAInfo22 = NCPAInfo22;
        }

        public String getNCPAInfo23() {
            return NCPAInfo23;
        }

        public void setNCPAInfo23(String NCPAInfo23) {
            this.NCPAInfo23 = NCPAInfo23;
        }

        public String getNCPAInfo24() {
            return NCPAInfo24;
        }

        public void setNCPAInfo24(String NCPAInfo24) {
            this.NCPAInfo24 = NCPAInfo24;
        }

        public String getNCPAInfo25() {
            return NCPAInfo25;
        }

        public void setNCPAInfo25(String NCPAInfo25) {
            this.NCPAInfo25 = NCPAInfo25;
        }

        public String getNCPAInfo26() {
            return NCPAInfo26;
        }

        public void setNCPAInfo26(String NCPAInfo26) {
            this.NCPAInfo26 = NCPAInfo26;
        }

        public String getNCPAInfo27() {
            return NCPAInfo27;
        }

        public void setNCPAInfo27(String NCPAInfo27) {
            this.NCPAInfo27 = NCPAInfo27;
        }

        public String getNCPAInfo28() {
            return NCPAInfo28;
        }

        public void setNCPAInfo28(String NCPAInfo28) {
            this.NCPAInfo28 = NCPAInfo28;
        }

        public String getNCPAInfo29() {
            return NCPAInfo29;
        }

        public void setNCPAInfo29(String NCPAInfo29) {
            this.NCPAInfo29 = NCPAInfo29;
        }

        public String getNCPAInfo3() {
            return NCPAInfo3;
        }

        public void setNCPAInfo3(String NCPAInfo3) {
            this.NCPAInfo3 = NCPAInfo3;
        }

        public String getNCPAInfo30() {
            return NCPAInfo30;
        }

        public void setNCPAInfo30(String NCPAInfo30) {
            this.NCPAInfo30 = NCPAInfo30;
        }

        public String getNCPAInfo4() {
            return NCPAInfo4;
        }

        public void setNCPAInfo4(String NCPAInfo4) {
            this.NCPAInfo4 = NCPAInfo4;
        }

        public String getNCPAInfo5() {
            return NCPAInfo5;
        }

        public void setNCPAInfo5(String NCPAInfo5) {
            this.NCPAInfo5 = NCPAInfo5;
        }

        public String getNCPAInfo6() {
            return NCPAInfo6;
        }

        public void setNCPAInfo6(String NCPAInfo6) {
            this.NCPAInfo6 = NCPAInfo6;
        }

        public String getNCPAInfo7() {
            return NCPAInfo7;
        }

        public void setNCPAInfo7(String NCPAInfo7) {
            this.NCPAInfo7 = NCPAInfo7;
        }

        public String getNCPAInfo8() {
            return NCPAInfo8;
        }

        public void setNCPAInfo8(String NCPAInfo8) {
            this.NCPAInfo8 = NCPAInfo8;
        }

        public String getNCPAInfo9() {
            return NCPAInfo9;
        }

        public void setNCPAInfo9(String NCPAInfo9) {
            this.NCPAInfo9 = NCPAInfo9;
        }

        public String getNCPARAccompanyStatus() {
            return NCPARAccompanyStatus;
        }

        public void setNCPARAccompanyStatus(String NCPARAccompanyStatus) {
            this.NCPARAccompanyStatus = NCPARAccompanyStatus;
        }

        public String getNCPARIdNo() {
            return NCPARIdNo;
        }

        public void setNCPARIdNo(String NCPARIdNo) {
            this.NCPARIdNo = NCPARIdNo;
        }

        public String getNCPARRowID() {
            return NCPARRowID;
        }

        public void setNCPARRowID(String NCPARRowID) {
            this.NCPARRowID = NCPARRowID;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getEpisodeID() {
            return episodeID;
        }

        public void setEpisodeID(String episodeID) {
            this.episodeID = episodeID;
        }

        public String getPatAge() {
            return patAge;
        }

        public void setPatAge(String patAge) {
            this.patAge = patAge;
        }

        public String getPatName() {
            return patName;
        }

        public void setPatName(String patName) {
            this.patName = patName;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }
    }



}