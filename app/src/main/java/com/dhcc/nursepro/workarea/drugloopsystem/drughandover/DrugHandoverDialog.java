package com.dhcc.nursepro.workarea.drugloopsystem.drughandover;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.dhcc.nursepro.R;

/**
 * DrugHandoverDialog
 * 
 *
 * @author Devlix126
 * created at 2019/5/27 11:22
 */
public class DrugHandoverDialog extends Dialog {
    private ImageView imgDrughandover;
    private TextView tvDrughandoveresult;
    private LinearLayout llDrughandoverDialog;
    private TextView tvDrughandoverSure;
    private TextView tvDrughandoverCancle;

    private String handoverResult;
    private int imgId;
    private int sureVisible;
    private int cancleVisible;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;



    public DrugHandoverDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    public void setHandoverresult(String handoverResult) {
        this.handoverResult = handoverResult;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setSureVisible(int sureVisible) {
        this.sureVisible = sureVisible;
    }

    public void setCancleVisible(int cancleVisible) {
        this.cancleVisible = cancleVisible;
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
        setContentView(R.layout.drughandover_dialog_layout);
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
        imgDrughandover = findViewById(R.id.img_drughandover);
        tvDrughandoveresult = findViewById(R.id.tv_drughandoveresult);
        llDrughandoverDialog = findViewById(R.id.ll_drughandover_dialog);
        tvDrughandoverSure = findViewById(R.id.tv_drughandover_sure);
        tvDrughandoverCancle = findViewById(R.id.tv_drughandover_cancle);
    }

    private void initData() {
        if (handoverResult != null) {
            tvDrughandoveresult.setText(handoverResult);
        }

        if (imgId != 0) {
            imgDrughandover.setImageResource(imgId);
        }

        tvDrughandoverSure.setVisibility(sureVisible);
        tvDrughandoverCancle.setVisibility(cancleVisible);
        if (cancleVisible == View.GONE){
            llDrughandoverDialog.setPadding(ConvertUtils.dp2px(56),0,ConvertUtils.dp2px(56),0);
        }else {
            llDrughandoverDialog.setPadding(0,0,0,0);
        }

    }

    private void initEvent() {
        tvDrughandoverSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvDrughandoverCancle.setOnClickListener(new View.OnClickListener() {
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