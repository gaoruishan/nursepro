package com.dhcc.nursepro.workarea.rfid.adapter;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.allotbed.bean.AllotBedInfoBean;
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
        helper.setText(R.id.tv_pat,item.getName())
                .setText(R.id.tv_bed,item.getBedCode().replace("床","")+"床")
                .setImageResource(R.id.img_rfid_sex,item.getSex().equals("男")||item.getSex().equals("女")?
                        (item.getSex().equals("男")?R.drawable.dhcc_sex_male:R.drawable.dhcc_sex_female):null);
        LinearLayout llRfid = helper.getView(R.id.ll_rifd);
        llRfid.setSelected(item.getIfBind().equals("1"));
    }
}
