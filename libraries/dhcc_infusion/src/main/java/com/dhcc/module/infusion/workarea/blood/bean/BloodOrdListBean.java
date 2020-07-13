package com.dhcc.module.infusion.workarea.blood.bean;

import android.text.TextUtils;

import java.util.List;

public class BloodOrdListBean {
    /**
     * ArcimDescList : [{"ArcimDesc":"血常规"}]
     * CreateDateTime :
     * CtcpDesc : 急诊医生01
     * DisposeStatDesc : 需处理临嘱
     * DoseQtyUnit :
     * EpisodeID : 139
     * LabColor :
     * LabNo : 1000003770
     * Notes :
     * OeoriId : 131||3||1
     * PhOrdQtyUnit : 总量1
     * PhcinDesc :
     * SpecDesc : 引流液
     */

    private String CreateDateTime;
    private String CtcpDesc;
    private String DisposeStatDesc;
    private String DoseQtyUnit;
    private String EpisodeID;
    private String LabColor;
    private String LabNo;
    private String Notes;
    private String OeoriId;
    private String PhOrdQtyUnit;
    private String PhcinDesc;
    private String SpecDesc;
    //配液/复核
    private String desUser;
    private String auditUser;
    private String desDateTime;
    private String auditDateTime;
    //采血核对
    private String AuditLabUser;
    private String AuditLabDateTime;
    private List<ArcimDescListBean> ArcimDescList;
    private String select;
    private String execCtcpDesc;
    private String execDateTime;
    private String sttDateTime;

    public String getSttDateTime() {
        return sttDateTime;
    }

    public void setSttDateTime(String sttDateTime) {
        this.sttDateTime = sttDateTime;
    }

    public String getExecDateTime() {
        return execDateTime;
    }

    public void setExecDateTime(String execDateTime) {
        this.execDateTime = execDateTime;
    }

    public String getExecCtcpDesc() {
        return execCtcpDesc;
    }

    public void setExecCtcpDesc(String execCtcpDesc) {
        this.execCtcpDesc = execCtcpDesc;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getDesDateTime() {
        return desDateTime == null ? "" : desDateTime;
    }

    public void setDesDateTime(String desDateTime) {
        this.desDateTime = desDateTime;
    }

    public String getAuditDateTime() {
        return auditDateTime == null ? "" : auditDateTime;
    }

    public void setAuditDateTime(String auditDateTime) {
        this.auditDateTime = auditDateTime;
    }

    public String getAuditLabDateTime() {
        return AuditLabDateTime == null ? "" : AuditLabDateTime;
    }

    public void setAuditLabDateTime(String auditLabDateTime) {
        AuditLabDateTime = auditLabDateTime;
    }

    public String getAuditLabUser() {
        return AuditLabUser == null ? "" : AuditLabUser;
    }

    public void setAuditLabUser(String auditLabUser) {
        AuditLabUser = auditLabUser;
    }

    public String getDesUser() {
        return desUser == null ? "" : desUser;
    }

    public void setDesUser(String desUser) {
        this.desUser = desUser;
    }

    public String getAuditUser() {
        return auditUser == null ? "" : auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getCreateDateTime() {
        return CreateDateTime;
    }

    public void setCreateDateTime(String CreateDateTime) {
        this.CreateDateTime = CreateDateTime;
    }

    public String getCtcpDesc() {
        return CtcpDesc;
    }

    public void setCtcpDesc(String CtcpDesc) {
        this.CtcpDesc = CtcpDesc;
    }

    public String getDisposeStatDesc() {
        return DisposeStatDesc == null ? "" : DisposeStatDesc;
    }

    public void setDisposeStatDesc(String DisposeStatDesc) {
        this.DisposeStatDesc = DisposeStatDesc;
    }

    public String getDoseQtyUnit() {
        return DoseQtyUnit;
    }

    public void setDoseQtyUnit(String DoseQtyUnit) {
        this.DoseQtyUnit = DoseQtyUnit;
    }

    public String getEpisodeID() {
        return EpisodeID;
    }

    public void setEpisodeID(String EpisodeID) {
        this.EpisodeID = EpisodeID;
    }

    public String getLabColor() {
        return LabColor;
    }

    public void setLabColor(String LabColor) {
        this.LabColor = LabColor;
    }

    public String getLabNo() {
        return LabNo;
    }

    public void setLabNo(String LabNo) {
        this.LabNo = LabNo;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public String getOeoriId() {
        return OeoriId;
    }
    public String getOrderId() {
        if(!TextUtils.isEmpty(LabNo)){
            return LabNo;
        }
        return OeoriId;
    }

    public void setOeoriId(String OeoriId) {
        this.OeoriId = OeoriId;
    }

    public String getPhOrdQtyUnit() {
        return PhOrdQtyUnit;
    }

    public void setPhOrdQtyUnit(String PhOrdQtyUnit) {
        this.PhOrdQtyUnit = PhOrdQtyUnit;
    }

    public String getPhcinDesc() {
        return PhcinDesc;
    }

    public void setPhcinDesc(String PhcinDesc) {
        this.PhcinDesc = PhcinDesc;
    }

    public String getSpecDesc() {
        return SpecDesc;
    }

    public void setSpecDesc(String SpecDesc) {
        this.SpecDesc = SpecDesc;
    }

    public List<ArcimDescListBean> getArcimDescList() {
        return ArcimDescList;
    }

    public void setArcimDescList(List<ArcimDescListBean> ArcimDescList) {
        this.ArcimDescList = ArcimDescList;
    }

}