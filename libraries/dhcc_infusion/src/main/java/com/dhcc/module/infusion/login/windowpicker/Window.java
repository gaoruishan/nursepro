package com.dhcc.module.infusion.login.windowpicker;

import java.util.List;

import cn.qqtheme.framework.entity.LinkageSecond;

/**
 * com.dhcc.infusion.workarea.allotbed
 * <p>
 * author Q
 * Date: 2019/3/19
 * Time:16:20
 */
public class Window implements LinkageSecond<Void> {
    private String name;
    private String windId;

    public Window(String name) {
//        windId = name;
//        if (name.contains("$")) {
//            this.name = name.substring(0, name.indexOf("$"));
//        }else {
//            this.name = name;
//        }

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

    public String getWindId() {
//        if (windId.contains("$")) {
//            return windId.substring(windId.indexOf("$") + 1, windId.length());
//        }else {
//            return "未绑定windID";
//        }
        return  name;
    }

    @Override
    public List<Void> getThirds() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Window)) {
            return false;
        }
        Window obj1 = (Window) obj;
        return name.equals(obj1.getName());
    }

    @Override
    public String toString() {
        return "name=" + name;
    }

}