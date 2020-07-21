package com.dhcc.module.nurse.education.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-17/17:54
 * @email:grs0515@163.com
 */
public class HealthEduBean extends CommResult {


    public static final int TYPE_1 = 1;
    /**
     * eduItemList : [{"blankFlag":"1","id":"1","name":"宣教对象","options":"本人/配偶/父母/手足/外佣、看护","type":"1"},{"blankFlag":"","id":"2","name":"疾病认知","options":"了解/部分了解/不了解","type":"0"},{"blankFlag":"1","id":"4","name":"语言","options":"普通话/方言/英语/盲文/身体语言/哑语","type":"1"},{"blankFlag":"1","id":"5","name":"国籍","options":"本籍","type":"1"},{"blankFlag":"","id":"6","name":"教育程度","options":"小学/初中/高中/中专/大专/大学及以上/文盲","type":"0"}]
     * eduSubjectList : [{"desc":"入院宣教","hospID":"2","id":"1","pid":"0","relateData":"","relateType":"","startDate":"2020-07-20","startFlag":"1","stopDate":""},{"desc":"入院须知","hospID":"2","id":"2","pid":"1","relateData":["1"],"relateType":"2","startDate":"2020-07-20","startFlag":"1","stopDate":""},{"desc":"防跌倒坠床","hospID":"2","id":"3","pid":"1","relateData":"","relateType":"","startDate":"2020-07-20","startFlag":"1","stopDate":""},{"desc":"防压疮","hospID":"2","id":"4","pid":"1","relateData":"","relateType":"","startDate":"2020-07-20","startFlag":"1","stopDate":""},{"desc":"出院宣教","hospID":"2","id":"5","pid":"0","relateData":"","relateType":"","startDate":"2020-07-20","startFlag":"1","stopDate":""},{"desc":"出院须知","hospID":"2","id":"6","pid":"5","relateData":["11356||1"],"relateType":"1","startDate":"2020-07-20","startFlag":"1","stopDate":""},{"desc":"手术宣教","hospID":"2","id":"7","pid":"0","relateData":"","relateType":"","startDate":"2020-07-20","startFlag":"1","stopDate":""},{"desc":"手术前宣教","hospID":"2","id":"8","pid":"7","relateData":["83"],"relateType":"2","startDate":"2020-07-20","startFlag":"1","stopDate":""}]
     * eduTasjList :
     * educationList : {"columns":[{"dataIndex":"eduDateTime","title":"宣教时间"},{"dataIndex":"eduSubject","title":"主题"},{"dataIndex":"eduContent","title":"宣教内容"},{"dataIndex":"option1","title":"宣教对象"},{"dataIndex":"option2","title":"疾病认知"},{"dataIndex":"option4","title":"语言"},{"dataIndex":"option5","title":"国籍"},{"dataIndex":"option6","title":"教育程度"},{"dataIndex":"nurseSign","title":"宣教护士"}],"data":[{"eduContent":"1介绍病区环境、各项规章制度，妥善保管贵重物品。\r2预防跌倒/坠床的注意事项。\r3介绍主管医生及责任护士，告知住院期间不得擅自外出及请假。\r4宣教饮食运动药物知识，入院期间按医嘱服药，自备药不许存放。\r5患者有吸烟、饮酒习惯，指导患者配合戒烟酒\r1 告知预防跌倒/坠床的注意事项。\r2 介绍主管医生及责任护士。\r555","eduDateTime":"2020-07-20 21:06","eduRecordId":"4","eduSubject":"入院宣教-入院须知、入院宣教-防跌倒坠床","nurseSign":"护士01","option1":"手足/其他\f444","option2":"不了解","option4":"盲文/方言/身体语言","option5":"其他\f6666","option6":"高中","subjectIds":"[2,3]"},{"eduContent":"1介绍病区环境、各项规章制度，妥善保管贵重物品。\r2预防跌倒/坠床的注意事项。\r3介绍主管医生及责任护士，告知住院期间不得擅自外出及请假。\r4宣教饮食运动药物知识，入院期间按医嘱服药，自备药不许存放。\r5患者有吸烟、饮酒习惯，指导患者配合戒烟酒","eduDateTime":"2020-07-20 21:07","eduRecordId":"5","eduSubject":"入院宣教-入院须知","nurseSign":"护士01","option1":"手足/其他/外佣、看护\f444","option2":"不了解","option4":"盲文/方言/身体语言","option5":"其他\f6666","option6":"高中","subjectIds":"[2]"},{"eduContent":"1介绍病区环境、各项规章制度，妥善保管贵重物品。\r2预防跌倒/坠床的注意事项。\r3介绍主管医生及责任护士，告知住院期间不得擅自外出及请假。\r4宣教饮食运动药物知识，入院期间按医嘱服药，自备药不许存放。\r5患者有吸烟、饮酒习惯，指导患者配合戒烟酒\r1 告知预防跌倒/坠床的注意事项。\r2 介绍主管医生及责任护士。","eduDateTime":"2020-07-20 21:09","eduRecordId":"6","eduSubject":"入院宣教-入院须知、入院宣教-防跌倒坠床","nurseSign":"护士01","option1":"手足/其他\f444","option2":"不了解","option4":"盲文/方言/身体语言","option5":"其他\f6666","option6":"高中","subjectIds":"[2,3]"}]}
     */

    private String eduTasjList;
    private EducationListBean educationList;
    private List<EduItemListBean> eduItemList;
    private List<EduSubjectListBean> eduSubjectList;

    public String getEduTasjList() {
        return eduTasjList;
    }

    public void setEduTasjList(String eduTasjList) {
        this.eduTasjList = eduTasjList;
    }

    public EducationListBean getEducationList() {
        return educationList;
    }

    public void setEducationList(EducationListBean educationList) {
        this.educationList = educationList;
    }

    public List<EduItemListBean> getEduItemList() {
        return eduItemList;
    }

    public void setEduItemList(List<EduItemListBean> eduItemList) {
        this.eduItemList = eduItemList;
    }

    public List<EduSubjectListBean> getEduSubjectList() {
        return eduSubjectList;
    }

    public void setEduSubjectList(List<EduSubjectListBean> eduSubjectList) {
        this.eduSubjectList = eduSubjectList;
    }

    public static class EduItemListBean {
        /**
         * blankFlag : 1
         * id : 1
         * name : 宣教对象
         * options : 本人/配偶/父母/手足/外佣、看护
         * type : 1
         */

        private String blankFlag;
        private String id;
        private String name;
        private String options;
        private String type;

        public String getBlankFlag() {
            return blankFlag;
        }

        public void setBlankFlag(String blankFlag) {
            this.blankFlag = blankFlag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class EduSubjectListBean {

        /**
         * desc : 入院须知
         * hospID : 2
         * id : 2
         * pid : 1
         * relateData : ["1"]
         * relateType : 2
         * startDate : 2020-07-20
         * startFlag : 1
         * stopDate :
         */

        private String desc;
        private String hospID;
        private String id;
        private String pid;
        private String relateType;
        private String startDate;
        private String startFlag;
        private String stopDate;
        private Object relateData;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getHospID() {
            return hospID;
        }

        public void setHospID(String hospID) {
            this.hospID = hospID;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getRelateType() {
            return relateType;
        }

        public void setRelateType(String relateType) {
            this.relateType = relateType;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStartFlag() {
            return startFlag;
        }

        public void setStartFlag(String startFlag) {
            this.startFlag = startFlag;
        }

        public String getStopDate() {
            return stopDate;
        }

        public void setStopDate(String stopDate) {
            this.stopDate = stopDate;
        }

        public Object getRelateData() {
            return relateData;
        }

        public void setRelateData(Object relateData) {
            this.relateData = relateData;
        }
    }
}
