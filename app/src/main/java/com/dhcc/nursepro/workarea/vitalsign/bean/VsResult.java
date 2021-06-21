package com.dhcc.nursepro.workarea.vitalsign.bean;

/**
 * Created by Gaopingdong on 2018/11/19.
 */
public class VsResult {
    public  float  bodyTemp;//体温
    public String tempUnit;//体温单位

    public  float  spo2;    //血氧
    public  float  pi;      //灌注
    public  float  prSpo2; //血氧脉率

    public  float  sys;   //高压
    public  float  dia;   //低压
    public  float  map;   //平均压
    public String bpUnit;//血压单位
    public  float  prBp; //血压脉率


    public float getBodyTemp() {
        return bodyTemp;
    }

    public void setBodyTemp(float bodyTemp) {
        this.bodyTemp = bodyTemp;
    }

    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }

    public float getSpo2() {
        return spo2;
    }

    public void setSpo2(float spo2) {
        this.spo2 = spo2;
    }

    public float getPi() {
        return pi;
    }

    public void setPi(float pi) {
        this.pi = pi;
    }

    public float getPrSpo2() {
        return prSpo2;
    }

    public void setPrSpo2(float prSpo2) {
        this.prSpo2 = prSpo2;
    }

    public float getSys() {
        return sys;
    }

    public void setSys(float sys) {
        this.sys = sys;
    }

    public float getDia() {
        return dia;
    }

    public void setDia(float dia) {
        this.dia = dia;
    }

    public float getMap() {
        return map;
    }

    public void setMap(float map) {
        this.map = map;
    }

    public String getBpUnit() {
        return bpUnit;
    }

    public void setBpUnit(String bpUnit) {
        this.bpUnit = bpUnit;
    }

    public float getPrBp() {
        return prBp;
    }

    public void setPrBp(float prBp) {
        this.prBp = prBp;
    }

    @Override
    public String toString() {
        return "VsResult{" +
                "bodyTemp=" + bodyTemp +
                ", tempUnit='" + tempUnit + '\'' +
                ", spo2=" + spo2 +
                ", pi=" + pi +
                ", prSpo2=" + prSpo2 +
                ", sys=" + sys +
                ", dia=" + dia +
                ", map=" + map +
                ", bpUnit='" + bpUnit + '\'' +
                ", prBp=" + prBp +
                '}';
    }
}
