package com.dhcc.nursepro.message.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.message.bean
 * <p>
 * author Q
 * Date: 2019/12/25
 * Time:11:17
 */
public class beantest {
    /**
     * UnExecOrdPatList : []
     * abnormalPatList : [{"bedCode":"14","episodeId":"162","patName":"高兴","regNo":"0000000130","sex":"F"},{"bedCode":"18","episodeId":"55","patName":"住院2","regNo":"0000000051","sex":"F"}]
     * conPatList : []
     * msg : 成功
     * msgcode : 999999
     * newOrdPatList : []
     * schEnDateTime : 2019-12-25 23:59:59
     * schStDateTime : 2019-12-25 00:00:00
     * skinTimeList : [{"ObserveTime":"1200","OeoreGroup":[{"ArcimDesc":"注射用青霉素钠[400万U]()","DoseQtyUnit":"800 万IU","PhOrdQtyUnit":"800 万IU"}],"OeoreId":"1||196||1","OverTime":"","PatName":"秦海贤","PatRegNo":"0000000001","PatSex":"男","SkinResutl":"","TestStartTime":"09:11:21","skinTestAuditCtcpDesc":"","skinTestAuditDateTime":"","skinTestCtcpDesc":"","skinTestDateTime":""}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String schEnDateTime;
    private String schStDateTime;
    private String status;
    private List<?> UnExecOrdPatList;
    private List<AbnormalPatListBean> abnormalPatList;
    private List<?> conPatList;
    private List<?> newOrdPatList;
    private List<SkinTimeListBean> skinTimeList;

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

    public String getSchEnDateTime() {
        return schEnDateTime;
    }

    public void setSchEnDateTime(String schEnDateTime) {
        this.schEnDateTime = schEnDateTime;
    }

    public String getSchStDateTime() {
        return schStDateTime;
    }

    public void setSchStDateTime(String schStDateTime) {
        this.schStDateTime = schStDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getUnExecOrdPatList() {
        return UnExecOrdPatList;
    }

    public void setUnExecOrdPatList(List<?> UnExecOrdPatList) {
        this.UnExecOrdPatList = UnExecOrdPatList;
    }

    public List<AbnormalPatListBean> getAbnormalPatList() {
        return abnormalPatList;
    }

    public void setAbnormalPatList(List<AbnormalPatListBean> abnormalPatList) {
        this.abnormalPatList = abnormalPatList;
    }

    public List<?> getConPatList() {
        return conPatList;
    }

    public void setConPatList(List<?> conPatList) {
        this.conPatList = conPatList;
    }

    public List<?> getNewOrdPatList() {
        return newOrdPatList;
    }

    public void setNewOrdPatList(List<?> newOrdPatList) {
        this.newOrdPatList = newOrdPatList;
    }

    public List<SkinTimeListBean> getSkinTimeList() {
        return skinTimeList;
    }

    public void setSkinTimeList(List<SkinTimeListBean> skinTimeList) {
        this.skinTimeList = skinTimeList;
    }

    public static class AbnormalPatListBean {
        /**
         * bedCode : 14
         * episodeId : 162
         * patName : 高兴
         * regNo : 0000000130
         * sex : F
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

    public static class SkinTimeListBean {
        /**
         * ObserveTime : 1200
         * OeoreGroup : [{"ArcimDesc":"注射用青霉素钠[400万U]()","DoseQtyUnit":"800 万IU","PhOrdQtyUnit":"800 万IU"}]
         * OeoreId : 1||196||1
         * OverTime :
         * PatName : 秦海贤
         * PatRegNo : 0000000001
         * PatSex : 男
         * SkinResutl :
         * TestStartTime : 09:11:21
         * skinTestAuditCtcpDesc :
         * skinTestAuditDateTime :
         * skinTestCtcpDesc :
         * skinTestDateTime :
         */

        private String ObserveTime;
        private String OeoreId;
        private String OverTime;
        private String PatName;
        private String PatRegNo;
        private String PatSex;
        private String SkinResutl;
        private String TestStartTime;
        private String skinTestAuditCtcpDesc;
        private String skinTestAuditDateTime;
        private String skinTestCtcpDesc;
        private String skinTestDateTime;
        private List<OeoreGroupBean> OeoreGroup;

        public String getObserveTime() {
            return ObserveTime;
        }

        public void setObserveTime(String ObserveTime) {
            this.ObserveTime = ObserveTime;
        }

        public String getOeoreId() {
            return OeoreId;
        }

        public void setOeoreId(String OeoreId) {
            this.OeoreId = OeoreId;
        }

        public String getOverTime() {
            return OverTime;
        }

        public void setOverTime(String OverTime) {
            this.OverTime = OverTime;
        }

        public String getPatName() {
            return PatName;
        }

        public void setPatName(String PatName) {
            this.PatName = PatName;
        }

        public String getPatRegNo() {
            return PatRegNo;
        }

        public void setPatRegNo(String PatRegNo) {
            this.PatRegNo = PatRegNo;
        }

        public String getPatSex() {
            return PatSex;
        }

        public void setPatSex(String PatSex) {
            this.PatSex = PatSex;
        }

        public String getSkinResutl() {
            return SkinResutl;
        }

        public void setSkinResutl(String SkinResutl) {
            this.SkinResutl = SkinResutl;
        }

        public String getTestStartTime() {
            return TestStartTime;
        }

        public void setTestStartTime(String TestStartTime) {
            this.TestStartTime = TestStartTime;
        }

        public String getSkinTestAuditCtcpDesc() {
            return skinTestAuditCtcpDesc;
        }

        public void setSkinTestAuditCtcpDesc(String skinTestAuditCtcpDesc) {
            this.skinTestAuditCtcpDesc = skinTestAuditCtcpDesc;
        }

        public String getSkinTestAuditDateTime() {
            return skinTestAuditDateTime;
        }

        public void setSkinTestAuditDateTime(String skinTestAuditDateTime) {
            this.skinTestAuditDateTime = skinTestAuditDateTime;
        }

        public String getSkinTestCtcpDesc() {
            return skinTestCtcpDesc;
        }

        public void setSkinTestCtcpDesc(String skinTestCtcpDesc) {
            this.skinTestCtcpDesc = skinTestCtcpDesc;
        }

        public String getSkinTestDateTime() {
            return skinTestDateTime;
        }

        public void setSkinTestDateTime(String skinTestDateTime) {
            this.skinTestDateTime = skinTestDateTime;
        }

        public List<OeoreGroupBean> getOeoreGroup() {
            return OeoreGroup;
        }

        public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
            this.OeoreGroup = OeoreGroup;
        }

        public static class OeoreGroupBean {
            /**
             * ArcimDesc : 注射用青霉素钠[400万U]()
             * DoseQtyUnit : 800 万IU
             * PhOrdQtyUnit : 800 万IU
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
    }
}
