package com.dhcc.nursepro.workarea.workareabean;

import com.base.commlibs.bean.CommBean;

import java.util.ArrayList;
import java.util.List;

public class MainConfigBean {
    /**
     * mainConfig : ["BEDMAP","VITALSIGN","EVENTS","ORDERSEARCH","ORDEREXECUTE","CHECK","LAB","OPERATION","LABOUT","DOSINGREVIEW","ALLOTBED","DOCORDERLIST","BLOOD","MILK","MOTHERBABYLINK"]
     * msg :
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<String> mainConfig;
    private List<ScreenPartsBean> screenParts;
    private String scantimes;

    public List<ScreenPartsBean> getScreenParts() {
        if (screenParts == null) {
            return new ArrayList<>();
        }
        return screenParts;
    }

    public void setScreenParts(List<ScreenPartsBean> screenParts) {
        this.screenParts = screenParts;
    }

    public String getScantimes(){
        return scantimes;
    }

    public void setScantimes(String scantimes){
        this.scantimes = scantimes;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMainConfig() {
        return mainConfig;
    }

    public void setMainConfig(List<String> mainConfig) {
        this.mainConfig = mainConfig;
    }

    public static class ScreenPartsBean {

        /**
         * keyCode : oecprDesc|长期!临时|Single
         * keyType :
         * keyValue :
         */

        private String keyCode;
        private String keyType;
        private String keyValue;
        private String danjuStr;
        private String keyDesc;
        private boolean commonKey;
        private List<CommBean> listBean;

        @Override
        public String toString() {
            return "ScreenPartsBean{" +
                    "keyCode='" + keyCode + '\'' +
                    ", keyType='" + keyType + '\'' +
                    ", keyValue='" + keyValue + '\'' +
                    ", danjuStr='" + danjuStr + '\'' +
                    ", keyDesc='" + keyDesc + '\'' +
                    ", commonKey=" + commonKey +
                    '}';
        }

        public boolean isCommonKey() {
            return commonKey;
        }

        public void setCommonKey(boolean commonKey) {
            this.commonKey = commonKey;
        }

        public String getDanjuStr() {
            return danjuStr == null ? "" : danjuStr;
        }

        public void setDanjuStr(String danjuStr) {
            this.danjuStr = danjuStr;
        }

        public String getKeyDesc() {
            return keyDesc == null ? "" : keyDesc;
        }

        public void setKeyDesc(String keyDesc) {
            this.keyDesc = keyDesc;
        }

        public List<CommBean> getListBean() {
            if (listBean == null) {
                return new ArrayList<>();
            }
            return listBean;
        }

        public void setListBean(List<CommBean> listBean) {
            this.listBean = listBean;
        }

        public String getKeyCode() {
            return keyCode;
        }

        public void setKeyCode(String keyCode) {
            this.keyCode = keyCode;
        }

        public String getKeyType() {
            return keyType;
        }

        public void setKeyType(String keyType) {
            this.keyType = keyType;
        }

        public String getKeyValue() {
            return keyValue;
        }

        public void setKeyValue(String keyValue) {
            this.keyValue = keyValue;
        }
    }
}
