package com.dhcc.module.infusion.workarea.continues;

import com.dhcc.module.infusion.workarea.comm.bean.CommInfusionBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-29/10:25
 * @email:grs0515@163.com
 */
public class ContinueBean extends CommInfusionBean {

    /**
     * CurOeoreId : 568||3||1
     * CurRegNo :
     * DefautSpeed : 45
     * DistantDate : 2019-04-29
     * DistantTime : 11:24:58
     * PatInfo : {"PatName":"lh041101","PatRegNo":"0000000435","PatSex":"女"}
     * msg : 成功
     * msgcode :
     * ordList : [{"Notes":"","OeoreId":"568||3||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"待续液"},{"Notes":"","OeoreId":"568||3||3","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"待续液"},{"Notes":"","OeoreId":"568||3||5","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"未配液"}]
     * status : 0
     */

    private String DefautSpeed;
    private String DistantDate;
    private String DistantTime;
    private PatInfoBean PatInfo;

    public String getCurOeoreId() {
        return CurOeoreId;
    }

    public void setCurOeoreId(String CurOeoreId) {
        this.CurOeoreId = CurOeoreId;
    }

    public String getCurRegNo() {
        return CurRegNo;
    }

    public void setCurRegNo(String CurRegNo) {
        this.CurRegNo = CurRegNo;
    }

    public String getDefautSpeed() {
        return DefautSpeed;
    }

    public void setDefautSpeed(String DefautSpeed) {
        this.DefautSpeed = DefautSpeed;
    }

    public String getDistantDate() {
        return DistantDate;
    }

    public void setDistantDate(String DistantDate) {
        this.DistantDate = DistantDate;
    }

    public String getDistantTime() {
        return DistantTime;
    }

    public void setDistantTime(String DistantTime) {
        this.DistantTime = DistantTime;
    }

    public PatInfoBean getPatInfo() {
        return PatInfo;
    }

    public void setPatInfo(PatInfoBean PatInfo) {
        this.PatInfo = PatInfo;
    }

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

}
