package com.dhcc.module.infusion.login.windowpicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.entity.LinkageFirst;

/**
 * com.dhcc.module.infusion.login.windowpicker
 * <p>
 * author Q
 * Date: 2019/3/19
 * Time:16:20
 */
public class Ward implements LinkageFirst<Window> {
    private String name;

    public Ward(String name) {
        this.name = name;
    }

    @Override
    public Object getId() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }
    public List<Window> getSeconds(Map<String ,ArrayList<String>> map) {
        List<Window> cities = new ArrayList<>();
        List list =new ArrayList();
        list = map.get(name);
        int a;
        if (list.size() == 0 || list == null){
            cities.add(new Window("默认窗口"));
        }else {
            for (int i = 0; i < list.size(); i++) {
                cities.add(new Window((String) list.get(i)));
            }
        }
        return cities;

    }
    @Override
    public List<Window> getSeconds() {
        List<Window> cities = new ArrayList<>();
        switch (name) {
            case "新":
                for (char i = 'A'; i <= 'R'; i++) {
                    cities.add(new Window(String.valueOf(i)));
                }
                cities.remove(new Window("I"));
                cities.remove(new Window("O"));
                break;
        }
        return cities;
    }

    @Override
    public String toString() {
        return "name=" + name;
    }

}

