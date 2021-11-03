package com.dhcc.module.nurse.ca.bean;

/**
 * @author:gaoruishan
 * @date:202021/11/1/09:37
 * @email:grs0515@163.com
 */
public class Login2Bean extends CaResult{

    /**
     * hisUserName : HIS系统用户姓名
     * hisUserID : HIS系统SS_user表Rowid
     */

    private String hisUserName;
    private String hisUserID;

    public String getHisUserName() {
        return hisUserName;
    }

    public void setHisUserName(String hisUserName) {
        this.hisUserName = hisUserName;
    }

    public String getHisUserID() {
        return hisUserID;
    }

    public void setHisUserID(String hisUserID) {
        this.hisUserID = hisUserID;
    }
}
