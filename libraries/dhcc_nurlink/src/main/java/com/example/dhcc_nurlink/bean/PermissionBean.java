package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

/**
 * com.example.nurlink
 * <p>
 * author Q
 * Date: 2021/1/20
 * Time:14:25
 */
public class PermissionBean extends CommResult {
    private String patControl;
    private String userType;

    public String getPatControl() {
        return patControl;
    }

    public void setPatControl(String patControl) {
        this.patControl = patControl;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
