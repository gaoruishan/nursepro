package com.dhcc.res.item.bean;

import com.dhcc.res.CustomBean;

/**
 * @author:gaoruishan
 * @date:202020/12/10/09:16
 * @email:grs0515@163.com
 */
public  class CustomPatItem extends CustomBean {

    private String tvBedno;
    private String tvCarelevel;
    private String tvName;
    private String tvAge;
    private String imgSex;

    public CustomPatItem() {
    }

    public String getTvAge() {
        return tvAge == null ? "" : tvAge;
    }

    public void setTvAge(String tvAge) {
        this.tvAge = tvAge;
    }

    public String getTvBedno() {
        return tvBedno == null ? "" : tvBedno;
    }

    public void setTvBedno(String tvBedno) {
        this.tvBedno = tvBedno;
    }

    public String getTvCarelevel() {
        return tvCarelevel == null ? "" : tvCarelevel;
    }

    public void setTvCarelevel(String tvCarelevel) {
        this.tvCarelevel = tvCarelevel;
    }

    public String getTvName() {
        return tvName == null ? "" : tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getImgSex() {
        return imgSex == null ? "" : imgSex;
    }

    public void setImgSex(String imgSex) {
        this.imgSex = imgSex;
    }
}
