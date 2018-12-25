package com.dhcc.nursepro.workarea.orderexecute;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * OrderExecOrderDialog
 * 创建自定义的dialog
 *
 * @author Devlix126
 * created at 2018/12/25 10:00
 */
public class OrderExecOrderDialog extends Dialog {
    private TextView tvPopupScaninfo;
    private TextView tvPopupPatinfo;
    private TextView tvPopupOrderinfo;
    private TextView tvPopupOrderinfoex;
    private TextView tvPopupOrderexec;
    private TextView tvPopupOrdercancel;

    private String scanInfo;
    private String patInfo;
    private String orderInfo;
    private String orderInfoEx;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;


    public OrderExecOrderDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    public void setScanInfo(String scanInfo) {
        this.scanInfo = scanInfo;
    }

    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public void setOrderInfoEx(String orderInfoEx) {
        this.orderInfoEx = orderInfoEx;
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
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        tvPopupScaninfo = findViewById(R.id.tv_popup_scaninfo);
        tvPopupPatinfo = findViewById(R.id.tv_popup_patinfo);
        tvPopupOrderinfo = findViewById(R.id.tv_popup_orderinfo);
        tvPopupOrderinfoex = findViewById(R.id.tv_popup_orderinfoex);
        tvPopupOrderexec = findViewById(R.id.tv_popup_orderexec);
        tvPopupOrdercancel = findViewById(R.id.tv_popup_ordercancel);
    }

    private void initData() {
        if (scanInfo != null) {
            tvPopupScaninfo.setText(scanInfo);
        }

        if (patInfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }

        if (orderInfo != null) {
            tvPopupOrderinfo.setText(orderInfo);
        }

        if (orderInfoEx != null) {
            tvPopupOrderinfoex.setText(orderInfoEx);
        }

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

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onSureOnclickListener {
        public void onSureClick();
    }

    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancelOnclickListener {
        public void onCancelClick();
    }

}