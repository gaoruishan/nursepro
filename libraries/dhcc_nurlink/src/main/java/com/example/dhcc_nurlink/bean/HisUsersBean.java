package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.example.dhcc_nurlink.bean
 * <p>
 * author Q
 * Date: 2020/12/10
 * Time:11:02
 */
public class HisUsersBean extends CommResult {
    private List<UserListBean> UserList;

    public List<UserListBean> getUserList() {
        return UserList;
    }

    public void setUserList(List<UserListBean> UserList) {
        this.UserList = UserList;
    }

    public static class UserListBean {
        /**
         * userCode : YS01
         * userId : 12175
         * userName : 医生01
         * userType : DOCTOR
         */

        private String userCode;
        private String userId;
        private String userName;
        private String userType;

        private String select = "0";

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }
        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
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

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
