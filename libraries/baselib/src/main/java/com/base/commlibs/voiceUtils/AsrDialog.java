package com.base.commlibs.voiceUtils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.R;

/**
 * com.example.dhcc_sound
 * <p>
 * author Q
 * Date: 2021/6/15
 * Time:11:30
 */
public class AsrDialog extends Dialog {
    private ImageView mMicIv;
    private TextView mTipTv;
    private TextView tvNurTip;
    private String tipsMsg="";

    public AsrDialog(Context context) {
        super(context, R.style.CustomAsrDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_asr_dialog);
        //getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //需要添加的语句
        initView();
        initData();
    }

    public void setTipText(String msg){
        if (mTipTv!=null){
            mTipTv.setText(msg);
        }
    }

    private void initView(){
        mMicIv = (ImageView) findViewById(R.id.asr_mic_iv);
        mTipTv = (TextView) findViewById(R.id.asr_tip_tv);
        tvNurTip = (TextView) findViewById(R.id.tv_tip);
    }
    private void initData(){
        if (!tipsMsg.equals("")){
            tvNurTip.setVisibility(View.VISIBLE);
            tvNurTip.setText(tipsMsg);
        }else {
            tvNurTip.setVisibility(View.GONE);
        }
    }
    public void showtips(String tipsMsg){
        this.tipsMsg = tipsMsg;
    }

    public void setEnergy(int id){
        if (mMicIv != null){
            mMicIv.setImageResource(id);
        }
    }

}
