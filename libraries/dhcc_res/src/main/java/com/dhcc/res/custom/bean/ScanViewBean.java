package com.dhcc.res.custom.bean;

public class ScanViewBean {
    private String icon;
    private String title;
    private String content;
    private String desc;
    private String param;
    private String method;

    public String getIcon() {
        return icon == null ? "" : icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParam() {
        return param == null ? "" : param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getMethod() {
        return method == null ? "" : method;
    }

    public void setMethod(String action) {
        this.method = action;
    }
}
