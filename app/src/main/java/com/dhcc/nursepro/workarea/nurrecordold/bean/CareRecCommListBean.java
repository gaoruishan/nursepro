package com.dhcc.nursepro.workarea.nurrecordold.bean;

import java.util.List;
import java.util.Map;

public class CareRecCommListBean {


    /**
     * dataList : [{"CareDate":"2019-07-20","CareDesc":"","CareTime":"16:15","CareUser":"护士01","par":"46","rw":"198"},{"CareDate":"2019-07-18","CareDesc":"","CareTime":"09:35","CareUser":"护士01","par":"46","rw":"197"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     * titleList : [{"TitleCode":"CareDate","TitleDesc":"日期"},{"TitleCode":"CareTime","TitleDesc":"时间"},{"TitleCode":"CareUser","TitleDesc":"记录人"},{"TitleCode":"CareDesc","TitleDesc":"描述"}]
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<DataListBean> dataList;
    private List<TitleListBean> titleList;
    private Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
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

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public List<TitleListBean> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<TitleListBean> titleList) {
        this.titleList = titleList;
    }

    public static class DataListBean {
        /**
         * CareDate : 2019-07-20
         * CareDesc :
         * CareTime : 16:15
         * CareUser : 护士01
         * par : 46
         * rw : 198
         */

        private String CareDate;
        private String CareDesc;
        private String CareTime;
        private String CareUser;
        private String par;
        private String rw;

        public String getCareDate() {
            return CareDate;
        }

        public void setCareDate(String CareDate) {
            this.CareDate = CareDate;
        }

        public String getCareDesc() {
            return CareDesc;
        }

        public void setCareDesc(String CareDesc) {
            this.CareDesc = CareDesc;
        }

        public String getCareTime() {
            return CareTime;
        }

        public void setCareTime(String CareTime) {
            this.CareTime = CareTime;
        }

        public String getCareUser() {
            return CareUser;
        }

        public void setCareUser(String CareUser) {
            this.CareUser = CareUser;
        }

        public String getPar() {
            return par;
        }

        public void setPar(String par) {
            this.par = par;
        }

        public String getRw() {
            return rw;
        }

        public void setRw(String rw) {
            this.rw = rw;
        }
    }

    public static class TitleListBean {
        /**
         * TitleCode : CareDate
         * TitleDesc : 日期
         */

        private String TitleCode;
        private String TitleDesc;

        public String getTitleCode() {
            return TitleCode;
        }

        public void setTitleCode(String TitleCode) {
            this.TitleCode = TitleCode;
        }

        public String getTitleDesc() {
            return TitleDesc;
        }

        public void setTitleDesc(String TitleDesc) {
            this.TitleDesc = TitleDesc;
        }
    }
}
