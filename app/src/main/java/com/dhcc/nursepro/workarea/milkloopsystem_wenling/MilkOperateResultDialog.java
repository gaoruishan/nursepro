package com.dhcc.nursepro.workarea.milkloopsystem_wenling;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem
 * <p>
 * author Q
 * Date: 2018/9/21
 * Time:14:51
 */
public class MilkOperateResultDialog  extends Dialog {
    private ImageView imgPopupMilkOperationresult;
    private TextView tvPopupMilkOperationresult;
    private TextView tvPopupSure;

    private String execresult;
    private int imgId;
    private int sureVisible;

    private MilkOperateResultDialog.onSureOnclickListener sureOnclickListener;


    public MilkOperateResultDialog(Context context) {
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

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(MilkOperateResultDialog.onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mdialog_milkoperateresult);
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
        imgPopupMilkOperationresult = findViewById(R.id.img_popup_milkoperationresult);
        tvPopupMilkOperationresult = findViewById(R.id.tv_popup_milkoperationresult);
        tvPopupSure = findViewById(R.id.tv_popup_sure);
    }

    private void initData() {
        if (execresult != null) {
            tvPopupMilkOperationresult.setText(execresult);
        }

        if (imgId != 0) {
            imgPopupMilkOperationresult.setImageResource(imgId);
        }

        tvPopupSure.setVisibility(sureVisible);

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
        public void onSureClick();
    }
}
