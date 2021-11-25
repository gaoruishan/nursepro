package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.example.dhcc_nurlink.bean
 * <p>
 * author Q
 * Date: 2020/12/10
 * Time:14:29
 */
public class PhoneBookListBean extends CommResult {
    private List<GroupListBean> GroupList;
    private List<UserListBean> UserList;

    public List<GroupListBean> getGroupList() {
        return GroupList;
    }

    public void setGroupList(List<GroupListBean> GroupList) {
        this.GroupList = GroupList;
    }

    public List<UserListBean> getUserList() {
        return UserList;
    }

    public void setUserList(List<UserListBean> UserList) {
        this.UserList = UserList;
    }

    public static class GroupListBean {
        /**
         * GroupCode : FZY
         * GroupLeader : 10211
         * GroupName : 分组一
         */

        private String GroupCode;
        private String GroupLeader;
        private String GroupName;
        private String GroupId;

        public String getGroupId() {
            return GroupId;
        }

        public void setGroupId(String groupId) {
            GroupId = groupId;
        }

        public String getGroupCode() {
            return GroupCode;
        }

        public void setGroupCode(String GroupCode) {
            this.GroupCode = GroupCode;
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

    public static class UserListBean {
        /**
         * DeviceId : 003
         * FirstFlag : true
         * FirstPin : H
         * NickName : 测试用户3
         * PinName : HS01
         * UserCode : HS01
         * UserId : 12177
         * UserName : 护士01
         * UserType : NURSE
         * VOIPId : 111113
         */

        private String DeviceId;
        private String FirstFlag="true";
        private String FirstPin="A";
        private String NickName;
        private String PinName;
        private String UserCode;
        private String UserId;
        private String UserName;
        private String UserType;
        private String VOIPId;
        private String select = "0";

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
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
    }
}
