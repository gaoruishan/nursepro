package com.dhcc.module.nurse.task.bean;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/14
 * Time:14:27
 */
public class StatusListBean {
    /**
     * text : 未执行
     * value : 0
     */

    private String text;
    private String value;
    private Boolean select = false;

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
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
}
