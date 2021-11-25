package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

/**
 * com.example.dhcc_nurlink.bean
 * <p>
 * author Q
 * Date: 2020/11/27
 * Time:13: 58
 */
public class UserBean extends CommResult {
    private String userName;
    private String telStatus;
    private String select = "0";

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelStatus() {
        return telStatus;
    }

    public void setTelStatus(String telStatus) {
        this.telStatus = telStatus;
    }
}
