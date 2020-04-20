package com.dhcc.res.nurse.bean;

/**
 * @author:gaoruishan
 * @date:202020-04-16/11:57
 * @email:grs0515@163.com
 */
public class RadioBean {
    private String code;
    private String name;
    private String radioYes;
    private String radioNo;
    //1是 0否
    private String select;

    public RadioBean(String code, String name, String radioYes, String radioNo) {
        this.code = code;
        this.name = name;
        this.radioYes = radioYes;
        this.radioNo = radioNo;
    }

    public RadioBean(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "RadioBean{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", radioYes='" + radioYes + '\'' +
                ", radioNo='" + radioNo + '\'' +
                ", select='" + select + '\'' +
                '}';
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRadioYes() {
        return radioYes == null ? "" : radioYes;
    }

    public void setRadioYes(String radioYes) {
        this.radioYes = radioYes;
    }

    public String getRadioNo() {
        return radioNo == null ? "" : radioNo;
    }

    public void setRadioNo(String radioNo) {
        this.radioNo = radioNo;
    }

    public String isSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }
}
