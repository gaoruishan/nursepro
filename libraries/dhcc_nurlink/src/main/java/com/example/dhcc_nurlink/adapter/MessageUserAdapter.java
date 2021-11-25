package com.example.dhcc_nurlink.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.MessageListBean;
import com.example.dhcc_nurlink.bean.UserBean;

import java.util.List;

/**
 * com.example.dhcc_nurlink.adapter
 * <p>
 * author Q
 * Date: 2020/11/27
 * Time:14:01
 */
public class MessageUserAdapter extends BaseQuickAdapter<MessageListBean.MesListBean, BaseViewHolder> {

    public MessageUserAdapter(@Nullable List<MessageListBean.MesListBean> data) {
        super(R.layout.item_user_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageListBean.MesListBean item) {
        helper.setText(R.id.tv_username,item.getMsgFromBed()+" "+item.getMsgFromUserName())
                .setText(R.id.tv_tel_msgtype,item.getActionDesc())
                .setText(R.id.tv_user_loc,item.getMsgFromLoc())
                .setText(R.id.tv_tel_exec,item.getMesStatus())
                .setText(R.id.tv_user_content,item.getMsgFromLoc())
                .setText(R.id.tv_call_time,item.getRecDateTime())
                .setTextColor(R.id.tv_tel_msgtype,item.getIsExec().equals("Y")?mContext.getResources().getColor(R.color.blue):mContext.getResources().getColor(R.color.red))
                .setTextColor(R.id.tv_tel_exec,item.getIsExec().equals("Y")?mContext.getResources().getColor(R.color.dhcc_gray):mContext.getResources().getColor(R.color.red))
                ;

    }
}
