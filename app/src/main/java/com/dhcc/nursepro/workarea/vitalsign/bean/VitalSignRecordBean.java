package com.dhcc.nursepro.workarea.vitalsign.bean;

import java.util.List;

public class VitalSignRecordBean {

    /**
     * msg :
     * msgcode : 999999
     * status : 0
     * tempConfig : [{"blank":"false","code":"temperature","desc":"腋温（℃）","errorValueHightFrom":"43","errorValueLowTo":"34","normalValueRangFrom":"36.3","normalValueRangTo":"37.3","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"true","valueType":"N"},{"blank":"false","code":"rectemperature","desc":"肛温（℃）","normalValueRangFrom":"36.3","normalValueRangTo":"37.2","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"true","valueType":"N"},{"blank":"false","code":"pulse","desc":"脉搏（次/分）","normalValueRangFrom":"60","normalValueRangTo":"100","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"true","valueType":"N"},{"blank":"false","code":"breath","desc":"呼吸（次/分）","normalValueRangFrom":"12","normalValueRangTo":"24","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"true","valueType":"N"},{"blank":"false","code":"sysPressure","desc":"收缩压（mmHg）","normalValueRangFrom":"90","normalValueRangTo":"140","options":[""],"select":"false","times":["6","18"],"validate":"true","valueType":"N"},{"blank":"false","code":"diaPressure","desc":"舒张压（mmHg）","normalValueRangFrom":"60","normalValueRangTo":"90","options":[""],"select":"false","times":["6","18"],"validate":"true","valueType":"N"},{"blank":"false","code":"degrBlood","desc":"血氧饱和度","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"false","valueType":"T"},{"blank":"false","code":"heartbeat","desc":"心率（次/分）","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"false","valueType":"N"},{"blank":"false","code":"phyCooling","desc":"物理降温(℃)","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"false","valueType":"N"},{"blank":"false","code":"painInten","desc":"疼痛评分","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"false","valueType":"N"},{"blank":"false","code":"defFreq","desc":"大便次数（次）","options":[""],"select":"false","symbol":["\u203b","/E"],"times":["14"],"validate":"false","valueType":"T"},{"blank":"false","code":"height","desc":"身高（cm）","options":[""],"select":"false","symbol":["平车","卧床","轮椅"],"times":["14"],"validate":"false","valueType":"T"},{"blank":"false","code":"weight","desc":"体重（KG）","options":[""],"select":"false","symbol":["平车","卧床","轮椅"],"times":["14"],"validate":"false","valueType":"N"},{"blank":"false","code":"uriVolume","desc":"尿 量（ml）","options":[""],"select":"false","times":["14"],"validate":"false","valueType":"N"},{"blank":"false","code":"liquidOut","desc":"总出量（ml）","options":[""],"select":"false","times":["14"],"validate":"false","valueType":"T"},{"blank":"false","code":"Barthel","desc":"Barthel评分","options":[""],"select":"false","times":["14"],"validate":"false","valueType":"T"},{"blank":"false","code":"Bedsore","desc":"压疮风险评估","options":[""],"select":"false","times":["14"],"validate":"false","valueType":"T"},{"blank":"false","code":"Fallrisk","desc":"跌倒风险评估","options":[""],"select":"false","times":["14"],"validate":"false","valueType":"T"},{"blank":"false","code":"Fallbed","desc":"坠床风险评估","options":["有","无"],"select":"true","times":["14"],"validate":"false","valueType":"T"},{"blank":"false","code":"Item34_Title","desc":"空白栏1标题","options":["引流量(ml)","心包引流(ml)","纵膈引流(ml)","心包纵膈引流(ml)","胸腔引流(ml)","胸腔引流左(ml)","胸腔引流右(ml)","腰大池引流(ml)","T管引流(ml)","腹腔引流(ml)","伤口引流(ml)","胃肠减压(ml)","脑室引流(ml)","腹膜后引流(ml)","耻骨后引流(ml)","肾造瘘左(ml)","肾造瘘右(ml)","膀胱造瘘(ml)","回肠造瘘(ml)","回肠引流(ml)","左侧输尿管引流(ml)","右侧输尿管引流(ml)","盆腔引流(ml)","大便量(ml)","呕吐量(ml)","汗液(ml)","腹水(ml)","胸水(ml)","月经量(ml)","超虑量(ml)","腹膜透析超滤量(ml)","阴道出血量(ml)","头部引流(ml)","腰穿压力(mmH2O)","CVP(mmHg)","肘静脉压(cmH2O)","有创动脉血压(mmHg)","腹围(cm)","左上肢血压(mmHg)","左下肢血压(mmHg)","右上肢血压(mmHg)","右下肢血压(mmHg)","CRRT(ml)","腿围左（上/下 cm）"],"select":"true","times":["2","6","10","14","18","22"],"validate":"false","valueType":"T"},{"blank":"true","code":"Item34","desc":"空白栏1","options":[""],"select":"false","times":["2","6","10","14","18","22"],"validate":"false","valueType":"T"},{"blank":"false","code":"Reason","desc":"未测原因","options":["拒测","外出","请假","血透","检查"],"select":"true","times":["2","6","10","14","18","22"],"validate":"false","valueType":"T"}]
     * tempList : [{"code":"temperature","value":""},{"code":"rectemperature","value":""},{"code":"pulse","value":""},{"code":"breath","value":""},{"code":"sysPressure","value":"80"},{"code":"diaPressure","value":"100"},{"code":"degrBlood","value":""},{"code":"heartbeat","value":""},{"code":"phyCooling","value":""},{"code":"painInten","value":""},{"code":"defFreq","value":""},{"code":"height","value":""},{"code":"weight","value":""},{"code":"uriVolume","value":""},{"code":"liquidOut","value":""},{"code":"Barthel","value":""},{"code":"Bedsore","value":""},{"code":"Fallrisk","value":""},{"code":"Fallbed","value":""},{"code":"Item34_Title","value":""},{"code":"Item34","value":""},{"code":"Reason","value":""}]
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
         * desc : 腋温（℃）
         * errorValueHightFrom : 43
         * errorValueLowTo : 34
         * normalValueRangFrom : 36.3
         * normalValueRangTo : 37.3
         * options : [""]
         * select : false
         * times : ["2","6","10","14","18","22"]
         * validate : true
         * valueType : N
         * symbol : ["\u203b","/E"]
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
        private List<String> times;
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

        public List<String> getTimes() {
            return times;
        }

        public void setTimes(List<String> times) {
            this.times = times;
        }

        public List<String> getSymbol() {
            return symbol;
        }

        public void setSymbol(List<String> symbol) {
            this.symbol = symbol;
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
