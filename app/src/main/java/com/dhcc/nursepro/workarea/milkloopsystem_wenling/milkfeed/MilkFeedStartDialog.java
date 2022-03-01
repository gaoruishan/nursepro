package com.dhcc.nursepro.workarea.milkloopsystem_wenling.milkfeed;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecOrderDialog;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem_wenling.milkfeed
 * <p>
 * author Q
 * Date: 2019/7/8
 * Time:16:05
 */
public class MilkFeedStartDialog extends Dialog {
    private Context context;
    private TextView tvPopupPatinfo;
    private TextView tvPopupOrderexec;
    private TextView tvPopupOrdercancel;
    private EditText edtMotherMilk,edtOtherMilk;

    private String patInfo,motherMilk,otherMilk;

    private OrderExecOrderDialog.onSureOnclickListener sureOnclickListener;
    private OrderExecOrderDialog.onCancelOnclickListener cancelOnclickListener;


    public MilkFeedStartDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }


    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
    }

    public String getMotherMilk(){
        return edtMotherMilk.getText().toString();
    }
    public String getOtherMilk(){
        return edtOtherMilk.getText().toString();
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(OrderExecOrderDialog.onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    /**
     * 设置取消按钮的显示内容和监听
     */
    public void setCancelOnclickListener(OrderExecOrderDialog.onCancelOnclickListener onCancelOnclickListener) {

        this.cancelOnclickListener = onCancelOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milkfeedstart_dialog_layout);
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
        tvPopupPatinfo = findViewById(R.id.tv_popup_patinfo);

        tvPopupOrderexec = findViewById(R.id.tv_popup_orderexec);
        tvPopupOrdercancel = findViewById(R.id.tv_popup_ordercancel);

        edtMotherMilk = findViewById(R.id.et_mom_milkamount);
        edtOtherMilk = findViewById(R.id.et_other_milkamount);

    }

    private void initData() {

        if (patInfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }

    }

    private void initEvent() {
        tvPopupOrderexec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick(null);
                }
            }
        });
        tvPopupOrdercancel.setOnClickListener(new View.OnClickListener() {
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
