package com.dhcc.nursepro.Activity;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;
import com.dhcc.nursepro.workarea.checkresult.adapter.CheckPatListAdapter;
import com.dhcc.nursepro.workarea.labresult.api.LabApiManager;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecOrderDialog;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderExecuteOrderDialogAdapter;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建自定义的dialog
 * Created by Q on 2018/3/22.
 */
public class RequestDialog extends Dialog {
    private Context context;
    private TextView tvPopupPatinfo;
    private TextView tvPopupOrderexec;
    private TextView tvPopupOrdercancel;

    private String patInfo;
    private List<ScanResultBean.OrdersBean> childOrders = new ArrayList<>();
    private String orderInfoEx;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;


    public RequestDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    public void setChildOrders(List<ScanResultBean.OrdersBean> childOrders) {
        this.childOrders = childOrders;
    }

    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
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
        setContentView(R.layout.localrequest_dialog_layout);
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
        tvPopupPatinfo = findViewById(R.id.tv_popup_patinfo);
        tvPopupOrderexec = findViewById(R.id.tv_popup_orderexec);
        tvPopupOrdercancel = findViewById(R.id.tv_popup_ordercancel);
    }



    private void initData() {

        if (patInfo != null) {
            tvPopupPatinfo.setText(patInfo);
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