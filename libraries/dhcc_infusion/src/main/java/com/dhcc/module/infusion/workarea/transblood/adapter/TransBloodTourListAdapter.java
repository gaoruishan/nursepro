package com.dhcc.module.infusion.workarea.transblood.adapter;

import android.support.annotation.Nullable;

import com.base.commlibs.comm.BaseCommViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.transblood.bean.PatrolRecordBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-03-09/14:56
 * @email:grs0515@163.com
 */
public class TransBloodTourListAdapter extends BaseTransBloodAdapter<PatrolRecordBean, BaseCommViewHolder> {

    public TransBloodTourListAdapter(int layoutResId, @Nullable List<PatrolRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseCommViewHolder helper, PatrolRecordBean item) {
        super.convert(helper, item);

        helper.setTextData(R.id.tv_item_bloodtour_datetime, item.getDate() + " " + item.getTime())
                .setTextData(R.id.tv_item_bloodtour_nurse, item.getUser())
                .setTextData(R.id.tv_item_bloodtour_effect, item.getEffect())
                .setTextData(R.id.tv_item_bloodop_opstatus, item.getTourType())
                .setTextData(R.id.tv_item_speed, item.getSpeed(), "","滴/分")
                .setTextData(R.id.tv_item_temp, item.getTemperature(), "","℃")
                .setTextData(R.id.tv_item_pulse, item.getPulse(), "","次/分")
                .setTextData(R.id.tv_item_sys, item.getSysPressure(), "","mmHg(收)")
                .setTextData(R.id.tv_item_dia, item.getDiaPressure(), "","mmHg(舒)")
                .setTextData(R.id.tv_item_bloodtour_situation, item.getSituation());
    }
}
