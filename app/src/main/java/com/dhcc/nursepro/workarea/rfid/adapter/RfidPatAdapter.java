package com.dhcc.nursepro.workarea.rfid.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.rfid.bean.RfidPatBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.rfid.adapter
 * <p>
 * author Q
 * Date: 2021/6/23
 * Time:9:41
 */
public class RfidPatAdapter extends BaseQuickAdapter<RfidPatBean.PatInfoListBean, BaseViewHolder> {

    public RfidPatAdapter(@Nullable List<RfidPatBean.PatInfoListBean> data) {
        super(R.layout.item_rfid_bind, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RfidPatBean.PatInfoListBean item) {

        int dra = item.getSex().equals("女") ? R.drawable.dhcc_sex_female : R.drawable.dhcc_sex_male;
        helper.setText(R.id.tv_pat,item.getName())
                .setGone(R.id.img_rfid_sex, !TextUtils.isEmpty(item.getSex()))
                .setText(R.id.tv_bed,item.getBedCode().replace("床","")+"床")
                .setImageResource(R.id.img_rfid_sex,dra);
        LinearLayout llRfid = helper.getView(R.id.ll_rifd);
        llRfid.setSelected(item.getIfBind().equals("1"));
    }
}
