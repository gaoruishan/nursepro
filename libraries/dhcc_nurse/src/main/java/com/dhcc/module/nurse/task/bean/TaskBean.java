package com.dhcc.module.nurse.task.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/3
 * Time:14:39
 */
public class TaskBean extends CommResult {

    private List<SchListBean> schList;
    private List<SchSheetListBean> schSheetList;
    private List<TimesListBean> timesList;

    public List<SchListBean> getSchList() {
        return schList;
    }

    public void setSchList(List<SchListBean> schList) {
        this.schList = schList;
    }

    public List<SchSheetListBean> getSchSheetList() {
        return schSheetList;
    }

    public void setSchSheetList(List<SchSheetListBean> schSheetList) {
        this.schSheetList = schSheetList;
    }

    public List<TimesListBean> getTimesList() {
        return timesList;
    }

    public void setTimesList(List<TimesListBean> timesList) {
        this.timesList = timesList;
    }

    public static class SchListBean {
        /**
         * recList : {"ID":"1","body":{"all":[{"code":"DefaultSee","name":"需处理","value":"5"},{"code":"BLD","name":"病理单*","value":"0"},{"code":"CQSYD","name":"输液单*","value":"0"},{"code":"CQZSD","name":"注射单*","value":"0"},{"code":"CUDY","name":"出院带药单*","value":"0"},{"code":"HLZLD","name":"处置治疗单*","value":"0"},{"code":"JCD","name":"检查单*","value":"0"},{"code":"JYD","name":"检验单*","value":"0"},{"code":"PSD","name":"皮试单*","value":"0"},{"code":"SQZBD","name":"术前准备单*","value":"0"},{"code":"SXD","name":"输血单*","value":"0"},{"code":"YSHLD","name":"饮食护理单*","value":"0"},{"code":"cqkfyd","name":"口服药单*","value":"0"}],"unexec":[{"code":"BLD","name":"病理单*","value":"0"},{"code":"CQSYD","name":"输液单*","value":"0"},{"code":"CQZSD","name":"注射单*","value":"0"},{"code":"CUDY","name":"出院带药单*","value":"0"},{"code":"HLZLD","name":"处置治疗单*","value":"0"},{"code":"JCD","name":"检查单*","value":"0"},{"code":"JYD","name":"检验单*","value":"0"},{"code":"PSD","name":"皮试单*","value":"0"},{"code":"SQZBD","name":"术前准备单*","value":"0"},{"code":"SXD","name":"输血单*","value":"0"},{"code":"YSHLD","name":"饮食护理单*","value":"0"},{"code":"cqkfyd","name":"口服药单*","value":"0"}],"unsee":[{"code":"DefaultSee","name":"需处理","value":"5"}]},"head":[{"key":"all","name":"所有任务","value":"5"},{"key":"unsee","name":"未处理","value":"5"},{"key":"unexec","name":"未执行","value":"0"}],"name":"医嘱执行统计图"}
         * schCode : ordTask
         */

        private RecListBean recList;
        private String schCode;

        public RecListBean getRecList() {
            return recList;
        }

        public void setRecList(RecListBean recList) {
            this.recList = recList;
        }

        public String getSchCode() {
            return schCode;
        }

        public void setSchCode(String schCode) {
            this.schCode = schCode;
        }

        public static class RecListBean {
            /**
             * ID : 1
             * body : {"all":[{"code":"DefaultSee","name":"需处理","value":"5"},{"code":"BLD","name":"病理单*","value":"0"},{"code":"CQSYD","name":"输液单*","value":"0"},{"code":"CQZSD","name":"注射单*","value":"0"},{"code":"CUDY","name":"出院带药单*","value":"0"},{"code":"HLZLD","name":"处置治疗单*","value":"0"},{"code":"JCD","name":"检查单*","value":"0"},{"code":"JYD","name":"检验单*","value":"0"},{"code":"PSD","name":"皮试单*","value":"0"},{"code":"SQZBD","name":"术前准备单*","value":"0"},{"code":"SXD","name":"输血单*","value":"0"},{"code":"YSHLD","name":"饮食护理单*","value":"0"},{"code":"cqkfyd","name":"口服药单*","value":"0"}],"unexec":[{"code":"BLD","name":"病理单*","value":"0"},{"code":"CQSYD","name":"输液单*","value":"0"},{"code":"CQZSD","name":"注射单*","value":"0"},{"code":"CUDY","name":"出院带药单*","value":"0"},{"code":"HLZLD","name":"处置治疗单*","value":"0"},{"code":"JCD","name":"检查单*","value":"0"},{"code":"JYD","name":"检验单*","value":"0"},{"code":"PSD","name":"皮试单*","value":"0"},{"code":"SQZBD","name":"术前准备单*","value":"0"},{"code":"SXD","name":"输血单*","value":"0"},{"code":"YSHLD","name":"饮食护理单*","value":"0"},{"code":"cqkfyd","name":"口服药单*","value":"0"}],"unsee":[{"code":"DefaultSee","name":"需处理","value":"5"}]}
             * head : [{"key":"all","name":"所有任务","value":"5"},{"key":"unsee","name":"未处理","value":"5"},{"key":"unexec","name":"未执行","value":"0"}]
             * name : 医嘱执行统计图
             */

            private String ID;
            private BodyBean body;
            private String name;
            private List<HeadBean> head;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public BodyBean getBody() {
                return body;
            }

            public void setBody(BodyBean body) {
                this.body = body;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<HeadBean> getHead() {
                return head;
            }

            public void setHead(List<HeadBean> head) {
                this.head = head;
            }

            public static class BodyBean {
                private List<AllBean> all;
                private List<UnexecBean> unexec;
                private List<UnseeBean> unsee;

                public List<AllBean> getAll() {
                    return all;
                }

                public void setAll(List<AllBean> all) {
                    this.all = all;
                }

                public List<UnexecBean> getUnexec() {
                    return unexec;
                }

                public void setUnexec(List<UnexecBean> unexec) {
                    this.unexec = unexec;
                }

                public List<UnseeBean> getUnsee() {
                    return unsee;
                }

                public void setUnsee(List<UnseeBean> unsee) {
                    this.unsee = unsee;
                }

                public static class AllBean {
                    /**
                     * code : DefaultSee
                     * name : 需处理
                     * value : 5
                     */

                    private String code;
                    private String name;
                    private String value;

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }

                public static class UnexecBean {
                    /**
                     * code : BLD
                     * name : 病理单*
                     * value : 0
                     */

                    private String code;
                    private String name;
                    private String value;

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }

                public static class UnseeBean {
                    /**
                     * code : DefaultSee
                     * name : 需处理
                     * value : 5
                     */

                    private String code;
                    private String name;
                    private String value;

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }

            public static class HeadBean {
                /**
                 * key : all
                 * name : 所有任务
                 * value : 5
                 */

                private String key;
                private String name;
                private String value;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }

    public static class SchSheetListBean {
        /**
         * schCode : ordTask
         * schDesc : 医嘱查询
         */

        private String schCode;
        private String schDesc;

        public String getSchCode() {
            return schCode;
        }

        public void setSchCode(String schCode) {
            this.schCode = schCode;
        }

        public String getSchDesc() {
            return schDesc;
        }

        public void setSchDesc(String schDesc) {
            this.schDesc = schDesc;
        }
    }
}
