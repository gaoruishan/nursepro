package com.dhcc.module.infusion.workarea.patrol.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.view.SelectTextView;
import com.dhcc.module.infusion.view.SelectorImageView;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommQuickAdapter;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.List;

/**
 * 巡视列表
 * @author:gaoruishan
 * @date:202019-04-28/10:08
 * @email:grs0515@163.com
 */
public class PatrolOrdListAdapter extends CommQuickAdapter<OrdListBean, BaseViewHolder> {

    public static final String OrdStateOK = "已输完";

    public PatrolOrdListAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdListBean item) {
        SelectTextView stv = helper.getView(R.id.stv);
        this.setCommData(helper, item);
        this.setCommItemClick(helper, R.id.ll_item);
        helper.setGone(R.id.siv_selector, false);
        if (OrdStateOK.equals(item.getOrdState())) {
            swichUi(helper, stv);
        }
    }

    private void swichUi(final BaseViewHolder helper, SelectTextView stv) {
        final SelectorImageView sivSelector = helper.getView(R.id.siv_selector);
        stv.setUnSelectedBg(R.drawable.infusion_bg_circle_gray);
        stv.setUnSelectTextColor(0xffffffff);
        helper.setGone(R.id.siv_selector, true);
        helper.setGone(R.id.rv_child, sivSelector.isCheck());
        helper.setGone(R.id.v_block, !sivSelector.isCheck());
        sivSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sivSelector.toggle();
                helper.setGone(R.id.rv_child, sivSelector.isCheck());
                helper.setGone(R.id.v_block, !sivSelector.isCheck());
            }
        });
    }

    @Override
    protected void setCommItemData(Bundle bundle, OrdListBean ordListBean) {
        super.setCommItemData(bundle, ordListBean);
        bundle.putString(ID, ordListBean.getOeoreId());
    }
}
