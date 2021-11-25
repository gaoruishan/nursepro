
package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

/**
 * com.example.nurlink
 * <p>
 * author Q
 * Date: 2021/1/20
 * Time:14:25
 */
public class SetDutyBean extends CommResult {
    private String dutyFlag;

    public String getDutyFlag() {
        return dutyFlag;
    }

    public void setDutyFlag(String dutyFlag) {
        this.dutyFlag = dutyFlag;
    }
}
