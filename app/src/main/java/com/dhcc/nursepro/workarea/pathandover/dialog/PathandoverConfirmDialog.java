package com.dhcc.nursepro.workarea.pathandover.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.R;


public class PathandoverConfirmDialog extends Dialog {
    private Context context;

    private EditText etNurname;
    private EditText etNurpass;
    private TextView tvSure;
    private TextView tvCancle;

    private String nurName = "";
    private String nurPass = "";

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;


    public PathandoverConfirmDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
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
        setContentView(R.layout.dialog_pathandover_confirm);
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

        etNurname = findViewById(R.id.et_nurname);
        etNurpass = findViewById(R.id.et_nurpass);
        tvSure = findViewById(R.id.tv_sure);
        tvCancle = findViewById(R.id.tv_cancle);

    }

    private void initData() {

    }

    private void initEvent() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNurname.getText()) || TextUtils.isEmpty(etNurpass.getText())) {
                    Toast.makeText(context, "工号或密码未填写", Toast.LENGTH_LONG).show();
                    return;
                }

                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });

    }

    public String getNurName() {
        return etNurname.getText().toString();
    }

    public String getNurPass() {
        return etNurpass.getText().toString();
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