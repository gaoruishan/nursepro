package com.dhcc.nursepro.workarea.workareautils;

import android.app.Activity;
import android.content.Intent;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.dhcc.module.nurse.bloodsugar.BloodSugarFragment;
import com.dhcc.module.nurse.education.HealthEduFragment;
import com.dhcc.module.nurse.nurplan.NurPlanFragment;
import com.dhcc.module.nurse.task.TaskOverviewFragment;
import com.dhcc.nursepro.Activity.MainActivity;
import com.dhcc.nursepro.Activity.SingleMainActivity;
import com.dhcc.nursepro.Activity.SplashActivity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.workarea.WorkareaFragment;
import com.dhcc.nursepro.workarea.allotbed.AllotBedFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodTransfusionSystemFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckResultListFragment;
import com.dhcc.nursepro.workarea.docorderlist.DocOrderListFragment;
import com.dhcc.nursepro.workarea.dosingreview.DosingReviewFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.DrugHandoverFragment;
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
import com.dhcc.nursepro.workarea.plyout.PlyOutListFragment;
import com.dhcc.nursepro.workarea.rjorder.RjOrderFragment;
import com.dhcc.nursepro.workarea.shift.ShiftFragment;
import com.dhcc.nursepro.workarea.taskmanage.TaskManageFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignRecordFragment;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea.workareautils
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:15:44
 */
public class WorkareaMainConfig {
    public ArrayList getMainCoinfgList(MainConfigBean mainConfigBean){
        if ("1".equals(SPUtils.getInstance().getString(SharedPreference.SINGLEMODEL))){
            SharedPreference.FRAGMENTARY = new ArrayList();
        }
        ArrayList listConfig=new ArrayList();
        for (int i = 0; i <mainConfigBean.getMainList().size() ; i++) {
            listConfig.add(getMainConfigItem(mainConfigBean.getMainList().get(i)));
        }
        return listConfig;
    }
    private Map getMainConfigItem(MainConfigBean.MainListBean item){

        Map map = new HashMap();
        map.put("fragicon",R.drawable.icon_orderexcute);
        switch (item.getModuleCode()) {
            case "BEDMAP":
                map.put("desc","床位图");
                map.put("fragName", BedMapFragment.class.getName());
                map.put("fragicon",R.drawable.icon_bedmap);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "VITALSIGN":
                map.put("desc","生命体征");
                map.put("fragName", VitalSignFragment.class.getName());
                map.put("fragicon",R.drawable.icon_vitalsign);
//                map.put("singleModel","1");
                SharedPreference.FRAGMENTARY.add(map);

                Map mapVital = new HashMap();
                mapVital.put("desc","生命体征");
                mapVital.put("fragName", VitalSignRecordFragment.class.getName());
                mapVital.put("fragicon",R.drawable.icon_vitalsign);
                mapVital.put("singleModel","1");
                SharedPreference.FRAGMENTARY.add(mapVital);
                break;
            case "EVENTS":
                map.put("desc","事件登记");
                map.put("fragName", PatEventsFragment.class.getName());
                map.put("fragicon",R.drawable.icon_events);
                map.put("singleModel","1");
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "ORDERSEARCH":
                map.put("desc","医嘱查询");
                map.put("fragName", OrderSearchFragment.class.getName());
                map.put("fragicon",R.drawable.icon_orderserarch);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "ORDEREXECUTE":
                map.put("desc","医嘱执行");
                map.put("fragName", OrderExecuteFragment.class.getName());
                map.put("fragicon",R.drawable.icon_orderexcute);
                map.put("singleModel","1");
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "CHECK":
                map.put("desc","检查结果");
                map.put("fragName", CheckPatsFragment.class.getName());
                map.put("fragicon",R.drawable.icon_check);
                SharedPreference.FRAGMENTARY.add(map);

                Map map1 = new HashMap();
                map1.put("desc","检查报告");
                map1.put("fragName", CheckResultListFragment.class.getName());
                map1.put("fragicon",R.drawable.icon_check);
                map1.put("singleModel","2");
                SharedPreference.FRAGMENTARY.add(map1);
                break;
            case "LAB":
                map.put("desc","检验结果");
                map.put("fragName", LabPatsFragment.class.getName());
                map.put("fragicon",R.drawable.icon_lab);
                SharedPreference.FRAGMENTARY.add(map);

                Map map2 = new HashMap();
                map2.put("desc","检验结果");
                map2.put("fragName", LabResultListFragment.class.getName());
                map2.put("fragicon",R.drawable.icon_lab);
                map2.put("singleModel","2");
                SharedPreference.FRAGMENTARY.add(map2);
                break;
            case "OPERATION":
                map.put("desc","手术查询");
                map.put("fragName", OperationFragment.class.getName());
                map.put("fragicon",R.drawable.icon_operation);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "LABOUT":
                map.put("desc","检验打包");
                map.put("fragName", LabOutListFragment.class.getName());
                map.put("fragicon",R.drawable.icon_labout);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "DOSINGREVIEW":
                map.put("desc","配液复核");
                map.put("fragName", DosingReviewFragment.class.getName());
                map.put("fragicon",R.drawable.icon_dosingreview);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "ALLOTBED":
                map.put("desc","入院分床");
                map.put("fragName", AllotBedFragment.class.getName());
                map.put("fragicon",R.drawable.icon_allotbed);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "DOCORDERLIST":
                map.put("desc","医嘱单");
                map.put("fragName", DocOrderListFragment.class.getName());
                map.put("fragicon",R.drawable.icon_docorderlist);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "BLOOD":
                map.put("desc","输血系统");
                map.put("fragName", BloodTransfusionSystemFragment.class.getName());
                map.put("fragicon",R.drawable.icon_blood);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "MILK":
                map.put("desc","母乳闭环");
                map.put("fragName", MilkLoopSystemFragment.class.getName());
                map.put("fragicon",R.drawable.icon_milk);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "MOTHERBABYLINK":
                map.put("desc","母婴关联");
                map.put("fragName", MotherBabyLinkFragment.class.getName());
                map.put("fragicon",R.drawable.icon_motherbabylink);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "MODELDETAIL":
                map.put("desc","护理病历");
                map.put("fragName", PatNurRecordFragment.class.getName());
                map.put("fragicon",R.drawable.icon_events);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "NURTOUR":
                map.put("desc","巡视");
                map.put("fragName", NurTourFragment.class.getName());
                map.put("fragicon",R.drawable.icon_tour);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "DRUGHANDOVER":
                map.put("desc","药品交接");
                map.put("fragName", DrugHandoverFragment.class.getName());
                map.put("fragicon",R.drawable.icon_drugpreparation);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "DRUGPREPARATION":
                map.put("desc","药品交接");
                map.put("fragName",DrugHandoverFragment.class.getName());
                map.put("fragicon",R.drawable.icon_drugpreparation);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "RLREG":
                map.put("desc","余液登记");
                map.put("fragName", RLRegFragment.class.getName());
                map.put("fragicon",R.drawable.icon_drugrlreg);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "SHIFT":
                map.put("desc","交班本");
                map.put("fragName", ShiftFragment.class.getName());
                map.put("fragicon",R.drawable.icon_drugrlreg);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "IFOrdRec":
                map.put("desc","静配接收");
                map.put("fragName", DrugReceiveFragment.class.getName());
                map.put("fragicon",R.drawable.icon_drugrlreg);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "TaskManageFragment":
                //w ##class(Nur.DHCNurPdaModule).Save("任务管理^TaskManageFragment^26^Y^")
                map.put("desc","任务管理");
                map.put("fragName", TaskManageFragment.class.getName());
                map.put("fragicon",R.drawable.icon_task_manage);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "PLYOUT":
                map.put("desc","病理运送");
                map.put("fragName", PlyOutListFragment.class.getName());
                map.put("fragicon",R.drawable.icon_events);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "RJORD":
                map.put("desc","日间输液");
                map.put("fragName", RjOrderFragment.class.getName());
                map.put("fragicon",R.drawable.icon_events);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "HealthEduFragment":
                //w ##class(Nur.DHCNurPdaModule).Save("健康宣教^HealthEduFragment^21^Y^")
                map.put("desc","健康宣教");
                map.put("fragName", HealthEduFragment.class.getName());
                map.put("fragicon",R.drawable.dhcc_main_nurse_education);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "TaskOverviewFragment":
                map.put("desc","任务总览");
                map.put("fragName", TaskOverviewFragment.class.getName());
                map.put("fragicon",R.drawable.dhcc_main_nurse_task_overview);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "NurPlanFragment":
                //w ##class(Nur.DHCNurPdaModule).Save("护理计划^NurPlanFragment^24^Y^")
                map.put("desc","护理计划");
                map.put("fragName", NurPlanFragment.class.getName());
                map.put("fragicon",R.drawable.dhcc_main_nurse_plan);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "BloodSugarFragment":
                //w ##class(Nur.DHCNurPdaModule).Save("血糖采集^BloodSugarFragment^25^Y^")
                map.put("desc","血糖采集");
                map.put("fragName", BloodSugarFragment.class.getName());
                map.put("fragicon",R.drawable.dhcc_main_nurse_blood_sugar);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            case "ORDEXEANDSEARCH":
                map.put("desc","医嘱");
                map.put("fragName", OrderSearchAndExecuteFragment.class.getName());
                map.put("fragicon",R.drawable.dhcc_main_nurse_task_overview);
                SharedPreference.FRAGMENTARY.add(map);
                break;
            default:
                break;
        }

        if (item.getModuleCode()!=null){
            map.put("code",item.getModuleCode());
        }
        if (item.getModuleDesc()!=null){
            map.put("desc",item.getModuleDesc());
        }
        return map;
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
            }
        });
    }

}
