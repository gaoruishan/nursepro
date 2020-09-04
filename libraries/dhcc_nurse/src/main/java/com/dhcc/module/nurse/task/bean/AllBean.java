package com.dhcc.module.nurse.task.bean;

import java.io.Serializable;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>任务总览列表内容，用于将类型列表传递到下一个界面
 * author Q
 * Date: 2020/8/13
 * Time:15:06
 */
public class AllBean  implements Serializable {
    /**
     * code : DefaultSee
     * name : 需处理
     * value : 5
     */

    private String code;
    private String name;
    private String value;
    private String groupCode;
    private String emrCode;

    public String getEmrCode() {
        return emrCode == null ? "" : emrCode;
    }

    public void setEmrCode(String emrCode) {
        this.emrCode = emrCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getCode() {
        if (groupCode!=null&&!groupCode.isEmpty()){
            return groupCode;
        }else {
            return code;
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
