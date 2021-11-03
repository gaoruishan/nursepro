package com.dhcc.module.nurse.ca.bean;

import com.base.commlibs.http.CommResult;

/**
 * @author:gaoruishan
 * @date:202021/10/29/17:31
 * @email:grs0515@163.com
 */
public class CaResult  extends CommResult {
    //接口返回Code值：0成功、其余失败
    private String retCode;

    private String retMsg;

    @Override
    public String getMsg() {
        return retMsg;
    }

    @Override
    public String getStatus() {
        return retCode;
    }

    public String getRetCode() {
        return retCode == null ? "" : retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg == null ? "" : retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
