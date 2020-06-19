package com.dhcc.module.infusion.workarea.transblood.bean;

/**
 * @author:gaoruishan
 * @date:202020-03-20/11:04
 * @email:grs0515@163.com
 */
public class InfusionTypeBean {

    /**
     * InfusionType : 输注前
     * InfusionTypeFlag : 0
     */

    private String InfusionType;
    private String InfusionTypeFlag;

    public String getInfusionType() {
        return InfusionType;
    }

    public void setInfusionType(String InfusionType) {
        this.InfusionType = InfusionType;
    }

    public String getInfusionTypeFlag() {
        return InfusionTypeFlag;
    }

    public void setInfusionTypeFlag(String InfusionTypeFlag) {
        this.InfusionTypeFlag = InfusionTypeFlag;
    }
}
