package com.dhcc.nursepro.workarea.workareautils;

import android.app.Activity;
import android.content.Intent;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.accompany.AccompanyFragment;
import com.dhcc.module.nurse.bloodsugar.BloodSugarFragment;
import com.dhcc.module.nurse.education.HealthEduFragment;
import com.dhcc.module.nurse.log.NurLogFragment;
import com.dhcc.module.nurse.nurplan.NurPlanFragment;
import com.dhcc.module.nurse.outmanage.OutManageFragment;
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
import com.dhcc.nursepro.workarea.rfid.PatBindInfusionFragment;
import com.dhcc.nursepro.workarea.rfid.PatBindRfidFragment;
import com.dhcc.nursepro.workarea.rjorder.RjOrderFragment;
import com.dhcc.nursepro.workarea.shift.ShiftFragment;
import com.dhcc.nursepro.workarea.taskmanage.TaskManageFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignRecordFragment;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

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
     *         }
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
                    case "AccompanyFragment":
                        //w ##class(Nur.DHCNurPdaModule).Save("陪护人记录^AccompanyFragment^27^Y^")
                        mainSubListBean.setFragmentClassName(AccompanyFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.dhcc_main_nurse_blood_sugar);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "OutManageFragment":
                        //w ##class(Nur.DHCNurPdaModule).Save("外出管理^OutManageFragment^28^Y^")
                        mainSubListBean.setFragmentClassName(OutManageFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.dhcc_main_nurse_blood_sugar);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "ORDEXEANDSEARCH":
                        mainSubListBean.setFragmentClassName(OrderSearchAndExecuteFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.dhcc_main_nurse_task_overview);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "PatHandover":
                        mainSubListBean.setFragmentClassName(PatHandoverFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_events);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "InfusionSituation":
                        mainSubListBean.setFragmentClassName(InfusionSituationFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_blood);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                    case "PatBindRfid":
                        mainSubListBean.setFragmentClassName(PatBindRfidFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_blood);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                   case "PatBindInfusionFragment":
                       //w ##class(Nur.DHCNurPdaModule).Save("输液绑定^PatBindInfusionFragment^33^Y^")
                        mainSubListBean.setFragmentClassName(PatBindInfusionFragment.class.getName());
                        mainSubListBean.setImgResouseId(R.drawable.icon_blood);
                        SharedPreference.FRAGMENTARY.add(mainSubListBean.toMap());
                        break;
                  case "NurLogFragment":
                       //w ##class(Nur.DHCNurPdaModule).Save("日志^NurLogFragment^33^Y^")
                        mainSubListBean.setFragmentClassName(NurLogFragment.class.getName());
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
            subList.add(new MainConfigBean.MainListBean.MainSubListBean("AccompanyFragment","陪护人记录"));
            subList.add(new MainConfigBean.MainListBean.MainSubListBean("OutManageFragment","外出管理"));
            subList.add(new MainConfigBean.MainListBean.MainSubListBean("PatBindRfid","RFID绑定"));
            subList.add(new MainConfigBean.MainListBean.MainSubListBean("PatBindInfusionFragment","输液绑定"));
            subList.add(new MainConfigBean.MainListBean.MainSubListBean("NurLogFragment","日志"));

            mainListTestBean.setMenuName("测试");
            mainListTestBean.setMainSubList(subList);
            mainList.add(mainListTestBean);
        }
    }


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
