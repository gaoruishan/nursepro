package com.dhcc.nursepro.workarea.shift.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.shift.bean
 * <p>
 * author Q
 * Date: 2019/11/12
 * Time:15:41
 */
public class ShiftBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * shiftData : [{"data":[{"col":"3","count":"0","itemDR":"7","itemName":"入院","row":"1"},{"col":"4","count":"0","itemDR":"8","itemName":"转入","row":"1"},{"col":"2","count":"0","itemDR":"9","itemName":"转出","row":"2"},{"col":"1","count":"0","itemDR":"10","itemName":"出院","row":"2"},{"col":"3","count":"0","itemDR":"12","itemName":"分娩完成","row":"3"},{"col":"2","count":"0","itemDR":"13","itemName":"今日手术","row":"4"},{"col":"3","count":"0","itemDR":"14","itemName":"明日手术","row":"4"},{"col":"4","count":"0","itemDR":"20","itemName":"压疮风险","row":"4"},{"col":"1","count":"5","itemDR":"29","itemName":"原有","row":"1"},{"col":"1","count":"5","itemDR":"30","itemName":"现有","row":"5"},{"col":"2","count":"0","itemDR":"31","itemName":"新入","row":"1"},{"col":"3","count":"0","itemDR":"32","itemName":"死亡","row":"2"},{"col":"2","count":"0","itemDR":"33","itemName":"病危","row":"3"},{"col":"4","count":"0","itemDR":"34","itemName":"病重","row":"3"},{"col":"4","count":"0","itemDR":"35","itemName":"特级护理","row":"2"},{"col":"1","count":"0","itemDR":"36","itemName":"一级护理","row":"3"},{"col":"1","count":"0","itemDR":"37","itemName":"新生儿","row":"4"}],"name":"白班","sign":"","timeRange":"08:00~17:00","type":"1||4"},{"data":[{"col":"3","count":"0","itemDR":"7","itemName":"入院","row":"1"},{"col":"4","count":"0","itemDR":"8","itemName":"转入","row":"1"},{"col":"2","count":"0","itemDR":"9","itemName":"转出","row":"2"},{"col":"1","count":"0","itemDR":"10","itemName":"出院","row":"2"},{"col":"3","count":"0","itemDR":"12","itemName":"分娩完成","row":"3"},{"col":"2","count":"0","itemDR":"13","itemName":"今日手术","row":"4"},{"col":"3","count":"0","itemDR":"14","itemName":"明日手术","row":"4"},{"col":"4","count":"0","itemDR":"20","itemName":"压疮风险","row":"4"},{"col":"1","count":"5","itemDR":"29","itemName":"原有","row":"1"},{"col":"1","count":"5","itemDR":"30","itemName":"现有","row":"5"},{"col":"2","count":"0","itemDR":"31","itemName":"新入","row":"1"},{"col":"3","count":"0","itemDR":"32","itemName":"死亡","row":"2"},{"col":"2","count":"0","itemDR":"33","itemName":"病危","row":"3"},{"col":"4","count":"0","itemDR":"34","itemName":"病重","row":"3"},{"col":"4","count":"0","itemDR":"35","itemName":"特级护理","row":"2"},{"col":"1","count":"0","itemDR":"36","itemName":"一级护理","row":"3"},{"col":"1","count":"0","itemDR":"37","itemName":"新生儿","row":"4"}],"name":"小夜班","sign":"","timeRange":"17:01~01:00","type":"1||5"},{"data":[{"col":"3","count":"0","itemDR":"7","itemName":"入院","row":"1"},{"col":"4","count":"0","itemDR":"8","itemName":"转入","row":"1"},{"col":"2","count":"0","itemDR":"9","itemName":"转出","row":"2"},{"col":"1","count":"0","itemDR":"10","itemName":"出院","row":"2"},{"col":"3","count":"0","itemDR":"12","itemName":"分娩完成","row":"3"},{"col":"2","count":"0","itemDR":"13","itemName":"今日手术","row":"4"},{"col":"3","count":"0","itemDR":"14","itemName":"明日手术","row":"4"},{"col":"4","count":"0","itemDR":"20","itemName":"压疮风险","row":"4"},{"col":"1","count":"5","itemDR":"29","itemName":"原有","row":"1"},{"col":"1","count":"0","itemDR":"30","itemName":"现有","row":"5"},{"col":"2","count":"0","itemDR":"31","itemName":"新入","row":"1"},{"col":"3","count":"0","itemDR":"32","itemName":"死亡","row":"2"},{"col":"2","count":"0","itemDR":"33","itemName":"病危","row":"3"},{"col":"4","count":"0","itemDR":"34","itemName":"病重","row":"3"},{"col":"4","count":"0","itemDR":"35","itemName":"特级护理","row":"2"},{"col":"1","count":"0","itemDR":"36","itemName":"一级护理","row":"3"},{"col":"1","count":"0","itemDR":"37","itemName":"新生儿","row":"4"}],"name":"大夜班","sign":"","timeRange":"01:01~07:59","type":"1||6"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<ShiftDataBean> shiftData;

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

    public List<ShiftDataBean> getShiftData() {
        return shiftData;
    }

    public void setShiftData(List<ShiftDataBean> shiftData) {
        this.shiftData = shiftData;
    }

    public static class ShiftDataBean {
        /**
         * data : [{"col":"3","count":"0","itemDR":"7","itemName":"入院","row":"1"},{"col":"4","count":"0","itemDR":"8","itemName":"转入","row":"1"},{"col":"2","count":"0","itemDR":"9","itemName":"转出","row":"2"},{"col":"1","count":"0","itemDR":"10","itemName":"出院","row":"2"},{"col":"3","count":"0","itemDR":"12","itemName":"分娩完成","row":"3"},{"col":"2","count":"0","itemDR":"13","itemName":"今日手术","row":"4"},{"col":"3","count":"0","itemDR":"14","itemName":"明日手术","row":"4"},{"col":"4","count":"0","itemDR":"20","itemName":"压疮风险","row":"4"},{"col":"1","count":"5","itemDR":"29","itemName":"原有","row":"1"},{"col":"1","count":"5","itemDR":"30","itemName":"现有","row":"5"},{"col":"2","count":"0","itemDR":"31","itemName":"新入","row":"1"},{"col":"3","count":"0","itemDR":"32","itemName":"死亡","row":"2"},{"col":"2","count":"0","itemDR":"33","itemName":"病危","row":"3"},{"col":"4","count":"0","itemDR":"34","itemName":"病重","row":"3"},{"col":"4","count":"0","itemDR":"35","itemName":"特级护理","row":"2"},{"col":"1","count":"0","itemDR":"36","itemName":"一级护理","row":"3"},{"col":"1","count":"0","itemDR":"37","itemName":"新生儿","row":"4"}]
         * name : 白班
         * sign :
         * timeRange : 08:00~17:00
         * type : 1||4
         */

        private String name;
        private String sign;
        private String timeRange;
        private String type;
        private List<DataBean> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimeRange() {
            return timeRange;
        }

        public void setTimeRange(String timeRange) {
            this.timeRange = timeRange;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * col : 3
             * count : 0
             * itemDR : 7
             * itemName : 入院
             * row : 1
             */

            private String col;
            private String count;
            private String itemDR;
            private String itemName;
            private String row;

            public String getCol() {
                return col;
            }

            public void setCol(String col) {
                this.col = col;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getItemDR() {
                return itemDR;
            }

            public void setItemDR(String itemDR) {
                this.itemDR = itemDR;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getRow() {
                return row;
            }

            public void setRow(String row) {
                this.row = row;
            }
        }
    }
}
