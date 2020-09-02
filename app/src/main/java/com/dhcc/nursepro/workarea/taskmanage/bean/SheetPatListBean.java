package com.dhcc.nursepro.workarea.taskmanage.bean;

public class SheetPatListBean {
    /**
     * bedCode : 20
     * patName : 住院N3
     * patRegNo : 0000000212
     * sheetCode : CQSYD
     * sheetDesc : 输液单
     * sheetPatOrdNum : 3
     */

    private String bedCode;
    private String patName;
    private String patRegNo;
    private String sheetCode;
    private String sheetDesc;
    private String sheetPatOrdNum;
    private String patSex;

    public String getPatSex() {
        return patSex == null ? "" : patSex;
    }

    public void setPatSex(String patSex) {
        this.patSex = patSex;
    }

    public String getBedCode() {
        return bedCode == null ? "" : bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getPatName() {
        return patName == null ? "" : patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatRegNo() {
        return patRegNo == null ? "" : patRegNo;
    }

    public void setPatRegNo(String patRegNo) {
        this.patRegNo = patRegNo;
    }

    public String getSheetCode() {
        return sheetCode == null ? "" : sheetCode;
    }

    public void setSheetCode(String sheetCode) {
        this.sheetCode = sheetCode;
    }

    public String getSheetDesc() {
        return sheetDesc == null ? "" : sheetDesc;
    }

    public void setSheetDesc(String sheetDesc) {
        this.sheetDesc = sheetDesc;
    }

    public String getSheetPatOrdNum() {
        return sheetPatOrdNum == null ? "" : sheetPatOrdNum;
    }

    public void setSheetPatOrdNum(String sheetPatOrdNum) {
        this.sheetPatOrdNum = sheetPatOrdNum;
    }
}