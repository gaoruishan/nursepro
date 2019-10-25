package com.dhcc.res.nurse.bean;

import java.util.List;

/**
 * 主页配置Bean
 * @author:gaoruishan
 * @date:202019-07-04/11:16
 * @email:grs0515@163.com
 */
public class ConfigWorkArea {

    /**
     * desc : 主页模块配置
     * list : [{"id":100,"name":"配液","image":"","fragment":""},{"id":101,"name":"穿刺","image":"","fragment":""},{"id":102,"name":"巡视","image":"","fragment":""},{"id":103,"name":"续液","image":"","fragment":""},{"id":104,"name":"拔针","image":"","fragment":""},{"id":105,"name":"医嘱执行","image":"","fragment":""}]
     */

    private String desc;
    private List<ListBean> list;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 100
         * name : 配液
         * image :
         * fragment :
         */

        private int id;
        private String name;
        private String image;
        private String fragment;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getFragment() {
            return fragment == null ? "" : fragment;
        }

        public void setFragment(String fragment) {
            this.fragment = fragment;
        }
    }

    @Override
    public String toString() {
        return "ConfigWorkArea{" +
                "desc='" + desc + '\'' +
                ", list=" + list +
                '}';
    }
}
