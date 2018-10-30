package com.dhcc.nursepro.message.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.message.bean.MessageBean;

import java.util.List;

/**
 * MessageAdapter
 *
 * @author DevLix126
 * @date 2018/9/13
 */
public class MessageNewOrderAdapter extends BaseQuickAdapter<MessageBean.NewOrdPatListBean, BaseViewHolder> {


    public MessageNewOrderAdapter(@Nullable List<MessageBean.NewOrdPatListBean> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean.NewOrdPatListBean item) {
        ImageView imgPatientSex = helper.getView(R.id.img_message_patientsex);
        TextView tvPatientLoc = helper.getView(R.id.tv_message_patientloc);
        TextView tvPatientDoctorName = helper.getView(R.id.tv_message_doctorname);
        LinearLayout llMessageRightMenu = helper.getView(R.id.ll_message_rightmenu);

        if ("F".equals(item.getSex())) {
            imgPatientSex.setSelected(true);
        } else {
            imgPatientSex.setSelected(false);
        }

        tvPatientLoc.setVisibility(View.GONE);
        tvPatientDoctorName.setVisibility(View.GONE);
        llMessageRightMenu.setVisibility(View.GONE);

        helper.setText(R.id.tv_message_bedno, "".equals(item.getBedCode()) ? "未分床" : item.getBedCode())
                .setText(R.id.tv_message_patientname, item.getPatName());
    }
}
