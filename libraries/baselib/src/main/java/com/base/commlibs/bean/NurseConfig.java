package com.base.commlibs.bean;

/**
 * @author:gaoruishan
 * @date:202021/4/14/09:13
 * @email:grs0515@163.com
 */
public class NurseConfig {

    /**
     * webServiceUserName : dhwebservice
     * webServicePassword : dhwebservice
     * defaultIP : 10.1.21.123
     * defaultPath : /imedical/web
     * oppdaService : /Nur.MOES.Service.WebService.cls
     * pdaService : /Nur.MNIS.Service.WebService.cls
     */

    private String webServiceUserName;
    private String webServicePassword;
    private String defaultIP;
    private String defaultPath;
    private String oppdaService;
    private String pdaService;

    public String getWebServiceUserName() {
        return webServiceUserName;
    }

    public void setWebServiceUserName(String webServiceUserName) {
        this.webServiceUserName = webServiceUserName;
    }

    public String getWebServicePassword() {
        return webServicePassword;
    }

    public void setWebServicePassword(String webServicePassword) {
        this.webServicePassword = webServicePassword;
    }

    public String getDefaultIP() {
        return defaultIP;
    }

    public void setDefaultIP(String defaultIP) {
        this.defaultIP = defaultIP;
    }

    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public String getOppdaService() {
        return oppdaService;
    }

    public void setOppdaService(String oppdaService) {
        this.oppdaService = oppdaService;
    }

    public String getPdaService() {
        return pdaService;
    }

    public void setPdaService(String pdaService) {
        this.pdaService = pdaService;
    }

    @Override
    public String toString() {
        return "NurseConfig{" +
                "webServiceUserName='" + webServiceUserName + '\'' +
                ", webServicePassword='" + webServicePassword + '\'' +
                ", defaultIP='" + defaultIP + '\'' +
                ", defaultPath='" + defaultPath + '\'' +
                ", oppdaService='" + oppdaService + '\'' +
                ", pdaService='" + pdaService + '\'' +
                '}';
    }
}
