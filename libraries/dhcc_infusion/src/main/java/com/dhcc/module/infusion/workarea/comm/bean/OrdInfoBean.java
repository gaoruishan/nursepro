package com.dhcc.module.infusion.workarea.comm.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.dosing.bean.OeoreGroupBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-05-09/17:24
 * @email:grs0515@163.com
 */
public class OrdInfoBean extends CommResult {

    /**
     * msg : 成功
     * msgcode :
     * ordInfoArr : {"CreateDateTime":"2018-04-11 10:54","Doctor":"医生01","Notes":"备注0411","OrdWorkList":[{"DateTime":"2019-04-26 15:33","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"Demo Group"},{"DateTime":"2019-04-26 15:33","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"Demo Group"},{"DateTime":"2019-04-26 15:33","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"Demo Group"},{"DateTime":"2019-04-27 14:21","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 14:31","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:07","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:08","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:09","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:09","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:22","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-29 10:09","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:32","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:35","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-28 09:00","WorkCode":"Tour","WorkType":"巡视","WorkUser":"Demo Group"},{"DateTime":"2019-04-28 16:44","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 16:44","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 16:45","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 16:49","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 16:54","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 17:01","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 17:08","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 17:11","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 17:11","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-29 10:09","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:36","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-05-08 18:05","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:07","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:07","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:21","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:37","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"},{"DateTime":"2019-04-30 10:14","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"}],"PhcfrCode":"Q4h","PhcinDesc":"静脉滴注","oeoreGroup":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}]}
     * status : 0
     */

    private OrdInfoArrBean ordInfoArr;


    public OrdInfoArrBean getOrdInfoArr() {
        return ordInfoArr;
    }

    public void setOrdInfoArr(OrdInfoArrBean ordInfoArr) {
        this.ordInfoArr = ordInfoArr;
    }


    public static class OrdInfoArrBean {
        /**
         * CreateDateTime : 2018-04-11 10:54
         * Doctor : 医生01
         * Notes : 备注0411
         * OrdWorkList : [{"DateTime":"2019-04-26 15:33","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"Demo Group"},{"DateTime":"2019-04-26 15:33","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"Demo Group"},{"DateTime":"2019-04-26 15:33","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"Demo Group"},{"DateTime":"2019-04-27 14:21","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 14:31","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:07","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:08","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:09","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:09","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-27 17:22","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-29 10:09","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:32","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:35","WorkCode":"Puncture","WorkType":"穿刺","WorkUser":"护士01"},{"DateTime":"2019-04-28 09:00","WorkCode":"Tour","WorkType":"巡视","WorkUser":"Demo Group"},{"DateTime":"2019-04-28 16:44","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 16:44","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 16:45","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 16:49","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 16:54","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 17:01","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 17:08","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 17:11","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-28 17:11","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-29 10:09","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:36","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-05-08 18:05","WorkCode":"Tour","WorkType":"巡视","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:07","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:07","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:21","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"},{"DateTime":"2019-04-29 16:37","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"},{"DateTime":"2019-04-30 10:14","WorkCode":"Extract","WorkType":"拔针","WorkUser":"护士01"}]
         * PhcfrCode : Q4h
         * PhcinDesc : 静脉滴注
         * oeoreGroup : [{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}]
         */

        private String CreateDateTime;
        private String Doctor;
        private String Notes;
        private String PhcfrCode;
        private String PhcinDesc;
        private String OeoreState;
        private String OverTime;
        private List<OrdWorkListBean> OrdWorkList;
        private List<OeoreGroupBean> oeoreGroup;

        public String getOverTime() {
            return OverTime == null ? "" : OverTime;
        }

        public void setOverTime(String overTime) {
            OverTime = overTime;
        }

        public String getOeoreState() {
            return OeoreState == null ? "" : OeoreState;
        }

        public void setOeoreState(String oeoreState) {
            OeoreState = oeoreState;
        }

        public String getCreateDateTime() {
            return CreateDateTime;
        }

        public void setCreateDateTime(String CreateDateTime) {
            this.CreateDateTime = CreateDateTime;
        }

        public String getDoctor() {
            return Doctor;
        }

        public void setDoctor(String Doctor) {
            this.Doctor = Doctor;
        }

        public String getNotes() {
            return Notes;
        }

        public void setNotes(String Notes) {
            this.Notes = Notes;
        }

        public String getPhcfrCode() {
            return PhcfrCode;
        }

        public void setPhcfrCode(String PhcfrCode) {
            this.PhcfrCode = PhcfrCode;
        }

        public String getPhcinDesc() {
            return PhcinDesc;
        }

        public void setPhcinDesc(String PhcinDesc) {
            this.PhcinDesc = PhcinDesc;
        }

        public List<OrdWorkListBean> getOrdWorkList() {
            return OrdWorkList;
        }

        public void setOrdWorkList(List<OrdWorkListBean> OrdWorkList) {
            this.OrdWorkList = OrdWorkList;
        }

        public List<OeoreGroupBean> getOeoreGroup() {
            return oeoreGroup;
        }

        public void setOeoreGroup(List<OeoreGroupBean> oeoreGroup) {
            this.oeoreGroup = oeoreGroup;
        }

        public static class OrdWorkListBean {
            /**
             * DateTime : 2019-04-26 15:33
             * WorkCode : Puncture
             * WorkType : 穿刺
             * WorkUser : Demo Group
             */

            private String DateTime;
            private String WorkCode;
            private String WorkType;
            private String WorkUser;
            private List<OrdWorkListBean> childOrdWorkListBean;

            public List<OrdWorkListBean> getChildOrdWorkListBean() {
                if (childOrdWorkListBean == null) {
                    return new ArrayList<>();
                }
                return childOrdWorkListBean;
            }

            public void setChildOrdWorkListBean(List<OrdWorkListBean> childOrdWorkListBean) {
                this.childOrdWorkListBean = childOrdWorkListBean;
            }

            public String getDateTime() {
                return DateTime;
            }

            public void setDateTime(String DateTime) {
                this.DateTime = DateTime;
            }

            public String getWorkCode() {
                return WorkCode;
            }

            public void setWorkCode(String WorkCode) {
                this.WorkCode = WorkCode;
            }

            public String getWorkType() {
                return WorkType;
            }

            public void setWorkType(String WorkType) {
                this.WorkType = WorkType;
            }

            public String getWorkUser() {
                return WorkUser;
            }

            public void setWorkUser(String WorkUser) {
                this.WorkUser = WorkUser;
            }
        }

    }
}
