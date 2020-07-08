package com.dhcc.module.infusion.workarea.orderexecute.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;

import java.io.Serializable;
import java.util.List;

/**
 * OrderSearchBean
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderExecuteBean extends CommResult implements Serializable {


    /**
     * buttons : [{"code":"excuteOrder","desc":"执行","exeCode":"F"},{"code":"cancelExcuteOrder","desc":"撤销执行","exeCode":"C"}]
     * curOeoreId :
     * ordList : [{"ArcimDescList":[{"ArcimDesc":"注射用奥美拉唑钠[60mg]","DoseQtyUnit":"60 mg","PhOrdQtyUnit":"1 支"},{"ArcimDesc":"一次性注射器[10ml]","DoseQtyUnit":" mg","PhOrdQtyUnit":"1 个"}],"CreateDateTime":"","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":" mg","EpisodeID":"165","Notes":" ","OeoriId":"144||20||1","OrdState":"未执行","PhOrdQtyUnit":"总量1 个","PhcinDesc":"静脉注射","auditDateTime":"","auditUser":"","desDateTime":"","desUser":""},{"ArcimDescList":[{"ArcimDesc":"注射用哌库溴铵(阿端)[4MG:一针一水]","DoseQtyUnit":"4 mg","PhOrdQtyUnit":"1 支"},{"ArcimDesc":"注射用氨甲环酸(荷莫塞)[1G]","DoseQtyUnit":"1 g","PhOrdQtyUnit":"1 支"}],"CreateDateTime":"","DisposeStatDesc":"需处理临嘱","DoseQtyUnit":"1 g","EpisodeID":"165","Notes":" ","OeoriId":"144||24||1","OrdState":"未执行","PhOrdQtyUnit":"总量1 支","PhcinDesc":"静脉注射","auditDateTime":"","auditUser":"","desDateTime":"","desUser":""}]
     * patInfo : {"PatAge":"33岁","PatName":"宇宇宇001","PatRegNo":"0000000097","PatSex":"未知性别"}
     * sheetList : [{"code":"Default","desc":"全部医嘱"},{"code":"DefaultSee","desc":"需处理医嘱"}]
     */

    private String curOeoreId;
    private PatInfoBean patInfo;
    private List<OrdButtonsBean> buttons;
    private List<BloodOrdListBean> ordList;
    private List<OrdSheetListBean> sheetList;

    public String getCurOeoreId() {
        return curOeoreId;
    }

    public void setCurOeoreId(String curOeoreId) {
        this.curOeoreId = curOeoreId;
    }

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public List<OrdButtonsBean> getButtons() {
        return buttons;
    }

    public void setButtons(List<OrdButtonsBean> buttons) {
        this.buttons = buttons;
    }

    public List<BloodOrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<BloodOrdListBean> ordList) {
        this.ordList = ordList;
    }

    public List<OrdSheetListBean> getSheetList() {
        return sheetList;
    }

    public void setSheetList(List<OrdSheetListBean> sheetList) {
        this.sheetList = sheetList;
    }

    public static class PatInfoBean {
        /**
         * PatAge : 33岁
         * PatName : 宇宇宇001
         * PatRegNo : 0000000097
         * PatSex : 未知性别
         */

        private String PatAge;
        private String PatName;
        private String PatRegNo;
        private String PatSex;

        public String getPatAge() {
            return PatAge;
        }

        public void setPatAge(String PatAge) {
            this.PatAge = PatAge;
        }

        public String getPatName() {
            return PatName;
        }

        public void setPatName(String PatName) {
            this.PatName = PatName;
        }

        public String getPatRegNo() {
            return PatRegNo;
        }

        public void setPatRegNo(String PatRegNo) {
            this.PatRegNo = PatRegNo;
        }

        public String getPatSex() {
            return PatSex;
        }

        public void setPatSex(String PatSex) {
            this.PatSex = PatSex;
        }
    }


}
