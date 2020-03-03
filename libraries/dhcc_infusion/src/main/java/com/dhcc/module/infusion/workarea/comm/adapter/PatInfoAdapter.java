package com.dhcc.module.infusion.workarea.comm.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.base.commlibs.utils.ViewUtil;
import com.dhcc.module.infusion.view.SelectorImageView;
import com.dhcc.module.infusion.workarea.comm.bean.PatDetailBean;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommDosingAdapter;

import java.util.List;

/**
 * 患者信息-治疗列表
 * @author:gaoruishan
 * @date:202019-05-06/14:16
 * @email:grs0515@163.com
 */
public class PatInfoAdapter extends BaseQuickAdapter<PatDetailBean.RecOrdListBean, BaseViewHolder> {

    public final static String PAT_STATE_1 = "开始";
    public final static String PAT_STATE_2 = "输液中";
    public final static String PAT_STATE_3 = "完成";
    public final static String PAT_STATE_4 = "异常结束";
    private boolean showDriveLine;


    public PatInfoAdapter(int layoutResId, @Nullable List<PatDetailBean.RecOrdListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PatDetailBean.RecOrdListBean item) {
        helper.setText(R.id.tv_item_date, item.getRecOrdDateTime() + "");
        //列表
        RecyclerView rvItemPat = helper.getView(R.id.rv_item_pat);
        //创建布局管理
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvItemPat, 0);
        CommDosingAdapter commDosingAdapter = AdapterFactory.getCommDosingOrdList();
        rvItemPat.setAdapter(commDosingAdapter);
        commDosingAdapter.replaceData(item.getOrdList());
        //箭头切换
        final SelectorImageView sivSelector = helper.getView(R.id.siv_selector);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sivSelector.toggle();
                helper.setGone(R.id.rv_item_pat, sivSelector.isCheck());
                helper.setGone(R.id.v_line_pat, !sivSelector.isCheck());
                if (!PAT_STATE_2.equals(item.getRecOrdState())) {
                    helper.setGone(R.id.ll_bg_color, !sivSelector.isCheck());
                }
            }
        };
        helper.getView(R.id.ll_head_sw).setOnClickListener(onClickListener);
        helper.getView(R.id.tv_radius).setOnClickListener(onClickListener);
        sivSelector.setOnClickListener(onClickListener);
        //完成/异常 显示--已完成--
        boolean b = PAT_STATE_3.equals(item.getRecOrdState()) || PAT_STATE_4.equals(item.getRecOrdState());
        helper.setGone(R.id.ll_drive_pat, false);
        if (!showDriveLine && b) {
            helper.setGone(R.id.ll_drive_pat, b);
            showDriveLine = b;
        }
        helper.setBackgroundColor(R.id.ll_bg_color, ContextCompat.getColor(mContext, R.color.white));
        TextView tvRadius = helper.getView(R.id.tv_radius);
        tvRadius.setText("第" + item.getTreatNum() + "次治疗");
        //隐藏了倒计时
        helper.setGone(R.id.ll_count, false);
        if (PAT_STATE_2.equals(item.getRecOrdState())) {
            helper.setBackgroundColor(R.id.ll_bg_color, ContextCompat.getColor(mContext, R.color.bg_blue_light));
            //治疗中
            ViewUtil.setBgRadiusColor(tvRadius, 10, "#62ABFF");
        } else if (PAT_STATE_1.equals(item.getRecOrdState())) {
            helper.setText(R.id.tv_state_unit, "");
            // 未治疗
            ViewUtil.setBgRadiusColor(tvRadius, 10, "#E3F1FF", "#62ABFF");
        } else if (PAT_STATE_4.equals(item.getRecOrdState())) {
            helper.setText(R.id.tv_state_unit, item.getRecOrdState());
            helper.setTextColor(R.id.tv_state_unit, Color.parseColor("#FF6262"));
            // 异常
            ViewUtil.setBgRadiusColor(tvRadius, 10, "#FF6262");
            setGone(helper, sivSelector);
        } else if (PAT_STATE_3.equals(item.getRecOrdState())) {
            helper.setText(R.id.tv_state_unit, "正常");
            // 已完成
            ViewUtil.setBgRadiusColor(tvRadius, 10, "#D8D8D8");
            setGone(helper, sivSelector);
        }
    }

    private void setGone(BaseViewHolder helper, SelectorImageView sivSelector) {
        sivSelector.toggle(false);
        helper.setGone(R.id.rv_item_pat, false);
        helper.setGone(R.id.v_line_pat, true);
        helper.setGone(R.id.ll_bg_color, true);
    }
}
