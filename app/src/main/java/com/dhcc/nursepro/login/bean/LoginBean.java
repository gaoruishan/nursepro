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
     * broadcastList : [{"Action":"com.scanner.broadcast","Decode":"data","Name":"成为"},{"Action":"com.scanner.test","Decode":"test","Name":"成为2"},{"Action":"lachesis_barcode_value_notice_broadcast","Decode":"lachesis_barcode_value_notice_broadcast_data_string","Name":"新联"}]
     * locs : [{"groupDesc":"住院护士长","groupId":"25","hospitalRowId":"0","linkLoc":"110","locDesc":"内分泌科护理单元","locId":"197","wardId":"5"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"104","locDesc":"呼吸内科一二护理单元","locId":"193","wardId":"1"},{"groupDesc":"住院护士长","groupId":"25","hospitalRowId":"0","linkLoc":"108","locDesc":"消化内科一护理单元","locId":"195","wardId":"3"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"109","locDesc":"消化内科二护理单元","locId":"196","wardId":"4"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"110","locDesc":"内分泌科护理单元","locId":"197","wardId":"5"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"111","locDesc":"血液内科护理单元","locId":"198","wardId":"6"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"114","locDesc":"心内科一护理单元","locId":"200","wardId":"8"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"114","locDesc":"心内科二护理单元","locId":"201","wardId":"9"},{"groupDesc":"ICU护士长","groupId":"114","hospitalRowId":"0","linkLoc":"125","locDesc":"重症医学科一护理单元","locId":"210","wardId":"18"},{"groupDesc":"ICU护士","groupId":"24","hospitalRowId":"0","linkLoc":"125","locDesc":"重症医学科一护理单元","locId":"210","wardId":"18"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"136","locDesc":"肝胆外科护理单元","locId":"220","wardId":"28"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"149","locDesc":"心胸外科护理护理单元","locId":"231","wardId":"39"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"151","locDesc":"神经外科肿瘤功能护理单元","locId":"232","wardId":"40"},{"groupDesc":"产科护士","groupId":"111","hospitalRowId":"0","linkLoc":"156","locDesc":"产科一护理单元","locId":"236","wardId":"44"},{"groupDesc":"产科护士长","groupId":"112","hospitalRowId":"0","linkLoc":"156","locDesc":"产科一护理单元","locId":"236","wardId":"44"},{"groupDesc":"产科护士","groupId":"111","hospitalRowId":"0","linkLoc":"157","locDesc":"产科二护理单元","locId":"237","wardId":"45"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"158","locDesc":"眼科护理单元","locId":"238","wardId":"46"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"168","locDesc":"消化肝胆护理单元[分院]","locId":"246","wardId":"54"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"0","linkLoc":"169","locDesc":"内分泌科护理单元[分院]","locId":"247","wardId":"55"},{"groupDesc":"产科护士","groupId":"111","hospitalRowId":"0","linkLoc":"190","locDesc":"产科一护理单元[分院]","locId":"261","wardId":"69"},{"groupDesc":"急诊输液护士","groupId":"175","hospitalRowId":"","linkLoc":"","locDesc":"急诊内科门诊","locId":"266","wardId":""},{"groupDesc":"急诊治疗护士","groupId":"225","hospitalRowId":"","linkLoc":"","locDesc":"急诊内科门诊","locId":"266","wardId":""},{"groupDesc":"急诊输液护士","groupId":"175","hospitalRowId":"","linkLoc":"","locDesc":"急诊输液室","locId":"285","wardId":""},{"groupDesc":"急诊留观护士","groupId":"22","hospitalRowId":"0","linkLoc":"266","locDesc":"急诊留观室","locId":"286","wardId":"75"},{"groupDesc":"手术室护士","groupId":"51","hospitalRowId":"0","linkLoc":"364","locDesc":"手术室","locId":"362","wardId":"82"},{"groupDesc":"手术室护士长","groupId":"52","hospitalRowId":"0","linkLoc":"364","locDesc":"手术室","locId":"362","wardId":"82"},{"groupDesc":"门诊手术室护士","groupId":"204","hospitalRowId":"0","linkLoc":"0","locDesc":"门诊手术室","locId":"365","wardId":"84"}]
     * msg :
     * msgcode : 999999
     * schEnDateTime : 2019-01-04 23:59:59
     * schStDateTime : 2019-01-04 00:00:00
     * status : 0
     * userId : 4636
     * userName : 护士01
     */

    private String msg;
    private String msgcode;
    private String schEnDateTime;
    private String schStDateTime;
    private String status;
    private String userId;
    private String userName;
    private List<BroadcastListBean> broadcastList;
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

    public List<BroadcastListBean> getBroadcastList() {
        return broadcastList;
    }

    public void setBroadcastList(List<BroadcastListBean> broadcastList) {
        this.broadcastList = broadcastList;
    }

    public List<LocsBean> getLocs() {
        return locs;
    }

    public void setLocs(List<LocsBean> locs) {
        this.locs = locs;
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

    public static class LocsBean {
        /**
         * groupDesc : 住院护士长
         * groupId : 25
         * hospitalRowId : 0
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
