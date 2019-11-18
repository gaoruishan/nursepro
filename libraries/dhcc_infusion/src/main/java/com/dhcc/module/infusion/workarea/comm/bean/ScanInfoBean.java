package com.dhcc.module.infusion.workarea.comm.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * 扫码信息
 * @author:gaoruishan
 * @date:202019-11-14/17:18
 * @email:grs0515@163.com
 */
public class ScanInfoBean extends CommResult {

    /**
     * canExeFlag : 0
     * diagFlag : 1
     * flag : PAT
     * orders : [{}]
     * patInfo : {"IPAppBedID":"","IPBookID":"","admReason":"全自费","age":"33岁","balance":"46089.74","bedCode":"05","careLevel":"一级护理","careLevelColor":"#FF0000","ctLocDesc":"内分泌科","currLocID":"95","currWardID":"3","diagnosis":"咳嗽病,风证类,非ICD诊断,tt","dischargeStatus":"","episodeID":"1","homeAddres":"","ifFirstToBed":"N","ifNewBaby":"N","illState":"病危","inDays":"288","inDeptDateTime":"2019-01-30 17:01","inHospDateTime":"2019-01-30 17:01:06","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士01 李秀琴[1900]","mainNurseID":["3","173"],"medicareNo":"100001","motherTransLoc":"","name":"秦海贤","nation":"","operLaterDays":"","operation":"头部血管治疗性超声","orderID":"1","patLinkman":"","patientID":"1","personID":"360822198609284091 ","regDateTime":"2019-01-30 16:59:43","regNo":"0000000001","roomDesc":"内分泌1病室","sex":"男","telphone":"13426096345","totalCost":"3910.26","wardDesc":"内分泌科护理单元","weekDays":"第41周/第1天"}
     */

    private String canExeFlag;
    private String diagFlag;
    private String flag;
    private PatInfoBean patInfo;
    private List<PatOrdersBean> orders;

    public String getCanExeFlag() {
        return canExeFlag;
    }

    public void setCanExeFlag(String canExeFlag) {
        this.canExeFlag = canExeFlag;
    }

    public String getDiagFlag() {
        return diagFlag;
    }

    public void setDiagFlag(String diagFlag) {
        this.diagFlag = diagFlag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public List<PatOrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<PatOrdersBean> orders) {
        this.orders = orders;
    }


}
