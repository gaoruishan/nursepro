package com.dhcc.nursepro.workarea.ordersearch.bean;

import java.io.Serializable;
import java.util.List;

/**
 * OrderSearchBean
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderSearchBean implements Serializable {


    /**
     * buttons : [{"code":"seeOrder","desc":"处理医嘱"},{"code":"cancelSeeOrder","desc":"撤销处理医嘱"}]
     * detailColums : [{"code":"bedCode","name":"床位号"},{"code":"regNo","name":"登记号"},{"code":"patName","name":"病人姓名"},{"code":"arcimDesc","name":"医嘱名称"},{"code":"doseQtyUnit","name":"剂量"},{"code":"phcinDesc","name":"用药途径"},{"code":"phcfrCode","name":"频率"},{"code":"Durat","name":"疗程"},{"code":"phOrdQtyUnit","name":"总量"},{"code":"ctcpDesc","name":"医生"},{"code":"notes","name":"备注"},{"code":"labNo","name":"标本号"},{"code":"reclocDesc","name":"接收科室"},{"code":"ordStatDesc","name":"医嘱状态"},{"code":"orcatDesc","name":"医嘱大类"},{"code":"seeOrderUserName","name":"处理医嘱人"},{"code":"seeOrderUserDateTime","name":"处理时间"},{"code":"ordDep","name":"开医嘱科室"},{"code":"createDateTime","name":"开医嘱时间"},{"code":"sttDateTime","name":"要求执行时间"},{"code":"xCtcpDesc","name":"停医嘱人"},{"code":"xDateTime","name":"停医嘱时间"},{"code":"execXUserDesc","name":"处理停医嘱人"},{"code":"execXDateTime","name":"处理停医嘱时间"},{"code":"PreDisDateTime","name":"预停时间"}]
     * msg :
     * msgcode : 999999
     * orders : [{"bedCode":"02","name":"王伟测试","patOrds":[[{"orderInfo":{"DspStat":"","Durat":"1天","EncryptLevel":"","ID":"91||168","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"Ⅰ级护理","bedCode":"02","cancelReason":"","createDateTime":"2018-08-10 14:02:23","ctcpDesc":"医生01","disposeStatCode":"NeedToStop","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"91||168","ordStatDesc":"停止","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"7.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||168","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-10 14:02:38","totalAmount":"7.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-08-16 09:15:14"},"type":"main"}],[{"orderInfo":{"DspStat":"","Durat":"1天","EncryptLevel":"","ID":"91||170","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"Ⅰ级护理","bedCode":"02","cancelReason":"","createDateTime":"2018-08-16 09:15:02","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"91||170","ordStatDesc":"核实","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"7.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000129 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"91||170","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-16 09:15:14","totalAmount":"7.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||172","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[500ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-27 10:36:46","ctcpDesc":"医生01","disposeStatCode":"NeedToStop","doseQtyUnit":"500 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||172","ordStatDesc":"停止","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1000 ml","phcfrCode":"Q12h","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180827000001","price":"1.60","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"91||172","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-27 10:36:52","totalAmount":"3.20","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-08-27 10:56:01"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||172","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[500ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-27 10:36:46","ctcpDesc":"医生01","disposeStatCode":"NeedToStop","doseQtyUnit":"500 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||172","ordStatDesc":"停止","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1000 ml","phcfrCode":"Q12h","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180827000001","price":"1.60","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"91||172","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-27 10:36:52","totalAmount":"3.20","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-08-27 10:56:01"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||175","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"复方甘草片[CO*100]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-27 10:54:37","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"3 片","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||175","ordStatDesc":"核实","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"3 片","phcfrCode":"ONCE","phcinDesc":"口服","placerNo":"","prescNo":"I180827000003","price":"0.06","printFlag":"","reclocDesc":"住院药房","regNo":"0000000129 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"91||175","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-27 11:22:08","totalAmount":"0.18","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}]]},{"bedCode":"04","name":"zfm03","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"47||62","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"67岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"04","cancelReason":"","createDateTime":"2018-08-24 10:50:56","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"100 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"47||62","ordStatDesc":"核实","patIdentity":"全自费","patName":"zfm03","phOrdQtyUnit":"200 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180824000001","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000070 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"47||62","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-24 10:51:08","totalAmount":"2.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}]]},{"bedCode":"03","name":"马亭（演示勿动）","patOrds":[[{"orderInfo":{"DspStat":"","Durat":"1天","EncryptLevel":"","ID":"293||18","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"56岁","arcimDesc":"Ⅱ级护理","bedCode":"03","cancelReason":"","createDateTime":"2018-03-29 15:50:34","ctcpDesc":"医生01","disposeStatCode":"NeedToStop","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100109","no":"16","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"293||18","ordStatDesc":"停止","patIdentity":"全自费","patName":"马亭（演示勿动）","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"6.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000290 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"293||18","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-03-29 15:48:44","totalAmount":"6.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-03-29 16:53:57"},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"293||41","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"56岁","arcimDesc":"复方角菜酸酯栓(太宁)[CO*12]","bedCode":"03","cancelReason":"","createDateTime":"2018-03-31 09:57:42","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"2 枚","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100109","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"293||41","ordStatDesc":"核实","patIdentity":"全自费","patName":"马亭（演示勿动）","phOrdQtyUnit":"6 盒(12)","phcfrCode":"ONCE","phcinDesc":"外用","placerNo":"","prescNo":"I180331000005","price":"2.49","printFlag":"","reclocDesc":"住院药房","regNo":"0000000290 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"293||41","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-03-31 09:58:00","totalAmount":"14.94","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"","Durat":"","EncryptLevel":"","ID":"293||48","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"56岁","arcimDesc":"甲状腺及颈部淋巴结超声","bedCode":"03","cancelReason":"","createDateTime":"2018-04-02 10:44:29","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100109","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"检查","ordDep":"内分泌科","ordID":"293||48","ordStatDesc":"执行","patIdentity":"全自费","patName":"马亭（演示勿动）","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"242.00","printFlag":"","reclocDesc":"超声医学科","regNo":"0000000290 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"293||48","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-04-02 10:44:36","totalAmount":"242.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"","Durat":"","EncryptLevel":"","ID":"293||71","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"56岁","arcimDesc":"经颅多普勒超声(TCD)","bedCode":"03","cancelReason":"","createDateTime":"2018-04-09 09:28:58","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100109","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"检查","ordDep":"内分泌科","ordID":"293||71","ordStatDesc":"核实","patIdentity":"全自费","patName":"马亭（演示勿动）","phOrdQtyUnit":"1 次","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"202.00","printFlag":"","reclocDesc":"超声医学科","regNo":"0000000290 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"293||71","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-04-09 09:28:00","totalAmount":"202.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"","Durat":"","EncryptLevel":"","ID":"293||72","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"56岁","arcimDesc":"甲状腺及颈部淋巴结超声","bedCode":"03","cancelReason":"","createDateTime":"2018-04-09 09:29:27","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100109","no":"2","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"检查","ordDep":"内分泌科","ordID":"293||72","ordStatDesc":"执行","patIdentity":"全自费","patName":"马亭（演示勿动）","phOrdQtyUnit":"1 次","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"242.00","printFlag":"","reclocDesc":"超声医学科","regNo":"0000000290 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"293||72","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-04-09 09:29:00","totalAmount":"242.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"293||76","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"56岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"03","cancelReason":"","createDateTime":"2018-04-10 08:46:57","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"250 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100109","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"293||76","ordStatDesc":"核实","patIdentity":"全自费","patName":"马亭（演示勿动）","phOrdQtyUnit":"250 ml","phcfrCode":"BIW","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180410000001","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000290 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"293||76","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDateTime":"2018-04-10 08:47:00","totalAmount":"0.19","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"293||76","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"56岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"03","cancelReason":"","createDateTime":"2018-04-10 08:46:57","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"250 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100109","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"293||76","ordStatDesc":"核实","patIdentity":"全自费","patName":"马亭（演示勿动）","phOrdQtyUnit":"250 ml","phcfrCode":"BIW","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180410000001","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000290 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"293||76","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDateTime":"2018-04-10 08:47:00","totalAmount":"0.19","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"","Durat":"1天","EncryptLevel":"","ID":"293||84","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"56岁","arcimDesc":"经皮肝穿刺门静脉化疗术","bedCode":"03","cancelReason":"","createDateTime":"2018-04-10 14:51:40","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100109","no":"1001","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"处置治疗","ordDep":"内分泌科","ordID":"293||84","ordStatDesc":"核实","patIdentity":"全自费","patName":"马亭（演示勿动）","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"400.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000290 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"293||84","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-04-10 14:51:50","totalAmount":"400.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}]]},{"bedCode":"37","name":"tyu03","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"396||25","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"43岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"37","cancelReason":"","createDateTime":"2018-06-05 08:41:30","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"400 万IU","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100135","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"396||25","ordStatDesc":"核实","patIdentity":"全自费","patName":"tyu03","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180605000001","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000143 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"396||25","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-06-05 08:41:35","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"396||25","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"43岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"37","cancelReason":"","createDateTime":"2018-06-05 08:41:30","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"400 万IU","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100135","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"396||25","ordStatDesc":"核实","patIdentity":"全自费","patName":"tyu03","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180605000001","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000143 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"396||25","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-06-05 08:41:35","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}]]},{"bedCode":"43","name":"测试押金","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"640||2","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"29岁","arcimDesc":"臭氧取气阀","bedCode":"43","cancelReason":"","createDateTime":"2018-06-05 15:02:57","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100179","no":"2","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"材料","ordDep":"内分泌科","ordID":"640||2","ordStatDesc":"核实","patIdentity":"全自费","patName":"测试押金","phOrdQtyUnit":"1 ","phcfrCode":"ONCE","phcinDesc":"","placerNo":"","prescNo":"","price":"45.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000475 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"640||2","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-06-05 15:03:07","totalAmount":"45.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}]]}]
     * sheetList : [{"code":"DefaultSee","desc":"需处理医嘱"},{"code":"WZX","desc":"未执行"},{"code":"CQSYD","desc":"输液单"},{"code":"cqkfyd","desc":"口服药单"},{"code":"CQZSD","desc":"注射单"},{"code":"HLZLD","desc":"处置治疗单"},{"code":"YSHLD","desc":"饮食护理单"},{"code":"JCD*","desc":"检查单"},{"code":"JYD","desc":"检验单"},{"code":"BLD","desc":"病理单"},{"code":"PSD","desc":"皮试单"},{"code":"SXD","desc":"输血单"},{"code":"SQZBD","desc":"术前准备单"},{"code":"CUDY","desc":"出院带药单"},{"code":"Default","desc":"全部医嘱"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<ButtonsBean> buttons;
    private List<DetailColumsBean> detailColums;
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

    public List<DetailColumsBean> getDetailColums() {
        return detailColums;
    }

    public void setDetailColums(List<DetailColumsBean> detailColums) {
        this.detailColums = detailColums;
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

    public static class ButtonsBean implements Serializable {
        /**
         * code : seeOrder
         * desc : 处理医嘱
         */

        private String code;
        private String desc;
        private String singleFlag;

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
        public String getSingleFlag() {
            return singleFlag;
        }

        public void setSingleFlag(String singleFlag) {
            this.singleFlag = singleFlag;
        }
    }

    public static class DetailColumsBean implements Serializable {
        /**
         * code : bedCode
         * name : 床位号
         */

        private String code;
        private String name;

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
    }

    public static class OrdersBean implements Serializable {
        /**
         * bedCode : 02
         * name : 王伟测试
         * patOrds : [[{"orderInfo":{"DspStat":"","Durat":"1天","EncryptLevel":"","ID":"91||168","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"Ⅰ级护理","bedCode":"02","cancelReason":"","createDateTime":"2018-08-10 14:02:23","ctcpDesc":"医生01","disposeStatCode":"NeedToStop","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"91||168","ordStatDesc":"停止","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"7.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||168","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-10 14:02:38","totalAmount":"7.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-08-16 09:15:14"},"type":"main"}],[{"orderInfo":{"DspStat":"","Durat":"1天","EncryptLevel":"","ID":"91||170","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"Ⅰ级护理","bedCode":"02","cancelReason":"","createDateTime":"2018-08-16 09:15:02","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"91||170","ordStatDesc":"核实","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"7.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000129 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"91||170","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-16 09:15:14","totalAmount":"7.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||172","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[500ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-27 10:36:46","ctcpDesc":"医生01","disposeStatCode":"NeedToStop","doseQtyUnit":"500 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||172","ordStatDesc":"停止","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1000 ml","phcfrCode":"Q12h","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180827000001","price":"1.60","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"91||172","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-27 10:36:52","totalAmount":"3.20","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-08-27 10:56:01"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||172","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[500ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-27 10:36:46","ctcpDesc":"医生01","disposeStatCode":"NeedToStop","doseQtyUnit":"500 ml","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||172","ordStatDesc":"停止","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1000 ml","phcfrCode":"Q12h","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180827000001","price":"1.60","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"91||172","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-27 10:36:52","totalAmount":"3.20","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-08-27 10:56:01"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||175","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"复方甘草片[CO*100]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-27 10:54:37","ctcpDesc":"医生01","disposeStatCode":"NeedToDeal","doseQtyUnit":"3 片","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||175","ordStatDesc":"核实","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"3 片","phcfrCode":"ONCE","phcinDesc":"口服","placerNo":"","prescNo":"I180827000003","price":"0.06","printFlag":"","reclocDesc":"住院药房","regNo":"0000000129 ","seeOrderUserDateTime":"","seeOrderUserName":"","seqNo":"91||175","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-27 11:22:08","totalAmount":"0.18","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}]]
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

        public static class PatOrdsBean implements Serializable {
            /**
             * orderInfo : {"DspStat":"","Durat":"1天","EncryptLevel":"","ID":"91||168","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"Ⅰ级护理","bedCode":"02","cancelReason":"","createDateTime":"2018-08-10 14:02:23","ctcpDesc":"医生01","disposeStatCode":"NeedToStop","doseQtyUnit":"","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"护理","ordDep":"内分泌科","ordID":"91||168","ordStatDesc":"停止","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 ","phcfrCode":"","phcinDesc":"","placerNo":"","prescNo":"","price":"7.00","printFlag":"","reclocDesc":"内分泌科护理单元","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||168","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDateTime":"2018-08-10 14:02:38","totalAmount":"7.00","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-08-16 09:15:14"}
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

            public static class OrderInfoBean implements Serializable {
                /**
                 * DspStat :
                 * Durat : 1天
                 * EncryptLevel :
                 * ID : 91||168
                 * PatLevel :
                 * PreDisDateTime :
                 * admLoc : 内分泌科
                 * age : 30岁
                 * arcimDesc : Ⅰ级护理
                 * bedCode : 02
                 * cancelReason :
                 * createDateTime : 2018-08-10 14:02:23
                 * ctcpDesc : 医生01
                 * disposeStatCode : NeedToStop
                 * doseQtyUnit :
                 * execXDateTime :
                 * execXUserDesc :
                 * flowSpeed :
                 * labNo :
                 * medCareNo : 100056
                 * no : 1
                 * notes :
                 * notifyClinician : N
                 * oecprDesc : 长期医嘱
                 * orcatDesc : 护理
                 * ordDep : 内分泌科
                 * ordID : 91||168
                 * ordStatDesc : 停止
                 * patIdentity : 全自费
                 * patName : 王伟测试
                 * phOrdQtyUnit : 1
                 * phcfrCode :
                 * phcinDesc :
                 * placerNo :
                 * prescNo :
                 * price : 7.00
                 * printFlag :
                 * reclocDesc : 内分泌科护理单元
                 * regNo : 0000000129
                 * seeOrderUserDateTime : 2018-08-14 10:03:00
                 * seeOrderUserName : innurse
                 * seqNo : 91||168
                 * skinTestInfo :
                 * skinTestNumber :
                 * specCollDateTime :
                 * specDesc :
                 * sttDateTime : 2018-08-10 14:02:38
                 * totalAmount : 7.00
                 * tubeColor :
                 * tubeColorDesc : {}
                 * xCtcpDesc : 医生01
                 * xDateTime : 2018-08-16 09:15:14
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

            }
        }
    }

    public static class SheetListBean implements Serializable {
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
