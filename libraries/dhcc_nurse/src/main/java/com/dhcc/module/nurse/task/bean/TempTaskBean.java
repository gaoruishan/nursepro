package com.dhcc.module.nurse.task.bean;

import com.base.commlibs.http.CommResult;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/11
 * Time:10:18
 */
public class TempTaskBean extends CommResult {
    private List<TempCodeListBean> tempCodeList;
    private List<TempDateListBean> tempDateList;

    public List<TempCodeListBean> getTempCodeList() {
        if (tempCodeList == null) {
            return new ArrayList<>();
        }
        return tempCodeList;
    }

    public void setTempCodeList(List<TempCodeListBean> tempCodeList) {
        this.tempCodeList = tempCodeList;
    }

    public List<TempDateListBean> getTempDateList() {
        return tempDateList;
    }

    public void setTempDateList(List<TempDateListBean> tempDateList) {
        this.tempDateList = tempDateList;
    }

    public static class TempCodeListBean {
        /**
         * code : all
         * desc : 全部
         * num : 127
         * numDesc : 任务数
         */

        private String code;
        private String desc;
        private String num;
        private String numDesc;

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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getNumDesc() {
            return numDesc;
        }

        public void setNumDesc(String numDesc) {
            this.numDesc = numDesc;
        }
    }
    public static class TempDateListBean {
        /**
         * bedCode : 04
         * episodeId : 25
         * name : jhm3004
         * obsDataList : [{"obsDesc":"呼吸（次/分）","obsKey":"breath","obsValue":""},{"obsDesc":"舒张压（mmHg）","obsKey":"diaPressure","obsValue":""},{"obsDesc":"脉搏（次/分）","obsKey":"pulse","obsValue":""},{"obsDesc":"收缩压（mmHg）","obsKey":"sysPressure","obsValue":""},{"obsDesc":"腋温（℃）","obsKey":"temperature","obsValue":""},{"obsDesc":"体重（KG）","obsKey":"weight","obsValue":""}]
         * sex : 未知
         * timeKey : 06:00
         */

        private String bedCode;
        private String episodeId;
        private String name;
        private String sex;
        private String timeKey;
        private List<ObsDataListBean> obsDataList;
        private Boolean select = false;

        public Boolean getSelect() {
            return select;
        }

        public void setSelect(Boolean select) {
            this.select = select;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTimeKey() {
            return timeKey;
        }

        public void setTimeKey(String timeKey) {
            this.timeKey = timeKey;
        }

        public List<ObsDataListBean> getObsDataList() {
            return obsDataList;
        }

        public void setObsDataList(List<ObsDataListBean> obsDataList) {
            this.obsDataList = obsDataList;
        }

        public static class ObsDataListBean {
            /**
             * obsDesc : 呼吸（次/分）
             * obsKey : breath
             * obsValue :
             */

            private String obsDesc;
            private String obsKey;
            private String obsValue;

            public String getObsDesc() {
                return obsDesc;
            }

            public void setObsDesc(String obsDesc) {
                this.obsDesc = obsDesc;
            }

            public String getObsKey() {
                return obsKey;
            }

            public void setObsKey(String obsKey) {
                this.obsKey = obsKey;
            }

            public String getObsValue() {
                return obsValue;
            }

            public void setObsValue(String obsValue) {
                this.obsValue = obsValue;
            }
        }
    }
}
