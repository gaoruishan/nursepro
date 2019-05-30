package com.dhcc.module.infusion.workarea.puncture;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-24/14:52
 * @email:grs0515@163.com
 */
public class PunctureBean extends CommResult {


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

    private String CurOeoreId;
    private String CurRegNo;
    private String DefautSpeed;
    private String DistantTime;
    private String DistantDate;
    private PatInfoBean PatInfo;
    private List<PunturePartListBean> PunturePartList;
    private List<OrdListBean> ordList;

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

    public List<PunturePartListBean> getPunturePartList() {
        return PunturePartList;
    }

    public void setPunturePartList(List<PunturePartListBean> PunturePartList) {
        this.PunturePartList = PunturePartList;
    }

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }


    public static class PatInfoBean {
        private String PatName;
        private String PatRegNo;
        private String PatSex;

        /**
         * PatName : lh041101
         * PatRegNo : 0000000435
         * PatSex : 女
         */
        public int getPatSexDrawable() {
            if ("男".equals(PatSex)) {
                return R.drawable.infusion_sex_male;
            } else if ("女".equals(PatSex)) {
                return R.drawable.infusion_sex_female;
            } else {
                return R.drawable.infusion_sex_male;
            }
        }

        public String getPatName() {
            return PatName;
        }

        public void setPatName(String PatName) {
            this.PatName = PatName;
        }

        public String getPatRegNo() {
            return PatRegNo;
        }

        public void setPatRegNo(String PatRegNo) {
            this.PatRegNo = PatRegNo;
        }

        public String getPatSex() {
            return PatSex;
        }

        public void setPatSex(String PatSex) {
            this.PatSex = PatSex;
        }
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

}
