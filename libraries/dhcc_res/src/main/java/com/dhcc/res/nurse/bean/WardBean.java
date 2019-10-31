package com.dhcc.res.nurse.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登陆科室模型
 * @author:gaoruishan
 * @date:202019-10-22/08:54
 * @email:grs0515@163.com
 */
public class WardBean {
    private List<String> firstList;
    private String firstString;
    private String secondString;
    private Map<String, List<String>> listMap;
    private String firstLabel;
    private String secondLabel;

    public String getFirstLabel() {
        return firstLabel == null ? "" : firstLabel;
    }

    public void setFirstLabel(String firstLabel) {
        this.firstLabel = firstLabel;
    }

    public String getSecondLabel() {
        return secondLabel == null ? "" : secondLabel;
    }

    public void setSecondLabel(String secondLabel) {
        this.secondLabel = secondLabel;
    }

    public int getFirstIndex() {
        int firstIndex = 0;
        for (int i = 0; i < getFirstList().size(); i++) {
            if (getFirstList().get(i).equals(getFirstString())) {
                firstIndex = i;
            }
        }
        return firstIndex;
    }

    public List<String> getFirstList() {
        if (firstList == null) {
            return new ArrayList<>();
        }
        return firstList;
    }

    public void setFirstList(List<String> firstList) {
        this.firstList = firstList;
    }

    public String getFirstString() {
        return firstString == null ? "" : firstString;
    }

    public void setFirstString(String firstString) {
        this.firstString = firstString;
    }

    public int getSecondIndex() {
        int secondIndex = 0;
        List<String> list = getListMap().get(getFirstString());
        for (int i = 0; list != null && i < list.size(); i++) {
            if (list.get(i).equals(getSecondString())) {
                secondIndex = i;
            }
        }
        return secondIndex;
    }

    public Map<String, List<String>> getListMap() {
        return listMap == null ? new HashMap<String, List<String>>() : listMap;
    }

    public String getSecondString() {
        return secondString == null ? "" : secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }

    public void setListMap(Map<String, List<String>> listMap) {
        this.listMap = listMap;
    }
}
