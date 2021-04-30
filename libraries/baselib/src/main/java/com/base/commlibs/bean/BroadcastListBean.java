package com.base.commlibs.bean;

public class BroadcastListBean {
        /**
         * Action : ACTION_CONTENT_NOTIFY_MOTO
         * Decode : com.motorolasolutions.emdk.datawedge.data_string
         * Name : 摩托
         */

        private String Action;
        private String Decode;
        private String Name;

    public BroadcastListBean(String action, String decode, String name) {
        Action = action;
        Decode = decode;
        Name = name;
    }

    public String getAction() {
            return Action;
        }

        public void setAction(String Action) {
            this.Action = Action;
        }

        public String getDecode() {
            return Decode;
        }

        public void setDecode(String Decode) {
            this.Decode = Decode;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
