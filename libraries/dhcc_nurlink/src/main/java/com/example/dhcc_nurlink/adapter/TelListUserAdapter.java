package com.example.dhcc_nurlink.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.HisUsersBean;
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
public class TelListUserAdapter extends BaseQuickAdapter<PhoneBookListBean.UserListBean, BaseViewHolder> {

    public TelListUserAdapter(@Nullable List<PhoneBookListBean.UserListBean> data) {
        super(R.layout.item_user_tellist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,PhoneBookListBean.UserListBean item) {
        helper.setText(R.id.tv_username,item.getUserName())
                .setText(R.id.tv_pin,item.getFirstPin())
                .setGone(R.id.tv_pin,item.getFirstFlag().equals("true")?true:false)
                .setGone(R.id.view_device,item.getFirstFlag().equals("true")?false:true)
                .addOnClickListener(R.id.tv_tel_call)
                .addOnClickListener(R.id.img_telstatu);

        ImageView imageView = helper.getView(R.id.img_telstatu);
        if (item.getSelect().equals("0")){
            imageView.setSelected(false);
        }else {
            imageView.setSelected(true);
        }
    }
}
