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
import com.dhcc.nursepro.workarea.orderexecute.OrderExecOrderDialog;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecResultDialog;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

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
    private List<String> ItemNameList = new ArrayList<String>();
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

    //医嘱执行弹窗
    private OrderExecOrderDialog execOrderDialog;
    private OrderExecResultDialog execResultDialog;

    //医嘱执行提示音
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        //        setToolbarType(BaseActivity.ToolbarType.TOP);
        //        setToolbarBottomLineVisibility(true);
        //        hideToolbarNavigationIcon();

        initView(view);
        initData();

        //提示音集合
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap.put(1, soundPool.load(getContext(), R.raw.notice22, 1));
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

    private void initView(View view) {

        recConfig = view.findViewById(R.id.recy_workarea);
        recConfig.setHasFixedSize(true);
        recConfig.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        patEventsAdapter = new WorkAreaAdapter(new ArrayList<String>());
        recConfig.setAdapter(patEventsAdapter);
        patEventsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick(ItemNameList.get(position) + "");
            }
        });
    }

    private void initData() {
        HashMap<String, String> properties = new HashMap<String, String>();
        WorkareaApiManager.getMainConfig(properties, "getMainConfig", new WorkareaApiManager.GetMainconfigCallback() {
            @Override
            public void onSuccess(MainConfigBean mainConfigBean) {
                ItemNameList = mainConfigBean.getMainConfig();
                //                ItemNameList.add("DRUGHANDOVER");
                //                ItemNameList.add("DRUGPREPARATION");
                //                ItemNameList.add("RLREG");
                //                ItemNameList.add("MODELDETAIL");
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
            default:

                break;
        }
    }

    public class WorkAreaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public WorkAreaAdapter(@Nullable List<String> data) {
            super(R.layout.item_workarea, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tvItem = helper.getView(R.id.tv_workarea);
            ImageView imageView = helper.getView(R.id.icon_workarea);
            switch (item) {
                case "BEDMAP":
                    tvItem.setText("床位图");
                    imageView.setImageResource(R.drawable.icon_bedmap);
                    break;
                case "VITALSIGN":
                    tvItem.setText("生命体征");
                    imageView.setImageResource(R.drawable.icon_vitalsign);
                    break;
                case "EVENTS":
                    tvItem.setText("事件登记");
                    imageView.setImageResource(R.drawable.icon_events);
                    break;
                case "ORDERSEARCH":
                    tvItem.setText("医嘱查询");
                    imageView.setImageResource(R.drawable.icon_orderserarch);
                    break;
                case "ORDEREXECUTE":
                    tvItem.setText("医嘱执行");
                    imageView.setImageResource(R.drawable.icon_orderexcute);
                    break;
                case "CHECK":
                    tvItem.setText("检查报告");
                    imageView.setImageResource(R.drawable.icon_check);
                    break;
                case "LAB":
                    tvItem.setText("检验结果");
                    imageView.setImageResource(R.drawable.icon_lab);
                    break;
                case "OPERATION":
                    tvItem.setText("手术查询");
                    imageView.setImageResource(R.drawable.icon_operation);
                    break;
                case "LABOUT":
                    tvItem.setText("检验打包");
                    imageView.setImageResource(R.drawable.icon_labout);
                    break;
                case "DOSINGREVIEW":
                    tvItem.setText("配液复核");
                    imageView.setImageResource(R.drawable.icon_dosingreview);
                    break;
                case "ALLOTBED":
                    tvItem.setText("入院分床");
                    imageView.setImageResource(R.drawable.icon_allotbed);
                    break;
                case "DOCORDERLIST":
                    tvItem.setText("医嘱单");
                    imageView.setImageResource(R.drawable.icon_docorderlist);
                    break;
                case "BLOOD":
                    tvItem.setText("输血系统");
                    imageView.setImageResource(R.drawable.icon_blood);
                    break;
                case "MILK":
                    tvItem.setText("母乳闭环");
                    imageView.setImageResource(R.drawable.icon_milk);
                    break;
                case "MOTHERBABYLINK":
                    tvItem.setText("母婴关联");
                    imageView.setImageResource(R.drawable.icon_motherbabylink);
                    break;
                case "MODELDETAIL":
                    tvItem.setText("护理病历");
                    imageView.setImageResource(R.drawable.icon_motherbabylink);
                    break;
                case "NURTOUR":
                    tvItem.setText("巡视");
                    imageView.setImageResource(R.drawable.icon_tour);
                    break;
                case "DRUGHANDOVER":
                    tvItem.setText("药品交接");
                    imageView.setImageResource(R.drawable.icon_drughandover);
                    break;
                case "DRUGPREPARATION":
                    tvItem.setText("取备用药");
                    imageView.setImageResource(R.drawable.icon_drugpreparation);
                    break;
                case "RLREG":
                    tvItem.setText("余液登记");
                    imageView.setImageResource(R.drawable.icon_drugrlreg);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);

        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            scanInfo = bundle.getString("data");
            barCode = bundle.getString("data");
            getScanInfo();
            if (execResultDialog != null && execResultDialog.isShowing()) {
                execResultDialog.dismiss();
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
                if (execResultDialog != null && execResultDialog.isShowing()) {
                    execResultDialog.dismiss();
                }
            }
        }
    }

    private void getScanInfo() {
        OrderExecuteApiManager.getScanMsgByMain(episodeId, scanInfo, new OrderExecuteApiManager.GetScanCallBack() {
            @Override
            public void onSuccess(ScanResultBean scanResultBean) {
                //提示信息
                String msg = scanResultBean.getMsg();

                //提示框
                if (execOrderDialog == null) {
                    execOrderDialog = new OrderExecOrderDialog(getActivity());

                    execOrderDialog.setSureOnclickListener(new OrderExecOrderDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            execOrSeeOrderScan(execOrderDialog.getSttDateTime(), execOrderDialog.getArcimDesc(), execOrderDialog.getOrderId(), "F", execOrderDialog.getBedCode());
                            execOrderDialog.dismiss();
                        }
                    });

                    execOrderDialog.setCancelOnclickListener(new OrderExecOrderDialog.onCancelOnclickListener() {
                        @Override
                        public void onCancelClick() {
                            execOrderDialog.dismiss();
                        }
                    });
                    execOrderDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            episodeId = "";
                            scanInfo = "";
                            execOrderDialog = null;
                        }
                    });
                }

                //PAT 扫腕带返回患者信息
                //ORD 扫医嘱条码返回医嘱信息
                if ("PAT".equals(scanResultBean.getFlag())) {

                    if ("104999".equals(scanResultBean.getMsgcode())) {

                        if (execResultDialog != null && execResultDialog.isShowing()) {
                            execResultDialog.dismiss();
                        }
                        execResultDialog = new OrderExecResultDialog(getActivity());
                        execResultDialog.setExecresult(scanResultBean.getMsg() + "是否继续执行医嘱？");
                        execResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                        execResultDialog.setSureVisible(View.VISIBLE);
                        execResultDialog.setCancleVisible(View.VISIBLE);
                        execResultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
                            @Override
                            public void onSureClick() {
                                execResultDialog.dismiss();
                                ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                                episodeId = patInfoBean.getEpisodeID();
                                regNo = patInfoBean.getRegNo();

                                patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                                patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                            }
                        });
                        execResultDialog.setCancelOnclickListener(new OrderExecResultDialog.onCancelOnclickListener() {
                            @Override
                            public void onCancelClick() {
                                execResultDialog.dismiss();
                            }
                        });
                        execResultDialog.show();

                    } else {
                        ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                        episodeId = patInfoBean.getEpisodeID();
                        regNo = patInfoBean.getRegNo();
                        patInfo = "".equals(patInfoBean.getBedCode()) ? "未分床-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge() : patInfoBean.getBedCode().replace("床", "") + "床-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                        patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                        execOrderDialog.setPatInfo(patInfo);
                        if (execOrderDialog != null && !execOrderDialog.isShowing()) {
                            execOrderDialog.show();
                        }
                    }

                } else {
                    barCode = scanInfo;
                    if (execResultDialog != null && execResultDialog.isShowing()) {
                        execResultDialog.dismiss();
                    }

                    if ("1".equals(scanResultBean.getDiagFlag())) {

                        List<ScanResultBean.OrdersBean> ordersBeanList = scanResultBean.getOrders();
                        ScanResultBean.OrdersBean ordersBean = ordersBeanList.get(0);
                        execOrderDialog.setChildOrders(ordersBeanList);
                        execOrderDialog.setPopMsgInfo(msg);
                        execOrderDialog.setCanExeFlag(scanResultBean.getCanExeFlag());
                        execOrderDialog.setOrderInfoEx(ordersBean.getSttDateTime() + " " + ordersBean.getPhcinDesc() + " " + ordersBean.getCtcpDesc() + "");

                        //功能区扫码 附加数据
                        execOrderDialog.setSttDateTime(ordersBean.getSttDateTime());
                        execOrderDialog.setArcimDesc(ordersBean.getArcimDesc());
                        execOrderDialog.setOrderId(ordersBean.getID());
                        execOrderDialog.setBedCode(ordersBean.getBedCode());

                        if (execOrderDialog != null && !execOrderDialog.isShowing()) {
                            execOrderDialog.show();
                        }

                    } else {
                        execResultDialog = new OrderExecResultDialog(getActivity());
                        execResultDialog.setExecresult("扫码执行成功");
                        execResultDialog.setImgId(R.drawable.icon_popup_sucess);
                        execResultDialog.setSureVisible(View.GONE);
                        execResultDialog.setCancleVisible(View.GONE);
                        execResultDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                execResultDialog.dismiss();
                            }
                        }, 1000);
                    }


                }
            }

            @Override
            public void onFail(String code, String msg) {

                playSound(1, 0);
                if (execOrderDialog != null && execOrderDialog.isShowing()) {
                    execOrderDialog.dismiss();
                }
                if (execResultDialog != null && execResultDialog.isShowing()) {
                    execResultDialog.dismiss();
                }
                execResultDialog = new OrderExecResultDialog(getActivity());
                execResultDialog.setExecresult(msg);
                execResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                execResultDialog.setSureVisible(View.VISIBLE);
                execResultDialog.setCancleVisible(View.GONE);
                execResultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        execResultDialog.dismiss();
                    }
                });
                execResultDialog.show();
            }
        });

    }

    /**
     * 扫码执行
     */
    private void execOrSeeOrderScan(String creattime, String order, String oeoreIdScan, String execStatusCodeScan, String bedCode) {
        if (StringUtils.isEmpty(patSaveInfo)) {
            if (execResultDialog != null && execResultDialog.isShowing()) {
                execResultDialog.dismiss();
            }
            playSound(1, 0);
            execResultDialog = new OrderExecResultDialog(getActivity());
            execResultDialog.setExecresult("请扫描患者腕带");
            execResultDialog.setImgId(R.drawable.icon_popup_error_patient);
            execResultDialog.setSureVisible(View.VISIBLE);
            execResultDialog.setCancleVisible(View.GONE);
            execResultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
                @Override
                public void onSureClick() {
                    execResultDialog.dismiss();
                }
            });
            execResultDialog.show();
            return;
        } else if (!patSaveInfo.split("-")[0].equals(bedCode)) {
            if (execResultDialog != null && execResultDialog.isShowing()) {
                execResultDialog.dismiss();
            }
            playSound(1, 0);
            execResultDialog = new OrderExecResultDialog(getActivity());
            execResultDialog.setExecresult("非此患者医嘱，请检查患者与医嘱对应关系");
            execResultDialog.setImgId(R.drawable.icon_popup_error_patient);
            execResultDialog.setSureVisible(View.VISIBLE);
            execResultDialog.setCancleVisible(View.GONE);
            execResultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
                @Override
                public void onSureClick() {
                    execResultDialog.dismiss();
                }
            });
            execResultDialog.show();
            return;
        }
        OrderExecuteApiManager.execOrSeeOrder(barCode, creattime, order, patSaveInfo, "1", "", "", "", oeoreIdScan, execStatusCodeScan, new OrderExecuteApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {
                barCode = "";

                if (execResultDialog != null && execResultDialog.isShowing()) {
                    execResultDialog.dismiss();
                }

                execResultDialog = new OrderExecResultDialog(getActivity());
                execResultDialog.setExecresult("扫码执行成功");
                execResultDialog.setImgId(R.drawable.icon_popup_sucess);
                execResultDialog.setSureVisible(View.GONE);
                execResultDialog.setCancleVisible(View.GONE);
                execResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        execResultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                playSound(1, 0);
                if (execResultDialog != null && execResultDialog.isShowing()) {
                    execResultDialog.dismiss();
                }
                execResultDialog = new OrderExecResultDialog(getActivity());
                execResultDialog.setExecresult(msg);
                execResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                execResultDialog.setSureVisible(View.VISIBLE);
                execResultDialog.setCancleVisible(View.GONE);
                execResultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        execResultDialog.dismiss();
                    }
                });
                execResultDialog.show();
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
}
