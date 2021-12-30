package com.dhcc.nursepro.workarea.orderexecute;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderExecuteOrderDialogAdapter;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderExecOrderDialog
 * 创建自定义的dialog
 *
 * @author Devlix126
 * created at 2018/12/25 10:00
 */
public class OrderExecOrderDialog extends Dialog {
    private Context context;
    private TextView tvPopupPatinfo;
    private TextView tvPopupOrderunit;
    private TextView tvPopupOrderinfo;
    private RecyclerView recyPopupChildOrderInfo;
    private TextView tvPopupOrderinfoex;
    private TextView tvPopupOrderexec;
    private TextView tvPopupOrdercancel;

    private String patInfo;
    private String orderInfo;
    private String orderUnit;
    private List<ScanResultBean.OrdersBean> childOrders = new ArrayList<>();
    private String orderInfoEx;

    private OrderExecuteOrderDialogAdapter adapter;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;
    private View llMsg,llDevice;
    private TextView tvPopupMsg,tvPopupDevice;
    private String msgInfo;
    private String canExeFlag;

    //功能区扫码 附加数据
    private String sttDateTime = "";
    private String arcimDesc = "";
    private String orderId = "";
    private String bedCode = "";
    private String CDSS; // EH 21-11-04 CDSS注意事项
    private View llCDSS;
    private TextView tvCDSS;
    private boolean barCodeTypeInfusion;
    private String devicNo;

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

    public OrderExecOrderDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    public void setChildOrders(List<ScanResultBean.OrdersBean> childOrders) {
        this.childOrders = childOrders;
        if (childOrders != null && childOrders.size() > 0 && recyPopupChildOrderInfo != null && adapter != null) {
            adapter.setNewData(childOrders);
        }
    }


    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
        if (patInfo != null && tvPopupPatinfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }
    }

    public void setPopMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
        if (!TextUtils.isEmpty(msgInfo) && llMsg != null && tvPopupMsg != null) {
            llMsg.setVisibility(View.VISIBLE);
            tvPopupMsg.setText(msgInfo);
        }
    }

    public void setOrderUnit(String orderUnit) {
        this.orderUnit = orderUnit;
        if (orderUnit != null && tvPopupOrderunit != null) {
            tvPopupOrderunit.setText(orderUnit);
        }
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
        if (orderInfo != null && tvPopupOrderinfo != null) {
            tvPopupOrderinfo.setText(orderInfo);
        }
    }

    public void setOrderInfoEx(String orderInfoEx) {
        this.orderInfoEx = orderInfoEx;
        if (orderInfoEx != null && tvPopupOrderinfoex != null) {
            tvPopupOrderinfoex.setText(orderInfoEx);
        }
    }

    public void setCanExeFlag(String canExeFlag) {
        this.canExeFlag = canExeFlag;

        //canExeFlag 0 不能 1 能
        if (tvPopupOrderexec != null) {
            if ("0".equals(canExeFlag)) {
                tvPopupOrderexec.setEnabled(false);
                tvPopupOrderexec.setClickable(false);
                tvPopupOrderexec.setBackgroundResource(R.drawable.bg_dialog_cancel);
            } else {
                tvPopupOrderexec.setEnabled(true);
                tvPopupOrderexec.setClickable(true);
                tvPopupOrderexec.setBackgroundResource(R.drawable.bg_dialog_sure);
            }
        }

    }

    public void setCDSS(String CDSS) {
        this.CDSS = CDSS;
        if (!TextUtils.isEmpty(CDSS) && llCDSS != null && tvCDSS != null) {
            llCDSS.setVisibility(View.VISIBLE);
            tvCDSS.setText(android.text.Html.fromHtml(CDSS));
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderexecorder_dialog_layout);
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
        tvPopupPatinfo = findViewById(R.id.tv_popup_patinfo);
        tvPopupOrderinfo = findViewById(R.id.tv_popup_orderinfo);
        tvPopupOrderunit = findViewById(R.id.tv_popup_orderunit);
        recyPopupChildOrderInfo = findViewById(R.id.recy_popup_childOrderInfo);
        tvPopupOrderinfoex = findViewById(R.id.tv_popup_orderinfoex);
        tvPopupOrderexec = findViewById(R.id.tv_popup_orderexec);
        tvPopupOrdercancel = findViewById(R.id.tv_popup_ordercancel);
        llMsg = findViewById(R.id.ll_msg);
        llDevice = findViewById(R.id.ll_device);
        tvPopupMsg = findViewById(R.id.tv_popup_msg);
        tvPopupDevice = findViewById(R.id.tv_popup_device);

        //提高展示效率
        recyPopupChildOrderInfo.setHasFixedSize(true);
        //设置的布局管理
        recyPopupChildOrderInfo.setLayoutManager(new LinearLayoutManager(context));
        llCDSS = findViewById(R.id.ll_CDSS);
        tvCDSS = findViewById(R.id.tv_CDSS);
        tvCDSS.setMovementMethod(android.text.method.ScrollingMovementMethod.getInstance());
    }

    private void initAdapter() {
        adapter = new OrderExecuteOrderDialogAdapter(new ArrayList<>());
        recyPopupChildOrderInfo.setAdapter(adapter);
    }

    private void initData() {
        //设备
        setDeviceInfo(devicNo);

        if (patInfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }

        if (orderInfo != null) {
            tvPopupOrderinfo.setText(orderInfo);
        }

        if (orderUnit != null) {
            tvPopupOrderunit.setText(orderUnit);
        }

        if (childOrders != null && childOrders.size() > 0) {
            adapter.setNewData(childOrders);
        }

        if (orderInfoEx != null) {
            tvPopupOrderinfoex.setText(orderInfoEx);
        }

        if (!TextUtils.isEmpty(msgInfo)) {
            llMsg.setVisibility(View.VISIBLE);
            tvPopupMsg.setText(msgInfo);
        }

        //canExeFlag 0 不能 1 能
        if (!"1".equals(canExeFlag)) {
            tvPopupOrderexec.setEnabled(false);
            tvPopupOrderexec.setClickable(false);
            tvPopupOrderexec.setBackgroundResource(R.drawable.bg_dialog_cancel);
        }
        if (!TextUtils.isEmpty(CDSS)) {
            llCDSS.setVisibility(View.VISIBLE);
            tvCDSS.setText(android.text.Html.fromHtml(CDSS));
        }
        llDevice.setVisibility(barCodeTypeInfusion ? View.VISIBLE : View.GONE);
    }

    private void initEvent() {
        tvPopupOrderexec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvPopupOrdercancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });
    }

    public void setBarCodeType(boolean barCodeTypeInfusion) {
        this.barCodeTypeInfusion = barCodeTypeInfusion;
    }

    public void setDevicNo(String devicNo) {
        this.devicNo = devicNo;
        setDeviceInfo(devicNo);
    }

    public void setDeviceInfo(String devicNo) {
        if(!TextUtils.isEmpty(devicNo)){
            if (llDevice != null) {
                llDevice.setVisibility(View.VISIBLE);
            }
            if (tvPopupDevice != null) {
                tvPopupDevice.setText(devicNo + "");
            }
        }
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

}