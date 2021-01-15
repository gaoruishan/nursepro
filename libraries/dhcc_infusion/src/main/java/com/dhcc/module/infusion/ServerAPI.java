package com.dhcc.module.infusion;

/**
 * API接口
 * @author:gaoruishan
 * @date:202020/12/4/16:37
 * @email:grs0515@163.com
 */
public class ServerAPI {

    //登陆
    public static final String Logon = "Logon";
    //获取主页配置
    public static final String getMainConfig = "getMainConfig";
    //广播码
    public static final String getBroadcastConfig = "getBroadcastConfig";
    //消息列表-输液
    public static final String getInfusionMessage = "getInfusionMessage";
    //消息列表-皮试
    public static final String getSkinTestMessage = "getSkinTestMessage";
    //置皮试结果
    public static final String setSkinTestResult = "setSkinTestResult";
    //消息数量提示(安医附院)
    public static final String getNotifyMessage = "getNotifyMessage";
    //工作量统计
    public static final String getWorkload = "getWorkload";
    //配液列表
    public static final String getOrdList = "getOrdList";
    //配液复核
    public static final String despensingOrd = "despensingOrd";
    //患者信息
    public static final String getPatInfo = "getPatInfo";
    //获取医嘱详情
    public static final String getOrdInfo = "getOrdInfo";
    //穿刺列表
    public static final String getPunctOrdList = "getPunctOrdList";
    //穿刺执行
    public static final String punctureOrd = "punctureOrd";
    //巡视列表
    public static final String getTourOrdList = "getTourOrdList";
    //巡视执行
    public static final String tourOrd = "tourOrd";
    //续液列表
    public static final String getChangeOrdList = "getChangeOrdList";
    //续液执行
    public static final String changeOrd = "changeOrd";
    //拔针列表
    public static final String getFinishOrdList = "getFinishOrdList";
    //拔针执行
    public static final String extractOrd = "extractOrd";
    //皮试列表
    public static final String getSkinOrdList = "getSkinOrdList";
    //皮试配液
    public static final String skinDespensingOrd = "skinDespensingOrd";
    //皮试计时
    public static final String skinTime = "skinTime";
    //获取扫码信息-采血/注射/医嘱执行
    public static final String getScanInfo = "getScanInfo";
    //采血列表
    public static final String getLabOrdList = "getLabOrdList";
    //采血执行
    public static final String exeLabOrd = "exeLabOrd";
    //采血核对
    public static final String auditOrd = "auditOrd";
    //注射列表
    public static final String getInjectOrdList = "getInjectOrdList";
    //注射配液
    public static final String injectDespensing = "injectDespensing";
    //注射执行
    public static final String exeInjectOrd = "exeInjectOrd";
    //获取医嘱列表
    public static final String getOrders = "getOrders";
    //医嘱执行
    public static final String execOrder = "execOrder";
    //药品接受列表
    public static final String getIFOrdListByBarCode = "getIFOrdListByBarCode";
    //输液接收
    public static final String BatchIFSave = "BatchIFSave";





    /*=======输血系统(广西医科大一附)=========*/
    //输血列表
    public static final String getTransBloodList = "getTransBloodList";
    //输血详情
    public static final String getTransBloodDetail = "getTransBloodDetail";
    //输血签收
    public static final String bloodReceive = "bloodReceive";
    //输血复核
    public static final String bloodCheck = "bloodCheck";
    //输注开始
    public static final String startTransfusion = "startTransfusion";
    //输血巡视
    public static final String bloodPatrol = "bloodPatrol";
    //输血结束
    public static final String endTransfusion = "endTransfusion";
    //输血回收
    public static final String recycleBloodbag = "recycleBloodbag";


}
