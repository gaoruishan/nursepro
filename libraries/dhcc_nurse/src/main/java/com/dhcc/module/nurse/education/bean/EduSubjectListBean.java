package com.dhcc.module.nurse.education.bean;

public class EduSubjectListBean {

    /**
     * desc : 入院须知
     * hospID : 2
     * id : 2
     * pid : 1
     * relateData : ["1"]
     * relateType : 2
     * startDate : 2020-07-20
     * startFlag : 1
     * stopDate :
     */

    private String desc;
    private String hospID;
    private String id;
    private String pid;
    private String relateType;
    private String startDate;
    private String startFlag;
    private String stopDate;
    private Object relateData;

    private boolean hide;
    private boolean select;
    private boolean groupSelect;

    public boolean isGroupSelect() {
        return groupSelect;
    }

    public void setGroupSelect(boolean groupSelect) {
        this.groupSelect = groupSelect;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHospID() {
        return hospID;
    }

    public void setHospID(String hospID) {
        this.hospID = hospID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 处理主标题
     * @return
     */
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRelateType() {
        return relateType;
    }

    public void setRelateType(String relateType) {
        this.relateType = relateType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(String startFlag) {
        this.startFlag = startFlag;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public Object getRelateData() {
        return relateData;
    }

    public void setRelateData(Object relateData) {
        this.relateData = relateData;
    }


    @Override
    public String toString() {
        return "EduSubjectListBean{" +
                "desc='" + desc + '\'' +
                ", hospID='" + hospID + '\'' +
                ", id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", relateType='" + relateType + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startFlag='" + startFlag + '\'' +
                ", stopDate='" + stopDate + '\'' +
                ", relateData=" + relateData +
                ", hide=" + hide +
                ", select=" + select +
                ", groupSelect=" + groupSelect +
                '}';
    }
}