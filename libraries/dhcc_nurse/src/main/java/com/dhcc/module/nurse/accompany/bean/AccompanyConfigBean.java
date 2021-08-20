package com.dhcc.module.nurse.accompany.bean;

import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.ArrayList;
import java.util.List;

public class AccompanyConfigBean extends ConfigSheetBean{

    public static final String TYPE_1 = "1";
    public static final String TYPE_4 = "4";
    public static final String TYPE_6 = "6";
    public static final String TYPE_7 = "7";

    private String checkbox;
    // 1 单行文本; 2 多行文本; 3 下拉框 ; 4 单选框; 5 多选框; 6 时间; 7 日期;
    private String type;
    private String defaultValue;
    //输入内容
    private String inputValue;
    //单选/多选
    private List<SheetListBean> sheetList;

    /**
     * 组装数据
     * @return
     */
    public List<SheetListBean> getBuildSheetList() {
        sheetList = new ArrayList<>();
        String[] split = defaultValue.split("&");
        for (String s : split) {
            sheetList.add(new SheetListBean(field, s));
        }
        return sheetList;
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

    public String getInputValue() {
        return inputValue == null ? "" : inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue == null ? "" : defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }


    @Override
    public String toString() {
        return "AccompanyConfigBean{" +
                "checkbox='" + checkbox + '\'' +
                ", field='" + field + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", inputValue='" + inputValue + '\'' +
                ", sheetList=" + sheetList +
                '}';
    }
}