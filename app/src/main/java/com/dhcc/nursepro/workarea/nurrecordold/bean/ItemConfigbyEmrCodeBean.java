package com.dhcc.nursepro.workarea.nurrecordold.bean;

import java.util.List;

public class ItemConfigbyEmrCodeBean {

    /**
     * ItemSetList : [{"ItemCode":"Item32","LinkCode":"Item14","LinkNote":"1!2!3!4","LinkType":"2"},{"ItemCode":"Item33","LinkCode":"Item14","LinkNote":"1!2!3!4","LinkType":"2"},{"ItemCode":"Item34","LinkCode":"Item14","LinkNote":"1!2!3","LinkType":"2"},{"ItemCode":"Item35","LinkCode":"Item14","LinkNote":"1!2!3!4!5!6","LinkType":"2"}]
     * Status : 0
     * msg : 成功
     * msgcode : 999999
     */

    private String Status;
    private String msg;
    private String msgcode;
    private List<ItemSetListBean> ItemSetList;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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

    public List<ItemSetListBean> getItemSetList() {
        return ItemSetList;
    }

    public void setItemSetList(List<ItemSetListBean> ItemSetList) {
        this.ItemSetList = ItemSetList;
    }

    public static class ItemSetListBean {
        /**
         * ItemCode : Item32
         * LinkCode : Item14
         * LinkNote : 1!2!3!4
         * LinkType : 2
         */

        private String ItemCode;
        private String LinkCode;
        private String LinkNote;
        private String LinkType;

        public String getItemCode() {
            return ItemCode;
        }

        public void setItemCode(String ItemCode) {
            this.ItemCode = ItemCode;
        }

        public String getLinkCode() {
            return LinkCode;
        }

        public void setLinkCode(String LinkCode) {
            this.LinkCode = LinkCode;
        }

        public String getLinkNote() {
            return LinkNote;
        }

        public void setLinkNote(String LinkNote) {
            this.LinkNote = LinkNote;
        }

        public String getLinkType() {
            return LinkType;
        }

        public void setLinkType(String LinkType) {
            this.LinkType = LinkType;
        }
    }
}
