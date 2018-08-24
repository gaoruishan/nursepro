package com.dhcc.nursepro.workarea.ordersearch.bean;

import java.util.List;

/**
 * OrderSearchBean
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderSearchBean {

    /**
     * buttons : [{"code":"seeOrder","desc":"处理医嘱"},{"code":"cancelSeeOrder","desc":"撤销处理医嘱"}]
     * msg :
     * msgcode : 999999
     * orders : [{"bedCode":"04","name":"zfm03","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}]]}]
     * sheetList : [{"code":"DefaultSee","desc":"需处理医嘱"},{"code":"WZX","desc":"未执行"},{"code":"CQSYD","desc":"输液单"},{"code":"cqkfyd","desc":"口服药单"},{"code":"CQZSD","desc":"注射单"},{"code":"HLZLD","desc":"处置治疗单"},{"code":"YSHLD","desc":"饮食护理单"},{"code":"JCD*","desc":"检查单"},{"code":"JYD","desc":"检验单"},{"code":"BLD","desc":"病理单"},{"code":"PSD","desc":"皮试单"},{"code":"SXD","desc":"输血单"},{"code":"SQZBD","desc":"术前准备单"},{"code":"CUDY","desc":"出院带药单"},{"code":"Default","desc":"全部医嘱"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<ButtonsBean> buttons;
    private List<OrdersBean> orders;
    private List<SheetListBean> sheetList;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ButtonsBean> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonsBean> buttons) {
        this.buttons = buttons;
    }

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public List<SheetListBean> getSheetList() {
        return sheetList;
    }

    public void setSheetList(List<SheetListBean> sheetList) {
        this.sheetList = sheetList;
    }

    public static class ButtonsBean {
        /**
         * code : seeOrder
         * desc : 处理医嘱
         */

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class OrdersBean {
        /**
         * bedCode : 04
         * name : zfm03
         * patOrds : [[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}]]
         */

        private String bedCode;
        private String name;
        private List<List<PatOrdsBean>> patOrds;

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<List<PatOrdsBean>> getPatOrds() {
            return patOrds;
        }

        public void setPatOrds(List<List<PatOrdsBean>> patOrds) {
            this.patOrds = patOrds;
        }

        public static class PatOrdsBean {
            /**
             * orderInfo : {"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""}
             * type : main
             */

            private OrderInfoBean orderInfo;
            private String type;

            public OrderInfoBean getOrderInfo() {
                return orderInfo;
            }

            public void setOrderInfo(OrderInfoBean orderInfo) {
                this.orderInfo = orderInfo;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class OrderInfoBean {
                /**
                 * DspStat : 未发
                 * Durat : 1天
                 * EncryptLevel :
                 * ID : 47||62
                 * PatLevel :
                 * PreDisDateTime :
                 * admLoc : 内分泌科
                 * age : 67岁
                 * arcimDesc : 10%葡萄糖注射液(塑瓶)[100ML]
                 * bedCode : 04
                 * cancelReason :
                 * createDateTime : 2018-08-24 10:50:56
                 * ctcpDesc : 医生01
                 * disposeStatCode : NeedToDeal
                 * doseQtyUnit : 100 ml
                 * execXDateTime :
                 * execXUserDesc :
                 * flowSpeed :
                 * labNo :
                 * medCareNo : 100023
                 * no : 1
                 * notes :
                 * notifyClinician : N
                 * oecprDesc : 长期医嘱
                 * orcatDesc : 西药
                 * ordDep : 内分泌科
                 * ordID : 47||62
                 * ordStatDesc : 核实
                 * patIdentity : 全自费
                 * patName : zfm03
                 * phOrdQtyUnit : 200 ml
                 * phcfrCode : Bid
                 * phcinDesc : 静脉滴注
                 * placerNo :
                 * prescNo : I180824000001
                 * price : 1.20
                 * printFlag :
                 * reclocDesc : 静脉药物配置中心
                 * regNo : 0000000070
                 * seeOrderUserDateTime :
                 * seeOrderUserName :
                 * seqNo : 47||62
                 * skinTestInfo :
                 * skinTestNumber :
                 * specCollDateTime :
                 * specDesc :
                 * sttDateTime : 2018-08-24 10:51:08
                 * totalAmount : 2.40
                 * tubeColor :
                 * tubeColorDesc : {}
                 * xCtcpDesc :
                 * xDateTime :
                 */

                private String DspStat;
                private String Durat;
                private String EncryptLevel;
                private String ID;
                private String PatLevel;
                private String PreDisDateTime;
                private String admLoc;
                private String age;
                private String arcimDesc;
                private String bedCode;
                private String cancelReason;
                private String createDateTime;
                private String ctcpDesc;
                private String disposeStatCode;
                private String doseQtyUnit;
                private String execXDateTime;
                private String execXUserDesc;
                private String flowSpeed;
                private String labNo;
                private String medCareNo;
                private String no;
                private String notes;
                private String notifyClinician;
                private String oecprDesc;
                private String orcatDesc;
                private String ordDep;
                private String ordID;
                private String ordStatDesc;
                private String patIdentity;
                private String patName;
                private String phOrdQtyUnit;
                private String phcfrCode;
                private String phcinDesc;
                private String placerNo;
                private String prescNo;
                private String price;
                private String printFlag;
                private String reclocDesc;
                private String regNo;
                private String seeOrderUserDateTime;
                private String seeOrderUserName;
                private String seqNo;
                private String skinTestInfo;
                private String skinTestNumber;
                private String specCollDateTime;
                private String specDesc;
                private String sttDateTime;
                private String totalAmount;
                private String tubeColor;
                private TubeColorDescBean tubeColorDesc;
                private String xCtcpDesc;
                private String xDateTime;

                public String getDspStat() {
                    return DspStat;
                }

                public void setDspStat(String DspStat) {
                    this.DspStat = DspStat;
                }

                public String getDurat() {
                    return Durat;
                }

                public void setDurat(String Durat) {
                    this.Durat = Durat;
                }

                public String getEncryptLevel() {
                    return EncryptLevel;
                }

                public void setEncryptLevel(String EncryptLevel) {
                    this.EncryptLevel = EncryptLevel;
                }

                public String getID() {
                    return ID;
                }

                public void setID(String ID) {
                    this.ID = ID;
                }

                public String getPatLevel() {
                    return PatLevel;
                }

                public void setPatLevel(String PatLevel) {
                    this.PatLevel = PatLevel;
                }

                public String getPreDisDateTime() {
                    return PreDisDateTime;
                }

                public void setPreDisDateTime(String PreDisDateTime) {
                    this.PreDisDateTime = PreDisDateTime;
                }

                public String getAdmLoc() {
                    return admLoc;
                }

                public void setAdmLoc(String admLoc) {
                    this.admLoc = admLoc;
                }

                public String getAge() {
                    return age;
                }

                public void setAge(String age) {
                    this.age = age;
                }

                public String getArcimDesc() {
                    return arcimDesc;
                }

                public void setArcimDesc(String arcimDesc) {
                    this.arcimDesc = arcimDesc;
                }

                public String getBedCode() {
                    return bedCode;
                }

                public void setBedCode(String bedCode) {
                    this.bedCode = bedCode;
                }

                public String getCancelReason() {
                    return cancelReason;
                }

                public void setCancelReason(String cancelReason) {
                    this.cancelReason = cancelReason;
                }

                public String getCreateDateTime() {
                    return createDateTime;
                }

                public void setCreateDateTime(String createDateTime) {
                    this.createDateTime = createDateTime;
                }

                public String getCtcpDesc() {
                    return ctcpDesc;
                }

                public void setCtcpDesc(String ctcpDesc) {
                    this.ctcpDesc = ctcpDesc;
                }

                public String getDisposeStatCode() {
                    return disposeStatCode;
                }

                public void setDisposeStatCode(String disposeStatCode) {
                    this.disposeStatCode = disposeStatCode;
                }

                public String getDoseQtyUnit() {
                    return doseQtyUnit;
                }

                public void setDoseQtyUnit(String doseQtyUnit) {
                    this.doseQtyUnit = doseQtyUnit;
                }

                public String getExecXDateTime() {
                    return execXDateTime;
                }

                public void setExecXDateTime(String execXDateTime) {
                    this.execXDateTime = execXDateTime;
                }

                public String getExecXUserDesc() {
                    return execXUserDesc;
                }

                public void setExecXUserDesc(String execXUserDesc) {
                    this.execXUserDesc = execXUserDesc;
                }

                public String getFlowSpeed() {
                    return flowSpeed;
                }

                public void setFlowSpeed(String flowSpeed) {
                    this.flowSpeed = flowSpeed;
                }

                public String getLabNo() {
                    return labNo;
                }

                public void setLabNo(String labNo) {
                    this.labNo = labNo;
                }

                public String getMedCareNo() {
                    return medCareNo;
                }

                public void setMedCareNo(String medCareNo) {
                    this.medCareNo = medCareNo;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getNotes() {
                    return notes;
                }

                public void setNotes(String notes) {
                    this.notes = notes;
                }

                public String getNotifyClinician() {
                    return notifyClinician;
                }

                public void setNotifyClinician(String notifyClinician) {
                    this.notifyClinician = notifyClinician;
                }

                public String getOecprDesc() {
                    return oecprDesc;
                }

                public void setOecprDesc(String oecprDesc) {
                    this.oecprDesc = oecprDesc;
                }

                public String getOrcatDesc() {
                    return orcatDesc;
                }

                public void setOrcatDesc(String orcatDesc) {
                    this.orcatDesc = orcatDesc;
                }

                public String getOrdDep() {
                    return ordDep;
                }

                public void setOrdDep(String ordDep) {
                    this.ordDep = ordDep;
                }

                public String getOrdID() {
                    return ordID;
                }

                public void setOrdID(String ordID) {
                    this.ordID = ordID;
                }

                public String getOrdStatDesc() {
                    return ordStatDesc;
                }

                public void setOrdStatDesc(String ordStatDesc) {
                    this.ordStatDesc = ordStatDesc;
                }

                public String getPatIdentity() {
                    return patIdentity;
                }

                public void setPatIdentity(String patIdentity) {
                    this.patIdentity = patIdentity;
                }

                public String getPatName() {
                    return patName;
                }

                public void setPatName(String patName) {
                    this.patName = patName;
                }

                public String getPhOrdQtyUnit() {
                    return phOrdQtyUnit;
                }

                public void setPhOrdQtyUnit(String phOrdQtyUnit) {
                    this.phOrdQtyUnit = phOrdQtyUnit;
                }

                public String getPhcfrCode() {
                    return phcfrCode;
                }

                public void setPhcfrCode(String phcfrCode) {
                    this.phcfrCode = phcfrCode;
                }

                public String getPhcinDesc() {
                    return phcinDesc;
                }

                public void setPhcinDesc(String phcinDesc) {
                    this.phcinDesc = phcinDesc;
                }

                public String getPlacerNo() {
                    return placerNo;
                }

                public void setPlacerNo(String placerNo) {
                    this.placerNo = placerNo;
                }

                public String getPrescNo() {
                    return prescNo;
                }

                public void setPrescNo(String prescNo) {
                    this.prescNo = prescNo;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getPrintFlag() {
                    return printFlag;
                }

                public void setPrintFlag(String printFlag) {
                    this.printFlag = printFlag;
                }

                public String getReclocDesc() {
                    return reclocDesc;
                }

                public void setReclocDesc(String reclocDesc) {
                    this.reclocDesc = reclocDesc;
                }

                public String getRegNo() {
                    return regNo;
                }

                public void setRegNo(String regNo) {
                    this.regNo = regNo;
                }

                public String getSeeOrderUserDateTime() {
                    return seeOrderUserDateTime;
                }

                public void setSeeOrderUserDateTime(String seeOrderUserDateTime) {
                    this.seeOrderUserDateTime = seeOrderUserDateTime;
                }

                public String getSeeOrderUserName() {
                    return seeOrderUserName;
                }

                public void setSeeOrderUserName(String seeOrderUserName) {
                    this.seeOrderUserName = seeOrderUserName;
                }

                public String getSeqNo() {
                    return seqNo;
                }

                public void setSeqNo(String seqNo) {
                    this.seqNo = seqNo;
                }

                public String getSkinTestInfo() {
                    return skinTestInfo;
                }

                public void setSkinTestInfo(String skinTestInfo) {
                    this.skinTestInfo = skinTestInfo;
                }

                public String getSkinTestNumber() {
                    return skinTestNumber;
                }

                public void setSkinTestNumber(String skinTestNumber) {
                    this.skinTestNumber = skinTestNumber;
                }

                public String getSpecCollDateTime() {
                    return specCollDateTime;
                }

                public void setSpecCollDateTime(String specCollDateTime) {
                    this.specCollDateTime = specCollDateTime;
                }

                public String getSpecDesc() {
                    return specDesc;
                }

                public void setSpecDesc(String specDesc) {
                    this.specDesc = specDesc;
                }

                public String getSttDateTime() {
                    return sttDateTime;
                }

                public void setSttDateTime(String sttDateTime) {
                    this.sttDateTime = sttDateTime;
                }

                public String getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(String totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public String getTubeColor() {
                    return tubeColor;
                }

                public void setTubeColor(String tubeColor) {
                    this.tubeColor = tubeColor;
                }

                public TubeColorDescBean getTubeColorDesc() {
                    return tubeColorDesc;
                }

                public void setTubeColorDesc(TubeColorDescBean tubeColorDesc) {
                    this.tubeColorDesc = tubeColorDesc;
                }

                public String getXCtcpDesc() {
                    return xCtcpDesc;
                }

                public void setXCtcpDesc(String xCtcpDesc) {
                    this.xCtcpDesc = xCtcpDesc;
                }

                public String getXDateTime() {
                    return xDateTime;
                }

                public void setXDateTime(String xDateTime) {
                    this.xDateTime = xDateTime;
                }

                public static class TubeColorDescBean {
                }
            }
        }
    }

    public static class SheetListBean {
        /**
         * code : DefaultSee
         * desc : 需处理医嘱
         */

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
