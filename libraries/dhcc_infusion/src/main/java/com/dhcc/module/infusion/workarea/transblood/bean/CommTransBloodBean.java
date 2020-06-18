package com.dhcc.module.infusion.workarea.transblood.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;

import java.util.List;

/**
 * 输血公共类
 * @author:gaoruishan
 * @date:202020-01-08/11:15
 * @email:grs0515@163.com
 */
public class CommTransBloodBean extends CommResult {
    //病人信息
    private PatInfoBean patInfo;
    private List<BloodInfoBean> bloodList;

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


    public List<BloodInfoBean> getBloodList() {
        return bloodList;
    }

    public void setBloodList(List<BloodInfoBean> bloodList) {
        this.bloodList = bloodList;
    }

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        patInfo = patInfo;
    }


}
