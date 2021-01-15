package com.dhcc.nursepro.workarea.pathandover.bean;

import java.util.List;

public class GetConnectListBean {

    /**
     * connectAllList : [{"bedCode":"01","name":"刘新雨（演示勿动）","patRegNo":"0000000089","record":[{"firstUser":"12177","rigSubDate":"2021-01-14","rigSubTime":"09:10","rigSubType":"病房移交手术室"},{"firstUser":"12177","rigSubDate":"2021-01-14","rigSubTime":"09:10","rigSubType":"手术室移交病房"}],"rigDate":"2021-01-14","rigTime":"09:08","rigType":"手术","rigUser":"12177"},{"bedCode":"01","name":"刘新雨（演示勿动）","patRegNo":"0000000089","record":[],"rigDate":"2021-01-14","rigTime":"09:09","rigType":"转科","rigUser":"12177"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     * connectType: 手术:1^转科:2^分娩:3^治疗:4
     */

    private String msg;
    private String msgcode;
    private String status;
    private String connectType;
    /**
     * bedCode : 01
     * name : 刘新雨（演示勿动）
     * patRegNo : 0000000089
     * record : [{"firstUser":"12177","rigSubDate":"2021-01-14","rigSubTime":"09:10","rigSubType":"病房移交手术室"},{"firstUser":"12177","rigSubDate":"2021-01-14","rigSubTime":"09:10","rigSubType":"手术室移交病房"}]
     * rigDate : 2021-01-14
     * rigTime : 09:08
     * rigType : 手术
     * rigUser : 12177
     */

    private List<ConnectAllListBean> connectAllList;

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

    public String getConnectType() {
        return connectType;
    }

    public void setConnectType(String connectType) {
        this.connectType = connectType;
    }

    public List<ConnectAllListBean> getConnectAllList() {
        return connectAllList;
    }

    public void setConnectAllList(List<ConnectAllListBean> connectAllList) {
        this.connectAllList = connectAllList;
    }

    public static class ConnectAllListBean {
        private String bedCode;
        private String name;
        private String patRegNo;
        private String rigDate;
        private String rigTime;
        private String rigType;
        private String rigUser;
        private int recordShow;
        /**
         * firstUser : 12177
         * rigSubDate : 2021-01-14
         * rigSubTime : 09:10
         * rigSubType : 病房移交手术室
         */

        private List<RecordBean> record;

        public int getRecordShow() {
            return recordShow;
        }

        public void setRecordShow(int recordShow) {
            this.recordShow = recordShow;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatRegNo() {
            return patRegNo;
        }

        public void setPatRegNo(String patRegNo) {
            this.patRegNo = patRegNo;
        }

        public String getRigDate() {
            return rigDate;
        }

        public void setRigDate(String rigDate) {
            this.rigDate = rigDate;
        }

        public String getRigTime() {
            return rigTime;
        }

        public void setRigTime(String rigTime) {
            this.rigTime = rigTime;
        }

        public String getRigType() {
            return rigType;
        }

        public void setRigType(String rigType) {
            this.rigType = rigType;
        }

        public String getRigUser() {
            return rigUser;
        }

        public void setRigUser(String rigUser) {
            this.rigUser = rigUser;
        }

        public List<RecordBean> getRecord() {
            return record;
        }

        public void setRecord(List<RecordBean> record) {
            this.record = record;
        }

        public static class RecordBean {
            private String firstUser;
            private String rigSubDate;
            private String rigSubTime;
            private String rigSubType;

            public String getFirstUser() {
                return firstUser;
            }

            public void setFirstUser(String firstUser) {
                this.firstUser = firstUser;
            }

            public String getRigSubDate() {
                return rigSubDate;
            }

            public void setRigSubDate(String rigSubDate) {
                this.rigSubDate = rigSubDate;
            }

            public String getRigSubTime() {
                return rigSubTime;
            }

            public void setRigSubTime(String rigSubTime) {
                this.rigSubTime = rigSubTime;
            }

            public String getRigSubType() {
                return rigSubType;
            }

            public void setRigSubType(String rigSubType) {
                this.rigSubType = rigSubType;
            }
        }
    }
}
