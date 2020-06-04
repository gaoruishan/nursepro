package com.dhcc.nursepro.workarea.nurrecordnew.bean;

import java.io.Serializable;
import java.util.List;

public class RecModelListBean {

    /**
     * menuList : [{"ModelList":[{"getListMth":"","getValMth":"","guId":"6dca2de8907646b7b621d24364200f1d","linkModel":"","modelCode":"DHCNURBarthelLR","modelName":"Barthel评估","modelNum":"","modelType":"3","saveMth":"","wStatus":"0"},{"getListMth":"","getValMth":"","guId":"165a867cfbb94662ae844dad91a14835","linkModel":"","modelCode":"DHCNURDDFXPGJHLJLDLR","modelName":"跌倒评估","modelNum":"","modelType":"3","saveMth":"","wStatus":"0"},{"getListMth":"","getValMth":"","guId":"09579d9fef1e4cf0b3fc4ae9b99e8768","linkModel":"","modelCode":"DHCNURGLHTFXYSPGJHLCSLR","modelName":"管道评估","modelNum":"","modelType":"3","saveMth":"","wStatus":"0"}],"menuCode":"PGB","menuName":"评估表"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<MenuListBean> menuList;


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

    public List<MenuListBean> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuListBean> menuList) {
        this.menuList = menuList;
    }

    public static class MenuListBean {
        /**
         * ModelList : [{"getListMth":"","getValMth":"","guId":"6dca2de8907646b7b621d24364200f1d","linkModel":"","modelCode":"DHCNURBarthelLR","modelName":"Barthel评估","modelNum":"","modelType":"3","saveMth":"","wStatus":"0"},{"getListMth":"","getValMth":"","guId":"165a867cfbb94662ae844dad91a14835","linkModel":"","modelCode":"DHCNURDDFXPGJHLJLDLR","modelName":"跌倒评估","modelNum":"","modelType":"3","saveMth":"","wStatus":"0"},{"getListMth":"","getValMth":"","guId":"09579d9fef1e4cf0b3fc4ae9b99e8768","linkModel":"","modelCode":"DHCNURGLHTFXYSPGJHLCSLR","modelName":"管道评估","modelNum":"","modelType":"3","saveMth":"","wStatus":"0"}]
         * menuCode : PGB
         * menuName : 评估表
         */

        private String menuCode;
        private String menuName;
        private List<ModelListBean> ModelList;

        public String getMenuCode() {
            return menuCode;
        }

        public void setMenuCode(String menuCode) {
            this.menuCode = menuCode;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public List<ModelListBean> getModelList() {
            return ModelList;
        }

        public void setModelList(List<ModelListBean> ModelList) {
            this.ModelList = ModelList;
        }

        public static class ModelListBean implements Serializable {
            /**
             * getListMth :
             * getValMth :
             * guId : 6dca2de8907646b7b621d24364200f1d
             * linkModel :
             * modelCode : DHCNURBarthelLR
             * modelName : Barthel评估
             * modelNum :
             * modelType : 3
             * saveMth :
             * wStatus : 0
             */

            private String getListMth;
            private String getValMth;
            private String guId;
            private String linkModel;
            private String modelCode;
            private String modelName;
            private String modelNum;
            private String modelType;
            private String saveMth;
            private String wStatus;
            private String h5Url;

            public String getH5Url() {
                return h5Url == null ? "" : h5Url;
            }

            public void setH5Url(String h5Url) {
                this.h5Url = h5Url;
            }

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

            public String getGuId() {
                return guId;
            }

            public void setGuId(String guId) {
                this.guId = guId;
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

            public String getWStatus() {
                return wStatus;
            }

            public void setWStatus(String wStatus) {
                this.wStatus = wStatus;
            }
        }
    }
}
