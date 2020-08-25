package com.dhcc.module.nurse.nurplan.bean;

import com.base.commlibs.bean.CommBean;
import com.base.commlibs.http.CommResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-20/11:41
 * @email:grs0515@163.com
 */
public class NurPlanGoalBean extends CommResult {

    private List<GoalListBean> goalList;

    /**
     * 获取勾选
     * @param data
     * @return
     */
    public static String getSelectBean(List<GoalListBean> data) {
        List<String> mStList = new ArrayList<>();
        for (GoalListBean datum : data) {
            if (datum.isSelect()) {
                mStList.add(datum.goalDR);
            }
        }
        if (mStList.size() > 0) {
            return mStList.toString();
        }
        return null;
    }

    public List<GoalListBean> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<GoalListBean> goalList) {
        this.goalList = goalList;
    }

    public static class GoalListBean extends CommBean {
        /**
         * createAt : 2020-08-04 17:26:55
         * goalDR : 1157
         * goalName : 住院期间无感染发生。
         * recordID : 1
         * sign : 护士01
         */

        private String createAt;
        private String goalDR;
        private String goalName;
        private String recordID;
        private String sign;
        private String status;

        public String getStatus() {
            return status == null ? "" : status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getGoalDR() {
            return goalDR;
        }

        public void setGoalDR(String goalDR) {
            this.goalDR = goalDR;
        }

        public String getGoalName() {
            return goalName;
        }

        public void setGoalName(String goalName) {
            this.goalName = goalName;
        }

        public String getRecordID() {
            return recordID;
        }

        public void setRecordID(String recordID) {
            this.recordID = recordID;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
