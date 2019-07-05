package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation;


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
 * DrugPreDialog
 * 
 *
 * @author Devlix126
 * created at 2019/6/6 15:53
 */
public class DrugPreDialog extends Dialog {
    private ImageView imgDrugpre;
    private TextView tvDrugpreesult;
    private LinearLayout llDrugpreDialog;
    private TextView tvDrugpreSure;
    private TextView tvDrugpreCancle;

    private String drugPreResult;
    private int imgId;
    private int sureVisible;
    private int cancleVisible;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;



    public DrugPreDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    public void setDrugPreResult(String drugPreResult) {
        this.drugPreResult = drugPreResult;
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
        setContentView(R.layout.drugpre_dialog_layout);
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


        imgDrugpre = findViewById(R.id.img_drugpre);
        tvDrugpreesult = findViewById(R.id.tv_drugpreesult);
        llDrugpreDialog = findViewById(R.id.ll_drugpre_dialog);
        tvDrugpreSure = findViewById(R.id.tv_drugpre_sure);
        tvDrugpreCancle = findViewById(R.id.tv_drugpre_cancle);

    }

    private void initData() {
        if (drugPreResult != null) {
            tvDrugpreesult.setText(drugPreResult);
        }

        if (imgId != 0) {
            imgDrugpre.setImageResource(imgId);
        }

        tvDrugpreSure.setVisibility(sureVisible);
        tvDrugpreCancle.setVisibility(cancleVisible);
        if (cancleVisible == View.GONE){
            llDrugpreDialog.setPadding(ConvertUtils.dp2px(56),0,ConvertUtils.dp2px(56),0);
        }else {
            llDrugpreDialog.setPadding(0,0,0,0);
        }

    }

    private void initEvent() {
        tvDrugpreSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvDrugpreCancle.setOnClickListener(new View.OnClickListener() {
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