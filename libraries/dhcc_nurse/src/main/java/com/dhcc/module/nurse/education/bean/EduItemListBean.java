package com.dhcc.module.nurse.education.bean;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dhcc.module.nurse.params.SaveEduParams;
import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.ArrayList;
import java.util.List;

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
    private List<SheetListBean> sheetList;
    private String other;

    public String getOther() {
        return other == null ? "" : other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<SheetListBean> getSheetList() {
        if (sheetList == null) {
            return new ArrayList<>();
        }
        return sheetList;
    }

    public void setSheetList(List<SheetListBean> sheetList) {
        this.sheetList = sheetList;
    }

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

    /**
     * 将options 转为SheetListBean对象
     * @return
     */
    public List<SheetListBean> getOptionListBeans() {
        List<SheetListBean> list = new ArrayList<>();
        String options = getOptions();
        if (options.contains("/")) {
            String[] split = options.split("/");
            for (String s : split) {
                SheetListBean bean = new SheetListBean();
                bean.setDesc(s);
                list.add(bean);
            }
        } else {
            SheetListBean bean = new SheetListBean();
            bean.setDesc(options);
            list.add(bean);
        }
        return list;
    }

    public String getOptions() {
        return options == null ? "" : options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "EduItemListBean{" +
                "blankFlag='" + blankFlag + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", options='" + options + '\'' +
                ", type='" + type + '\'' +
                ", sheetList=" + sheetList +
                ", other='" + other + '\'' +
                '}';
    }

    /**
     * 将options 转为EduTaskIdBean对象
     * @return
     */
    public SaveEduParams.EduTaskIdBean getEduTaskBean() {
        //{"id":"1","option":"手足/其他/本人/配偶\f444"}
        //{"id":"2","option":"不了解"}
        SaveEduParams.EduTaskIdBean eduTaskIdBean = new SaveEduParams.EduTaskIdBean();
        eduTaskIdBean.option = "";
        for (SheetListBean bean : sheetList) {
            if (bean.isSelect()) {
                eduTaskIdBean.id = id;
                eduTaskIdBean.option += bean.getDesc() + "/";
            }
        }
        int length = eduTaskIdBean.option.length();
        eduTaskIdBean.option = eduTaskIdBean.option.substring(0, length - 1);
        if (!TextUtils.isEmpty(other)) {
            eduTaskIdBean.option += "\f" + other;
        }
        return eduTaskIdBean;
    }
}