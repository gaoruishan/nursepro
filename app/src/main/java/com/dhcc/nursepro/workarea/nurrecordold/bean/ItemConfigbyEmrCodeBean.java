package com.dhcc.nursepro.workarea.nurrecordold.bean;

import java.util.List;

public class ItemConfigbyEmrCodeBean {

    /**
     * ItemSetList : [{"ItemCode":"btnSkip","LinkCode":"DHCNURXHPGD","LinkCodeId":"296","LinkNote":"Item14!Item1","LinkType":"3","ModeInfo":{"getListMth":"","getValMth":"getPGDVal","linkModel":"","modelCode":"DHCNURXHPGD","modelName":"入院评估单","modelNum":"3","modelType":"2","saveMth":"savePGDData"}},{"ItemCode":"Item26","LinkCode":"Item13","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item25","LinkCode":"Item13","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item27","LinkCode":"Item13","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item28","LinkCode":"Item13","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item29","LinkCode":"Item13","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item30","LinkCode":"Item13","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item15","LinkCode":"Item20","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item16","LinkCode":"Item20","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item17","LinkCode":"Item20","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item18","LinkCode":"Item20","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item19","LinkCode":"Item20","LinkCodeId":"296","LinkNote":"","LinkType":"2","ModeInfo":{}},{"ItemCode":"Item1","LinkCode":"Item1","LinkCodeId":"296","LinkNote":"","LinkType":"4","ModeInfo":{}}]
     * Status : 0
     * msg : 成功
     * msgcode : 999999
     */

    private String Status;
    private String msg;
    private String msgcode;
    private List<ItemSetListBean> ItemSetList;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

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

    public List<ItemSetListBean> getItemSetList() {
        return ItemSetList;
    }

    public void setItemSetList(List<ItemSetListBean> ItemSetList) {
        this.ItemSetList = ItemSetList;
    }

    public static class ItemSetListBean {
        /**
         * ItemCode : btnSkip
         * LinkCode : DHCNURXHPGD
         * LinkCodeId : 296
         * LinkNote : Item14!Item1
         * LinkType : 3
         * ModeInfo : {"getListMth":"","getValMth":"getPGDVal","linkModel":"","modelCode":"DHCNURXHPGD","modelName":"入院评估单","modelNum":"3","modelType":"2","saveMth":"savePGDData"}
         */

        // LinkType : 1     默认值
        // LinkType : 2     计分
        // LinkType : 3     跳转
        // LinkType : 4     必填项
        // LinkType : 5     输入类型-限定数字

        private String ItemCode;
        private String LinkCode;
        private String LinkCodeId;
        private String LinkNote;
        private String LinkType;
        private ModeInfoBean ModeInfo;

        public String getItemCode() {
            return ItemCode;
        }

        public void setItemCode(String ItemCode) {
            this.ItemCode = ItemCode;
        }

        public String getLinkCode() {
            return LinkCode;
        }

        public void setLinkCode(String LinkCode) {
            this.LinkCode = LinkCode;
        }

        public String getLinkCodeId() {
            return LinkCodeId;
        }

        public void setLinkCodeId(String LinkCodeId) {
            this.LinkCodeId = LinkCodeId;
        }

        public String getLinkNote() {
            return LinkNote;
        }

        public void setLinkNote(String LinkNote) {
            this.LinkNote = LinkNote;
        }

        public String getLinkType() {
            return LinkType;
        }

        public void setLinkType(String LinkType) {
            this.LinkType = LinkType;
        }

        public ModeInfoBean getModeInfo() {
            return ModeInfo;
        }

        public void setModeInfo(ModeInfoBean ModeInfo) {
            this.ModeInfo = ModeInfo;
        }

        public static class ModeInfoBean {
            /**
             * getListMth :
             * getValMth : getPGDVal
             * linkModel :
             * modelCode : DHCNURXHPGD
             * modelName : 入院评估单
             * modelNum : 3
             * modelType : 2
             * saveMth : savePGDData
             */

            private String getListMth;
            private String getValMth;
            private String linkModel;
            private String modelCode;
            private String modelName;
            private String modelNum;
            private String modelType;
            private String saveMth;

            public String getGetListMth() {
                return getListMth;
            }

            public void setGetListMth(String getListMth) {
                this.getListMth = getListMth;
            }

            public String getGetValMth() {
                return getValMth;
            }

            public void setGetValMth(String getValMth) {
                this.getValMth = getValMth;
            }

            public String getLinkModel() {
                return linkModel;
            }

            public void setLinkModel(String linkModel) {
                this.linkModel = linkModel;
            }

            public String getModelCode() {
                return modelCode;
            }

            public void setModelCode(String modelCode) {
                this.modelCode = modelCode;
            }

            public String getModelName() {
                return modelName;
            }

            public void setModelName(String modelName) {
                this.modelName = modelName;
            }

            public String getModelNum() {
                return modelNum;
            }

            public void setModelNum(String modelNum) {
                this.modelNum = modelNum;
            }

            public String getModelType() {
                return modelType;
            }

            public void setModelType(String modelType) {
                this.modelType = modelType;
            }

            public String getSaveMth() {
                return saveMth;
            }

            public void setSaveMth(String saveMth) {
                this.saveMth = saveMth;
            }
        }
    }
}
