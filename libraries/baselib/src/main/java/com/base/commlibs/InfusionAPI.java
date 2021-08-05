package com.base.commlibs;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPStaticUtils;

/**
 * API接口
 * @author:gaoruishan
 * @date:202020/12/4/16:37
 * @email:grs0515@163.com
 */
public class InfusionAPI {

    /**
     * 8.5 替换的方法
     * getOrders → GetOrdList
     * getOrdList → GetDispensingOrdList
     * despensingOrd → DispensingOrd
     * getPunctOrdList → GetPunctureOrdList
     * getFinishOrdList → GetExtractOrdList
     * getSkinOrdList → GetSkinTestOrdList
     * skinTime → SetSkinTestTime
     * skinDespensingOrd → SkinTestDispensingOrd
     * exeInjectOrd → InjectOrd
     * exeLabOrd → LabOrd
     * getIFOrdListByBarCode → GetReceiveOrdList
     * BatchIFSave → ReceiveOrd
     */

    /**
     * 兼容老版本
     */
    public static boolean isNew() {
        return BaseWebServiceUtils.NUR_MOES_SERVICE.equals(SPStaticUtils.getString(SharedPreference.oppdaService));
    }

    //获取医嘱列表
    public static String GetOrdList(){
        return isNew() ? "getOrdList" : "getOrders";
    }
    //配液列表
    public static String GetDispensingOrdList() {
        return  isNew() ? "getDispensingOrdList" : "getOrdList";
    }
    //配液复核
    public static String DispensingOrd(){
        return isNew() ? "dispensingOrd" : "despensingOrd";
    }
    //穿刺列表
    public static String GetPunctureOrdList(){
        return isNew() ? "getPunctureOrdList" : "getPunctOrdList";
    }
    //拔针列表
    public static String GetExtractOrdList(){
       return isNew() ? "getExtractOrdList" : "getFinishOrdList";
    }
    //皮试列表
    public static String GetSkinTestOrdList(){
        return isNew() ? "getSkinTestOrdList" : "getSkinOrdList";
    }
    //皮试计时
    public static String SetSkinTestTime(){
       return isNew() ? "setSkinTestTime" : "skinTime";
    }
    //皮试配液
    public static String SkinTestDispensingOrd (){
        return  isNew() ? "skinTestDispensingOrd" : "skinDespensingOrd";
    }
    //注射执行
    public static String InjectOrd(){
       return isNew() ? "injectOrd" : "exeInjectOrd";
    }
    //采血执行
    public static String LabOrd (){
        return isNew() ? "labOrd" : "exeLabOrd";
    }

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
    public static final String SetSkinTestResult = "setSkinTestResult";
    //消息数量提示(安医附院)
    public static final String getNotifyMessage = "getNotifyMessage";
    //工作量统计
    public static final String GetWorkload = "getWorkload";
    //患者信息
    public static final String getPatInfo = "getPatInfo";
    //获取医嘱详情
    public static final String getOrdInfo = "getOrdInfo";
    //穿刺执行
    public static final String PunctureOrd = "punctureOrd";
    //巡视列表
    public static final String GetTourOrdList = "getTourOrdList";
    //巡视执行
    public static final String TourOrd = "tourOrd";
    //续液列表
    public static final String GetChangeOrdList = "getChangeOrdList";
    //续液执行
    public static final String ChangeOrd = "changeOrd";
    //拔针执行
    public static final String ExtractOrd = "extractOrd";
    //获取扫码信息-采血/注射/医嘱执行
    public static final String getScanInfo = "getScanInfo";
    //采血列表
    public static final String GetLabOrdList = "getLabOrdList";
    //采血核对
    public static final String auditOrd = "auditOrd";
    //注射列表
    public static final String GetInjectOrdList = "getInjectOrdList";
    //注射配液
    public static final String injectDespensing = "injectDispensingOrd";
    //医嘱执行
    public static final String execOrder = "execOrder";
    //药品接受列表
    public static final String GetReceiveOrdList = "getReceiveOrdList";
    //输液接收
    public static final String ReceiveOrd = "receiveOrd";


    /*=======输血系统(广西医科大一附)=========*/
    //每次扫码 获取类型
    public static final String getBarcodeFlag = "GetBarcodeFlag";
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
