package com.dhcc.module.nurse.task.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/13
 * Time:17:28
 */
public class NurTaskSchBean extends CommResult {
    private List<StatusListBean> statusList;
    private List<TimesListBean> timesList;
    private List<TypeListBean> typeList;

    public List<StatusListBean> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusListBean> statusList) {
        this.statusList = statusList;
    }

    public List<TimesListBean> getTimesList() {
        return timesList;
    }

    public void setTimesList(List<TimesListBean> timesList) {
        this.timesList = timesList;
    }

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    public static class TimesListBean {
        /**
         * timeEnd : 2020-08-13 23:59
         * timeStt : 2020-08-13 00:00
         * timesDesc : 全天
         */

        private String timeEnd;
        private String timeStt;
        private String timesDesc;

        public String getTimeEnd() {
            return timeEnd;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }

        public String getTimeStt() {
            return timeStt;
        }

        public void setTimeStt(String timeStt) {
            this.timeStt = timeStt;
        }

        public String getTimesDesc() {
            return timesDesc;
        }

        public void setTimesDesc(String timesDesc) {
            this.timesDesc = timesDesc;
        }
    }

    public static class TypeListBean {
        /**
         * id : 1
         * longNameEN : Monitor
         * shortNameCN : 监测
         * shortNameEN : M
         */

        private String id;
        private String longNameEN;
        private String shortNameCN;
        private String shortNameEN;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLongNameEN() {
            return longNameEN;
        }

        public void setLongNameEN(String longNameEN) {
            this.longNameEN = longNameEN;
        }

        public String getShortNameCN() {
            return shortNameCN;
        }

        public void setShortNameCN(String shortNameCN) {
            this.shortNameCN = shortNameCN;
        }

        public String getShortNameEN() {
            return shortNameEN;
        }

        public void setShortNameEN(String shortNameEN) {
            this.shortNameEN = shortNameEN;
        }
    }
}
