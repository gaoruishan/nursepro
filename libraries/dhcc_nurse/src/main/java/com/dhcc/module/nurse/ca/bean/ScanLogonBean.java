package com.dhcc.module.nurse.ca.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/11/2/11:43
 * @email:grs0515@163.com
 */
public class ScanLogonBean extends CommResult {

    /**
     * ErrorInfo :
     * Locs : [{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"2","linkLoc":"113","locDesc":"内分泌科护理单元 住院护士","locId":"114","wardId":"21"},{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"2","linkLoc":"113","locDesc":"内分泌科护理单元  Inpatient Nurse","locId":"114","wardId":"21"},{"groupDesc":"治疗科室护士","groupId":"172","hospitalRowId":"2","linkLoc":"113","locDesc":"内分泌科护理单元  治疗科室护士","locId":"114","wardId":"21"},{"groupDesc":"住院护士长","groupId":"25","hospitalRowId":"2","linkLoc":"113","locDesc":"内分泌科护理单元  住院护士长","locId":"114","wardId":"21"},{"groupDesc":"治疗师","groupId":"275","hospitalRowId":"2","linkLoc":"113","locDesc":"内分泌科护理单元  治疗师","locId":"114","wardId":"21"},{"groupDesc":"预住院中心护士","groupId":"276","hospitalRowId":"2","linkLoc":"113","locDesc":"内分泌科护理单元  预住院中心护士","locId":"114","wardId":"21"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"2","linkLoc":"123","locDesc":"日间手术病房  住院护士","locId":"123","wardId":"23"},{"groupDesc":"预住院中心护士","groupId":"276","hospitalRowId":"2","linkLoc":"123","locDesc":"日间手术病房  预住院中心护士","locId":"123","wardId":"23"},{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"2","linkLoc":"124","locDesc":"乳甲外科护理单元  Inpatient Nurse","locId":"125","wardId":"24"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"2","linkLoc":"138","locDesc":"肾内科护理单元  住院护士","locId":"139","wardId":"28"},{"groupDesc":"产房护士","groupId":"283","hospitalRowId":"2","linkLoc":"15","locDesc":"产房  产房护士","locId":"14","wardId":"2"},{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"2","linkLoc":"168","locDesc":"消化内科护理单元  Inpatient Nurse","locId":"169","wardId":"32"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"2","linkLoc":"168","locDesc":"消化内科护理单元  住院护士","locId":"169","wardId":"32"},{"groupDesc":"住院护士长","groupId":"25","hospitalRowId":"2","linkLoc":"168","locDesc":"消化内科护理单元  住院护士长","locId":"169","wardId":"32"},{"groupDesc":"产科护士长","groupId":"112","hospitalRowId":"2","linkLoc":"15","locDesc":"产科二护理单元  产科护士长","locId":"16","wardId":"3"},{"groupDesc":"Obstetrical Nurse","groupId":"133","hospitalRowId":"2","linkLoc":"15","locDesc":"产科二护理单元  Obstetrical Nurse","locId":"16","wardId":"3"},{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"2","linkLoc":"176","locDesc":"心内科护理单元  Inpatient Nurse","locId":"178","wardId":"35"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"2","linkLoc":"176","locDesc":"心内科护理单元  住院护士","locId":"178","wardId":"35"},{"groupDesc":"产科护士","groupId":"111","hospitalRowId":"2","linkLoc":"15","locDesc":"产科护理单元  产科护士","locId":"17","wardId":"4"},{"groupDesc":"产科护士长","groupId":"112","hospitalRowId":"2","linkLoc":"15","locDesc":"产科护理单元  产科护士长","locId":"17","wardId":"4"},{"groupDesc":"Pre Inpatient Nurse","groupId":"344","hospitalRowId":"2","linkLoc":"191","locDesc":"血液内科护理单元  Pre Inpatient Nurse","locId":"192","wardId":"38"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"2","linkLoc":"195","locDesc":"眼科护理单元  住院护士","locId":"197","wardId":"39"},{"groupDesc":"预住院中心护士","groupId":"276","hospitalRowId":"0","linkLoc":"0","locDesc":"预住院中心  预住院中心护士","locId":"205","wardId":"40"},{"groupDesc":"Pre Inpatient Nurse","groupId":"344","hospitalRowId":"0","linkLoc":"0","locDesc":"预住院中心  Pre Inpatient Nurse","locId":"205","wardId":"40"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"2","linkLoc":"214","locDesc":"中医科护理单元  住院护士","locId":"215","wardId":"41"},{"groupDesc":"ICU护士长","groupId":"114","hospitalRowId":"2","linkLoc":"220","locDesc":"重症医学科护理单元  ICU护士长","locId":"221","wardId":"43"},{"groupDesc":"ICU护士","groupId":"24","hospitalRowId":"2","linkLoc":"220","locDesc":"重症医学科护理单元  ICU护士","locId":"221","wardId":"43"},{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"10","linkLoc":"253","locDesc":"内分泌科护理单元[东院]  Inpatient Nurse","locId":"254","wardId":"49"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"10","linkLoc":"253","locDesc":"内分泌科护理单元[东院]  住院护士","locId":"254","wardId":"49"},{"groupDesc":"预住院中心护士","groupId":"276","hospitalRowId":"0","linkLoc":"0","locDesc":"预住院中心[东院]  预住院中心护士","locId":"271","wardId":"52"},{"groupDesc":"Pre Inpatient Nurse","groupId":"344","hospitalRowId":"0","linkLoc":"0","locDesc":"预住院中心[东院]  Pre Inpatient Nurse","locId":"271","wardId":"52"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"10","linkLoc":"278","locDesc":"呼吸内科护理单元[东院]  住院护士","locId":"279","wardId":"53"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"3","linkLoc":"322","locDesc":"呼吸内科护理单元[口腔]  住院护士","locId":"323","wardId":"62"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"3","linkLoc":"364","locDesc":"内分泌科护理单元[口腔]  住院护士","locId":"365","wardId":"70"},{"groupDesc":"住院护士长","groupId":"25","hospitalRowId":"3","linkLoc":"364","locDesc":"内分泌科护理单元[口腔]  住院护士长","locId":"365","wardId":"70"},{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"3","linkLoc":"403","locDesc":"心内科护理单元[口腔]  Inpatient Nurse","locId":"404","wardId":"77"},{"groupDesc":"预住院中心护士","groupId":"276","hospitalRowId":"0","linkLoc":"0","locDesc":"预住院中心[口腔]  预住院中心护士","locId":"436","wardId":"83"},{"groupDesc":"Pre Inpatient Nurse","groupId":"344","hospitalRowId":"0","linkLoc":"0","locDesc":"预住院中心[口腔]  Pre Inpatient Nurse","locId":"436","wardId":"83"},{"groupDesc":"产科护士","groupId":"111","hospitalRowId":"9","linkLoc":"443","locDesc":"产科护理单元[西院]  产科护士","locId":"444","wardId":"85"},{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"9","linkLoc":"462","locDesc":"内分泌科护理单元[西院]  Inpatient Nurse","locId":"463","wardId":"89"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"9","linkLoc":"462","locDesc":"内分泌科护理单元[西院]  住院护士","locId":"463","wardId":"89"},{"groupDesc":"住院护士长","groupId":"25","hospitalRowId":"9","linkLoc":"462","locDesc":"内分泌科护理单元[西院]  住院护士长","locId":"463","wardId":"89"},{"groupDesc":"预住院中心护士","groupId":"276","hospitalRowId":"0","linkLoc":"0","locDesc":"预住院中心[西院]  预住院中心护士","locId":"480","wardId":"92"},{"groupDesc":"Pre Inpatient Nurse","groupId":"344","hospitalRowId":"0","linkLoc":"0","locDesc":"预住院中心[西院]  Pre Inpatient Nurse","locId":"480","wardId":"92"},{"groupDesc":"住院护士长","groupId":"25","hospitalRowId":"9","linkLoc":"487","locDesc":"呼吸内科护理单元[西院]  住院护士长","locId":"488","wardId":"93"},{"groupDesc":"ICU护士","groupId":"24","hospitalRowId":"9","linkLoc":"489","locDesc":"重症医学科护理单元[西院]  ICU护士","locId":"490","wardId":"94"},{"groupDesc":"Inpatient Nurse","groupId":"132","hospitalRowId":"2","linkLoc":"53","locDesc":"呼吸内科护理单元  Inpatient Nurse","locId":"54","wardId":"10"},{"groupDesc":"住院护士","groupId":"23","hospitalRowId":"2","linkLoc":"53","locDesc":"呼吸内科护理单元  住院护士","locId":"54","wardId":"10"},{"groupDesc":"住院护士长","groupId":"25","hospitalRowId":"2","linkLoc":"53","locDesc":"呼吸内科护理单元  住院护士长","locId":"54","wardId":"10"},{"groupDesc":"预住院中心护士","groupId":"276","hospitalRowId":"2","linkLoc":"53","locDesc":"呼吸内科护理单元  预住院中心护士","locId":"54","wardId":"10"}]
     * UserID : 12177
     * UserName : 护士01
     * curDateTime : 2021-11-02 14:38:14
     * schEnDateTime : 2021-11-02 23:59:59
     * schStDateTime : 2021-11-02 00:00:00
     * userCode : 0
     */

    private String ErrorInfo;
    private String UserID;
    private String UserName;
    private String curDateTime;
    private String schEnDateTime;
    private String schStDateTime;
    private String userCode;
    /**
     * groupDesc : 住院护士
     * groupId : 23
     * hospitalRowId : 2
     * linkLoc : 113
     * locDesc : 内分泌科护理单元 住院护士
     * locId : 114
     * wardId : 21
     */

    private List<LocsBean> Locs;

    public String getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(String ErrorInfo) {
        this.ErrorInfo = ErrorInfo;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getCurDateTime() {
        return curDateTime;
    }

    public void setCurDateTime(String curDateTime) {
        this.curDateTime = curDateTime;
    }

    public String getSchEnDateTime() {
        return schEnDateTime;
    }

    public void setSchEnDateTime(String schEnDateTime) {
        this.schEnDateTime = schEnDateTime;
    }

    public String getSchStDateTime() {
        return schStDateTime;
    }

    public void setSchStDateTime(String schStDateTime) {
        this.schStDateTime = schStDateTime;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<LocsBean> getLocs() {
        return Locs;
    }

    public void setLocs(List<LocsBean> Locs) {
        this.Locs = Locs;
    }

    public static class LocsBean {
        private String groupDesc;
        private String groupId;
        private String hospitalRowId;
        private String linkLoc;
        private String locDesc;
        private String locId;
        private String wardId;

        public String getGroupDesc() {
            return groupDesc;
        }

        public void setGroupDesc(String groupDesc) {
            this.groupDesc = groupDesc;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getHospitalRowId() {
            return hospitalRowId;
        }

        public void setHospitalRowId(String hospitalRowId) {
            this.hospitalRowId = hospitalRowId;
        }

        public String getLinkLoc() {
            return linkLoc;
        }

        public void setLinkLoc(String linkLoc) {
            this.linkLoc = linkLoc;
        }

        public String getLocDesc() {
            return locDesc;
        }

        public void setLocDesc(String locDesc) {
            this.locDesc = locDesc;
        }

        public String getLocId() {
            return locId;
        }

        public void setLocId(String locId) {
            this.locId = locId;
        }

        public String getWardId() {
            return wardId;
        }

        public void setWardId(String wardId) {
            this.wardId = wardId;
        }
    }
}
