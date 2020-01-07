package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean
 * <p>
 * author Q
 * Date: 2020/1/6
 * Time:15:53
 */
public class BloodTemDataBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * status : 0
     * tempConfig : [{"blank":"false","code":"temperature","desc":"体温（℃）","errorValueHightFrom":"43","errorValueLowTo":"34","normalValueRangFrom":"36.3","normalValueRangTo":"37.3","options":[""],"select":"false","symbol":["拒测","不升"],"times":["2","6","10","14","18","22"],"validate":"true","valueType":"N"},{"blank":"false","code":"breath","desc":"呼吸（次/分）","normalValueRangFrom":"12","normalValueRangTo":"24","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"true","valueType":""},{"blank":"false","code":"sysPressure","desc":"收缩压（mmHg）","normalValueRangFrom":"90","normalValueRangTo":"140","options":[""],"select":"false","times":["6","18"],"validate":"true","valueType":""},{"blank":"false","code":"diaPressure","desc":"舒张压（mmHg）","normalValueRangFrom":"60","normalValueRangTo":"90","options":[""],"select":"false","times":["6","18"],"validate":"true","valueType":""},{"blank":"false","code":"heartbeat","desc":"心率（次/分）","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"false","valueType":""},{"blank":"false","code":"height","desc":"身高（cm）","options":[""],"select":"false","symbol":["平车","卧床","轮椅"],"times":["14"],"validate":"false","valueType":""},{"blank":"false","code":"weight","desc":"体重（KG）","options":[""],"select":"false","symbol":["平车","卧床","轮椅","2¹/E ","1\u2076/E","1\u2075/E","1³/E "],"times":["14"],"validate":"false","valueType":"N"}]
     * tempList : [{"code":"temperature","value":""},{"code":"breath","value":""},{"code":"sysPressure","value":""},{"code":"diaPressure","value":""},{"code":"heartbeat","value":""},{"code":"height","value":""},{"code":"weight","value":""}]
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<TempConfigBean> tempConfig;
    private List<TempListBean> tempList;

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

    public List<TempConfigBean> getTempConfig() {
        return tempConfig;
    }

    public void setTempConfig(List<TempConfigBean> tempConfig) {
        this.tempConfig = tempConfig;
    }

    public List<TempListBean> getTempList() {
        return tempList;
    }

    public void setTempList(List<TempListBean> tempList) {
        this.tempList = tempList;
    }

    public static class TempConfigBean {
        /**
         * blank : false
         * code : temperature
         * desc : 体温（℃）
         * errorValueHightFrom : 43
         * errorValueLowTo : 34
         * normalValueRangFrom : 36.3
         * normalValueRangTo : 37.3
         * options : [""]
         * select : false
         * symbol : ["拒测","不升"]
         * times : ["2","6","10","14","18","22"]
         * validate : true
         * valueType : N
         */

        private String blank;
        private String code;
        private String desc;
        private String errorValueHightFrom;
        private String errorValueLowTo;
        private String normalValueRangFrom;
        private String normalValueRangTo;
        private String select;
        private String validate;
        private String valueType;
        private List<String> options;
        private List<String> symbol;
        private List<String> times;

        public String getBlank() {
            return blank;
        }

        public void setBlank(String blank) {
            this.blank = blank;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getErrorValueHightFrom() {
            return errorValueHightFrom;
        }

        public void setErrorValueHightFrom(String errorValueHightFrom) {
            this.errorValueHightFrom = errorValueHightFrom;
        }

        public String getErrorValueLowTo() {
            return errorValueLowTo;
        }

        public void setErrorValueLowTo(String errorValueLowTo) {
            this.errorValueLowTo = errorValueLowTo;
        }

        public String getNormalValueRangFrom() {
            return normalValueRangFrom;
        }

        public void setNormalValueRangFrom(String normalValueRangFrom) {
            this.normalValueRangFrom = normalValueRangFrom;
        }

        public String getNormalValueRangTo() {
            return normalValueRangTo;
        }

        public void setNormalValueRangTo(String normalValueRangTo) {
            this.normalValueRangTo = normalValueRangTo;
        }

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }

        public String getValidate() {
            return validate;
        }

        public void setValidate(String validate) {
            this.validate = validate;
        }

        public String getValueType() {
            return valueType;
        }

        public void setValueType(String valueType) {
            this.valueType = valueType;
        }

        public List<String> getOptions() {
            return options;
        }

        public void setOptions(List<String> options) {
            this.options = options;
        }

        public List<String> getSymbol() {
            return symbol;
        }

        public void setSymbol(List<String> symbol) {
            this.symbol = symbol;
        }

        public List<String> getTimes() {
            return times;
        }

        public void setTimes(List<String> times) {
            this.times = times;
        }
    }

    public static class TempListBean {
        /**
         * code : temperature
         * value :
         */

        private String code;
        private String value;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
