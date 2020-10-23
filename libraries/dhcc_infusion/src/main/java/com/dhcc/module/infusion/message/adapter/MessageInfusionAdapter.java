package com.dhcc.module.infusion.message.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.base.commlibs.utils.ViewUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.res.util.MessageUtil;
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
        helper.getView(R.id.ll_message_content).setSelected(item.isSelect());
        String testStartTime = item.getInitDataTime();
        String observeTime = item.getOverTime();
        if(!TextUtils.isEmpty(item.getOverTime())){
            String formatEndTime = ViewUtil.getBetweenTime(observeTime, testStartTime, 1000);
            CountView cvCount = helper.getView(R.id.cv_count);
            boolean isOk = MessageUtil.setCountTime(mContext, cvCount, testStartTime, formatEndTime, true, new CountView.OnCountViewStatusListener() {
                @Override
                public void onStop() {
                    helper.setGone(R.id.tv_time_ok, true);
                    helper.setGone(R.id.ll_count, false);
                }
            });
            helper.setGone(R.id.ll_count, isOk);
            helper.setGone(R.id.tv_time_ok, !isOk);
        }

        helper.setText(R.id.tv_message_regno, item.getPatRegNo())
                .setText(R.id.tv_message_name, item.getPatName())
                .addOnClickListener(R.id.ll_message_content)
                .setImageResource(R.id.img_message_sex, CustomPatView.getPatSexDrawable(item.getPatSex()));

    }
}
