package com.dhcc.nursepro.workarea.nurrecordnew.bean;

import java.util.HashMap;

public class MEViewLink {
    private String linkName;
    private String linkContent;
    private HashMap<String, String> MEHashMap;
    private String resultViewId;

    public MEViewLink() {
    }

    public MEViewLink(String linkName, String resultViewId) {
        this.linkName = linkName;
        this.resultViewId = resultViewId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkContent() {
        return linkContent;
    }

    public void setLinkContent(String linkContent) {
        this.linkContent = linkContent;
    }

    public HashMap<String, String> getMEHashMap() {
        return MEHashMap;
    }

    public void setMEHashMap(HashMap<String, String> MEHashMap) {
        this.MEHashMap = MEHashMap;
    }

    public String getResultViewId() {
        return resultViewId;
    }

    public void setResultViewId(String resultViewId) {
        this.resultViewId = resultViewId;
    }
}
