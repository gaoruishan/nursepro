package com.dhcc.module.infusion.workarea.transblood.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.comm.BaseCommViewHolder;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.transblood.bean.BloodInfoBean;
import com.dhcc.res.infusion.CustomSkinTagView;
import com.dhcc.res.infusion.CustomStatusView;

import java.util.List;

/**
 * 输血列表
 * @author:gaoruishan
 * @date:202020-03-06/14:36
 * @email:grs0515@163.com
 */
public class TransBloodListAdapter extends BaseTransBloodAdapter<BloodInfoBean, BaseCommViewHolder> {

    public TransBloodListAdapter(int layoutResId, @Nullable List<BloodInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseCommViewHolder helper, final BloodInfoBean item) {
        super.convert(helper, item);
        if (scanCode != null) {
            int color = scanCode.contains(item.getBloodBagNo()) ? R.color.dhcc_blue_dark2 : R.color.white;
            helper.setBackgroundColor(R.id.ll_item, ContextCompat.getColor(mContext, color));
        }
        //点击事件
        setCommItemClick(helper, helper.itemView);
        //设置状态/用户/时间
        setStatusUserTime(helper, item);
        //同意书
        CustomSkinTagView customSkinTagView = helper.getView(R.id.custom_tag);
        helper.setGone(R.id.custom_tag, !TextUtils.isEmpty(item.getAgreeStatus()));
        customSkinTagView.setText(item.getAgreeStatus(), "同意", CustomSkinTagView.yinColor)
                .setText(item.getAgreeStatus(), "拒绝", CustomSkinTagView.yangColor);
        //设置状态
        setStatus(helper, item);

        //终止原因
        View line = helper.getView(R.id.line_item_bloodop_endreason);
        TextView bloodStopReason = helper.getView(R.id.tv_item_bloodop_endreason);
        if (OrdState.TYPE_BLOOD_E.equals(item.getEndType())) {
            line.setVisibility(View.GONE);
            bloodStopReason.setVisibility(View.GONE);
        } else if (OrdState.TYPE_BLOOD_Z.equals(item.getEndType())) {
            line.setVisibility(View.VISIBLE);
            bloodStopReason.setVisibility(View.VISIBLE);
            bloodStopReason.setText("终止原因：" + item.getStopReason());
        }

        helper.setText(R.id.tv_item_bloodop_bagno, item.getBloodBagNo())
                .setText(R.id.tv_item_bloodop_productdesc, item.getBloodProducDesc())
                .setText(R.id.tv_item_bloodop_bloodtype, item.getBldTyp())
                .setText(R.id.tv_time, item.getValidTime())
                .setText(R.id.tv_operate, item.getBloodMatchResults())
                .setText(R.id.tv_dose, item.getBloodMl())
        ;
    }

    @Override
    protected void setJumpDetailData(Bundle bundle, BloodInfoBean item) {
        bundle.putString("bloodRowId", item.getBloodRowId());
    }

    private void setStatusUserTime(BaseViewHolder helper, BloodInfoBean item) {

        helper.setGone(R.id.custom_check_rec, !TextUtils.isEmpty(item.getReciveFirstUser()));
        helper.setGone(R.id.custom_check_start, !TextUtils.isEmpty(item.getTransFirstUser()));
        helper.setGone(R.id.custom_check_finish, !TextUtils.isEmpty(item.getTransEdUser()));
        CustomStatusView customCheckRec = helper.getView(R.id.custom_check_rec);
        customCheckRec.setStatus(!TextUtils.isEmpty(item.getReciveTag()) ? item.getReciveTag() : "签")
                .setUser(item.getReciveFirstUser() + "/" + item.getReciveSecondUser())
                .setTime(item.getReciveDate() + " " + item.getReciveTime());

        CustomStatusView customCheckStart = helper.getView(R.id.custom_check_start);
        customCheckStart.setStatus(!TextUtils.isEmpty(item.getTransTag()) ? item.getTransTag() : "输")
                .setUser(item.getTransFirstUser() + "/" + item.getTransSecondUser())
                .setTime(item.getTransStartDate() + " " + item.getTransStartTime());

        CustomStatusView customCheckFinish = helper.getView(R.id.custom_check_finish);
        customCheckFinish.setStatus(!TextUtils.isEmpty(item.getTransEndTag()) ? item.getTransTag() : "完")
                .setUser(item.getTransEdUser())
                .setTime(item.getTransEdDate() + " " + item.getTransEdTime());
    }

    private void setStatus(BaseViewHolder helper, BloodInfoBean item) {
        TextView tvStatus = helper.getView(R.id.tv_item_bloodop_opstatus);
        helper.setGone(R.id.tv_item_bloodop_opstatus, !TextUtils.isEmpty(item.getStatus()));
        if (OrdState.STATE_BLOOD_R[0].equals(item.getStatus())) {
            tvStatus.setText(OrdState.STATE_BLOOD_R[1]);
            tvStatus.setBackgroundResource(R.drawable.bg_oval_blue);
        } else if (OrdState.STATE_BLOOD_S[0].equals(item.getStatus())) {
            tvStatus.setText(OrdState.STATE_BLOOD_S[1]);
            tvStatus.setBackgroundResource(R.drawable.bg_oval_yellow);
        } else if (OrdState.STATE_BLOOD_E[0].equals(item.getStatus())) {
            if (OrdState.TYPE_BLOOD_E.equals(item.getEndType())) {
                tvStatus.setText(OrdState.STATE_BLOOD_E[1]);
                tvStatus.setBackgroundResource(R.drawable.bg_oval_greenlight);
            } else if (OrdState.TYPE_BLOOD_Z.equals(item.getEndType())) {
                tvStatus.setText(OrdState.STATE_BLOOD_E[2]);
                tvStatus.setBackgroundResource(R.drawable.bg_oval_red);
            }
        } else if (OrdState.STATE_BLOOD_RE[0].equals(item.getStatus())) {
            tvStatus.setText(OrdState.STATE_BLOOD_RE[0]);
            tvStatus.setBackgroundResource(R.drawable.bg_oval_green);
        }
    }
}
