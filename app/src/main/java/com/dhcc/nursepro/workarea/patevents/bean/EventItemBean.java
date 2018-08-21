package com.dhcc.nursepro.workarea.patevents.bean;

import java.util.List;

public class EventItemBean {
    /**
     * eventItemList : [{"desc":"入院","id":"1"},{"desc":"转入","id":"2"},{"desc":"手术","id":"3"},{"desc":"分娩","id":"4"},{"desc":"死亡","id":"7"},{"desc":"出院","id":"54"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<EventItemListBean> eventItemList;

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

    public List<EventItemListBean> getEventItemList() {
        return eventItemList;
    }

    public void setEventItemList(List<EventItemListBean> eventItemList) {
        this.eventItemList = eventItemList;
    }

    public static class EventItemListBean {
        /**
         * desc : 入院
         * id : 1
         */

        private String desc;
        private String id;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
