package com.dhcc.module.infusion.workarea.dosing.bean;

import java.util.List;

public class OrdListBean {
    /**
     * Notes :
     * OeoreId : 568||3||1
     * OeoreSubList : [{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}]
     * OrdState : 已复核
     */

    private String Notes;
    private String OeoreId;
    private String OrdState;
    private String wayNo;
    private List<OeoreGroupBean> OeoreSubList;

    public String getWayNo() {
        return wayNo == null ? "" : wayNo;
    }

    public void setWayNo(String wayNo) {
        this.wayNo = wayNo;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public String getOeoreId() {
        return OeoreId;
    }

    public void setOeoreId(String OeoreId) {
        this.OeoreId = OeoreId;
    }

    public String getOrdState() {
        return OrdState;
    }

    public void setOrdState(String OrdState) {
        this.OrdState = OrdState;
    }

    public List<OeoreGroupBean> getOeoreSubList() {
        return OeoreSubList;
    }

    public void setOeoreSubList(List<OeoreGroupBean> OeoreSubList) {
        this.OeoreSubList = OeoreSubList;
    }


}