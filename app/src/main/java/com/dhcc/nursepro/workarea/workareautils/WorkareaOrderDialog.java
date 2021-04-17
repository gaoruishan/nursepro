package com.dhcc.nursepro.workarea.workareautils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.workareaadapter.OrderExecuteOrderDialogAdapter;
import com.dhcc.nursepro.workarea.workareabean.ScanResultBean;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * OrderExecOrderDialog
 * 创建自定义的dialog
 *
 * @author Devlix126
 * created at 2018/12/25 10:00
 */
public class WorkareaOrderDialog extends Dialog {
    private Context context;

    private TextView tvPopupPatinfo;
    private TextView tvJp;
    private LinearLayout llMsg;
    private TextView tvPopupMsg;
    private TextView tvPopupOrderinfo;
    private TextView tvPopupOrderunit;
    private LinearLayout llSpiSpeed;
    private Spinner spiSpeed;
    private EditText etSpeed;
    private LinearLayout llWay;
    private FlowLayout flWay;
    private RecyclerView recyPopupChildOrderInfo;
    private TextView tvPopupOrderinfoex;
    private LinearLayout llExbtn;
    private TextView tvPopupOrderExec;
    private TextView tvPopupOrderCancel;
    private LinearLayout llExbtn1;
    private TextView tvPopupOrderTour;
    private TextView tvPopupOrderSuspendContinue;
    private LinearLayout llExbtn2;
    private TextView tvPopupOrderStop;
    private TextView tvPopupOrderEnd;
    private ImageView imgCancle;


    private String patInfo = "";
    private String orderInfo = "";
    private String orderUnit = "";
    private List<String> spiList = new ArrayList<>();
    private String speedUnit = "";
    private String speed = "";
    private List<ScanResultBean.WayNoList> wayNoList = new ArrayList<>();
    private HashMap<String, View> wayCheckMap = new HashMap<>();
    private String wayNo = "";
    private int ifJpShow = View.GONE;
    private List<ScanResultBean.OrdersBean> childOrders = new ArrayList<>();
    private String orderInfoEx = "";
    private String msgInfo = "";
    private String canExeFlag = "";
    //功能区扫码 附加数据
    private String scanPat = "";
    private String bedCode = "";
    private String sttDateTime = "";
    private String arcimDesc = "";
    private String orderId = "";
    private String btnExecText = "";
    private String btnSuspendContinueText = "";
    private String btnType = "";
    private String ifState = "";

    private OrderExecuteOrderDialogAdapter adapter;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;
    private onTourOnclickListener tourOnclickListener;
    private onSuspendContinueclickListener suspendContinueclickListener;
    private onStopOnclickListener stopOnclickListener;
    private onEndOnclickListener endOnclickListener;

    public WorkareaOrderDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getSttDateTime() {
        return sttDateTime;
    }

    public void setSttDateTime(String sttDateTime) {
        this.sttDateTime = sttDateTime;
    }

    public String getArcimDesc() {
        return arcimDesc;
    }

    public void setArcimDesc(String arcimDesc) {
        this.arcimDesc = arcimDesc;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
        if (!StringUtils.isEmpty(patInfo) && tvPopupPatinfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
        if (!StringUtils.isEmpty(orderInfo) && tvPopupOrderinfo != null) {
            tvPopupOrderinfo.setText(orderInfo);
        }
    }

    public String getOrderUnit() {
        return orderUnit;
    }

    public void setOrderUnit(String orderUnit) {
        this.orderUnit = orderUnit;
        if (!StringUtils.isEmpty(orderUnit) && tvPopupOrderunit != null) {
            tvPopupOrderunit.setText(orderUnit);
        }
    }

    public String getSpeed() {
        if (etSpeed != null) {
            return etSpeed.getText().toString();
        } else {
            return speed;
        }
    }

    public void setSpeed(String speed) {
        this.speed = speed;
        if (etSpeed != null) {
            etSpeed.setText(speed);
        }

    }

    public String getSpeedUnit() {
        return speedUnit;
    }

    public void setSpeedUnit(String speed) {
        this.speedUnit = speed;
    }

    public List getSpiList() {
        return spiList;
    }

    public void setSpiList(List spiList) {
        this.spiList = spiList;
        if (spiSpeed != null && spiList.size() > 0) {
//            List ls = new ArrayList<String>();
//            ls.add("滴/秒");
//            ls.add("滴/分");
            ArrayAdapter arr_adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, spiList);
            //设置样式
            arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spiSpeed.setAdapter(arr_adapter);
            if (spiList.size() > 0) {
                spiSpeed.setSelection(0);
            }

            for (int i = 0; i < spiList.size(); i++) {
                if (speedUnit.equals(spiList.get(i))) {
                    spiSpeed.setSelection(i);
                }
            }
            spiSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    spiSpeed.setSelection(position);
                    speedUnit = spiList.get(position) + "";
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    public void setWayNoList(List<ScanResultBean.WayNoList> wayNoList) {
        this.wayNoList = wayNoList;
    }

    public String getWayNo() {
        return wayNo;
    }

    public int getJp() {
        return ifJpShow;
    }

    public void setJp(int ifJpShow) {
        this.ifJpShow = ifJpShow;
        if (tvJp != null) {
            tvJp.setVisibility(ifJpShow);
        }
    }

    public List<ScanResultBean.OrdersBean> getChildOrders() {
        return childOrders;
    }

    public void setChildOrders(List<ScanResultBean.OrdersBean> childOrders) {
        this.childOrders = childOrders;
        if (childOrders != null && childOrders.size() > 0 && recyPopupChildOrderInfo != null && adapter != null) {
            adapter.setNewData(childOrders);
        }
    }

    public String getOrderInfoEx() {
        return orderInfoEx;
    }

    public void setOrderInfoEx(String orderInfoEx) {
        this.orderInfoEx = orderInfoEx;
        if (!StringUtils.isEmpty(orderInfoEx) && tvPopupOrderinfoex != null) {
            tvPopupOrderinfoex.setText(orderInfoEx);
        }
    }

    public void setPopMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
        if (!StringUtils.isEmpty(msgInfo) && llMsg != null && tvPopupMsg != null) {
            llMsg.setVisibility(View.VISIBLE);
            tvPopupMsg.setText(msgInfo);
        }
    }

    public void setBtnExecText(String btnExecText) {
        this.btnExecText = btnExecText;
        if (!StringUtils.isEmpty(btnExecText) && tvPopupOrderExec != null) {
            tvPopupOrderExec.setText(btnExecText);
        }
    }

    public void setBtnSuspendContinueText(String btnSuspendContinueText) {
        this.btnSuspendContinueText = btnSuspendContinueText;
        if (!StringUtils.isEmpty(btnSuspendContinueText) && tvPopupOrderSuspendContinue != null) {
            tvPopupOrderSuspendContinue.setText(btnSuspendContinueText);
        }
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;

        if (!StringUtils.isEmpty(btnType) && tvPopupOrderExec != null && llExbtn1 != null && llExbtn2 != null && llSpiSpeed != null && llWay != null) {
            if (!btnExecText.equals("")) {
                llExbtn.setVisibility(View.VISIBLE);
                llExbtn1.setVisibility(View.GONE);
                llExbtn2.setVisibility(View.GONE);
                tvPopupOrderExec.setText(btnExecText);
            }
            llSpiSpeed.setVisibility(View.GONE);
            llWay.setVisibility(View.GONE);
            switch (btnType) {
                case "peiye":
                    llExbtn.setVisibility(View.VISIBLE);
                    llExbtn1.setVisibility(View.GONE);
                    llExbtn2.setVisibility(View.GONE);
                    tvPopupOrderExec.setText("配液");
                    break;
                case "fuhe":
                    llExbtn.setVisibility(View.VISIBLE);
                    llExbtn1.setVisibility(View.GONE);
                    llExbtn2.setVisibility(View.GONE);
                    tvPopupOrderExec.setText("复核");
                    break;
                case "exe":
                    llExbtn.setVisibility(View.VISIBLE);
                    llExbtn1.setVisibility(View.GONE);
                    llExbtn2.setVisibility(View.GONE);
                    tvPopupOrderExec.setText("执行");
                    llSpiSpeed.setVisibility(View.VISIBLE);
                    llWay.setVisibility(View.VISIBLE);
                    break;
                case "exed":
                    llExbtn.setVisibility(View.GONE);
                    llExbtn1.setVisibility(View.VISIBLE);
                    llExbtn2.setVisibility(View.VISIBLE);
                    tvPopupOrderExec.setText("已执行");
                    break;
                default:
                    break;
            }

        }
        setBtnExec(scanPat, canExeFlag);
    }

    private void setBtnExec(String scanPat, String canExeFlag) {
        Log.e("TAG", "(WorkareaOrderDialog.java:355) " + scanPat + "-" + canExeFlag + "-" + btnType);
        if (tvPopupOrderExec != null) {

            switch (btnType) {
                case "peiye":
                case "fuhe":
                    tvPopupOrderExec.setEnabled(true);
                    tvPopupOrderExec.setClickable(true);
                    tvPopupOrderExec.setBackgroundResource(R.drawable.bg_dialog_sure);
                    break;
                case "exe":
                    if ("1".equals(scanPat) && "1".equals(canExeFlag)) {
                        tvPopupOrderExec.setEnabled(true);
                        tvPopupOrderExec.setClickable(true);
                        tvPopupOrderExec.setBackgroundResource(R.drawable.bg_dialog_sure);
                        tvPopupMsg.setText(tvPopupMsg.getText().toString().replace(",请扫描患者腕带", ""));
                    } else {
                        tvPopupOrderExec.setEnabled(false);
                        tvPopupOrderExec.setClickable(false);
                        tvPopupOrderExec.setBackgroundResource(R.drawable.bg_dialog_unclick);
//                        tvPopupMsg.setText(msgInfo+",请扫描患者腕带");
                        tvPopupMsg.setText(msgInfo + "");
                    }
                    break;
                case "exed":
                    tvPopupOrderExec.setEnabled(false);
                    tvPopupOrderExec.setClickable(false);
                    tvPopupOrderExec.setBackgroundResource(R.drawable.bg_dialog_unclick);
                    break;
                default:

                    break;
            }
        }


    }

    public String getIfState() {
        return ifState;
    }

    public void setIfState(String ifState) {
        this.ifState = ifState;
        if (!StringUtils.isEmpty(ifState) && llExbtn1 != null && tvPopupOrderSuspendContinue != null) {
            switch (ifState) {
                case "Suspend":
                    tvPopupOrderSuspendContinue.setText("继续");
                    break;
                default:
                    tvPopupOrderSuspendContinue.setText("暂停");
                    break;
            }
        }
    }

    public void setScanPat(String scanPat) {
        this.scanPat = scanPat;
        setBtnExec(scanPat, canExeFlag);
    }

    public void setCanExeFlag(String canExeFlag) {
        this.canExeFlag = canExeFlag;
        setBtnExec(scanPat, canExeFlag);
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    /**
     * 设置取消按钮的显示内容和监听
     */
    public void setCancelOnclickListener(onCancelOnclickListener onCancelOnclickListener) {

        this.cancelOnclickListener = onCancelOnclickListener;
    }

    /**
     * 设置巡视按钮的显示内容和监听
     */
    public void setTourOnclickListener(onTourOnclickListener onTourOnclickListener) {

        this.tourOnclickListener = onTourOnclickListener;
    }

    /**
     * 设置暂停/继续按钮的显示内容和监听
     */
    public void setSuspendContinueclickListenerOnclickListener(onSuspendContinueclickListener onSuspendContinueclickListener) {

        this.suspendContinueclickListener = onSuspendContinueclickListener;
    }

    /**
     * 设置停止按钮的显示内容和监听
     */
    public void setStopOnclickListener(onStopOnclickListener onStopOnclickListener) {

        this.stopOnclickListener = onStopOnclickListener;
    }

    /**
     * 设置结束按钮的显示内容和监听
     */
    public void setEndOnclickListener(onEndOnclickListener onEndOnclickListener) {

        this.endOnclickListener = onEndOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workareaorder_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化adapter
        initAdapter();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        tvJp = findViewById(R.id.tv_jp);
        tvPopupPatinfo = findViewById(R.id.tv_popup_patinfo);
        llMsg = findViewById(R.id.ll_msg);
        tvPopupMsg = findViewById(R.id.tv_popup_msg);
        tvPopupOrderinfo = findViewById(R.id.tv_popup_orderinfo);
        tvPopupOrderunit = findViewById(R.id.tv_popup_orderunit);
        llSpiSpeed = findViewById(R.id.ll_speed);
        spiSpeed = findViewById(R.id.sp_speedunit);
        etSpeed = findViewById(R.id.et_speed);
        llWay = findViewById(R.id.ll_way);
        flWay = findViewById(R.id.fl_way);
        recyPopupChildOrderInfo = findViewById(R.id.recy_popup_childOrderInfo);
        tvPopupOrderinfoex = findViewById(R.id.tv_popup_orderinfoex);
        llExbtn = findViewById(R.id.ll_exbtn);
        tvPopupOrderExec = findViewById(R.id.tv_popup_order_exec);
        tvPopupOrderCancel = findViewById(R.id.tv_popup_order_cancel);
        llExbtn1 = findViewById(R.id.ll_exbtn1);
        tvPopupOrderTour = findViewById(R.id.tv_popup_order_tour);
        tvPopupOrderSuspendContinue = findViewById(R.id.tv_popup_order_suspend_continue);
        llExbtn2 = findViewById(R.id.ll_exbtn2);
        tvPopupOrderStop = findViewById(R.id.tv_popup_order_stop);
        tvPopupOrderEnd = findViewById(R.id.tv_popup_order_end);
        imgCancle = findViewById(R.id.img_cancle);
        imgCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //提高展示效率
        recyPopupChildOrderInfo.setHasFixedSize(true);
        //设置的布局管理
        recyPopupChildOrderInfo.setLayoutManager(new LinearLayoutManager(context));

    }

    private void initAdapter() {
        adapter = new OrderExecuteOrderDialogAdapter(new ArrayList<>());
        recyPopupChildOrderInfo.setAdapter(adapter);
    }

    private void initData() {

        if (!StringUtils.isEmpty(patInfo)) {
            tvPopupPatinfo.setText(patInfo);
        }

        if (!StringUtils.isEmpty(orderInfo)) {
            tvPopupOrderinfo.setText(orderInfo);
        }

        if (!StringUtils.isEmpty(orderUnit)) {
            tvPopupOrderunit.setText(orderUnit);
        }

        if (childOrders != null && childOrders.size() > 0) {
            adapter.setNewData(childOrders);
        }

        if (!StringUtils.isEmpty(orderInfoEx)) {
            tvPopupOrderinfoex.setText(orderInfoEx);
        }

        if (!StringUtils.isEmpty(msgInfo)) {
            llMsg.setVisibility(View.VISIBLE);
            tvPopupMsg.setText(msgInfo);
        }

        tvJp.setVisibility(ifJpShow);

        if (spiSpeed != null && spiList.size() > 0) {
//            List ls = new ArrayList<String>();
//            ls.add("滴/秒");
//            ls.add("滴/分");
            ArrayAdapter<String> arr_adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, spiList);
            //设置样式
            arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spiSpeed.setAdapter(arr_adapter);
            if (spiList.size() > 0) {
                spiSpeed.setSelection(0);
            }

            for (int i = 0; i < spiList.size(); i++) {
                if (speedUnit.equals(spiList.get(i))) {
                    spiSpeed.setSelection(i);
                }
            }
            spiSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    spiSpeed.setSelection(position);
                    speedUnit = spiList.get(position) + "";
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if (etSpeed != null) {
            etSpeed.setText(speed);
            etSpeed.setTextColor(Color.parseColor("#4A4A4A"));

//            boolean ifSpeedEdit = true;
//            etSpeed.setEnabled(ifSpeedEdit);
//            spiSpeed.setEnabled(ifSpeedEdit);
//            spiSpeed.setSelected(ifSpeedEdit);
//            if (ifSpeedEdit){
//                etSpeed.setTextColor(Color.parseColor("#4A4A4A"));
//            }else {
//                etSpeed.setTextColor(Color.parseColor("#9B9B9B"));
//            }
        }

        if (wayNoList != null && wayNoList.size() > 0) {
            for (int i = 0; i < wayNoList.size(); i++) {
                CheckBox checkBox = new CheckBox(context);
                checkBox.setText(wayNoList.get(i).getWayNum());
                checkBox.setTag(wayNoList.get(i).getWayNo());
                wayCheckMap.put(String.valueOf(checkBox.getId()), checkBox);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            for (View checkBox1 : wayCheckMap.values()) {
                                if (checkBox1 instanceof CheckBox) {
                                    if (buttonView.getId() == checkBox1.getId()) {
//                                        ((CheckBox) checkBox1).setChecked(true);
                                        wayNo = checkBox1.getTag().toString();
                                    } else {
                                        ((CheckBox) checkBox1).setChecked(false);
                                    }
                                }
                            }

                        } else {
                            wayNo = "";
                        }
                    }
                });
                flWay.addView(checkBox);
                if (i == wayNoList.size() - 1) {
                    checkBox.setChecked(true);
                }
            }

        }

        //执行按钮文字变更
        llSpiSpeed.setVisibility(View.GONE);
        llWay.setVisibility(View.GONE);
        if (!btnExecText.equals("")) {
            llExbtn.setVisibility(View.VISIBLE);
            llExbtn1.setVisibility(View.GONE);
            llExbtn2.setVisibility(View.GONE);
            tvPopupOrderExec.setText(btnExecText);
        }
        switch (btnType) {
            case "peiye":
                llExbtn.setVisibility(View.VISIBLE);
                llExbtn1.setVisibility(View.GONE);
                llExbtn2.setVisibility(View.GONE);
                tvPopupOrderExec.setText("配液");
                break;
            case "fuhe":
                llExbtn.setVisibility(View.VISIBLE);
                llExbtn1.setVisibility(View.GONE);
                llExbtn2.setVisibility(View.GONE);
                tvPopupOrderExec.setText("复核");
                break;
            case "exe":
                llExbtn.setVisibility(View.VISIBLE);
                llExbtn1.setVisibility(View.GONE);
                llExbtn2.setVisibility(View.GONE);
                tvPopupOrderExec.setText("执行");
                llSpiSpeed.setVisibility(View.VISIBLE);
                llWay.setVisibility(View.VISIBLE);
                break;
            case "exed":
                llExbtn.setVisibility(View.GONE);
                llExbtn1.setVisibility(View.VISIBLE);
                llExbtn2.setVisibility(View.VISIBLE);
                tvPopupOrderExec.setText("已执行");
                break;
            default:
                break;
        }


        //暂停继续按钮文字变更
        switch (ifState) {
            case "Suspend":
                tvPopupOrderSuspendContinue.setText("继续");
                break;
            default:
                tvPopupOrderSuspendContinue.setText("暂停");
                break;
        }

        //执行按钮 是否可点击变更
        switch (btnType) {
            case "peiye":
            case "fuhe":
                tvPopupOrderExec.setEnabled(true);
                tvPopupOrderExec.setClickable(true);
                tvPopupOrderExec.setBackgroundResource(R.drawable.bg_dialog_sure);
                break;
            case "exe":
                if ("1".equals(scanPat) && "1".equals(canExeFlag)) {
                    tvPopupOrderExec.setEnabled(true);
                    tvPopupOrderExec.setClickable(true);
                    tvPopupOrderExec.setBackgroundResource(R.drawable.bg_dialog_sure);
//                    tvPopupMsg.setText(tvPopupMsg.getText().toString().replace(",请扫描患者腕带",""));
                } else {
                    tvPopupOrderExec.setEnabled(false);
                    tvPopupOrderExec.setClickable(false);
                    tvPopupOrderExec.setBackgroundResource(R.drawable.bg_dialog_unclick);
                    // tvPopupMsg.setText(msgInfo+",请扫描患者腕带");
//                    tvPopupMsg.setText(msgInfo+"");
                }
                break;
            case "exed":
                tvPopupOrderExec.setEnabled(false);
                tvPopupOrderExec.setClickable(false);
                tvPopupOrderExec.setBackgroundResource(R.drawable.bg_dialog_unclick);
                break;
            default:

                break;
        }
    }

    private void initEvent() {
        tvPopupOrderExec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvPopupOrderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });
        tvPopupOrderTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tourOnclickListener != null) {
                    tourOnclickListener.onTourClick();
                }
            }
        });
        tvPopupOrderSuspendContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (suspendContinueclickListener != null) {
                    suspendContinueclickListener.onSuspendContinueClick();
                }
            }
        });
        tvPopupOrderStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stopOnclickListener != null) {
                    stopOnclickListener.onStopClick();
                }
            }
        });
        tvPopupOrderEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (endOnclickListener != null) {
                    endOnclickListener.onEndClick();
                }
            }
        });
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(false);
    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onSureOnclickListener {
        void onSureClick();
    }

    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancelOnclickListener {
        void onCancelClick();
    }

    /**
     * 设置巡视按钮被点击的接口
     */
    public interface onTourOnclickListener {
        void onTourClick();
    }

    /**
     * 设置暂停/继续按钮被点击的接口
     */
    public interface onSuspendContinueclickListener {
        void onSuspendContinueClick();
    }

    /**
     * 设置停止按钮被点击的接口
     */
    public interface onStopOnclickListener {
        void onStopClick();
    }

    /**
     * 设置结束按钮被点击的接口
     */
    public interface onEndOnclickListener {
        void onEndClick();
    }
}