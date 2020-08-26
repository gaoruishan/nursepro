package com.dhcc.module.nurse.bloodsugar.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.nurse.bloodsugar.bean
 * <p>
 * author Q
 * Date: 2020/8/21
 * Time:16:21
 */
public class BloodSugarNotelistBean extends CommResult {
    private List<SugarInfoListBean> sugarInfoList;
    private List<FilterListBean> filterList;

    public List<FilterListBean> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<FilterListBean> filterList) {
        this.filterList = filterList;
    }

    public static class FilterListBean {
        /**
         * code : FBS
         * desc : 空腹血糖
         */

        private String code;
        private String desc;

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
    }
    public List<SugarInfoListBean> getSugarInfoList() {
        return sugarInfoList;
    }

    public void setSugarInfoList(List<SugarInfoListBean> sugarInfoList) {
        this.sugarInfoList = sugarInfoList;
    }

    public static class SugarInfoListBean {
        /**
         * color : #4472C4
         * date : 2020-08-15
         * sugarData : [{"code":"午餐前","obsNote":"","sugar":"","updateTime":"","updateUser":""},{"code":"午餐后","obsNote":"","sugar":"","updateTime":"","updateUser":""},{"code":"夜间","obsNote":"","sugar":"","updateTime":"","updateUser":""},{"code":"早餐后","obsNote":"","sugar":"","updateTime":"","updateUser":""},{"code":"晚餐前","obsNote":"","sugar":"","updateTime":"","updateUser":""},{"code":"晚餐后","obsNote":"","sugar":"","updateTime":"","updateUser":""},{"code":"睡前","obsNote":"","sugar":"","updateTime":"","updateUser":""},{"code":"空腹血糖","obsNote":"","sugar":"","updateTime":"","updateUser":""},{"code":"随机","obsNote":"","sugar":"","updateTime":"","updateUser":""}]
         */

        private String color;
        private String date;
        private List<SugarDataBean> sugarData;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<SugarDataBean> getSugarData() {
            return sugarData;
        }

        public void setSugarData(List<SugarDataBean> sugarData) {
            this.sugarData = sugarData;
        }

        public static class SugarDataBean {
            /**
             * code : 午餐前
             * obsNote :
             * sugar :
             * updateTime :
             * updateUser :
             */

            private String code;
            private String obsNote;
            private String sugar;
            private String updateTime;
            private String updateUser;
            private String date;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getObsNote() {
                return obsNote;
            }

            public void setObsNote(String obsNote) {
                this.obsNote = obsNote;
            }

            public String getSugar() {
                return sugar;
            }

            public void setSugar(String sugar) {
                this.sugar = sugar;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }
        }
    }
}
