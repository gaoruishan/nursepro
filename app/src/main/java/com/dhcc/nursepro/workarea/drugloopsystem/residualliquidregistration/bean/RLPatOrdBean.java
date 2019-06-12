package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean;

import java.io.Serializable;
import java.util.List;

public class RLPatOrdBean {


    /**
     * PatOrdList : [{"PatBed":"06","PatName":"lc2019021204","PatOrds":[{"JmOeoreId":"194||60||1","JmUnitDesc":"mg","MethDesc":"外用","OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||1"}],"OeoreId":"194||60||1","ResidualQty":"20 ml","SttdateTime":"2019-05-29 11:15:20","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||60||2","JmUnitDesc":"mg","MethDesc":"外用","OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||2"}],"OeoreId":"194||60||2","ResidualQty":"20 ml","SttdateTime":"2019-05-29 11:15:20","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||60||3","JmUnitDesc":"mg","MethDesc":"外用","OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||3"}],"OeoreId":"194||60||3","ResidualQty":"","SttdateTime":"2019-05-29 11:15:20","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||60||4","JmUnitDesc":"mg","MethDesc":"外用","OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||4"}],"OeoreId":"194||60||4","ResidualQty":"20 ml","SttdateTime":"2019-05-29 11:15:20","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||1","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||1"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||1"}],"OeoreId":"194||65||1","ResidualQty":"","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||2","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||2"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||2"}],"OeoreId":"194||65||2","ResidualQty":"","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||3","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||3"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||3"}],"OeoreId":"194||65||3","ResidualQty":"20 ml","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||4","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||4"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||4"}],"OeoreId":"194||65||4","ResidualQty":"","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||5","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||5"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||5"}],"OeoreId":"194||65||5","ResidualQty":"","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"}]},{"PatBed":"17","PatName":"lc2019021402","PatOrds":[{"JmOeoreId":"281||30||1","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml","oeoreId":"281||29||1"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"281||30||1"}],"OeoreId":"281||29||1","ResidualQty":"","SttdateTime":"2019-06-04 09:20:46","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"281||30||2","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml","oeoreId":"281||29||2"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"281||30||2"}],"OeoreId":"281||29||2","ResidualQty":"","SttdateTime":"2019-06-04 09:20:46","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"281||30||3","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml","oeoreId":"281||29||3"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"281||30||3"}],"OeoreId":"281||29||3","ResidualQty":"","SttdateTime":"2019-06-04 09:20:46","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"281||30||4","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml","oeoreId":"281||29||4"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"281||30||4"}],"OeoreId":"281||29||4","ResidualQty":"","SttdateTime":"2019-06-04 09:20:46","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"281||30||5","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"10%葡萄糖注射液(塑瓶)[250ML]","DoseQtyUnit":"250 ml","JmFlag":"0","PhOrdQtyUnit":"750 ml","oeoreId":"281||29||5"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"281||30||5"}],"OeoreId":"281||29||5","ResidualQty":"","SttdateTime":"2019-06-04 09:20:46","UnitDescComb":"ml^g^kg","WhereGo":"A^B"}]}]
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
         * PatOrds : [{"JmOeoreId":"194||60||1","JmUnitDesc":"mg","MethDesc":"外用","OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||1"}],"OeoreId":"194||60||1","ResidualQty":"20 ml","SttdateTime":"2019-05-29 11:15:20","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||60||2","JmUnitDesc":"mg","MethDesc":"外用","OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||2"}],"OeoreId":"194||60||2","ResidualQty":"20 ml","SttdateTime":"2019-05-29 11:15:20","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||60||3","JmUnitDesc":"mg","MethDesc":"外用","OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||3"}],"OeoreId":"194||60||3","ResidualQty":"","SttdateTime":"2019-05-29 11:15:20","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||60||4","JmUnitDesc":"mg","MethDesc":"外用","OeoreGroup":[{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||4"}],"OeoreId":"194||60||4","ResidualQty":"20 ml","SttdateTime":"2019-05-29 11:15:20","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||1","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||1"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||1"}],"OeoreId":"194||65||1","ResidualQty":"","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||2","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||2"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||2"}],"OeoreId":"194||65||2","ResidualQty":"","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||3","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||3"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||3"}],"OeoreId":"194||65||3","ResidualQty":"20 ml","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||4","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||4"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||4"}],"OeoreId":"194||65||4","ResidualQty":"","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"},{"JmOeoreId":"194||66||5","JmUnitDesc":"mg","MethDesc":"静脉滴注","OeoreGroup":[{"ArcimDesc":"0.9%氯化钠注射液[500ml]","DoseQtyUnit":"500 ml","JmFlag":"0","PhOrdQtyUnit":"1500 ml","oeoreId":"194||65||5"},{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||66||5"}],"OeoreId":"194||65||5","ResidualQty":"","SttdateTime":"2019-06-04 09:19:17","UnitDescComb":"ml^g^kg","WhereGo":"A^B"}]
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
             * JmOeoreId : 194||60||1
             * JmUnitDesc : mg
             * MethDesc : 外用
             * OeoreGroup : [{"ArcimDesc":"芬太尼透皮贴剂(多瑞吉)[8.4mg*5]","DoseQtyUnit":"8.4 mg","JmFlag":"1","PhOrdQtyUnit":"25.2 mg","oeoreId":"194||60||1"}]
             * OeoreId : 194||60||1
             * ResidualQty : 20 ml
             * SttdateTime : 2019-05-29 11:15:20
             * UnitDescComb : ml^g^kg
             * WhereGo : A^B
             */

            private String JmOeoreId;
            private String JmUnitDesc;
            private String MethDesc;
            private String OeoreId;
            private String ResidualQty;
            private String SttdateTime;
            private String UnitDescComb;
            private String WhereGo;
            private List<OeoreGroupBean> OeoreGroup;

            public String getJmOeoreId() {
                return JmOeoreId;
            }

            public void setJmOeoreId(String JmOeoreId) {
                this.JmOeoreId = JmOeoreId;
            }

            public String getJmUnitDesc() {
                return JmUnitDesc;
            }

            public void setJmUnitDesc(String JmUnitDesc) {
                this.JmUnitDesc = JmUnitDesc;
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

            public String getUnitDescComb() {
                return UnitDescComb;
            }

            public void setUnitDescComb(String UnitDescComb) {
                this.UnitDescComb = UnitDescComb;
            }

            public String getWhereGo() {
                return WhereGo;
            }

            public void setWhereGo(String WhereGo) {
                this.WhereGo = WhereGo;
            }

            public List<OeoreGroupBean> getOeoreGroup() {
                return OeoreGroup;
            }

            public void setOeoreGroup(List<OeoreGroupBean> OeoreGroup) {
                this.OeoreGroup = OeoreGroup;
            }

            public static class OeoreGroupBean implements Serializable {
                /**
                 * ArcimDesc : 芬太尼透皮贴剂(多瑞吉)[8.4mg*5]
                 * DoseQtyUnit : 8.4 mg
                 * JmFlag : 1
                 * PhOrdQtyUnit : 25.2 mg
                 * oeoreId : 194||60||1
                 */

                private String ArcimDesc;
                private String DoseQtyUnit;
                private String JmFlag;
                private String PhOrdQtyUnit;
                private String oeoreId;

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

                public String getOeoreId() {
                    return oeoreId;
                }

                public void setOeoreId(String oeoreId) {
                    this.oeoreId = oeoreId;
                }
            }
        }
    }
}
