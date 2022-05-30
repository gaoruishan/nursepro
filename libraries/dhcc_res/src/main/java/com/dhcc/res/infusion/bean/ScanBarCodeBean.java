package com.dhcc.res.infusion.bean;


import com.base.commlibs.http.CommResult;

/**
 * @author:gaoruishan
 * @date:202020-03-24/11:21
 * @email:grs0515@163.com
 */
public class ScanBarCodeBean extends CommResult {

    /**
     * bloodProductId : =<P0065V00
     * bloodbagId : 0220319178769
     * regNo : 0000000001
     */

    private String bloodProductId;
    private String bloodbagId;
    private String regNo;
    private String barCodeFlag;

    public String getBarCodeFlag() {
        return barCodeFlag == null ? "" : barCodeFlag;
    }

    public void setBarCodeFlag(String barCodeFlag) {
        this.barCodeFlag = barCodeFlag;
    }

    public String getBloodProductId() {
        return bloodProductId;
    }

    public void setBloodProductId(String bloodProductId) {
        this.bloodProductId = bloodProductId;
    }

    public String getBloodbagId() {
        return bloodbagId;
    }

    public void setBloodbagId(String bloodbagId) {
        this.bloodbagId = bloodbagId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
