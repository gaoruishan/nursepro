package com.base.commlibs.voiceUtils.voiceprint;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.base.commlibs.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * com.voicenew
 * <p>
 * author Q
 * Date: 2020/9/15
 * Time:10:43
 */
public class RegedPatAdapter extends BaseQuickAdapter<LocUsersBean.NurseListBean, BaseViewHolder> {


    public RegedPatAdapter(@Nullable List<LocUsersBean.NurseListBean> data) {
        super(R.layout.item_voice_pat, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocUsersBean.NurseListBean item) {
        TextView tvPatientLoc = helper.getView(R.id.tv_voice_user_name);
        TextView tvPatientDoctorName = helper.getView(R.id.tv_voice_user_code);
        helper.addOnClickListener(R.id.ll_message_rightmenu);
        tvPatientLoc.setText(item.getName()+" ");
        tvPatientDoctorName.setText(item.getUserCode());
        helper.setText(R.id.tv_message_bedno,item.getVoiceReg()?"已注册":"未注册")
                .setGone(R.id.ll_message_rightmenu,item.getVoiceReg()?true:false)
                .setTextColor(R.id.tv_voice_user_name,mContext.getResources().getColor(item.getVoiceReg()? R.color.blue: R.color.text_color_gray_8))
                .setTextColor(R.id.tv_voice_user_code,mContext.getResources().getColor(item.getVoiceReg()? R.color.blue: R.color.text_color_gray_8))
                .setTextColor(R.id.tv_message_bedno,mContext.getResources().getColor(item.getVoiceReg()? R.color.blue: R.color.text_color_gray_8));
    }
}
