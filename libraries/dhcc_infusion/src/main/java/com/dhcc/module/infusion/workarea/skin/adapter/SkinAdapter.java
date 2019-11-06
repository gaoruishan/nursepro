package com.dhcc.module.infusion.workarea.skin.adapter;

import android.support.annotation.Nullable;

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

    @Override
    protected void convert(BaseViewHolder helper, SkinListBean.OrdListBean item) {
        CustomSkinTagView customSkinTag = helper.getView(R.id.custom_skin_tag);
        customSkinTag.setText("皮").setTextColor("#ff00ff00").setCircleColor("#666666");
        helper.addOnClickListener(R.id.iv_select);
        helper.setText(R.id.tv_name, item.getArcimDesc())
                .setText(R.id.tv_time, item.getCreateDateTime())
                .setText(R.id.tv_operate, item.getDisposeStatDesc())
                .setText(R.id.tv_type, item.getPhcinDesc())
                .setText(R.id.tv_dose, item.getDoseQtyUnit())
                .setText(R.id.tv_dose1,item.getPhcinDesc())
                .setText(R.id.tv_creator, item.getCtcpDesc());
        helper.getView(R.id.iv_select).setSelected(item.isSelect());
    }
}
