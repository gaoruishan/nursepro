package com.dhcc.module.nurse.task.bean;

import com.base.commlibs.http.CommResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/3
 * Time:14:39
 */
public class TaskBean extends CommResult {
    /**
     * msg : 成功
     * msgcode : 999999
     * nurNormalNumList : {"ID":"5","body":{"all":[{"code":"10136||1","name":"翻身、拍背","value":"0"},{"code":"9738||1","name":"雾化吸入","value":"0"},{"code":"9744||1","name":"持续吸氧","value":"0"}],"unexec":[{"code":"10136||1","name":"翻身、拍背","value":"0"},{"code":"9738||1","name":"雾化吸入","value":"0"},{"code":"9744||1","name":"持续吸氧","value":"0"}]},"head":[{"key":"all","name":"所有任务","value":"0"},{"key":"unexec","name":"未执行","value":"0"}],"name":"常规护理任务-治疗处置"}
     * nurOrdNumList : {"ID":4,"body":{"all":[{"code":"Monitor","name":"监测","value":0},{"code":"Assess","name":"评估观察","value":0},{"code":"Refer","name":"处置","value":0},{"code":"Do","name":"措施","value":0},{"code":"Teach","name":"指导","value":0},{"code":"Contact","name":"照会转介","value":0},{"code":"Health","name":"健康","value":0},{"code":"CESHI","name":"测试","value":0}],"unexec":[{"code":"Monitor","name":"监测","value":0},{"code":"Assess","name":"评估观察","value":0},{"code":"Refer","name":"处置","value":0},{"code":"Do","name":"措施","value":0},{"code":"Teach","name":"指导","value":0},{"code":"Contact","name":"照会转介","value":0},{"code":"Health","name":"健康","value":0},{"code":"CESHI","name":"测试","value":0}]},"head":[{"key":"all","name":"所有任务","value":0},{"key":"unexec","name":"待执行","value":0}],"name":"护嘱执行统计图","taskStatus":0}
     * nurRateNumList : {"ID":"6","body":{"all":[{"code":"5||219","name":"跌倒_坠床风险评估单","value":"0"},{"code":"5||220","name":"Braden压力性损伤风险评估单","value":"7"}],"unexec":[{"code":"5||219","name":"跌倒_坠床风险评估单","value":"0"},{"code":"5||220","name":"Braden压力性损伤风险评估单","value":"7"}]},"head":[{"key":"all","name":"所有任务","value":"7"},{"key":"unexec","name":"未执行","value":"7"}],"name":"常规护理任务-护理评估"}
     * nurTemperNumList : {"ID":"7","body":{"all":[{"code":"TEMPERATURE","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"groupCode":"^rectemperature^temperature^","name":"体温(℃)","rank":"1","type":"T","value":"35"},{"code":"pulse","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"name":"脉搏（次/分）","rank":"3","type":"T","value":"35"},{"code":"breath","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"name":"呼吸（次/分）","rank":"4","type":"T","value":"35"},{"code":"PRESSURE","detail":{"2020-08-03 06:00":["02","11"]},"groupCode":"^diaPressure^sysPressure^","name":"血压","rank":"6","type":"T","value":"2"},{"code":"weight","detail":{"2020-08-03 06:00":["02","11"]},"name":"体重（KG）","rank":"14","type":"T","value":"2"}],"unexec":[{"code":"TEMPERATURE","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"groupCode":"^rectemperature^temperature^","name":"体温(℃)","rank":"1","type":"T","value":"35"},{"code":"pulse","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"name":"脉搏（次/分）","rank":"3","type":"T","value":"35"},{"code":"breath","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"name":"呼吸（次/分）","rank":"4","type":"T","value":"35"},{"code":"PRESSURE","detail":{"2020-08-03 06:00":["02","11"]},"groupCode":"^diaPressure^sysPressure^","name":"血压","rank":"6","type":"T","value":"2"},{"code":"weight","detail":{"2020-08-03 06:00":["02","11"]},"name":"体重（KG）","rank":"14","type":"T","value":"2"}]},"head":[{"key":"all","name":"所有任务","value":"109"},{"key":"unexec","name":"未执行","value":"109"}],"name":"常规护理任务-体征"}
     * ordNumList : {"ID":"1","body":{"all":[{"code":"DefaultSee","name":"需处理","value":"5"},{"code":"BLD","name":"病理单*","value":"0"},{"code":"CQSYD","name":"输液单*","value":"77"},{"code":"CQZSD","name":"注射单*","value":"0"},{"code":"CUDY","name":"出院带药单*","value":"0"},{"code":"HLZLD","name":"处置治疗单*","value":"7"},{"code":"JCD","name":"检查单*","value":"0"},{"code":"JYD","name":"检验单*","value":"0"},{"code":"PSD","name":"皮试单*","value":"0"},{"code":"SQZBD","name":"术前准备单*","value":"133"},{"code":"SXD","name":"输血单*","value":"0"},{"code":"YSHLD","name":"饮食护理单*","value":"0"},{"code":"cqkfyd","name":"口服药单*","value":"49"}],"unexec":[{"code":"BLD","name":"病理单*","value":"0"},{"code":"CQSYD","name":"输液单*","value":"77"},{"code":"CQZSD","name":"注射单*","value":"0"},{"code":"CUDY","name":"出院带药单*","value":"0"},{"code":"HLZLD","name":"处置治疗单*","value":"7"},{"code":"JCD","name":"检查单*","value":"0"},{"code":"JYD","name":"检验单*","value":"0"},{"code":"PSD","name":"皮试单*","value":"0"},{"code":"SQZBD","name":"术前准备单*","value":"133"},{"code":"SXD","name":"输血单*","value":"0"},{"code":"YSHLD","name":"饮食护理单*","value":"0"},{"code":"cqkfyd","name":"口服药单*","value":"49"}],"unsee":[{"code":"DefaultSee","name":"需处理","value":"5"}]},"head":[{"key":"all","name":"所有任务","value":"271"},{"key":"unsee","name":"未处理","value":"5"},{"key":"unexec","name":"未执行","value":"266"}],"name":"医嘱执行统计图"}
     * status : 0
     * timesList : [{"timeEnd":"2020-08-04 07:59","timeStt":"2020-08-03 17:31","timesDesc":"夜班"},{"timeEnd":"2020-08-04 07:59","timeStt":"2020-08-03 17:31","timesDesc":"夜班"},{"timeEnd":"2020-08-04 07:59","timeStt":"2020-08-03 17:31","timesDesc":"夜班"}]
     */

    private NurNormalNumListBean nurNormalNumList;
    private String nurOrdNumList;
    private NurRateNumListBean nurRateNumList;
    private NurTemperNumListBean nurTemperNumList;
    private OrdNumListBean ordNumList;
    private List<TimesListBean> timesList;
    public NurNormalNumListBean getNurNormalNumList() {
        return nurNormalNumList;
    }

    public void setNurNormalNumList(NurNormalNumListBean nurNormalNumList) {
        this.nurNormalNumList = nurNormalNumList;
    }

    public String getNurOrdNumList() {
        return nurOrdNumList;
    }

    public void setNurOrdNumList(String nurOrdNumList) {
        this.nurOrdNumList = nurOrdNumList;
    }

    public NurRateNumListBean getNurRateNumList() {
        return nurRateNumList;
    }

    public void setNurRateNumList(NurRateNumListBean nurRateNumList) {
        this.nurRateNumList = nurRateNumList;
    }

    public NurTemperNumListBean getNurTemperNumList() {
        return nurTemperNumList;
    }

    public void setNurTemperNumList(NurTemperNumListBean nurTemperNumList) {
        this.nurTemperNumList = nurTemperNumList;
    }

    public OrdNumListBean getOrdNumList() {
        return ordNumList;
    }

    public void setOrdNumList(OrdNumListBean ordNumList) {
        this.ordNumList = ordNumList;
    }

    public List<TimesListBean> getTimesList() {
        return timesList;
    }

    public void setTimesList(List<TimesListBean> timesList) {
        this.timesList = timesList;
    }

    public static class NurNormalNumListBean {
        /**
         * ID : 5
         * body : {"all":[{"code":"10136||1","name":"翻身、拍背","value":"0"},{"code":"9738||1","name":"雾化吸入","value":"0"},{"code":"9744||1","name":"持续吸氧","value":"0"}],"unexec":[{"code":"10136||1","name":"翻身、拍背","value":"0"},{"code":"9738||1","name":"雾化吸入","value":"0"},{"code":"9744||1","name":"持续吸氧","value":"0"}]}
         * head : [{"key":"all","name":"所有任务","value":"0"},{"key":"unexec","name":"未执行","value":"0"}]
         * name : 常规护理任务-治疗处置
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

            public static class AllBean {
                /**
                 * code : 10136||1
                 * name : 翻身、拍背
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

            public static class UnexecBean {
                /**
                 * code : 10136||1
                 * name : 翻身、拍背
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
        }

        public static class HeadBean {
            /**
             * key : all
             * name : 所有任务
             * value : 0
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

    public static class NurRateNumListBean {
        /**
         * ID : 6
         * body : {"all":[{"code":"5||219","name":"跌倒_坠床风险评估单","value":"0"},{"code":"5||220","name":"Braden压力性损伤风险评估单","value":"7"}],"unexec":[{"code":"5||219","name":"跌倒_坠床风险评估单","value":"0"},{"code":"5||220","name":"Braden压力性损伤风险评估单","value":"7"}]}
         * head : [{"key":"all","name":"所有任务","value":"7"},{"key":"unexec","name":"未执行","value":"7"}]
         * name : 常规护理任务-护理评估
         */

        private String ID;
        private BodyBeanX body;
        private String name;
        private List<HeadBeanX> head;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public BodyBeanX getBody() {
            return body;
        }

        public void setBody(BodyBeanX body) {
            this.body = body;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<HeadBeanX> getHead() {
            return head;
        }

        public void setHead(List<HeadBeanX> head) {
            this.head = head;
        }

        public static class BodyBeanX {
            private List<AllBeanX> all;
            private List<UnexecBeanX> unexec;

            public List<AllBeanX> getAll() {
                return all;
            }

            public void setAll(List<AllBeanX> all) {
                this.all = all;
            }

            public List<UnexecBeanX> getUnexec() {
                return unexec;
            }

            public void setUnexec(List<UnexecBeanX> unexec) {
                this.unexec = unexec;
            }

            public static class AllBeanX {
                /**
                 * code : 5||219
                 * name : 跌倒_坠床风险评估单
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

            public static class UnexecBeanX {
                /**
                 * code : 5||219
                 * name : 跌倒_坠床风险评估单
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
        }

        public static class HeadBeanX {
            /**
             * key : all
             * name : 所有任务
             * value : 7
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

    public static class NurTemperNumListBean {
        /**
         * ID : 7
         * body : {"all":[{"code":"TEMPERATURE","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"groupCode":"^rectemperature^temperature^","name":"体温(℃)","rank":"1","type":"T","value":"35"},{"code":"pulse","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"name":"脉搏（次/分）","rank":"3","type":"T","value":"35"},{"code":"breath","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"name":"呼吸（次/分）","rank":"4","type":"T","value":"35"},{"code":"PRESSURE","detail":{"2020-08-03 06:00":["02","11"]},"groupCode":"^diaPressure^sysPressure^","name":"血压","rank":"6","type":"T","value":"2"},{"code":"weight","detail":{"2020-08-03 06:00":["02","11"]},"name":"体重（KG）","rank":"14","type":"T","value":"2"}],"unexec":[{"code":"TEMPERATURE","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"groupCode":"^rectemperature^temperature^","name":"体温(℃)","rank":"1","type":"T","value":"35"},{"code":"pulse","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"name":"脉搏（次/分）","rank":"3","type":"T","value":"35"},{"code":"breath","detail":{"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]},"name":"呼吸（次/分）","rank":"4","type":"T","value":"35"},{"code":"PRESSURE","detail":{"2020-08-03 06:00":["02","11"]},"groupCode":"^diaPressure^sysPressure^","name":"血压","rank":"6","type":"T","value":"2"},{"code":"weight","detail":{"2020-08-03 06:00":["02","11"]},"name":"体重（KG）","rank":"14","type":"T","value":"2"}]}
         * head : [{"key":"all","name":"所有任务","value":"109"},{"key":"unexec","name":"未执行","value":"109"}]
         * name : 常规护理任务-体征
         */

        private String ID;
        private BodyBeanXX body;
        private String name;
        private List<HeadBeanXX> head;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public BodyBeanXX getBody() {
            return body;
        }

        public void setBody(BodyBeanXX body) {
            this.body = body;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<HeadBeanXX> getHead() {
            return head;
        }

        public void setHead(List<HeadBeanXX> head) {
            this.head = head;
        }

        public static class BodyBeanXX {
            private List<AllBeanXX> all;
            private List<UnexecBeanXX> unexec;

            public List<AllBeanXX> getAll() {
                return all;
            }

            public void setAll(List<AllBeanXX> all) {
                this.all = all;
            }

            public List<UnexecBeanXX> getUnexec() {
                return unexec;
            }

            public void setUnexec(List<UnexecBeanXX> unexec) {
                this.unexec = unexec;
            }

            public static class AllBeanXX {
                /**
                 * code : TEMPERATURE
                 * detail : {"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]}
                 * groupCode : ^rectemperature^temperature^
                 * name : 体温(℃)
                 * rank : 1
                 * type : T
                 * value : 35
                 */

                private String code;
                private DetailBean detail;
                private String groupCode;
                private String name;
                private String rank;
                private String type;
                private String value;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public DetailBean getDetail() {
                    return detail;
                }

                public void setDetail(DetailBean detail) {
                    this.detail = detail;
                }

                public String getGroupCode() {
                    return groupCode;
                }

                public void setGroupCode(String groupCode) {
                    this.groupCode = groupCode;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getRank() {
                    return rank;
                }

                public void setRank(String rank) {
                    this.rank = rank;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public static class DetailBean {
                    @SerializedName("2020-07-28 06:00")
                    private List<String> _$_202007280600315; // FIXME check this code
                    @SerializedName("2020-07-28 10:00")
                    private List<String> _$_20200728100083; // FIXME check this code
                    @SerializedName("2020-07-28 14:00")
                    private List<String> _$_202007281400267; // FIXME check this code
                    @SerializedName("2020-07-28 18:00")
                    private List<String> _$_202007281800316; // FIXME check this code
                    @SerializedName("2020-07-29 06:00")
                    private List<String> _$_202007290600224; // FIXME check this code
                    @SerializedName("2020-07-29 10:00")
                    private List<String> _$_20200729100057; // FIXME check this code
                    @SerializedName("2020-07-29 14:00")
                    private List<String> _$_202007291400235; // FIXME check this code
                    @SerializedName("2020-07-29 18:00")
                    private List<String> _$_20200729180044; // FIXME check this code
                    @SerializedName("2020-07-30 06:00")
                    private List<String> _$_202007300600190; // FIXME check this code
                    @SerializedName("2020-07-30 10:00")
                    private List<String> _$_20200730100057; // FIXME check this code
                    @SerializedName("2020-07-30 14:00")
                    private List<String> _$_202007301400231; // FIXME check this code
                    @SerializedName("2020-07-30 18:00")
                    private List<String> _$_20200730180053; // FIXME check this code
                    @SerializedName("2020-07-31 06:00")
                    private List<String> _$_202007310600133; // FIXME check this code
                    @SerializedName("2020-07-31 10:00")
                    private List<String> _$_20200731100019; // FIXME check this code
                    @SerializedName("2020-07-31 14:00")
                    private List<String> _$_202007311400144; // FIXME check this code
                    @SerializedName("2020-07-31 18:00")
                    private List<String> _$_202007311800239; // FIXME check this code
                    @SerializedName("2020-08-01 06:00")
                    private List<String> _$_202008010600174; // FIXME check this code
                    @SerializedName("2020-08-01 10:00")
                    private List<String> _$_202008011000265; // FIXME check this code
                    @SerializedName("2020-08-01 14:00")
                    private List<String> _$_202008011400331; // FIXME check this code
                    @SerializedName("2020-08-01 18:00")
                    private List<String> _$_202008011800204; // FIXME check this code
                    @SerializedName("2020-08-02 06:00")
                    private List<String> _$_202008020600234; // FIXME check this code
                    @SerializedName("2020-08-02 10:00")
                    private List<String> _$_20200802100090; // FIXME check this code
                    @SerializedName("2020-08-02 14:00")
                    private List<String> _$_202008021400216; // FIXME check this code
                    @SerializedName("2020-08-02 18:00")
                    private List<String> _$_20200802180081; // FIXME check this code
                    @SerializedName("2020-08-03 06:00")
                    private List<String> _$_202008030600122; // FIXME check this code
                    @SerializedName("2020-08-03 10:00")
                    private List<String> _$_202008031000131; // FIXME check this code
                    @SerializedName("2020-08-03 14:00")
                    private List<String> _$_20200803140098; // FIXME check this code
                    @SerializedName("2020-08-03 18:00")
                    private List<String> _$_202008031800281; // FIXME check this code

                    public List<String> get_$_202007280600315() {
                        return _$_202007280600315;
                    }

                    public void set_$_202007280600315(List<String> _$_202007280600315) {
                        this._$_202007280600315 = _$_202007280600315;
                    }

                    public List<String> get_$_20200728100083() {
                        return _$_20200728100083;
                    }

                    public void set_$_20200728100083(List<String> _$_20200728100083) {
                        this._$_20200728100083 = _$_20200728100083;
                    }

                    public List<String> get_$_202007281400267() {
                        return _$_202007281400267;
                    }

                    public void set_$_202007281400267(List<String> _$_202007281400267) {
                        this._$_202007281400267 = _$_202007281400267;
                    }

                    public List<String> get_$_202007281800316() {
                        return _$_202007281800316;
                    }

                    public void set_$_202007281800316(List<String> _$_202007281800316) {
                        this._$_202007281800316 = _$_202007281800316;
                    }

                    public List<String> get_$_202007290600224() {
                        return _$_202007290600224;
                    }

                    public void set_$_202007290600224(List<String> _$_202007290600224) {
                        this._$_202007290600224 = _$_202007290600224;
                    }

                    public List<String> get_$_20200729100057() {
                        return _$_20200729100057;
                    }

                    public void set_$_20200729100057(List<String> _$_20200729100057) {
                        this._$_20200729100057 = _$_20200729100057;
                    }

                    public List<String> get_$_202007291400235() {
                        return _$_202007291400235;
                    }

                    public void set_$_202007291400235(List<String> _$_202007291400235) {
                        this._$_202007291400235 = _$_202007291400235;
                    }

                    public List<String> get_$_20200729180044() {
                        return _$_20200729180044;
                    }

                    public void set_$_20200729180044(List<String> _$_20200729180044) {
                        this._$_20200729180044 = _$_20200729180044;
                    }

                    public List<String> get_$_202007300600190() {
                        return _$_202007300600190;
                    }

                    public void set_$_202007300600190(List<String> _$_202007300600190) {
                        this._$_202007300600190 = _$_202007300600190;
                    }

                    public List<String> get_$_20200730100057() {
                        return _$_20200730100057;
                    }

                    public void set_$_20200730100057(List<String> _$_20200730100057) {
                        this._$_20200730100057 = _$_20200730100057;
                    }

                    public List<String> get_$_202007301400231() {
                        return _$_202007301400231;
                    }

                    public void set_$_202007301400231(List<String> _$_202007301400231) {
                        this._$_202007301400231 = _$_202007301400231;
                    }

                    public List<String> get_$_20200730180053() {
                        return _$_20200730180053;
                    }

                    public void set_$_20200730180053(List<String> _$_20200730180053) {
                        this._$_20200730180053 = _$_20200730180053;
                    }

                    public List<String> get_$_202007310600133() {
                        return _$_202007310600133;
                    }

                    public void set_$_202007310600133(List<String> _$_202007310600133) {
                        this._$_202007310600133 = _$_202007310600133;
                    }

                    public List<String> get_$_20200731100019() {
                        return _$_20200731100019;
                    }

                    public void set_$_20200731100019(List<String> _$_20200731100019) {
                        this._$_20200731100019 = _$_20200731100019;
                    }

                    public List<String> get_$_202007311400144() {
                        return _$_202007311400144;
                    }

                    public void set_$_202007311400144(List<String> _$_202007311400144) {
                        this._$_202007311400144 = _$_202007311400144;
                    }

                    public List<String> get_$_202007311800239() {
                        return _$_202007311800239;
                    }

                    public void set_$_202007311800239(List<String> _$_202007311800239) {
                        this._$_202007311800239 = _$_202007311800239;
                    }

                    public List<String> get_$_202008010600174() {
                        return _$_202008010600174;
                    }

                    public void set_$_202008010600174(List<String> _$_202008010600174) {
                        this._$_202008010600174 = _$_202008010600174;
                    }

                    public List<String> get_$_202008011000265() {
                        return _$_202008011000265;
                    }

                    public void set_$_202008011000265(List<String> _$_202008011000265) {
                        this._$_202008011000265 = _$_202008011000265;
                    }

                    public List<String> get_$_202008011400331() {
                        return _$_202008011400331;
                    }

                    public void set_$_202008011400331(List<String> _$_202008011400331) {
                        this._$_202008011400331 = _$_202008011400331;
                    }

                    public List<String> get_$_202008011800204() {
                        return _$_202008011800204;
                    }

                    public void set_$_202008011800204(List<String> _$_202008011800204) {
                        this._$_202008011800204 = _$_202008011800204;
                    }

                    public List<String> get_$_202008020600234() {
                        return _$_202008020600234;
                    }

                    public void set_$_202008020600234(List<String> _$_202008020600234) {
                        this._$_202008020600234 = _$_202008020600234;
                    }

                    public List<String> get_$_20200802100090() {
                        return _$_20200802100090;
                    }

                    public void set_$_20200802100090(List<String> _$_20200802100090) {
                        this._$_20200802100090 = _$_20200802100090;
                    }

                    public List<String> get_$_202008021400216() {
                        return _$_202008021400216;
                    }

                    public void set_$_202008021400216(List<String> _$_202008021400216) {
                        this._$_202008021400216 = _$_202008021400216;
                    }

                    public List<String> get_$_20200802180081() {
                        return _$_20200802180081;
                    }

                    public void set_$_20200802180081(List<String> _$_20200802180081) {
                        this._$_20200802180081 = _$_20200802180081;
                    }

                    public List<String> get_$_202008030600122() {
                        return _$_202008030600122;
                    }

                    public void set_$_202008030600122(List<String> _$_202008030600122) {
                        this._$_202008030600122 = _$_202008030600122;
                    }

                    public List<String> get_$_202008031000131() {
                        return _$_202008031000131;
                    }

                    public void set_$_202008031000131(List<String> _$_202008031000131) {
                        this._$_202008031000131 = _$_202008031000131;
                    }

                    public List<String> get_$_20200803140098() {
                        return _$_20200803140098;
                    }

                    public void set_$_20200803140098(List<String> _$_20200803140098) {
                        this._$_20200803140098 = _$_20200803140098;
                    }

                    public List<String> get_$_202008031800281() {
                        return _$_202008031800281;
                    }

                    public void set_$_202008031800281(List<String> _$_202008031800281) {
                        this._$_202008031800281 = _$_202008031800281;
                    }
                }
            }

            public static class UnexecBeanXX {
                /**
                 * code : TEMPERATURE
                 * detail : {"2020-07-28 06:00":["11"],"2020-07-28 10:00":["11"],"2020-07-28 14:00":["02","11"],"2020-07-28 18:00":["11"],"2020-07-29 06:00":["11"],"2020-07-29 10:00":["11"],"2020-07-29 14:00":["02","11"],"2020-07-29 18:00":["11"],"2020-07-30 06:00":["11"],"2020-07-30 10:00":["11"],"2020-07-30 14:00":["02","11"],"2020-07-30 18:00":["11"],"2020-07-31 06:00":["11"],"2020-07-31 10:00":["11"],"2020-07-31 14:00":["02","11"],"2020-07-31 18:00":["11"],"2020-08-01 06:00":["11"],"2020-08-01 10:00":["11"],"2020-08-01 14:00":["02","11"],"2020-08-01 18:00":["11"],"2020-08-02 06:00":["11"],"2020-08-02 10:00":["11"],"2020-08-02 14:00":["02","11"],"2020-08-02 18:00":["11"],"2020-08-03 06:00":["11"],"2020-08-03 10:00":["11"],"2020-08-03 14:00":["02","11"],"2020-08-03 18:00":["11"]}
                 * groupCode : ^rectemperature^temperature^
                 * name : 体温(℃)
                 * rank : 1
                 * type : T
                 * value : 35
                 */

                private String code;
                private DetailBeanX detail;
                private String groupCode;
                private String name;
                private String rank;
                private String type;
                private String value;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public DetailBeanX getDetail() {
                    return detail;
                }

                public void setDetail(DetailBeanX detail) {
                    this.detail = detail;
                }

                public String getGroupCode() {
                    return groupCode;
                }

                public void setGroupCode(String groupCode) {
                    this.groupCode = groupCode;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getRank() {
                    return rank;
                }

                public void setRank(String rank) {
                    this.rank = rank;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public static class DetailBeanX {
                    @SerializedName("2020-07-28 06:00")
                    private List<String> _$_20200728060012; // FIXME check this code
                    @SerializedName("2020-07-28 10:00")
                    private List<String> _$_202007281000314; // FIXME check this code
                    @SerializedName("2020-07-28 14:00")
                    private List<String> _$_202007281400287; // FIXME check this code
                    @SerializedName("2020-07-28 18:00")
                    private List<String> _$_202007281800328; // FIXME check this code
                    @SerializedName("2020-07-29 06:00")
                    private List<String> _$_202007290600103; // FIXME check this code
                    @SerializedName("2020-07-29 10:00")
                    private List<String> _$_202007291000216; // FIXME check this code
                    @SerializedName("2020-07-29 14:00")
                    private List<String> _$_202007291400220; // FIXME check this code
                    @SerializedName("2020-07-29 18:00")
                    private List<String> _$_202007291800238; // FIXME check this code
                    @SerializedName("2020-07-30 06:00")
                    private List<String> _$_20200730060034; // FIXME check this code
                    @SerializedName("2020-07-30 10:00")
                    private List<String> _$_2020073010005; // FIXME check this code
                    @SerializedName("2020-07-30 14:00")
                    private List<String> _$_202007301400331; // FIXME check this code
                    @SerializedName("2020-07-30 18:00")
                    private List<String> _$_202007301800289; // FIXME check this code
                    @SerializedName("2020-07-31 06:00")
                    private List<String> _$_202007310600151; // FIXME check this code
                    @SerializedName("2020-07-31 10:00")
                    private List<String> _$_20200731100041; // FIXME check this code
                    @SerializedName("2020-07-31 14:00")
                    private List<String> _$_20200731140040; // FIXME check this code
                    @SerializedName("2020-07-31 18:00")
                    private List<String> _$_20200731180092; // FIXME check this code
                    @SerializedName("2020-08-01 06:00")
                    private List<String> _$_202008010600186; // FIXME check this code
                    @SerializedName("2020-08-01 10:00")
                    private List<String> _$_202008011000132; // FIXME check this code
                    @SerializedName("2020-08-01 14:00")
                    private List<String> _$_20200801140010; // FIXME check this code
                    @SerializedName("2020-08-01 18:00")
                    private List<String> _$_202008011800180; // FIXME check this code
                    @SerializedName("2020-08-02 06:00")
                    private List<String> _$_202008020600196; // FIXME check this code
                    @SerializedName("2020-08-02 10:00")
                    private List<String> _$_202008021000103; // FIXME check this code
                    @SerializedName("2020-08-02 14:00")
                    private List<String> _$_20200802140073; // FIXME check this code
                    @SerializedName("2020-08-02 18:00")
                    private List<String> _$_202008021800202; // FIXME check this code
                    @SerializedName("2020-08-03 06:00")
                    private List<String> _$_20200803060037; // FIXME check this code
                    @SerializedName("2020-08-03 10:00")
                    private List<String> _$_202008031000258; // FIXME check this code
                    @SerializedName("2020-08-03 14:00")
                    private List<String> _$_202008031400177; // FIXME check this code
                    @SerializedName("2020-08-03 18:00")
                    private List<String> _$_20200803180034; // FIXME check this code

                    public List<String> get_$_20200728060012() {
                        return _$_20200728060012;
                    }

                    public void set_$_20200728060012(List<String> _$_20200728060012) {
                        this._$_20200728060012 = _$_20200728060012;
                    }

                    public List<String> get_$_202007281000314() {
                        return _$_202007281000314;
                    }

                    public void set_$_202007281000314(List<String> _$_202007281000314) {
                        this._$_202007281000314 = _$_202007281000314;
                    }

                    public List<String> get_$_202007281400287() {
                        return _$_202007281400287;
                    }

                    public void set_$_202007281400287(List<String> _$_202007281400287) {
                        this._$_202007281400287 = _$_202007281400287;
                    }

                    public List<String> get_$_202007281800328() {
                        return _$_202007281800328;
                    }

                    public void set_$_202007281800328(List<String> _$_202007281800328) {
                        this._$_202007281800328 = _$_202007281800328;
                    }

                    public List<String> get_$_202007290600103() {
                        return _$_202007290600103;
                    }

                    public void set_$_202007290600103(List<String> _$_202007290600103) {
                        this._$_202007290600103 = _$_202007290600103;
                    }

                    public List<String> get_$_202007291000216() {
                        return _$_202007291000216;
                    }

                    public void set_$_202007291000216(List<String> _$_202007291000216) {
                        this._$_202007291000216 = _$_202007291000216;
                    }

                    public List<String> get_$_202007291400220() {
                        return _$_202007291400220;
                    }

                    public void set_$_202007291400220(List<String> _$_202007291400220) {
                        this._$_202007291400220 = _$_202007291400220;
                    }

                    public List<String> get_$_202007291800238() {
                        return _$_202007291800238;
                    }

                    public void set_$_202007291800238(List<String> _$_202007291800238) {
                        this._$_202007291800238 = _$_202007291800238;
                    }

                    public List<String> get_$_20200730060034() {
                        return _$_20200730060034;
                    }

                    public void set_$_20200730060034(List<String> _$_20200730060034) {
                        this._$_20200730060034 = _$_20200730060034;
                    }

                    public List<String> get_$_2020073010005() {
                        return _$_2020073010005;
                    }

                    public void set_$_2020073010005(List<String> _$_2020073010005) {
                        this._$_2020073010005 = _$_2020073010005;
                    }

                    public List<String> get_$_202007301400331() {
                        return _$_202007301400331;
                    }

                    public void set_$_202007301400331(List<String> _$_202007301400331) {
                        this._$_202007301400331 = _$_202007301400331;
                    }

                    public List<String> get_$_202007301800289() {
                        return _$_202007301800289;
                    }

                    public void set_$_202007301800289(List<String> _$_202007301800289) {
                        this._$_202007301800289 = _$_202007301800289;
                    }

                    public List<String> get_$_202007310600151() {
                        return _$_202007310600151;
                    }

                    public void set_$_202007310600151(List<String> _$_202007310600151) {
                        this._$_202007310600151 = _$_202007310600151;
                    }

                    public List<String> get_$_20200731100041() {
                        return _$_20200731100041;
                    }

                    public void set_$_20200731100041(List<String> _$_20200731100041) {
                        this._$_20200731100041 = _$_20200731100041;
                    }

                    public List<String> get_$_20200731140040() {
                        return _$_20200731140040;
                    }

                    public void set_$_20200731140040(List<String> _$_20200731140040) {
                        this._$_20200731140040 = _$_20200731140040;
                    }

                    public List<String> get_$_20200731180092() {
                        return _$_20200731180092;
                    }

                    public void set_$_20200731180092(List<String> _$_20200731180092) {
                        this._$_20200731180092 = _$_20200731180092;
                    }

                    public List<String> get_$_202008010600186() {
                        return _$_202008010600186;
                    }

                    public void set_$_202008010600186(List<String> _$_202008010600186) {
                        this._$_202008010600186 = _$_202008010600186;
                    }

                    public List<String> get_$_202008011000132() {
                        return _$_202008011000132;
                    }

                    public void set_$_202008011000132(List<String> _$_202008011000132) {
                        this._$_202008011000132 = _$_202008011000132;
                    }

                    public List<String> get_$_20200801140010() {
                        return _$_20200801140010;
                    }

                    public void set_$_20200801140010(List<String> _$_20200801140010) {
                        this._$_20200801140010 = _$_20200801140010;
                    }

                    public List<String> get_$_202008011800180() {
                        return _$_202008011800180;
                    }

                    public void set_$_202008011800180(List<String> _$_202008011800180) {
                        this._$_202008011800180 = _$_202008011800180;
                    }

                    public List<String> get_$_202008020600196() {
                        return _$_202008020600196;
                    }

                    public void set_$_202008020600196(List<String> _$_202008020600196) {
                        this._$_202008020600196 = _$_202008020600196;
                    }

                    public List<String> get_$_202008021000103() {
                        return _$_202008021000103;
                    }

                    public void set_$_202008021000103(List<String> _$_202008021000103) {
                        this._$_202008021000103 = _$_202008021000103;
                    }

                    public List<String> get_$_20200802140073() {
                        return _$_20200802140073;
                    }

                    public void set_$_20200802140073(List<String> _$_20200802140073) {
                        this._$_20200802140073 = _$_20200802140073;
                    }

                    public List<String> get_$_202008021800202() {
                        return _$_202008021800202;
                    }

                    public void set_$_202008021800202(List<String> _$_202008021800202) {
                        this._$_202008021800202 = _$_202008021800202;
                    }

                    public List<String> get_$_20200803060037() {
                        return _$_20200803060037;
                    }

                    public void set_$_20200803060037(List<String> _$_20200803060037) {
                        this._$_20200803060037 = _$_20200803060037;
                    }

                    public List<String> get_$_202008031000258() {
                        return _$_202008031000258;
                    }

                    public void set_$_202008031000258(List<String> _$_202008031000258) {
                        this._$_202008031000258 = _$_202008031000258;
                    }

                    public List<String> get_$_202008031400177() {
                        return _$_202008031400177;
                    }

                    public void set_$_202008031400177(List<String> _$_202008031400177) {
                        this._$_202008031400177 = _$_202008031400177;
                    }

                    public List<String> get_$_20200803180034() {
                        return _$_20200803180034;
                    }

                    public void set_$_20200803180034(List<String> _$_20200803180034) {
                        this._$_20200803180034 = _$_20200803180034;
                    }
                }
            }
        }

        public static class HeadBeanXX {
            /**
             * key : all
             * name : 所有任务
             * value : 109
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

    public static class OrdNumListBean {
        /**
         * ID : 1
         * body : {"all":[{"code":"DefaultSee","name":"需处理","value":"5"},{"code":"BLD","name":"病理单*","value":"0"},{"code":"CQSYD","name":"输液单*","value":"77"},{"code":"CQZSD","name":"注射单*","value":"0"},{"code":"CUDY","name":"出院带药单*","value":"0"},{"code":"HLZLD","name":"处置治疗单*","value":"7"},{"code":"JCD","name":"检查单*","value":"0"},{"code":"JYD","name":"检验单*","value":"0"},{"code":"PSD","name":"皮试单*","value":"0"},{"code":"SQZBD","name":"术前准备单*","value":"133"},{"code":"SXD","name":"输血单*","value":"0"},{"code":"YSHLD","name":"饮食护理单*","value":"0"},{"code":"cqkfyd","name":"口服药单*","value":"49"}],"unexec":[{"code":"BLD","name":"病理单*","value":"0"},{"code":"CQSYD","name":"输液单*","value":"77"},{"code":"CQZSD","name":"注射单*","value":"0"},{"code":"CUDY","name":"出院带药单*","value":"0"},{"code":"HLZLD","name":"处置治疗单*","value":"7"},{"code":"JCD","name":"检查单*","value":"0"},{"code":"JYD","name":"检验单*","value":"0"},{"code":"PSD","name":"皮试单*","value":"0"},{"code":"SQZBD","name":"术前准备单*","value":"133"},{"code":"SXD","name":"输血单*","value":"0"},{"code":"YSHLD","name":"饮食护理单*","value":"0"},{"code":"cqkfyd","name":"口服药单*","value":"49"}],"unsee":[{"code":"DefaultSee","name":"需处理","value":"5"}]}
         * head : [{"key":"all","name":"所有任务","value":"271"},{"key":"unsee","name":"未处理","value":"5"},{"key":"unexec","name":"未执行","value":"266"}]
         * name : 医嘱执行统计图
         */

        private String ID;
        private BodyBeanXXX body;
        private String name;
        private List<HeadBeanXXX> head;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public BodyBeanXXX getBody() {
            return body;
        }

        public void setBody(BodyBeanXXX body) {
            this.body = body;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<HeadBeanXXX> getHead() {
            return head;
        }

        public void setHead(List<HeadBeanXXX> head) {
            this.head = head;
        }

        public static class BodyBeanXXX {
            private List<AllBeanXXX> all;
            private List<UnexecBeanXXX> unexec;
            private List<UnseeBean> unsee;

            public List<AllBeanXXX> getAll() {
                return all;
            }

            public void setAll(List<AllBeanXXX> all) {
                this.all = all;
            }

            public List<UnexecBeanXXX> getUnexec() {
                return unexec;
            }

            public void setUnexec(List<UnexecBeanXXX> unexec) {
                this.unexec = unexec;
            }

            public List<UnseeBean> getUnsee() {
                return unsee;
            }

            public void setUnsee(List<UnseeBean> unsee) {
                this.unsee = unsee;
            }

            public static class AllBeanXXX {
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

            public static class UnexecBeanXXX {
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

        public static class HeadBeanXXX {
            /**
             * key : all
             * name : 所有任务
             * value : 271
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

    public static class TimesListBean {
        /**
         * timeEnd : 2020-08-04 07:59
         * timeStt : 2020-08-03 17:31
         * timesDesc : 夜班
         */

        private String timeEnd;
        private String timeStt;
        private String timesDesc;

        public String getTimeEnd() {
            return timeEnd;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }

        public String getTimeStt() {
            return timeStt;
        }

        public void setTimeStt(String timeStt) {
            this.timeStt = timeStt;
        }

        public String getTimesDesc() {
            return timesDesc;
        }

        public void setTimesDesc(String timesDesc) {
            this.timesDesc = timesDesc;
        }
    }
}
