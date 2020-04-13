package com.dhcc.infusion.update;

import com.base.commlibs.http.CommResult;

import java.util.List;

public class UpdateBean extends CommResult {


    /**
     * allUpdateFlag :
     * list : [{"wardId":"","locDesc":"急诊内科门诊 急诊治疗护士","locId":"203","appAddress":"http://192.168.1.11:8080/updateApp/Infusion_PDA_13_2020-01-02.apk","newVersion":"13","updateFlag":"1"},{"wardId":"","locDesc":"门诊输液室 门诊输液护士","locId":"249","appAddress":"http://192.168.1.11:8080/updateApp/Infusion_PDA_13_2020-01-02.apk","newVersion":"13","updateFlag":"1"}]
     */

    private String allUpdateFlag;
    private String allAppAddress;
    private String allNewVersion;
    private List<ListBean> list;

    public String getAllNewVersion() {
        return allNewVersion == null ? "" : allNewVersion;
    }

    public void setAllNewVersion(String allNewVersion) {
        this.allNewVersion = allNewVersion;
    }

    public String getAllAppAddress() {
        return allAppAddress == null ? "" : allAppAddress;
    }

    public void setAllAppAddress(String allAppAddress) {
        this.allAppAddress = allAppAddress;
    }

    public String getAllUpdateFlag() {
        return allUpdateFlag;
    }

    public void setAllUpdateFlag(String allUpdateFlag) {
        this.allUpdateFlag = allUpdateFlag;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * wardId :
         * locDesc : 急诊内科门诊 急诊治疗护士
         * locId : 203
         * appAddress : http://192.168.1.11:8080/updateApp/Infusion_PDA_13_2020-01-02.apk
         * newVersion : 13
         * updateFlag : 1
         */

        private String wardId;
        private String locDesc;
        private String locId;
        private String appAddress;
        private String newVersion;
        private String forceFlag;

        public String getWardId() {
            return wardId;
        }

        public void setWardId(String wardId) {
            this.wardId = wardId;
        }

        public String getLocDesc() {
            return locDesc == null ? "" : locDesc;
        }

        public void setLocDesc(String locDesc) {
            this.locDesc = locDesc;
        }

        public String getLocId() {
            return locId;
        }

        public void setLocId(String locId) {
            this.locId = locId;
        }

        public String getAppAddress() {
            return appAddress;
        }

        public void setAppAddress(String appAddress) {
            this.appAddress = appAddress;
        }

        public String getNewVersion() {
            return newVersion == null ? "0" : newVersion;
        }

        public void setNewVersion(String newVersion) {
            this.newVersion = newVersion;
        }

        public String getForceFlag() {
            return forceFlag == null ? "" : forceFlag;
        }

        public void setForceFlag(String forceFlag) {
            this.forceFlag = forceFlag;
        }
    }
}
