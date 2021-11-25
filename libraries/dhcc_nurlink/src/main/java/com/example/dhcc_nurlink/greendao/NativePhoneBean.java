package com.example.dhcc_nurlink.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;

/**
 * com.example.dhcc_nurlink.greendao
 * <p>
 * author Q
 * Date: 2021/1/20
 * Time:14:35
 */
@Entity
public class NativePhoneBean  {
    @Id(autoincrement = true)
    private Long id;
    private String CTLOCDesc;
    private String DeviceId;
    private String FirstFlag;
    private String FirstPin;
    private String HCCSCLRowId;
    private String NickName;
    private String PinName;
    private String UserCode;
    private String UserId;
    private String UserName;
    private String UserType;
    private String VOIPId;
    private String strGroupList;
    @Generated(hash = 1143973624)
    public NativePhoneBean(Long id, String CTLOCDesc, String DeviceId,
            String FirstFlag, String FirstPin, String HCCSCLRowId, String NickName,
            String PinName, String UserCode, String UserId, String UserName,
            String UserType, String VOIPId, String strGroupList) {
        this.id = id;
        this.CTLOCDesc = CTLOCDesc;
        this.DeviceId = DeviceId;
        this.FirstFlag = FirstFlag;
        this.FirstPin = FirstPin;
        this.HCCSCLRowId = HCCSCLRowId;
        this.NickName = NickName;
        this.PinName = PinName;
        this.UserCode = UserCode;
        this.UserId = UserId;
        this.UserName = UserName;
        this.UserType = UserType;
        this.VOIPId = VOIPId;
        this.strGroupList = strGroupList;
    }
    @Generated(hash = 548296124)
    public NativePhoneBean() {
    }
    public String getCTLOCDesc() {
        return this.CTLOCDesc;
    }
    public void setCTLOCDesc(String CTLOCDesc) {
        this.CTLOCDesc = CTLOCDesc;
    }
    public String getDeviceId() {
        return this.DeviceId;
    }
    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }
    public String getFirstFlag() {
        return this.FirstFlag;
    }
    public void setFirstFlag(String FirstFlag) {
        this.FirstFlag = FirstFlag;
    }
    public String getFirstPin() {
        return this.FirstPin;
    }
    public void setFirstPin(String FirstPin) {
        this.FirstPin = FirstPin;
    }
    public String getHCCSCLRowId() {
        return this.HCCSCLRowId;
    }
    public void setHCCSCLRowId(String HCCSCLRowId) {
        this.HCCSCLRowId = HCCSCLRowId;
    }
    public String getNickName() {
        return this.NickName;
    }
    public void setNickName(String NickName) {
        this.NickName = NickName;
    }
    public String getPinName() {
        return this.PinName;
    }
    public void setPinName(String PinName) {
        this.PinName = PinName;
    }
    public String getUserCode() {
        return this.UserCode;
    }
    public void setUserCode(String UserCode) {
        this.UserCode = UserCode;
    }
    public String getUserId() {
        return this.UserId;
    }
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
    public String getUserName() {
        return this.UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserType() {
        return this.UserType;
    }
    public void setUserType(String UserType) {
        this.UserType = UserType;
    }
    public String getVOIPId() {
        return this.VOIPId;
    }
    public void setVOIPId(String VOIPId) {
        this.VOIPId = VOIPId;
    }
    public String getStrGroupList() {
        return this.strGroupList;
    }
    public void setStrGroupList(String strGroupList) {
        this.strGroupList = strGroupList;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}