package com.base.commlibs.voiceUtils.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.bean
 * <p>
 * author Q
 * Date: 2021/4/29
 * Time:15:42
 */
public class VoicePatListBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * patList : [{"bedCode":"01","episodeId":"195","patName":"闭环演示(勿动)","regNo":"0000000134"},{"bedCode":"03","episodeId":"16","patName":"CDSS专用1","regNo":"0000000014"},{"bedCode":"04","episodeId":"27","patName":"张日间1","regNo":"0000000015"},{"bedCode":"05","episodeId":"141","patName":"涨日间2","regNo":"0000000045"},{"bedCode":"06","episodeId":"35","patName":"住院z1","regNo":"0000000031"},{"bedCode":"07","episodeId":"80","patName":"myh总院2","regNo":"0000000070"},{"bedCode":"08","episodeId":"364","patName":"王子问","regNo":"0000000214"},{"bedCode":"09","episodeId":"40","patName":"总院计费","regNo":"0000000034"},{"bedCode":"10","episodeId":"207","patName":"张三","regNo":"0000000139"},{"bedCode":"11","episodeId":"340","patName":"高龄","regNo":"0000000197"},{"bedCode":"12","episodeId":"164","patName":"jhm1001","regNo":"0000000120"},{"bedCode":"13","episodeId":"165","patName":"jhm1002","regNo":"0000000121"},{"bedCode":"14","episodeId":"91","patName":"杨晓阳","regNo":"0000000002"},{"bedCode":"15","episodeId":"1","patName":"zfmcs0707","regNo":"0000000001"},{"bedCode":"16","episodeId":"26","patName":"沈杰克","regNo":"0000000027"},{"bedCode":"17","episodeId":"244","patName":"张三三","regNo":"0000000017"},{"bedCode":"18","episodeId":"88","patName":"检查","regNo":"0000000071"},{"bedCode":"19","episodeId":"626","patName":"演示数据1","regNo":"0000000273"},{"bedCode":"20","episodeId":"112","patName":"刘新雨（演示勿动）","regNo":"0000000089"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<PatListBean> patList;

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

    public List<PatListBean> getPatList() {
        return patList;
    }

    public void setPatList(List<PatListBean> patList) {
        this.patList = patList;
    }

    public static class PatListBean {
        /**
         * bedCode : 01
         * episodeId : 195
         * patName : 闭环演示(勿动)
         * regNo : 0000000134
         */

        private String bedCode;
        private String episodeId;
        private String patName;
        private String regNo;
        private String RQDDate;

        public String getRQDDate() {
            return RQDDate;
        }

        public void setRQDDate(String RQDDate) {
            this.RQDDate = RQDDate;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
        }

        public String getPatName() {
            return patName;
        }

        public void setPatName(String patName) {
            this.patName = patName;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }
    }
}
