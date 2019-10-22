package com.dhcc.module.infusion.message.bean;

import com.base.commlibs.http.CommResult;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MessageInfusionBean extends CommResult implements MultiItemEntity {


    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    //类型布局
    private int typeItem = TYPE_1;
    /**
     * InfusionTimeList : [{"PatName":"井尔","PatRegNo":"","PatSex":"男","overTime":"14919"}]
     * msg : 成功
     * msgcode :
     * status : 0
     */

    private List<InfusionTimeListBean> InfusionTimeList;


    @Override
    public int getItemType() {
        return typeItem;
    }

    public void setTypeItem(int typeItem) {
        this.typeItem = typeItem;
    }

    public List<InfusionTimeListBean> getInfusionTimeList() {
        return InfusionTimeList;
    }

    public void setInfusionTimeList(List<InfusionTimeListBean> InfusionTimeList) {
        this.InfusionTimeList = InfusionTimeList;
    }

    public static class InfusionTimeListBean {
        /**
         * PatName : 井尔
         * PatRegNo :
         * PatSex : 男
         * overTime : 14919
         */

        private String PatName;
        private String PatRegNo;
        private String PatSex;
        private String overTime;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
        public String getPatName() {
            return PatName;
        }

        public void setPatName(String PatName) {
            this.PatName = PatName;
        }

        public String getPatRegNo() {
            return PatRegNo;
        }

        public void setPatRegNo(String PatRegNo) {
            this.PatRegNo = PatRegNo;
        }

        public String getPatSex() {
            return PatSex;
        }

        public void setPatSex(String PatSex) {
            this.PatSex = PatSex;
        }

        public String getOverTime() {
            return overTime;
        }

        public void setOverTime(String overTime) {
            this.overTime = overTime;
        }
    }
}
