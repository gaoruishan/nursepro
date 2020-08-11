package com.dhcc.res.infusion.bean;

import java.io.Serializable;

public class SheetListBean implements Serializable {
    /**
     * code : DefaultSee
     * desc : 需处理医嘱
     */

    private String code;
    private String desc;
    private String num;
    private boolean select;

    public SheetListBean() {
    }

    public SheetListBean(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public SheetListBean(String code, String desc,String num) {
        this.code = code;
        this.desc = desc;
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SheetListBean{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", select=" + select +
                '}';
    }
}