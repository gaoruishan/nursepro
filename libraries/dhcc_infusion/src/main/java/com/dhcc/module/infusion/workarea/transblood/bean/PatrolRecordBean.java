package com.dhcc.module.infusion.workarea.transblood.bean;

public class PatrolRecordBean extends VitalSignsBean{
        /**
         * date : 2018-09-29
         * effect : 无
         * id : 16
         * situation : 
         * speed : 20
         * time : 10:50
         * user : 护士01
         */

        private String date;
        private String effect;
        private String id;
        private String situation;
        private String speed;
        private String time;
        private String user;
        private String tourType;

    public String getTourType() {
        return tourType == null ? "" : tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSituation() {
            return situation;
        }

        public void setSituation(String situation) {
            this.situation = situation;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }