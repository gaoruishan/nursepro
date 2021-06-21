package com.dhcc.nursepro.workarea.workareautils;

import android.app.Activity;
import android.content.Intent;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.bloodsugar.BloodSugarFragment;
import com.dhcc.module.nurse.education.HealthEduFragment;
import com.dhcc.module.nurse.nurplan.NurPlanFragment;
import com.dhcc.module.nurse.task.TaskOverviewFragment;
import com.dhcc.nursepro.Activity.MainActivity;
import com.dhcc.nursepro.Activity.SingleMainActivity;
import com.dhcc.nursepro.BuildConfig;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.workarea.Infusionsituation.InfusionSituationFragment;
import com.dhcc.nursepro.workarea.allotbed.AllotBedFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodTransfusionSystemFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckResultListFragment;
import com.dhcc.nursepro.workarea.docorderlist.DocOrderListFragment;
import com.dhcc.nursepro.workarea.dosingreview.DosingReviewFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.DrugHandoverFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.DrugPreparationFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.RLRegFragment;
import com.dhcc.nursepro.workarea.infusiondrugreceive.DrugReceiveFragment;
import com.dhcc.nursepro.workarea.labout.LabOutListFragment;
import com.dhcc.nursepro.workarea.labresult.LabPatsFragment;
import com.dhcc.nursepro.workarea.labresult.LabResultListFragment;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.MilkLoopSystemFragment;
import com.dhcc.nursepro.workarea.motherbabylink.MotherBabyLinkFragment;
import com.dhcc.nursepro.workarea.nurrecordnew.PatNurRecordFragment;
import com.dhcc.nursepro.workarea.nurtour.NurTourFragment;
import com.dhcc.nursepro.workarea.operation.OperationFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderSearchAndExecuteFragment;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.pathandover.PatHandoverFragment;
import com.dhcc.nursepro.workarea.plyout.PlyOutListFragment;
import com.dhcc.nursepro.workarea.rjorder.RjOrderFragment;
import com.dhcc.nursepro.workarea.shift.ShiftFragment;
import com.dhcc.nursepro.workarea.taskmanage.TaskManageFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignRecordFragment;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;
import com.github.mikephil.charting.renderer.BarChartRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea.workareautils
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:15:44
 */
public class WorkareaMainConfig {
    public List<MainConfigBean.MainListBean> getMainCoinfgList(MainConfigBean mainConfigBean){
        if ("1".equals(SPUtils.getInstance().getString(SharedPreference.SINGLEMODEL))){
            SharedPreference.FRAGMENTARY = new ArrayList();
        }
        //添加测试
        testModel(mainConfigBean.getMainList());

//        ArrayList listConfig=new ArrayList();
//        for (int i = 0; i <mainConfigBean.getMainList().size() ; i++) {
//            listConfig.add(getMainConfigItem(mainConfigBean.getMainList().get(i)));
//        }

        modifyMainList(mainConfigBean.getMainList());
        return mainConfigBean.getMainList();
    }


    /**
     *
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
     *
     * @param mainList
     */
    private void modifyMainList(List<MainConfigBean.MainListBean> mainList) {
        for (MainConfigBean.MainListBean mainListBean : mainList) {
            for (MainConfigBean.MainListBean.MainSubListBean mainSubListBean : mainListBean.getMainSubList()) {
                switch (mainSubListBean.getModuleCode()) {
                    case "BEDMAP":
                        mainSubListBean.setFragmentClassName(BedMapFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_bedmap);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "VITALSIGN":
                        mainSubListBean.setFragmentClassName(VitalSignFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_vitalsign);
//                map.put("singleModel","1");
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());

                        Map mapVital = new HashMap();
                        mapVital.put("desc","生命体征");
                        mapVital.put("fragName", VitalSignRecordFragment.class.getName());
                        mapVital.put("fragicon",R.drawable.icon_vitalsign);
                        mapVital.put("singleModel","2");
                        SharedPreference.FRAGMENTARY.add(mapVital);
                        break;
                    case "EVENTS":
                        mainSubListBean.setFragmentClassName(PatEventsFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_events);
                        Map<String, String> mapEvents = mainSubListBean.toMap();
                        mapEvents.put("singleModel", "1");
                        SharedPreference.FRAGMENTARY.add(mapEvents);
                        break;
                    case "ORDERSEARCH":
                        mainSubListBean.setFragmentClassName(OrderSearchFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_orderserarch);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "ORDEREXECUTE":
                        mainSubListBean.setFragmentClassName(OrderExecuteFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_orderexcute);
                        Map<String, String> map = mainSubListBean.toMap();
                        map.put("singleModel", "1");
                        SharedPreference.FRAGMENTARY.add(map);
                        break;
                    case "ORDEREXECLAB":
                        mainSubListBean.setFragmentClassName(OrderExecuteFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_orderexcute);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "CHECK":
                        mainSubListBean.setFragmentClassName(CheckPatsFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_check);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());

                        Map mapCheck = new HashMap();
                        mapCheck.put("desc","检查报告");
                        mapCheck.put("fragName", CheckResultListFragment.class.getName());
                        mapCheck.put("fragicon",R.drawable.icon_check);
                        mapCheck.put("singleModel","2");
                        SharedPreference.FRAGMENTARY.add(mapCheck);
                        break;
                    case "LAB":
                        mainSubListBean.setFragmentClassName(LabPatsFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_lab);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());

                        Map mapLab = new HashMap();
                        mapLab.put("desc","检验结果");
                        mapLab.put("fragName", LabResultListFragment.class.getName());
                        mapLab.put("fragicon",R.drawable.icon_lab);
                        mapLab.put("singleModel","2");
                        SharedPreference.FRAGMENTARY.add(mapLab);
                        break;
                    case "OPERATION":
                        mainSubListBean.setFragmentClassName(OperationFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_operation);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "LABOUT":
                        mainSubListBean.setFragmentClassName(LabOutListFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_labout);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "DOSINGREVIEW":
                        mainSubListBean.setFragmentClassName(DosingReviewFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_dosingreview);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "ALLOTBED":
                        mainSubListBean.setFragmentClassName(AllotBedFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_allotbed);
                        Map<String, String> mapAllotBed = mainSubListBean.toMap();
                        mapAllotBed.put("singleModel", "1");
                        SharedPreference.FRAGMENTARY.add(mapAllotBed);
                        break;
                    case "DOCORDERLIST":
                        mainSubListBean.setFragmentClassName(DocOrderListFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_docorderlist);
                        Map<String, String> mapDocOrderList = mainSubListBean.toMap();
                        mapDocOrderList.put("singleModel", "1");
                        SharedPreference.FRAGMENTARY.add(mapDocOrderList);
                        break;
                    case "BLOOD":
                        mainSubListBean.setFragmentClassName(BloodTransfusionSystemFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_blood);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "MILK":
                        mainSubListBean.setFragmentClassName(MilkLoopSystemFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_milk);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "MOTHERBABYLINK":
                        mainSubListBean.setFragmentClassName(MotherBabyLinkFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_motherbabylink);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "MODELDETAIL":
                        mainSubListBean.setFragmentClassName(PatNurRecordFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_events);
                        Map<String, String> mapModelDetail = mainSubListBean.toMap();
                        mapModelDetail.put("singleModel", "1");
                        SharedPreference.FRAGMENTARY.add(mapModelDetail);
                        break;
                    case "MODELDETAILOLD":
                        mainSubListBean.setFragmentClassName(com.dhcc.nursepro.workarea.nurrecordold.PatNurRecordFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_events);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "NURTOUR":
                        mainSubListBean.setFragmentClassName(NurTourFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_tour);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "DRUGHANDOVER":
                        mainSubListBean.setFragmentClassName(DrugHandoverFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_drughandover);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "DRUGPREPARATION":
                        mainSubListBean.setFragmentClassName(DrugPreparationFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_drugpreparation);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "RLREG":
                        mainSubListBean.setFragmentClassName(RLRegFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_drugrlreg);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "SHIFT":
                        mainSubListBean.setFragmentClassName(ShiftFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_drugrlreg);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "IFOrdRec":
                        mainSubListBean.setFragmentClassName(DrugReceiveFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_drugrlreg);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "TaskManageFragment":
                        //w ##class(Nur.DHCNurPdaModule).Save("任务管理^TaskManageFragment^26^Y^")
                        mainSubListBean.setFragmentClassName(TaskManageFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_task_manage);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "PLYOUT":
                        mainSubListBean.setFragmentClassName(PlyOutListFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_events);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "RJORD":
                        mainSubListBean.setFragmentClassName(RjOrderFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_events);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "HealthEduFragment":
                        //w ##class(Nur.DHCNurPdaModule).Save("健康宣教^HealthEduFragment^21^Y^")
                        mainSubListBean.setFragmentClassName(HealthEduFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.dhcc_main_nurse_education);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "TaskOverviewFragment":
                        mainSubListBean.setFragmentClassName(TaskOverviewFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.dhcc_main_nurse_task_overview);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "NurPlanFragment":
                        //w ##class(Nur.DHCNurPdaModule).Save("护理计划^NurPlanFragment^24^Y^")
                        mainSubListBean.setFragmentClassName(NurPlanFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.dhcc_main_nurse_plan);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "BloodSugarFragment":
                        //w ##class(Nur.DHCNurPdaModule).Save("血糖采集^BloodSugarFragment^25^Y^")
                        mainSubListBean.setFragmentClassName(BloodSugarFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.dhcc_main_nurse_blood_sugar);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "ORDEXEANDSEARCH":
                        mainSubListBean.setFragmentClassName(OrderSearchAndExecuteFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.dhcc_main_nurse_task_overview);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "PatHandover":
                        //w ##class(Nur.DHCNurPdaModule).Save("护理计划^NurPlanFragment^24^Y^")
                        mainSubListBean.setFragmentClassName(PatHandoverFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_events);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "InfusionSituation":
                        mainSubListBean.setFragmentClassName(InfusionSituationFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_blood);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 添加测模块
     * @param mainList
     */
    private void testModel(List<MainConfigBean.MainListBean> mainList ) {
        if (BuildConfig.DEBUG) {
            MainConfigBean.MainListBean mainListTestBean = new MainConfigBean.MainListBean();
            List<MainConfigBean.MainListBean.MainSubListBean> subList = new ArrayList<>();

            subList.add(new MainConfigBean.MainListBean.MainSubListBean("NurPlanFragment","护理计划"));
            subList.add(new MainConfigBean.MainListBean.MainSubListBean("PatHandover","患者交接"));
            subList.add(new MainConfigBean.MainListBean.MainSubListBean("InfusionSituation","输液信息"));
            subList.add(new MainConfigBean.MainListBean.MainSubListBean("web","web测试","/hello.html"));

            mainListTestBean.setMenuName("测试");
            mainListTestBean.setMainSubList(subList);
            mainList.add(mainListTestBean);
        }
    }

//    private Map getMainConfigItem(MainConfigBean.MainListBean item){
//
//        Map map = new HashMap();
//        map.put("fragUrl", item.getModuleUrl());
//        map.put("fragicon",R.drawable.icon_orderexcute);
//        switch (item.getModuleCode()) {
//            case "BEDMAP":
//                map.put("desc","床位图");
//                map.put("fragName", BedMapFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_bedmap);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "VITALSIGN":
//                map.put("desc","生命体征");
//                map.put("fragName", VitalSignFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_vitalsign);
////                map.put("singleModel","1");
//                SharedPreference.FRAGMENTARY.add(map);
//
//                Map mapVital = new HashMap();
//                mapVital.put("desc","生命体征");
//                mapVital.put("fragName", VitalSignRecordFragment.class.getName());
//                mapVital.put("fragicon",R.drawable.icon_vitalsign);
//                mapVital.put("singleModel","2");
//                SharedPreference.FRAGMENTARY.add(mapVital);
//                break;
//            case "EVENTS":
//                map.put("desc","事件登记");
//                map.put("fragName", PatEventsFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_events);
//                map.put("singleModel","1");
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "ORDERSEARCH":
//                map.put("desc","医嘱查询");
//                map.put("fragName", OrderSearchFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_orderserarch);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "ORDEREXECUTE":
//                map.put("desc","医嘱执行");
//                map.put("fragName", OrderExecuteFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_orderexcute);
//                map.put("singleModel","1");
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "CHECK":
//                map.put("desc","检查结果");
//                map.put("fragName", CheckPatsFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_check);
//                SharedPreference.FRAGMENTARY.add(map);
//
//                Map map1 = new HashMap();
//                map1.put("desc","检查报告");
//                map1.put("fragName", CheckResultListFragment.class.getName());
//                map1.put("fragicon",R.drawable.icon_check);
//                map1.put("singleModel","2");
//                SharedPreference.FRAGMENTARY.add(map1);
//                break;
//            case "LAB":
//                map.put("desc","检验结果");
//                map.put("fragName", LabPatsFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_lab);
//                SharedPreference.FRAGMENTARY.add(map);
//
//                Map map2 = new HashMap();
//                map2.put("desc","检验结果");
//                map2.put("fragName", LabResultListFragment.class.getName());
//                map2.put("fragicon",R.drawable.icon_lab);
//                map2.put("singleModel","2");
//                SharedPreference.FRAGMENTARY.add(map2);
//                break;
//            case "OPERATION":
//                map.put("desc","手术查询");
//                map.put("fragName", OperationFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_operation);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "LABOUT":
//                map.put("desc","检验打包");
//                map.put("fragName", LabOutListFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_labout);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "DOSINGREVIEW":
//                map.put("desc","配液复核");
//                map.put("fragName", DosingReviewFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_dosingreview);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "ALLOTBED":
//                map.put("desc","入院分床");
//                map.put("fragName", AllotBedFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_allotbed);
//                map.put("singleModel","1");
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "DOCORDERLIST":
//                map.put("desc","医嘱单");
//                map.put("fragName", DocOrderListFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_docorderlist);
//                map.put("singleModel","1");
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "BLOOD":
//                map.put("desc","输血系统");
//                map.put("fragName", BloodTransfusionSystemFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_blood);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "MILK":
//                map.put("desc","母乳闭环");
//                map.put("fragName", MilkLoopSystemFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_milk);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "MOTHERBABYLINK":
//                map.put("desc","母婴关联");
//                map.put("fragName", MotherBabyLinkFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_motherbabylink);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "MODELDETAIL":
//                map.put("desc","护理病历");
//                map.put("fragName", PatNurRecordFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_events);
//                map.put("singleModel","1");
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "MODELDETAILOLD":
//                map.put("desc","护理病历");
//                map.put("fragName", com.dhcc.nursepro.workarea.nurrecordold.PatNurRecordFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_events);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "NURTOUR":
//                map.put("desc","巡视");
//                map.put("fragName", NurTourFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_tour);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "DRUGHANDOVER":
//                map.put("desc","药品交接");
//                map.put("fragName", DrugHandoverFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_drugpreparation);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "DRUGPREPARATION":
//                map.put("desc","药品交接");
//                map.put("fragName",DrugHandoverFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_drugpreparation);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "RLREG":
//                map.put("desc","余液登记");
//                map.put("fragName", RLRegFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_drugrlreg);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "SHIFT":
//                map.put("desc","交班本");
//                map.put("fragName", ShiftFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_drugrlreg);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "IFOrdRec":
//                map.put("desc","静配接收");
//                map.put("fragName", DrugReceiveFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_drugrlreg);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "TaskManageFragment":
//                //w ##class(Nur.DHCNurPdaModule).Save("任务管理^TaskManageFragment^26^Y^")
//                map.put("desc","任务管理");
//                map.put("fragName", TaskManageFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_task_manage);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "PLYOUT":
//                map.put("desc","病理运送");
//                map.put("fragName", PlyOutListFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_events);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "RJORD":
//                map.put("desc","日间输液");
//                map.put("fragName", RjOrderFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_events);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "HealthEduFragment":
//                //w ##class(Nur.DHCNurPdaModule).Save("健康宣教^HealthEduFragment^21^Y^")
//                map.put("desc","健康宣教");
//                map.put("fragName", HealthEduFragment.class.getName());
//                map.put("fragicon",R.drawable.dhcc_main_nurse_education);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "TaskOverviewFragment":
//                map.put("desc","任务总览");
//                map.put("fragName", TaskOverviewFragment.class.getName());
//                map.put("fragicon",R.drawable.dhcc_main_nurse_task_overview);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "NurPlanFragment":
//                //w ##class(Nur.DHCNurPdaModule).Save("护理计划^NurPlanFragment^24^Y^")
//                map.put("desc","护理计划");
//                map.put("fragName", NurPlanFragment.class.getName());
//                map.put("fragicon",R.drawable.dhcc_main_nurse_plan);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "BloodSugarFragment":
//                //w ##class(Nur.DHCNurPdaModule).Save("血糖采集^BloodSugarFragment^25^Y^")
//                map.put("desc","血糖采集");
//                map.put("fragName", BloodSugarFragment.class.getName());
//                map.put("fragicon",R.drawable.dhcc_main_nurse_blood_sugar);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "ORDEXEANDSEARCH":
//                map.put("desc","医嘱");
//                map.put("fragName", OrderSearchAndExecuteFragment.class.getName());
//                map.put("fragicon",R.drawable.dhcc_main_nurse_task_overview);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "PatHandover":
//                //w ##class(Nur.DHCNurPdaModule).Save("护理计划^NurPlanFragment^24^Y^")
//                map.put("desc","患者交接");
//                map.put("fragName", PatHandoverFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_events);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            case "InfusionSituation":
//                map.put("desc","输液信息");
//                map.put("fragName", InfusionSituationFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_blood);
//                SharedPreference.FRAGMENTARY.add(map);
//                break;
//            default:
//                break;
//        }
//
//        if (item.getModuleCode()!=null){
//            map.put("code",item.getModuleCode());
//        }
//        if (item.getModuleDesc()!=null){
//            map.put("desc",item.getModuleDesc());
//        }
//        return map;
//    }

    public void getMainConfigData(Activity activity) {
//        locId = spUtils.getString(SharedPreference.LOCID);
//        groupId = spUtils.getString(SharedPreference.GROUPID);
        SharedPreference.FRAGMENTMAP = new HashMap();
        WorkareaApiManager.getMainConfig(new WorkareaApiManager.GetMainconfigCallback() {
            @Override
            public void onSuccess(MainConfigBean mainConfigBean) {
                if (mainConfigBean.getSchStDateTime() != null && mainConfigBean.getSchEnDateTime() != null) {
                    SchDateTimeUtil.putSchStartEndDateTime(mainConfigBean.getSchStDateTime(),mainConfigBean.getSchEnDateTime());

                    if (StringUtils.isEmpty(mainConfigBean.getCurDateTime())) {
                        SPUtils.getInstance().put(SharedPreference.CURDATETIME, mainConfigBean.getSchEnDateTime());
                    } else {
                        SPUtils.getInstance().put(SharedPreference.CURDATETIME, mainConfigBean.getCurDateTime());
                    }
                }
//                SharedPreference.FRAGMENTARY = new ArrayList();
//                Map map = new HashMap();
//                map.put("code","Main");
//                map.put("desc","主页");
//                map.put("fragName", WorkareaFragment.class.getName());
//                map.put("fragicon",R.drawable.icon_workarea);
//                SharedPreference.FRAGMENTARY.add(map);

                WorkareaMainConfig workareaMainConfig = new WorkareaMainConfig();
                workareaMainConfig.getMainCoinfgList(mainConfigBean);
//                workAreaAdapter.setNewData(listMainConfig);
                SPUtils.getInstance().put(SharedPreference.BLOODSCANTIMES, mainConfigBean.getScantimes());
                DataCache.saveJson(mainConfigBean, SharedPreference.DATA_MAIN_CONFIG);
                if (SPUtils.getInstance().getString(SharedPreference.SINGLEMODEL,"0").equals("0")){
                    activity.startActivity(new Intent(activity, MainActivity.class));
                }else {
                    activity.startActivity(new Intent(activity, SingleMainActivity.class));
                }
                activity.finish();
            }
            @Override
            public void onFail(String code, String msg) {
//                showToast("error" + code + ":" + msg);
                ToastUtils.showShort(msg);
                activity.startActivity(new Intent(activity, LoginActivity.class));
            }
        });
    }

}
