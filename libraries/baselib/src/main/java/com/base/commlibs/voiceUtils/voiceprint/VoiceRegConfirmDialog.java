package com.base.commlibs.voiceUtils.voiceprint;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.R;


/**
 * 创建自定义的dialog
 * Created by Q on 2018/3/22.
 */
public class VoiceRegConfirmDialog extends Dialog {
    private Context context;
    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;
    private TextView tvScore,tvSugges;
    private ImageView imgSugges;

    private TextView tvPopupOrderexec;
    private TextView tvPopupOrdercancel;
    private float score=0;

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public VoiceRegConfirmDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

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
        setContentView(R.layout.voiceprint_reg_dialog_layout);
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
       tvScore = findViewById(R.id.tv_score);
       tvSugges = findViewById(R.id.tv_suggest);
       imgSugges = findViewById(R.id.img_suggest);
        tvPopupOrderexec = findViewById(R.id.tv_popup_orderexec);
        tvPopupOrdercancel = findViewById(R.id.tv_popup_ordercancel);
    }


    private void initData() {
        tvScore.setText(score+"");
        if (score>85){
            tvSugges.setText("建议");
            tvSugges.setTextColor(context.getResources().getColor(R.color.chart_green));
//            imgSugges.setImageResource(R.drawable.voice_reg_suggest);
        }else if (score>70){
            tvSugges.setText("一般");
//            tvSugges.setTextColor(context.getResources().getColor(R.color.darkorange));
//            imgSugges.setImageResource(R.drawable.voice_reg_normal);
        }else if (score>50){
            tvSugges.setText("不建议");
            tvSugges.setTextColor(context.getResources().getColor(R.color.red));
//            imgSugges.setImageResource(R.drawable.voice_reg_nosug);
        }else {
            tvSugges.setText("验证不合格，请重来");
        }
    }

    private void initEvent() {
        tvPopupOrderexec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
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
        void onSureClick();
    }

    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancelOnclickListener {
        void onCancelClick();
    }

}