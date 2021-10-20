package com.dhcc.nursepro.workarea.rfid.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/10/19/17:56
 * @email:grs0515@163.com
 */
public class InfusionPatBean extends CommResult {

    /**
     * bedCode : 01
     * bedId : 21||1
     * patInfo : {"careLevel":"","deviceno":"1","mainDoctor":"医生01","mainDoctorCode":"YS01","mainNurse":"护士01 赵君","mainNurseCode":"HS01^1411","medicareNo":"100455","ordNum":"","patAge":"45岁","patName":"浦冰冰","patSex":"女","personId":"","regNo":"0000001627","selectBed":"^12177^"}
     */

    private List<BedListBean> bedList;

    public List<BedListBean> getBedList() {
        return bedList;
    }

    public void setBedList(List<BedListBean> bedList) {
        this.bedList = bedList;
    }

    public static class BedListBean {
        private String bedCode;
        private String bedId;
        /**
         * careLevel :
         * deviceno : 1
         * mainDoctor : 医生01
         * mainDoctorCode : YS01
         * mainNurse : 护士01 赵君
         * mainNurseCode : HS01^1411
         * medicareNo : 100455
         * ordNum :
         * patAge : 45岁
         * patName : 浦冰冰
         * patSex : 女
         * personId :
         * regNo : 0000001627
         * selectBed : ^12177^
         */

        private PatInfoBean patInfo;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getBedId() {
            return bedId;
        }

        public void setBedId(String bedId) {
            this.bedId = bedId;
        }

        public PatInfoBean getPatInfo() {
            return patInfo;
        }

        public void setPatInfo(PatInfoBean patInfo) {
            this.patInfo = patInfo;
        }

        public static class PatInfoBean {
            private String careLevel;
            private String deviceno;
            private String mainDoctor;
            private String mainDoctorCode;
            private String mainNurse;
            private String mainNurseCode;
            private String medicareNo;
            private String ordNum;
            private String patAge;
            private String patName;
            private String patSex;
            private String personId;
            private String regNo;
            private String selectBed;

            public String getCareLevel() {
                return careLevel;
            }

            public void setCareLevel(String careLevel) {
                this.careLevel = careLevel;
            }

            public String getDeviceno() {
                return deviceno;
            }

            public void setDeviceno(String deviceno) {
                this.deviceno = deviceno;
            }

            public String getMainDoctor() {
                return mainDoctor;
            }

            public void setMainDoctor(String mainDoctor) {
                this.mainDoctor = mainDoctor;
            }

            public String getMainDoctorCode() {
                return mainDoctorCode;
            }

            public void setMainDoctorCode(String mainDoctorCode) {
                this.mainDoctorCode = mainDoctorCode;
            }

            public String getMainNurse() {
                return mainNurse;
            }

            public void setMainNurse(String mainNurse) {
                this.mainNurse = mainNurse;
            }

            public String getMainNurseCode() {
                return mainNurseCode;
            }

            public void setMainNurseCode(String mainNurseCode) {
                this.mainNurseCode = mainNurseCode;
            }

            public String getMedicareNo() {
                return medicareNo;
            }

            public void setMedicareNo(String medicareNo) {
                this.medicareNo = medicareNo;
            }

            public String getOrdNum() {
                return ordNum;
            }

            public void setOrdNum(String ordNum) {
                this.ordNum = ordNum;
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

            public String getPatSex() {
                return patSex;
            }

            public void setPatSex(String patSex) {
                this.patSex = patSex;
            }

            public String getPersonId() {
                return personId;
            }

            public void setPersonId(String personId) {
                this.personId = personId;
            }

            public String getRegNo() {
                return regNo;
            }

            public void setRegNo(String regNo) {
                this.regNo = regNo;
            }

            public String getSelectBed() {
                return selectBed;
            }

            public void setSelectBed(String selectBed) {
                this.selectBed = selectBed;
            }
        }
    }
}
