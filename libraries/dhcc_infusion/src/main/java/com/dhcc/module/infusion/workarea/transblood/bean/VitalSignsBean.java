package com.dhcc.module.infusion.workarea.transblood.bean;

/**
 * @author:gaoruishan
 * @date:202020-03-10/14:25
 * @email:grs0515@163.com
 */
public class VitalSignsBean {

    /**
     * temperature : 体温(℃): 37.1
     * pulse : 脉搏(次/每分): 67
     * sysPressure : 收缩压(mmHg): 188
     * diaPressure : 舒张压(mmHg): 90
     */
    //体温 脉搏 舒张压 收缩压
    private String temperature;
    private String pulse;
    private String sysPressure;
    private String diaPressure;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getSysPressure() {
        return sysPressure;
    }

    public void setSysPressure(String sysPressure) {
        this.sysPressure = sysPressure;
    }

    public String getDiaPressure() {
        return diaPressure;
    }

    public void setDiaPressure(String diaPressure) {
        this.diaPressure = diaPressure;
    }
}
