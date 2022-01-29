package com.dhcc.res.custom.bean;

import java.util.List;

public class ListViewBean {
    /**
     * start : 2022-01-27 00:00
     * end : 2022-01-27 23:59
     * param : dateTime
     * desc : dateTimeView控制日期时间,start或end开始结束时间,param参数名;会覆盖掉上层dateTimeView
     */

    private DateTimeViewBean dateTimeView;
    /**
     * header : {"content":"这是头部"}
     * content : [{"type":"text","line1Content":{"title":"测试list内容1111","style":{"color":"#DEFECC","size":"20"}},"line2Content":{"title":"测试list内容1111"},"line3Content":{"title":"日期时间:2022-01-27 12:12"}},{"type":"rich","richContent":"<h2 style=\"background-color:red;\">这是一个标题<\/h2>"}]
     * footer : {"content":"这是尾部"}
     */

    private ListBean list;

    public DateTimeViewBean getDateTimeView() {
        return dateTimeView;
    }

    public void setDateTimeView(DateTimeViewBean dateTimeView) {
        this.dateTimeView = dateTimeView;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class DateTimeViewBean {
        private String start;
        private String end;
        private String param;
        private String desc;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class ListBean {
        /**
         * content : 这是头部
         */

        private HeaderBean header;
        /**
         * content : 这是尾部
         */

        private FooterBean footer;
        /**
         * type : text
         * line1Content : {"title":"测试list内容1111","style":{"color":"#DEFECC","size":"20"}}
         * line2Content : {"title":"测试list内容1111"}
         * line3Content : {"title":"日期时间:2022-01-27 12:12"}
         */

        private List<ContentBean> content;

        public HeaderBean getHeader() {
            return header;
        }

        public void setHeader(HeaderBean header) {
            this.header = header;
        }

        public FooterBean getFooter() {
            return footer;
        }

        public void setFooter(FooterBean footer) {
            this.footer = footer;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class HeaderBean {
            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class FooterBean {
            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class ContentBean {
            private String type;
            /**
             * title : 测试list内容1111
             * style : {"color":"#DEFECC","size":"20"}
             */

            private Line1ContentBean line1Content;
            /**
             * title : 测试list内容1111
             */

            private Line2ContentBean line2Content;
            /**
             * title : 日期时间:2022-01-27 12:12
             */

            private Line3ContentBean line3Content;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Line1ContentBean getLine1Content() {
                return line1Content;
            }

            public void setLine1Content(Line1ContentBean line1Content) {
                this.line1Content = line1Content;
            }

            public Line2ContentBean getLine2Content() {
                return line2Content;
            }

            public void setLine2Content(Line2ContentBean line2Content) {
                this.line2Content = line2Content;
            }

            public Line3ContentBean getLine3Content() {
                return line3Content;
            }

            public void setLine3Content(Line3ContentBean line3Content) {
                this.line3Content = line3Content;
            }

            public static class Line1ContentBean {
                private String title;
                /**
                 * color : #DEFECC
                 * size : 20
                 */

                private StyleBean style;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public StyleBean getStyle() {
                    return style;
                }

                public void setStyle(StyleBean style) {
                    this.style = style;
                }

                public static class StyleBean {
                    private String color;
                    private String size;

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }

                    public String getSize() {
                        return size;
                    }

                    public void setSize(String size) {
                        this.size = size;
                    }
                }
            }

            public static class Line2ContentBean {
                private String title;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class Line3ContentBean {
                private String title;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
