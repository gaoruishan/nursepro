package com.dhcc.nursepro.workarea.nurrecord.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurrecord.bean
 * <p>
 * author Q
 * Date: 2018/12/20
 * Time:17:25
 */
public class NurRecordModelListBean {
    /**
     * menuList : [{"ModelList":[{"linkModel":"DHCNURPGD_AZRCSHNLPDHH","modelCode":"DHCNUR2","modelName":"外科危重护理记录表格(作废)","modelType":"3","wStatus":"0"},{"linkModel":"","modelCode":"DHCNUR27_1","modelName":"护理记录单","modelType":"1","wStatus":"0"}],"menuCode":"JLD","menuName":"记录单"},{"ModelList":[{"linkModel":"","modelCode":"DHCNURFX_RYPG","modelName":"复兴入院评估单","modelType":"2","wStatus":"0"},{"linkModel":"","modelCode":"DHCNURANHUI14","modelName":"通用入院评估单(省立)","modelType":"2","wStatus":"0"}],"menuCode":"PGD","menuName":"评估单"}]
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
         * ModelList : [{"linkModel":"DHCNURPGD_AZRCSHNLPDHH","modelCode":"DHCNUR2","modelName":"外科危重护理记录表格(作废)","modelType":"3","wStatus":"0"},{"linkModel":"","modelCode":"DHCNUR27_1","modelName":"护理记录单","modelType":"1","wStatus":"0"}]
         * menuCode : JLD
         * menuName : 记录单
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

        public static class ModelListBean {
            /**
             * linkModel : DHCNURPGD_AZRCSHNLPDHH
             * modelCode : DHCNUR2
             * modelName : 外科危重护理记录表格(作废)
             * modelType : 3
             * wStatus : 0
             */

            private String linkModel;
            private String modelCode;
            private String modelName;
            private String modelType;
            private String wStatus;

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

            public String getModelType() {
                return modelType;
            }

            public void setModelType(String modelType) {
                this.modelType = modelType;
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
