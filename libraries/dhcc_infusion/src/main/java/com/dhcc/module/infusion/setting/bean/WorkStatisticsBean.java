package com.dhcc.module.infusion.setting.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-05-13/17:07
 * @email:grs0515@163.com
 */
public class WorkStatisticsBean extends CommResult {

    /**
     * TypeList : [{"WorkTypeDesc":"配液","WorkTypeNum":"8"},{"WorkTypeDesc":"复合","WorkTypeNum":"8"},{"WorkTypeDesc":"穿刺","WorkTypeNum":"7"},{"WorkTypeDesc":"巡视","WorkTypeNum":"7"},{"WorkTypeDesc":"换液","WorkTypeNum":"8"},{"WorkTypeDesc":"拔针","WorkTypeNum":"7"}]
     * UserList : [{"UserName":"护士01","UserNum":"45","userTypeList":[{"WorkTypeDesc":"配液","WorkTypeNum":"8"},{"WorkTypeDesc":"复合","WorkTypeNum":"8"},{"WorkTypeDesc":"穿刺","WorkTypeNum":"7"},{"WorkTypeDesc":"巡视","WorkTypeNum":"7"},{"WorkTypeDesc":"Change","WorkTypeNum":"8"},{"WorkTypeDesc":"拔针","WorkTypeNum":"7"}]}]
     * ordInfoArr :
     */

    private String ordInfoArr;
    private List<TypeListBean> TypeList;
    private List<UserListBean> UserList;

    public String getOrdInfoArr() {
        return ordInfoArr;
    }

    public void setOrdInfoArr(String ordInfoArr) {
        this.ordInfoArr = ordInfoArr;
    }

    public List<TypeListBean> getTypeList() {
        return TypeList;
    }

    public void setTypeList(List<TypeListBean> TypeList) {
        this.TypeList = TypeList;
    }

    public List<UserListBean> getUserList() {
        return UserList;
    }

    public void setUserList(List<UserListBean> UserList) {
        this.UserList = UserList;
    }

    public static class TypeListBean {
        /**
         * WorkTypeDesc : 配液
         * WorkTypeNum : 8
         */

        private String WorkTypeDesc;
        private String WorkTypeNum;

        public String getWorkTypeDesc() {
            return WorkTypeDesc;
        }

        public void setWorkTypeDesc(String WorkTypeDesc) {
            this.WorkTypeDesc = WorkTypeDesc;
        }

        public String getWorkTypeNum() {
            return WorkTypeNum;
        }

        public void setWorkTypeNum(String WorkTypeNum) {
            this.WorkTypeNum = WorkTypeNum;
        }
    }

    public static class UserListBean {
        /**
         * UserName : 护士01
         * UserNum : 45
         * userTypeList : [{"WorkTypeDesc":"配液","WorkTypeNum":"8"},{"WorkTypeDesc":"复合","WorkTypeNum":"8"},{"WorkTypeDesc":"穿刺","WorkTypeNum":"7"},{"WorkTypeDesc":"巡视","WorkTypeNum":"7"},{"WorkTypeDesc":"Change","WorkTypeNum":"8"},{"WorkTypeDesc":"拔针","WorkTypeNum":"7"}]
         */

        private String UserName;
        private String UserNum;
        private List<TypeListBean> userTypeList;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserNum() {
            return UserNum;
        }

        public void setUserNum(String UserNum) {
            this.UserNum = UserNum;
        }

        public List<TypeListBean> getUserTypeList() {
            return userTypeList;
        }

        public void setUserTypeList(List<TypeListBean> userTypeList) {
            this.userTypeList = userTypeList;
        }
    }
}
