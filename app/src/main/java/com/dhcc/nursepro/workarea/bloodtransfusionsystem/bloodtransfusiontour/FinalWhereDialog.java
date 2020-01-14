package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusiontour;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * 创建自定义的dialog
 * Created by Q on 2018/3/22.
 */
public class FinalWhereDialog extends Dialog implements View.OnClickListener {

    private String finalWhere = "废弃";
    private TextView tvThrow, tvLab, tvBlood, tvOther;
    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView titleTv;//消息标题文本
    private TextView messageTv;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public FinalWhereDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalwhere_dialog_layout);
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

    /**
     * 初始化界面控件
     */
    private void initView() {
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        titleTv = findViewById(R.id.title);
        messageTv = findViewById(R.id.message);

        tvThrow = findViewById(R.id.tv_throw);
        tvLab = findViewById(R.id.tv_lab);
        tvBlood = findViewById(R.id.tv_blood);
        tvOther = findViewById(R.id.tv_other);
        tvThrow.setSelected(true);
        tvThrow.setOnClickListener(this);
        tvLab.setOnClickListener(this);
        tvBlood.setOnClickListener(this);
        tvOther.setOnClickListener(this);


    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }
        if (messageStr != null) {
            messageTv.setText(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    public String getFinalWhere() {
        return finalWhere;
    }

    @Override
    public void onClick(View v) {
        tvThrow.setSelected(false);
        tvLab.setSelected(false);
        tvBlood.setSelected(false);
        tvOther.setSelected(false);
        switch (v.getId()) {
            case R.id.tv_throw:
              tvThrow.setSelected(true);
              finalWhere = "废弃";
                break;
            case R.id.tv_lab:
                tvLab.setSelected(true);
                finalWhere = "检验科";
                break;
            case R.id.tv_blood:
                tvBlood.setSelected(true);
                finalWhere = "血库";
                break;
            case R.id.tv_other:
                tvOther.setSelected(true);
                finalWhere = "其他";
                break;
            default:
                break;
        }
    }

    public String getReason() {
        return finalWhere;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        void onYesClick();
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }

}