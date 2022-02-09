package com.dhcc.res.custom.bean;

public class ListViewBean {

    /**
     * end : 2022-01-29
     * param : dateTime
     * start : 2022-01-28
     */

    private DateTimeViewBean dateTimeView2;
    /**
     * content : {"line1Content":{"code":"DisposeStatDesc"},"line2Content":{"code":"CreateDateTime"},"type":"order"}
     * header : {"content":"测试这是头部"}
     * method : GetListData
     * param : barcode,tabCode,dateTime,SheetCode
     */

    private ListBean list;

    public DateTimeViewBean getDateTimeView2() {
        return dateTimeView2;
    }

    public void setDateTimeView2(DateTimeViewBean dateTimeView2) {
        this.dateTimeView2 = dateTimeView2;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }


    public static class ListBean {
        /**
         * line1Content : {"code":"DisposeStatDesc"}
         * line2Content : {"code":"CreateDateTime"}
         * type : order
         */

        private ContentBean content;
        /**
         * content : 测试这是头部
         */

        private HeaderBean header;
        private String method;
        private String param;

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public HeaderBean getHeader() {
            return header;
        }

        public void setHeader(HeaderBean header) {
            this.header = header;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public static class ContentBean {
            /**
             * code : DisposeStatDesc
             */

            private Line1ContentBean line1Content;
            /**
             * code : CreateDateTime
             */

            private Line2ContentBean line2Content;
            private String type;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class Line1ContentBean {
                private String code;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }
            }

            public static class Line2ContentBean {
                private String code;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }
            }
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
    }
}
