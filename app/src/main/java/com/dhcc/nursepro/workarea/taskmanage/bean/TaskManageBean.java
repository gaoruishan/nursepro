package com.dhcc.nursepro.workarea.taskmanage.bean;

import com.base.commlibs.http.CommResult;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-05-11/15:13
 * @email:grs0515@163.com
 */
public class TaskManageBean extends CommResult  {

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    private List<TaskPatListBean> taskPatList;
    private List<TaskSheetListBean> taskSheetList;

    public List<TaskPatListBean> getTaskPatList() {
        return taskPatList;
    }

    public void setTaskPatList(List<TaskPatListBean> taskPatList) {
        this.taskPatList = taskPatList;
    }

    public List<TaskSheetListBean> getTaskSheetList() {
        return taskSheetList;
    }

    public void setTaskSheetList(List<TaskSheetListBean> taskSheetList) {
        this.taskSheetList = taskSheetList;
    }

    public static class TaskPatListBean implements MultiItemEntity{

        /**
         * bedCode : 19
         * patName : MYH入院2
         * patRegNo : 0000000171
         * sheetList : []
         * sheetOrdNum : 0
         */

        private String bedCode;
        private String patName;
        private String patRegNo;
        private String sheetOrdNum;
        private List<SheetPatListBean> sheetList;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getPatName() {
            return patName;
        }

        public void setPatName(String patName) {
            this.patName = patName;
        }

        public String getPatRegNo() {
            return patRegNo;
        }

        public void setPatRegNo(String patRegNo) {
            this.patRegNo = patRegNo;
        }

        public String getSheetOrdNum() {
            return sheetOrdNum;
        }

        public void setSheetOrdNum(String sheetOrdNum) {
            this.sheetOrdNum = sheetOrdNum;
        }

        public List<SheetPatListBean> getSheetList() {
            return sheetList;
        }

        public void setSheetList(List<SheetPatListBean> sheetList) {
            this.sheetList = sheetList;
        }

        @Override
        public int getItemType() {
            return TYPE_1;
        }
    }

    public static class TaskSheetListBean implements MultiItemEntity  {
        /**
         * sheetCode : CQSYD
         * sheetDesc : 输液单
         * sheetOrdNum : 8
         * sheetPatList : [{"bedCode":"20","patName":"住院N3","patRegNo":"0000000212","sheetCode":"CQSYD","sheetDesc":"输液单","sheetPatOrdNum":"3"},{"bedCode":"22","patName":"zfm0420","patRegNo":"0000000261","sheetCode":"CQSYD","sheetDesc":"输液单","sheetPatOrdNum":"3"},{"bedCode":"50","patName":"卢珊珊","patRegNo":"0000000071","sheetCode":"CQSYD","sheetDesc":"输液单","sheetPatOrdNum":"2"}]
         */

        private String sheetCode;
        private String sheetDesc;
        private String sheetOrdNum;
        private List<SheetPatListBean> sheetPatList;

        public String getSheetCode() {
            return sheetCode;
        }

        public void setSheetCode(String sheetCode) {
            this.sheetCode = sheetCode;
        }

        public String getSheetDesc() {
            return sheetDesc;
        }

        public void setSheetDesc(String sheetDesc) {
            this.sheetDesc = sheetDesc;
        }

        public String getSheetOrdNum() {
            return sheetOrdNum;
        }

        public void setSheetOrdNum(String sheetOrdNum) {
            this.sheetOrdNum = sheetOrdNum;
        }

        public List<SheetPatListBean> getSheetPatList() {
            return sheetPatList;
        }

        public void setSheetPatList(List<SheetPatListBean> sheetPatList) {
            this.sheetPatList = sheetPatList;
        }

        @Override
        public int getItemType() {
            return TYPE_2;
        }
    }
}
