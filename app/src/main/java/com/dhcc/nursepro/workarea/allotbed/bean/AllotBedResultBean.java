package com.dhcc.nursepro.workarea.allotbed.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.allotbed.bean
 * <p>
 * author Q
 * Date: 2018/9/6
 * Time:15:59
 */
public class AllotBedResultBean {
    /**
     * doctorList : [{"ID":"134","jobNo":"1772","name":"董凤奎"},{"ID":"263","jobNo":"7414","name":"张理英"},{"ID":"357","jobNo":"2351","name":"闵祥荣"},{"ID":"492","jobNo":"2656","name":"汪春林"},{"ID":"574","jobNo":"2958","name":"薛红琴"},{"ID":"694","jobNo":"3420","name":"陆志莲"},{"ID":"775","jobNo":"3676","name":"讲长林"},{"ID":"1079","jobNo":"4835","name":"李苗苗[4835]"},{"ID":"1377","jobNo":"7323","name":"丁艳"},{"ID":"1667","jobNo":"4829","name":"石亚飞"},{"ID":"161","jobNo":"1837","name":"李周妹"},{"ID":"248","jobNo":"5995","name":"王凯[5995]"},{"ID":"1454","jobNo":"0135","name":"王峰"}]
     * emptyBedList : [{"bedCode":"08","bedId":"5||8"},{"bedCode":"14","bedId":"5||14"},{"bedCode":"41","bedId":"5||41"},{"bedCode":"45","bedId":"5||45"},{"bedCode":"46","bedId":"5||46"},{"bedCode":"48","bedId":"5||48"},{"bedCode":"53","bedId":"5||53"},{"bedCode":"54","bedId":"5||54"},{"bedCode":"55","bedId":"5||55"},{"bedCode":"56","bedId":"5||56"},{"bedCode":"57","bedId":"5||57"},{"bedCode":"58","bedId":"5||58"},{"bedCode":"59","bedId":"5||59"},{"bedCode":"60","bedId":"5||60"},{"bedCode":"61","bedId":"5||61"},{"bedCode":"62","bedId":"5||62"},{"bedCode":"63","bedId":"5||63"},{"bedCode":"64","bedId":"5||64"},{"bedCode":"65","bedId":"5||65"},{"bedCode":"66","bedId":"5||66"},{"bedCode":"67","bedId":"5||67"},{"bedCode":"68","bedId":"5||68"},{"bedCode":"69","bedId":"5||69"},{"bedCode":"70","bedId":"5||71"},{"bedCode":"71","bedId":"5||72"},{"bedCode":"72","bedId":"5||73"}]
     * msg : 床位已经有人!
     * msgcode : 102121
     * nurseList : [{"ID":"4","jobNo":"hs02","name":"护士02"},{"ID":"184","jobNo":"1900","name":"李秀琴[1900]"},{"ID":"306","jobNo":"2263","name":"汪丽华"},{"ID":"671","jobNo":"3357","name":"王娟[3357]"},{"ID":"674","jobNo":"3364","name":"李娇"},{"ID":"731","jobNo":"3534","name":"汪舒"},{"ID":"736","jobNo":"3544","name":"刘婷婷"},{"ID":"830","jobNo":"3840","name":"关畅"},{"ID":"832","jobNo":"3848","name":"柳玉花"},{"ID":"1119","jobNo":"4973","name":"郭潘红"},{"ID":"1320","jobNo":"5786","name":"王倩倩"},{"ID":"1362","jobNo":"6188","name":"汪利娟"},{"ID":"1791","jobNo":"innurse","name":"innurse"},{"ID":"40","jobNo":"1411","name":"赵君"},{"ID":"3","jobNo":"hs01","name":"护士01"}]
     * status : -1
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<DoctorListBean> doctorList;
    private List<EmptyBedListBean> emptyBedList;
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

    public List<DoctorListBean> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<DoctorListBean> doctorList) {
        this.doctorList = doctorList;
    }

    public List<EmptyBedListBean> getEmptyBedList() {
        return emptyBedList;
    }

    public void setEmptyBedList(List<EmptyBedListBean> emptyBedList) {
        this.emptyBedList = emptyBedList;
    }

    public List<NurseListBean> getNurseList() {
        return nurseList;
    }

    public void setNurseList(List<NurseListBean> nurseList) {
        this.nurseList = nurseList;
    }

    public static class DoctorListBean {
        /**
         * ID : 134
         * jobNo : 1772
         * name : 董凤奎
         */

        private String ID;
        private String jobNo;
        private String name;

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
    }

    public static class EmptyBedListBean {
        /**
         * bedCode : 08
         * bedId : 5||8
         */

        private String bedCode;
        private String bedId;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getBedId() {
            return bedId;
        }

        public void setBedId(String bedId) {
            this.bedId = bedId;
        }
    }

    public static class NurseListBean {
        /**
         * ID : 4
         * jobNo : hs02
         * name : 护士02
         */

        private String ID;
        private String jobNo;
        private String name;

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
    }
}
