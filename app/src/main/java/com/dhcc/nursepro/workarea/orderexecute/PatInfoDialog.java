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
public class PatInfoDialog extends Dialog {
    private Context context;
    private TextView tvPopupPatinfo;
    private TextView tvPopupSure;
    private String patInfo;

    private onSureOnclickListener sureOnclickListener;


    public PatInfoDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
        if (patInfo != null && tvPopupPatinfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderexecpatinfo_dialog_layout);
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
        tvPopupSure = findViewById(R.id.tv_sure);
        tvPopupSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });

    }

    private void initData() {

        if (patInfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }

    }

    private void initEvent() {
        tvPopupSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onSureOnclickListener {
        void onSureClick();
    }

}