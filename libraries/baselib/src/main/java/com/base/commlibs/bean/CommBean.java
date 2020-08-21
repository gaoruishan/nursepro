package com.base.commlibs.bean;

import java.io.Serializable;

/**
 * 通用bean
 * @author:gaoruishan
 * @date:202019-08-19/16:31
 * @email:grs0515@163.com
 */
public class CommBean implements Serializable {
    private String name;
    private int type;
    private Object obj;
    private boolean show;
    private boolean select;
    private String curDateTime;

    public String getCurDateTime() {
        return curDateTime == null ? "" : curDateTime;
    }

    public void setCurDateTime(String curDateTime) {
        this.curDateTime = curDateTime;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public CommBean() {
    }

    public CommBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
