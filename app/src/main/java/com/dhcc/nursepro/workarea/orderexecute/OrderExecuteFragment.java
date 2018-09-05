package com.dhcc.nursepro.workarea.orderexecute;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.UniversalActivity;
import com.dhcc.nursepro.common.BasePushDialog;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderExecuteOrderTypeAdapter;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderExecutePatientOrderAdapter;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * OrderExecuteFragment
 * 医嘱执行/处理
 *
 * @author DevLix126
 * created at 2018/8/31 10:21
 */

public class OrderExecuteFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {
    private RelativeLayout rlOrderexecuteScan;
    private TextView tvOrderexecuteWarning;
    private TextView tvOrderexecuteWarningtitle;

    private RecyclerView recyOrderexecuteOrdertype;
    private TextView tvOrderexecutePatinfo;
    private TextView tvOrderexecuteStartdatetime;
    private TextView tvOrderexecuteEnddatetime;
    private RecyclerView recyOrderexecutePatorder;
    private LinearLayout llOrderexecuteNoselectbottom;
    private TextView tvBottomNoselecttext;
    private LinearLayout llOrderexecuteSelectbottom;
    private TextView tvBottomSelecttext;
    private TextView tvBottomHandletype;
    private TextView tvBottomUndo;
    private TextView tvBottomTodo;

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
    private String regNo = "0000000129";
    private String sheetCode = "";
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    private BasePushDialog basePushDialog;
    private Receiver mReceiver = new Receiver();

    private OrderExecResultDialog execResultDialog;

    /**
     * 医嘱处理/医嘱执行
     * 0 处理
     * 1 执行
     */
    private int exectype = 0;

    /**
     * 执行类型
     * <p>
     * A 接受
     * R 拒绝
     * S 完成
     */
    private String handleCode = "A";

    /**
     * 医嘱id
     * 多个医嘱进行处理，接口依然调用execOrSeeOrder，第一个参数拼串，，每个医嘱的ID 用^分割
     * 11||1^12||1
     */
    private StringBuffer sbOeoreId;
    private String oeoreId = "";


    /**
     * 操作
     * execStatusCode (F 执行，C 撤销执行，A 接受，S 完成，R 拒绝)
     * <p>
     * F 执行
     * C 撤销执行
     * A 接受
     * R 拒绝
     * S 完成
     * ""撤销处理
     */
    private String execStatusCode;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_orderexecute), 0xffffffff, 17);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Action.ORDER_HANDLE_ACCEPT);
        filter.addAction(Action.ORDER_HANDLE_REFUSE);
        filter.addAction(Action.ORDER_HANDLE_COMPLETE);
        getActivity().registerReceiver(mReceiver, filter);

        startDate = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
        startTime = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(11, 16);
        endDate = spUtils.getString(SharedPreference.SCHENDATETIME).substring(0, 10);
        endTime = spUtils.getString(SharedPreference.SCHENDATETIME).substring(11, 16);

        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);
    }

    private void initView(View view) {
        rlOrderexecuteScan = view.findViewById(R.id.rl_orderexecute_scan);
        tvOrderexecuteWarning = view.findViewById(R.id.tv_orderexecute_warning);
        tvOrderexecuteWarningtitle = view.findViewById(R.id.tv_orderexecute_warningtitle);

        recyOrderexecuteOrdertype = view.findViewById(R.id.recy_orderexecute_ordertype);
        tvOrderexecutePatinfo = view.findViewById(R.id.tv_orderexecute_patinfo);
        tvOrderexecuteStartdatetime = view.findViewById(R.id.tv_orderexecute_startdatetime);
        tvOrderexecuteEnddatetime = view.findViewById(R.id.tv_orderexecute_enddatetime);
        recyOrderexecutePatorder = view.findViewById(R.id.recy_orderexecute_patorder);
        llOrderexecuteNoselectbottom = view.findViewById(R.id.ll_orderexecute_noselectbottom);
        tvBottomNoselecttext = view.findViewById(R.id.tv_bottom_noselecttext);
        llOrderexecuteSelectbottom = view.findViewById(R.id.ll_orderexecute_selectbottom);
        tvBottomSelecttext = view.findViewById(R.id.tv_bottom_selecttext);
        tvBottomHandletype = view.findViewById(R.id.tv_bottom_handletype);
        tvBottomUndo = view.findViewById(R.id.tv_bottom_undo);
        tvBottomTodo = view.findViewById(R.id.tv_bottom_todo);

        tvOrderexecuteStartdatetime.setText(startDate + " " + startTime);
        tvOrderexecuteEnddatetime.setText(endDate + " " + endTime);

        tvOrderexecuteStartdatetime.setOnClickListener(this);
        tvOrderexecuteEnddatetime.setOnClickListener(this);
        tvBottomHandletype.setOnClickListener(this);
        tvBottomUndo.setOnClickListener(this);
        tvBottomTodo.setOnClickListener(this);

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
        orderTypeAdapter = new OrderExecuteOrderTypeAdapter(new ArrayList<OrderExecuteBean.SheetListBean>());
        orderTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                sheetCode = ((OrderExecuteBean.SheetListBean) adapter.getItem(position)).getCode();
                asyncInitData();

                //左侧刷新分类选中状态显示
                orderTypeAdapter.setSelectedPostion(position);
                orderTypeAdapter.notifyDataSetChanged();
            }
        });
        recyOrderexecuteOrdertype.setAdapter(orderTypeAdapter);

        //        patientAdapter = new OrderExecutePatientAdapter(new ArrayList<OrderExecuteBean.OrdersBean>());
        //        recyOrderexecutePatorder.setAdapter(patientAdapter);
        patientOrderAdapter = new OrderExecutePatientOrderAdapter(new ArrayList<List<OrderExecuteBean.OrdersBean.PatOrdsBean>>());
        patientOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderExecuteBean.OrdersBean.PatOrdsBean patOrdsBean = patOrders.get(position).get(0);
                if (view.getId() == R.id.ll_oepat_orderselect) {
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
        OrderExecuteApiManager.getOrder(regNo, sheetCode, startDate, startTime, endDate, endTime, new OrderExecuteApiManager.GetOrderCallback() {
            @Override
            public void onSuccess(OrderExecuteBean orderExecuteBean) {
                hideLoadingTip();
                sheetList = orderExecuteBean.getSheetList();
                detailColums = orderExecuteBean.getDetailColums();
                buttons = orderExecuteBean.getButtons();
                //                orders = orderExecuteBean.getOrders();
                //                patientAdapter.setNewData(orders);

                if (buttons.size() == 0) {
                    llOrderexecuteNoselectbottom.setVisibility(View.GONE);
                    llOrderexecuteSelectbottom.setVisibility(View.GONE);
                } else {
                    llOrderexecuteNoselectbottom.setVisibility(View.VISIBLE);
                    llOrderexecuteSelectbottom.setVisibility(View.GONE);
                    if (buttons.get(0).getDesc().contains("处理")) {
                        tvBottomNoselecttext.setText("请您选择需处理的医嘱");
                        exectype = 0;
                    } else {
                        tvBottomNoselecttext.setText("请您选择需执行的医嘱");
                        exectype = 1;
                    }
                }

                if (orderExecuteBean.getOrders().size() > 0) {
                    patient = orderExecuteBean.getOrders().get(0);
                    tvOrderexecutePatinfo.setText("".equals(patient.getBedCode()) ? "未分" : patient.getBedCode() + "床  " + patient.getName());
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
                orderTypeAdapter.setNewData(sheetList);

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                patientOrderAdapter.loadMoreFail();
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void refreshBottom() {
        Toast.makeText(getActivity(), "111", Toast.LENGTH_SHORT).show();
        sbOeoreId = new StringBuffer();
        selectCount = 0;
        for (int i = 0; i < patOrders.size(); i++) {
            if (patOrders.get(i) != null && patOrders.get(i).get(0) != null && patOrders.get(i).get(0).getSelect() != null && "1".equals(patOrders.get(i).get(0).getSelect())) {
                if (selectCount == 0) {
                    sbOeoreId.append(patOrders.get(i).get(0).getOrderInfo().getID());
                } else {
                    sbOeoreId.append("^" + patOrders.get(i).get(0).getOrderInfo().getID());
                }
                selectCount++;
            }
        }
        oeoreId = sbOeoreId.toString();

        if (selectCount == 0) {
            llOrderexecuteNoselectbottom.setVisibility(View.VISIBLE);
            llOrderexecuteSelectbottom.setVisibility(View.GONE);
            if (buttons.get(0).getDesc().contains("执行")) {
                tvBottomNoselecttext.setText("请您选择需执行的医嘱");
            } else {
                tvBottomNoselecttext.setText("请您选择需处理的医嘱");
            }
        } else {
            llOrderexecuteNoselectbottom.setVisibility(View.GONE);
            llOrderexecuteSelectbottom.setVisibility(View.VISIBLE);
            tvBottomSelecttext.setText("已选择" + selectCount + "个");
            if (buttons.size() == 1) {
                tvBottomUndo.setVisibility(View.GONE);
                tvBottomTodo.setVisibility(View.VISIBLE);
                tvBottomTodo.setText(buttons.get(0).getDesc().replace("医嘱", ""));
                if (exectype == 0) {
                    execStatusCode = "A";
                } else {
                    execStatusCode = "";
                }
            } else {
                tvBottomUndo.setVisibility(View.VISIBLE);
                tvBottomUndo.setText(buttons.get(1).getDesc().replace("医嘱", ""));
                tvBottomTodo.setVisibility(View.VISIBLE);
                tvBottomTodo.setText(buttons.get(0).getDesc().replace("医嘱", ""));
            }

        }


    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_orderexecute_startdatetime:
                etChangeFlag = "START";
                chooseTime();

                break;
            case R.id.tv_orderexecute_enddatetime:
                etChangeFlag = "END";
                chooseTime();
                break;
            case R.id.tv_bottom_handletype:
                basePushDialog = showPushDialog(new OrderHandleTypeFragment());
                break;
            case R.id.tv_bottom_undo:
                if (exectype == 0) {
                    execStatusCode = "";
                } else {
                    execStatusCode = "C";
                }
                execOrSeeOrder();
                break;
            case R.id.tv_bottom_todo:
                if (exectype == 0) {
                    execStatusCode = handleCode;
                } else {
                    execStatusCode = "F";
                }
                execOrSeeOrder();
                break;
            default:
                break;
        }
    }

    private void chooseTime() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    private void execOrSeeOrder() {
        OrderExecuteApiManager.ExecOrSeeOrder(oeoreId, execStatusCode, new OrderExecuteApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {
                /**
                 * 操作
                 * execStatusCode (F 执行，C 撤销执行，A 接受，S 完成，R 拒绝)
                 * <p>
                 * F 执行
                 * C 撤销执行
                 * A 接受
                 * R 拒绝
                 * S 完成
                 * ""撤销处理
                 */
                execResultDialog = new OrderExecResultDialog(getActivity());
                switch (execStatusCode) {
                    case "F":
                        execResultDialog.setExecresult("执行成功");
                        break;
                    case "C":
                        execResultDialog.setExecresult("撤销执行成功");
                        break;
                    case "A":
                    case "R":
                    case "S":
                        execResultDialog.setExecresult("处理成功");
                        break;
                    case "":
                        execResultDialog.setExecresult("撤销处理成功");
                        break;
                    default:
                        break;
                }
                execResultDialog.setImgId(R.drawable.icon_popup_sucess);
                execResultDialog.setSureVisible(View.GONE);
                execResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        execResultDialog.dismiss();
                        asyncInitData();
                    }
                }, 2000);
            }

            @Override
            public void onFail(String code, String msg) {
                execResultDialog = new OrderExecResultDialog(getActivity());
                execResultDialog.setExecresult(msg);
                execResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                execResultDialog.setSureVisible(View.VISIBLE);
                execResultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        execResultDialog.dismiss();
                    }
                });
                execResultDialog.show();
//                Toast.makeText(getActivity(), code+":"+msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPreFinish(UniversalActivity activity) {
        super.onPreFinish(activity);
        activity.unregisterReceiver(mReceiver);
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_execute, container, false);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case Action.ORDER_HANDLE_ACCEPT:
                    basePushDialog.dismiss();
                    tvBottomHandletype.setText("接受");
                    handleCode = "A";
                    break;
                case Action.ORDER_HANDLE_REFUSE:
                    basePushDialog.dismiss();
                    tvBottomHandletype.setText("拒绝");
                    handleCode = "R";
                    break;
                case Action.ORDER_HANDLE_COMPLETE:
                    basePushDialog.dismiss();
                    tvBottomHandletype.setText("完成");
                    handleCode = "S";
                    break;
                default:
                    break;
            }
        }
    }
}
