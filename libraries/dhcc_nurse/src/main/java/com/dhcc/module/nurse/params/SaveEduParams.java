package com.dhcc.module.nurse.params;

import com.base.commlibs.http.CommRequest;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202020-07-23/08:44
 * @email:grs0515@163.com
 */
public class SaveEduParams extends CommRequest {

    private static SaveEduParams mInstance = new SaveEduParams();
    /**
     * episodeId(p1): 324
     * Subject(p2): 入院宣教-入院须知、入院宣教-防跌倒坠床
     * Content(p3): 1介绍病区环境、各项规章制度，妥善保管贵重物品。
     * 2预防跌倒/坠床的注意事项。
     * 3介绍主管医生及责任护士，告知住院期间不得擅自外出及请假。
     * 4宣教饮食运动药物知识，入院期间按医嘱服药，自备药不许存放。
     * 5患者有吸烟、饮酒习惯，指导患者配合戒烟酒
     * 1 告知预防跌倒/坠床的注意事项。
     * 2 介绍主管医生及责任护士。
     * dddddddd
     * EducationDate(p4): 2020-07-23
     * EducationTime(p5): 08:47
     * EduItemList(p6): [{"id":"1","option":"手足/其他/本人/配偶\f444"},{"id":"4","option":"盲文/方言/身体语言"},{"id":"5","option":"其他\f6666"},{"id":"2","option":"不了解"},{"id":"6","option":"高中"}]
     * userId(p7): 10211
     * locId(p8): 151
     * wardId(p9): 3
     * id(p10):
     * SubjectIds(p11): [2,3]
     * eduTaskIds(p12): []
     */
    public String episodeId;
    public String Subject;
    public String Content;
    public String EducationDate;
    public String EducationTime;
    public String EduItemList;
    public String userId;
    public String locId;
    public String wardId;
    public String id = "";
    public String SubjectIds = "";
    public String eduTaskIds = "[]";

    private SaveEduParams() {
    }

    public static SaveEduParams getInstance() {
        return mInstance;
    }

    /**
     * 添加UserId,LocId 等
     * @return
     */
    @Override
    public HashMap<String, String> getProperties() {
        addUserId().addLocId().addWardId();
        return super.getProperties();
    }

    /**
     * 选项id
     */
    public static class EduTaskIdBean {
        public String id;
        public String option;
    }
}
