package com.dhcc.module.infusion.workarea.transblood.bean;

import com.dhcc.module.infusion.workarea.patrol.bean.InfusionReasonListBean;
import com.dhcc.module.infusion.workarea.patrol.bean.InfusionStateListBean;
import com.dhcc.module.infusion.workarea.puncture.bean.PunturePartListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-03-04/11:06
 * @email:grs0515@163.com
 */
public class TransBloodListBean extends CommTransBloodBean {

    //生命体征
    private VitalSignsBean vitalSigns;
    //穿刺部位
    private List<PunturePartListBean> punturePartList;
    //默认滴速
    private String defaultSpeed;
    //预计时间
    private String distantTime;
    private String distantDate;
    //输液情况
    private List<InfusionStateListBean> infusionStateList;
    //结束
    private List<InfusionReasonListBean> infusionEndReasonList;
    private List<InfusionStateListBean> infusionEndStateList;
    //巡视类型
    private List<InfusionTypeBean> tourTypeList;
    //巡视记录
    private List<PatrolRecordBean> tourList;

    public List<PatrolRecordBean> getTourList() {
        return tourList;
    }

    public void setTourList(List<PatrolRecordBean> tourList) {
        this.tourList = tourList;
    }

    public List<InfusionReasonListBean> getInfusionEndReasonList() {
        return infusionEndReasonList;
    }

    public void setInfusionEndReasonList(List<InfusionReasonListBean> infusionEndReasonList) {
        this.infusionEndReasonList = infusionEndReasonList;
    }

    public List<InfusionStateListBean> getInfusionEndStateList() {
        return infusionEndStateList;
    }

    public void setInfusionEndStateList(List<InfusionStateListBean> infusionEndStateList) {
        this.infusionEndStateList = infusionEndStateList;
    }

    public List<InfusionTypeBean> getTourTypeList() {
        return tourTypeList;
    }

    public void setTourTypeList(List<InfusionTypeBean> tourTypeList) {
        this.tourTypeList = tourTypeList;
    }

    public List<InfusionReasonListBean> getInfusionReasonList() {
        return infusionEndReasonList;
    }

    public void setInfusionReasonList(List<InfusionReasonListBean> infusionReasonList) {
        this.infusionEndReasonList = infusionReasonList;
    }

    public List<InfusionStateListBean> getInfusionStateList() {
        return infusionStateList;
    }

    public void setInfusionStateList(List<InfusionStateListBean> infusionStateList) {
        this.infusionStateList = infusionStateList;
    }

    public String getDistantTime() {
        return distantTime == null ? "" : distantTime;
    }

    public void setDistantTime(String distantTime) {
        this.distantTime = distantTime;
    }

    public String getDistantDate() {
        return distantDate == null ? "" : distantDate;
    }

    public void setDistantDate(String distantDate) {
        this.distantDate = distantDate;
    }

    public String getDefaultSpeed() {
        return defaultSpeed == null ? "" : defaultSpeed;
    }

    public void setDefaultSpeed(String defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }

    public List<PunturePartListBean> getPunturePartList() {
        return punturePartList;
    }

    public void setPunturePartList(List<PunturePartListBean> punturePartList) {
        this.punturePartList = punturePartList;
    }

    public VitalSignsBean getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSignsBean vitalSigns) {
        this.vitalSigns = vitalSigns;
    }


}
