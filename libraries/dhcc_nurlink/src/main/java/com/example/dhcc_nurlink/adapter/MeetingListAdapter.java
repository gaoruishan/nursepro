package com.example.dhcc_nurlink.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.BedMapBean;
import com.example.dhcc_nurlink.bean.MeetingUserBean;
import com.raisound.voip.ConferenceMember;

import java.util.List;

/**
 * com.example.dhcc_nurlink.adapter
 * <p>
 * author Q
 * Date: 2021/1/9
 * Time:10:01
 */
public class MeetingListAdapter extends BaseQuickAdapter<MeetingUserBean, BaseViewHolder> {

    public MeetingListAdapter(@Nullable List<MeetingUserBean> data) {
        super(R.layout.item_meeting_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeetingUserBean item) {
        helper.setText(R.id.tv_name,item.getNickname())
                .setText(R.id.tv_status,item.getJoinStatus());

    }
}
