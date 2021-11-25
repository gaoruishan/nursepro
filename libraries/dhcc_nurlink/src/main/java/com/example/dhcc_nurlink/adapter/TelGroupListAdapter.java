package com.example.dhcc_nurlink.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.GroupBean;
import com.example.dhcc_nurlink.bean.PhoneBookListBean;
import com.example.dhcc_nurlink.bean.UserBean;

import java.util.List;

/**
 * com.example.dhcc_nurlink.adapter
 * <p>
 * author Q
 * Date: 2020/11/27
 * Time:14:01
 */
public class TelGroupListAdapter extends BaseQuickAdapter<PhoneBookListBean.GroupListBean, BaseViewHolder> {

    public TelGroupListAdapter(@Nullable List<PhoneBookListBean.GroupListBean> data) {
        super(R.layout.item_user_group, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhoneBookListBean.GroupListBean item) {
        helper.setText(R.id.tv_group_name,item.getGroupName())
                .addOnClickListener(R.id.ll_group);
    }
}
