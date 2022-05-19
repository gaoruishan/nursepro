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

    public ListBean getList() {
        return list;
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

        public HeaderBean getHeader() {
            return header;
        }

        public String getMethod() {
            return method;
        }

        public String getParam() {
            return param;
        }


        public static class ContentBean {
            private LineContentBean line1Content;
            private LineContentBean line2Content;
            private LineContentBean line3Content;
            private String type;

            public LineContentBean getLine1Content() {
                return line1Content;
            }

            public LineContentBean getLine2Content() {
                return line2Content;
            }

            public LineContentBean getLine3Content() {
                return line3Content;
            }

            public String getType() {
                return type == null ? "" : type;
            }

            public static class LineContentBean {
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

        }
    }
}
