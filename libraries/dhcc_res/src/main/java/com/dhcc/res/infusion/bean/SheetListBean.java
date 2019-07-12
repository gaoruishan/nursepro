package com.dhcc.res.infusion.bean;

import java.io.Serializable;

public class SheetListBean implements Serializable {
    /**
     * code : DefaultSee
     * desc : 需处理医嘱
     */

    private String code;
    private String desc;

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
}