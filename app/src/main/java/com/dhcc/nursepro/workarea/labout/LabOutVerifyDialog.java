package com.dhcc.nursepro.workarea.labout;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * OrderExecOrderDialog
 *
 *
 * @author Devlix126
 * created at 2019/11/18 15:10
 */
public class LabOutVerifyDialog extends Dialog {

    private EditText edHGUserCode;
    private EditText edHGPW;
    private TextView tvPopupSend;
    private TextView tvPopupCancel;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;


    public LabOutVerifyDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    public void setHGUserCode(String hgUserCode) {
        if (edHGUserCode != null) {
            edHGUserCode.setText(hgUserCode);
        }
    }

    public String getHGUserCode() {
        if (edHGUserCode != null) {
            return edHGUserCode.getText().toString();
        }
        return "";
    }

    public String getHGPW() {
        if (edHGPW != null) {
            return edHGPW.getText().toString();
        }
        return "";
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
        setContentView(R.layout.laboutverify_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        edHGUserCode = findViewById(R.id.ed_popup_hgusercode);
        edHGPW = findViewById(R.id.ed_popup_hgpw);
        tvPopupSend = findViewById(R.id.tv_popup_send);
        tvPopupCancel = findViewById(R.id.tv_popup_cancel);

    }

    private void initEvent() {
        tvPopupSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvPopupCancel.setOnClickListener(new View.OnClickListener() {
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
        void onSureClick();
    }

    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancelOnclickListener {
        void onCancelClick();
    }

}