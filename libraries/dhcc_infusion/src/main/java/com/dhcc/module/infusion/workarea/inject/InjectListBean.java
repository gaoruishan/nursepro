package com.dhcc.module.infusion.workarea.inject;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-11-14/14:42
 * @email:grs0515@163.com
 */
public class InjectListBean extends CommResult {

    /**
     * ordList : [{"ArcimDesc":[{"ArcimDesc":"注射用奥美拉唑钠[60mg]","DoseQtyUnit":"60 mg","PhOrdQtyUnit":"1 支"},{"ArcimDesc":"一次性注射器[10ml]","DoseQtyUnit":" mg","PhOrdQtyUnit":"1 个"}],"CreateDateTime":"","CtcpDesc":"医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":" mg","EpisodeID":"165","Notes":" ","OeoriId":"144||20||1","PhOrdQtyUnit":"总量1 个","PhcinDesc":"静脉注射"},{"ArcimDesc":[{"ArcimDesc":"注射用丹参[400MG]","DoseQtyUnit":"400 mg","PhOrdQtyUnit":"1 支"}],"CreateDateTime":"","CtcpDesc":"医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"400 mg","EpisodeID":"165","Notes":" ","OeoriId":"144||22||1","PhOrdQtyUnit":"总量1 支","PhcinDesc":"静脉注射"},{"ArcimDesc":[{"ArcimDesc":"注射用氨甲环酸(力达非)[0.25G]","DoseQtyUnit":"0.25 g","PhOrdQtyUnit":"1 支"}],"CreateDateTime":"","CtcpDesc":"医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"0.25 g","EpisodeID":"165","Notes":" ","OeoriId":"144||23||1","PhOrdQtyUnit":"总量1 支","PhcinDesc":"静脉注射"},{"ArcimDesc":[{"ArcimDesc":"注射用哌库溴铵(阿端)[4MG:一针一水]","DoseQtyUnit":"4 mg","PhOrdQtyUnit":"1 支"},{"ArcimDesc":"注射用氨甲环酸(荷莫塞)[1G]","DoseQtyUnit":"1 g","PhOrdQtyUnit":"1 支"}],"CreateDateTime":"","CtcpDesc":"医生01","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"1 g","EpisodeID":"165","Notes":" ","OeoriId":"144||24||1","PhOrdQtyUnit":"总量1 支","PhcinDesc":"静脉注射"}]
     * patInfo : {"PatAge":"31岁","PatName":"宇宇宇001","PatRegNo":"0000000097","PatSex":"未知性别"}
     */

    private PatInfoBean patInfo;
    private String curOeoreId;
    //按钮类型/描述
    private String btnType;
    private String btnDesc;
    //是否双验证(腕带 瓶贴 )  1:是
    private String injectFlag;
    private List<BloodOrdListBean> ordList;

    public String getInjectFlag() {
        return injectFlag == null ? "" : injectFlag;
    }

    public void setInjectFlag(String injectFlag) {
        this.injectFlag = injectFlag;
    }

    public String getBtnType() {
        return btnType == null ? "" : btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;
    }

    public String getBtnDesc() {
        return btnDesc == null ? "" : btnDesc;
    }

    public void setBtnDesc(String btnDesc) {
        this.btnDesc = btnDesc;
    }

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public String getCurOeoreId() {
        return curOeoreId == null ? "" : curOeoreId;
    }

    public void setCurOeoreId(String curOeoreId) {
        this.curOeoreId = curOeoreId;
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
