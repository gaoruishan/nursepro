package com.dhcc.nursepro.workarea.bedselect.bean;


import java.util.List;

/**
 * BedListBean
 *
 * @author DevLix126
 * @date 2018/8/29
 */
public class BedSelectListBean {

    /**
     * bedList : [{"groupCode":"A","groupList":[{"bedCode":"01","bedId":"5||1","select":"0"},{"bedCode":"02","bedId":"5||2","select":"0"},{"bedCode":"03","bedId":"5||3","select":"0"},{"bedCode":"04","bedId":"5||4","select":"0"},{"bedCode":"05","bedId":"5||5","select":"0"}]},{"groupCode":"B","groupList":[{"bedCode":"06","bedId":"5||6","select":"0"},{"bedCode":"07","bedId":"5||7","select":"0"},{"bedCode":"08","bedId":"5||8","select":"0"},{"bedCode":"09","bedId":"5||9","select":"0"}]},{"groupCode":"C","groupList":[{"bedCode":"10","bedId":"5||10","select":"0"},{"bedCode":"11","bedId":"5||11","select":"0"},{"bedCode":"12","bedId":"5||12","select":"0"},{"bedCode":"13","bedId":"5||13","select":"0"},{"bedCode":"14","bedId":"5||14","select":"0"},{"bedCode":"15","bedId":"5||15","select":"0"},{"bedCode":"16","bedId":"5||16","select":"0"}]},{"groupCode":"D","groupList":[{"bedCode":"17","bedId":"5||17","select":"0"},{"bedCode":"18","bedId":"5||18","select":"0"},{"bedCode":"19","bedId":"5||19","select":"0"},{"bedCode":"20","bedId":"5||20","select":"0"},{"bedCode":"21","bedId":"5||21","select":"0"},{"bedCode":"22","bedId":"5||22","select":"0"},{"bedCode":"23","bedId":"5||23","select":"0"},{"bedCode":"24","bedId":"5||24","select":"0"},{"bedCode":"25","bedId":"5||25","select":"0"},{"bedCode":"26","bedId":"5||26","select":"0"},{"bedCode":"27","bedId":"5||27","select":"0"},{"bedCode":"28","bedId":"5||28","select":"0"},{"bedCode":"29","bedId":"5||29","select":"0"},{"bedCode":"30","bedId":"5||30","select":"0"},{"bedCode":"31","bedId":"5||31","select":"0"},{"bedCode":"32","bedId":"5||32","select":"0"},{"bedCode":"33","bedId":"5||33","select":"0"},{"bedCode":"34","bedId":"5||34","select":"0"},{"bedCode":"35","bedId":"5||35","select":"0"},{"bedCode":"38","bedId":"5||38","select":"0"},{"bedCode":"39","bedId":"5||39","select":"0"},{"bedCode":"40","bedId":"5||40","select":"0"},{"bedCode":"41","bedId":"5||41","select":"0"},{"bedCode":"42","bedId":"5||42","select":"0"},{"bedCode":"43","bedId":"5||43","select":"0"},{"bedCode":"44","bedId":"5||44","select":"0"},{"bedCode":"45","bedId":"5||45","select":"0"},{"bedCode":"46","bedId":"5||46","select":"0"},{"bedCode":"48","bedId":"5||48","select":"0"},{"bedCode":"49","bedId":"5||49","select":"0"},{"bedCode":"50","bedId":"5||50","select":"0"},{"bedCode":"51","bedId":"5||51","select":"0"},{"bedCode":"52","bedId":"5||52","select":"0"},{"bedCode":"53","bedId":"5||53","select":"0"},{"bedCode":"54","bedId":"5||54","select":"0"},{"bedCode":"55","bedId":"5||55","select":"0"},{"bedCode":"56","bedId":"5||56","select":"0"},{"bedCode":"57","bedId":"5||57","select":"0"},{"bedCode":"58","bedId":"5||58","select":"0"},{"bedCode":"59","bedId":"5||59","select":"0"},{"bedCode":"60","bedId":"5||60","select":"0"},{"bedCode":"61","bedId":"5||61","select":"0"},{"bedCode":"62","bedId":"5||62","select":"0"},{"bedCode":"63","bedId":"5||63","select":"0"},{"bedCode":"64","bedId":"5||64","select":"0"},{"bedCode":"65","bedId":"5||65","select":"0"},{"bedCode":"66","bedId":"5||66","select":"0"},{"bedCode":"67","bedId":"5||67","select":"0"},{"bedCode":"68","bedId":"5||68","select":"0"},{"bedCode":"69","bedId":"5||69","select":"0"},{"bedCode":"70","bedId":"5||71","select":"0"},{"bedCode":"71","bedId":"5||72","select":"0"},{"bedCode":"72","bedId":"5||73","select":"0"}]}]
     * msg : 成功
     * msgcode : 999999
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<BedListBean> bedList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BedListBean> getBedList() {
        return bedList;
    }

    public void setBedList(List<BedListBean> bedList) {
        this.bedList = bedList;
    }

    public static class BedListBean{
        /**
         * groupCode : A
         * groupList : [{"bedCode":"01","bedId":"5||1","select":"0"},{"bedCode":"02","bedId":"5||2","select":"0"},{"bedCode":"03","bedId":"5||3","select":"0"},{"bedCode":"04","bedId":"5||4","select":"0"},{"bedCode":"05","bedId":"5||5","select":"0"}]
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

        public static class GroupListBean{
            /**
             * bedCode : 01
             * bedId : 5||1
             * select : 0
             */

            private String bedCode;
            private String bedId;
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

            public String getSelect() {
                return select;
            }

            public void setSelect(String select) {
                this.select = select;
            }
        }
    }
}
