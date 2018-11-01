package com.dhcc.nursepro.login.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.login.bean
 * <p>
 * author Q
 * Date: 2018/11/1
 * Time:9:48
 */
public class GetScanActionBean {
    /**
     * broadcastList : [{"Action":"com.scanner.broadcast","Decode":"data","Name":"成为"},{"Action":"test","Decode":"testString","Name":"测试设备"}]
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
         * Action : com.scanner.broadcast
         * Decode : data
         * Name : 成为
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
