package com.dhcc.module.infusion.workarea.patrol.bean;

public class InfusionStateListBean {
        /**
         * InfusionState : 正常
         */

        private String InfusionState;
        // 1:显示输入框
        private String InfusionStateFlag;

    public String getInfusionStateFlag() {
        return InfusionStateFlag == null ? "" : InfusionStateFlag;
    }

    public void setInfusionStateFlag(String infusionStateFlag) {
        InfusionStateFlag = infusionStateFlag;
    }

    public String getInfusionState() {
            return InfusionState;
        }

        public void setInfusionState(String InfusionState) {
            this.InfusionState = InfusionState;
        }
    }
