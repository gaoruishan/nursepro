package com.dhcc.nursepro.workarea.nurrecordnew.bean;

import java.util.List;

public class SyncEmrData {

    /**
     * Status : 0
     * itemList : [{"ItemCode":"Item1","ItemValue":"张三"},{"ItemCode":"Item13","ItemValue":"444"},{"ItemCode":"Item14","ItemValue":"555"}]
     * msg : 成功
     * msgcode : 999999
     */

    private String Status;
    private String msg;
    private String msgcode;
    private List<ItemListBean> itemList;

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

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBean {
        /**
         * ItemCode : Item1
         * ItemValue : 张三
         */

        private String ItemCode;
        private String ItemValue;

        public String getItemCode() {
            return ItemCode;
        }

        public void setItemCode(String ItemCode) {
            this.ItemCode = ItemCode;
        }

        public String getItemValue() {
            return ItemValue;
        }

        public void setItemValue(String ItemValue) {
            this.ItemValue = ItemValue;
        }
    }
}
