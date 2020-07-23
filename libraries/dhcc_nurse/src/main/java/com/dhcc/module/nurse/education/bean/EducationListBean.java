package com.dhcc.module.nurse.education.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class EducationListBean {
    private List<ColumnsBean> columns;
    private List<DataBean> data;

    public List<ColumnsBean> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnsBean> columns) {
        this.columns = columns;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ColumnsBean  {
        /**
         * dataIndex : dateTime
         * title : 宣教时间
         */

        private String dataIndex;
        private String title;

        public String getDataIndex() {
            return dataIndex;
        }

        public void setDataIndex(String dataIndex) {
            this.dataIndex = dataIndex;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class DataBean implements MultiItemEntity, Serializable {
        /**
         * eduContent : 1介绍病区环境、各项规章制度，妥善保管贵重物品。2预防跌倒/坠床的注意事项。3介绍主管医生及责任护士，告知住院期间不得擅自外出及请假。4宣教饮食运动药物知识，入院期间按医嘱服药，自备药不许存放。5患者有吸烟、饮酒习惯，指导患者配合戒烟酒1 告知预防跌倒/坠床的注意事项。2 介绍主管医生及责任护士。555
         * dateTime : 2020-07-20 21:06
         * eduRecordId : 4
         * eduSubject : 入院宣教-入院须知、入院宣教-防跌倒坠床
         * nurseSign : 护士01
         * option1 : 手足/其他444
         * option2 : 不了解
         * option4 : 盲文/方言/身体语言
         * option5 : 其他6666
         * option6 : 高中
         * subjectIds : [2,3]
         */

        private String eduContent;
        private String eduDateTime;
        private String eduRecordId;
        private String eduSubject;
        private String nurseSign;
        private String option1;
        private String option2;
        private String option4;
        private String option5;
        private String option6;
        private String subjectIds;


        public String getEduContent() {
            return eduContent;
        }

        public void setEduContent(String eduContent) {
            this.eduContent = eduContent;
        }

        public String getEduDateTime() {
            return eduDateTime;
        }

        public void setEduDateTime(String eduDateTime) {
            this.eduDateTime = eduDateTime;
        }

        public String getEduRecordId() {
            return eduRecordId;
        }

        public void setEduRecordId(String eduRecordId) {
            this.eduRecordId = eduRecordId;
        }

        public String getEduSubject() {
            return eduSubject;
        }

        public void setEduSubject(String eduSubject) {
            this.eduSubject = eduSubject;
        }

        public String getNurseSign() {
            return nurseSign;
        }

        public void setNurseSign(String nurseSign) {
            this.nurseSign = nurseSign;
        }

        public String getOption1() {
            return option1;
        }

        public void setOption1(String option1) {
            this.option1 = option1;
        }

        public String getOption2() {
            return option2;
        }

        public void setOption2(String option2) {
            this.option2 = option2;
        }

        public String getOption4() {
            return option4;
        }

        public void setOption4(String option4) {
            this.option4 = option4;
        }

        public String getOption5() {
            return option5;
        }

        public void setOption5(String option5) {
            this.option5 = option5;
        }

        public String getOption6() {
            return option6;
        }

        public void setOption6(String option6) {
            this.option6 = option6;
        }

        public String getSubjectIds() {
            return subjectIds;
        }

        public void setSubjectIds(String subjectIds) {
            this.subjectIds = subjectIds;
        }

        @Override
        public int getItemType() {
            return HealthEduBean.TYPE_1;
        }
    }
}