package com.dhcc.module.nurse.task.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.NurRateTaskBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/12
 * Time:14:54
 */
public class TaskNurRateAdapter extends BaseQuickAdapter<NurRateTaskBean.RecDataBean, BaseViewHolder> {

    public TaskNurRateAdapter(int layoutResId, @Nullable List<NurRateTaskBean.RecDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NurRateTaskBean.RecDataBean item) {
        if (item.getRecordScore() != null && item.getRecordScore().size() > 0) {
            NurRateTaskBean.RecDataBean.RecordScoreBean recordScore = item.getRecordScore().get(0);
            helper.setText(R.id.bl_tv_status, recordScore.getName())
                    .setText(R.id.tv_2, recordScore.getRecordName());
        }
        boolean isSex = "M".equals(item.getSex()) || "男".equals(item.getSex());
        helper.setBackgroundRes(R.id.view_sex, isSex ? R.drawable.dhcc_sex_male : R.drawable.dhcc_sex_female);
        helper.setText(R.id.tv_patinfo, item.getBedCode() + " " + item.getPatientName());

    }

    /**
     * 过滤emrCode
     * @param recData
     * @param emrCode
     */
    public void setInitData(List<NurRateTaskBean.RecDataBean> recData, String emrCode) {
        List<NurRateTaskBean.RecDataBean> mRecData = new ArrayList<>();
        if (recData != null) {
            for (NurRateTaskBean.RecDataBean recDatum : recData) {
                if (recDatum.getRecordScore() != null) {
                    if (emrCode.equals(recDatum.getRecordScore().get(0).getLinkCode())) {
                        mRecData.add(recDatum);
                    }
                }
            }
        }
        setNewData(mRecData);
    }
}
