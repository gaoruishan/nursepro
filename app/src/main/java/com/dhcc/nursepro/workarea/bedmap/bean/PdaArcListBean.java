package com.dhcc.nursepro.workarea.bedmap.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.bean
 * <p>
 * author Q
 * Date: 2020/10/26
 * Time:17:03
 */
public class PdaArcListBean {
    /**
     * arcItemList : [{"itemDesc":"一次性注射器[10ml]","itemDur":"","itemDurRowid":"","itemFreq":"","itemFreqRowid":"","itemId":"1482||1","itemPrice":"0.8000"},{"itemDesc":"静脉输液","itemDur":"1天","itemDurRowid":"1","itemFreq":"St","itemFreqRowid":"27","itemId":"7809||1","itemPrice":"2.0000"},{"itemDesc":"静脉采血","itemDur":"1天","itemDurRowid":"1","itemFreq":"St","itemFreqRowid":"27","itemId":"7820||1","itemPrice":"1.5000"},{"itemDesc":"Ⅰ级护理","itemDur":"","itemDurRowid":"","itemFreq":"","itemFreqRowid":"","itemId":"11588||1","itemPrice":"7.0000"},{"itemDesc":"Ⅱ级护理","itemDur":"","itemDurRowid":"","itemFreq":"","itemFreqRowid":"","itemId":"11586||1","itemPrice":"8.0000"}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<ArcItemListBean> arcItemList;

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

    public List<ArcItemListBean> getArcItemList() {
        return arcItemList;
    }

    public void setArcItemList(List<ArcItemListBean> arcItemList) {
        this.arcItemList = arcItemList;
    }

    public static class ArcItemListBean {
        /**
         * itemDesc : 一次性注射器[10ml]
         * itemDur :
         * itemDurRowid :
         * itemFreq :
         * itemFreqRowid :
         * itemId : 1482||1
         * itemPrice : 0.8000
         */

        private String itemDesc;
        private String itemDur;
        private String itemDurRowid;
        private String itemFreq;
        private String itemFreqRowid;
        private String itemId;
        private String itemPrice;
        private int itemNum = 1;

        public int getItemNum() {
            return itemNum;
        }

        public void setItemNum(int itemNum) {
            this.itemNum = itemNum;
        }

        public String getItemDesc() {
            return itemDesc;
        }

        public void setItemDesc(String itemDesc) {
            this.itemDesc = itemDesc;
        }

        public String getItemDur() {
            return itemDur;
        }

        public void setItemDur(String itemDur) {
            this.itemDur = itemDur;
        }

        public String getItemDurRowid() {
            return itemDurRowid;
        }

        public void setItemDurRowid(String itemDurRowid) {
            this.itemDurRowid = itemDurRowid;
        }

        public String getItemFreq() {
            return itemFreq;
        }

        public void setItemFreq(String itemFreq) {
            this.itemFreq = itemFreq;
        }

        public String getItemFreqRowid() {
            return itemFreqRowid;
        }

        public void setItemFreqRowid(String itemFreqRowid) {
            this.itemFreqRowid = itemFreqRowid;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(String itemPrice) {
            this.itemPrice = itemPrice;
        }
    }
}
