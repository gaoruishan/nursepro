package com.dhcc.module.infusion.workarea.blood.bean;

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
    private List<ArcimDescListBean> ArcimDescList;

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

    public static class ArcimDescListBean {
        /**
         * ArcimDesc : 血常规
         */

        private String ArcimDesc;
        private String DoseQtyUnit;
        private String PhOrdQtyUnit;

        public String getDoseQtyUnit() {
            return DoseQtyUnit == null ? "" : DoseQtyUnit;
        }

        public void setDoseQtyUnit(String doseQtyUnit) {
            DoseQtyUnit = doseQtyUnit;
        }

        public String getPhOrdQtyUnit() {
            return PhOrdQtyUnit == null ? "" : PhOrdQtyUnit;
        }

        public void setPhOrdQtyUnit(String phOrdQtyUnit) {
            PhOrdQtyUnit = phOrdQtyUnit;
        }

        public String getArcimDesc() {
            return ArcimDesc;
        }

        public void setArcimDesc(String ArcimDesc) {
            this.ArcimDesc = ArcimDesc;
        }
    }
}