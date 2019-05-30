package com.dhcc.module.infusion.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Nurse
 *
 * @author DevLix126
 * @date 2018/8/14
 */
@Entity
public class InfusionInfo {
    @Id(autoincrement = true)
    private Long id;
    private String schEnDateTime;
    private String schStDateTime;
    private String status;
    private String userId;
    private String userCode;
    private String userName;
    private String groupDesc;
    private String groupId;
    private String hospitalRowId;
    private String linkLoc;
    private String locDesc;
    private String locId;
    private String wardId;
    @Generated(hash = 1915596477)
    public InfusionInfo(Long id, String schEnDateTime, String schStDateTime, String status,
            String userId, String userCode, String userName, String groupDesc, String groupId,
            String hospitalRowId, String linkLoc, String locDesc, String locId, String wardId) {
        this.id = id;
        this.schEnDateTime = schEnDateTime;
        this.schStDateTime = schStDateTime;
        this.status = status;
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.groupDesc = groupDesc;
        this.groupId = groupId;
        this.hospitalRowId = hospitalRowId;
        this.linkLoc = linkLoc;
        this.locDesc = locDesc;
        this.locId = locId;
        this.wardId = wardId;
    }

    @Generated(hash = 1235967646)
    public InfusionInfo() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSchEnDateTime() {
        return this.schEnDateTime;
    }
    public void setSchEnDateTime(String schEnDateTime) {
        this.schEnDateTime = schEnDateTime;
    }
    public String getSchStDateTime() {
        return this.schStDateTime;
    }
    public void setSchStDateTime(String schStDateTime) {
        this.schStDateTime = schStDateTime;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserCode() {
        return this.userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getGroupDesc() {
        return this.groupDesc;
    }
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
    public String getGroupId() {
        return this.groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getHospitalRowId() {
        return this.hospitalRowId;
    }
    public void setHospitalRowId(String hospitalRowId) {
        this.hospitalRowId = hospitalRowId;
    }
    public String getLinkLoc() {
        return this.linkLoc;
    }
    public void setLinkLoc(String linkLoc) {
        this.linkLoc = linkLoc;
    }
    public String getLocDesc() {
        return this.locDesc;
    }
    public void setLocDesc(String locDesc) {
        this.locDesc = locDesc;
    }
    public String getLocId() {
        return this.locId;
    }
    public void setLocId(String locId) {
        this.locId = locId;
    }
    public String getWardId() {
        return this.wardId;
    }
    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

}
