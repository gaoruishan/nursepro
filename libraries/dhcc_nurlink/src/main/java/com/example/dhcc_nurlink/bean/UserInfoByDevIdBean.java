package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

/**
 * com.example.dhcc_nurlink.bean
 * <p>
 * author Q
 * Date: 2020/12/10
 * Time:10:24
 */
public class UserInfoByDevIdBean extends CommResult {
    /**
     * NickName : 测试用户3
     * UserCode : HS01
     * UserId : 12177
     * UserLoc : 113
     * UserName : 003
     * VOIPId : 111113
     */

    private String NickName;
    private String UserCode;
    private String UserId;
    private String UserLoc;
    private String UserName;
    private String VOIPId;
    private String UserLocDesc="内分泌科";
    private String UserType="NURSE";
    private String VoiceTextShow="Y";

    public String getVoiceTextShow() {
        return VoiceTextShow;
    }

    public void setVoiceTextShow(String voiceTextShow) {
        VoiceTextShow = voiceTextShow;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getUserLocDesc() {
        return UserLocDesc;
    }

    public void setUserLocDesc(String userLocDesc) {
        UserLocDesc = userLocDesc;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String UserCode) {
        this.UserCode = UserCode;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getUserLoc() {
        return UserLoc;
    }

    public void setUserLoc(String UserLoc) {
        this.UserLoc = UserLoc;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getVOIPId() {
        return VOIPId;
    }

    public void setVOIPId(String VOIPId) {
        this.VOIPId = VOIPId;
    }
}
