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
public class MessageConsultationAdapter extends BaseQuickAdapter<MessageBean.ConPatListBean,BaseViewHolder> {


    public MessageConsultationAdapter(@Nullable List<MessageBean.ConPatListBean> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean.ConPatListBean item) {
        ImageView imgPatientSex = helper.getView(R.id.img_message_patientsex);
        LinearLayout llMessageRightMenu = helper.getView(R.id.ll_message_rightmenu);

        if ("F".equals(item.getSex())) {
            imgPatientSex.setSelected(true);
        } else {
            imgPatientSex.setSelected(false);
        }

        llMessageRightMenu.setVisibility(View.VISIBLE);

        helper.setText(R.id.tv_message_bedno, "".equals(item.getBedCode()) ? "未分" : item.getBedCode() + "床")
                .setText(R.id.tv_message_patientname, item.getPatName())
                .setText(R.id.tv_message_patientloc, item.getPatLoc())
                .setText(R.id.tv_message_doctorname, item.getConDocdesc())
                .addOnClickListener(R.id.ll_message_rightmenu);
    }
}
