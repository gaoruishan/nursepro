package com.dhcc.nursepro.login.bean;

import java.util.List;

/**
 * LoginBean
 *
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginBean {

    /**
     * locs : [{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"2","linkLoc":"110","locDesc":"内分泌科护理单元","locId":"197","wardId":"5"},{"groupDesc":"ICU Nurse","groupId":"138","hospitalRowId":"","linkLoc":"","locDesc":"重症医学科一","locId":"125","wardId":""},{"groupDesc":"Obstetrical Nurse","groupId":"133","hospitalRowId":"","linkLoc":"","locDesc":"产科一","locId":"156","wardId":""},{"groupDesc":"临床药学","groupId":"200","hospitalRowId":"2","linkLoc":"110","locDesc":"内分泌科护理单元","locId":"197","wardId":"5"},{"groupDesc":"Operation Nurse","groupId":"140","hospitalRowId":"2","linkLoc":"364","locDesc":"手术室","locId":"362","wardId":"82"},{"groupDesc":"Nursing Manager","groupId":"147","hospitalRowId":"","linkLoc":"","locDesc":"护理部","locId":"402","wardId":""},{"groupDesc":"Nursing Manager","groupId":"","hospitalRowId":"","linkLoc":"","locDesc":"风湿免疫科门诊","locId":"5","wardId":""}]
     * msg :
     * msgcode : 999999
     * schEnDateTime : 13/08/2018,23:59:59
     * schStDateTime : 13/08/2018,00:00:00
     * status : 0
     * userId : 3
     * userName : innurse
     */

    private String msg;
    private String msgcode;
    private String schEnDateTime;
    private String schStDateTime;
    private String status;
    private String userId;
    private String userName;
    private List<LocsBean> locs;

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

    public String getSchEnDateTime() {
        return schEnDateTime;
    }

    public void setSchEnDateTime(String schEnDateTime) {
        this.schEnDateTime = schEnDateTime;
    }

    public String getSchStDateTime() {
        return schStDateTime;
    }

    public void setSchStDateTime(String schStDateTime) {
        this.schStDateTime = schStDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<LocsBean> getLocs() {
        return locs;
    }

    public void setLocs(List<LocsBean> locs) {
        this.locs = locs;
    }

    public static class LocsBean {
        /**
         * groupDesc : Inpatient Nurse
         * groupId : 132
         * hospitalRowId : 2
         * linkLoc : 110
         * locDesc : 内分泌科护理单元
         * locId : 197
         * wardId : 5
         */

        private String groupDesc;
        private String groupId;
        private String hospitalRowId;
        private String linkLoc;
        private String locDesc;
        private String locId;
        private String wardId;

        public String getGroupDesc() {
            return groupDesc;
        }

        public void setGroupDesc(String groupDesc) {
            this.groupDesc = groupDesc;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getHospitalRowId() {
            return hospitalRowId;
        }

        public void setHospitalRowId(String hospitalRowId) {
            this.hospitalRowId = hospitalRowId;
        }

        public String getLinkLoc() {
            return linkLoc;
        }

        public void setLinkLoc(String linkLoc) {
            this.linkLoc = linkLoc;
        }

        public String getLocDesc() {
            return locDesc;
        }

        public void setLocDesc(String locDesc) {
            this.locDesc = locDesc;
        }

        public String getLocId() {
            return locId;
        }

        public void setLocId(String locId) {
            this.locId = locId;
        }

        public String getWardId() {
            return wardId;
        }

        public void setWardId(String wardId) {
            this.wardId = wardId;
        }
    }
}
