package com.dhcc.module.infusion.workarea.transblood.bean;

/**
 * @author:gaoruishan
 * @date:202020-04-09/10:38
 * @email:grs0515@163.com
 */
public class BloodConfig {
    //"签"配置
    private String reciveTag;
    //"输"配置
    private String transTag;
    //"完"配置
    private String transEndTag;
    //默认弹框 1弹框 0不弹框
    private String receiveDialogFlag;


    public String getReciveTag() {
        return reciveTag == null ? "" : reciveTag;
    }

    public void setReciveTag(String reciveTag) {
        this.reciveTag = reciveTag;
    }

    public String getTransTag() {
        return transTag == null ? "" : transTag;
    }

    public void setTransTag(String transTag) {
        this.transTag = transTag;
    }

    public String getTransEndTag() {
        return transEndTag == null ? "" : transEndTag;
    }

    public void setTransEndTag(String transEndTag) {
        this.transEndTag = transEndTag;
    }

    public String getReceiveDialogFlag() {
        return receiveDialogFlag == null ? "" : receiveDialogFlag;
    }

    public void setReceiveDialogFlag(String receiveDialogFlag) {
        this.receiveDialogFlag = receiveDialogFlag;
    }

}
