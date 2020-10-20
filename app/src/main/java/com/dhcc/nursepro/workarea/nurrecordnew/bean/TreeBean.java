package com.dhcc.nursepro.workarea.nurrecordnew.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class TreeBean extends AbstractExpandableItem<MultiItemEntity> implements MultiItemEntity {
    private String id;
    private String text;
    private int type;
    private List<MultiItemEntity> treeBeanList;

    public TreeBean(String id, String text, int type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    public TreeBean(String id, String text, int type, List<MultiItemEntity> treeBeanList) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.treeBeanList = treeBeanList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<MultiItemEntity> getTreeBeanList() {
        return treeBeanList;
    }

    public void setTreeBeanList(List<MultiItemEntity> treeBeanList) {
        this.treeBeanList = treeBeanList;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public int getLevel() {
        return type;
    }
}