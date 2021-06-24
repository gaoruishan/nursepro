package com.dhcc.nursepro.workarea.rfid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.setting.SettingExitDialog;

/**
 * com.dhcc.nursepro.workarea.rfid
 * <p>
 * author Q
 * Date: 2021/6/23
 * Time:9:43
 */
public class RfidBindDialog  extends Dialog {

    private TextView tvSure,tvCancle,tvMsg;
    private SettingExitDialog.onSureOnclickListener sureOnclickListener;
    private SettingExitDialog.onCancleOnclickListener onCancleOnclickListener;

    public void setbtnShow(int i1,int i2){
        if (tvSure!=null){
            tvSure.setVisibility(i1);
        }
        if (tvCancle!=null){
            tvCancle.setVisibility(i2);
        }
    }
    public void setTvMsg(String  msg) {
     if (tvMsg!=null){
            tvMsg.setText(msg);
        }
    }

    public RfidBindDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(SettingExitDialog.onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    public void setOnCancleOnclickListener(SettingExitDialog.onCancleOnclickListener onCancleOnclickListener) {

        this.onCancleOnclickListener = onCancleOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bindrfid_dialog_layout);
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
        tvMsg = findViewById(R.id.tv_popup_allotbedresult);
        tvMsg.setText("请操作pda绑定当前患者");

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
    public interface onSureOnclickListener extends SettingExitDialog.onCancleOnclickListener {
        void onSureClick();
    }
    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancleOnclickListener {
        void onCancleClick();
    }
}
