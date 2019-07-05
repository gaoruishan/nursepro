package com.dhcc.nursepro.workarea.drugloopsystem.drughandover;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * OrderExecOrderDialog
 * 创建自定义的dialog
 *
 * @author Devlix126
 * created at 2018/12/25 10:00
 */
public class DrugReceiveDialog extends Dialog {
    private Context context;
    private ImageView imgDrugreceiveScan;
    private TextView tvDrugreceivetip;
    private TextView tvDrugreceiveBoxcode;
    private TextView tvDrugreceiveDruginfo;
    private TextView tvDrugreceiveCarryuser;
    private TextView tvDrugreceiveNurse;

    private LinearLayout llDrugreceiveDialog;
    private TextView tvDrugreceiveSure;
    private TextView tvDrugreceiveCancle;


    private String boxCode;
    private String drugInfo;
    private String carryUser;
    private String nurseInfo;


    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;


    public DrugReceiveDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getDrugInfo() {
        return drugInfo;
    }

    public void setDrugInfo(String drugInfo) {
        this.drugInfo = drugInfo;
    }

    public String getCarryUser() {
        return carryUser;
    }

    public void setCarryUser(String carryUser) {
        this.carryUser = carryUser;
        tvDrugreceiveCarryuser.setText(carryUser);
        imgDrugreceiveScan.setVisibility(View.GONE);
        tvDrugreceivetip.setText("请点击下方确认按钮确认交接");
    }

    public String getNurseInfo() {
        return nurseInfo;
    }

    public void setNurseInfo(String nurseInfo) {
        this.nurseInfo = nurseInfo;
        tvDrugreceiveNurse.setText(nurseInfo);
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
        setContentView(R.layout.drugreceive_dialog_layout);
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

        imgDrugreceiveScan = findViewById(R.id.img_drugreceive_scan);
        tvDrugreceivetip = findViewById(R.id.tv_drugreceivetip);
        tvDrugreceiveBoxcode = findViewById(R.id.tv_drugreceive_boxcode);
        tvDrugreceiveDruginfo = findViewById(R.id.tv_drugreceive_druginfo);
        tvDrugreceiveCarryuser = findViewById(R.id.tv_drugreceive_carryuser);
        tvDrugreceiveNurse = findViewById(R.id.tv_drugreceive_nurse);

        llDrugreceiveDialog = findViewById(R.id.ll_drugreceive_dialog);
        tvDrugreceiveSure = findViewById(R.id.tv_drugreceive_sure);
        tvDrugreceiveCancle = findViewById(R.id.tv_drugreceive_cancle);


    }

    private void initData() {

        if (boxCode != null) {
            tvDrugreceiveBoxcode.setText(boxCode);
        }

        if (drugInfo != null) {
            tvDrugreceiveDruginfo.setText(drugInfo);
        }

        if (carryUser != null) {
            tvDrugreceiveCarryuser.setText(carryUser);
        }

        if (nurseInfo != null) {
            tvDrugreceiveNurse.setText(nurseInfo);
        }

    }

    private void initEvent() {
        tvDrugreceiveSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvDrugreceiveCancle.setOnClickListener(new View.OnClickListener() {
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