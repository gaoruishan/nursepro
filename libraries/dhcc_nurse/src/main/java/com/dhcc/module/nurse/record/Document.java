package com.dhcc.module.nurse.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * xml文档
 * @author:gaoruishan
 * @date:202022/4/7/18:26
 * @email:grs0515@163.com
 */
@XStreamAlias("Document")
public class Document {

    @XStreamAsAttribute()
    @XStreamAlias("EmrCode")
    public String emrCode = "";

    @XStreamAsAttribute()
    @XStreamAlias("SaveType")
    public String saveType = "";

    @XStreamAlias("Elements")
    public List<ElementBase> elements = new ArrayList<>();


    public List<ElementBase> getElements() {
        if (elements == null) {
            return new ArrayList<>();
        }
        //去改变数据
        if ("DHCNURZLNLPG".equals(emrCode)) {
            for (ElementBase element : elements) {

            }
        }

        return elements;
    }

}
