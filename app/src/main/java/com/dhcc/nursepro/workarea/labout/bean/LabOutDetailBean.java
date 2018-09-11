package com.dhcc.nursepro.workarea.labout.bean;

import java.util.List;

public class LabOutDetailBean {
    /**
     * carryFlag : 1
     * detailList : [{"bedNo":"03","carryLabDesc":"粪便常规隐血试验","carryLabNo":"18032900070","carryLoc":"内分泌科护理单元","carryNo":"201808071548054636","carryStatus":"S","id":"195","patName":"马亭（演示勿动）","recDate":"","recTime":"","recUserName":"","specName":"粪便","tranStatus":"未接收"},{"bedNo":"03","carryLabDesc":"凝血六项","carryLabNo":"18032900071","carryLoc":"内分泌科护理单元","carryNo":"201808071548054636","carryStatus":"S","id":"196","patName":"马亭（演示勿动）","recDate":"","recTime":"","recUserName":"","specName":"血浆","tranStatus":"未接收"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String carryFlag;
    private String msg;
    private String msgcode;
    private String status;
    private List<DetailListBean> detailList;
    private String transContainer;

    public String getCarryFlag() {
        return carryFlag;
    }

    public void setCarryFlag(String carryFlag) {
        this.carryFlag = carryFlag;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransContainer() {
        return transContainer;
    }

    public void setTransContainer(String transContainer) {
        this.transContainer = transContainer;
    }



    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public static class DetailListBean {
        /**
         * bedNo : 03
         * carryLabDesc : 粪便常规隐血试验
         * carryLabNo : 18032900070
         * carryLoc : 内分泌科护理单元
         * carryNo : 201808071548054636
         * carryStatus : S
         * id : 195
         * patName : 马亭（演示勿动）
         * recDate :
         * recTime :
         * recUserName :
         * specName : 粪便
         * tranStatus : 未接收
         */

        private String bedNo;
        private String carryLabDesc;
        private String carryLabNo;
        private String carryLoc;
        private String carryNo;
        private String carryStatus;
        private String id;
        private String patName;
        private String recDate;
        private String recTime;
        private String recUserName;
        private String specName;
        private String tranStatus;
        private String statusDesc;

        public String getBedNo() {
            return bedNo;
        }

        public void setBedNo(String bedNo) {
            this.bedNo = bedNo;
        }

        public String getCarryLabDesc() {
            return carryLabDesc;
        }

        public void setCarryLabDesc(String carryLabDesc) {
            this.carryLabDesc = carryLabDesc;
        }

        public String getCarryLabNo() {
            return carryLabNo;
        }

        public void setCarryLabNo(String carryLabNo) {
            this.carryLabNo = carryLabNo;
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

        public String getCarryStatus() {
            return carryStatus;
        }

        public void setCarryStatus(String carryStatus) {
            this.carryStatus = carryStatus;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPatName() {
            return patName;
        }

        public void setPatName(String patName) {
            this.patName = patName;
        }

        public String getRecDate() {
            return recDate;
        }

        public void setRecDate(String recDate) {
            this.recDate = recDate;
        }

        public String getRecTime() {
            return recTime;
        }

        public void setRecTime(String recTime) {
            this.recTime = recTime;
        }

        public String getRecUserName() {
            return recUserName;
        }

        public void setRecUserName(String recUserName) {
            this.recUserName = recUserName;
        }

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
        }

        public String getTranStatus() {
            return tranStatus;
        }

        public void setTranStatus(String tranStatus) {
            this.tranStatus = tranStatus;
        }
        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }
    }
}
