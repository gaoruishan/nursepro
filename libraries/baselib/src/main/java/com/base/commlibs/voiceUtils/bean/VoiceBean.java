package com.base.commlibs.voiceUtils.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.base.commlibs.bean
 * <p>
 * author Q
 * Date: 2020/10/19
 * Time:9:07
 */
public class VoiceBean {
     /**
     * command : {"action":"生命体征","bedNo":"15","code":2002}
     * form : {"code":3002,"data":[{"key":"呼吸","value":"80"}]}
     */

    private CommandBean command=new CommandBean();
    private FormBean form=new FormBean();
    private Boolean isLast = false;

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    public CommandBean getCommand() {
        return command;
    }

    public void setCommand(CommandBean command) {
        this.command = command;
    }

    public FormBean getForm() {
        return form;
    }

    public void setForm(FormBean form) {
        this.form = form;
    }

    public static class CommandBean {
        /**
         * action : 生命体征
         * bedNo : 15
         * code : 2002
         */

        private String action="";
        private String bedNo="";
        private int code;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getBedNo() {
            return bedNo;
        }

        public void setBedNo(String bedNo) {
            this.bedNo = bedNo;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static class FormBean {
        /**
         * code : 3002
         * data : [{"key":"呼吸","value":"80"}]
         */

        private int code;
        private List<DataBean> data=new ArrayList<>();

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * key : 呼吸
             * value : 80
             */

            private String key="";
            private String value="";

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
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
