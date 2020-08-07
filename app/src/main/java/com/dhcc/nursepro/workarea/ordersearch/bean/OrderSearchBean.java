package com.dhcc.nursepro.workarea.ordersearch.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderSearchBean
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderSearchBean implements Serializable {


    /**
     * buttons : [{"code":"excuteOrder","desc":"执行医嘱","singleFlag":"Y"},{"code":"cancelExcuteOrder","desc":"撤销执行","singleFlag":""},{"code":"setSkinTest","desc":"皮试结果","singleFlag":"Y"}]
     * detailColums : [{"code":"bedCode","name":"床位号"},{"code":"regNo","name":"登记号"},{"code":"patName","name":"病人姓名"},{"code":"addOrdBtn","name":"补费用"},{"code":"createDateTime","name":"开医嘱时间"},{"code":"arcimDesc","name":"医嘱名称"},{"code":"doseQtyUnit","name":"剂量"},{"code":"phcinDesc","name":"用药途径"},{"code":"phcfrCode","name":"频率"},{"code":"Durat","name":"疗程"},{"code":"phOrdQtyUnit","name":"总量"},{"code":"ctcpDesc","name":"医生"},{"code":"notes","name":"备注"},{"code":"labNo","name":"标本号"},{"code":"reclocDesc","name":"接收科室"},{"code":"ordStatDesc","name":"医嘱状态"},{"code":"orcatDesc","name":"医嘱大类"},{"code":"seeOrderUserName","name":"处理医嘱人"},{"code":"seeOrderUserDateTime","name":"处理时间"},{"code":"ordDep","name":"开医嘱科室"},{"code":"sttDateTime","name":"要求执行时间"},{"code":"xCtcpDesc","name":"停医嘱人"},{"code":"xDateTime","name":"停医嘱时间"},{"code":"execXUserDesc","name":"处理停医嘱人"},{"code":"execXDateTime","name":"处理停医嘱时间"},{"code":"EncryptLevel","name":"病人密级"},{"code":"PatLevel","name":"病人级别"},{"code":"ordID","name":"医嘱ID"},{"code":"patIdentity","name":"病人身份"},{"code":"age","name":"年龄"},{"code":"no","name":"序号"},{"code":"prescNo","name":"处方号"},{"code":"price","name":"单价"},{"code":"totalAmount","name":"总价"},{"code":"placerNo","name":"条码号"},{"code":"DspStat","name":"发药状态"},{"code":"PreDisDateTime","name":"预停时间"},{"code":"specCollDateTime","name":"采血时间"},{"code":"specDesc","name":"标本名称"},{"code":"medCareNo","name":"住院号"},{"code":"flowSpeed","name":"滴速"},{"code":"admLoc","name":"就诊科室"},{"code":"cancelReason","name":"撤销原因"},{"code":"skinTestNumber","name":"皮试批号"},{"code":"seqNo","name":"关联号"},{"code":"notifyClinician","name":"加急标记"},{"code":"skinTestInfo","name":"皮试信息"},{"code":"tubeColor","name":"采血管颜色代码"},{"code":"tubeColorDesc","name":"采血管颜色"}]
     * msg :
     * msgcode : 999999
     * orders : [{"bedCode":"08","name":"住院","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"41||86||1","PatLevel":"","PreDisDateTime":"","abnorm":"Y","addOrdBtn":"补费用","admLoc":"内分泌科","age":"26岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"08","cancelReason":"","createDateTime":"2019-07-17 16:24:57","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"41||86||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100012","no":"3","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"41||86","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"住院","phOrdQtyUnit":"800 万IU","phcfrCode":"St","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190717000008","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000048 ","seeOrderUserDateTime":"2019-07-17 16:28:00","seeOrderUserName":"护士01","seqNo":"41||86","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"07-17","sttDateTime":"2019-07-17 16:25","sttTime":"16:25","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]},{"bedCode":"20","name":"住院5","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"79||30||1","PatLevel":"","PreDisDateTime":"","abnorm":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"20","cancelReason":"","createDateTime":"2019-02-14 17:54:01","ctcpDesc":"医生01","disposeStatCode":"皮试^#8df38d","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"79||30||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"4","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"79||30","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"住院5","phOrdQtyUnit":"800 万IU","phcfrCode":"ONCE","phcinDesc":"皮试","placerNo":"","preparedFlag":"","prescNo":"I190214000054","price":"2.70","printFlag":"W","reclocDesc":"住院药房","regNo":"0000000060 ","seeOrderUserDateTime":"2019-02-14 17:56:00","seeOrderUserName":"护士01","seqNo":"79||30","skinColor":"8E8E8E","skinResult":"皮","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"02-14","sttDateTime":"2019-02-14 17:54","sttTime":"17:54","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"79||33||1","PatLevel":"","PreDisDateTime":"","abnorm":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"氯化钾注射液[1g:10ml]","bedCode":"20","cancelReason":"","createDateTime":"2019-02-14 17:54:57","ctcpDesc":"医生01","disposeStatCode":"皮试^#8df38d","doseQtyUnit":"1 g","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"79||33||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"7","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"79||33","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"住院5","phOrdQtyUnit":"1 g","phcfrCode":"ONCE","phcinDesc":"皮试","placerNo":"","preparedFlag":"","prescNo":"I190214000055","price":"0.98","printFlag":"W","reclocDesc":"住院药房","regNo":"0000000060 ","seeOrderUserDateTime":"2019-02-14 17:56:00","seeOrderUserName":"护士01","seqNo":"79||33","skinColor":"8E8E8E","skinResult":"皮","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"02-14","sttDateTime":"2019-02-14 17:54","sttTime":"17:54","totalAmount":"0.98","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"79||31||1","PatLevel":"","PreDisDateTime":"","abnorm":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"盐酸利多卡因注射液[0.4g:20ml]","bedCode":"20","cancelReason":"","createDateTime":"2019-02-14 17:54:24","ctcpDesc":"医生01","disposeStatCode":"皮试^#8df38d","doseQtyUnit":"0.4 g","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"79||31||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"5","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"79||31","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"住院5","phOrdQtyUnit":"0.4 g","phcfrCode":"St","phcinDesc":"皮试","placerNo":"","preparedFlag":"","prescNo":"I190214000055","price":"1.04","printFlag":"W","reclocDesc":"住院药房","regNo":"0000000060 ","seeOrderUserDateTime":"2019-02-14 17:56:00","seeOrderUserName":"护士01","seqNo":"79||31","skinColor":"8E8E8E","skinResult":"皮","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"02-14","sttDateTime":"2019-02-14 17:55","sttTime":"17:55","totalAmount":"1.04","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"79||73||1","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"22岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"20","cancelReason":"","createDateTime":"2019-07-22 15:36:16","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"79||73||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100023","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"79||73","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"住院5","phOrdQtyUnit":"800 万IU","phcfrCode":"St","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190722000001","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000060 ","seeOrderUserDateTime":"2019-07-22 15:37:00","seeOrderUserName":"护士01","seqNo":"79||73","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"07-22","sttDateTime":"2019-07-22 15:36","sttTime":"15:36","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]},{"bedCode":"26","name":"myh测试1302","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"223||22||1","PatLevel":"","PreDisDateTime":"","abnorm":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"25岁","arcimDesc":"注射用青霉素钠[80万U]","bedCode":"26","cancelReason":"","createDateTime":"2019-02-14 17:56:04","ctcpDesc":"医生01","disposeStatCode":"皮试^#8df38d","doseQtyUnit":"80 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"223||22||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100069","no":"8","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"223||22","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"myh测试1302","phOrdQtyUnit":"80 万IU","phcfrCode":"ONCE","phcinDesc":"皮试","placerNo":"","preparedFlag":"","prescNo":"I190214000057","price":"0.50","printFlag":"W","reclocDesc":"住院药房","regNo":"0000000162 ","seeOrderUserDateTime":"2019-02-14 17:56:00","seeOrderUserName":"护士01","seqNo":"223||22","skinColor":"8E8E8E","skinResult":"皮","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"02-14","sttDateTime":"2019-02-14 17:56","sttTime":"17:56","totalAmount":"0.50","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"223||19||1","PatLevel":"","PreDisDateTime":"","abnorm":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"25岁","arcimDesc":"盐酸普鲁卡因注射液[2%*2ML]","bedCode":"26","cancelReason":"","createDateTime":"2019-02-14 17:55:48","ctcpDesc":"医生01","disposeStatCode":"皮试^#8df38d","doseQtyUnit":"2 ml","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"223||19||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100069","no":"5","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"223||19","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"myh测试1302","phOrdQtyUnit":"2 ml","phcfrCode":"St","phcinDesc":"皮试","placerNo":"","preparedFlag":"","prescNo":"I190214000057","price":"0.35","printFlag":"W","reclocDesc":"住院药房","regNo":"0000000162 ","seeOrderUserDateTime":"2019-02-14 17:56:00","seeOrderUserName":"护士01","seqNo":"223||19","skinColor":"8E8E8E","skinResult":"皮","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"02-14","sttDateTime":"2019-02-14 17:56","sttTime":"17:56","totalAmount":"0.35","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"223||21||1","PatLevel":"","PreDisDateTime":"","abnorm":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"25岁","arcimDesc":"注射用盐酸氨溴索[30MG]","bedCode":"26","cancelReason":"","createDateTime":"2019-02-14 17:56:00","ctcpDesc":"医生01","disposeStatCode":"皮试^#8df38d","doseQtyUnit":"30 mg","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"223||21||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100069","no":"7","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"223||21","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"myh测试1302","phOrdQtyUnit":"30 mg","phcfrCode":"St","phcinDesc":"皮试","placerNo":"","preparedFlag":"","prescNo":"I190214000057","price":"14.50","printFlag":"W","reclocDesc":"住院药房","regNo":"0000000162 ","seeOrderUserDateTime":"2019-02-14 17:56:00","seeOrderUserName":"护士01","seqNo":"223||21","skinColor":"8E8E8E","skinResult":"皮","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"02-14","sttDateTime":"2019-02-14 17:56","sttTime":"17:56","totalAmount":"14.50","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]},{"bedCode":"27","name":"蚩尤","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"474||18||1","PatLevel":"","PreDisDateTime":"","abnorm":"","addOrdBtn":"补费用","admLoc":"消化内科","age":"118岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"27","cancelReason":"","createDateTime":"2019-07-16 10:49:36","ctcpDesc":"医生01","disposeStatCode":"皮试^#8df38d","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"474||18||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100123","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"消化内科","ordID":"474||18","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"蚩尤","phOrdQtyUnit":"800 万IU","phcfrCode":"St","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190716000005","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000254 ","seeOrderUserDateTime":"2019-08-17 09:24:00","seeOrderUserName":"护士01","seqNo":"474||18","skinColor":"8E8E8E","skinResult":"皮","skinTestInfo":" ( )","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"07-16","sttDateTime":"2019-07-16 10:49","sttTime":"10:49","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]},{"bedCode":"36","name":"皮试","patOrds":[[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||1||1","PatLevel":"","PreDisDateTime":"","abnorm":"","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:18:01","ctcpDesc":"医生01","disposeStatCode":"皮试^#8df38d","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"护士01","execDateTime":"02-21 17:29","execID":"498||1||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100136","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||1","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"800 万IU","phcfrCode":"St","phcinDesc":"皮试","placerNo":"","preparedFlag":"","prescNo":"I190221000035","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||1","skinColor":"8E8E8E","skinResult":"皮","skinTestInfo":"","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"02-21","sttDateTime":"2019-02-21 17:19","sttTime":"17:19","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||1","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"护士01","execDateTime":"02-21 17:29","execID":"498||2||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-22","sttDateTime":"2019-02-22 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||1","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-22","sttDateTime":"2019-02-22 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||1","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-22","sttDateTime":"2019-02-22 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||2","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"护士01","execDateTime":"02-21 17:29","execID":"498||2||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-22","sttDateTime":"2019-02-22 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||2","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-22","sttDateTime":"2019-02-22 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||2","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||2","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-22","sttDateTime":"2019-02-22 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||3","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-23","sttDateTime":"2019-02-23 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||3","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-23","sttDateTime":"2019-02-23 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||3","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||3","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-23","sttDateTime":"2019-02-23 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||4","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||4","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-23","sttDateTime":"2019-02-23 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||4","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||4","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-23","sttDateTime":"2019-02-23 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||4","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||4","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-23","sttDateTime":"2019-02-23 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||5","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||5","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-24","sttDateTime":"2019-02-24 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||5","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||5","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-24","sttDateTime":"2019-02-24 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||5","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||5","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-24","sttDateTime":"2019-02-24 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||6","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||6","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-24","sttDateTime":"2019-02-24 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||6","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||6","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-24","sttDateTime":"2019-02-24 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||6","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||6","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-24","sttDateTime":"2019-02-24 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||7","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||7","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-25","sttDateTime":"2019-02-25 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||7","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||7","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-25","sttDateTime":"2019-02-25 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||7","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||7","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-25","sttDateTime":"2019-02-25 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||8","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||8","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-25","sttDateTime":"2019-02-25 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||8","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||8","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-25","sttDateTime":"2019-02-25 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||8","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||8","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-25","sttDateTime":"2019-02-25 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||9","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||9","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-26","sttDateTime":"2019-02-26 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||9","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||9","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-26","sttDateTime":"2019-02-26 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||9","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||9","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-26","sttDateTime":"2019-02-26 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||10","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||10","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-26","sttDateTime":"2019-02-26 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||10","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||10","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-26","sttDateTime":"2019-02-26 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||10","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||10","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-26","sttDateTime":"2019-02-26 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||11","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||11","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-27","sttDateTime":"2019-02-27 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||11","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||11","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-27","sttDateTime":"2019-02-27 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||11","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||11","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-27","sttDateTime":"2019-02-27 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||12","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||12","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-27","sttDateTime":"2019-02-27 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||12","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||12","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-27","sttDateTime":"2019-02-27 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||12","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||12","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-27","sttDateTime":"2019-02-27 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||13","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||13","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-28","sttDateTime":"2019-02-28 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||13","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||13","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-28","sttDateTime":"2019-02-28 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||13","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||13","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-28","sttDateTime":"2019-02-28 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||14","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||14","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-28","sttDateTime":"2019-02-28 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||14","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||14","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-28","sttDateTime":"2019-02-28 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||14","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||14","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"02-28","sttDateTime":"2019-02-28 14:00","sttTime":"14:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||15","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||15","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"03-01","sttDateTime":"2019-03-01 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||15","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||15","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"03-01","sttDateTime":"2019-03-01 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||15","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||15","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"03-01","sttDateTime":"2019-03-01 08:00","sttTime":"08:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||2||16","PatLevel":"","PreDisDateTime":"","abnorm":"N","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:20:27","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||2||16","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"2","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||2","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||2","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"03-01","sttDateTime":"2019-03-01 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||3||16","PatLevel":"","PreDisDateTime":"","abnorm":"Y","actDesc":"皮","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:23:37","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||3||16","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"3","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||3","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||3","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"03-01","sttDateTime":"2019-03-01 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"498||4||16","PatLevel":"","PreDisDateTime":"","abnorm":"N","actDesc":"原","addOrdBtn":"补费用","admLoc":"内分泌科","age":"27岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"36","cancelReason":"","createDateTime":"2019-02-21 17:24:58","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"498||4||16","execXDateTime":"","execXUserDesc":"","flowSpeed":"1 滴/秒","labNo":"","medCareNo":"100136","no":"4","notes":"","notifyClinician":"N","oecprDesc":"长期医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"498||4","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"皮试","phOrdQtyUnit":"1600 万IU","phcfrCode":"Bid","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190221000036","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000267 ","seeOrderUserDateTime":"2019-02-21 17:27:00","seeOrderUserName":"护士01","seqNo":"498||4","skinColor":"#28FF28","skinResult":"阴","skinTestInfo":" (-)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","speedFlowRate":"1","speedFlowUnit":"滴/秒","sttDate":"03-01","sttDateTime":"2019-03-01 16:00","sttTime":"16:00","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]}]
     * sheetDefCode : PSD
     * sheetList : [{"code":"DefaultSee","desc":"需处理医嘱"},{"code":"WZX","desc":"未执行"},{"code":"CQSYD","desc":"输液单"},{"code":"cqkfyd","desc":"口服药单"},{"code":"CQZSD","desc":"注射单"},{"code":"HLZLD","desc":"处置治疗单"},{"code":"YSHLD","desc":"饮食护理单"},{"code":"JYD","desc":"检验单"},{"code":"JCD","desc":"检查单"},{"code":"PSD","desc":"皮试单"},{"code":"BLD","desc":"病理单"},{"code":"SXD","desc":"输血单"},{"code":"CUDY","desc":"出院带药单"}]
     * status : 0
     */

    private String msg;
    private String msgcode;
    private String sheetDefCode;
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

    public String getSheetDefCode() {
        return sheetDefCode;
    }

    public void setSheetDefCode(String sheetDefCode) {
        this.sheetDefCode = sheetDefCode;
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
         * code : excuteOrder
         * desc : 执行医嘱
         * singleFlag : Y
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
         * bedCode : 08
         * name : 住院
         * patOrds : [[{"orderInfo":{"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"41||86||1","PatLevel":"","PreDisDateTime":"","abnorm":"Y","addOrdBtn":"补费用","admLoc":"内分泌科","age":"26岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"08","cancelReason":"","createDateTime":"2019-07-17 16:24:57","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"41||86||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100012","no":"3","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"41||86","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"住院","phOrdQtyUnit":"800 万IU","phcfrCode":"St","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190717000008","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000048 ","seeOrderUserDateTime":"2019-07-17 16:28:00","seeOrderUserName":"护士01","seqNo":"41||86","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"07-17","sttDateTime":"2019-07-17 16:25","sttTime":"16:25","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]
         */

        private String bedCode;
        private String name;
        private List<List<PatOrdsBean>> patOrds;
        private String ifPatRepeat="0";

        public String getIfPatRepeat() {
            return ifPatRepeat;
        }

        public void setIfPatRepeat(String ifPatRepeat) {
            this.ifPatRepeat = ifPatRepeat;
        }

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
             * orderInfo : {"DspStat":"未发","Durat":"1天","EncryptLevel":"","ID":"41||86||1","PatLevel":"","PreDisDateTime":"","abnorm":"Y","addOrdBtn":"补费用","admLoc":"内分泌科","age":"26岁","arcimDesc":"注射用青霉素钠[400万U]","bedCode":"08","cancelReason":"","createDateTime":"2019-07-17 16:24:57","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","doseQtyUnit":"800 万IU","examInfo":{},"execCtcpDesc":"","execDateTime":"","execID":"41||86||1","execXDateTime":"","execXUserDesc":"","flowSpeed":"","labNo":"","medCareNo":"100012","no":"3","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"西药","ordDep":"内分泌科","ordID":"41||86","ordStatDesc":"核实","ordTyp":"R","patIdentity":"全自费","patName":"住院","phOrdQtyUnit":"800 万IU","phcfrCode":"St","phcinDesc":"静脉滴注","placerNo":"","preparedFlag":"","prescNo":"I190717000008","price":"2.70","printFlag":"","reclocDesc":"住院药房","regNo":"0000000048 ","seeOrderUserDateTime":"2019-07-17 16:28:00","seeOrderUserName":"护士01","seqNo":"41||86","skinColor":"#FF0000","skinResult":"阳","skinTestInfo":" (+)护士01","skinTestNumber":"","specCollDateTime":"","specDesc":"","sttDate":"07-17","sttDateTime":"2019-07-17 16:25","sttTime":"16:25","totalAmount":"2.70","tubeColor":"","tubeColorCode":"","tubeColorDesc":"","verifyFlag":"","xCtcpDesc":"","xDateTime":""}
             * type : main
             */

            private OrderInfoBean orderInfo;
            private String type;
            private Map<String,String> orderInfoMap = new HashMap<>();

            public Map<String, String> getOrderInfoMap() {
                return orderInfoMap;
            }

            public void setOrderInfoMap(Map<String, String> orderInfoMap) {
                this.orderInfoMap = orderInfoMap;
            }

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
                 * DspStat : 未发
                 * Durat : 1天
                 * EncryptLevel :
                 * ID : 41||86||1
                 * PatLevel :
                 * PreDisDateTime :
                 * abnorm : Y
                 * addOrdBtn : 补费用
                 * admLoc : 内分泌科
                 * age : 26岁
                 * arcimDesc : 注射用青霉素钠[400万U]
                 * bedCode : 08
                 * cancelReason :
                 * createDateTime : 2019-07-17 16:24:57
                 * ctcpDesc : 医生01
                 * disposeStatCode : 已执行^#b4a89a
                 * doseQtyUnit : 800 万IU
                 * examInfo : {}
                 * execCtcpDesc :
                 * execDateTime :
                 * execID : 41||86||1
                 * execXDateTime :
                 * execXUserDesc :
                 * flowSpeed :
                 * labNo :
                 * medCareNo : 100012
                 * no : 3
                 * notes :
                 * notifyClinician : N
                 * oecprDesc : 临时医嘱
                 * orcatDesc : 西药
                 * ordDep : 内分泌科
                 * ordID : 41||86
                 * ordStatDesc : 核实
                 * ordTyp : R
                 * patIdentity : 全自费
                 * patName : 住院
                 * phOrdQtyUnit : 800 万IU
                 * phcfrCode : St
                 * phcinDesc : 静脉滴注
                 * placerNo :
                 * preparedFlag :
                 * prescNo : I190717000008
                 * price : 2.70
                 * printFlag :
                 * reclocDesc : 住院药房
                 * regNo : 0000000048
                 * seeOrderUserDateTime : 2019-07-17 16:28:00
                 * seeOrderUserName : 护士01
                 * seqNo : 41||86
                 * skinColor : #FF0000
                 * skinResult : 阳
                 * skinTestInfo :  (+)护士01
                 * skinTestNumber :
                 * specCollDateTime :
                 * specDesc :
                 * sttDate : 07-17
                 * sttDateTime : 2019-07-17 16:25
                 * sttTime : 16:25
                 * totalAmount : 2.70
                 * tubeColor :
                 * tubeColorCode :
                 * tubeColorDesc :
                 * verifyFlag :
                 * xCtcpDesc :
                 * xDateTime :
                 */

                private String DspStat;
                private String Durat;
                private String EncryptLevel;
                private String ID;
                private String PatLevel;
                private String PreDisDateTime;
                private String abnorm;
                private String addOrdBtn;
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
                private String preparedFlag;
                private String prescNo;
                private String price;
                private String printFlag;
                private String reclocDesc;
                private String regNo;
                private String seeOrderUserDateTime;
                private String seeOrderUserName;
                private String seqNo;
                private String skinColor;
                private String skinResult;
                private String skinTestInfo;
                private String skinTestNumber;
                private String specCollDateTime;
                private String specDesc;
                private String sttDate;
                private String sttDateTime;
                private String sttTime;
                private String totalAmount;
                private String tubeColor;
                private String tubeColorCode;
                private String tubeColorDesc;
                private String verifyFlag;
                private String xCtcpDesc;
                private String xDateTime;
                private String filteFlagExtend;

                public String getFilteFlagExtend() {
                    return filteFlagExtend;
                }

                public void setFilteFlagExtend(String filteFlagExtend) {
                    this.filteFlagExtend = filteFlagExtend;
                }
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

                public String getAbnorm() {
                    return abnorm;
                }

                public void setAbnorm(String abnorm) {
                    this.abnorm = abnorm;
                }

                public String getAddOrdBtn() {
                    return addOrdBtn;
                }

                public void setAddOrdBtn(String addOrdBtn) {
                    this.addOrdBtn = addOrdBtn;
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

                public String getPreparedFlag() {
                    return preparedFlag;
                }

                public void setPreparedFlag(String preparedFlag) {
                    this.preparedFlag = preparedFlag;
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

                public String getSkinColor() {
                    return skinColor;
                }

                public void setSkinColor(String skinColor) {
                    this.skinColor = skinColor;
                }

                public String getSkinResult() {
                    return skinResult;
                }

                public void setSkinResult(String skinResult) {
                    this.skinResult = skinResult;
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

                public String getTubeColorCode() {
                    return tubeColorCode;
                }

                public void setTubeColorCode(String tubeColorCode) {
                    this.tubeColorCode = tubeColorCode;
                }

                public String getTubeColorDesc() {
                    return tubeColorDesc;
                }

                public void setTubeColorDesc(String tubeColorDesc) {
                    this.tubeColorDesc = tubeColorDesc;
                }

                public String getVerifyFlag() {
                    return verifyFlag;
                }

                public void setVerifyFlag(String verifyFlag) {
                    this.verifyFlag = verifyFlag;
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

                public static class ExamInfoBean implements Serializable {

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
