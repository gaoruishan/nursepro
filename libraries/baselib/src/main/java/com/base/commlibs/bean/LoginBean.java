package com.base.commlibs.bean;

import java.util.List;

/**
 * LoginBean
 * @author DevLix126
 * @date 2018/8/13
 */
public class LoginBean extends ConfigBean {
    /**
     * broadcastList : [{"Action":"ACTION_CONTENT_NOTIFY_MOTO","Decode":"com.motorolasolutions.emdk.datawedge.data_string","Name":"摩托"},{"Action":"com.ge.action.barscan","Decode":"value","Name":"易迈海"},{"Action":"com.scanner.broadcast","Decode":"data","Name":"成为"},{"Action":"lachesis_barcode_value_notice_broadcast","Decode":"lachesis_barcode_value_notice_broadcast_data_string","Name":"新联"}]
     * locs : [{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"门诊护士","groupId":"105","hospitalRowId":"","linkLoc":"","locDesc":"肝胆外科门诊 门诊护士","locId":"13","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"急诊输液护士","groupId":"175","hospitalRowId":"","linkLoc":"","locDesc":"急诊内科门诊 急诊输液护士","locId":"266","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"急诊分诊护士","groupId":"199","hospitalRowId":"","linkLoc":"","locDesc":"急诊内科门诊 急诊分诊护士","locId":"266","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"急诊治疗护士","groupId":"225","hospitalRowId":"","linkLoc":"","locDesc":"急诊内科门诊 急诊治疗护士","locId":"266","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"急诊分诊护士","groupId":"199","hospitalRowId":"","linkLoc":"","locDesc":"急诊外科门诊 急诊分诊护士","locId":"267","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"急诊输液护士","groupId":"175","hospitalRowId":"","linkLoc":"","locDesc":"急诊输液室 急诊输液护士","locId":"285","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"输液护士","groupId":"212","hospitalRowId":"","linkLoc":"","locDesc":"门诊输液室 输液护士","locId":"287","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"治疗护士","groupId":"213","hospitalRowId":"","linkLoc":"","locDesc":"门诊输液室 治疗护士","locId":"287","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"急诊输液护士（旧）（停用）","groupId":"227","hospitalRowId":"","linkLoc":"","locDesc":"门诊输液室 急诊输液护士（旧）（停用）","locId":"287","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"门诊护士","groupId":"105","hospitalRowId":"","linkLoc":"","locDesc":"呼吸内科门诊 门诊护士","locId":"2","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"分诊护士","groupId":"18","hospitalRowId":"","linkLoc":"","locDesc":"呼吸内科门诊 分诊护士","locId":"2","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"标本采集","groupId":"26","hospitalRowId":"","linkLoc":"","locDesc":"采血处 标本采集","locId":"354","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"治疗护士","groupId":"213","hospitalRowId":"","linkLoc":"","locDesc":"换药室 治疗护士","locId":"372","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"分诊护士","groupId":"18","hospitalRowId":"","linkLoc":"","locDesc":"消化内科门诊 分诊护士","locId":"3","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"护理部主任","groupId":"117","hospitalRowId":"","linkLoc":"","locDesc":"护理部 护理部主任","locId":"402","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"护理部","groupId":"69","hospitalRowId":"","linkLoc":"","locDesc":"护理部 护理部","locId":"402","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"分诊护士","groupId":"18","hospitalRowId":"","linkLoc":"","locDesc":"内分泌科门诊[分院] 分诊护士","locId":"56","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"门诊护士","groupId":"105","hospitalRowId":"","linkLoc":"","locDesc":"内分泌门诊 门诊护士","locId":"7","wardId":""},{"WinList":[{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}],"groupDesc":"分诊护士","groupId":"18","hospitalRowId":"","linkLoc":"","locDesc":"内分泌门诊 分诊护士","locId":"7","wardId":""}]
     * msg :
     * msgcode : 999999
     * status : 0
     * userId : 4636
     * userName : 护士01
     */
    private String schEnDateTime;
    private String schStDateTime;
    private String userId;
    private String userName;

    private List<BroadcastListBean> broadcastList;
    private List<LocsBean> locs;

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

    public static class LocsBean {
        /**
         * WinList : [{"WinCode":"1","WinDesc":"窗口1"},{"WinCode":"2","WinDesc":"窗口2"}]
         * groupDesc : 门诊护士
         * groupId : 105
         * hospitalRowId :
         * linkLoc :
         * locDesc : 肝胆外科门诊 门诊护士
         * locId : 13
         * wardId :
         */

        private String groupDesc;
        private String groupId;
        private String hospitalRowId;
        private String linkLoc;
        private String locDesc;
        private String locId;
        private String wardId;
        private List<WinListBean> WinList;

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
            return wardId == null ? "" : wardId;
        }

        public void setWardId(String wardId) {
            this.wardId = wardId;
        }

        public List<WinListBean> getWinList() {
            return WinList;
        }

        public void setWinList(List<WinListBean> WinList) {
            this.WinList = WinList;
        }

        public static class WinListBean {
            /**
             * WinCode : 1
             * WinDesc : 窗口1
             */

            private String WinCode;
            private String WinDesc;

            public String getWinCode() {
                return WinCode;
            }

            public void setWinCode(String WinCode) {
                this.WinCode = WinCode;
            }

            public String getWinDesc() {
                return WinDesc;
            }

            public void setWinDesc(String WinDesc) {
                this.WinDesc = WinDesc;
            }
        }
    }
}
