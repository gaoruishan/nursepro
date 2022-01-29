package com.dhcc.res.custom.bean;

public class DateTimeViewBean {
    private String start;
    private String end;
    private String param;
    private String desc;

    public String getStart() {
        return start == null ? "" : start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end == null ? "" : end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getParam() {
        return param == null ? "" : param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
