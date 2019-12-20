package com.dhcc.nursepro.workarea.orderexecute;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.base.BasePushDialog;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.utils.DialogFactory;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderExecuteOrderTypeAdapter;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderExecutePatientOrderAdapter;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;
import com.dhcc.nursepro.workarea.orderexecute.presenter.OrdExeFilterPresenter;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * OrderExecuteFragment
 * 医嘱执行/处理
 *
 * @author DevLix126
 * created at 2018/8/31 10:21
 */

public class OrderExecuteFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout rlOrderexecuteScan;

    private RecyclerView recyOrderexecuteOrdertype;
    private TextView tvOrderexecutePatinfo;
    private TextView tvOrderexecuteStartdatetime;
    private TextView tvOrderexecuteEnddatetime;
    private RecyclerView recyOrderexecutePatorder;
    private LinearLayout llOrderexecuteNoselectbottom;
    private LinearLayout llorderexecuteselectnum;
    private TextView tvBottomNoselecttext;
    private LinearLayout llOrderexecuteSelectbottom;
    private TextView tvBottomSelecttext;
    private TextView tvBottomHandletype;
    private View viewBottomHandleline;
    private ImageView imgBottomHandlesure;

    private String etChangeFlag;

    private List<OrderExecuteBean.ButtonsBean> buttons;
    private int selectCount;
    //    private List<OrderExecuteBean.OrdersBean> orders;
    private OrderExecuteBean.OrdersBean patient;
    private List<List<OrderExecuteBean.OrdersBean.PatOrdsBean>> patOrders;
    private List<OrderExecuteBean.SheetListBean> sheetList;
    private List<OrderExecuteBean.DetailColumsBean> detailColums;
    private OrderExecuteOrderTypeAdapter orderTypeAdapter;
    private OrderExecutePatientOrderAdapter patientOrderAdapter;

    private SPUtils spUtils = SPUtils.getInstance();
    private String scanInfo = "";
    private String barCode = "";
    //    private String regNo = "0000000129";
    private String regNo = "";
    private String sheetCode = "";
    private String sheetCodePresenter = "sheetCodePresenter";
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    private BasePushDialog basePushDialog;

    private String patInfo = "";
    private String patSaveInfo = "";
    private String orderInfo = "";
    private String orderInfoEx = "";
    private String skinBatch = "";
    private String skinUserCode = "";
    private String skinUserPass = "";

    private OrderExecOrderDialog execOrderDialog;

    private SkinResultOrderDialog skinResultOrderDialog;

    private OrderExecResultDialog execResultDialog;

    /**
     * 一个按钮可选择执行类型进行操作
     * 处理类型类型 A 接受R 拒绝S 完成   皮试结果 Y 阳性N 阴性
     */
    private String handleCode = "";

    /**
     * 医嘱id
     * 多个医嘱进行处理，接口依然调用execOrSeeOrder，第一个参数拼串，，每个医嘱的ID 用^分割
     * 11||1^12||1
     */
    private StringBuffer sbOeoreId;
    private String oeoreId = "";


    /**
     * 操作
     * execStatusCode F 执行，C 撤销执行，A 接受，S 完成，R 拒绝F 执行C 撤销执行A 接受R 拒绝S 完成N 阴性 Y 阳性""撤销处理 ST 皮试计时
     */
    private String execStatusCode;

    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();

    private String episodeId = "";
    private StringBuffer sbOrderSaveInfo;
    private String orderSaveInfo = "";
    private StringBuffer sbTimeSaveInfo;
    private String timeSaveInfo = "";
    private OrdExeFilterPresenter presenter;
    private String screenParts="";

    //处理类型
    public static List typelist = new ArrayList();
    private LinearLayout llBtn;
    //点击的按钮的名称
    private String exeButtonDesc = "";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_orderexecute), 0xffffffff, 17);

        Bundle bundle = getArguments();
        if (bundle != null) {
            scanInfo = bundle.getString("regNo");
            getScanInfo();
            if (execResultDialog != null && execResultDialog.isShowing()) {
                execResultDialog.dismiss();
            }
        }
        startDate = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
        startTime = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(11, 16);
        endDate = spUtils.getString(SharedPreference.SCHENDATETIME).substring(0, 10);
        endTime = spUtils.getString(SharedPreference.SCHENDATETIME).substring(11, 16);

        initView(view);
        initAdapter();

        //提示音集合
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap.put(1, soundPool.load(getContext(), R.raw.notice22, 1));
        //添加筛选
        presenter = new OrdExeFilterPresenter(this.getActivity());
        presenter.setOnSelectCallBackListener(new OrdExeFilterPresenter.OnSelectCallBackListener() {
            @Override
            public void onSelect(String s) {
                screenParts = s;
                asyncInitData();
            }
        });
    }

    private void getScanInfo() {

        OrderExecuteApiManager.getScanMsg(episodeId, scanInfo, new OrderExecuteApiManager.GetScanCallBack() {
            @Override
            public void onSuccess(ScanResultBean scanResultBean) {
                //提示信息
                String msg = scanResultBean.getMsg();
                //添加筛选
                setToolbarRightCustomView(presenter.getRightTextView());
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
                                tvOrderexecutePatinfo.setText("".equals(patInfoBean.getBedCode()) ? "未分床  " + patInfoBean.getName() : patInfoBean.getBedCode() + "  " + patInfoBean.getName());

                                patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                                patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                                rlOrderexecuteScan.setVisibility(View.GONE);
                                getView().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        asyncInitData();
                                    }
                                }, 300);
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
                        rlOrderexecuteScan.setVisibility(View.GONE);
                        ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                        episodeId = patInfoBean.getEpisodeID();
                        regNo = patInfoBean.getRegNo();
                        tvOrderexecutePatinfo.setText("".equals(patInfoBean.getBedCode()) ? "未分床  " + patInfoBean.getName() + "  " + patInfoBean.getSex() + "  " + patInfoBean.getAge() : patInfoBean.getBedCode().replace("床", "") + "床  " + patInfoBean.getName() + "  " + patInfoBean.getSex() + "  " + patInfoBean.getAge());
                        patInfo = "".equals(patInfoBean.getBedCode()) ? "未分床-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge() : patInfoBean.getBedCode().replace("床", "") + "床-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                        patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                        getView().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                asyncInitData();
                            }
                        }, 300);
                    }

                } else {
                    if (execResultDialog != null && execResultDialog.isShowing()) {
                        execResultDialog.dismiss();
                    }

                    if (execOrderDialog != null && execOrderDialog.isShowing()) {
                        execOrderDialog.dismiss();
                    }

                    if ("1".equals(scanResultBean.getDiagFlag())) {


                        execOrderDialog = new OrderExecOrderDialog(getActivity());
                        execOrderDialog.setPatInfo(patInfo);
                        List<ScanResultBean.OrdersBean> ordersBeanList = scanResultBean.getOrders();
                        ScanResultBean.OrdersBean ordersBean = ordersBeanList.get(0);
                        execOrderDialog.setChildOrders(ordersBeanList);
                        execOrderDialog.setPopMsgInfo(msg);
                        execOrderDialog.setCanExeFlag(scanResultBean.getCanExeFlag());
                        execOrderDialog.setOrderInfoEx(ordersBean.getSttDateTime() + " " + ordersBean.getPhcinDesc() + " " + ordersBean.getCtcpDesc() + "");
                        execOrderDialog.show();
                        execOrderDialog.setSureOnclickListener(new OrderExecOrderDialog.onSureOnclickListener() {
                            @Override
                            public void onSureClick() {
                                execOrderDialog.dismiss();
                                execOrSeeOrderScan(ordersBean.getSttDateTime(), ordersBean.getArcimDesc(), ordersBean.getID(), "F");
                            }
                        });

                        execOrderDialog.setCancelOnclickListener(new OrderExecOrderDialog.onCancelOnclickListener() {
                            @Override
                            public void onCancelClick() {
                                execOrderDialog.dismiss();
                            }
                        });
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
                                asyncInitData();
                            }
                        }, 1000);
                    }


                }
            }

            @Override
            public void onFail(String code, String msg) {

                //                if (episodeId == ""){
                //                    msg = "请先扫描病人腕带";
                //                }
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

    private void initView(View view) {

        llBtn = view.findViewById(R.id.ll_orderexecute_btn);

        rlOrderexecuteScan = view.findViewById(R.id.rl_orderexecute_scan);

        recyOrderexecuteOrdertype = view.findViewById(R.id.recy_orderexecute_ordertype);
        tvOrderexecutePatinfo = view.findViewById(R.id.tv_orderexecute_patinfo);
        tvOrderexecuteStartdatetime = view.findViewById(R.id.tv_orderexecute_startdatetime);
        tvOrderexecuteEnddatetime = view.findViewById(R.id.tv_orderexecute_enddatetime);
        recyOrderexecutePatorder = view.findViewById(R.id.recy_orderexecute_patorder);
        llOrderexecuteNoselectbottom = view.findViewById(R.id.ll_orderexecute_noselectbottom);
        llorderexecuteselectnum = view.findViewById(R.id.ll_orderexecute_selectnum);
        tvBottomNoselecttext = view.findViewById(R.id.tv_bottom_noselecttext);
        llOrderexecuteSelectbottom = view.findViewById(R.id.ll_orderexecute_selectbottom);
        tvBottomSelecttext = view.findViewById(R.id.tv_bottom_selecttext);
        tvBottomHandletype = view.findViewById(R.id.tv_bottom_handletype);
        viewBottomHandleline = view.findViewById(R.id.view_bottom_handleline);
        imgBottomHandlesure = view.findViewById(R.id.img_bottom_handlesure);


        tvOrderexecuteStartdatetime.setText(startDate + " " + startTime);
        tvOrderexecuteEnddatetime.setText(endDate + " " + endTime);

        tvOrderexecuteStartdatetime.setOnClickListener(this);
        tvOrderexecuteEnddatetime.setOnClickListener(this);
        tvBottomHandletype.setOnClickListener(this);

        //提高展示效率
        recyOrderexecuteOrdertype.setHasFixedSize(true);
        //设置的布局管理
        recyOrderexecuteOrdertype.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recyOrderexecutePatorder.setHasFixedSize(true);
        //设置的布局管理
        recyOrderexecutePatorder.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        orderTypeAdapter = new OrderExecuteOrderTypeAdapter(new ArrayList<>());
        orderTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                sheetCode = ((OrderExecuteBean.SheetListBean) adapter.getItem(position)).getCode();
                if (!sheetCode.equals(sheetCodePresenter)){
                    sheetCodePresenter = sheetCode;
                    presenter.setOrdTypeSelectedCode(sheetCode);
                    screenParts = "";
                }
                asyncInitData();

                //左侧刷新分类选中状态显示
                orderTypeAdapter.setSelectedCode(sheetCode);
                orderTypeAdapter.notifyDataSetChanged();
            }
        });
        recyOrderexecuteOrdertype.setAdapter(orderTypeAdapter);

        //        patientAdapter = new OrderExecutePatientAdapter(new ArrayList<OrderExecuteBean.OrdersBean>());
        //        recyOrderexecutePatorder.setAdapter(patientAdapter);
        patientOrderAdapter = new OrderExecutePatientOrderAdapter(new ArrayList<>());
        patientOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderExecuteBean.OrdersBean.PatOrdsBean patOrdsBean = patOrders.get(position).get(0);
                if (view.getId() == R.id.ll_oepat_orderselect) {
//                    if ("PSD".equals(sheetCode)) {
//                        llorderexecuteselectnum.setVisibility(View.GONE);
//                    } else {
//                        llorderexecuteselectnum.setVisibility(View.VISIBLE);
//                    }

                    if (patOrdsBean.getSelect() == null || "0".equals(patOrdsBean.getSelect()) || "".equals(patOrdsBean.getSelect())) {
                        patOrdsBean.setSelect("1");
                    } else {
                        patOrdsBean.setSelect("0");
                    }
                    patientOrderAdapter.notifyItemChanged(position);
                    if (llOrderexecuteSelectbottom.getVisibility() == View.VISIBLE || llOrderexecuteNoselectbottom.getVisibility() == View.VISIBLE) {
                        refreshBottom();
                    }

                }
            }
        });
        recyOrderexecutePatorder.setAdapter(patientOrderAdapter);

    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderExecuteApiManager.getOrder(regNo, sheetCode, startDate, startTime, endDate, endTime,screenParts, new OrderExecuteApiManager.GetOrderCallback() {
            @Override
            public void onSuccess(OrderExecuteBean orderExecuteBean) {
                hideLoadingTip();
                sheetList = orderExecuteBean.getSheetList();
                detailColums = orderExecuteBean.getDetailColums();
                buttons = orderExecuteBean.getButtons();
                //                orders = orderExecuteBean.getOrders();
                //                patientAdapter.setNewData(orders);
                llBtn.removeAllViews();
                viewBottomHandleline.setVisibility(View.GONE);
                tvBottomHandletype.setVisibility(View.GONE);
                imgBottomHandlesure.setVisibility(View.GONE);

                for (int i = 0; i <buttons.size() ; i++) {
                    if (buttons.get(i).getExecode()!=null && buttons.get(i).getExecode().contains("|")){
                        typelist = new ArrayList();
                        String[] stype = buttons.get(i).getExecode().split("\\^");
                        for (int j = 0; j <stype.length ; j++) {
                            typelist.add(stype[j]);
                        };
                        viewBottomHandleline.setVisibility(View.VISIBLE);
                        tvBottomHandletype.setVisibility(View.VISIBLE);
                        String deftype = typelist.get(0).toString();
                        tvBottomHandletype.setText(deftype.substring(deftype.indexOf("|")+1,deftype.length()));
                        imgBottomHandlesure.setVisibility(View.VISIBLE);
                        handleCode = deftype.substring(0,deftype.indexOf("|"));
                    }
                    TextView tvButton = new TextView(getContext());
                    tvButton.setText(buttons.get(i).getDesc());
                    //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                if (config.getItemDesc().length() > 7) {
//                    titleTV.setTextSize(12);
//                } else {
//                    titleTV.setTextSize(16);
//                }
                    tvButton.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ConvertUtils.dp2px(90), ViewGroup.LayoutParams.MATCH_PARENT);
                    titleParams.setMargins(0, 0, 0, 0);//4个参数按顺序分别是左上右下
                    tvButton.setLayoutParams(titleParams);
                    if (i%2 == 0){
                        tvButton.setBackgroundResource(R.color.blue);
                    }else {
                        tvButton.setBackgroundResource(R.color.blue_dark);
                    }
                    tvButton.setTextColor(getResources().getColor(R.color.white));
                    if (buttons.get(i).getExecode()!=null){
                        tvButton.setTag(R.string.key_execode,buttons.get(i).getExecode());
                    }else {
                        //非配置按钮--皮试计时
                        tvButton.setTag(R.string.key_execode,"UNKNOW");
                    }
                    tvButton.setTag(R.string.key_singleflag,buttons.get(i).getSingleFlag());


                    tvButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            exeButtonDesc = tvButton.getText().toString();
                            String strexecode = tvButton.getTag(R.string.key_execode).toString();
                            String strSingleFlag = tvButton.getTag(R.string.key_singleflag).toString();
                            if (strexecode.equals("UNKNOW")){
                                showToast("未配置按钮，不可操作");
                            }else {
                                if (strexecode.contains("|")){
                                    execStatusCode = handleCode;
                                }else {
                                    execStatusCode = strexecode;
                                }
                                if ("PSD".equals(sheetCode) && exeButtonDesc.contains("皮试")) {
                                    //跟处理医嘱相同方式置皮试结果
                                    if (strexecode.contains("|")){
                                        execOrSeeOrder();
                                    }else {
                                        //弹框置皮试结果
                                        if (oeoreId.split("\\^").length > 1) {
                                            showToast("皮试结果只能逐一设置，请选择单条医嘱执行");
                                        } else {
                                            showSkinResultOrderDialog(strSingleFlag);
                                        }
                                    }
                                } else {
                                    execOrSeeOrder();
                                }
                            }
                        }
                    });

                    llBtn.addView(tvButton);

                }

                if (buttons.size() == 0) {
                    llOrderexecuteNoselectbottom.setVisibility(View.GONE);
                    llOrderexecuteSelectbottom.setVisibility(View.GONE);
                } else {
                    llOrderexecuteNoselectbottom.setVisibility(View.VISIBLE);
                    llOrderexecuteSelectbottom.setVisibility(View.GONE);
                    tvBottomNoselecttext.setText("请选择医嘱");
                }

                if (orderExecuteBean.getOrders().size() > 0) {
                    patient = orderExecuteBean.getOrders().get(0);
                    //                    tvOrderexecutePatinfo.setText("".equals(patient.getBedCode()) ? "未分" : patient.getBedCode() + "  " + patient.getName());
                    patOrders = patient.getPatOrds();
                } else {
                    patOrders = null;
                }
                if (patOrders != null) {
                    patientOrderAdapter.setSize(patOrders.size());
                    patientOrderAdapter.setDetailColums(detailColums);
                }
                patientOrderAdapter.setNewData(patOrders);
                patientOrderAdapter.loadMoreEnd();
                recyOrderexecutePatorder.scrollToPosition(0);

                //左侧列表判断有无默认值，有的话滑动到默认值类型
                if (!"".equals(orderExecuteBean.getSheetDefCode())) {
                    if (!sheetCodePresenter.equals(orderExecuteBean.getSheetDefCode())){
                        sheetCodePresenter  = orderExecuteBean.getSheetDefCode();
                        presenter.setOrdTypeSelectedCode(orderExecuteBean.getSheetDefCode());
                        screenParts = "";
                    }
                    orderTypeAdapter.setSelectedCode(orderExecuteBean.getSheetDefCode());
                }
                orderTypeAdapter.setNewData(sheetList);

                if (!"".equals(orderExecuteBean.getSheetDefCode())) {
                    for (int i = 0; i < sheetList.size(); i++) {
                        if (sheetList.get(i).getCode().equals(orderExecuteBean.getSheetDefCode())) {
                            recyOrderexecuteOrdertype.scrollToPosition(i);
                            break;
                        }
                    }
                }

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                patientOrderAdapter.loadMoreFail();
                showToast("error" + code + ":" + msg);
            }
        });

    }

    /**
     * 扫码执行
     */
    private void execOrSeeOrderScan(String creattime, String order, String oeoreIdScan, String execStatusCodeScan) {
        OrderExecuteApiManager.execOrSeeOrder(barCode,creattime, order, patSaveInfo, "1", "", "", "", oeoreIdScan, execStatusCodeScan, new OrderExecuteApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {

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
                        asyncInitData();
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

    public void refreshBottom() {
        sbOeoreId = new StringBuffer();
        sbOrderSaveInfo = new StringBuffer();
        sbTimeSaveInfo = new StringBuffer();
        selectCount = 0;
        for (int i = 0; i < patOrders.size(); i++) {

            String orderDescs = "";
            for (int j = 0; j < patOrders.get(i).size(); j++) {
                if (j == patOrders.get(i).size() - 1) {
                    orderDescs = orderDescs + patOrders.get(i).get(j).getOrderInfo().getArcimDesc();
                } else {
                    orderDescs = orderDescs + patOrders.get(i).get(j).getOrderInfo().getArcimDesc() + "\n";
                }
            }
            if (patOrders.get(i) != null && patOrders.get(i).get(0) != null && patOrders.get(i).get(0).getSelect() != null && "1".equals(patOrders.get(i).get(0).getSelect())) {
                if (selectCount == 0) {
                    sbOeoreId.append(patOrders.get(i).get(0).getOrderInfo().getID());
                    //                    sbOrderSaveInfo.append(patOrders.get(i).get(0).getOrderInfo().getArcimDesc());
                    sbOrderSaveInfo.append(orderDescs);
                    sbTimeSaveInfo.append(patOrders.get(i).get(0).getOrderInfo().getSttDateTime());
                } else {
                    sbOeoreId.append("^" + patOrders.get(i).get(0).getOrderInfo().getID());
                    //                    sbOrderSaveInfo.append("^" + patOrders.get(i).get(0).getOrderInfo().getArcimDesc());
                    sbOrderSaveInfo.append("^" + orderDescs);
                    sbTimeSaveInfo.append("^" + patOrders.get(i).get(0).getOrderInfo().getSttDateTime());
                }
                selectCount++;
            }
        }
        oeoreId = sbOeoreId.toString();
        orderSaveInfo = sbOrderSaveInfo.toString();
        timeSaveInfo = sbTimeSaveInfo.toString();
        if (selectCount == 0) {
            llOrderexecuteNoselectbottom.setVisibility(View.VISIBLE);
            llOrderexecuteSelectbottom.setVisibility(View.GONE);
            tvBottomNoselecttext.setText("请选择医嘱");

        } else {
            llOrderexecuteNoselectbottom.setVisibility(View.GONE);
            llOrderexecuteSelectbottom.setVisibility(View.VISIBLE);
            tvBottomSelecttext.setText("已选择" + selectCount + "个");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_orderexecute_startdatetime:
                etChangeFlag = "START";
                chooseTime(TimeUtils.string2Millis(tvOrderexecuteStartdatetime.getText().toString() + ":00"));
                break;
            case R.id.tv_orderexecute_enddatetime:
                etChangeFlag = "END";
                chooseTime(TimeUtils.string2Millis(tvOrderexecuteEnddatetime.getText().toString() + ":00"));
                break;
            case R.id.tv_bottom_handletype:
                    basePushDialog = showPushDialog(new OrderHandleTypeFragment());
                break;
            default:
                break;
        }
    }

    private void chooseTime(long currentTimeMillis) {
        DateUtils.chooseDateTime(currentTimeMillis,getContext(), getFragmentManager(), new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                    String date = TimeUtils.millis2String(millseconds).substring(0, 10);
                    String time = TimeUtils.millis2String(millseconds).substring(11, 16);

                    if ("START".equals(etChangeFlag)) {
                        if (!date.equals(startDate) || !time.equals(startTime)) {
                            //日期时间发生改变，需重新请求数据
                            startDate = date;
                            startTime = time;
                            asyncInitData();
                        }
                        tvOrderexecuteStartdatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
                    } else {
                        if (!date.equals(endDate) || !time.equals(endTime)) {
                            //日期时间发生改变，需重新请求数据
                            endDate = date;
                            endTime = time;
                            asyncInitData();
                        }
                        tvOrderexecuteEnddatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
                    }
            }
        });

    }

    private void execOrSeeOrder() {
        OrderExecuteApiManager.execOrSeeOrder("",timeSaveInfo, orderSaveInfo, patSaveInfo, "0", skinBatch, skinUserCode, skinUserPass, oeoreId, execStatusCode, new OrderExecuteApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {
                skinBatch = "";
                skinUserCode = "";
                skinUserPass = "";
                /**
                 * 操作
                 * execStatusCode (F 执行，C 撤销执行，A 接受，S 完成，R 拒绝)F 执行Y 皮试阳性N 皮试阴性* C 撤销执行A 接受R 拒绝S 完成""撤销处理
                 */
                if (execResultDialog != null && execResultDialog.isShowing()) {
                    execResultDialog.dismiss();
                }

                execResultDialog = new OrderExecResultDialog(getActivity());
                execResultDialog.setExecresult("手动"+exeButtonDesc+"成功");
                execResultDialog.setImgId(R.drawable.icon_popup_sucess);
                execResultDialog.setSureVisible(View.GONE);
                execResultDialog.setCancleVisible(View.GONE);
                execResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        execResultDialog.dismiss();
                        asyncInitData();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                playSound(1, 0);
                skinBatch = "";
                skinUserCode = "";
                skinUserPass = "";
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
     * 皮试单-点击'皮试结果'/'皮试计时'
     */
    private void showSkinResultOrderDialog(String skinSinge) {
//        String s = f(R.id.tv_bottom_todo, TextView.class).getText().toString();
        if (!TextUtils.isEmpty(exeButtonDesc) && exeButtonDesc.contains("计时")) {
            // 皮试计时
            DialogFactory.showCountTime(getActivity(), new DialogFactory.CommClickListener() {
                @Override
                public void data(Object[] args) {
                    super.data(args);
                    doSkinTime((String) args[0], (String) args[1]);
                }
            });
            return;
        }

        if (skinResultOrderDialog != null && skinResultOrderDialog.isShowing()) {
            skinResultOrderDialog.dismiss();
        }
        skinResultOrderDialog = new SkinResultOrderDialog(getActivity());
        skinResultOrderDialog.setPatInfo(patInfo);
        skinResultOrderDialog.setSingleFlag(skinSinge);
        skinResultOrderDialog.show();
        skinResultOrderDialog.setSureOnclickListener(new SkinResultOrderDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                execStatusCode = skinResultOrderDialog.getSkinResult();
                skinBatch = skinResultOrderDialog.getSkinNum();
                skinUserCode = skinResultOrderDialog.getNurName();
                skinUserPass = skinResultOrderDialog.getNurPass();
                String userId = spUtils.getString(SharedPreference.USERCODE);
                if (skinUserCode.equals(userId)) {
                    showToast("请其他护士进行双签");
                } else {
                    skinResultOrderDialog.dismiss();
                    execOrSeeOrder();
                }
            }
        });

        skinResultOrderDialog.setCancelOnclickListener(new SkinResultOrderDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                skinResultOrderDialog.dismiss();
            }
        });
    }

    private void doSkinTime(String observeTime, String note) {
        OrderExecuteApiManager.skinTime(oeoreId, observeTime, note, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                DialogFactory.showCommDialog(getActivity(), bean.getMsg(), null, 0, null, true);
                asyncInitData();//刷新
            }
        });
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.ORDER_HANDLE_TYPE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String bundleDesc=bundle.getString("handledesc");
            String budleCode=bundle.getString("handlecode");
            basePushDialog.dismiss();
            tvBottomHandletype.setText(bundleDesc);
            handleCode = budleCode;
        }
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

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_execute, container, false);
    }
}
