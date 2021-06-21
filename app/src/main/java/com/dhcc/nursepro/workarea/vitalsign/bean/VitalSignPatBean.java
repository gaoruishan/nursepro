package com.dhcc.nursepro.workarea.vitalsign.bean;

public class VitalSignPatBean {
    private String bedCode;
    private String patName;
    private String regNo;
    private String episodeId;

    public VitalSignPatBean() {
    }

    public VitalSignPatBean(String bedCode, String patName, String regNo, String episodeId) {
        this.bedCode = bedCode;
        this.patName = patName;
        this.regNo = regNo;
        this.episodeId = episodeId;
    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }
}
