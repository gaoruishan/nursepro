package com.dhcc.module.nurse.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 设置信息
 */
@XStreamAlias("SettingInfo")
public class SettingInfo {

    @XStreamAlias("Id")
    public String id = "";

    @XStreamAlias("NameText")
    public String nameText = "";

    @XStreamAlias("ElType")
    public String elType = "";

    @XStreamAlias("FoundationJS")
    public String foundationJS = "";

    @XStreamAlias("DataInfo")
    public DataInfo dataInfo = new DataInfo();

    @XStreamAlias("BasicInfo")
    public BasicInfo basicInfo = new BasicInfo();

    @XStreamAlias("PDAStyle")
    public PDAStyle pdaStyle = new PDAStyle();


    /**
     * 基础信息
     */
    @XStreamAlias("BasicInfo")
    public static class BasicInfo {

        /**
         * 标签属性-元素类型 LableInfo,TextInfo,TextareaInfo,ButtonInfo等
         */
        @XStreamAsAttribute()
        @XStreamAlias("xsi:type")
        public String type = "";

        @XStreamAlias("Text")
        public String text = "";

        @XStreamAlias("FormName")
        public String formName = "";

        @XStreamAlias("Signature")
        public String signature = "";

    }

    /**
     * 数据信息
     */
    @XStreamAlias("DataInfo")
    public static class DataInfo {

        @XStreamAlias("SaveField")
        public String saveField = "";

        @XStreamAlias("DataSourceRefInfo")
        public String dataSourceRefInfo = "";

    }

    /**
     * PDA设置
     */
    @XStreamAlias("PDAStyle")
    public static class PDAStyle {

        @XStreamAlias("IsHide")
        public String IsHide = "";

    }

}
