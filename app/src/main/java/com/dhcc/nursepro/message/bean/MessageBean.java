package com.dhcc.nursepro.message.bean;

import com.dhcc.nursepro.R;

import java.util.List;

public class MessageBean {

    /**
     * abnormalPatList : [{"bedCode":"03","episodeId":"315","patName":"马亭（演示勿动）","regNo":"0000000290","sex":"M"},{"bedCode":"08","episodeId":"96","patName":"智勇双全","regNo":"0000000133","sex":"M"},{"bedCode":"25","episodeId":"157","patName":"ly005","regNo":"0000000137","sex":"M"},{"bedCode":"29","episodeId":"189","patName":"韦丹寒","regNo":"0000000208","sex":"F"},{"bedCode":"38","episodeId":"435","patName":"tyu8901","regNo":"0000000351","sex":"F"}]
     * conPatList : [{"bedCode":"02","conDocdesc":"","episodeId":"94","patLoc":"内分泌科","patName":"王伟测试","regNo":"0000000129","sex":"M"}]
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
    private String soundFlag;
    private String vibrateFlag;

    public String getSoundFlag() {
        return soundFlag;
    }

    public void setSoundFlag(String soundFlag) {
        this.soundFlag = soundFlag;
    }

    public String getVibrateFlag() {
        return vibrateFlag;
    }

    public void setVibrateFlag(String vibrateFlag) {
        this.vibrateFlag = vibrateFlag;
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
         * bedCode : 02
         * conDocdesc :
         * episodeId : 94
         * patLoc : 内分泌科
         * patName : 王伟测试
         * regNo : 0000000129
         * sex : M
         */

        private String bedCode;
        private String conDocdesc;
        private String episodeId;
        private String patLoc;
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

        public String getPatLoc() {
            return patLoc;
        }

        public void setPatLoc(String patLoc) {
            this.patLoc = patLoc;
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


    /**
     * PatName : lh041101
     * PatRegNo : 0000000435
     * patSex : 女
     */
    public static int getPatSexDrawable(String patSex) {
        if ("男".equals(patSex)) {
            return R.drawable.sex_male;
        } else if ("女".equals(patSex)) {
            return R.drawable.sex_female;
        } else {
            return R.drawable.sex_male;
        }
    }

    private List<SkinTimeListBean> skinTimeList;

    public List<MessageBean.SkinTimeListBean> getSkinTimeList() {
        return skinTimeList;
    }

    public void setSkinTimeList(List<MessageBean.SkinTimeListBean> SkinTimeList) {
        this.skinTimeList = SkinTimeList;
    }

    public static class SkinTimeListBean {
        /**
         * ObserveTime : 1200
         * OeoreGroup : [{"ArcimDesc":"氯化钠注射液[1g:10ml]","DoseQtyUnit":"1 g","PhOrdQtyUnit":"1 支"},{"ArcimDesc":"注射用头孢米诺钠[0.5G]()","DoseQtyUnit":"1 g","PhOrdQtyUnit":"2 支"}]
         * OeoreId :
         * OverTime : 1173
         * PatName : 井尔
         * PatRegNo : 0000000164
         * PatSex : 男
         * SkinResutl :
         * TestStartTime : 09:06:57
         */

        private String ObserveTime;//总用时间
        private String OeoreId;
        private String OverTime;//倒计时
        private String PatName;
        private String PatRegNo;
        private String PatSex;
        private String SkinResutl;
        private String TestStartTime;//
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

        public List<OeoreGroupBean> getOeoreGroup() {
            return OeoreGroup;
        }

        public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
            this.OeoreGroup = OeoreGroup;
        }
    }
}
