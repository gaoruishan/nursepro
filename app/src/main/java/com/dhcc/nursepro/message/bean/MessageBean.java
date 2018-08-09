package com.dhcc.nursepro.message.bean;

import java.util.List;

public class MessageBean {

    /**
     * abnormalPatList : [{"bedCode":"03","episodeId":"315","patName":"马亭（演示勿动）","regNo":"0000000290","sex":"M"},{"bedCode":"08","episodeId":"96","patName":" 智勇双全","regNo":"0000000133","sex":"M"},{"bedCode":"25","episodeId":"157","patName":"ly005","regNo":"0000000137","sex":"M"},{"bedCode":"29","episodeId":"189","patName":"韦丹寒","regNo":"0000000208","sex":"F"},{"bedCode":"38","episodeId":"435","patName":"tyu8901","regNo":"0000000351","sex":"F"}]
     * conPatList : [{"bedCode":"01床","conDocdesc":"李翔","episodeId":"123456","patName":"测试患者","regNo":"0000000001","sex":"F"}]
     * msg : 成功
     * msgcode : 999999
     * newOrdPatList : [{"bedCode":"02","episodeId":"94","patName":"王伟测试","regNo":"0000000129","sex":"M"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<AbnormalPatListBean> abnormalPatList;
    private List<ConPatListBean> conPatList;
    private List<NewOrdPatListBean> newOrdPatList;

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

    public List<AbnormalPatListBean> getAbnormalPatList() {
        return abnormalPatList;
    }

    public void setAbnormalPatList(List<AbnormalPatListBean> abnormalPatList) {
        this.abnormalPatList = abnormalPatList;
    }

    public List<ConPatListBean> getConPatList() {
        return conPatList;
    }

    public void setConPatList(List<ConPatListBean> conPatList) {
        this.conPatList = conPatList;
    }

    public List<NewOrdPatListBean> getNewOrdPatList() {
        return newOrdPatList;
    }

    public void setNewOrdPatList(List<NewOrdPatListBean> newOrdPatList) {
        this.newOrdPatList = newOrdPatList;
    }

    public static class AbnormalPatListBean {
        /**
         * bedCode : 03
         * episodeId : 315
         * patName : 马亭（演示勿动）
         * regNo : 0000000290
         * sex : M
         */

        private String bedCode;
        private String episodeId;
        private String patName;
        private String regNo;
        private String sex;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public static class ConPatListBean {
        /**
         * bedCode : 01床
         * conDocdesc : 李翔
         * episodeId : 123456
         * patName : 测试患者
         * regNo : 0000000001
         * sex : F
         */

        private String bedCode;
        private String conDocdesc;
        private String episodeId;
        private String patName;
        private String regNo;
        private String sex;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getConDocdesc() {
            return conDocdesc;
        }

        public void setConDocdesc(String conDocdesc) {
            this.conDocdesc = conDocdesc;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public static class NewOrdPatListBean {
        /**
         * bedCode : 02
         * episodeId : 94
         * patName : 王伟测试
         * regNo : 0000000129
         * sex : M
         */

        private String bedCode;
        private String episodeId;
        private String patName;
        private String regNo;
        private String sex;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
