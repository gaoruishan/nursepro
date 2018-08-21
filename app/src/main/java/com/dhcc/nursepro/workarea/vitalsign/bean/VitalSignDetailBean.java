package com.dhcc.nursepro.workarea.vitalsign.bean;

import java.util.List;

public class VitalSignDetailBean {
    /**
     * endDate : 2018-08-12
     * msg : 成功
     * msgcode : 999999
     * stDate : 2018-03-01
     * status : 0
     * tempDataList : [{"Barthel":"","Bedsore":"","Fallbed":"","Fallrisk":"","Item34":"","Item34_Title":"","Reason":"","breath":"","date":"2018-03-26","defFreq":"","degrBlood":"","diaPressure":"110","heartbeat":"","height":"","liquidOut":"","painInten":"","phyCooling":"","pulse":"","rectemperature":"","sysPressure":"120","temperature":"","time":"06:00","uriVolume":"","weight":""},{"Barthel":"","Bedsore":"","Fallbed":"","Fallrisk":"","Item34":"","Item34_Title":"","Reason":"","breath":"100","date":"2018-03-26","defFreq":"","degrBlood":"95","diaPressure":"100","heartbeat":"95","height":"","liquidOut":"","painInten":"","phyCooling":"","pulse":"100","rectemperature":"36","sysPressure":"100","temperature":"36","time":"10:00","uriVolume":"","weight":"56"},{"Barthel":"","Bedsore":"","Fallbed":"","Fallrisk":"","Item34":"","Item34_Title":"","Reason":"","breath":"","date":"2018-08-10","defFreq":"","degrBlood":"","diaPressure":"","heartbeat":"","height":"","liquidOut":"","painInten":"","phyCooling":"","pulse":"","rectemperature":"","sysPressure":"","temperature":"39","time":"18:00","uriVolume":"","weight":""}]
     */

    private String endDate;
    private String msg;
    private String msgcode;
    private String stDate;
    private String status;
    private List<TempDataListBean> tempDataList;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TempDataListBean> getTempDataList() {
        return tempDataList;
    }

    public void setTempDataList(List<TempDataListBean> tempDataList) {
        this.tempDataList = tempDataList;
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
