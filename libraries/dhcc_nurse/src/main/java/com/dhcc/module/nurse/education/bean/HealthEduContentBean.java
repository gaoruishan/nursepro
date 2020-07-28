package com.dhcc.module.nurse.education.bean;

import android.text.TextUtils;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-22/11:24
 * @email:grs0515@163.com
 */
public class HealthEduContentBean extends CommResult {

    private List<EduContentsBean> eduContents;

    public List<EduContentsBean> getEduContents() {
        return eduContents;
    }

    public void setEduContents(List<EduContentsBean> eduContents) {
        this.eduContents = eduContents;
    }

    public static class EduContentsBean {
        /**
         * content : ["1介绍病区环境、各项规章制度，妥善保管贵重物品。\n2预防跌倒/坠床的注意事项。\n3介绍主管医生及责任护士，告知住院期间不得擅自外出及请假。\n4宣教饮食运动药物知识，入院期间按医嘱服药，自备药不许存放。\n5患者有吸烟、饮酒习惯，指导患者配合戒烟酒"]
         * title : 入院须知
         */

        private String pid;
        private String subjectId;
        private String title;
        private List<String> content;

        public String getPid() {
            return pid == null ? "" : pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getSubjectId() {
            return subjectId == null ? "" : subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getTitle() {
            return title;
        }
        public String getSubjectTitle(List<EduSubjectListBean> eduSubjectList) {
            String subjectTitle = "";
            for (EduSubjectListBean bean : eduSubjectList) {
                if (bean.getId().equals(pid)) {
                    subjectTitle = bean.getDesc();
                    break;
                }
            }
            if(!TextUtils.isEmpty(subjectTitle)){
                return subjectTitle + "-" + title;
            }
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
    }
}
