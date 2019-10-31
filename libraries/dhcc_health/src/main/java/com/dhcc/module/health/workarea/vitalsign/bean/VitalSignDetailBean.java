package com.dhcc.module.health.workarea.vitalsign.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;
import java.util.Map;

public class VitalSignDetailBean extends CommResult {

    /**
     * endDate : 2018-07-25
     * msg : 成功
     * msgcode : 999999
     * stDate : 2018-03-01
     * status : 0
     * tempConfig : [{"code":"temperature","desc":"腋温"},{"code":"rectemperature","desc":"肛温"},{"code":"pulse","desc":"脉搏"},{"code":"breath","desc":"呼吸"},{"code":"sysPressure","desc":"收缩压"},{"code":"diaPressure","desc":"舒张压"},{"code":"degrBlood","desc":"血氧饱和度"},{"code":"heartbeat","desc":"心率"},{"code":"phyCooling","desc":"物理降温"},{"code":"painInten","desc":"疼痛评分"},{"code":"defFreq","desc":"大便次数"},{"code":"height","desc":"身高"},{"code":"weight","desc":"体重"},{"code":"uriVolume","desc":"尿 量"},{"code":"liquidOut","desc":"总出量"},{"code":"Barthel","desc":"Barthel评分"},{"code":"Bedsore","desc":"压疮风险评估"},{"code":"Fallrisk","desc":"跌倒风险评估"},{"code":"Fallbed","desc":"坠床风险评估"},{"code":"Item34_Title","desc":"空白栏1标题"},{"code":"Item34","desc":"空白栏1"},{"code":"Reason","desc":"未测原因"}]
     * tempDataList : [{"Barthel":"","Bedsore":"","Fallbed":"","Fallrisk":"","Item34":"","Item34_Title":"","Reason":"","breath":"","date":"2018-03-26","defFreq":"","degrBlood":"","diaPressure":"110","heartbeat":"","height":"","liquidOut":"","painInten":"","phyCooling":"","pulse":"","rectemperature":"","sysPressure":"120","temperature":"","time":"06:00","uriVolume":"","weight":""},{"Barthel":"","Bedsore":"","Fallbed":"","Fallrisk":"","Item34":"","Item34_Title":"","Reason":"","breath":"100","date":"2018-03-26","defFreq":"","degrBlood":"95","diaPressure":"100","heartbeat":"95","height":"","liquidOut":"","painInten":"","phyCooling":"","pulse":"100","rectemperature":"36","sysPressure":"100","temperature":"36","time":"10:00","uriVolume":"","weight":"56"}]
     */

    private String endDate;
    private String stDate;
    private List<TempConfigBean> tempConfig;
    private List<TempDataListBean> tempDataList;
    private Map map;

    public Map getMap(){
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    public List<TempConfigBean> getTempConfig() {
        return tempConfig;
    }

    public void setTempConfig(List<TempConfigBean> tempConfig) {
        this.tempConfig = tempConfig;
    }

    public List<TempDataListBean> getTempDataList() {
        return tempDataList;
    }

    public void setTempDataList(List<TempDataListBean> tempDataList) {
        this.tempDataList = tempDataList;
    }

    public static class TempConfigBean {
        /**
         * code : temperature
         * desc : 腋温
         */

        private String code;
        private String desc;

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
    }

    public static class TempDataListBean {
        /**
         * Barthel :
         * Bedsore :
         * Fallbed :
         * Fallrisk :
         * Item34 :
         * Item34_Title :
         * Reason :
         * breath :
         * date : 2018-03-26
         * defFreq :
         * degrBlood :
         * diaPressure : 110
         * heartbeat :
         * height :
         * liquidOut :
         * painInten :
         * phyCooling :
         * pulse :
         * rectemperature :
         * sysPressure : 120
         * temperature :
         * time : 06:00
         * uriVolume :
         * weight :
         */

        private String Barthel;
        private String Bedsore;
        private String Fallbed;
        private String Fallrisk;
        private String Item34;
        private String Item34_Title;
        private String Reason;
        private String breath;
        private String date;
        private String defFreq;
        private String degrBlood;
        private String diaPressure;
        private String heartbeat;
        private String height;
        private String liquidOut;
        private String painInten;
        private String phyCooling;
        private String pulse;
        private String rectemperature;
        private String sysPressure;
        private String temperature;
        private String time;
        private String uriVolume;
        private String weight;

        public String getBarthel() {
            return Barthel;
        }

        public void setBarthel(String Barthel) {
            this.Barthel = Barthel;
        }

        public String getBedsore() {
            return Bedsore;
        }

        public void setBedsore(String Bedsore) {
            this.Bedsore = Bedsore;
        }

        public String getFallbed() {
            return Fallbed;
        }

        public void setFallbed(String Fallbed) {
            this.Fallbed = Fallbed;
        }

        public String getFallrisk() {
            return Fallrisk;
        }

        public void setFallrisk(String Fallrisk) {
            this.Fallrisk = Fallrisk;
        }

        public String getItem34() {
            return Item34;
        }

        public void setItem34(String Item34) {
            this.Item34 = Item34;
        }

        public String getItem34_Title() {
            return Item34_Title;
        }

        public void setItem34_Title(String Item34_Title) {
            this.Item34_Title = Item34_Title;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getBreath() {
            return breath;
        }

        public void setBreath(String breath) {
            this.breath = breath;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDefFreq() {
            return defFreq;
        }

        public void setDefFreq(String defFreq) {
            this.defFreq = defFreq;
        }

        public String getDegrBlood() {
            return degrBlood;
        }

        public void setDegrBlood(String degrBlood) {
            this.degrBlood = degrBlood;
        }

        public String getDiaPressure() {
            return diaPressure;
        }

        public void setDiaPressure(String diaPressure) {
            this.diaPressure = diaPressure;
        }

        public String getHeartbeat() {
            return heartbeat;
        }

        public void setHeartbeat(String heartbeat) {
            this.heartbeat = heartbeat;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getLiquidOut() {
            return liquidOut;
        }

        public void setLiquidOut(String liquidOut) {
            this.liquidOut = liquidOut;
        }

        public String getPainInten() {
            return painInten;
        }

        public void setPainInten(String painInten) {
            this.painInten = painInten;
        }

        public String getPhyCooling() {
            return phyCooling;
        }

        public void setPhyCooling(String phyCooling) {
            this.phyCooling = phyCooling;
        }

        public String getPulse() {
            return pulse;
        }

        public void setPulse(String pulse) {
            this.pulse = pulse;
        }

        public String getRectemperature() {
            return rectemperature;
        }

        public void setRectemperature(String rectemperature) {
            this.rectemperature = rectemperature;
        }

        public String getSysPressure() {
            return sysPressure;
        }

        public void setSysPressure(String sysPressure) {
            this.sysPressure = sysPressure;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUriVolume() {
            return uriVolume;
        }

        public void setUriVolume(String uriVolume) {
            this.uriVolume = uriVolume;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
