package com.dhcc.module.nurse.nurplan;

import android.os.Bundle;

import com.dhcc.module.nurse.BaseBundleData;
import com.dhcc.module.nurse.nurplan.bean.QuestionListBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-20/10:14
 * @email:grs0515@163.com
 */
public class PlanBundleData extends BaseBundleData {

    public String episodeId;
    //List 需要转一下
    public String questionList;
    public String position;
    public String planId;
    public String title;

    public PlanBundleData() {
        super();
    }


    public PlanBundleData(Bundle bundle) {
        super(bundle);
    }

    public List<QuestionListBean> getQuestionList() {
        if (bundle != null) {
            String key = "questionList";
            return getTypeTokenListBean(key, QuestionListBean.class);
        }
        return new ArrayList<>();
    }

    public PlanBundleData setQuestionList(List<QuestionListBean> eduSubjectList) {
        this.questionList = new Gson().toJson(eduSubjectList);
        return this;
    }

    public String getEpisodeId() {
        return getString("episodeId");
    }

    public PlanBundleData setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
        return this;
    }

    public String getPosition() {
        return getString("position");
    }

    public PlanBundleData setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getPlanId() {
        return getString("planId");
    }

    public PlanBundleData setPlanId(String planId) {
        this.planId = planId;
        return this;
    }
    public String getTitle() {
        return getString("title");
    }

    public PlanBundleData setTitle(String title) {
        this.title = title;
        return this;
    }

}
