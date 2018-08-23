package com.dhcc.nursepro.workarea.checkandlab.bean;

import java.util.List;

public class LabReslutDetailBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * resultDetail : [{"resultAbFlag":"L","resultName":"白细胞","resultRefRanges":"4-10","resultValue":"0.00 *10^9/L"},{"resultAbFlag":"","resultName":"血红蛋白","resultRefRanges":"120-160","resultValue":"140.0 g/dl"},{"resultAbFlag":"","resultName":"中值细胞%","resultRefRanges":"","resultValue":"50 "},{"resultAbFlag":"","resultName":"红细胞","resultRefRanges":"","resultValue":"少许/HP "},{"resultAbFlag":"","resultName":"中性粒细胞数","resultRefRanges":"2-7.7","resultValue":"2.00 *10^9/L"},{"resultAbFlag":"","resultName":"中性粒细胞比率","resultRefRanges":"45-70","resultValue":"50.00 %"},{"resultAbFlag":"","resultName":"淋巴细胞比率","resultRefRanges":"20-40","resultValue":"30.00 %"},{"resultAbFlag":"","resultName":"中值细胞#","resultRefRanges":"","resultValue":"11 "},{"resultAbFlag":"","resultName":"红细胞压积","resultRefRanges":"38-50","resultValue":"40.0 %"},{"resultAbFlag":"","resultName":"淋巴细胞数","resultRefRanges":"0.8-4","resultValue":"3.00 *10^9/L"},{"resultAbFlag":"","resultName":"红细胞平均血红蛋白量","resultRefRanges":"27-31","resultValue":"30.00 pg"},{"resultAbFlag":"","resultName":"红细胞平均血红蛋白浓度","resultRefRanges":"320-360","resultValue":"330.00 g/L"},{"resultAbFlag":"","resultName":"红细胞分布宽度","resultRefRanges":"37-50","resultValue":"40.00 %"},{"resultAbFlag":"","resultName":"红细胞分布宽度-CV值","resultRefRanges":"11.9-15","resultValue":"14.00 %"},{"resultAbFlag":"","resultName":"血小板","resultRefRanges":"100-300","resultValue":"200.00 *10^9/L"},{"resultAbFlag":"","resultName":"血小板压积","resultRefRanges":"0.13-0.43","resultValue":"0.20 %"},{"resultAbFlag":"","resultName":"血小板平均体积","resultRefRanges":"9-13","resultValue":"10.00 fL"},{"resultAbFlag":"","resultName":"血小板分布宽度","resultRefRanges":"9-17","resultValue":"12.00 fL"},{"resultAbFlag":"","resultName":"大血小板比率","resultRefRanges":"13-43","resultValue":"30.00 %"},{"resultAbFlag":"","resultName":"红细胞平均体积","resultRefRanges":"82-100","resultValue":"90.00 fL"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<ResultDetailBean> resultDetail;

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

    public List<ResultDetailBean> getResultDetail() {
        return resultDetail;
    }

    public void setResultDetail(List<ResultDetailBean> resultDetail) {
        this.resultDetail = resultDetail;
    }

    public static class ResultDetailBean {
        /**
         * resultAbFlag : L
         * resultName : 白细胞
         * resultRefRanges : 4-10
         * resultValue : 0.00 *10^9/L
         */

        private String resultAbFlag;
        private String resultName;
        private String resultRefRanges;
        private String resultValue;

        public String getResultAbFlag() {
            return resultAbFlag;
        }

        public void setResultAbFlag(String resultAbFlag) {
            this.resultAbFlag = resultAbFlag;
        }

        public String getResultName() {
            return resultName;
        }

        public void setResultName(String resultName) {
            this.resultName = resultName;
        }

        public String getResultRefRanges() {
            return resultRefRanges;
        }

        public void setResultRefRanges(String resultRefRanges) {
            this.resultRefRanges = resultRefRanges;
        }

        public String getResultValue() {
            return resultValue;
        }

        public void setResultValue(String resultValue) {
            this.resultValue = resultValue;
        }
    }
}
