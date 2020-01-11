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
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
import com.dhcc.nursepro.workarea.labout.LabOutListFragment;
import com.dhcc.nursepro.workarea.labresult.LabPatsFragment;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.MilkLoopSystemFragment;
import com.dhcc.nursepro.workarea.motherbabylink.MotherBabyLinkFragment;
import com.dhcc.nursepro.workarea.nurrecordold.PatNurRecordFragment;
import com.dhcc.nursepro.workarea.nurtour.NurTourFragment;
import com.dhcc.nursepro.workarea.operation.OperationFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.shift.ShiftFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;
import com.dhcc.nursepro.workarea.workareabean.OperateResultBean;
import com.dhcc.nursepro.workarea.workareabean.PreparedVerifyOrdBean;
import com.dhcc.nursepro.workarea.workareabean.ScanResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment {
    private RecyclerView recConfig;
    private List<MainConfigBean.MainListBean> ItemNameList = new ArrayList<MainConfigBean.MainListBean>();
    private WorkAreaAdapter patEventsAdapter;

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

    //医嘱执行弹窗
    private WorkareaOrderDialog orderDialog;
    private WorkareaOperateDialog operateDialog;
    private WorkareaResultDialog resultDialog;

    //医嘱执行提示音
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();
    private String locId = "";
    private String groupId = "";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);

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
        patEventsAdapter = new WorkAreaAdapter(new ArrayList<MainConfigBean.MainListBean>());
        recConfig.setAdapter(patEventsAdapter);
        patEventsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick(ItemNameList.get(position).getModuleCode() + "");
            }
        });
    }

    private void initData() {
        locId = spUtils.getString(SharedPreference.LOCID);
        groupId = spUtils.getString(SharedPreference.GROUPID);
        WorkareaApiManager.getMainConfig(new WorkareaApiManager.GetMainconfigCallback() {
            @Override
            public void onSuccess(MainConfigBean mainConfigBean) {
                if(mainConfigBean.getSchStDateTime() != null && mainConfigBean.getSchEnDateTime() != null){
                    spUtils.put(SharedPreference.SCHSTDATETIME, mainConfigBean.getSchStDateTime());
                    spUtils.put(SharedPreference.SCHENDATETIME, mainConfigBean.getSchEnDateTime());
                }
                ItemNameList = mainConfigBean.getMainList();
                patEventsAdapter.setNewData(ItemNameList);
                SPUtils.getInstance().put(SharedPreference.BLOODSCANTIMES, mainConfigBean.getScantimes());
                DataCache.saveJson(mainConfigBean, SharedPreference.DATA_MAIN_CONFIG);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    /**
     * MainConfig
     * ("BEDMAP");//床位图
     * ("VITALSIGN");//生命体征
     * ("EVENTS");//事件管理
     * ("ORDERSEARCH");//医嘱查询
     * ("ORDEREXECUTE");//医嘱执行
     * ("CHECK");//检查报告
     * ("LAB");//检验报告
     * ("OPERATION");//手术申请
     * ("LABOUT");//检验打包
     * ("DOSINGREVIEW");//输液复核
     * ("ALLOTBED");//入院分床
     * ("DOCORDERLIST");//医嘱单
     * ("BLOOD");//输血系统
     * ("MILK");//母乳闭环
     * ("MOTHERBABYLINK");//母婴关联
     * ("MODELDETAIL");//护理病历
     * ("NURTOUR");//巡视
     * ("DRUGHANDOVER");//药品交接
     * ("DRUGPREPARATION");//取备用药
     * ("RLREG");//余液登记
     */
    public void itemClick(String itemName) {
        switch (itemName) {
            case "BEDMAP":
                startFragment(BedMapFragment.class);
                break;
            case "VITALSIGN":
                startFragment(VitalSignFragment.class);
                break;
            case "EVENTS":
                startFragment(PatEventsFragment.class);
                break;
            case "ORDERSEARCH":
                startFragment(OrderSearchFragment.class);
                break;
            case "ORDEREXECUTE":
                startFragment(OrderExecuteFragment.class);
                break;
            case "CHECK":
                startFragment(CheckPatsFragment.class);
                break;
            case "LAB":
                startFragment(LabPatsFragment.class);
                break;
            case "OPERATION":
                startFragment(OperationFragment.class);
                break;
            case "LABOUT":
                startFragment(LabOutListFragment.class);
                break;
            case "DOSINGREVIEW":
                startFragment(DosingReviewFragment.class);
                break;
            case "ALLOTBED":
                startFragment(AllotBedFragment.class);
                break;
            case "DOCORDERLIST":
                startFragment(DocOrderListFragment.class);
                break;
            case "BLOOD":
                startFragment(BloodTransfusionSystemFragment.class);
                break;
            case "MILK":
                startFragment(MilkLoopSystemFragment.class);
                break;
            case "MOTHERBABYLINK":
                startFragment(MotherBabyLinkFragment.class);
                break;
            case "MODELDETAIL":
                //                startFragment(NurRecordModellistFragmen.class);
                startFragment(PatNurRecordFragment.class);
                break;
            case "NURTOUR":
                startFragment(NurTourFragment.class);
                break;
            case "DRUGHANDOVER":
                startFragment(DrugHandoverFragment.class);
                break;
            case "DRUGPREPARATION":
                startFragment(DrugPreparationFragment.class);
                break;
            case "RLREG":
                startFragment(RLRegFragment.class);
                break;
            case "SHIFT":
                startFragment(ShiftFragment.class);
                break;
            default:
                break;
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (!(locId.equals(SPUtils.getInstance().getString(SharedPreference.LOCID)) && groupId.equals(SPUtils.getInstance().getString(SharedPreference.GROUPID)))){
                initData();
            }
        }
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
            public void onSuccess(ScanResultBean scanResultBean) {
                //提示信息
                String msg = scanResultBean.getMsg();

                //提示框
                if (orderDialog == null) {
                    orderDialog = new WorkareaOrderDialog(getActivity());

                    orderDialog.setSureOnclickListener(new WorkareaOrderDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            if ("exe".equals(orderDialog.getBtnType())) {
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
                                operateDialog.setPatInfo(orderDialog.getPatInfo());
                                operateDialog.setChildOrders(orderDialog.getChildOrders());
                                operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                                operateDialog.setViewVisibility(View.GONE, View.VISIBLE,View.VISIBLE,true);

                                operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit());
                                if (scanResultBean.getOrders().size()>0){
                                    operateDialog.setSpeed(scanResultBean.getOrders().get(0).getSpeedFlowRate());
                                }
                                List ls = new ArrayList<String>();
                                for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                    ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                }
                                operateDialog.setSpiList(ls);
                                operateDialog.setOrderId(orderDialog.getOrderId());
                                operateDialog.setIfState(orderDialog.getIfState());

                                operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                    @Override
                                    public void onSureClick() {
//                                        suspendOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemarkinfo());
//                                        operateDialog.dismiss();
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
                                if (scanResultBean.getOrders().size()>0){
                                    operateDialog.setSpeed(scanResultBean.getOrders().get(0).getSpeedFlowRate());
                                }
                                List ls = new ArrayList<String>();
                                for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                    ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                }
                                operateDialog.setSpiList(ls);
                                operateDialog.setViewVisibility(View.GONE, View.VISIBLE, View.VISIBLE,false);
                                operateDialog.setOrderId(orderDialog.getOrderId());
                                operateDialog.setIfState(orderDialog.getIfState());

                                operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                    @Override
                                    public void onSureClick() {
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
                            operateDialog.setViewVisibility(View.VISIBLE, View.VISIBLE,View.GONE,true);
                            operateDialog.setOrderId(orderDialog.getOrderId());
                            operateDialog.setIfState(orderDialog.getIfState());
                            operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                @Override
                                public void onSureClick() {
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
                            operateDialog.show();
                            orderDialog.dismiss();
                        }
                    });

                    orderDialog.setEndOnclickListener(new WorkareaOrderDialog.onEndOnclickListener() {
                        @Override
                        public void onEndClick() {
                            endOrd(orderDialog.getOrderId());
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
                    if (orderDialog != null) {
                        orderDialog.setScanPat(scanPat);
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
                        if (orderDialog != null && !orderDialog.isShowing()) {
                            orderDialog.show();
                        }
                    }

                } else {
                    barCode = scanInfo;
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
                        orderDialog.setOrderInfoEx(orderInfoEx);

                        //功能区扫码 附加数据
                        orderDialog.setSttDateTime(ordersBean.getSttDateTime());
                        orderDialog.setArcimDesc(ordersBean.getArcimDesc());
                        orderDialog.setOrderId(ordersBean.getID());
                        orderDialog.setBedCode(ordersBean.getBedCode());
                        orderDialog.setBtnType(scanResultBean.getBtnType());
                        orderDialog.setIfState(scanResultBean.getIfState());

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
        } else if (!execpatInfo.split("-")[0].equals(bedCode)) {
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

        OrderExecuteApiManager.execOrSeeOrder(barCode, creattime, order, execpatInfo, "1", "", "", "", oeoreIdScan, execStatusCodeScan, new OrderExecuteApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {
                barCode = "";

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
        WorkareaApiManager.tourOrd(orderId, new WorkareaApiManager.OperateResultCallBack() {
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
        WorkareaApiManager.continueOrd(orderId, new WorkareaApiManager.OperateResultCallBack() {
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
        WorkareaApiManager.suspendOrd(orderId, infusionState, infusionReason, new WorkareaApiManager.OperateResultCallBack() {
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
        WorkareaApiManager.stopOrd(orderId, infusionState, ResidualQty, infusionReason, new WorkareaApiManager.OperateResultCallBack() {
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
        WorkareaApiManager.endOrd(orderId, new WorkareaApiManager.OperateResultCallBack() {
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

        AudioManager mgr = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        float volume = streamVolumeCurrent / streamVolumeMax;

        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);

        //参数：1、Map中取值   2、当前音量     3、最大音量  4、优先级   5、重播次数   6、播放速度

    }

    public class WorkAreaAdapter extends BaseQuickAdapter<MainConfigBean.MainListBean, BaseViewHolder> {
        public WorkAreaAdapter(@Nullable List<MainConfigBean.MainListBean> data) {
            super(R.layout.item_workarea, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MainConfigBean.MainListBean item) {
            TextView tvItem = helper.getView(R.id.tv_workarea);
            ImageView imageView = helper.getView(R.id.icon_workarea);
            switch (item.getModuleCode()) {
                case "BEDMAP":
                    tvItem.setText("床位图");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_bedmap);
                    break;
                case "VITALSIGN":
                    tvItem.setText("生命体征");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_vitalsign);
                    break;
                case "EVENTS":
                    tvItem.setText("事件登记");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_events);
                    break;
                case "ORDERSEARCH":
                    tvItem.setText("医嘱查询");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_orderserarch);
                    break;
                case "ORDEREXECUTE":
                    tvItem.setText("医嘱执行");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_orderexcute);
                    break;
                case "CHECK":
                    tvItem.setText("检查报告");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_check);
                    break;
                case "LAB":
                    tvItem.setText("检验结果");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_lab);
                    break;
                case "OPERATION":
                    tvItem.setText("手术查询");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_operation);
                    break;
                case "LABOUT":
                    tvItem.setText("检验打包");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_labout);
                    break;
                case "DOSINGREVIEW":
                    tvItem.setText("配液复核");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_dosingreview);
                    break;
                case "ALLOTBED":
                    tvItem.setText("入院分床");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_allotbed);
                    break;
                case "DOCORDERLIST":
                    tvItem.setText("医嘱单");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_docorderlist);
                    break;
                case "BLOOD":
                    tvItem.setText("输血系统");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_blood);
                    break;
                case "MILK":
                    tvItem.setText("母乳闭环");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_milk);
                    break;
                case "MOTHERBABYLINK":
                    tvItem.setText("母婴关联");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_motherbabylink);
                    break;
                case "MODELDETAIL":
                    tvItem.setText("护理病历");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_motherbabylink);
                    break;
                case "NURTOUR":
                    tvItem.setText("巡视");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_tour);
                    break;
                case "DRUGHANDOVER":
                    tvItem.setText("药品交接");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_drughandover);
                    break;
                case "DRUGPREPARATION":
                    tvItem.setText("取备用药");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_drugpreparation);
                    break;
                case "RLREG":
                    tvItem.setText("余液登记");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_drugrlreg);
                    break;
                case "SHIFT":
                    tvItem.setText("交班本");
                    tvItem.setText(item.getModuleDesc());
                    imageView.setImageResource(R.drawable.icon_drugrlreg);
                    break;
                default:
                    break;
            }
        }
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
