package com.dhcc.nursepro.workarea.plyout.bean;

import java.util.List;

public class PlyOutDetailBean {
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
            return bedNo == null ? "" : bedNo;
        }

        public String getCarryLabDesc() {
            return carryLabDesc == null ? "" : carryLabDesc;
        }

        public String getCarryLabNo() {
            return carryLabNo == null ? "" : carryLabNo;
        }

        public String getCarryLoc() {
            return carryLoc == null ? "" : carryLoc;
        }

        public String getCarryNo() {
            return carryNo == null ? "" : carryNo;
        }

        public String getCarryStatus() {
            return carryStatus == null ? "" : carryStatus;
        }

        public String getId() {
            return id == null ? "" : id;
        }

        public String getPatName() {
            return patName == null ? "" : patName;
        }

        public String getRecDate() {
            return recDate == null ? "" : recDate;
        }

        public String getRecTime() {
            return recTime == null ? "" : recTime;
        }

        public String getRecUserName() {
            return recUserName == null ? "" : recUserName;
        }

        public String getSpecName() {
            return specName == null ? "" : specName;
        }

        public String getTranStatus() {
            return tranStatus == null ? "" : tranStatus;
        }

        public String getStatusDesc() {
            return statusDesc == null ? "" : statusDesc;
        }
    }
}
