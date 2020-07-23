package com.dhcc.module.nurse.education.bean;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class EduItemListBean implements MultiItemEntity {
    //单选
    public static final int TYPE_0 = 0;
    // 多选
    public static final int TYPE_1 = 1;
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

    @Override
    public int getItemType() {
        if (!TextUtils.isEmpty(type)) {
            return Integer.valueOf(type);
        }
        return 0;
    }
}