package com.dhcc.nursepro.setting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * com.dhcc.nursepro.setting
 * <p>
 * author Q
 * Date: 2019/12/19
 * Time:14:22
 */
public class SettingExitDialog  extends Dialog {

    private TextView tvSure,tvCancle;
    private onSureOnclickListener sureOnclickListener;
    private onCancleOnclickListener onCancleOnclickListener;


    public SettingExitDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    public void setOnCancleOnclickListener(onCancleOnclickListener onCancleOnclickListener) {

        this.onCancleOnclickListener = onCancleOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingexit_dialog_layout);
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
        tvSure = findViewById(R.id.tv_exit_sure);
        tvCancle = findViewById(R.id.tv_exit_cancle);
    }

    private void initData() {

    }

    private void initEvent() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null){
                    sureOnclickListener.onCancleClick();
                }
            }
        });
    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onSureOnclickListener extends onCancleOnclickListener {
        void onSureClick();
    }
    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancleOnclickListener {
        void onCancleClick();
    }
}