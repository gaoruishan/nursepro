package com.dhcc.module.infusion.setting.bean;

public class TypeListBean {
    /**
     * WorkTypeDesc : 配液
     * WorkTypeNum : 8
     */

    private String WorkTypeDesc;
    private String WorkTypeNum;
    private String WorkDateTime;
    private String WorkOrdDesc;
    private String WorkType;
    private String WorkOeoreId;

    public String getWorkType() {
        return WorkType == null ? "" : WorkType;
    }

    public void setWorkType(String workType) {
        WorkType = workType;
    }

    public String getWorkDateTime() {
        return WorkDateTime == null ? "" : WorkDateTime;
    }

    public void setWorkDateTime(String workDateTime) {
        WorkDateTime = workDateTime;
    }

    public String getWorkOrdDesc() {
        return WorkOrdDesc == null ? "" : WorkOrdDesc;
    }

    public void setWorkOrdDesc(String workOrdDesc) {
        WorkOrdDesc = workOrdDesc;
    }

    public String getWorkOeoreId() {
        return WorkOeoreId == null ? "" : WorkOeoreId;
    }

    public void setWorkOeoreId(String workOeoreId) {
        WorkOeoreId = workOeoreId;
    }

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
