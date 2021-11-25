package com.example.dhcc_nurlink.adapter;

import android.os.Build;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.CallingHistoryBean;
import com.example.dhcc_nurlink.bean.UserBean;

import java.util.List;

/**
 * com.example.dhcc_nurlink.adapter
 * <p>
 * author Q
 * Date: 2020/11/27
 * Time:14:01
 */
public class CallHistoryAdapter extends BaseQuickAdapter<CallingHistoryBean.callBean, BaseViewHolder> {

    public CallHistoryAdapter(@Nullable List<CallingHistoryBean.callBean> data) {
        super(R.layout.item_user_telephone, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CallingHistoryBean.callBean item) {
        helper.setText(R.id.tv_username,item.getCallName())
                .setText(R.id.tv_call_time,item.getTime())
                .setImageDrawable(R.id.img_telstatu,item.getStatus().contains("Y")?
                        mContext.getResources().getDrawable(R.drawable.nurlink_icon_call_out):mContext.getResources().getDrawable(R.drawable.nurlink_icon_call_reject))
                .addOnClickListener(R.id.tv_tel_call);

    }
}
