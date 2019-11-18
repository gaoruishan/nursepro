package com.dhcc.module.infusion.workarea.blood.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-11-13/15:52
 * @email:grs0515@163.com
 */
public class BloodCollectBean extends CommResult {

    /**
     * ordList : [{"ArcimDescList":[{"ArcimDesc":"血常规"}],"CreateDateTime":"","CtcpDesc":"急诊医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"","EpisodeID":"139","LabColor":"","LabNo":"1000003770","Notes":" ","OeoriId":"131||3||1","PhOrdQtyUnit":"总量1 ","PhcinDesc":"","SpecDesc":"引流液"}]
     * patInfo : {"PatAge":"31岁","PatName":"宇宇宇001","PatRegNo":"0000000097","PatSex":"未知性别"}
     */

    private PatInfoBean patInfo;
    private List<BloodOrdListBean> ordList;

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public List<BloodOrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<BloodOrdListBean> ordList) {
        this.ordList = ordList;
    }



}
