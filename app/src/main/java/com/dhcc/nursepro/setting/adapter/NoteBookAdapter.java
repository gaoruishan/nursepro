package com.dhcc.nursepro.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.setting.bean.NoteBean;

import java.util.List;

/**
 * com.dhcc.nursepro.setting.adapter
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:15:16
 */
public class NoteBookAdapter extends BaseQuickAdapter<NoteBean.SoundListBean, BaseViewHolder> {
    public NoteBookAdapter(@Nullable List<NoteBean.SoundListBean> data) {
        super(R.layout.item_note, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoteBean.SoundListBean item) {
        helper.setText(R.id.tv_datetime,item.getSoundDate()+" "+item.getSoundTime())
                .addOnClickListener(R.id.tv_playvoice)
                .addOnClickListener(R.id.ll_message_rightmenu)
                .addOnClickListener(R.id.patinfodetail_title)
                .setGone(R.id.tv_playvoice,item.getSoundId().equals("0")?false:true);
        String itemCount = item.getSoundStr();
        if (itemCount.length()>10){
            itemCount = itemCount.substring(0,9)+"...";
        }
        helper.setText(R.id.patinfodetail_title, itemCount);
    }
}