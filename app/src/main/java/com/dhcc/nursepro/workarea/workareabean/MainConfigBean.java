package com.dhcc.nursepro.workarea.workareabean;

import com.base.commlibs.bean.CommBean;

import java.util.ArrayList;
import java.util.List;

public class MainConfigBean {

    /**
     * mainList : [{"moduleCode":"BEDMAP","moduleDesc":"床位图"},{"moduleCode":"VITALSIGN","moduleDesc":"生命体征"},{"moduleCode":"EVENTS","moduleDesc":"事件管理"},{"moduleCode":"DOCORDERLIST","moduleDesc":"医嘱单"},{"moduleCode":"CHECK","moduleDesc":"检查报告"},{"moduleCode":"LAB","moduleDesc":"检验报告"},{"moduleCode":"LABOUT","moduleDesc":"检验打包"},{"moduleCode":"NURTOUR","moduleDesc":"护理巡视"},{"moduleCode":"DOSINGREVIEW","moduleDesc":"输液复核"},{"moduleCode":"DOCORDERLIST","moduleDesc":"手术申请"},{"moduleCode":"BLOOD","moduleDesc":"输血系统"}]
     * msg :
     * msgcode : 999999
     * scantimes : 2
     * screenParts : [{"commonKey":"false","danjuStr":"DefaultSee!PSD!BLD","keyCode":"phcinDesc","keyDesc":"用法","keyType":"Multiple","keyValue":"注射!IV!DV"},{"commonKey":"false","danjuStr":"DefaultSee!PSD!BLD","keyCode":"ordType","keyDesc":"类型","keyType":"Multiple","keyValue":"药品!检验!其他"},{"commonKey":"false","danjuStr":"DefaultSee!PSD!BLD","keyCode":"oecprDesc","keyDesc":"优先级","keyType":"Single","keyValue":"长期!临时"},{"commonKey":"true","danjuStr":"DefaultSee!PSD!BLD","keyCode":"exeFlag","keyDesc":"执行状态","keyType":"Single","keyValue":"未执行!已执行"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String scantimes;
    private String status;
    private List<MainListBean> mainList;
    private List<ScreenPartsBean> screenParts;

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

    public String getScantimes() {
        return scantimes;
    }

    public void setScantimes(String scantimes) {
        this.scantimes = scantimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MainListBean> getMainList() {
        return mainList;
    }

    public void setMainList(List<MainListBean> mainList) {
        this.mainList = mainList;
    }

    public List<ScreenPartsBean> getScreenParts() {
        return screenParts;
    }

    public void setScreenParts(List<ScreenPartsBean> screenParts) {
        this.screenParts = screenParts;
    }

    public static class MainListBean {
        /**
         * moduleCode : BEDMAP
         * moduleDesc : 床位图
         */

        private String moduleCode;
        private String moduleDesc;

        public String getModuleCode() {
            return moduleCode;
        }

        public void setModuleCode(String moduleCode) {
            this.moduleCode = moduleCode;
        }

        public String getModuleDesc() {
            return moduleDesc;
        }

        public void setModuleDesc(String moduleDesc) {
            this.moduleDesc = moduleDesc;
        }
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
