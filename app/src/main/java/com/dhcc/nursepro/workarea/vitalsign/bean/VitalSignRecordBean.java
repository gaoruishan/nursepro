package com.dhcc.nursepro.workarea.vitalsign.bean;

import java.util.List;

public class VitalSignRecordBean {
    /**
     * Attention : 提醒:今日需要测血压
     * msg :
     * msgcode : 999999
     * status : 0
     * tempConfig : [{"blank":"false","code":"temperature","desc":"体温","errorValueHightFrom":"43","errorValueLowTo":"34","normalValueRangFrom":"36","normalValueRangTo":"37","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"true","valueType":"N"},{"blank":"false","code":"pulse","desc":"脉搏","normalValueRangFrom":"60","normalValueRangTo":"100","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"true","valueType":"N"},{"blank":"false","code":"breath","desc":"呼吸","normalValueRangFrom":"16","normalValueRangTo":"20","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"true","valueType":"N"},{"blank":"false","code":"sysPressure","desc":"收缩压","normalValueRangFrom":"89","normalValueRangTo":"141","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"true","valueType":"N"},{"blank":"false","code":"diaPressure","desc":"舒张压","normalValueRangFrom":"59","normalValueRangTo":"91","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"true","valueType":"N"},{"blank":"false","code":"defFreq","desc":"大便","options":[""],"select":"false","symbol":["\u203b","/E","☆"],"times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"uriVolume","desc":"尿量次","options":[""],"select":"false","symbol":["*"],"times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"weight","desc":"体重","options":[""],"select":"false","symbol":["平车","卧床","轮椅"],"times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"heartbeat","desc":"心率","normalValueRangFrom":"60","normalValueRangTo":"100","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"true","valueType":"N"},{"blank":"false","code":"phyCooling","desc":"物理降温","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"painInten","desc":"疼痛","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"Fallbed","desc":"缓解疼痛","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"rectemperature","desc":"肛温","normalValueRangFrom":"36.3","normalValueRangTo":"37.2","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"true","valueType":"N"},{"blank":"false","code":"Reason","desc":"未测原因","options":["不在","外出","拒测","其他"],"select":"true","times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"drainage","desc":"引流量","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"GasDec","desc":"胃肠减压","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"BP","desc":"BP","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"Fallrisk","desc":"跌倒/坠床风险","options":["是","否"],"select":"true","times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"Bedsore","desc":"压疮风险","options":["是","否"],"select":"true","times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"Barthel","desc":"VTE风险","options":["是","否"],"select":"true","times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"liquidln","desc":"总入量","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"liquidOut","desc":"总出量","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"degrBlood","desc":"血氧饱和度","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"JCDXL","desc":"基础代谢率","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"height","desc":"身高","options":[""],"select":"false","symbol":["平车","卧床","轮椅"],"times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"IncDra","desc":"切口引流","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"drugName","desc":"执行日期","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"BCGDate","desc":"卡介苗批号","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"umbilicalCord","desc":"脐带","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"aurigo","desc":"黄疸","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"false","code":"breaMilk","desc":"母乳","options":[""],"select":"false","symbol":["√"],"times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"cowMilk","desc":"牛乳","options":[""],"select":"false","symbol":["√"],"times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"bellies","desc":"腹围","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"N"},{"blank":"true","code":"Item34","desc":"空白栏1","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"Item34_Title","desc":"空白栏1标题","options":["引流量(ml)","心包引流(ml)","纵膈引流(ml)","心包纵膈引流(ml)","胸腔引流(ml)","胸腔引流左(ml)","胸腔引流右(ml)","腰大池引流(ml)","T管引流(ml)","腹腔引流(ml)","伤口引流(ml)","胃肠减压(ml)","脑室引流(ml)","腹膜后引流(ml)","耻骨后引流(ml)","肾造瘘左(ml)","肾造瘘右(ml)","膀胱造瘘(ml)","回肠造瘘(ml)","回肠引流(ml)","左侧输尿管引流(ml)","右侧输尿管引流(ml)","盆腔引流(ml)","大便量(ml)","呕吐量(ml)","汗液(ml)","腹水(ml)","胸水(ml)","月经量(ml)","超虑量(ml)","腹膜透析超滤量(ml)","阴道出血量(ml)","头部引流(ml)","腰穿压力(mmH2O)","CVP(mmHg)","肘静脉压(cmH2O)","有创动脉血压(mmHg)","腹围(cm)","左上肢血压(mmHg)","左下肢血压(mmHg)","右上肢血压(mmHg)","右下肢血压(mmHg)","CRRT(ml)","腿围左（上/下 cm）","胃肠减压"],"select":"true","times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"true","code":"Item35","desc":"空白栏2","options":[""],"select":"false","times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"},{"blank":"false","code":"Item35_Title","desc":"空白栏2标题","options":["引流量(ml)","心包引流(ml)","纵膈引流(ml)","心包纵膈引流(ml)","胸腔引流(ml)","胸腔引流左(ml)","胸腔引流右(ml)","腰大池引流(ml)","T管引流(ml)","腹腔引流(ml)","伤口引流(ml)","胃肠减压(ml)","脑室引流(ml)","腹膜后引流(ml)","耻骨后引流(ml)","肾造瘘左(ml)","肾造瘘右(ml)","膀胱造瘘(ml)","回肠造瘘(ml)","回肠引流(ml)","左侧输尿管引流(ml)","右侧输尿管引流(ml)","盆腔引流(ml)","大便量(ml)","呕吐量(ml)","汗液(ml)","腹水(ml)","胸水(ml)","月经量(ml)","超虑量(ml)","腹膜透析超滤量(ml)","阴道出血量(ml)","头部引流(ml)","腰穿压力(mmH2O)","CVP(mmHg)","肘静脉压(cmH2O)","有创动脉血压(mmHg)","腹围(cm)","左上肢血压(mmHg)","左下肢血压(mmHg)","右上肢血压(mmHg)","右下肢血压(mmHg)","CRRT(ml)","腿围左（上/下 cm）"],"select":"true","times":["3","7","11","15","19","23"],"validate":"false","valueType":"T"}]
     * tempList : [{"code":"temperature","value":""},{"code":"pulse","value":""},{"code":"breath","value":""},{"code":"sysPressure","value":""},{"code":"diaPressure","value":""},{"code":"defFreq","value":""},{"code":"uriVolume","value":""},{"code":"weight","value":""},{"code":"heartbeat","value":""},{"code":"phyCooling","value":""},{"code":"painInten","value":""},{"code":"Fallbed","value":""},{"code":"rectemperature","value":""},{"code":"Reason","value":"不在"},{"code":"drainage","value":""},{"code":"GasDec","value":""},{"code":"BP","value":""},{"code":"Fallrisk","value":""},{"code":"Bedsore","value":""},{"code":"Barthel","value":""},{"code":"liquidln","value":""},{"code":"liquidOut","value":""},{"code":"degrBlood","value":""},{"code":"JCDXL","value":""},{"code":"height","value":""},{"code":"IncDra","value":""},{"code":"drugName","value":""},{"code":"BCGDate","value":""},{"code":"umbilicalCord","value":""},{"code":"aurigo","value":""},{"code":"breaMilk","value":""},{"code":"cowMilk","value":""},{"code":"bellies","value":""},{"code":"Item34","value":""},{"code":"Item34_Title","value":""},{"code":"Item35","value":""},{"code":"Item35_Title","value":""}]
     */

    private String Attention;
    private String msg;
    private String msgcode;
    private String status;
    private List<TempConfigBean> tempConfig;
    private List<TempListBean> tempList;

    public String getAttention() {
        return Attention;
    }

    public void setAttention(String Attention) {
        this.Attention = Attention;
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
         * desc : 体温
         * errorValueHightFrom : 43
         * errorValueLowTo : 34
         * normalValueRangFrom : 36
         * normalValueRangTo : 37
         * options : [""]
         * select : false
         * times : ["3","7","11","15","19","23"]
         * validate : true
         * valueType : N
         * symbol : ["\u203b","/E","☆"]
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
