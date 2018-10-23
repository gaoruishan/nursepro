package com.dhcc.nursepro.workarea.patevents.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.patevents.bean.EventItemBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.patevents.adapter
 * <p>
 * author Q
 * Date: 2018/10/22
 * Time:15:01
 */
public class PatEventsDetailAdapter extends BaseQuickAdapter<EventItemBean.EventItemListBean, BaseViewHolder> {

    private String eventId;
    private String recId;
    private int isel = 0;

    public PatEventsDetailAdapter(@Nullable List<EventItemBean.EventItemListBean> data) {
        super(R.layout.item_pateventsdetail, data);
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void getEventId() {

    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public void setIsel(int isel) {
        this.isel = isel;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final EventItemBean.EventItemListBean item) {

        helper.setText(R.id.tv_patevents_eventtype, item.getDesc())
                .addOnClickListener(R.id.tv_patevents_eventtype);
        //修改事件，判断原有type
        //默认选中第一个
        if (eventId == null) {
            eventId = item.getId();
        }
        if (recId != null) {
            //                isel = Integer.parseInt(eventId);
            if (item.getId().equals(eventId)) {
                isel = helper.getAdapterPosition();
            } else {
                isel = -1;
            }
        }
        final TextView tvsel = helper.getView(R.id.tv_patevents_eventtype);
        if (isel == helper.getAdapterPosition()) {
            switch (item.getDesc()) {
                case "外出":
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_1));
                    break;
                case "死亡":
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_2));
                    break;
                case "转出":
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_3));
                    break;
                case "出院":
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_4));
                    break;
                case "分娩":
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_5));
                    break;
                case "手术":
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_6));
                    break;
                case "转入":
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_7));
                    break;
                case "入院":
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_8));
                    break;
                default:
                    tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_default));
                    break;

            }
            tvsel.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            tvsel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_unselect));
            tvsel.setTextColor(mContext.getResources().getColor(R.color.patevents_tv_unsel_color));
        }
    }
}
