package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean;

import java.io.Serializable;
import java.util.List;

public class RLPatOrdBean {


    /**
     * PatOrdList : [{"PatBed":"06","PatName":"lc2019021204","PatOrds":[{"JmOeoreId":"194||66||1","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"},{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||65||1","ResidualQty":"20 ml","SttdateTime":"2019-06-04 09:19:17"},{"JmOeoreId":"194||66||2","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"},{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||65||2","ResidualQty":"20 ml","SttdateTime":"2019-06-04 09:19:17"}]},{"PatBed":"02","PatName":"lc2019020201","PatOrds":[]},{"PatBed":"03","PatName":"lc2019021201","PatOrds":[]},{"PatBed":"04","PatName":"lc2019021202","PatOrds":[]},{"PatBed":"05","PatName":"lc2019021203","PatOrds":[]},{"PatBed":"01","PatName":"lc2019013101","PatOrds":[]},{"PatBed":"07","PatName":"zfmcs04","PatOrds":[]},{"PatBed":"08","PatName":"jhm0100","PatOrds":[]},{"PatBed":"09","PatName":"zfmcs03","PatOrds":[]},{"PatBed":"10","PatName":"zfmcs05","PatOrds":[]},{"PatBed":"11","PatName":"jhm0101","PatOrds":[]},{"PatBed":"12","PatName":"jhm0102","PatOrds":[]},{"PatBed":"13","PatName":"lc2019021601","PatOrds":[]},{"PatBed":"14","PatName":"tyu02","PatOrds":[]},{"PatBed":"15","PatName":"lc2019021301","PatOrds":[]},{"PatBed":"16","PatName":"lc2019021801","PatOrds":[]},{"PatBed":"17","PatName":"lc2019021402","PatOrds":[{"JmOeoreId":"281||30||1","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml"},{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml"}],"OeoreId":"281||29||1","ResidualQty":"20 ml","SttdateTime":"2019-06-04 09:20:46"},{"JmOeoreId":"281||30||2","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml"},{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml"}],"OeoreId":"281||29||2","ResidualQty":"20 ml","SttdateTime":"2019-06-04 09:20:46"}]},{"PatBed":"18","PatName":"lc2019021605","PatOrds":[]},{"PatBed":"19","PatName":"jhm0103","PatOrds":[]},{"PatBed":"20","PatName":"lc2019021401","PatOrds":[]},{"PatBed":"21","PatName":"jhm0106","PatOrds":[]},{"PatBed":"22","PatName":"jhm0107","PatOrds":[]},{"PatBed":"23","PatName":"lc2019022001","PatOrds":[]},{"PatBed":"24","PatName":"lc2018021802","PatOrds":[]},{"PatBed":"25","PatName":"jhm0110","PatOrds":[]},{"PatBed":"26","PatName":"lc2019021905","PatOrds":[]},{"PatBed":"27","PatName":"时效性验证","PatOrds":[]},{"PatBed":"28","PatName":"lc2019022002","PatOrds":[]},{"PatBed":"29","PatName":"转科需关注","PatOrds":[]},{"PatBed":"30","PatName":"lc2019022101","PatOrds":[]}]
     * msg : 成功
     * msgcode :
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<PatOrdListBean> PatOrdList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PatOrdListBean> getPatOrdList() {
        return PatOrdList;
    }

    public void setPatOrdList(List<PatOrdListBean> PatOrdList) {
        this.PatOrdList = PatOrdList;
    }

    public static class PatOrdListBean implements Serializable {
        /**
         * PatBed : 06
         * PatName : lc2019021204
         * PatOrds : [{"JmOeoreId":"194||66||1","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"},{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||65||1","ResidualQty":"20 ml","SttdateTime":"2019-06-04 09:19:17"},{"JmOeoreId":"194||66||2","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"},{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"}],"OeoreId":"194||65||2","ResidualQty":"20 ml","SttdateTime":"2019-06-04 09:19:17"}]
         */

        private String PatBed;
        private String PatName;
        private List<PatOrdsBean> PatOrds;

        public String getPatBed() {
            return PatBed;
        }

        public void setPatBed(String PatBed) {
            this.PatBed = PatBed;
        }

        public String getPatName() {
            return PatName;
        }

        public void setPatName(String PatName) {
            this.PatName = PatName;
        }

        public List<PatOrdsBean> getPatOrds() {
            return PatOrds;
        }

        public void setPatOrds(List<PatOrdsBean> PatOrds) {
            this.PatOrds = PatOrds;
        }

        public static class PatOrdsBean implements Serializable {
            /**
             * JmOeoreId : 194||66||1
             * MethDesc : 静脉滴注
             * OeoreGroup : [{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"},{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml"}]
             * OeoreId : 194||65||1
             * ResidualQty : 20 ml
             * SttdateTime : 2019-06-04 09:19:17
             */

            private String JmOeoreId;
            private String MethDesc;
            private String OeoreId;
            private String ResidualQty;
            private String SttdateTime;
            private List<OeoreGroupBean> OeoreGroup;

            public String getJmOeoreId() {
                return JmOeoreId;
            }

            public void setJmOeoreId(String JmOeoreId) {
                this.JmOeoreId = JmOeoreId;
            }

            public String getMethDesc() {
                return MethDesc;
            }

            public void setMethDesc(String MethDesc) {
                this.MethDesc = MethDesc;
            }

            public String getOeoreId() {
                return OeoreId;
            }

            public void setOeoreId(String OeoreId) {
                this.OeoreId = OeoreId;
            }

            public String getResidualQty() {
                return ResidualQty;
            }

            public void setResidualQty(String ResidualQty) {
                this.ResidualQty = ResidualQty;
            }

            public String getSttdateTime() {
                return SttdateTime;
            }

            public void setSttdateTime(String SttdateTime) {
                this.SttdateTime = SttdateTime;
            }

            public List<OeoreGroupBean> getOeoreGroup() {
                return OeoreGroup;
            }

            public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
                this.OeoreGroup = OeoreGroup;
            }

            public static class OeoreGroupBean implements Serializable {
                /**
                 * ArcimDesc : 0.9%氯化钠注射液[500ml]
                 * DoseQtyUnit : 500 ml
                 * JmFlag : 0
                 * PhOrdQtyUnit : 1500 ml
                 */

                private String ArcimDesc;
                private String DoseQtyUnit;
                private String JmFlag;
                private String PhOrdQtyUnit;

                public String getArcimDesc() {
                    return ArcimDesc;
                }

                public void setArcimDesc(String ArcimDesc) {
                    this.ArcimDesc = ArcimDesc;
                }

                public String getDoseQtyUnit() {
                    return DoseQtyUnit;
                }

                public void setDoseQtyUnit(String DoseQtyUnit) {
                    this.DoseQtyUnit = DoseQtyUnit;
                }

                public String getJmFlag() {
                    return JmFlag;
                }

                public void setJmFlag(String JmFlag) {
                    this.JmFlag = JmFlag;
                }

                public String getPhOrdQtyUnit() {
                    return PhOrdQtyUnit;
                }

                public void setPhOrdQtyUnit(String PhOrdQtyUnit) {
                    this.PhOrdQtyUnit = PhOrdQtyUnit;
                }
            }
        }
    }
}
