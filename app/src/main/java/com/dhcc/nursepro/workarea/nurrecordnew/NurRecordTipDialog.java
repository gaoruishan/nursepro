package com.dhcc.nursepro.workarea.nurrecordnew;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;

/**
 *
 */
public class NurRecordTipDialog extends Dialog {
    private TextView tvPopupNurrecordtip;
    private TextView tvPopupSure;

    private String nurRecordTip;

    private onSureOnclickListener sureOnclickListener;


    public NurRecordTipDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    public void setNurRecordTip(String nurRecordTip) {
        this.nurRecordTip = nurRecordTip;
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
        setContentView(R.layout.nurrecordtip_dialog_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {

        tvPopupNurrecordtip = findViewById(R.id.tv_popup_nurrecordtip);
        tvPopupSure = findViewById(R.id.tv_popup_sure);

    }

    private void initData() {
        if (!StringUtils.isEmpty(nurRecordTip)) {
            tvPopupNurrecordtip.setText(nurRecordTip);
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