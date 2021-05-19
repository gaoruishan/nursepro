package com.base.commlibs.bean;

/**
 * @author:gaoruishan
 * @date:202021/5/19/10:31
 * @email:grs0515@163.com
 */
public class WebConfigBean {

    /**
     * title : 测试
     * hideToolBar : 1
     * toolBar : {"show":"1","type":"text","text":"确定","image":""}
     */

    public String title = "";
    public String hideToolBar = "";
    /**
     * show : 1
     * type : text
     * text : 确定
     * image :
     */

    public ToolBarBean toolBar;

    public static class ToolBarBean {
        public String show = "";
        public String type = "";
        public String text = "";
        public String image = "";
        public String method = "";
    }

    @Override
    public String toString() {
        return "WebConfigBean{" +
                "title='" + title + '\'' +
                ", hideToolBar='" + hideToolBar + '\'' +
                ", toolBar=" + toolBar +
                '}';
    }
}
