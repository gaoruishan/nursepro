package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.nursepro.setting.bean
 * <p>
 * author Q
 * Date: 2020/9/30
 * Time:9:29
 */
public class NoteBean extends CommResult {
    private List<SoundListBean> soundList;

    public List<SoundListBean> getSoundList() {
        return soundList;
    }

    public void setSoundList(List<SoundListBean> soundList) {
        this.soundList = soundList;
    }

    public static class SoundListBean {
        /**
         * EpisodeId :
         * SoundAddress :
         * SoundType :
         * rowId : 126
         * soundDate : 2021-01-21
         * soundHead :
         * soundId : 392
         * soundStr : 你好,随笔扎堆儿,晓菲老师你好,就像孙老师您好,，孙老师，您好,王老师，您不好,那个李老师好,。,
         * soundTime : 09:46:59
         * soundUser : 朱欣远
         */

        private String EpisodeId;
        private String SoundAddress;
        private String SoundType;
        private String rowId;
        private String soundDate;
        private String soundHead;
        private String soundId;
        private String soundStr;
        private String soundTime;
        private String soundUser;
        private String PatBed;
        private String PatName;

        public String getPatName() {
            return PatName;
        }

        public void setPatName(String patName) {
            PatName = patName;
        }

        public String getPatBed() {
            return PatBed;
        }

        public void setPatBed(String patBed) {
            PatBed = patBed;
        }

        public String getEpisodeId() {
            return EpisodeId;
        }

        public void setEpisodeId(String EpisodeId) {
            this.EpisodeId = EpisodeId;
        }

        public String getSoundAddress() {
            return SoundAddress;
        }

        public void setSoundAddress(String SoundAddress) {
            this.SoundAddress = SoundAddress;
        }

        public String getSoundType() {
            return SoundType;
        }

        public void setSoundType(String SoundType) {
            this.SoundType = SoundType;
        }

        public String getRowId() {
            return rowId;
        }

        public void setRowId(String rowId) {
            this.rowId = rowId;
        }

        public String getSoundDate() {
            return soundDate;
        }

        public void setSoundDate(String soundDate) {
            this.soundDate = soundDate;
        }

        public String getSoundHead() {
            return soundHead;
        }

        public void setSoundHead(String soundHead) {
            this.soundHead = soundHead;
        }

        public String getSoundId() {
            return soundId;
        }

        public void setSoundId(String soundId) {
            this.soundId = soundId;
        }

        public String getSoundStr() {
            return soundStr;
        }

        public void setSoundStr(String soundStr) {
            this.soundStr = soundStr;
        }

        public String getSoundTime() {
            return soundTime;
        }

        public void setSoundTime(String soundTime) {
            this.soundTime = soundTime;
        }

        public String getSoundUser() {
            return soundUser;
        }

        public void setSoundUser(String soundUser) {
            this.soundUser = soundUser;
        }
    }
}
