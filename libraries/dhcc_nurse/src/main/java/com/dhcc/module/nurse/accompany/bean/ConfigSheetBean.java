package com.dhcc.module.nurse.accompany.bean;

/**
 * @author:gaoruishan
 * @date:202021/8/19/16:43
 * @email:grs0515@163.com
 */
public class ConfigSheetBean {

    protected String field;
    protected String title;

    public ConfigSheetBean() {
    }

    public ConfigSheetBean(String field, String title) {
        this.field = field;
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
