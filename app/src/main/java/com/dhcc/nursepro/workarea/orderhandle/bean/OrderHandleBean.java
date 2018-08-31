package com.dhcc.nursepro.workarea.orderhandle.bean;

import java.util.List;

public class OrderHandleBean{
    /**
     * buttons : [{"code":"excuteOrder","desc":"执行医嘱"},{"code":"cancelExcuteOrder","desc":"撤销执行"}]
     * detailColums : [{"code":"bedCode","name":"床位号"},{"code":"regNo","name":"登记号"},{"code":"patName","name":"病人姓名"},{"code":"arcimDesc","name":"医嘱名称"},{"code":"doseQtyUnit","name":"剂量"},{"code":"phcinDesc","name":"用药途径"},{"code":"phcfrCode","name":"频率"},{"code":"Durat","name":"疗程"},{"code":"phOrdQtyUnit","name":"总量"},{"code":"ctcpDesc","name":"医生"},{"code":"notes","name":"备注"},{"code":"labNo","name":"标本号"},{"code":"reclocDesc","name":"接收科室"},{"code":"ordStatDesc","name":"医嘱状态"},{"code":"orcatDesc","name":"医嘱大类"},{"code":"seeOrderUserName","name":"处理医嘱人"},{"code":"seeOrderUserDateTime","name":"处理时间"},{"code":"ordDep","name":"开医嘱科室"},{"code":"createDateTime","name":"开医嘱时间"},{"code":"sttDateTime","name":"要求执行时间"},{"code":"xCtcpDesc","name":"停医嘱人"},{"code":"xDateTime","name":"停医嘱时间"},{"code":"execXUserDesc","name":"处理停医嘱人"},{"code":"execXDateTime","name":"处理停医嘱时间"},{"code":"PreDisDateTime","name":"预停时间"}]
     * msg :
     * msgcode : 999999
     * orders : [{"bedCode":"02","name":"王伟测试","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||55||17","PatLevel":"","PreDisDateTime":"04/05/2018 19:54","admLoc":"内分泌科","age":"30岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"02","cancelReason":"","createDateTime":"2018-03-28 15:42:22","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"100 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||55||17","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"5","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||55","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"100 ml","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180328000026","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||55","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"04-13","sttDateTime":"2018-03-28 15:42:22","sttTime":"23:45","totalAmount":"1.20","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":"2018-05-04 19:54:44"},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||133||1","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"10%葡萄糖注射液(袋装)[500ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-04-13 19:56:01","ctcpDesc":"医生01","disposeStatCode":"LongNew","doseQtyUnit":"500 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||133||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||133","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180413000003","price":"7.50","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||133","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"04-13","sttDateTime":"2018-04-13 19:56:19","sttTime":"23:45","totalAmount":"7.50","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:59"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||134||1","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"维生素C注射液[1g:5ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-04-13 19:56:01","ctcpDesc":"医生01","doseQtyUnit":"1 g","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||134||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100056","no":"1.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||134","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 g","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180413000003","price":"0.58","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||133","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"04-13","sttDateTime":"2018-04-13 19:56:19","sttTime":"23:45","totalAmount":"0.58","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:59"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||133||2","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"10%葡萄糖注射液(袋装)[500ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-04-13 19:56:01","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"500 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||133||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||133","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180413000003","price":"7.50","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||133","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"04-14","sttDateTime":"2018-04-13 19:56:19","sttTime":"23:45","totalAmount":"7.50","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:59"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||134||2","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"维生素C注射液[1g:5ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-04-13 19:56:01","ctcpDesc":"医生01","doseQtyUnit":"1 g","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||134||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100056","no":"1.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||134","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 g","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180413000003","price":"0.58","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||133","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"04-14","sttDateTime":"2018-04-13 19:56:19","sttTime":"23:45","totalAmount":"0.58","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:59"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||148||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-05-04 18:16:48","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||148||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||148","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180504000023","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-07 10:41:00","seeOrderUserName":"护士01","seqNo":"91||148","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"05-05","sttDateTime":"2018-05-04 18:16:57","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:23"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||149||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-05-04 18:16:48","ctcpDesc":"医生01","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||149||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||149","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180504000023","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-07 10:41:00","seeOrderUserName":"护士01","seqNo":"91||148","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"05-05","sttDateTime":"2018-05-04 18:16:57","sttTime":"08:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:23"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||148||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-05-04 18:16:48","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||148||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||148","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180504000023","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-07 10:41:00","seeOrderUserName":"护士01","seqNo":"91||148","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"05-05","sttDateTime":"2018-05-04 18:16:57","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:23"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||149||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-05-04 18:16:48","ctcpDesc":"医生01","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||149||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||149","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180504000023","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-07 10:41:00","seeOrderUserName":"护士01","seqNo":"91||148","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"05-05","sttDateTime":"2018-05-04 18:16:57","sttTime":"16:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:23"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||154||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:28:32","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||154||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||154","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000008","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||154","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:28:38","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:34:06"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||155||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:28:32","ctcpDesc":"医生01","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||155||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||155","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000008","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||154","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:28:38","sttTime":"08:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:34:06"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||158||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||158||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||158","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||154||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:28:32","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||154||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||154","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000008","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||154","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:28:38","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:34:06"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||155||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:28:32","ctcpDesc":"医生01","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||155||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||155","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000008","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||154","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:28:38","sttTime":"16:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:34:06"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||158||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||158||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||158","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:33:38","sttTime":"16:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:33:38","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||158||3","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||158||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||158","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||3","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||158||4","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||158||4","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||158","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"16:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||4","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||4","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||165||1","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-09 09:49:22","ctcpDesc":"医生01","disposeStatCode":"LongNew","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||165||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||165","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180809000001","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||165","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-09","sttDateTime":"2018-08-09 09:49:27","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||165||2","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-09 09:49:22","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||165||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||165","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180809000001","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||165","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2018-08-09 09:49:27","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||165||3","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-09 09:49:22","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||165||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||165","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180809000001","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||165","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2018-08-09 09:49:27","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}]]}]
     * sheetList : [{"code":"DefaultSee","desc":"需处理医嘱"},{"code":"WZX","desc":"未执行"},{"code":"CQSYD","desc":"输液单"},{"code":"cqkfyd","desc":"口服药单"},{"code":"cqzsd","desc":"注射单"},{"code":"HLZLD","desc":"处置治疗单"},{"code":"YSHLD","desc":"饮食护理单"},{"code":"PSD","desc":"皮试单"},{"code":"JYD","desc":"检验单"},{"code":"BLD","desc":"病理单"},{"code":"SXD","desc":"输血单"},{"code":"SQZBD","desc":"术前准备单"},{"code":"CUDY","desc":"出院带药单"},{"code":"Default","desc":"全部医嘱"}]
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

    public static class ButtonsBean {
        /**
         * code : excuteOrder
         * desc : 执行医嘱
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

    public static class DetailColumsBean {
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

    public static class OrdersBean {
        /**
         * bedCode : 02
         * name : 王伟测试
         * patOrds : [[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||55||17","PatLevel":"","PreDisDateTime":"04/05/2018 19:54","admLoc":"内分泌科","age":"30岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"02","cancelReason":"","createDateTime":"2018-03-28 15:42:22","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"100 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||55||17","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"5","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||55","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"100 ml","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180328000026","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||55","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"04-13","sttDateTime":"2018-03-28 15:42:22","sttTime":"23:45","totalAmount":"1.20","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":"2018-05-04 19:54:44"},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||133||1","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"10%葡萄糖注射液(袋装)[500ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-04-13 19:56:01","ctcpDesc":"医生01","disposeStatCode":"LongNew","doseQtyUnit":"500 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||133||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||133","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180413000003","price":"7.50","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||133","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"04-13","sttDateTime":"2018-04-13 19:56:19","sttTime":"23:45","totalAmount":"7.50","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:59"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||134||1","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"维生素C注射液[1g:5ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-04-13 19:56:01","ctcpDesc":"医生01","doseQtyUnit":"1 g","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||134||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100056","no":"1.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||134","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 g","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180413000003","price":"0.58","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||133","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"04-13","sttDateTime":"2018-04-13 19:56:19","sttTime":"23:45","totalAmount":"0.58","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:59"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||133||2","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"10%葡萄糖注射液(袋装)[500ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-04-13 19:56:01","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"500 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||133||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||133","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180413000003","price":"7.50","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||133","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"04-14","sttDateTime":"2018-04-13 19:56:19","sttTime":"23:45","totalAmount":"7.50","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:59"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||134||2","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"维生素C注射液[1g:5ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-04-13 19:56:01","ctcpDesc":"医生01","doseQtyUnit":"1 g","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||134||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100056","no":"1.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||134","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"1 g","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180413000003","price":"0.58","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||133","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"04-14","sttDateTime":"2018-04-13 19:56:19","sttTime":"23:45","totalAmount":"0.58","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:59"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||148||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-05-04 18:16:48","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||148||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||148","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180504000023","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-07 10:41:00","seeOrderUserName":"护士01","seqNo":"91||148","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"05-05","sttDateTime":"2018-05-04 18:16:57","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:23"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||149||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-05-04 18:16:48","ctcpDesc":"医生01","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||149||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||149","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180504000023","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-07 10:41:00","seeOrderUserName":"护士01","seqNo":"91||148","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"05-05","sttDateTime":"2018-05-04 18:16:57","sttTime":"08:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:23"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||148||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"5%葡萄糖氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-05-04 18:16:48","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||148||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||148","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180504000023","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-07 10:41:00","seeOrderUserName":"护士01","seqNo":"91||148","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"05-05","sttDateTime":"2018-05-04 18:16:57","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:23"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||149||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-05-04 18:16:48","ctcpDesc":"医生01","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||149||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||149","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180504000023","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-07 10:41:00","seeOrderUserName":"护士01","seqNo":"91||148","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"05-05","sttDateTime":"2018-05-04 18:16:57","sttTime":"16:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:29:23"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||154||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:28:32","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||154||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||154","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000008","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||154","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:28:38","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:34:06"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||155||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:28:32","ctcpDesc":"医生01","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||155||1","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||155","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000008","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||154","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:28:38","sttTime":"08:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:34:06"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||158||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||158||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||158","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||1","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||154||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:28:32","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||154||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||154","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000008","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||154","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:28:38","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:34:06"},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||155||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:28:32","ctcpDesc":"医生01","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||155||2","execXDateTime":"2018-06-04 09:24:22","execXUserDesc":"护士01","flowSpeed":"","labNo":"","medCareNo":"100056","no":"2.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||155","ordStatDesc":"停止","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000008","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||154","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:28:38","sttTime":"16:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"医生01","xDateTime":"2018-06-01 16:34:06"},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||158||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||158||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||158","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:33:38","sttTime":"16:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||2","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-01","sttDateTime":"2018-06-01 16:33:38","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||158||3","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||158||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||158","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||3","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||158||4","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","disposeStatCode":"SkinTest","doseQtyUnit":"400 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||158||4","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||158","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"800 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"2.70","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"16:00","totalAmount":"5.40","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"},{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||159||4","PatLevel":"","PreDisDateTime":"","abnorm":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-06-01 16:29:52","ctcpDesc":"医生01","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||159||4","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"3.1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||159","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180601000011","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-06-01 16:30:00","seeOrderUserName":"护士01","seqNo":"91||158","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"06-02","sttDateTime":"2018-06-01 16:33:38","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"child"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||165||1","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-09 09:49:22","ctcpDesc":"医生01","disposeStatCode":"LongNew","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||165||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||165","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180809000001","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||165","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-09","sttDateTime":"2018-08-09 09:49:27","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||165||2","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-09 09:49:22","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||165||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||165","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180809000001","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||165","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2018-08-09 09:49:27","sttTime":"08:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||165||3","PatLevel":"","PreDisDateTime":"","admLoc":"内分泌科","age":"30岁","arcimDesc":"0.9%氯化钠注射液(塑瓶)[250ml]","bedCode":"02","cancelReason":"","createDateTime":"2018-08-09 09:49:22","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"250 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||165||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"1","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||165","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"500 ml","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180809000001","price":"1.30","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-08-14 10:03:00","seeOrderUserName":"innurse","seqNo":"91||165","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"08-10","sttDateTime":"2018-08-09 09:49:27","sttTime":"16:00","totalAmount":"2.60","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":""},"type":"main"}]]
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
             * orderInfo : {"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"91||55||17","PatLevel":"","PreDisDateTime":"04/05/2018 19:54","admLoc":"内分泌科","age":"30岁","arcimDesc":"10%葡萄糖注射液(塑瓶)[100ML]","bedCode":"02","cancelReason":"","createDateTime":"2018-03-28 15:42:22","ctcpDesc":"医生01","disposeStatCode":"LongUnnew","doseQtyUnit":"100 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"91||55||17","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100056","no":"5","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"91||55","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"王伟测试","phOrdQtyUnit":"100 ml","phcfrCode":"Qd","phcinDesc":"静脉滴注","placerNo":"","prescNo":"I180328000026","price":"1.20","printFlag":"","reclocDesc":"静脉药物配置中心","regNo":"0000000129 ","seeOrderUserDateTime":"2018-05-03 19:30:00","seeOrderUserName":"护士01","seqNo":"91||55","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"04-13","sttDateTime":"2018-03-28 15:42:22","sttTime":"23:45","totalAmount":"1.20","tubeColor":"","tubeColorDesc":{},"xCtcpDesc":"","xDateTime":"2018-05-04 19:54:44"}
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
                 * ID : 91||55||17
                 * PatLevel :
                 * PreDisDateTime : 04/05/2018 19:54
                 * admLoc : 内分泌科
                 * age : 30岁
                 * arcimDesc : 10%葡萄糖注射液(塑瓶)[100ML]
                 * bedCode : 02
                 * cancelReason :
                 * createDateTime : 2018-03-28 15:42:22
                 * ctcpDesc : 医生01
                 * disposeStatCode : LongUnnew
                 * doseQtyUnit : 100 ml
                 * examInfo : {}
                 * execCtcpDesc :
                 * execDateTime :
                 * execID : 91||55||17
                 * execXDateTime :
                 * execXUserDesc :
                 * flowSpeed :
                 * labNo :
                 * medCareNo : 100056
                 * no : 5
                 * notes :
                 * notifyClinician : N
                 * oecprDesc : 长期医嘱
                 * orcatDesc : 西药
                 * ordDep : 内分泌科
                 * ordID : 91||55
                 * ordStatDesc : 核实
                 * ordTyp : R
                 * patIdentity : 全自费
                 * patName : 王伟测试
                 * phOrdQtyUnit : 100 ml
                 * phcfrCode : Qd
                 * phcinDesc : 静脉滴注
                 * placerNo :
                 * prescNo : I180328000026
                 * price : 1.20
                 * printFlag :
                 * reclocDesc : 静脉药物配置中心
                 * regNo : 0000000129
                 * seeOrderUserDateTime : 2018-05-03 19:30:00
                 * seeOrderUserName : 护士01
                 * seqNo : 91||55
                 * skinTestInfo :
                 * skinTestNumber :
                 * specCollDateTime :
                 * specDesc :
                 * sttDate : 04-13
                 * sttDateTime : 2018-03-28 15:42:22
                 * sttTime : 23:45
                 * totalAmount : 1.20
                 * tubeColor :
                 * tubeColorDesc : {}
                 * xCtcpDesc :
                 * xDateTime : 2018-05-04 19:54:44
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
                private ExamInfoBean examInfo;
                private String execCtcpDesc;
                private String execDateTime;
                private String execID;
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
                private String ordTyp;
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
                private String sttDate;
                private String sttDateTime;
                private String sttTime;
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

                public ExamInfoBean getExamInfo() {
                    return examInfo;
                }

                public void setExamInfo(ExamInfoBean examInfo) {
                    this.examInfo = examInfo;
                }

                public String getExecCtcpDesc() {
                    return execCtcpDesc;
                }

                public void setExecCtcpDesc(String execCtcpDesc) {
                    this.execCtcpDesc = execCtcpDesc;
                }

                public String getExecDateTime() {
                    return execDateTime;
                }

                public void setExecDateTime(String execDateTime) {
                    this.execDateTime = execDateTime;
                }

                public String getExecID() {
                    return execID;
                }

                public void setExecID(String execID) {
                    this.execID = execID;
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

                public String getOrdTyp() {
                    return ordTyp;
                }

                public void setOrdTyp(String ordTyp) {
                    this.ordTyp = ordTyp;
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

                public String getSttDate() {
                    return sttDate;
                }

                public void setSttDate(String sttDate) {
                    this.sttDate = sttDate;
                }

                public String getSttDateTime() {
                    return sttDateTime;
                }

                public void setSttDateTime(String sttDateTime) {
                    this.sttDateTime = sttDateTime;
                }

                public String getSttTime() {
                    return sttTime;
                }

                public void setSttTime(String sttTime) {
                    this.sttTime = sttTime;
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

                public static class ExamInfoBean {
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
