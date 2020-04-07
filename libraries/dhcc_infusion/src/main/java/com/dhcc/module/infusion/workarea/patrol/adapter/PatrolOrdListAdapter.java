package com.dhcc.module.infusion.workarea.patrol.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.view.SelectTextView;
import com.dhcc.module.infusion.view.SelectorImageView;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommQuickAdapter;
import com.dhcc.module.infusion.workarea.dosing.bean.OeoreGroupBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.Arrays;
import java.util.List;

/**
 * 巡视列表
 * @author:gaoruishan
 * @date:202019-04-28/10:08
 * @email:grs0515@163.com
 */
public class PatrolOrdListAdapter extends CommQuickAdapter<OrdListBean, BaseViewHolder> {


    public PatrolOrdListAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdListBean item) {
        SelectTextView stv = helper.getView(R.id.stv);
        this.setCommData(helper, item);
        this.setCommItemClick(helper, R.id.ll_item);
        helper.setGone(R.id.siv_selector, false);
        if (Arrays.asList(OrdState.STATE_4).contains(item.getOrdState())) {
            //显示主医嘱
            helper.setGone(R.id.ll_block, true);
            stv.setUnSelectedBg(R.drawable.infusion_bg_circle_gray);
            stv.setUnSelectTextColor(0xffffffff);
            if (item.getOeoreSubList() != null) {
                if (item.getOeoreSubList().size() > 1) {
                    swichUi(helper);
                }
                if (item.getOeoreSubList().size() >= 1) {
                    OeoreGroupBean bean = item.getOeoreSubList().get(0);
                    helper.setText(R.id.tv_title, bean.getArcimDesc()).setText(R.id.tv_content, bean.getDoseQtyUnit()
                            + "   " + bean.getPhOrdQtyUnit());
                }
            }
        }
    }

    private void swichUi(final BaseViewHolder helper) {
        final SelectorImageView sivSelector = helper.getView(R.id.siv_selector);
        helper.setGone(R.id.siv_selector, true);
        helper.setGone(R.id.rv_child, sivSelector.isCheck());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sivSelector.toggle();
                helper.setGone(R.id.rv_child, sivSelector.isCheck());
                helper.setGone(R.id.ll_block,!sivSelector.isCheck());
            }
        };
        helper.setOnClickListener(R.id.ll_block, onClickListener);
        sivSelector.setOnClickListener(onClickListener);
    }

    @Override
    protected void setCommItemData(Bundle bundle, OrdListBean ordListBean) {
        super.setCommItemData(bundle, ordListBean);
        bundle.putString(ID, ordListBean.getOeoreId());
    }
}
