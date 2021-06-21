package com.dhcc.nursepro.workarea.orderexecutelab;


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
 * 创建自定义的dialog
 * Created by Q on 2018/3/22.
 */
public class OrderExecResultDialog extends Dialog {
    private ImageView imgPopupOrderexecresult;
    private TextView tvPopupOrderexecresult;
    private TextView tvPopupSure;
    private TextView tvPopupCancle;
    private LinearLayout llDialog;

    private String execresult;
    private int imgId;
    private int sureVisible;
    private int cancleVisible;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;



    public OrderExecResultDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    public void setExecresult(String execresult) {
        this.execresult = execresult;
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
        setContentView(R.layout.orderexecresult_dialog_layout);
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
        imgPopupOrderexecresult = findViewById(R.id.img_popup_orderexecresult);
        tvPopupOrderexecresult = findViewById(R.id.tv_popup_orderexecresult);
        tvPopupSure = findViewById(R.id.tv_popup_sure);
        tvPopupCancle = findViewById(R.id.tv_popup_cancle);
        llDialog = findViewById(R.id.ll_order_dialog);
    }

    private void initData() {
        if (execresult != null) {
            tvPopupOrderexecresult.setText(execresult);
        }

        if (imgId != 0) {
            imgPopupOrderexecresult.setImageResource(imgId);
        }

        tvPopupSure.setVisibility(sureVisible);
        tvPopupCancle.setVisibility(cancleVisible);
        if (cancleVisible == View.GONE){
            llDialog.setPadding(ConvertUtils.dp2px(56),0,ConvertUtils.dp2px(56),0);
        }else {
            llDialog.setPadding(0,0,0,0);
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
        tvPopupCancle.setOnClickListener(new View.OnClickListener() {
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