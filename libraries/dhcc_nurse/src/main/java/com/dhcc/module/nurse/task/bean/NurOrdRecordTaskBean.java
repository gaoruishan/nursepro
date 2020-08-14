package com.dhcc.module.nurse.task.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/12
 * Time:15:48
 */
public class NurOrdRecordTaskBean extends CommResult {

    private List<TaskSetListBean> taskSetList;

    public List<TaskSetListBean> getTaskSetList() {
        return taskSetList;
    }

    public void setTaskSetList(List<TaskSetListBean> taskSetList) {
        this.taskSetList = taskSetList;
    }

    public static class TaskSetListBean {
        /**
         * data : {"childItemList":[],"exeNoteList":[],"noteList":[],"subItemList":[{"subItemDesc":"","subItemId":"115||1","subItemName":"心跳增快","subItemSortNo":"1","subPositionId":"1","subWidgetType":"2"},{"subItemDesc":"","subItemId":"115||2","subItemName":"心律不整","subItemSortNo":"2","subPositionId":"2","subWidgetType":"2"},{"subItemDesc":"","subItemId":"115||3","subItemName":"低血压","subItemSortNo":"3","subPositionId":"3","subWidgetType":"2"},{"subItemDesc":"","subItemId":"115||4","subItemName":"冒冷汗","subItemSortNo":"4","subPositionId":"4","subWidgetType":"2"},{"subItemDesc":"","subItemId":"115||5","subItemName":"躁动不安","subItemSortNo":"5","subPositionId":"5","subWidgetType":"2"},{"subItemDesc":"","subItemId":"115||6","subItemName":"呼吸困难","subItemSortNo":"6","subPositionId":"6","subWidgetType":"2"},{"subItemDesc":"","subItemId":"115||7","subItemName":"尿量减少","subItemSortNo":"7","subPositionId":"7","subWidgetType":"2"},{"subItemDesc":"","subItemId":"115||8","subItemName":"酸血症","subItemSortNo":"8","subPositionId":"8","subWidgetType":"2"}]}
         * itemId : 115
         * itemName : 心输出量减少
         * itemRequired : N
         * itemUnit :
         * itemUrl :
         * itemValue :
         * positionId : 1
         * valueType :
         * widgetType : 2
         */

        private DataBean data;
        private String itemId;
        private String itemName;
        private String itemRequired;
        private String itemUnit;
        private String itemUrl;
        private String itemValue;
        private String positionId;
        private String valueType;
        private String widgetType;
        private String noteSelec;

        public String getNoteSelec() {
            return noteSelec;
        }

        public void setNoteSelec(String noteSelec) {
            this.noteSelec = noteSelec;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemRequired() {
            return itemRequired;
        }

        public void setItemRequired(String itemRequired) {
            this.itemRequired = itemRequired;
        }

        public String getItemUnit() {
            return itemUnit;
        }

        public void setItemUnit(String itemUnit) {
            this.itemUnit = itemUnit;
        }

        public String getItemUrl() {
            return itemUrl;
        }

        public void setItemUrl(String itemUrl) {
            this.itemUrl = itemUrl;
        }

        public String getItemValue() {
            return itemValue;
        }

        public void setItemValue(String itemValue) {
            this.itemValue = itemValue;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public String getValueType() {
            return valueType;
        }

        public void setValueType(String valueType) {
            this.valueType = valueType;
        }

        public String getWidgetType() {
            return widgetType;
        }

        public void setWidgetType(String widgetType) {
            this.widgetType = widgetType;
        }

        public static class DataBean {
            private List<?> childItemList;
            private List<?> exeNoteList;
            private List<?> noteList;
            private List<SubItemListBean> subItemList;

            public List<?> getChildItemList() {
                return childItemList;
            }

            public void setChildItemList(List<?> childItemList) {
                this.childItemList = childItemList;
            }

            public List<?> getExeNoteList() {
                return exeNoteList;
            }

            public void setExeNoteList(List<?> exeNoteList) {
                this.exeNoteList = exeNoteList;
            }

            public List<?> getNoteList() {
                return noteList;
            }

            public void setNoteList(List<?> noteList) {
                this.noteList = noteList;
            }

            public List<SubItemListBean> getSubItemList() {
                return subItemList;
            }

            public void setSubItemList(List<SubItemListBean> subItemList) {
                this.subItemList = subItemList;
            }

            public static class SubItemListBean {
                /**
                 * subItemDesc :
                 * subItemId : 115||1
                 * subItemName : 心跳增快
                 * subItemSortNo : 1
                 * subPositionId : 1
                 * subWidgetType : 2
                 */

                private String subItemDesc;
                private String subItemId;
                private String subItemName;
                private String subItemSortNo;
                private String subPositionId;
                private String subWidgetType;
                private String subSelec="0";

                public String getSubSelec() {
                    return subSelec;
                }

                public void setSubSelec(String subSelec) {
                    this.subSelec = subSelec;
                }

                public String getSubItemDesc() {
                    return subItemDesc;
                }

                public void setSubItemDesc(String subItemDesc) {
                    this.subItemDesc = subItemDesc;
                }

                public String getSubItemId() {
                    return subItemId;
                }

                public void setSubItemId(String subItemId) {
                    this.subItemId = subItemId;
                }

                public String getSubItemName() {
                    return subItemName;
                }

                public void setSubItemName(String subItemName) {
                    this.subItemName = subItemName;
                }

                public String getSubItemSortNo() {
                    return subItemSortNo;
                }

                public void setSubItemSortNo(String subItemSortNo) {
                    this.subItemSortNo = subItemSortNo;
                }

                public String getSubPositionId() {
                    return subPositionId;
                }

                public void setSubPositionId(String subPositionId) {
                    this.subPositionId = subPositionId;
                }

                public String getSubWidgetType() {
                    return subWidgetType;
                }

                public void setSubWidgetType(String subWidgetType) {
                    this.subWidgetType = subWidgetType;
                }
            }
        }
    }
}
