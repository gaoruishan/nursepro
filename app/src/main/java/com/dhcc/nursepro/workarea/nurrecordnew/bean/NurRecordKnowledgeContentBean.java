package com.dhcc.nursepro.workarea.nurrecordnew.bean;

import java.util.List;

public class NurRecordKnowledgeContentBean {

    /**
     * Status : 0
     * knowledgeContent : [[{"id":"FreeText1","title":"上帝发誓v","type":"FreeText"}],[{"id":"FreeText2","title":"新建文本","type":"FreeText"}],[{"id":"FreeText3","title":"OOO","type":"FreeText"}],[{"id":"FreeText4","title":"999","type":"FreeText"}],[{"id":"FreeText5","title":"III","type":"FreeText"}],[{"id":"FreeText6","title":"66","type":"FreeText"}],[{"id":"FreeText7","title":"jjj","type":"FreeText"}],[{"data":[{"id":"1","text":"da"},{"id":"2","text":"ddd"},{"id":"3","text":"bc"}],"default":"bc","id":"O2","title":"心别","type":"O"}],[{"id":"FreeText8","title":"iii","type":"FreeText"}],[{"id":"FreeText9","title":"顶顶顶顶顶","type":"FreeText"}],[{"id":"FreeText10","title":"对对都","type":"FreeText"}],[{"id":"FreeText11","title":"地方得到的","type":"FreeText"}],[{"id":"FreeText12","title":"dd","type":"FreeText"}],[{"id":"FreeText13","title":"yyy","type":"FreeText"}],[{"data":[{"id":"1","text":"R"},{"id":"2","text":"B"}],"default":"","id":"O10","title":"RR","type":"O"}],[{"id":"FreeText14","title":"HH","type":"FreeText"}],[{"data":[{"id":"1","text":"y"},{"id":"2","text":"y"},{"id":"3","text":"u"}],"default":"bc","id":"M16","title":"tyu","type":"M"}],[{"id":"FreeText15","title":"UUU","type":"FreeText"}]]
     * msg : 成功
     * msgcode : 999999
     */

    private String Status;
    private String msg;
    private String msgcode;
    /**
     * id : FreeText1
     * title : 上帝发誓v
     * type : FreeText
     */

    private List<List<KnowledgeContentBean>> knowledgeContent;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

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

    public List<List<KnowledgeContentBean>> getKnowledgeContent() {
        return knowledgeContent;
    }

    public void setKnowledgeContent(List<List<KnowledgeContentBean>> knowledgeContent) {
        this.knowledgeContent = knowledgeContent;
    }

    public static class KnowledgeContentBean {
        private String id;
        private String title;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
