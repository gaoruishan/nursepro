package com.dhcc.module.infusion.workarea.blood;

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
    private List<OrdListBean> ordList;

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
         * ArcimDescList : [{"ArcimDesc":"血常规"}]
         * CreateDateTime :
         * CtcpDesc : 急诊医生01
         * DisposeStatDesc : 需处理临嘱
         * DoseQtyUnit :
         * EpisodeID : 139
         * LabColor :
         * LabNo : 1000003770
         * Notes :
         * OeoriId : 131||3||1
         * PhOrdQtyUnit : 总量1
         * PhcinDesc :
         * SpecDesc : 引流液
         */

        private String CreateDateTime;
        private String CtcpDesc;
        private String DisposeStatDesc;
        private String DoseQtyUnit;
        private String EpisodeID;
        private String LabColor;
        private String LabNo;
        private String Notes;
        private String OeoriId;
        private String PhOrdQtyUnit;
        private String PhcinDesc;
        private String SpecDesc;
        private List<ArcimDescListBean> ArcimDescList;

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

        public String getLabColor() {
            return LabColor;
        }

        public void setLabColor(String LabColor) {
            this.LabColor = LabColor;
        }

        public String getLabNo() {
            return LabNo;
        }

        public void setLabNo(String LabNo) {
            this.LabNo = LabNo;
        }

        public String getNotes() {
            return Notes;
        }

        public void setNotes(String Notes) {
            this.Notes = Notes;
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

        public String getSpecDesc() {
            return SpecDesc;
        }

        public void setSpecDesc(String SpecDesc) {
            this.SpecDesc = SpecDesc;
        }

        public List<ArcimDescListBean> getArcimDescList() {
            return ArcimDescList;
        }

        public void setArcimDescList(List<ArcimDescListBean> ArcimDescList) {
            this.ArcimDescList = ArcimDescList;
        }

        public static class ArcimDescListBean {
            /**
             * ArcimDesc : 血常规
             */

            private String ArcimDesc;

            public String getArcimDesc() {
                return ArcimDesc;
            }

            public void setArcimDesc(String ArcimDesc) {
                this.ArcimDesc = ArcimDesc;
            }
        }
    }
}
