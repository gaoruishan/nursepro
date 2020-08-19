package com.dhcc.module.nurse.nurplan.bean;

public class StatusReasonListBean {
    /**
     * text : 误操作
     * value : 0
     */

    private String text;
    private String value;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StatusReasonListBean{" +
                "text='" + text + '\'' +
                ", value='" + value + '\'' +
                ", select=" + select +
                '}';
    }
}