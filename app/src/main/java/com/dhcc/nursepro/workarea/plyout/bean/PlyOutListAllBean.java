package com.dhcc.nursepro.workarea.plyout.bean;

import java.util.List;

public class PlyOutListAllBean {
    /**
     * labOutList : [{"carryCount":"0","carryDate":"2018-08-30","carryLoc":"内分泌科护理单元","carryNo":"201808301029414636","carryRowId":"324","carryTime":"10:29:41","carryUser":"护士01","reciCount":"0","status":"建单","transDate":"","transTime":"","transUser":""},{"carryCount":"0","carryDate":"2018-08-31","carryLoc":"内分泌科护理单元","carryNo":"201808311707544637","carryRowId":"325","carryTime":"17:07:54","carryUser":"护士02","reciCount":"0","status":"建单","transDate":"","transTime":"","transUser":""},{"carryCount":"0","carryDate":"2018-08-31","carryLoc":"内分泌科护理单元","carryNo":"201808311708114637","carryRowId":"326","carryTime":"17:08:11","carryUser":"护士02","reciCount":"0","status":"建单","transDate":"","transTime":"","transUser":""},{"carryCount":"0","carryDate":"2018-08-31","carryLoc":"内分泌科护理单元","carryNo":"201808311708274637","carryRowId":"327","carryTime":"17:08:27","carryUser":"护士02","reciCount":"0","status":"建单","transDate":"","transTime":"","transUser":""}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     * typeList : [{"code":"C","desc":"建单"},{"code":"S","desc":"已交接"},{"code":"P","desc":"部分处理"},{"code":"R","desc":"全部接收"}]
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<LabOutListBean> labOutList;
    private List<TypeListBean> typeList;

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

    public List<LabOutListBean> getLabOutList() {
        return labOutList;
    }

    public void setLabOutList(List<LabOutListBean> labOutList) {
        this.labOutList = labOutList;
    }

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    public static class LabOutListBean {
        /**
         * carryCount : 0
         * carryDate : 2018-08-30
         * carryLoc : 内分泌科护理单元
         * carryNo : 201808301029414636
         * carryRowId : 324
         * carryTime : 10:29:41
         * carryUser : 护士01
         * reciCount : 0
         * status : 建单
         * transDate :
         * transTime :
         * transUser :
         */

        private String carryCount;
        private String carryDate;
        private String carryLoc;
        private String carryNo;
        private String carryRowId;
        private String carryTime;
        private String carryUser;
        private String reciCount;
        private String status;
        private String transDate;
        private String transTime;
        private String transUser;

        public String getCarryCount() {
            return carryCount;
        }

        public void setCarryCount(String carryCount) {
            this.carryCount = carryCount;
        }

        public String getCarryDate() {
            return carryDate;
        }

        public void setCarryDate(String carryDate) {
            this.carryDate = carryDate;
        }

        public String getCarryLoc() {
            return carryLoc;
        }

        public void setCarryLoc(String carryLoc) {
            this.carryLoc = carryLoc;
        }

        public String getCarryNo() {
            return carryNo;
        }

        public void setCarryNo(String carryNo) {
            this.carryNo = carryNo;
        }

        public String getCarryRowId() {
            return carryRowId;
        }

        public void setCarryRowId(String carryRowId) {
            this.carryRowId = carryRowId;
        }

        public String getCarryTime() {
            return carryTime;
        }

        public void setCarryTime(String carryTime) {
            this.carryTime = carryTime;
        }

        public String getCarryUser() {
            return carryUser;
        }

        public void setCarryUser(String carryUser) {
            this.carryUser = carryUser;
        }

        public String getReciCount() {
            return reciCount;
        }

        public void setReciCount(String reciCount) {
            this.reciCount = reciCount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTransDate() {
            return transDate;
        }

        public void setTransDate(String transDate) {
            this.transDate = transDate;
        }

        public String getTransTime() {
            return transTime;
        }

        public void setTransTime(String transTime) {
            this.transTime = transTime;
        }

        public String getTransUser() {
            return transUser;
        }

        public void setTransUser(String transUser) {
            this.transUser = transUser;
        }
    }

    public static class TypeListBean {
        /**
         * code : C
         * desc : 建单
         */

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
