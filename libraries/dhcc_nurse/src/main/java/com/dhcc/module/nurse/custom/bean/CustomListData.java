package com.dhcc.module.nurse.custom.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;
import java.util.Map;

/**
 * @author:gaoruishan
 * @date:202022/2/8/16:15
 * @email:grs0515@163.com
 */
public class CustomListData extends CommResult {

    /**
     * btnDesc : 配液
     * btnType : Despensing
     * curOeoreId :
     * ordList : [{"ArcimDescList":[{"ArcimDesc":"地西泮注射液(安定)[10mg:2ml]","DoseQtyUnit":"1 支","PhOrdQtyUnit":"12 支"},{"ArcimDesc":"注射用青霉素钠[80万单位]","DoseQtyUnit":"160 万单位","PhOrdQtyUnit":"24 支"},{"ArcimDesc":"静脉推注及材料费","DoseQtyUnit":" 万单位","PhOrdQtyUnit":"12 项/次"}],"CreateDateTime":"2019-11-27 20:54:41","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":" 万单位","EpisodeID":"24957701","Notes":"  ","OeoriId":"25186794||53||5","PhOrdQtyUnit":"总量12 项/次","PhcinDesc":"静脉推注","auditDateTime":"","auditUser":"","desDateTime":"","desUser":"","OrdState":"已执行"},{"ArcimDescList":[{"ArcimDesc":"氯化钠注射液[[塑瓶][0.9%×10ml]","DoseQtyUnit":"1 支","PhOrdQtyUnit":"12 支"}],"CreateDateTime":"2019-11-27 20:54:41","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":" 万单位","EpisodeID":"24957701","Notes":"  ","OeoriId":"25186794||53||6","PhOrdQtyUnit":"总量12 项/次","PhcinDesc":"静脉推注","auditDateTime":"","auditUser":"","desDateTime":"","desUser":""},{"ArcimDescList":[{"ArcimDesc":"氯化钠注射液[[塑瓶][0.9%×10ml]","DoseQtyUnit":"1 支","PhOrdQtyUnit":"12 支"},{"ArcimDesc":"注射用青霉素钠[80万单位]","DoseQtyUnit":"160 万单位","PhOrdQtyUnit":"24 支"},{"ArcimDesc":"静脉推注及材料费","DoseQtyUnit":" 万单位","PhOrdQtyUnit":"12 项/次"}],"CreateDateTime":"2019-11-27 20:54:41","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":" 万单位","EpisodeID":"24957701","Notes":"  ","OeoriId":"25186794||53||7","PhOrdQtyUnit":"总量12 项/次","PhcinDesc":"静脉推注","auditDateTime":"","auditUser":"","desDateTime":"","desUser":""},{"ArcimDescList":[{"ArcimDesc":"地西泮注射液(安定)[10mg:2ml]","DoseQtyUnit":"1 支","PhOrdQtyUnit":"12 支"},{"ArcimDesc":"注射用青霉素钠[80万单位]","DoseQtyUnit":"160 万单位","PhOrdQtyUnit":"24 支"},{"ArcimDesc":"静脉推注及材料费","DoseQtyUnit":" 万单位","PhOrdQtyUnit":"12 项/次"}],"CreateDateTime":"2019-11-27 20:54:41","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":" 万单位","EpisodeID":"24957701","Notes":"  ","OeoriId":"25186794||53||8","PhOrdQtyUnit":"总量12 项/次","PhcinDesc":"静脉推注","auditDateTime":"","auditUser":"","desDateTime":"","desUser":""}]
     * patInfo : {"PatAge":"48岁","PatName":"刘春梅","PatRegNo":"0000000705","PatSex":"女"}
     */

    public String btnDesc;
    public String btnType;
    public String curOeoreId;
    /**
     * PatAge : 48岁
     * PatName : 刘春梅
     * PatRegNo : 0000000705
     * PatSex : 女
     */

    private Map patInfo;
    /**
     * ArcimDescList : [{"ArcimDesc":"地西泮注射液(安定)[10mg:2ml]","DoseQtyUnit":"1 支","PhOrdQtyUnit":"12 支"},{"ArcimDesc":"注射用青霉素钠[80万单位]","DoseQtyUnit":"160 万单位","PhOrdQtyUnit":"24 支"},{"ArcimDesc":"静脉推注及材料费","DoseQtyUnit":" 万单位","PhOrdQtyUnit":"12 项/次"}]
     * CreateDateTime : 2019-11-27 20:54:41
     * DisposeStatDesc : 需处理临嘱
     * DoseQtyUnit :  万单位
     * EpisodeID : 24957701
     * Notes :
     * OeoriId : 25186794||53||5
     * PhOrdQtyUnit : 总量12 项/次
     * PhcinDesc : 静脉推注
     * auditDateTime :
     * auditUser :
     * desDateTime :
     * desUser :
     * OrdState : 已执行
     */

    private List<Map<String,Object>> ordList;


    public Map getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(Map patInfo) {
        this.patInfo = patInfo;
    }

    public List<Map<String,Object>> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<Map<String,Object>> ordList) {
        this.ordList = ordList;
    }

}
