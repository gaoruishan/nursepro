package com.dhcc.module.nurse.education.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-20/15:38
 * @email:grs0515@163.com
 */
public class HealthEduAddBean extends CommResult {


    /**
     * status : 0
     * ordList : [{"name":"福州","type":"1"},{"name":"厦门","type":"1"},{"name":"泉州","type":"1"},{"name":"福州2","type":"2"},{"name":"厦门2","type":"2"},{"name":"泉州2","type":"2"},{"name":"福州3","type":"3"},{"name":"厦门3","type":"3"},{"name":"泉州3","type":"3"}]
     */

    private List<OrdListBean> ordList;

    public List<OrdListBean> getOrdList() {
        return ordList;
    }

    public void setOrdList(List<OrdListBean> ordList) {
        this.ordList = ordList;
    }

    public static class OrdListBean {
        /**
         * name : 福州
         * type : 1
         */

        private String name;
        private String type;
        private boolean hide;

        public boolean isHide() {
            return hide;
        }

        public void setHide(boolean hide) {
            this.hide = hide;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
