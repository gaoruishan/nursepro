package com.dhcc.nursepro.workarea.workareabean;

import com.base.commlibs.bean.CommBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainConfigBean {

    /**
     * {
     *     "curDateTime": "2021-06-16 14:52:49",
     *     "ifVoice": "Y",
     *     "mainList": [
     *         {
     *             "mainSubList": [
     *                 {
     *                     "moduleCode": "BEDMAP",
     *                     "moduleDesc": "床位图"
     *                 },
     *                 {
     *                     "moduleCode": "MODELDETAIL",
     *                     "moduleDesc": "护理病历"
     *                 },
     *                 {
     *                     "moduleCode": "NURTOUR",
     *                     "moduleDesc": "护理巡视"
     *                 },
     *                 {
     *                     "moduleCode": "BLOOD",
     *                     "moduleDesc": "输血系统"
     *                 },
     *                 {
     *                     "moduleCode": "MODELDETAIL",
     *                     "moduleDesc": "护理病历"
     *                 },
     *                 {
     *                     "moduleCode": "HealthEduFragment",
     *                     "moduleDesc": "健康宣教"
     *                 },
     *                 {
     *                     "moduleCode": "TaskOverviewFragment",
     *                     "moduleDesc": "任务总览"
     *                 },
     *                 {
     *                     "moduleCode": "NurPlanFragment",
     *                     "moduleDesc": "护理计划"
     *                 },
     *                 {
     *                     "moduleCode": "BloodSugarFragment",
     *                     "moduleDesc": "血糖采集"
     *                 }
     *             ],
     *             "menuName": "综合"
     *         },
     *         {
     *             "mainSubList": [
     *                 {
     *                     "moduleCode": "DOSINGREVIEW",
     *                     "moduleDesc": "输液复核"
     *                 },
     *                 {
     *                     "moduleCode": "IFOrdRec",
     *                     "moduleDesc": "静配接收"
     *                 }
     *             ],
     *             "menuName": "输液"
     *         },
     *         {
     *             "mainSubList": [
     *                 {
     *                     "moduleCode": "LABOUT",
     *                     "moduleDesc": "检验打包"
     *                 },
     *                 {
     *                     "moduleCode": "PLYOUT",
     *                     "moduleDesc": "病理运送"
     *                 }
     *             ],
     *             "menuName": "标本"
     *         },
     *         {
     *             "mainSubList": [
     *                 {
     *                     "moduleCode": "EVENTS",
     *                     "moduleDesc": "事件管理"
     *                 },
     *                 {
     *                     "moduleCode": "DOCORDERLIST",
     *                     "moduleDesc": "医嘱单"
     *                 },
     *                 {
     *                     "moduleCode": "CHECK",
     *                     "moduleDesc": "检查报告"
     *                 },
     *                 {
     *                     "moduleCode": "LAB",
     *                     "moduleDesc": "检验报告"
     *                 },
     *                 {
     *                     "moduleCode": "OPERATION",
     *                     "moduleDesc": "手术申请"
     *                 },
     *                 {
     *                     "moduleCode": "MILK",
     *                     "moduleDesc": "母乳闭环"
     *                 },
     *                 {
     *                     "moduleCode": "MOTHERBABYLINK",
     *                     "moduleDesc": "母婴关联"
     *                 }
     *             ],
     *             "menuName": "数据查询"
     *         },
     *         {
     *             "mainSubList": [
     *                 {
     *                     "moduleCode": "ORDERSEARCH",
     *                     "moduleDesc": "医嘱查询"
     *                 },
     *                 {
     *                     "moduleCode": "ORDEREXECUTE",
     *                     "moduleDesc": "医嘱执行"
     *                 },
     *                 {
     *                     "moduleCode": "ALLOTBED",
     *                     "moduleDesc": "入院分床"
     *                 },
     *                 {
     *                     "moduleCode": "VITALSIGN",
     *                     "moduleDesc": "生命体征"
     *                 }
     *             ],
     *             "menuName": "数据查询"
     *         }
     *     ],
     *     "msg": "",
     *     "msgcode": "999999",
     *     "scantimes": "2",
     *     "schEnDateTime": "2021-06-16 23:59",
     *     "schStDateTime": "2021-06-16 00:00",
     *     "screenParts": [
     *         {
     *             "commonKey": "false",
     *             "danjuStr": "DefaultSee!PSD!BLD",
     *             "keyCode": "execFlag",
     *             "keyDesc": "执行类型",
     *             "keyType": "Single",
     *             "keyValue": "未执行!已执行"
     *         },
     *         {
     *             "commonKey": "false",
     *             "danjuStr": "DefaultSee!PSD!BLD",
     *             "keyCode": "oecprDesc",
     *             "keyDesc": "优先级",
     *             "keyType": "Single",
     *             "keyValue": "长期!临时"
     *         },
     *         {
     *             "commonKey": "false",
     *             "danjuStr": "DefaultSee!PSD!BLD",
     *             "keyCode": "ordType",
     *             "keyDesc": "类型",
     *             "keyType": "Multiple",
     *             "keyValue": "药品!检验!其他"
     *         }
     *     ],
     *     "status": "0"
     * }
     */

    /**
     * 当前时间
     */
    private String curDateTime;
    /**
     * 是否开启语音
     */
    private String ifVoice;
    /**
     * 功能列表
     */
    private List<MainListBean> mainList;
    /**
     * msg
     */
    private String msg;
    /**
     * msgcode
     */
    private String msgcode;
    /**
     * 输血系统扫码次数 1或2
     */
    private String scantimes;
    /**
     * 全局查询结束时间
     */
    private String schEnDateTime;
    /**
     * 全局查询开始时间
     */
    private String schStDateTime;
    /**
     * 医嘱查询/执行 筛选项
     */
    private List<ScreenPartsBean> screenParts;
    /**
     * status
     */
    private String status;

    public String getCurDateTime() {
        return curDateTime;
    }

    public void setCurDateTime(String curDateTime) {
        this.curDateTime = curDateTime;
    }

    public String getIfVoice() {
        return ifVoice;
    }

    public void setIfVoice(String ifVoice) {
        this.ifVoice = ifVoice;
    }

    public List<MainListBean> getMainList() {
        return mainList;
    }

    public void setMainList(List<MainListBean> mainList) {
        this.mainList = mainList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }

    public String getScantimes() {
        return scantimes;
    }

    public void setScantimes(String scantimes) {
        this.scantimes = scantimes;
    }

    public String getSchEnDateTime() {
        return schEnDateTime;
    }

    public void setSchEnDateTime(String schEnDateTime) {
        this.schEnDateTime = schEnDateTime;
    }

    public String getSchStDateTime() {
        return schStDateTime;
    }

    public void setSchStDateTime(String schStDateTime) {
        this.schStDateTime = schStDateTime;
    }

    public List<ScreenPartsBean> getScreenParts() {
        return screenParts;
    }

    public void setScreenParts(List<ScreenPartsBean> screenParts) {
        this.screenParts = screenParts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MainListBean {
        /**
         * 功能分类子列表
         */
        private List<MainSubListBean> mainSubList;
        /**
         * 功能分类名称
         */
        private String menuName;

        public List<MainSubListBean> getMainSubList() {
            return mainSubList;
        }

        public void setMainSubList(List<MainSubListBean> mainSubList) {
            this.mainSubList = mainSubList;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public static class MainSubListBean {
            /**
             * 功能Code
             */
            private String moduleCode;
            /**
             * 功能描述
             */
            private String moduleDesc;

            private int imgResouseId;

            private String moduleUrl;

            private String fragmentClassName;

            private String menuName;

            public MainSubListBean() {
            }

            public MainSubListBean(String moduleCode, String moduleDesc) {
                this.moduleCode = moduleCode;
                this.moduleDesc = moduleDesc;
            }

            public MainSubListBean(String moduleCode, String moduleDesc, String moduleUrl) {
                this.moduleCode = moduleCode;
                this.moduleDesc = moduleDesc;
                this.moduleUrl = moduleUrl;
            }

            /**
             * map2.put("desc","检验结果");
             * map2.put("fragName", LabResultListFragment.class.getName());
             * map2.put("fragicon",R.drawable.icon_lab);
             * map2.put("singleModel","2");
             *
             * @return
             */
            public Map toMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("code", moduleCode);
                map.put("desc", moduleDesc);
                map.put("fragicon", imgResouseId);
                map.put("moduleUrl", moduleUrl);
                map.put("fragName", fragmentClassName);
                map.put("menuName", menuName);

                return map;
            }

            public String getModuleCode() {
                return moduleCode;
            }

            public void setModuleCode(String moduleCode) {
                this.moduleCode = moduleCode;
            }

            public String getModuleDesc() {
                return moduleDesc;
            }

            public void setModuleDesc(String moduleDesc) {
                this.moduleDesc = moduleDesc;
            }

            public int getImgResouseId() {
                return imgResouseId;
            }

            public void setImgResouseId(int imgResouseId) {
                this.imgResouseId = imgResouseId;
            }

            public String getModuleUrl() {
                return moduleUrl;
            }

            public void setModuleUrl(String moduleUrl) {
                this.moduleUrl = moduleUrl;
            }

            public String getFragmentClassName() {
                return fragmentClassName;
            }

            public void setFragmentClassName(String fragmentClassName) {
                this.fragmentClassName = fragmentClassName;
            }

            public String getMenuName() {
                return menuName;
            }

            public void setMenuName(String menuName) {
                this.menuName = menuName;
            }
        }
    }

    public static class ScreenPartsBean {
        /**
         * commonKey
         */
        private String commonKey;
        /**
         * 筛选单据范围
         */
        private String danjuStr;
        /**
         * keyCode
         */
        private String keyCode;
        /**
         * keyDesc
         */
        private String keyDesc;
        /**
         * keyType
         */
        private String keyType;
        /**
         * keyValue
         */
        private String keyValue;

        private List<CommBean> listBean;

        @Override
        public String toString() {
            return "ScreenPartsBean{" + "keyCode='" + keyCode + '\'' + ", keyType='" + keyType + '\'' + ", keyValue='" + keyValue + '\'' + ", danjuStr='" + danjuStr + '\'' + ", keyDesc='" + keyDesc + '\'' + ", commonKey=" + commonKey + '}';
        }

        public String getCommonKey() {
            return commonKey;
        }

        public void setCommonKey(String commonKey) {
            this.commonKey = commonKey;
        }

        public String getDanjuStr() {
            return danjuStr;
        }

        public void setDanjuStr(String danjuStr) {
            this.danjuStr = danjuStr;
        }

        public String getKeyCode() {
            return keyCode;
        }

        public void setKeyCode(String keyCode) {
            this.keyCode = keyCode;
        }

        public String getKeyDesc() {
            return keyDesc;
        }

        public void setKeyDesc(String keyDesc) {
            this.keyDesc = keyDesc;
        }

        public String getKeyType() {
            return keyType;
        }

        public void setKeyType(String keyType) {
            this.keyType = keyType;
        }

        public String getKeyValue() {
            return keyValue;
        }

        public void setKeyValue(String keyValue) {
            this.keyValue = keyValue;
        }

        public List<CommBean> getListBean() {
            return listBean;
        }

        public void setListBean(List<CommBean> listBean) {
            this.listBean = listBean;
        }
    }
}
