package com.dhcc.module.nurse.nurplan.bean;

import java.util.List;

public class QuestionAddListBean {

    /**
     * checked : 0
     * childs : [{"checked":"1","queName":"电解质不平衡-高血钾","queRowID":"36","queTypeName":"神经","queTypeRowID":"3"},{"checked":"1","queName":"电解质不平衡-低血钾","queRowID":"37","queTypeName":"神经","queTypeRowID":"3"},{"checked":"1","queName":"电解质不平衡-高血钠","queRowID":"38","queTypeName":"神经","queTypeRowID":"3"},{"checked":"1","queName":"电解质不平衡-低血钠","queRowID":"39","queTypeName":"神经","queTypeRowID":"3"},{"checked":"1","queName":"电解质不平衡-高血钙","queRowID":"40","queTypeName":"神经","queTypeRowID":"3"},{"checked":"1","queName":"电解质不平衡-低血钙","queRowID":"41","queTypeName":"神经","queTypeRowID":"3"}]
     * queName : 电解质不平衡
     * queRowID : 35
     * queTypeName : 神经
     * queTypeRowID : 3
     */

    private String checked;
    private String queName;
    private String queRowID;
    private String queTypeName;
    private String queTypeRowID;
    private List<ChildsBean> childs;
    private boolean select;
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getQueName() {
        return queName;
    }

    public void setQueName(String queName) {
        this.queName = queName;
    }

    public String getQueRowID() {
        return queRowID;
    }

    public void setQueRowID(String queRowID) {
        this.queRowID = queRowID;
    }

    public String getQueTypeName() {
        return queTypeName;
    }

    public void setQueTypeName(String queTypeName) {
        this.queTypeName = queTypeName;
    }

    public String getQueTypeRowID() {
        return queTypeRowID;
    }

    public void setQueTypeRowID(String queTypeRowID) {
        this.queTypeRowID = queTypeRowID;
    }

    public List<ChildsBean> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildsBean> childs) {
        this.childs = childs;
    }

    public static class ChildsBean {
        /**
         * checked : 1
         * queName : 电解质不平衡-高血钾
         * queRowID : 36
         * queTypeName : 神经
         * queTypeRowID : 3
         */

        private String checked;
        private String queName;
        private String queRowID;
        private String queTypeName;
        private String queTypeRowID;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getChecked() {
            return checked;
        }

        public void setChecked(String checked) {
            this.checked = checked;
        }

        public String getQueName() {
            return queName;
        }

        public void setQueName(String queName) {
            this.queName = queName;
        }

        public String getQueRowID() {
            return queRowID;
        }

        public void setQueRowID(String queRowID) {
            this.queRowID = queRowID;
        }

        public String getQueTypeName() {
            return queTypeName;
        }

        public void setQueTypeName(String queTypeName) {
            this.queTypeName = queTypeName;
        }

        public String getQueTypeRowID() {
            return queTypeRowID;
        }

        public void setQueTypeRowID(String queTypeRowID) {
            this.queTypeRowID = queTypeRowID;
        }
    }
}