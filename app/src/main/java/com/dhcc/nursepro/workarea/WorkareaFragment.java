package com.dhcc.nursepro.workarea;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.education.HealthEduFragment;
import com.dhcc.module.nurse.task.TaskOverviewFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.allotbed.AllotBedFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodTransfusionSystemFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.docorderlist.DocOrderListFragment;
import com.dhcc.nursepro.workarea.dosingreview.DosingReviewFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.DrugHandoverFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.DrugPreparationFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.RLRegFragment;
import com.dhcc.nursepro.workarea.infusiondrugreceive.DrugReceiveFragment;
import com.dhcc.nursepro.workarea.labout.LabOutListFragment;
import com.dhcc.nursepro.workarea.labresult.LabPatsFragment;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.MilkLoopSystemFragment;
import com.dhcc.nursepro.workarea.motherbabylink.MotherBabyLinkFragment;
import com.dhcc.nursepro.workarea.nurtour.NurTourFragment;
import com.dhcc.nursepro.workarea.operation.OperationFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderSearchAndExecuteFragment;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.plyout.PlyOutListFragment;
import com.dhcc.nursepro.workarea.rjorder.RjOrderFragment;
import com.dhcc.nursepro.workarea.shift.ShiftFragment;
import com.dhcc.nursepro.workarea.taskmanage.TaskManageFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.workareaadapter.WorkAreaAdapter;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;
import com.dhcc.nursepro.workarea.workareabean.OperateResultBean;
import com.dhcc.nursepro.workarea.workareabean.PreparedVerifyOrdBean;
import com.dhcc.nursepro.workarea.workareabean.ScanResultBean;
import com.dhcc.nursepro.workarea.workareautils.WorkareaMainConfig;
import com.dhcc.nursepro.workarea.workareautils.WorkareaOperateDialog;
import com.dhcc.nursepro.workarea.workareautils.WorkareaOrderDialog;
import com.dhcc.nursepro.workarea.workareautils.WorkareaResultDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment {
    private RecyclerView recConfig;
    private WorkAreaAdapter workAreaAdapter;
    private WorkareaMainConfig workareaMainConfig = new WorkareaMainConfig();
    private ArrayList listMainConfig = new ArrayList();

    //广播相关
    private WorkAreaReceiver workAreaReceiver = new WorkAreaReceiver();
    private IntentFilter workAreaFilter = new IntentFilter();

    //功能扩展 医嘱执行相关数据
    private SPUtils spUtils = SPUtils.getInstance();
    private String episodeId = "";
    private String regNo = "";
    private String scanInfo = "";
    private String barCode = "";
    private String patInfo = "";
    private String patSaveInfo = "";
    private String scanPat = "";
    private String curOeordId = "";
    private ScanResultBean scanResultBean = new ScanResultBean();

    //医嘱执行弹窗
    private WorkareaOrderDialog orderDialog;
    private WorkareaOperateDialog operateDialog;
    private WorkareaResultDialog resultDialog;

    //医嘱执行提示音
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();
    private String locId = "";
    private String groupId = "";
    private String ordSpeed = "";
    private String reason = "";
    private String regNoByPat = "";
    private String regNoByOrd = "";

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (!(locId.equals(SPUtils.getInstance().getString(SharedPreference.LOCID)) && groupId.equals(SPUtils.getInstance().getString(SharedPreference.GROUPID)))) {
                initData();
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        hindMap();

        initView(view);
        initData();

        //提示音集合
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap.put(1, soundPool.load(getContext(), R.raw.notice22, 1));
    }

    private void initView(View view) {

        recConfig = view.findViewById(R.id.recy_workarea);
        recConfig.setHasFixedSize(true);
        recConfig.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        workAreaAdapter = new WorkAreaAdapter(new ArrayList<HashMap>());
        recConfig.setAdapter(workAreaAdapter);
        workAreaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    Map map = (Map) adapter.getData().get(position);
                    if (map.get("fragName")==null){
                        showToast("该功能暂未开发");
                    }else {
                        Class<? extends BaseFragment> OrderExecuteFragmentClass = (Class<? extends BaseFragment>) Class.forName(map.get("fragName").toString());
                        startFragment(OrderExecuteFragmentClass);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        locId = spUtils.getString(SharedPreference.LOCID);
        groupId = spUtils.getString(SharedPreference.GROUPID);
        WorkareaApiManager.getMainConfig(new WorkareaApiManager.GetMainconfigCallback() {
            @Override
            public void onSuccess(MainConfigBean mainConfigBean) {
                if (mainConfigBean.getSchStDateTime() != null && mainConfigBean.getSchEnDateTime() != null) {
                    SchDateTimeUtil.putSchStartEndDateTime(mainConfigBean.getSchStDateTime(),mainConfigBean.getSchEnDateTime());

                    if (StringUtils.isEmpty(mainConfigBean.getCurDateTime())) {
                        spUtils.put(SharedPreference.CURDATETIME, mainConfigBean.getSchEnDateTime());
                    } else {
                        spUtils.put(SharedPreference.CURDATETIME, mainConfigBean.getCurDateTime());
                    }
                }
                SharedPreference.FRAGMENTARY = new ArrayList();
                Map map = new HashMap();
                map.put("code","Main");
                map.put("desc","主页");
                map.put("fragName",WorkareaFragment.class.getName());
                map.put("fragicon",R.drawable.icon_workarea);
                SharedPreference.FRAGMENTARY.add(map);

                listMainConfig = workareaMainConfig.getMainCoinfgList(mainConfigBean);
                workAreaAdapter.setNewData(listMainConfig);
                SPUtils.getInstance().put(SharedPreference.BLOODSCANTIMES, mainConfigBean.getScantimes());
                DataCache.saveJson(mainConfigBean, SharedPreference.DATA_MAIN_CONFIG);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (workAreaReceiver != null) {
            workAreaFilter.addAction(Action.DEVICE_SCAN_CODE);
            Objects.requireNonNull(getContext()).registerReceiver(workAreaReceiver, workAreaFilter);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (workAreaReceiver != null) {
            Objects.requireNonNull(getContext()).unregisterReceiver(workAreaReceiver);
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea, container, false);
    }

    private void getScanInfo() {
        WorkareaApiManager.getScanMsgByMain(episodeId, curOeordId, scanInfo, new WorkareaApiManager.GetScanCallBack() {
            @Override
            public void onSuccess(ScanResultBean scanResultBeanFromWS) {
                scanResultBean = scanResultBeanFromWS;
                //提示信息
                String msg = scanResultBean.getMsg();

                //提示框
                if (orderDialog == null) {
                    orderDialog = new WorkareaOrderDialog(getActivity());

                    orderDialog.setSureOnclickListener(new WorkareaOrderDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            if ("exe".equals(orderDialog.getBtnType())) {
                                ordSpeed = orderDialog.getSpeed() + " " +orderDialog.getSpeedUnit();
                                execOrSeeOrderScan(patSaveInfo, orderDialog.getSttDateTime(), orderDialog.getArcimDesc(), orderDialog.getOrderId(), "F", orderDialog.getBedCode());
                                orderDialog.dismiss();
                            } else {
                                preparedVerifyOrd(orderDialog.getOrderId());
                                orderDialog.dismiss();
                            }

                        }
                    });

                    orderDialog.setCancelOnclickListener(new WorkareaOrderDialog.onCancelOnclickListener() {
                        @Override
                        public void onCancelClick() {
                            orderDialog.dismiss();
                        }
                    });

                    orderDialog.setTourOnclickListener(new WorkareaOrderDialog.onTourOnclickListener() {
                        @Override
                        public void onTourClick() {
                            operateDialog = new WorkareaOperateDialog(getActivity());
                            operateDialog.setIfImgReasonShow(View.GONE);
                            operateDialog.setPatInfo(orderDialog.getPatInfo());
                            operateDialog.setChildOrders(orderDialog.getChildOrders());
                            operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                            operateDialog.setViewVisibility(View.GONE, View.VISIBLE, View.VISIBLE, true);
                            operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                            operateDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                            ordSpeed = scanResultBean.getFlowSpeed() + "";
                            List ls = new ArrayList<String>();
                            if (scanResultBean.getSpeedUnitList() != null) {
                                for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                    ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                }
                            }
                            operateDialog.setSpiList(ls);
                            List lsReason = new ArrayList<String>();
                            if (scanResultBean.getTourNoteList() != null) {
                                for (int i = 0; i < scanResultBean.getTourNoteList().size(); i++) {
                                    lsReason.add(scanResultBean.getTourNoteList().get(i).getNoteData());
                                }
                            }
                            operateDialog.setSpiReList(lsReason);
                            operateDialog.setIfImgReasonShow(View.VISIBLE);
                            operateDialog.setOrderId(orderDialog.getOrderId());
                            operateDialog.setIfState(orderDialog.getIfState());

                            operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                @Override
                                public void onSureClick() {
                                    //                                        suspendOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemarkinfo());
                                    //                                        operateDialog.dismiss();
                                    ordSpeed = operateDialog.getSpeed() +" "+ operateDialog.getSpeedUnit();
                                    reason = operateDialog.getRemarkinfo();
                                    tourOrd(operateDialog.getOrderId());
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setCancelOnclickListener(new WorkareaOperateDialog.onCancelOnclickListener() {
                                @Override
                                public void onCancelClick() {
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setJp(orderDialog.getJp());
                            operateDialog.show();
                            orderDialog.dismiss();
                        }
                        //                            tourOrd(orderDialog.getOrderId());
                        //                            orderDialog.dismiss();
                    });

                    orderDialog.setSuspendContinueclickListenerOnclickListener(new WorkareaOrderDialog.onSuspendContinueclickListener() {
                        @Override
                        public void onSuspendContinueClick() {
                            if ("Suspend".equals(orderDialog.getIfState())) {
                                continueOrd(orderDialog.getOrderId());
                                orderDialog.dismiss();
                            } else {
                                operateDialog = new WorkareaOperateDialog(getActivity());
                                operateDialog.setPatInfo(orderDialog.getPatInfo());
                                operateDialog.setChildOrders(orderDialog.getChildOrders());
                                operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                                operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                                operateDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                                ordSpeed = scanResultBean.getFlowSpeed() + "";
                                List ls = new ArrayList<String>();
                                if (scanResultBean.getSpeedUnitList() != null) {
                                    for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                        ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                    }
                                }
                                operateDialog.setSpiList(ls);
                                List lsReason = new ArrayList<String>();
                                if (scanResultBean.getSuspendNoteList() != null) {
                                    for (int i = 0; i < scanResultBean.getSuspendNoteList().size(); i++) {
                                        lsReason.add(scanResultBean.getSuspendNoteList().get(i).getNoteData());
                                    }
                                }
                                operateDialog.setSpiReList(lsReason);
                                operateDialog.setIfImgReasonShow(View.VISIBLE);
                                operateDialog.setViewVisibility(View.GONE, View.VISIBLE, View.VISIBLE, true);
                                operateDialog.setOrderId(orderDialog.getOrderId());
                                operateDialog.setIfState(orderDialog.getIfState());

                                operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                    @Override
                                    public void onSureClick() {
                                        ordSpeed = operateDialog.getSpeed() +" "+ operateDialog.getSpeedUnit();
                                        suspendOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemarkinfo());
                                        operateDialog.dismiss();
                                    }
                                });
                                operateDialog.setCancelOnclickListener(new WorkareaOperateDialog.onCancelOnclickListener() {
                                    @Override
                                    public void onCancelClick() {
                                        operateDialog.dismiss();
                                    }
                                });
                                operateDialog.setJp(orderDialog.getJp());
                                operateDialog.show();
                                orderDialog.dismiss();
                            }
                        }
                    });


                    orderDialog.setStopOnclickListener(new WorkareaOrderDialog.onStopOnclickListener() {
                        @Override
                        public void onStopClick() {
                            operateDialog = new WorkareaOperateDialog(getActivity());
                            operateDialog.setPatInfo(orderDialog.getPatInfo());
                            operateDialog.setChildOrders(orderDialog.getChildOrders());
                            operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                            operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                            operateDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                            ordSpeed = scanResultBean.getFlowSpeed() + "";
                            List ls = new ArrayList<String>();
                            if (scanResultBean.getSpeedUnitList() != null) {
                                for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                    ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                }
                            }
                            operateDialog.setSpiList(ls);

                            List lsReason = new ArrayList<String>();
                            if (scanResultBean.getStopNoteList() != null) {
                                for (int i = 0; i < scanResultBean.getStopNoteList().size(); i++) {
                                    lsReason.add(scanResultBean.getStopNoteList().get(i).getNoteData());
                                }
                            }
                            operateDialog.setSpiReList(lsReason);
                            operateDialog.setIfImgReasonShow(View.VISIBLE);
                            operateDialog.setViewVisibility(View.VISIBLE, View.VISIBLE, View.VISIBLE, true);
                            operateDialog.setOrderId(orderDialog.getOrderId());
                            operateDialog.setIfState(orderDialog.getIfState());
                            operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                @Override
                                public void onSureClick() {
                                    ordSpeed = operateDialog.getSpeed() +" "+ operateDialog.getSpeedUnit();
                                    stopOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemainingliquid(), operateDialog.getRemarkinfo());
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setCancelOnclickListener(new WorkareaOperateDialog.onCancelOnclickListener() {
                                @Override
                                public void onCancelClick() {
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setJp(orderDialog.getJp());
                            operateDialog.show();
                            orderDialog.dismiss();
                        }
                    });

                    orderDialog.setEndOnclickListener(new WorkareaOrderDialog.onEndOnclickListener() {
                        @Override
                        public void onEndClick() {
                            operateDialog = new WorkareaOperateDialog(getActivity());
                            operateDialog.setPatInfo(orderDialog.getPatInfo());
                            operateDialog.setChildOrders(orderDialog.getChildOrders());
                            operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                            operateDialog.setIfImgReasonShow(View.GONE);
                            operateDialog.setViewVisibility(View.GONE, View.VISIBLE, View.VISIBLE, true);
                            operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                            operateDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                            ordSpeed = scanResultBean.getFlowSpeed() + "";
                            List ls = new ArrayList<String>();
                            if (scanResultBean.getSpeedUnitList() != null) {
                                for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                    ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                }
                            }
                            operateDialog.setSpiList(ls);
                            operateDialog.setOrderId(orderDialog.getOrderId());
                            operateDialog.setIfState(orderDialog.getIfState());

                            operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                @Override
                                public void onSureClick() {
                                    //                                        suspendOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemarkinfo());
                                    //                                        operateDialog.dismiss();
                                    ordSpeed = operateDialog.getSpeed() +" "+ operateDialog.getSpeedUnit();
                                    reason = operateDialog.getRemarkinfo();
                                    endOrd(operateDialog.getOrderId());
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setCancelOnclickListener(new WorkareaOperateDialog.onCancelOnclickListener() {
                                @Override
                                public void onCancelClick() {
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setJp(orderDialog.getJp());
                            operateDialog.show();
                            orderDialog.dismiss();
                        }
                    });

                    orderDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            episodeId = "";
                            scanInfo = "";
                            scanPat = "";
                            patSaveInfo = "";
                            curOeordId = "";
                            orderDialog = null;
                        }
                    });
                }

                //PAT 扫腕带返回患者信息
                //ORD 扫医嘱条码返回医嘱信息
                if ("PAT".equals(scanResultBean.getFlag())) {
                    scanPat = "1";
                    regNoByPat = scanResultBeanFromWS.getPatInfo().getRegNo();
                    if (orderDialog != null) {
                        orderDialog.setScanPat(scanPat);
                        orderDialog.setPopMsgInfo(scanResultBean.getMsg());
                    }
                    if ("104999".equals(scanResultBean.getMsgcode())) {

                        if (resultDialog != null && resultDialog.isShowing()) {
                            resultDialog.dismiss();
                        }
                        resultDialog = new WorkareaResultDialog(getActivity());
                        resultDialog.setExecresult(scanResultBean.getMsg() + "是否继续执行医嘱？");
                        resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                        resultDialog.setSureVisible(View.VISIBLE);
                        resultDialog.setCancleVisible(View.VISIBLE);
                        resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                            @Override
                            public void onSureClick() {
                                resultDialog.dismiss();
                                ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                                episodeId = patInfoBean.getEpisodeID();
                                regNo = patInfoBean.getRegNo();

                                patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                                patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                                orderDialog.setPatInfo(patInfo);
                                orderDialog.setPopMsgInfo(scanResultBean.getMsg());
                                orderDialog.setJp(View.GONE);
                                if (orderDialog != null && !orderDialog.isShowing()) {
                                    orderDialog.show();
                                }
                            }
                        });
                        resultDialog.setCancelOnclickListener(new WorkareaResultDialog.onCancelOnclickListener() {
                            @Override
                            public void onCancelClick() {
                                resultDialog.dismiss();
                            }
                        });
                        resultDialog.show();

                    } else {
                        ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                        episodeId = patInfoBean.getEpisodeID();
                        regNo = patInfoBean.getRegNo();
                        patInfo = "".equals(patInfoBean.getBedCode()) ? "未分床-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge() : patInfoBean.getBedCode().replace("床", "") + "床-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                        patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                        orderDialog.setPatInfo(patInfo);
                        orderDialog.setJp(View.GONE);
                        if (orderDialog != null && !orderDialog.isShowing()) {
                            orderDialog.show();
                        }
                    }

                } else {
                    barCode = scanInfo;
                    regNoByOrd = scanResultBean.getOrders().get(0).getRegNo();
                    if (resultDialog != null && resultDialog.isShowing()) {
                        resultDialog.dismiss();
                    }

                    if ("1".equals(scanResultBean.getDiagFlag())) {

                        List<ScanResultBean.OrdersBean> ordersBeanList = scanResultBean.getOrders();
                        ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                        orderDialog.setPatInfo(patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge());
                        ScanResultBean.OrdersBean ordersBean = ordersBeanList.get(0);
                        curOeordId = ordersBean.getID();
                        orderDialog.setChildOrders(ordersBeanList);
                        orderDialog.setPopMsgInfo(msg);
                        orderDialog.setCanExeFlag(scanResultBean.getCanExeFlag());

                        String orderInfoEx = ordersBean.getSttDateTime() + " " + ordersBean.getPhcinDesc() + " " + ordersBean.getCtcpDesc();
                        if ("Y".equals(ordersBean.getPreparedFlag())) {
                            orderInfoEx = orderInfoEx + "\n" + "配液： " + ordersBean.getPeiYeDate() + " " + ordersBean.getPeiYeTime() + " " + ordersBean.getPeiYeUser();
                        }
                        if ("Y".equals(ordersBean.getVerifyFlag())) {
                            orderInfoEx = orderInfoEx + "\n" + "复核： " + ordersBean.getFuHeDate() + " " + ordersBean.getFuHeTime() + " " + ordersBean.getFuHeUser();
                        }
                        if (ordersBean.getExecDateTime() != null && !ordersBean.getExecDateTime().equals("")&& ordersBean.getExecCtcpDesc()!=null&& !ordersBean.getExecCtcpDesc().equals("")){
                            orderInfoEx = orderInfoEx +"\n"+"执行："+ordersBean.getExecDateTime()+" "+ordersBean.getExecCtcpDesc();
                        }
                        orderDialog.setOrderInfoEx(orderInfoEx);

                        //功能区扫码 附加数据
                        orderDialog.setSttDateTime(ordersBean.getSttDateTime());
                        orderDialog.setArcimDesc(ordersBean.getArcimDesc());
                        orderDialog.setOrderId(ordersBean.getID());
                        orderDialog.setBedCode(ordersBean.getBedCode());
                        orderDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                        orderDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                        ordSpeed = scanResultBean.getFlowSpeed() + "";
                        List ls = new ArrayList<String>();
                        if (scanResultBean.getSpeedUnitList() != null) {
                            for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                            }
                        }
                        orderDialog.setSpiList(ls);
                        orderDialog.setBtnExecText(scanResultBean.getBtnDesc());
                        orderDialog.setBtnType(scanResultBean.getBtnType());
                        orderDialog.setIfState(scanResultBean.getIfState());

                        if (ordersBean.getFilteFlagExtend() != null && ordersBean.getFilteFlagExtend().equals("JP")){
                            orderDialog.setJp(View.VISIBLE);
                        }
                        if (orderDialog != null && !orderDialog.isShowing()) {
                            orderDialog.show();
                        }

                    } else {
                        resultDialog = new WorkareaResultDialog(getActivity());
                        resultDialog.setExecresult("扫码执行成功");
                        resultDialog.setImgId(R.drawable.icon_popup_sucess);
                        resultDialog.setSureVisible(View.GONE);
                        resultDialog.setCancleVisible(View.GONE);
                        resultDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                resultDialog.dismiss();
                            }
                        }, 1000);
                    }


                }
            }

            @Override
            public void onFail(String code, String msg) {

                playSound(1, 0);
                if (orderDialog != null && orderDialog.isShowing()) {
                    orderDialog.dismiss();
                }
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });

    }

    /**
     * 扫码执行
     */
    private void execOrSeeOrderScan(String execpatInfo, String creattime, String order, String oeoreIdScan, String execStatusCodeScan, String bedCode) {
        if (StringUtils.isEmpty(execpatInfo)) {
            if (resultDialog != null && resultDialog.isShowing()) {
                resultDialog.dismiss();
            }
            playSound(1, 0);
            resultDialog = new WorkareaResultDialog(getActivity());
            resultDialog.setExecresult("请扫描患者腕带");
            resultDialog.setImgId(R.drawable.icon_popup_error_patient);
            resultDialog.setSureVisible(View.VISIBLE);
            resultDialog.setCancleVisible(View.GONE);
            resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                @Override
                public void onSureClick() {
                    resultDialog.dismiss();
                }
            });
            resultDialog.show();
            return;
        } else if (!(regNoByOrd.replaceAll(" ","")).equals((regNoByPat.replaceAll(" ","")))) {
            regNoByOrd="";
            regNoByPat = "";
            if (resultDialog != null && resultDialog.isShowing()) {
                resultDialog.dismiss();
            }
            playSound(1, 0);
            resultDialog = new WorkareaResultDialog(getActivity());
            resultDialog.setExecresult("非此患者医嘱，请检查患者与医嘱对应关系");
            resultDialog.setImgId(R.drawable.icon_popup_error_patient);
            resultDialog.setSureVisible(View.VISIBLE);
            resultDialog.setCancleVisible(View.GONE);
            resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                @Override
                public void onSureClick() {
                    resultDialog.dismiss();
                }
            });
            resultDialog.show();
            return;
        }

        OrderExecuteApiManager.execOrSeeOrder(ordSpeed, barCode, creattime, order, execpatInfo, "1", "", "", "", oeoreIdScan, execStatusCodeScan, new OrderExecuteApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {
                barCode = "";
                regNoByOrd="";
                regNoByPat = "";

                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult("扫码执行成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                regNoByOrd="";
                regNoByPat = "";
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void preparedVerifyOrd(String orderId) {
        WorkareaApiManager.preparedVerifyOrd(orderId, "1", new WorkareaApiManager.PrepareVeriftyCallback() {
            @Override
            public void onSuccess(PreparedVerifyOrdBean preparedVerifyOrdBean) {

                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(preparedVerifyOrdBean.getMsg());
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void tourOrd(String orderId) {
        WorkareaApiManager.tourOrd(ordSpeed, reason, orderId, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult("巡视成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void continueOrd(String orderId) {
        WorkareaApiManager.continueOrd(ordSpeed, orderId, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult("继续成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void suspendOrd(String orderId, String infusionState, String infusionReason) {
        WorkareaApiManager.suspendOrd(ordSpeed, orderId, infusionState, infusionReason, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult("暂停成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void stopOrd(String orderId, String infusionState, String ResidualQty, String infusionReason) {
        WorkareaApiManager.stopOrd(ordSpeed, orderId, infusionState, ResidualQty, infusionReason, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult("停止成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void endOrd(String orderId) {
        WorkareaApiManager.endOrd(ordSpeed, reason, orderId, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult("结束成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    public void playSound(int sound, int loop) {
        if (getActivity()==null)return;
        AudioManager mgr = (AudioManager) getActivity().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        float volume = streamVolumeCurrent / streamVolumeMax;

        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);

        //参数：1、Map中取值   2、当前音量     3、最大音量  4、优先级   5、重播次数   6、播放速度

    }

    public class WorkAreaReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();

                scanInfo = bundle.getString("data");
                getScanInfo();
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
            }
        }
    }
}
