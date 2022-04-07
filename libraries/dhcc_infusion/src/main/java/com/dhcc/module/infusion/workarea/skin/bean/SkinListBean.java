package com.dhcc.module.infusion.workarea.skin.bean;

import android.text.TextUtils;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.blood.bean.ArcimDescListBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-11-04/15:29
 * @email:grs0515@163.com
 */
public class SkinListBean extends CommResult {


    /**
     * ordList : [{"ArcimDesc":"____注射用青霉素钠[400万U] ( )","CreateDateTime":"2019-11-05 10:07:52","CtcpDesc":"医生01","DisposeStatDesc":"皮试","DoseQtyUnit":"800 万IU","EpisodeID":"661","Notes":" ","ObserveTime":"","OeoriId":"637||6||1","PhOrdQtyUnit":"800 万IU","PhcinDesc":"静脉滴注(皮试)","TestDateTime":" ","TestMethod":"","TestResult":"","TestStartTime":"","TestUser":""},{"ArcimDesc":"注射用青霉素钠[80万U] ( )","CreateDateTime":"2019-11-04 12:33:06","CtcpDesc":"医生01","DisposeStatDesc":"皮试","DoseQtyUnit":"80 万IU","EpisodeID":"660","Notes":" ","ObserveTime":"","OeoriId":"636||4||1","PhOrdQtyUnit":"80 万IU","PhcinDesc":"皮试(皮试)","TestDateTime":" ","TestMethod":"","TestResult":"","TestStartTime":"","TestUser":""},{"ArcimDesc":"注射用青霉素钠[400万U] ( )","CreateDateTime":"2019-11-04 14:31:33","CtcpDesc":"医生01","DisposeStatDesc":"皮试","DoseQtyUnit":"800 万IU","EpisodeID":"660","Notes":" ","ObserveTime":"20分钟","OeoriId":"636||5||1","PhOrdQtyUnit":"800 万IU","PhcinDesc":"静脉注射(皮试)","TestDateTime":" ","TestMethod":"","TestResult":"","TestStartTime":"2019-11-04 15:00:18","TestUser":""}]
     * patInfo : {"PatAge":"27岁","PatName":"第一","PatRegNo":"0000000020","PatSex":"女"}
     */

    private PatInfoBean patInfo;
    private List<OrdListBean> ordList;
    //按钮类型/描述
    private String btnType;
    private String btnDesc;
    private String curOeoreId;
    //是否皮试(腕带 瓶贴 双验证)  1:是
    private String skinFlag;
    //只能扫码选中  1:是
    private String onlyScanFlag;
    //扫码双签  1:是
    private String scanDoubleFlag;

    public String getScanDoubleFlag() {
        return scanDoubleFlag == null ? "" : scanDoubleFlag;
    }

    public String getOnlyScanFlag() {
        return onlyScanFlag == null ? "" : onlyScanFlag;
    }

    public void setOnlyScanFlag(String onlyScanFlag) {
        this.onlyScanFlag = onlyScanFlag;
    }

    public String getSkinFlag() {
        return skinFlag == null ? "" : skinFlag;
    }

    public void setSkinFlag(String skinFlag) {
        this.skinFlag = skinFlag;
    }

    public String getCurOeoreId() {
        return curOeoreId == null ? "" : curOeoreId;
    }

    public void setCurOeoreId(String curOeoreId) {
        this.curOeoreId = curOeoreId;
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

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

    public static class OrdListBean {
        /**
         * ArcimDesc : ____注射用青霉素钠[400万U] ( )
         * CreateDateTime : 2019-11-05 10:07:52
         * CtcpDesc : 医生01
         * DisposeStatDesc : 皮试
         * DoseQtyUnit : 800 万IU
         * EpisodeID : 661
         * Notes :
         * ObserveTime :
         * OeoriId : 637||6||1
         * PhOrdQtyUnit : 800 万IU
         * PhcinDesc : 静脉滴注(皮试)
         * TestDateTime :
         * TestMethod :
         * TestResult :
         * TestStartTime :
         * TestUser :
         */

        private List<ArcimDescListBean> ArcimDescList; //医嘱名称
        private String ArcimDesc; //医嘱名称
        private String CreateDateTime;//医嘱创建时间
        private String CtcpDesc; //医生
        private String DisposeStatDesc;//处置状态
        private String DoseQtyUnit;//剂量
        private String EpisodeID;//就诊号
        private String Notes;//备注
        private String ObserveTime;//观察时间
        private String OeoriId;//执行记录ID
        private String PhOrdQtyUnit; //总量
        private String PhcinDesc; //用法
        private String TestDateTime; //皮试时间
        private String TestMethod;//皮试方法
        private String TestResult;//皮试结果
        private String TestStartTime;//皮试开始时间
        private String TestUser;//皮试人
        private boolean select;
        //配液/复核
        private String desUser;
        private String auditUser;
        private String desDateTime;
        private String auditDateTime;

        public List<ArcimDescListBean> getArcimDescList() {
            if (ArcimDescList == null) {
                ArcimDescList = new ArrayList<>();
                //兼容空数据
                if(!TextUtils.isEmpty(ArcimDesc)){
                    ArcimDescListBean bean = new ArcimDescListBean();
                    bean.setArcimDesc(ArcimDesc);
                    ArcimDescList.add(bean);
                }
            }
            return ArcimDescList;
        }

        public void setArcimDescList(List<ArcimDescListBean> arcimDescList) {
            ArcimDescList = arcimDescList;
        }

        public String getAuditDateTime() {
            return auditDateTime == null ? "" : auditDateTime;
        }

        public void setAuditDateTime(String auditDateTime) {
            this.auditDateTime = auditDateTime;
        }

        public String getDesDateTime() {
            return desDateTime == null ? "" : desDateTime;
        }

        public void setDesDateTime(String desDateTime) {
            this.desDateTime = desDateTime;
        }

        public String getDesUser() {
            return desUser == null ? "" : desUser;
        }

        public void setDesUser(String desUser) {
            this.desUser = desUser;
        }

        public String getAuditUser() {
            return auditUser == null ? "" : auditUser;
        }

        public void setAuditUser(String auditUser) {
            this.auditUser = auditUser;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }


        public String getCreateDateTime() {
            return CreateDateTime;
        }

        public void setCreateDateTime(String CreateDateTime) {
            this.CreateDateTime = CreateDateTime;
        }

        public String getCtcpDesc() {
            return CtcpDesc;
        }

        public void setCtcpDesc(String CtcpDesc) {
            this.CtcpDesc = CtcpDesc;
        }

        public String getDisposeStatDesc() {
            return DisposeStatDesc;
        }

        public void setDisposeStatDesc(String DisposeStatDesc) {
            this.DisposeStatDesc = DisposeStatDesc;
        }

        public String getDoseQtyUnit() {
            return DoseQtyUnit;
        }

        public void setDoseQtyUnit(String DoseQtyUnit) {
            this.DoseQtyUnit = DoseQtyUnit;
        }

        public String getEpisodeID() {
            return EpisodeID;
        }

        public void setEpisodeID(String EpisodeID) {
            this.EpisodeID = EpisodeID;
        }

        public String getNotes() {
            return Notes == null ? "" : Notes;
        }

        public void setNotes(String Notes) {
            this.Notes = Notes;
        }

        public String getObserveTime() {
            return ObserveTime == null ? "" : ObserveTime;
        }

        public void setObserveTime(String ObserveTime) {
            this.ObserveTime = ObserveTime;
        }

        public String getOeoriId() {
            return OeoriId;
        }

        public void setOeoriId(String OeoriId) {
            this.OeoriId = OeoriId;
        }

        public String getPhOrdQtyUnit() {
            return PhOrdQtyUnit;
        }

        public void setPhOrdQtyUnit(String PhOrdQtyUnit) {
            this.PhOrdQtyUnit = PhOrdQtyUnit;
        }

        public String getPhcinDesc() {
            return PhcinDesc;
        }

        public void setPhcinDesc(String PhcinDesc) {
            this.PhcinDesc = PhcinDesc;
        }

        public String getTestDateTime() {
            return TestDateTime;
        }

        public void setTestDateTime(String TestDateTime) {
            this.TestDateTime = TestDateTime;
        }

        public String getTestMethod() {
            return TestMethod == null ? "" : TestMethod;
        }

        public void setTestMethod(String TestMethod) {
            this.TestMethod = TestMethod;
        }

        public String getTestResult() {
            return TestResult;
        }

        public void setTestResult(String TestResult) {
            this.TestResult = TestResult;
        }

        public String getTestStartTime() {
            return TestStartTime == null ? "" : TestStartTime;
        }

        public void setTestStartTime(String TestStartTime) {
            this.TestStartTime = TestStartTime;
        }

        public String getTestUser() {
            return TestUser == null ? "" : TestUser;
        }

        public void setTestUser(String TestUser) {
            this.TestUser = TestUser;
        }
    }
}
