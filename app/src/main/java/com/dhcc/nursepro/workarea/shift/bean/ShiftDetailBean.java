package com.dhcc.nursepro.workarea.shift.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.shift.bean
 * <p>
 * author Q
 * Date: 2019/11/12
 * Time:17:31
 */
public class ShiftDetailBean {
    /**
     * detailList : [{"bedNo":"36","content":"","datasrc":"系统带入","diagnois":"肾性高血压","name":"住院单测试","reason":"病危"},{"bedNo":"01","content":"","datasrc":"系统带入","diagnois":"咳嗽病,风证类,非ICD诊断,tt,肩胛区结缔组织良性肿瘤,哒哒哒哒哒哒多多多多","name":"秦海贤","reason":"病危"},{"bedNo":"15","content":"","datasrc":"系统带入","diagnois":"霍乱，由于O1群霍乱弧菌，埃尔托生物型所致,便秘","name":"myh0212","reason":"一级护理"},{"bedNo":"22","content":"","datasrc":"系统带入","diagnois":"高血压2级","name":"住院测试","reason":"一级护理"},{"bedNo":"08","content":"","datasrc":"系统带入","diagnois":"功能性咳嗽","name":"第二也是我","reason":"一级护理"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<DetailListBean> detailList;

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

    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public static class DetailListBean {
        /**
         * bedNo : 36
         * content :
         * datasrc : 系统带入
         * diagnois : 肾性高血压
         * name : 住院单测试
         * reason : 病危
         */

        private String bedNo;
        private String content;
        private String datasrc;
        private String diagnois;
        private String name;
        private String reason;

        public String getBedNo() {
            return bedNo;
        }

        public void setBedNo(String bedNo) {
            this.bedNo = bedNo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDatasrc() {
            return datasrc;
        }

        public void setDatasrc(String datasrc) {
            this.datasrc = datasrc;
        }

        public String getDiagnois() {
            return diagnois;
        }

        public void setDiagnois(String diagnois) {
            this.diagnois = diagnois;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
