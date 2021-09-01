package com.base.commlibs.voiceUtils.voiceprint;

import java.util.List;

/**
 * com.dhcc.nursepro.Activity.update.bean
 * <p>
 * author Q
 * Date: 2020/9/18
 * Time:9:44
 */
public class LocUsersBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * nurseList : [{"ID":"3","jobNo":"HS01","name":"护士01","userCode":"HS01","userId":"10211"},{"ID":"173","jobNo":"1900","name":"李秀琴[1900]","userCode":"1900","userId":"10400"},{"ID":"310","jobNo":"2263","name":"汪丽华","userCode":"2263","userId":"10538"},{"ID":"649","jobNo":"3357","name":"王娟[3357]","userCode":"3357","userId":"10887"},{"ID":"652","jobNo":"3364","name":"李娇","userCode":"3364","userId":"10890"},{"ID":"706","jobNo":"3534","name":"汪舒","userCode":"3534","userId":"10944"},{"ID":"711","jobNo":"3544","name":"刘婷婷","userCode":"3544","userId":"10949"},{"ID":"796","jobNo":"3840","name":"关畅","userCode":"3840","userId":"11037"},{"ID":"798","jobNo":"3848","name":"柳玉花","userCode":"3848","userId":"11039"},{"ID":"1058","jobNo":"4973","name":"郭潘红","userCode":"4973","userId":"11319"},{"ID":"1239","jobNo":"5786","name":"王倩倩","userCode":"5786","userId":"11516"},{"ID":"1277","jobNo":"6188","name":"汪利娟","userCode":"6188","userId":"11558"},{"ID":"4","jobNo":"HS02","name":"护士02","userCode":"HS02","userId":"10212"},{"ID":"1639","jobNo":"HS03","name":"护士03","userCode":"HS03","userId":"11863"},{"ID":"7","jobNo":"1001","name":"姜春梅","userCode":"1001","userId":"10224"},{"ID":"1641","jobNo":"nurse","name":"nurse","userCode":"nurse","userId":"11"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<NurseListBean> nurseList;

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

    public List<NurseListBean> getNurseList() {
        return nurseList;
    }

    public void setNurseList(List<NurseListBean> nurseList) {
        this.nurseList = nurseList;
    }

    public static class NurseListBean {
        /**
         * ID : 3
         * jobNo : HS01
         * name : 护士01
         * userCode : HS01
         * userId : 10211
         */

        private String ID;
        private String jobNo;
        private String name;
        private String userCode;
        private String userId;
        private Boolean voiceReg = false;

        public Boolean getVoiceReg() {
            return voiceReg;
        }

        public void setVoiceReg(Boolean voiceReg) {
            this.voiceReg = voiceReg;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getJobNo() {
            return jobNo;
        }

        public void setJobNo(String jobNo) {
            this.jobNo = jobNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
