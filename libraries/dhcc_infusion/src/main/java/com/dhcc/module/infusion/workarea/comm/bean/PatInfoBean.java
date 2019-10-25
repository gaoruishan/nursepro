package com.dhcc.module.infusion.workarea.comm.bean;

import com.dhcc.module.infusion.R;

public class PatInfoBean {
    /**
     * PatName : 井尔
     * PatRegNo : 0000000164
     * PatSex : 男
     */

    private String PatName;
    private String PatRegNo;
    private String PatSex;
    private String PatAge;
    private String PatSeat;
    //诊断
    private String PatDiag;

    public String getPatSeat() {
        return PatSeat == null ? "" : PatSeat;
    }

    public void setPatSeat(String patSeat) {
        PatSeat = patSeat;
    }

    /**
     * PatName : lh041101
     * PatRegNo : 0000000435
     * PatSex : 女
     */
    public int getPatSexDrawable() {
        if ("男".equals(PatSex)) {
            return R.drawable.dhcc_sex_male;
        } else if ("女".equals(PatSex)) {
            return R.drawable.dhcc_sex_female;
        } else {
            return R.drawable.dhcc_sex_male;
        }
    }

    public String getPatAge() {
        return PatAge == null ? "" : PatAge;
    }

    public void setPatAge(String patAge) {
        PatAge = patAge;
    }

    public String getPatDiag() {
        return PatDiag == null ? "" : PatDiag;
    }

    public void setPatDiag(String patDiag) {
        PatDiag = patDiag;
    }

    public String getPatName() {
        return PatName;
    }

    public void setPatName(String PatName) {
        this.PatName = PatName;
    }

    public String getPatRegNo() {
        return PatRegNo;
    }

    public void setPatRegNo(String PatRegNo) {
        this.PatRegNo = PatRegNo;
    }

    public String getPatSex() {
        return PatSex;
    }

    public void setPatSex(String PatSex) {
        this.PatSex = PatSex;
    }
}
