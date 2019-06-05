package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration;


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
 * RlRegResultDialog
 *
 *
 * @author Devlix126
 * created at 2019/6/3 11:33
 */
public class RlRegResultDialog extends Dialog {
    private ImageView imgRlRegResult;
    private TextView tvRlRegResult;
    private LinearLayout llRlRegResultDialog;
    private TextView tvRlRegResultSure;
    private TextView tvRlRegResultCancle;

    private String rlRegResult;
    private int imgId;
    private int sureVisible;
    private int cancleVisible;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;



    public RlRegResultDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    public String getRlRegResult() {
        return rlRegResult;
    }

    public void setRlRegResult(String rlRegResult) {
        this.rlRegResult = rlRegResult;
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
        setContentView(R.layout.drugrlregresult_dialog_layout);
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
        imgRlRegResult = findViewById(R.id.img_rlregresult);
        tvRlRegResult = findViewById(R.id.tv_rlregresult);
        llRlRegResultDialog = findViewById(R.id.ll_rlregresult_dialog);
        tvRlRegResultSure = findViewById(R.id.tv_rlregresult_sure);
        tvRlRegResultCancle = findViewById(R.id.tv_rlregresult_cancle);
    }

    private void initData() {
        if (rlRegResult != null) {
            tvRlRegResult.setText(rlRegResult);
        }

        if (imgId != 0) {
            imgRlRegResult.setImageResource(imgId);
        }

        tvRlRegResultSure.setVisibility(sureVisible);
        tvRlRegResultCancle.setVisibility(cancleVisible);
        if (cancleVisible == View.GONE){
            llRlRegResultDialog.setPadding(ConvertUtils.dp2px(56),0,ConvertUtils.dp2px(56),0);
        }else {
            llRlRegResultDialog.setPadding(0,0,0,0);
        }

    }

    private void initEvent() {
        tvRlRegResultSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvRlRegResultCancle.setOnClickListener(new View.OnClickListener() {
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