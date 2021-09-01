package com.base.commlibs.voiceUtils.voiceprint;

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
public class VoicePrintReadDialog extends Dialog {
    private ImageView mMicIv;
    private TextView tvNurTip;
    private String tipsMsg="";

    public void setTipsMsg(String tipsMsg) {
        this.tipsMsg = tipsMsg;
        if (tvNurTip!=null){
            tvNurTip.setVisibility(View.VISIBLE);
            tvNurTip.setText(tipsMsg);
        }
    }

    public VoicePrintReadDialog(Context context) {
        super(context, R.style.CustomAsrDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voiceprint_read_dialog);
        //getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //需要添加的语句
        initView();
        initData();
    }

    private void initView(){
        mMicIv = (ImageView) findViewById(R.id.asr_mic_iv);
        tvNurTip = (TextView) findViewById(R.id.tv_tip);
    }
    private void initData(){
        if (tvNurTip!=null){
            tvNurTip.setVisibility(View.VISIBLE);
            tvNurTip.setText(tipsMsg);
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
