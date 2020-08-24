package com.dhcc.module.nurse.nurplan.bean;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.utils.DataCache;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-20/17:09
 * @email:grs0515@163.com
 */
public class InterveFreqBean extends CommResult {

    private List<FreqListBean> freqList;

    /**
     * 获取频次
     * @param freqDR
     * @return
     */
    public static String getFreq(String freqDR) {
        InterveFreqBean json = DataCache.getJson(InterveFreqBean.class, InterveFreqBean.class.getSimpleName());
        if (json != null && json.getFreqList() != null) {
            for (FreqListBean bean : json.getFreqList()) {
                if (bean.getId().equals(freqDR)) {
                    return bean.namec;
                }
            }
        }
        return "";
    }

    /**
     * 获取选择类别
     * @param freqList
     * @return
     */
    public static List<String> getSelectData(List<FreqListBean> freqList) {
        List<String > mStList = new ArrayList<>();
        if (freqList != null) {
            for (FreqListBean bean : freqList) {
                mStList.add(bean.getNamee() + "(" + bean.getNamec() + ")");
            }
        }
        return mStList;
    }

    public List<FreqListBean> getFreqList() {
        return freqList;
    }

    public void setFreqList(List<FreqListBean> freqList) {
        this.freqList = freqList;
    }

    public static class FreqListBean {
        /**
         * activeFlag : Yes
         * clinicType : 门诊,急诊,住院,体检,新生儿
         * code : Prn
         * disposing : 8:00:00
         * factor : 1
         * id : 1
         * namec : 必要时
         * namee : Prn
         * weekFlag : No
         */

        private String activeFlag;
        private String clinicType;
        private String code;
        private String disposing;
        private String factor;
        private String id;
        private String namec;
        private String namee;
        private String weekFlag;

        public String getActiveFlag() {
            return activeFlag;
        }

        public void setActiveFlag(String activeFlag) {
            this.activeFlag = activeFlag;
        }

        public String getClinicType() {
            return clinicType;
        }

        public void setClinicType(String clinicType) {
            this.clinicType = clinicType;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDisposing() {
            return disposing;
        }

        public void setDisposing(String disposing) {
            this.disposing = disposing;
        }

        public String getFactor() {
            return factor;
        }

        public void setFactor(String factor) {
            this.factor = factor;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNamec() {
            return namec;
        }

        public void setNamec(String namec) {
            this.namec = namec;
        }

        public String getNamee() {
            return namee;
        }

        public void setNamee(String namee) {
            this.namee = namee;
        }

        public String getWeekFlag() {
            return weekFlag;
        }

        public void setWeekFlag(String weekFlag) {
            this.weekFlag = weekFlag;
        }
    }
}
