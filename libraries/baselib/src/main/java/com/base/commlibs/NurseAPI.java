package com.base.commlibs;

/**
 * 住院护士站接口
 * @author:gaoruishan
 * @date:202021/4/7/15:39
 * @email:grs0515@163.com
 */
public class NurseAPI {

    //更新
    public static final String GetNewVersion = "GetNewVersion";
    public static final String getNewVersion = "getNewVersion";
    public static final String getBedListSelected = "getBedListSelected";
    /**
     * 登陆
     */
    public static final String getBroadcastConfig = "getBroadcastConfig";
    public static final String GetUserPwd = "GetUserPwd";
    public static final String ScanLogon = "ScanLogon";
    public static final String Logon = "Logon";
    /**
     * 消息
     */
    public static final String setSkinTestResult = "setSkinTestResult";
    public static final String getSkinTestMessage = "getSkinTestMessage";
    public static final String getNotifyList = "getNotifyList";
    public static final String readMessage = "readMessage";
    /**
     * 设置
     */
    public static final String getBedList = "getBedList";
    public static final String setManagedBeds = "setManagedBeds";
    public static final String execOrSeeOrderByDateTime = "execOrSeeOrderByDateTime";
    /**
     * 主页
     */
    public static final String getMainConfig = "getMainConfig";
    public static final String getScanInfoByMain = "getScanInfoByMain";//医嘱执行
    public static final String preparedVerifyOrd = "preparedVerifyOrd";//配液复核
    public static final String tourOrd = "tourOrd";
    public static final String suspendOrd = "suspendOrd";
    public static final String continueOrd = "continueOrd";
    public static final String stopOrd = "continueOrd";
    public static final String finishOrd = "finishOrd";
    /**
     * 床位图
     */
    public static final String getWardPatList = "getWardPatList";
    public static final String getScanInfo = "getScanInfo";//医嘱执行
    public static final String getInfeeDetailByDate = "getInfeeDetailByDate";
    public static final String getPatListToVoice = "GetPatListToVoice";
    /**
     * 生命体征
     */
    public static final String getTempPatList = "getTempPatList";
    public static final String getTempValue = "getTempValue";
    public static final String getPatTempImage = "getPatTempImage";
    public static final String saveTempData = "saveTempData";
    /**
     * 事件登记
     */
    public static final String getPatEvents = "getPatEvents";
    public static final String getEventItem = "getEventItem";
    public static final String getPatWristInfo = "getPatWristInfo";//入院分床/输血系统/母婴关联
    public static final String saveEvent = "saveEvent";
    public static final String DelEvent = "DelEvent";
    /**
     * 医嘱查询/执行
     */
    public static final String getOrders = "getOrders";
    public static final String skinTime = "skinTime";
    public static final String execOrSeeOrder = "execOrSeeOrder";
    /**
     * 检查报告/检验结果
     */
    public static final String getInWardPatList = "getInWardPatList";//医嘱单/护理病历
    public static final String getRisOrderList = "getRisOrderList";
    public static final String getLabOrdList = "getLabOrdList";
    public static final String getLabResult = "getLabResult";
    /**
     * 手术查询
     */
    public static final String getOperationList = "getOperationList";
    /**
     * 检验打包
     */
    public static final String getLabOutList = "getLabOutList";
    public static final String getLabOutDetail = "getLabOutDetail";
    public static final String delOrExchange = "delOrExchange";
    /**
     * 配液复核
     */
    public static final String getInfusionOrdList = "getInfusionOrdList";
    /**
     * 入院分床
     */
    public static final String getAllotInfo = "getAllotInfo";
    public static final String allotBed = "allotBed";
    public static final String isFirstToBed = "isFirstToBed";
    public static final String firstBeHosTemData = "firstBeHosTemData";
    /**
     * 医嘱单
     */
    public static final String getDocOrderList = "getDocOrderList";
    /**
     * 输血系统
     */
    public static final String getBloodInfo = "getBloodInfo";
    public static final String bloodRecive = "bloodRecive";
    public static final String getInfusionBloodInfo = "getInfusionBloodInfo";
    public static final String startTransfusion = "startTransfusion";
    public static final String bloodPatrol = "bloodPatrol";
    public static final String endTransfusion = "endTransfusion";
    public static final String recycleBloodbag = "recycleBloodbag";
    public static final String getBloodList = "getBloodList";
    public static final String getBloodPatrolRecord = "getBloodPatrolRecord";
    public static final String getBloodTemData = "getBloodTemData";
    /**
     * 母乳闭环
     */
    public static final String getBottleInfo = "getBottleInfo";
    public static final String getMilkBagInfo = "getMilkBagInfo";
    public static final String getMilkBagInfoWL = "getMilkBagInfoWL";
    public static final String getBagFreezingInfoWL = "getBagFreezingInfoWL";
    public static final String getBottlingInfo = "getBottlingInfo";
    public static final String getcoldInfoWL = "getcoldInfoWL";
    public static final String bottling = "bottling";
    public static final String coldStorageWL = "coldStorageWL";
    public static final String exeStart = "exeStart";
    public static final String exeEnd = "exeEnd";
    public static final String bagFreezingWL = "bagFreezingWL";
    public static final String receiveBagWL = "receiveBagWL";
    public static final String getHeatInfo = "getHeatInfo";
    public static final String bagHeatStt = "bagHeatStt";
    public static final String bagHeatEnd = "bagHeatEnd";
    public static final String bagBottle = "bagBottle";
    public static final String getExeList = "getExeList";
    public static final String getScanInfoWL = "getScanInfoWL";
    /**
     * 母婴关联
     */
    public static final String motherRelationBaby = "motherRelationBaby";
    /**
     * 护理病历
     */
    public static final String getModelList = "getModelList";
    public static final String getNewEmrList = "getNewEmrList";
    public static final String GetXmlValues = "GetXmlValues";
    public static final String getDataSource = "getDataSource";
    public static final String saveNewEmrData = "saveNewEmrData";
    public static final String getKnowledgeTree = "getKnowledgeTree";
    public static final String getKnowledgeContent = "getKnowledgeContent";
    //老版
    public static final String getPGDId = "getPGDId";
    public static final String getEmrXml = "getEmrXml";
    public static final String getDateTime = "getDateTime";
    public static final String getEmrPatinfo = "getEmrPatinfo";
    public static final String linkEmrData = "linkEmrData";
    public static final String getItemConfigByEmrCode = "getItemConfigByEmrCode";
    /**
     * 巡视
     */
    public static final String getSumTourList = "getSumTourList";
    public static final String getGradeTourList = "getGradeTourList";
    public static final String getModelData = "getModelData";
    public static final String getInfusionTourList = "getInfusionTourList";
    public static final String saveTour = "saveTour";
    public static final String deleteTour = "deleteTour";
    /**
     * 药品交接
     */
    public static final String getOrdListByBarCode = "getOrdListByBarCode";
    public static final String BatchSave = "BatchSave";
    public static final String getOrdRecieveList = "getOrdRecieveList";
    /**
     * 余液登记
     */
    public static final String getResidualQtyList = "getResidualQtyList";
    public static final String residualQtyReg = "residualQtyReg";
    /**
     * 交班本
     */
    public static final String getShiftData = "getShiftData";
    public static final String getShiftDataDetail = "getShiftDataDetail";
    /**
     * 静配接收
     */
    public static final String getIFOrdListByBarCode = "getIFOrdListByBarCode";
    public static final String BatchIFSave = "BatchIFSave";
    /**
     * 任务管理
     */
    public static final String getOrderTasks = "getOrderTasks";
    /**
     * 病历运送
     */
    public static final String getPlyOutList = "getPlyOutList";
    public static final String getPlyOutDetail = "getPlyOutDetail";
    public static final String delOrExchangePly = "delOrExchangePly";
    //=============================智护============================
    /**
     * 健康宣教
     */
    public static final String getEducationList = "getEducationList";
    public static final String getEduContents = "getEduContents";
    public static final String saveEdu = "saveEdu";
    public static final String getEduOrdList = "getEduOrdList";
    /**
     * 任务总览
     */
    public static final String getExecuteSummaryData = "getExecuteSummaryData";
    public static final String getNormalOrdTask = "getNormalOrdTask";
    public static final String GetTempDateMeasureByDay = "GetTempDateMeasureByDay";
    public static final String getNurPlanTaskList = "getNurPlanTaskList";
    public static final String getExecuteTaskList = "getExecuteTaskList";
    public static final String getNurTaskSch = "getNurTaskSch";
    public static final String executeNurTask = "executeNurTask";
    public static final String getNeedEmr = "getNeedEmr";
    /**
     * 护理计划
     */
    public static final String getQuestionRecord = "getQuestionRecord";
    public static final String cancelQuestion = "cancelQuestion";
    public static final String stopQuestion = "stopQuestion";
    public static final String getQuestionList = "getQuestionList";
    public static final String saveQuestionRecord = "saveQuestionRecord";
    public static final String saveQuestionComments = "saveQuestionComments";
    public static final String getGoalByQestId = "getGoalByQestId";
    public static final String getInterventionByQestId = "getInterventionByQestId";
    public static final String getInterventionFreq = "getInterventionFreq";
    public static final String stopInterventions = "stopInterventions";
    public static final String cancelInterventions = "cancelInterventions";
    public static final String getInterventionList = "getInterventionList";
    public static final String saveNewInterventionList = "saveNewInterventionList";
    public static final String saveGoalRecord = "saveGoalRecord";
    public static final String saveIntervRecord = "saveIntervRecord";
    /**
     * 血糖
     */
    public static final String getSugarPatList = "getSugarPatList";
    public static final String getSugarValueAndItem = "getSugarValueAndItem";
    public static final String saveSugarInfo = "saveSugarInfo";
    public static final String getSugarValueByDate = "getSugarValueByDate";
    //=============================智护============================

    /**
     * 患者交接
     */
    public static final String getConnectList = "getConnectList";
    public static final String getScanConnect = "getScanConnect";
    public static final String saveConnect = "saveConnect";
    public static final String getConnectAndPat = "getConnectAndPat";
    public static final String getNurseInfo = "getNurseInfo";
    public static final String saveConnectSub = "saveConnectSub";
    /**
     * 取备用药
     */
    public static final String getOrdInfo = "getOrdInfo";
    public static final String TakeOrd = "TakeOrd";
    public static final String getTakeOrdList = "getTakeOrdList";

    /**
     * 输液情况
     */
    public static final String GetInfusionByWard = "GetInfusionByWard";
    public static final String GetInfusionDetailByWard = "GetInfusionDetailByWard";
}
