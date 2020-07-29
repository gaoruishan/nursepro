package com.dhcc.module.nurse.education;

import android.os.Bundle;
import android.text.TextUtils;

import com.base.commlibs.utils.ReflectUtil;
import com.dhcc.module.nurse.education.bean.EduSubjectListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-22/11:53
 * @email:grs0515@163.com
 */
public class BundleData {

    private Bundle bundle;
    public String episodeId;
    public String desc;
    public String subjectIds;
    //List 需要转一下
    public String eduSubjectList;
    public String dateTime;
    public String taskIds;
    //已宣教-对应save中的id
    public String eduRecordId;

    public BundleData() {
    }

    public BundleData(Bundle bundle) {
        this.bundle = bundle;
    }

    public String getEduRecordId() {
        return getString("eduRecordId");
    }

    public BundleData setEduRecordId(String eduRecordId) {
        this.eduRecordId = eduRecordId;
        return this;
    }

    public String getTaskIds() {
        return getString("taskIds") ;
    }

    public BundleData setTaskIds(String taskIds) {
        if(!TextUtils.isEmpty(taskIds)){
            this.taskIds = taskIds.replaceAll(" ","");
        }
        return this;
    }

    public String getEpisodeId() {
        return getString("episodeId");
    }

    public BundleData setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
        return this;
    }

    public String getDateTime() {
        return getString("dateTime");
    }

    public BundleData setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getSubjectIds() {
        return getString("subjectIds");
    }

    public BundleData setSubjectIds(String subjectIds) {
        if(!TextUtils.isEmpty(subjectIds)){
            this.subjectIds = subjectIds.replaceAll(" ","");
        }
        return this;
    }

    public String getDesc() {
        return getString("desc");
    }

    private String getString(String key) {
        if (bundle != null) {
            return bundle.getString(key);
        }
        return "";
    }

    public BundleData setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public List<EduSubjectListBean> getEduSubjectList() {
        if (bundle != null) {
            String eduSubjectList = bundle.getString("eduSubjectList");
            Type type = new TypeToken<List<EduSubjectListBean>>() {
            }.getType();
            return new Gson().fromJson(eduSubjectList, type);
        }
        return new ArrayList<>();

    }

    public BundleData setEduSubjectList(List<EduSubjectListBean> eduSubjectList) {
        this.eduSubjectList = new Gson().toJson(eduSubjectList);
        return this;
    }

    public Bundle build() {
        return ReflectUtil.getPublicFieldsToBundle(this);
    }
}
