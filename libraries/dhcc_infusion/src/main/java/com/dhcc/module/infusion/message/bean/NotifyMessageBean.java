package com.dhcc.module.infusion.message.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-06-21/17:03
 * @email:grs0515@163.com
 */
public class NotifyMessageBean extends CommResult {

    private List<NotifyMessageListBean> NotifyMessageList;

    public List<NotifyMessageListBean> getNotifyMessageList() {
        return NotifyMessageList;
    }

    public void setNotifyMessageList(List<NotifyMessageListBean> NotifyMessageList) {
        this.NotifyMessageList = NotifyMessageList;
    }

    public static class NotifyMessageListBean {
        /**
         * MDesc : 输液
         * MNum : 5
         * MType : Infusion
         */

        private String MDesc;
        private String MNum;
        private String MType;

        public String getMDesc() {
            return MDesc;
        }

        public void setMDesc(String MDesc) {
            this.MDesc = MDesc;
        }

        public String getMNum() {
            return MNum;
        }

        public void setMNum(String MNum) {
            this.MNum = MNum;
        }

        public String getMType() {
            return MType;
        }

        public void setMType(String MType) {
            this.MType = MType;
        }
    }
}
