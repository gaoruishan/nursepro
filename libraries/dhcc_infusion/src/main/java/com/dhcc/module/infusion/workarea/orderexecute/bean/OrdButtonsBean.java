package com.dhcc.module.infusion.workarea.orderexecute.bean;

import com.dhcc.res.infusion.bean.ClickBean;

public class OrdButtonsBean extends ClickBean {
    /**
     * code : excuteOrder
     * desc : 执行
     * exeCode : F
     */
    private String code;
    private String desc;
    private String exeCode;

    public String getCode() {
        return code == null ? "" : code;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public String getExeCode() {
        return exeCode;
    }

    public void setExeCode(String exeCode) {
        this.exeCode = exeCode;
    }

    @Override
    public String getText() {
        return desc;
    }
}