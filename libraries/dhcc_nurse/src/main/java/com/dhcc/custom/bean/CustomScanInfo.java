package com.dhcc.custom.bean;

import com.base.commlibs.http.CommResult;

/**
 * @author:gaoruishan
 * @date:202022/1/28/16:29
 * @email:grs0515@163.com
 */
public class CustomScanInfo extends CommResult {

    private String canExeFlag;
    // 1 扫码执行
    private String scanFlag;
    private String diagFlag;
    private String flag;
    private String barCodeType;

    public String getBarCodeType() {
        return barCodeType == null ? "" : barCodeType;
    }

    public void setBarCodeType(String barCodeType) {
        this.barCodeType = barCodeType;
    }

    public String getCanExeFlag() {
        return canExeFlag == null ? "" : canExeFlag;
    }

    public void setCanExeFlag(String canExeFlag) {
        this.canExeFlag = canExeFlag;
    }

    public String getScanFlag() {
        return scanFlag == null ? "" : scanFlag;
    }

    public void setScanFlag(String scanFlag) {
        this.scanFlag = scanFlag;
    }

    public String getDiagFlag() {
        return diagFlag == null ? "" : diagFlag;
    }

    public void setDiagFlag(String diagFlag) {
        this.diagFlag = diagFlag;
    }

    public String getFlag() {
        return flag == null ? "" : flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
