package com.dhcc.nursepro.setting.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.setting.bean
 * <p>
 * author Q
 * Date: 2020/9/30
 * Time:9:29
 */
public class NoteBean {

    /**
     * msg : 成功
     * msgcode :
     * soundList : [{"soundDate":"2020-10-28","soundHead":"","soundStr":"332","soundTime":"11:08:14","soundUser":"王军英"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<SoundListBean> soundList;

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

    public List<SoundListBean> getSoundList() {
        return soundList;
    }

    public void setSoundList(List<SoundListBean> soundList) {
        this.soundList = soundList;
    }

    public static class SoundListBean {
        /**
         * soundDate : 2020-10-28
         * soundHead :
         * soundStr : 332
         * soundTime : 11:08:14
         * soundUser : 王军英
         */

        private String soundDate;
        private String soundHead;
        private String soundStr;
        private String soundTime;
        private String soundUser;
        private String soundId;
        private String rowId;

        public String getRowId() {
            return rowId;
        }

        public void setRowId(String rowId) {
            this.rowId = rowId;
        }

        public String getSoundId() {
            return soundId;
        }

        public void setSoundId(String soundId) {
            this.soundId = soundId;
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
