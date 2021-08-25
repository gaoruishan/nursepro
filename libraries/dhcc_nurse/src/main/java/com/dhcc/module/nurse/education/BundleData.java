package com.dhcc.module.nurse.education;

import android.os.Bundle;
import android.text.TextUtils;

import com.base.commlibs.comm.BaseBundleData;
import com.dhcc.module.nurse.accompany.bean.AccompanyBean;
import com.dhcc.module.nurse.accompany.bean.AccompanyConfigBean;
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
public class BundleData extends BaseBundleData {

    public String episodeId;
    public String desc;
    public String subjectIds;
    //List 需要转一下
    public String eduSubjectList;
    public String dateTime;
    public String taskIds;
    //已宣教-对应save中的id
    public String eduRecordId;

    //陪护 rowId
    public String nCPARRowIDs;
    //List 需要转一下
    public String configTEMPList;
    public String accompanyList;
    //位置
    public String position;
    //类型
    public String type;
    public String user;
    public String id;


    public BundleData(Bundle bundle) {
        super(bundle);
    }

    public BundleData() {

    }

    public String getUser() {
        return getString("user");
    }

    public BundleData setUser(String user) {
        this.user = user;
        return this;
    }
    public String getId() {
        return getString("id");
    }

    public BundleData setId(String id) {
        this.id = id;
        return this;
    }
    public String getPosition() {
        return getString("position");
    }

    public BundleData setPosition(String position) {
        this.position = position;
        return this;
    }
    public String getType() {
        return getString("type");
    }

    public BundleData setType(String type) {
        this.type = type;
        return this;
    }

    public String getNCPARRowIDs() {
        return getString("nCPARRowIDs");
    }

    public BundleData setNCPARRowIDs(String nCPARRowIDs) {
        this.nCPARRowIDs = nCPARRowIDs;
        return this;
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

    /**
     * 陪护配置
     * @return
     */
    public List<AccompanyConfigBean> getConfigTEMPList() {
        if (bundle != null) {
            String configTEMPList = bundle.getString("configTEMPList");
            Type type = new TypeToken<List<AccompanyConfigBean>>() {
            }.getType();
            return new Gson().fromJson(configTEMPList, type);
        }
        return new ArrayList<>();

    }

    public BundleData setConfigTEMPList(List<AccompanyConfigBean> configTEMPList) {
        this.configTEMPList = new Gson().toJson(configTEMPList);
        return this;
    }

    /**
     * 陪护列表
     * @return
     */
    public List<AccompanyBean.AccompanyListBean> getAccompanyList() {
        if (bundle != null) {
            String accompanyList = bundle.getString("accompanyList");
            Type type = new TypeToken<List<AccompanyBean.AccompanyListBean>>() {
            }.getType();
            return new Gson().fromJson(accompanyList, type);
        }
        return new ArrayList<>();

    }
    public BundleData setAccompanyList(List<AccompanyBean.AccompanyListBean> accompanyList) {
        this.accompanyList = new Gson().toJson(accompanyList);
        return this;
    }


}
