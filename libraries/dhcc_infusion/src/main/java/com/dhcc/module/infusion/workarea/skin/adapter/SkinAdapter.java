package com.dhcc.module.infusion.workarea.skin.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.base.commlibs.utils.ViewUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.skin.bean.SkinListBean;
import com.dhcc.res.infusion.CustomSkinTagView;

import java.util.List;

/**
 * 皮试适配器
 * @author:gaoruishan
 * @date:202019-11-04/15:33
 * @email:grs0515@163.com
 */
public class SkinAdapter extends BaseQuickAdapter<SkinListBean.OrdListBean, BaseViewHolder> {

    public SkinAdapter(@Nullable List<SkinListBean.OrdListBean> data) {
        super(R.layout.item_skin_list, data);
    }

    public SkinListBean.OrdListBean getSelectBean() {
        for (SkinListBean.OrdListBean bean : getData()) {
            if (bean.isSelect()) {
                return bean;
            }
        }
        return null;
    }

    @Override
    protected void convert(BaseViewHolder helper, SkinListBean.OrdListBean item) {
        CustomSkinTagView customSkinTag = helper.getView(R.id.custom_skin_tag);
        customSkinTag.setTextWithColor(item.getTestResult());
        helper.addOnClickListener(R.id.iv_select);
        helper.setText(R.id.tv_name, item.getArcimDesc())
                .setText(R.id.tv_time, item.getCreateDateTime())
                .setText(R.id.tv_operate, item.getDisposeStatDesc())
                .setText(R.id.tv_type, item.getPhcinDesc())
                .setText(R.id.tv_dose, item.getDoseQtyUnit())
                .setText(R.id.tv_user, item.getTestUser())
                .setText(R.id.tv_method, item.getTestMethod())
                .setText(R.id.tv_dose1, item.getPhOrdQtyUnit())
                .setText(R.id.tv_notes, item.getNotes())
                .setText(R.id.tv_skin_time_start, item.getTestStartTime())
                .setText(R.id.tv_creator, item.getCtcpDesc());
        String observeTime = item.getObserveTime();
        String testStartTime = item.getTestStartTime();
        helper.setGone(R.id.ll_time, !TextUtils.isEmpty(observeTime))
                .setGone(R.id.tv_user, !TextUtils.isEmpty(item.getTestUser()))
                .setGone(R.id.tv_method, !TextUtils.isEmpty(item.getTestMethod()))
                .setGone(R.id.v_line_time, !TextUtils.isEmpty(observeTime));
        ViewUtil.setBetweenTime(
                (TextView) helper.getView(R.id.tv_skin_between),
                (TextView) helper.getView(R.id.tv_skin_time_end),
                observeTime, testStartTime);
        helper.getView(R.id.iv_select).setSelected(item.isSelect());
    }


}
