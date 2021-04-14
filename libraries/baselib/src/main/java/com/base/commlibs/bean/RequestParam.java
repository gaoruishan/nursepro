package com.base.commlibs.bean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202021/4/13/10:29
 * @email:grs0515@163.com
 */
public class RequestParam {

    /**
     * password : 1
     * logonWardId :
     * userCode : hs01
     */

    private HashMap<String, String> params;
    /**
     * params : {"password":"1","logonWardId":"","userCode":"hs01"}
     * method : Logon
     * version : 3.3
     */

    private String method;
    private String version;

    public HashMap<String, String> getParams() {
        return params == null ? new HashMap<String, String>() : params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
