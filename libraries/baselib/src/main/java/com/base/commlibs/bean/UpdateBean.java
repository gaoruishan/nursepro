package com.base.commlibs.bean;

import com.base.commlibs.http.CommResult;

public class UpdateBean extends CommResult {

    /**
     * msg :
     * msgcode : 999999
     * newVersion : 1.2.0
     * status : 0
     */

    private String appAddress;
    private String newVersion;

    public String getAppAddress() {
        return appAddress;
    }

    public void setAppAddress(String appAddress) {
        this.appAddress = appAddress;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }
}
