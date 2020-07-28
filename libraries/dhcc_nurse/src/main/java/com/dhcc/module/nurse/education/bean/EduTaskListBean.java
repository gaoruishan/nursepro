package com.dhcc.module.nurse.education.bean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-24/12:06
 * @email:grs0515@163.com
 */
public class EduTaskListBean  {

    /**
     * eduContents : [{"content":["为了使您更好地配合手术治疗,我们为您提供以下指导: 1.\t树立战胜疾病的信心，保持心情舒畅。 2.\t手术前一天需要备皮、备血，做皮试等术前准备，故术前一天请您不要外出。如有药物过敏史，请告知医护工作人员。 3.\t手术前一天麻醉及手术医师需要患者及家属签字,请在病房等候。(麻醉签字是请手术室的麻醉师，手术签字请找主管医生。) 4.\t手术前一晚可进少量无渣饮食，术前晚22：00以后禁食禁饮。如有高血压病的患者，手术当日仍需服用降压药。 5.\t需要肠道准备的患者，护士会告知注意事项及使用方法。 6.\t手术前一晚应保证充足睡眠。 7.\t手术当日早晨请更换医院的清洁衣裤，贴身穿。 8.\t手术前必须去除金属饰品、手表及假牙等，搞好个人卫生，贵重物品请家属保管，请戴好腕带。 9.\t手术前请准备以下物品：一次性中单、便盆、尿壶。 10.\t手术当日，患者送手术室后，家属请回病房等候。"],"title":"手术前宣教"}]
     * planDate : 2020-07-21
     * subjectId : 8
     * taskId : 6
     */

    private String planDate;
    private String subjectId;
    //主题
    private String subjectTitle;
    private String taskId;
    private boolean select;
    private List<EduContentsBean> eduContents;


    public String getSubjectTitle() {
        return subjectTitle == null ? "" : subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }


    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<EduContentsBean> getEduContents() {
        return eduContents;
    }

    public void setEduContents(List<EduContentsBean> eduContents) {
        this.eduContents = eduContents;
    }


    public static class EduContentsBean {
        /**
         * content : ["为了使您更好地配合手术治疗,我们为您提供以下指导: 1.\t树立战胜疾病的信心，保持心情舒畅。 2.\t手术前一天需要备皮、备血，做皮试等术前准备，故术前一天请您不要外出。如有药物过敏史，请告知医护工作人员。 3.\t手术前一天麻醉及手术医师需要患者及家属签字,请在病房等候。(麻醉签字是请手术室的麻醉师，手术签字请找主管医生。) 4.\t手术前一晚可进少量无渣饮食，术前晚22：00以后禁食禁饮。如有高血压病的患者，手术当日仍需服用降压药。 5.\t需要肠道准备的患者，护士会告知注意事项及使用方法。 6.\t手术前一晚应保证充足睡眠。 7.\t手术当日早晨请更换医院的清洁衣裤，贴身穿。 8.\t手术前必须去除金属饰品、手表及假牙等，搞好个人卫生，贵重物品请家属保管，请戴好腕带。 9.\t手术前请准备以下物品：一次性中单、便盆、尿壶。 10.\t手术当日，患者送手术室后，家属请回病房等候。"]
         * title : 手术前宣教
         */

        private String title;
        private List<String> content;

        public String getTitle() {
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
