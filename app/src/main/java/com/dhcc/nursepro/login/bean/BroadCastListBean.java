package com.dhcc.nursepro.login.bean;

import java.util.List;

public class BroadCastListBean {

    /**
     * broadcastList : [{"Action":"ACTION_CONTENT_NOTIFY_MOTO","Decode":"com.motorolasolutions.emdk.datawedge.data_string","Name":"摩托"},{"Action":"com.scanner.broadcast","Decode":"data","Name":"成为"},{"Action":"lachesis_barcode_value_notice_broadcast","Decode":"lachesis_barcode_value_notice_broadcast_data_string","Name":"新联"}]
     * msg :
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<BroadcastListBean> broadcastList;

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

    public List<BroadcastListBean> getBroadcastList() {
        return broadcastList;
    }

    public void setBroadcastList(List<BroadcastListBean> broadcastList) {
        this.broadcastList = broadcastList;
    }

    public static class BroadcastListBean {
        /**
         * Action : ACTION_CONTENT_NOTIFY_MOTO
         * Decode : com.motorolasolutions.emdk.datawedge.data_string
         * Name : 摩托
         */

        private String Action;
        private String Decode;
        private String Name;

        public String getAction() {
            return Action;
        }

        public void setAction(String Action) {
            this.Action = Action;
        }

        public String getDecode() {
            return Decode;
        }

        public void setDecode(String Decode) {
            this.Decode = Decode;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
