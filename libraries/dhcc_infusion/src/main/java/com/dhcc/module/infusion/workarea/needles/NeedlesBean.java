package com.dhcc.module.infusion.workarea.needles;

import com.dhcc.module.infusion.workarea.comm.bean.CommInfusionBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-29/13:44
 * @email:grs0515@163.com
 */
public class NeedlesBean extends CommInfusionBean {

    /**
     * CurOeoreId : 568||3||1
     * CurRegNo : 0000000435
     * PatInfo : {"PatName":"lh041101","PatRegNo":"0000000435","PatSex":"女"}
     * lastIfFlag : 0
     * msg : 成功
     * msgcode :
     * ordList : [{"Notes":"","OeoreId":"568||3||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"待续液"},{"Notes":"","OeoreId":"568||3||3","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"待续液"},{"Notes":"","OeoreId":"568||3||5","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"未配液"}]
     * status : 0
     */

    //最后一袋液体标记 1是 0不是
    private String lastIfFlag;

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

    public PatInfoBean getPatInfo() {
        return PatInfo;
    }

    public void setPatInfo(PatInfoBean PatInfo) {
        this.PatInfo = PatInfo;
    }

    public String getLastIfFlag() {
        return lastIfFlag;
    }

    public void setLastIfFlag(String lastIfFlag) {
        this.lastIfFlag = lastIfFlag;
    }


    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

}
