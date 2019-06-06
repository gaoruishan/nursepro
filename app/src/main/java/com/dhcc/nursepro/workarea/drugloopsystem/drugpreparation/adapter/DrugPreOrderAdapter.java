package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.GetOrdInfoBean;

import java.util.List;

public class DrugPreOrderAdapter extends BaseQuickAdapter<GetOrdInfoBean.OeoreGroupBean, BaseViewHolder> {
    private ImageView imgDrugpreorderSelect;
    private View lineDrugpreorder;

    private int size;

    public DrugPreOrderAdapter(@Nullable List<GetOrdInfoBean.OeoreGroupBean> data) {
        super(R.layout.item_drugpre_orders, data);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrdInfoBean.OeoreGroupBean item) {

        imgDrugpreorderSelect = helper.getView(R.id.img_drugpreorder_select);
        lineDrugpreorder = helper.getView(R.id.line_drugpreorders);

        helper.setText(R.id.tv_drugpreorder_name, item.getArcimDesc())
                .setText(R.id.tv_drugpreorder_dose, item.getDoseQtyUnit())
                .setText(R.id.tv_drugpreorder_code, item.getArcimCode());

        if (item.isScan()) {
            imgDrugpreorderSelect.setVisibility(View.VISIBLE);
            imgDrugpreorderSelect.setSelected(true);
        } else {
            imgDrugpreorderSelect.setVisibility(View.GONE);
            imgDrugpreorderSelect.setSelected(false);
        }

        if (helper.getLayoutPosition() < size - 1) {
            lineDrugpreorder.setVisibility(View.VISIBLE);
        } else {
            lineDrugpreorder.setVisibility(View.GONE);
        }
    }
}
