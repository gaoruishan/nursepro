package com.dhcc.module.nurse.nurplan.bean;

import com.base.commlibs.http.CommResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-24/09:01
 * @email:grs0515@163.com
 */
public class NurPlanAddInterveBean extends CommResult {

    private List<AddListBean> addList;

    /**
     * 获取选择列表
     * @param addList
     * @return
     */
    public static List<String> getSelectData(List<AddListBean> addList) {
        List<String> mStringList = new ArrayList<>();
        if (addList != null) {
            for (AddListBean bean : addList) {
                mStringList.add(bean.getIntShortName());
            }
        }
        return mStringList;
    }

    public List<AddListBean> getAddList() {
        return addList;
    }

    public void setAddList(List<AddListBean> addList) {
        this.addList = addList;
    }

    public static class AddListBean {
        /**
         * intCode : A000
         * intShortName : 评估活动时的活动耐力
         * intTypeDR : 2
         * rowID : 1
         */

        private String intCode;
        private String intShortName;
        private String intTypeDR;
        private String rowID;

        public String getIntCode() {
            return intCode;
        }

        public void setIntCode(String intCode) {
            this.intCode = intCode;
        }

        public String getIntShortName() {
            return intShortName;
        }

        public void setIntShortName(String intShortName) {
            this.intShortName = intShortName;
        }

        public String getIntTypeDR() {
            return intTypeDR;
        }

        public void setIntTypeDR(String intTypeDR) {
            this.intTypeDR = intTypeDR;
        }

        public String getRowID() {
            return rowID;
        }

        public void setRowID(String rowID) {
            this.rowID = rowID;
        }
    }
}
