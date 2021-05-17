package com.dhcc.module.nurse.task.bean;

import android.text.TextUtils;

import com.base.commlibs.http.CommResult;
import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-09-03/10:33
 * @email:grs0515@163.com
 */
public class NurRateTaskBean extends CommResult {

    private List<RecDataBean> recData;
    private List<TempCodeListBean> tempCodeList;

    public List<RecDataBean> getRecData() {
        return recData;
    }

    public void setRecData(List<RecDataBean> recData) {
        this.recData = recData;
    }

    public List<TempCodeListBean> getTempCodeList() {
        //去掉""的对象
        List<TempCodeListBean> mStList = new ArrayList<>();
        if (tempCodeList != null) {
            for (TempCodeListBean bean : tempCodeList) {
                if (!TextUtils.isEmpty(bean.getDesc())) {
                    mStList.add(bean);
                }
            }
        }
        return mStList;
    }

    public void setTempCodeList(List<TempCodeListBean> tempCodeList) {
        this.tempCodeList = tempCodeList;
    }

    public static class RecDataBean {
        /**
         * bedCode : 07
         * episodeID : 18
         * patientName : pu01
         * recordScore : [{"EmrCode":"2D938C77302E42519C3AFC097FB045AA","endDate":"65624","endTime":"63000","linkCode":"DHCNURNJSGCRMYYMORSEDDPGD","name":"白班","recordName":"跌倒_坠床风险评估单","score":"true","startDate":"65624","startTime":"30600","url":"DHCNURmorsepgd.csp?EmrCode=2D938C77302E42519C3AFC097FB045AA&EpisodeID=18"}]
         * sex : 男
         */

        private String bedCode;
        private String episodeID;
        private String patientName;
        private String sex;
        private List<RecordScoreBean> recordScore;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getEpisodeID() {
            return episodeID;
        }

        public void setEpisodeID(String episodeID) {
            this.episodeID = episodeID;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public List<RecordScoreBean> getRecordScore() {
            return recordScore;
        }

        public void setRecordScore(List<RecordScoreBean> recordScore) {
            this.recordScore = recordScore;
        }

        public static class RecordScoreBean {
            /**
             * EmrCode : 2D938C77302E42519C3AFC097FB045AA
             * endDate : 65624
             * endTime : 63000
             * linkCode : DHCNURNJSGCRMYYMORSEDDPGD
             * name : 白班
             * recordName : 跌倒_坠床风险评估单
             * score : true
             * startDate : 65624
             * startTime : 30600
             * url : DHCNURmorsepgd.csp?EmrCode=2D938C77302E42519C3AFC097FB045AA&EpisodeID=18
             */

            private String EmrCode;
            private String endDate;
            private String endTime;
            private String linkCode;
            private String name;
            private String recordName;
            private String score;
            private String startDate;
            private String startTime;
            private String url;
            private String guId;

            public String getGuId() {
                return guId == null ? "" : guId;
            }

            public void setGuId(String guId) {
                this.guId = guId;
            }

            public String getEmrCode() {
                return EmrCode;
            }

            public void setEmrCode(String EmrCode) {
                this.EmrCode = EmrCode;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getLinkCode() {
                return linkCode;
            }

            public void setLinkCode(String linkCode) {
                this.linkCode = linkCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRecordName() {
                return recordName;
            }

            public void setRecordName(String recordName) {
                this.recordName = recordName;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class TempCodeListBean extends SheetListBean {
        /**
         * code : DHCNURBRADENPFLR
         * desc : Braden压力性损伤风险评估单
         * num : 2
         * numDesc : 任务数
         */

//        private String code;
//        private String desc;
//        private String num;
        private String numDesc;


        public String getNumDesc() {
            return numDesc;
        }

        public void setNumDesc(String numDesc) {
            this.numDesc = numDesc;
        }
    }
}
