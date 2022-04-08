package com.dhcc.module.nurse.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202022/4/7/18:41
 * @email:grs0515@163.com
 */
@XStreamAlias("ElementBase")
public class ElementBase {
    /**
     * 标签属性-元素类型 LableElement,ContainerElement,TextareaElement,DateElement,TimeElement
     * CheckElement,TextElement,ButtonElement等
     */
    @XStreamAsAttribute()
    @XStreamAlias("xsi:type")
    public String type = "";
    /*标签-元素信息*/
    @XStreamAlias("SettingInfo")
    public SettingInfo settingInfo = new SettingInfo();

    /*ContainerElement,CheckElement 类型 会有子元素*/
    @XStreamAlias("Elements")
    public List<ElementBase> elements = new ArrayList<>();


}
