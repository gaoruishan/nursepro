package com.dhcc.module.infusion.workarea.dosing.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-24/14:52
 * @email:grs0515@163.com
 */
public class DosingBean extends CommResult {

    /**
     * msg : 成功
     * msgcode :
     * ordList : [{"Notes":"","OeoreId":"568||3||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"已复核"},{"Notes":"","OeoreId":"568||3||3","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"已配液"},{"Notes":"","OeoreId":"568||3||5","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"未配液"}]
     * ordState : 已配液
     * status : 0
     */

    private String ordState;
    private List<OrdListBean> ordList;


    public String getOrdState() {
        return ordState;
    }

    public void setOrdState(String ordState) {
        this.ordState = ordState;
    }


    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

}
