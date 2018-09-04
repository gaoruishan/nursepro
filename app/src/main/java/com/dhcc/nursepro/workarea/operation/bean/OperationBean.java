package com.dhcc.nursepro.workarea.operation.bean;

import java.util.List;

public class OperationBean {
    /**
     * msg : 成功
     * msgcode : 999999
     * opList : [{"applyTime":"2018-03-23","opApplyid":"2","opDoctor":"医生01,","opRoom":"103手术间 首台","opnName":"三支血管的操作","patinfo":"12 zxw01","state":"术毕"},{"applyTime":"2018-03-23","opApplyid":"3","opDoctor":"医生01,","opRoom":" ","opnName":"输注重组蛋白","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-23","opApplyid":"4","opDoctor":"医生01,","opRoom":" ","opnName":"一氧化氮疗法","patinfo":"12 zxw01","state":""},{"applyTime":"2018-03-23","opApplyid":"6","opDoctor":"医生01,","opRoom":" ","opnName":"一氧化氮疗法","patinfo":"12 zxw01","state":""},{"applyTime":"2018-03-23","opApplyid":"7","opDoctor":"医生01,","opRoom":" ","opnName":"一氧化氮疗法","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-26","opApplyid":"9","opDoctor":"医生01,","opRoom":" ","opnName":"心脏治疗性超声","patinfo":"02 王伟测试","state":"申请"},{"applyTime":"2018-03-26","opApplyid":"10","opDoctor":"医生02,","opRoom":" ","opnName":"一氧化氮疗法","patinfo":"02 王伟测试","state":""},{"applyTime":"2018-03-26","opApplyid":"11","opDoctor":"医生02,","opRoom":"101手术间 首台","opnName":"颈部血管治疗性超声","patinfo":"02 王伟测试","state":"完成"},{"applyTime":"2018-03-26","opApplyid":"14","opDoctor":"医生01,","opRoom":" ","opnName":"输注重组蛋白","patinfo":"12 zxw01","state":"拒绝"},{"applyTime":"2018-03-26","opApplyid":"15","opDoctor":"医生01,","opRoom":" ","opnName":"一氧化氮疗法","patinfo":"12 zxw01","state":"拒绝"},{"applyTime":"2018-03-27","opApplyid":"16","opDoctor":"医生02,","opRoom":" ","opnName":"输注重组蛋白","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-27","opApplyid":"18","opDoctor":"医生01,","opRoom":" ","opnName":"一氧化氮疗法","patinfo":"12 zxw01","state":""},{"applyTime":"2018-03-27","opApplyid":"19","opDoctor":"医生01,","opRoom":"102手术间 接台2","opnName":"输注白细胞介素","patinfo":"12 zxw01","state":"完成"},{"applyTime":"2018-03-27","opApplyid":"20","opDoctor":"医生02,","opRoom":"102手术间 接台5","opnName":"经皮交通动脉血管成形术","patinfo":"22 ly002","state":"完成"},{"applyTime":"2018-03-27","opApplyid":"25","opDoctor":"陆玉梅,","opRoom":" ","opnName":"注射阿地白细胞介素","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-27","opApplyid":"26","opDoctor":"医生02,","opRoom":" ","opnName":"注射噁唑烷酮类抗生素","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-28","opApplyid":"29","opDoctor":"医生01,","opRoom":" ","opnName":"注射阿地白细胞介素","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-28","opApplyid":"30","opDoctor":"医生01,","opRoom":" ","opnName":"注射阿地白细胞介素","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-28","opApplyid":"31","opDoctor":"孟玉秀,","opRoom":" ","opnName":"注射噁唑烷酮类抗生素","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-28","opApplyid":"32","opDoctor":"陆玉梅,","opRoom":" ","opnName":"经活体外血管治疗","patinfo":"12 zxw01","state":"申请"},{"applyTime":"2018-03-28","opApplyid":"37","opDoctor":"医生01,","opRoom":" ","opnName":"头部血管治疗性超声","patinfo":"35 yw曹芷若","state":""},{"applyTime":"2018-03-28","opApplyid":"38","opDoctor":"医生01,","opRoom":" ","opnName":"颈部血管治疗性超声","patinfo":"29 韦丹寒","state":""},{"applyTime":"2018-03-28","opApplyid":"39","opDoctor":"医生02,","opRoom":" ","opnName":"化学治疗药物置入","patinfo":"17 150yh","state":"申请"},{"applyTime":"2018-03-30","opApplyid":"45","opDoctor":"医生01,","opRoom":" ","opnName":"单克隆抗体治疗","patinfo":"08 智勇双全","state":"申请"},{"applyTime":"2018-04-02","opApplyid":"50","opDoctor":"医生02,","opRoom":" ","opnName":"心脏治疗性超声^输注重组蛋白","patinfo":"12 zxw01","state":"拒绝"},{"applyTime":"2018-04-03","opApplyid":"51","opDoctor":"医生01,","opRoom":"101手术间 接台2","opnName":"脑室穿刺术","patinfo":"13 lh032603之婴","state":"完成"},{"applyTime":"2018-04-03","opApplyid":"52","opDoctor":"医生01,","opRoom":"101手术间 首台","opnName":"头部血管治疗性超声","patinfo":"39 lc2018040301","state":"完成"},{"applyTime":"2018-04-03","opApplyid":"57","opDoctor":"陆玉梅,","opRoom":"101手术间 接台2","opnName":"头部血管治疗性超声","patinfo":"02 王伟测试","state":"安排"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<OpListBean> opList;

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

    public List<OpListBean> getOpList() {
        return opList;
    }

    public void setOpList(List<OpListBean> opList) {
        this.opList = opList;
    }

    public static class OpListBean {
        /**
         * applyTime : 2018-03-23
         * opApplyid : 2
         * opDoctor : 医生01,
         * opRoom : 103手术间 首台
         * opnName : 三支血管的操作
         * patinfo : 12 zxw01
         * state : 术毕
         */

        private String applyTime;
        private String opApplyid;
        private String opDoctor;
        private String opRoom;
        private String opnName;
        private String patinfo;
        private String state;

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getOpApplyid() {
            return opApplyid;
        }

        public void setOpApplyid(String opApplyid) {
            this.opApplyid = opApplyid;
        }

        public String getOpDoctor() {
            return opDoctor;
        }

        public void setOpDoctor(String opDoctor) {
            this.opDoctor = opDoctor;
        }

        public String getOpRoom() {
            return opRoom;
        }

        public void setOpRoom(String opRoom) {
            this.opRoom = opRoom;
        }

        public String getOpnName() {
            return opnName;
        }

        public void setOpnName(String opnName) {
            this.opnName = opnName;
        }

        public String getPatinfo() {
            return patinfo;
        }

        public void setPatinfo(String patinfo) {
            this.patinfo = patinfo;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
