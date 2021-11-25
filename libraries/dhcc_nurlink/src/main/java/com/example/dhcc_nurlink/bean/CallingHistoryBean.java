package com.example.dhcc_nurlink.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.dhcc_nurlink.bean
 * <p>
 * author Q
 * Date: 2021/1/8
 * Time:9:09
 */
public class CallingHistoryBean {

    private List<callBean> callList = new ArrayList<>();

    public List<callBean> getCallList() {
        return callList;
    }

    public void setCallList(List<callBean> callList) {
        this.callList = callList;
    }

    public static class callBean {
        /**
         * callName : 来电去电姓名（会议号）
         * Status : 接听状态
         */
        String callName;
        String Time;
        String Status;
        String phoneNumber;
        String meetingNumber;
        String outOrIn;

        public String getOutOrIn() {
            return outOrIn;
        }

        public void setOutOrIn(String outOrIn) {
            this.outOrIn = outOrIn;
        }

        public String getMeetingNumber() {
            return meetingNumber;
        }

        public void setMeetingNumber(String meetingNumber) {
            this.meetingNumber = meetingNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getCallName() {
            return callName;
        }

        public void setCallName(String callName) {
            this.callName = callName;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }
    }


}
