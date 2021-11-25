package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.example.nurlink
 * <p>
 * author Q
 * Date: 2021/1/20
 * Time:14:25
 */
public class AllUserBean extends CommResult {
    private List<UserListBean> UserList;

    public List<UserListBean> getUserList() {
        return UserList;
    }

    public void setUserList(List<UserListBean> UserList) {
        this.UserList = UserList;
    }

    public static class UserListBean {
        /**
         * CTLOCDesc : CT室[口腔]
         * DeviceId :
         * FirstFlag : true
         * FirstPin : C
         * GroupList : [{"GroupCode":"PWK","GroupId":"1","GroupLeader":"","GroupName":"普外烧伤救治群组"}]
         * HCCSCLRowId : 12
         * NickName :
         * PinName : CAOYING
         * UserCode : 1050
         * UserId :
         * UserName : 曹颖
         * UserType : NURSE
         * VOIPId : 1050
         */

        private String CTLOCDesc;
        private String DeviceId;
        private String FirstFlag;
        private String FirstPin;
        private String HCCSCLRowId;
        private String NickName;
        private String PinName;
        private String UserCode;
        private String UserId;
        private String UserName;
        private String UserType;
        private String VOIPId;
        private List<GroupListBean> GroupList;

        public String getCTLOCDesc() {
            return CTLOCDesc;
        }

        public void setCTLOCDesc(String CTLOCDesc) {
            this.CTLOCDesc = CTLOCDesc;
        }

        public String getDeviceId() {
            return DeviceId;
        }

        public void setDeviceId(String DeviceId) {
            this.DeviceId = DeviceId;
        }

        public String getFirstFlag() {
            return FirstFlag;
        }

        public void setFirstFlag(String FirstFlag) {
            this.FirstFlag = FirstFlag;
        }

        public String getFirstPin() {
            return FirstPin;
        }

        public void setFirstPin(String FirstPin) {
            this.FirstPin = FirstPin;
        }

        public String getHCCSCLRowId() {
            return HCCSCLRowId;
        }

        public void setHCCSCLRowId(String HCCSCLRowId) {
            this.HCCSCLRowId = HCCSCLRowId;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getPinName() {
            return PinName;
        }

        public void setPinName(String PinName) {
            this.PinName = PinName;
        }

        public String getUserCode() {
            return UserCode;
        }

        public void setUserCode(String UserCode) {
            this.UserCode = UserCode;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String UserType) {
            this.UserType = UserType;
        }

        public String getVOIPId() {
            return VOIPId;
        }

        public void setVOIPId(String VOIPId) {
            this.VOIPId = VOIPId;
        }

        public List<GroupListBean> getGroupList() {
            return GroupList;
        }

        public void setGroupList(List<GroupListBean> GroupList) {
            this.GroupList = GroupList;
        }

        public static class GroupListBean {
            /**
             * GroupCode : PWK
             * GroupId : 1
             * GroupLeader :
             * GroupName : 普外烧伤救治群组
             */

            private String GroupCode;
            private String GroupId;
            private String GroupLeader;
            private String GroupName;

            public String getGroupCode() {
                return GroupCode;
            }

            public void setGroupCode(String GroupCode) {
                this.GroupCode = GroupCode;
            }

            public String getGroupId() {
                return GroupId;
            }

            public void setGroupId(String GroupId) {
                this.GroupId = GroupId;
            }

            public String getGroupLeader() {
                return GroupLeader;
            }

            public void setGroupLeader(String GroupLeader) {
                this.GroupLeader = GroupLeader;
            }

            public String getGroupName() {
                return GroupName;
            }

            public void setGroupName(String GroupName) {
                this.GroupName = GroupName;
            }
        }
    }
}
