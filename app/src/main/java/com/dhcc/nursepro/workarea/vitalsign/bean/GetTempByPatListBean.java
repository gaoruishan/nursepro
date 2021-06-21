package com.dhcc.nursepro.workarea.vitalsign.bean;

import java.util.List;

public class GetTempByPatListBean {

    private String curDate;
    private String curTime;
    private String msg;
    private String msgcode;
    private List<PatListBean> patList;
    private String status;
    private List<TempConfigBean> tempConfig;

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
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

    public List<PatListBean> getPatList() {
        return patList;
    }

    public void setPatList(List<PatListBean> patList) {
        this.patList = patList;
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

    public static class PatListBean {
        private String bedCode;
        private String patName;
        private String regNo;
        private String episodeId;
        private String selectedBed;
        private List<TempListBean> tempList;

        public PatListBean(String patName) {
            this.patName = patName;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getPatName() {
            return patName;
        }

        public void setPatName(String patName) {
            this.patName = patName;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
        }

        public String getSelectedBed() {
            return selectedBed;
        }

        public void setSelectedBed(String selectedBed) {
            this.selectedBed = selectedBed;
        }

        public List<TempListBean> getTempList() {
            return tempList;
        }

        public void setTempList(List<TempListBean> tempList) {
            this.tempList = tempList;
        }

        public static class TempListBean {
            private String code;
            private String needFlag;
            private String value;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getNeedFlag() {
                return needFlag;
            }

            public void setNeedFlag(String needFlag) {
                this.needFlag = needFlag;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class TempConfigBean {
        private String blank;
        private String code;
        private String desc;
        private String errorValueHightFrom;
        private String errorValueLowTo;
        private String normalValueRangFrom;
        private String normalValueRangTo;
        private List<String> options;
        private String select;
        private List<String> times;
        private String validate;
        private List<String> symbol;

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

        public List<String> getOptions() {
            return options;
        }

        public void setOptions(List<String> options) {
            this.options = options;
        }

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }

        public List<String> getTimes() {
            return times;
        }

        public void setTimes(List<String> times) {
            this.times = times;
        }

        public String getValidate() {
            return validate;
        }

        public void setValidate(String validate) {
            this.validate = validate;
        }

        public List<String> getSymbol() {
            return symbol;
        }

        public void setSymbol(List<String> symbol) {
            this.symbol = symbol;
        }
    }
}
