package com.dhcc.module.infusion.message.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.message.bean.MessageInfusionBean;
import com.dhcc.res.infusion.CountView;
import com.dhcc.res.infusion.CustomPatView;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-23/15:57
 * @email:grs0515@163.com
 */
public class MessageInfusionAdapter extends BaseQuickAdapter<MessageInfusionBean.InfusionTimeListBean, BaseViewHolder> {


    public MessageInfusionAdapter(int layoutResId, @Nullable List<MessageInfusionBean.InfusionTimeListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageInfusionBean.InfusionTimeListBean item) {
        helper.setVisible(R.id.ll_count, !TextUtils.isEmpty(item.getOverTime()));
        if(!TextUtils.isEmpty(item.getOverTime())){
            CountView cvCount = helper.getView(R.id.cv_count);
            cvCount.getTitleName().setVisibility(View.GONE);
            cvCount.getOneDay().setTextColor(Color.parseColor("#FFFF6EA4"));
            cvCount.getOneDay().setTextSize(mContext.getResources().getDimension(R.dimen.dp_13));
            cvCount.start(Long.valueOf(item.getOverTime()),CountView.ONE_DAY);
        }
        helper.setText(R.id.tv_message_regno, item.getPatRegNo())
                .setText(R.id.tv_message_name, item.getPatName())
                .addOnClickListener(R.id.ll_message_content)
                .setImageResource(R.id.img_message_sex, CustomPatView.getPatSexDrawable(item.getPatSex()));

    }
}
