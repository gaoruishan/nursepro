package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.nursepro.setting.bean
 * <p>
 * author Q
 * Date: 2018/9/13
 * Time:16:23
 */
public class SettingBedListBean  extends CommResult {

    /**
     * bedList : [{"groupCode":"其他","groupList":[{"bedCode":"01","bedId":"3||1","patSex":"男","select":"1"},{"bedCode":"02","bedId":"3||2","select":"1"},{"bedCode":"03","bedId":"3||3","patSex":"男","select":"1"},{"bedCode":"04","bedId":"3||4","patSex":"男","select":"1"},{"bedCode":"05","bedId":"3||5","patSex":"男","select":"1"},{"bedCode":"06","bedId":"3||6","patSex":"女","select":"1"},{"bedCode":"07","bedId":"3||7","patSex":"男","select":"1"},{"bedCode":"08","bedId":"3||8","patSex":"男","select":"1"},{"bedCode":"09","bedId":"3||9","patSex":"未知性别","select":"1"},{"bedCode":"10","bedId":"3||10","patSex":"男","select":"1"},{"bedCode":"11","bedId":"3||11","patSex":"女","select":"1"},{"bedCode":"12","bedId":"3||12","select":"1"},{"bedCode":"13","bedId":"3||13","patSex":"女","select":"1"},{"bedCode":"14","bedId":"3||14","patSex":"女","select":"1"},{"bedCode":"15","bedId":"3||15","patSex":"男","select":"1"},{"bedCode":"16","bedId":"3||16","patSex":"男","select":"1"},{"bedCode":"17","bedId":"3||17","patSex":"女","select":"1"},{"bedCode":"18","bedId":"3||18","patSex":"女","select":"1"},{"bedCode":"19","bedId":"3||19","patSex":"男","select":"1"},{"bedCode":"20","bedId":"3||20","patSex":"女","select":"1"},{"bedCode":"21","bedId":"3||21","patSex":"男","select":"1"},{"bedCode":"22","bedId":"3||22","patSex":"女","select":"1"},{"bedCode":"23","bedId":"3||23","patSex":"女","select":"1"},{"bedCode":"24","bedId":"3||24","patSex":"男","select":"1"},{"bedCode":"25","bedId":"3||25","patSex":"女","select":"1"},{"bedCode":"26","bedId":"3||26","patSex":"女","select":"1"},{"bedCode":"27","bedId":"3||27","patSex":"男","select":"1"},{"bedCode":"28","bedId":"3||28","patSex":"女","select":"1"},{"bedCode":"29","bedId":"3||29","patSex":"女","select":"1"},{"bedCode":"30","bedId":"3||30","patSex":"女","select":"1"},{"bedCode":"31","bedId":"3||31","patSex":"女","select":"1"},{"bedCode":"32","bedId":"3||32","select":"1"},{"bedCode":"33","bedId":"3||33","patSex":"男","select":"1"},{"bedCode":"34","bedId":"3||34","patSex":"男","select":"1"},{"bedCode":"35","bedId":"3||35","patSex":"男","select":"1"},{"bedCode":"36","bedId":"3||36","patSex":"女","select":"1"},{"bedCode":"37","bedId":"3||37","patSex":"男","select":"1"},{"bedCode":"38","bedId":"3||38","patSex":"男","select":"1"},{"bedCode":"39","bedId":"3||39","patSex":"男","select":"1"},{"bedCode":"40","bedId":"3||40","patSex":"女","select":"1"},{"bedCode":"51","bedId":"3||51","select":"1"},{"bedCode":"52","bedId":"3||52","patSex":"女","select":"1"},{"bedCode":"41","bedId":"3||41","patSex":"女","select":"1"},{"bedCode":"42","bedId":"3||42","select":"1"},{"bedCode":"43","bedId":"3||43","patSex":"女","select":"1"},{"bedCode":"44","bedId":"3||44","patSex":"女","select":"1"},{"bedCode":"45","bedId":"3||45","select":"1"},{"bedCode":"53","bedId":"3||53","select":"1"},{"bedCode":"46","bedId":"3||46","patSex":"女","select":"1"},{"bedCode":"47","bedId":"3||47","patSex":"女","select":"1"},{"bedCode":"48","bedId":"3||48","patSex":"女","select":"1"},{"bedCode":"49","bedId":"3||49","select":"1"},{"bedCode":"50","bedId":"3||50","patSex":"女","select":"1"}]}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private List<BedListBean> bedList;

    public List<BedListBean> getBedList() {
        return bedList;
    }

    public void setBedList(List<BedListBean> bedList) {
        this.bedList = bedList;
    }

    public static class BedListBean {
        /**
         * groupCode : 其他
         * groupList : [{"bedCode":"01","bedId":"3||1","patSex":"男","select":"1"},{"bedCode":"02","bedId":"3||2","select":"1"},{"bedCode":"03","bedId":"3||3","patSex":"男","select":"1"},{"bedCode":"04","bedId":"3||4","patSex":"男","select":"1"},{"bedCode":"05","bedId":"3||5","patSex":"男","select":"1"},{"bedCode":"06","bedId":"3||6","patSex":"女","select":"1"},{"bedCode":"07","bedId":"3||7","patSex":"男","select":"1"},{"bedCode":"08","bedId":"3||8","patSex":"男","select":"1"},{"bedCode":"09","bedId":"3||9","patSex":"未知性别","select":"1"},{"bedCode":"10","bedId":"3||10","patSex":"男","select":"1"},{"bedCode":"11","bedId":"3||11","patSex":"女","select":"1"},{"bedCode":"12","bedId":"3||12","select":"1"},{"bedCode":"13","bedId":"3||13","patSex":"女","select":"1"},{"bedCode":"14","bedId":"3||14","patSex":"女","select":"1"},{"bedCode":"15","bedId":"3||15","patSex":"男","select":"1"},{"bedCode":"16","bedId":"3||16","patSex":"男","select":"1"},{"bedCode":"17","bedId":"3||17","patSex":"女","select":"1"},{"bedCode":"18","bedId":"3||18","patSex":"女","select":"1"},{"bedCode":"19","bedId":"3||19","patSex":"男","select":"1"},{"bedCode":"20","bedId":"3||20","patSex":"女","select":"1"},{"bedCode":"21","bedId":"3||21","patSex":"男","select":"1"},{"bedCode":"22","bedId":"3||22","patSex":"女","select":"1"},{"bedCode":"23","bedId":"3||23","patSex":"女","select":"1"},{"bedCode":"24","bedId":"3||24","patSex":"男","select":"1"},{"bedCode":"25","bedId":"3||25","patSex":"女","select":"1"},{"bedCode":"26","bedId":"3||26","patSex":"女","select":"1"},{"bedCode":"27","bedId":"3||27","patSex":"男","select":"1"},{"bedCode":"28","bedId":"3||28","patSex":"女","select":"1"},{"bedCode":"29","bedId":"3||29","patSex":"女","select":"1"},{"bedCode":"30","bedId":"3||30","patSex":"女","select":"1"},{"bedCode":"31","bedId":"3||31","patSex":"女","select":"1"},{"bedCode":"32","bedId":"3||32","select":"1"},{"bedCode":"33","bedId":"3||33","patSex":"男","select":"1"},{"bedCode":"34","bedId":"3||34","patSex":"男","select":"1"},{"bedCode":"35","bedId":"3||35","patSex":"男","select":"1"},{"bedCode":"36","bedId":"3||36","patSex":"女","select":"1"},{"bedCode":"37","bedId":"3||37","patSex":"男","select":"1"},{"bedCode":"38","bedId":"3||38","patSex":"男","select":"1"},{"bedCode":"39","bedId":"3||39","patSex":"男","select":"1"},{"bedCode":"40","bedId":"3||40","patSex":"女","select":"1"},{"bedCode":"51","bedId":"3||51","select":"1"},{"bedCode":"52","bedId":"3||52","patSex":"女","select":"1"},{"bedCode":"41","bedId":"3||41","patSex":"女","select":"1"},{"bedCode":"42","bedId":"3||42","select":"1"},{"bedCode":"43","bedId":"3||43","patSex":"女","select":"1"},{"bedCode":"44","bedId":"3||44","patSex":"女","select":"1"},{"bedCode":"45","bedId":"3||45","select":"1"},{"bedCode":"53","bedId":"3||53","select":"1"},{"bedCode":"46","bedId":"3||46","patSex":"女","select":"1"},{"bedCode":"47","bedId":"3||47","patSex":"女","select":"1"},{"bedCode":"48","bedId":"3||48","patSex":"女","select":"1"},{"bedCode":"49","bedId":"3||49","select":"1"},{"bedCode":"50","bedId":"3||50","patSex":"女","select":"1"}]
         */

        //添加选中状态字段
        private String select;

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }

        private String groupCode;
        private List<GroupListBean> groupList;

        public String getGroupCode() {
            return groupCode;
        }

        public void setGroupCode(String groupCode) {
            this.groupCode = groupCode;
        }

        public List<GroupListBean> getGroupList() {
            return groupList;
        }

        public void setGroupList(List<GroupListBean> groupList) {
            this.groupList = groupList;
        }

        public static class GroupListBean {
            /**
             * bedCode : 01
             * bedId : 3||1
             * patSex : 男
             * select : 1
             */

            private String bedCode;
            private String bedId;
            private String patSex;
            private String select;

            public String getBedCode() {
                return bedCode;
            }

            public void setBedCode(String bedCode) {
                this.bedCode = bedCode;
            }

            public String getBedId() {
                return bedId;
            }

            public void setBedId(String bedId) {
                this.bedId = bedId;
            }

            public String getPatSex() {
                return patSex;
            }

            public void setPatSex(String patSex) {
                this.patSex = patSex;
            }

            public String getSelect() {
                return select;
            }

            public void setSelect(String select) {
                this.select = select;
            }
        }
    }
}
