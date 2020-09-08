package com.dhcc.module.nurse.bloodsugar.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.nurse.bloodsugar.bean
 * <p>
 * author Q
 * Date: 2020/8/19
 * Time:17:30
 */
public class BloodSugarValueAndItemBean  extends CommResult {
    private String curDateTime;

    public String getCurDateTime() {
        return curDateTime;
    }

    public void setCurDateTime(String curDateTime) {
        this.curDateTime = curDateTime;
    }

    private List<SugarListBean> sugarList;

    public List<SugarListBean> getSugarList() {
        return sugarList;
    }

    public void setSugarList(List<SugarListBean> sugarList) {
        this.sugarList = sugarList;
    }

    public static class SugarListBean {
        /**
         * blank : false
         * code : FBS
         * desc : 空腹血糖
         * errorValueHightFrom : 20
         * errorValueLowTo : 0
         * normalValueRangFrom : 3.8
         * normalValueRangTo : 7.8
         * options : ["请假","外出","拒试"]
         * rowId : 101
         * select : true
         * symbol : ["请假","外出","拒试"]
         * times : ["14"]
         * validate : true
         * value :
         * valueType :
         */

        private String blank;
        private String code;
        private String desc;
        private String errorValueHightFrom;
        private String errorValueLowTo;
        private String normalValueRangFrom;
        private String normalValueRangTo;
        private String rowId;
        private String sugarRowId;
        private String select;
        private String validate;
        private String value;
        private String valueType;
        private List<String> options;
        private List<String> symbol;
        private List<String> times;

        public String getSugarRowId() {
            return sugarRowId;
        }

        public void setSugarRowId(String sugarRowId) {
            this.sugarRowId = sugarRowId;
        }

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

        public String getRowId() {
            return rowId;
        }

        public void setRowId(String rowId) {
            this.rowId = rowId;
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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
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
}
