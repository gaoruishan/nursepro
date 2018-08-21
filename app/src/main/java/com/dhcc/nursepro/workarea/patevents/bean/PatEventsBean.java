package com.dhcc.nursepro.workarea.patevents.bean;

import java.util.List;

public class PatEventsBean {
    /**
     * eventList : [{"addUser":"护士01","eventDate":"2018-03-26","eventDesc":"入院","eventId":"1","eventTime":"08:46","recId":"2382"},{"addUser":"innurse","eventDate":"2018-08-14","eventDesc":"手术","eventId":"3","eventTime":"11:11","recId":"2392"},{"addUser":"innurse","eventDate":"2018-08-17","eventDesc":"","eventId":"","eventTime":"08:43","recId":"2393"},{"addUser":"innurse","eventDate":"2018-08-17","eventDesc":"入院","eventId":"1","eventTime":"08:43","recId":"2394"},{"addUser":"","eventDate":"2018-03-01","eventDesc":"入院","eventId":"1","eventTime":"11:11","recId":"2395"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<EventListBean> eventList;

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

    public List<EventListBean> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventListBean> eventList) {
        this.eventList = eventList;
    }

    public static class EventListBean {
        /**
         * addUser : 护士01
         * eventDate : 2018-03-26
         * eventDesc : 入院
         * eventId : 1
         * eventTime : 08:46
         * recId : 2382
         */

        private String addUser;
        private String eventDate;
        private String eventDesc;
        private String eventId;
        private String eventTime;
        private String recId;

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public String getEventDate() {
            return eventDate;
        }

        public void setEventDate(String eventDate) {
            this.eventDate = eventDate;
        }

        public String getEventDesc() {
            return eventDesc;
        }

        public void setEventDesc(String eventDesc) {
            this.eventDesc = eventDesc;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getEventTime() {
            return eventTime;
        }

        public void setEventTime(String eventTime) {
            this.eventTime = eventTime;
        }

        public String getRecId() {
            return recId;
        }

        public void setRecId(String recId) {
            this.recId = recId;
        }
    }
}
