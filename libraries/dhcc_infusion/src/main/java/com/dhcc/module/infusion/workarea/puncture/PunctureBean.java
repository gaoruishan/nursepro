package com.dhcc.module.infusion.workarea.puncture;

import com.dhcc.module.infusion.workarea.comm.bean.CommInfusionBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-24/14:52
 * @email:grs0515@163.com
 */
public class PunctureBean extends CommInfusionBean {


    /**
     * CurOeoreId : 568-3-1
     * CurRegNo :
     * DefautSpeed : 45
     * DistantTime : 12:53:09
     * PatInfo : {"PatName":"lh041101","PatRegNo":"0000000435","PatSex":"女"}
     * PunturePartList : [{"PunturePart":"手背"},{"PunturePart":"脑后"},{"PunturePart":"脚背"}]
     * msg : 成功
     * msgcode :
     * ordList : [{"Notes":"","OeoreId":"568||3||1","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"已复核"},{"Notes":"","OeoreId":"568||3||3","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"已复核"},{"Notes":"","OeoreId":"568||3||5","OeoreSubList":[{"ArcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","DoseQtyUnit":"250 ml","PhOrdQtyUnit":"18 瓶"}],"OrdState":"未配液"}]
     * status : 0
     */

    private String DefautSpeed;
    private String DistantTime;
    private String DistantDate;
    private List<PunturePartListBean> PunturePartList;
    private List<PuntureToolListBean> PuntureToolList;

    public String getCurDate() {
        return curDate == null ? "" : curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public String getCurTime() {
        return curTime == null ? "" : curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public String getCurSumDose() {
        return curSumDose == null ? "" : curSumDose;
    }

    public void setCurSumDose(String curSumDose) {
        this.curSumDose = curSumDose;
    }

    public List<PuntureToolListBean> getPuntureToolList() {
        return PuntureToolList;
    }

    public void setPuntureToolList(List<PuntureToolListBean> puntureToolList) {
        PuntureToolList = puntureToolList;
    }

    public String getDistantDate() {
        return DistantDate == null ? "" : DistantDate;
    }

    public void setDistantDate(String distantDate) {
        DistantDate = distantDate;
    }

    public String getCurOeoreId() {
        return CurOeoreId;
    }

    public void setCurOeoreId(String CurOeoreId) {
        this.CurOeoreId = CurOeoreId;
    }

    public String getCurRegNo() {
        return CurRegNo;
    }

    public void setCurRegNo(String CurRegNo) {
        this.CurRegNo = CurRegNo;
    }

    public String getDefautSpeed() {
        return DefautSpeed;
    }

    public void setDefautSpeed(String DefautSpeed) {
        this.DefautSpeed = DefautSpeed;
    }

    public String getDistantTime() {
        return DistantTime;
    }

    public void setDistantTime(String DistantTime) {
        this.DistantTime = DistantTime;
    }

    public PatInfoBean getPatInfo() {
        return PatInfo;
    }

    public void setPatInfo(PatInfoBean PatInfo) {
        this.PatInfo = PatInfo;
    }

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

    /**
     * 获取穿刺部位
     * @return
     */
    public List<String> getPunturePartListString() {
        List<String> list = new ArrayList<>();
        if (getPunturePartList() != null) {
            for (PunctureBean.PunturePartListBean listBean : getPunturePartList()) {
                list.add(listBean.getPunturePart());
            }
        }
        return list;
    }

    public List<PunturePartListBean> getPunturePartList() {
        return PunturePartList;
    }

    public void setPunturePartList(List<PunturePartListBean> PunturePartList) {
        this.PunturePartList = PunturePartList;
    }

    public static class PunturePartListBean {
        /**
         * PunturePart : 手背
         */

        private String PunturePart;

        public String getPunturePart() {
            return PunturePart;
        }

        public void setPunturePart(String PunturePart) {
            this.PunturePart = PunturePart;
        }
    }

    public static class PuntureToolListBean {
        /**
         * PunturePart : 手背
         */

        private String PuntureTool;

        public String getPuntureTool() {
            return PuntureTool;
        }

        public void setPuntureTool(String PunturePart) {
            this.PuntureTool = PunturePart;
        }
    }

}
