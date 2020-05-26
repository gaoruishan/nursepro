package com.dhcc.module.health.workarea.patlist.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.patlist.bean.PatListBean;
import com.dhcc.module.health.workarea.patlist.bean.PatOrdersBean;
import com.noober.background.drawable.DrawableCreator;

import org.kobjects.util.Strings;

import java.util.List;

/**
 * com.dhcc.module.health.workarea.patlist.adapter
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:16:11
 */
public class PatOrdersAdapter extends BaseQuickAdapter<PatOrdersBean.CureInfoListBean, BaseViewHolder> {

        public PatOrdersAdapter(@Nullable List<PatOrdersBean.CureInfoListBean> data) {
                super(R.layout.health_item_order_execute, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, PatOrdersBean.CureInfoListBean item) {
                helper.setText(R.id.tv_dose, item.getOrderDoseQty())
                        .setText(R.id.tv_frequency, item.getOrderFreq())
                        .setText(R.id.tv_creator, item.getUserAdd())
                        .setText(R.id.tv_operate, item.getHandelDesc())
                        .setText(R.id.tv_time, item.getOrderSttDate())
                        .setText(R.id.tv_patinfo,item.getPatientName());
                TextView blTvStatus = helper.getView(R.id.bl_tv_status);
                blTvStatus.setText(item.getOrderStatus());

                TextView tvExec = helper.getView(R.id.bl_tv_exec);
                tvExec.setText(item.getDisposeStatdesc());
                String labColor = "#62ABFF";
                Drawable drawable = new DrawableCreator.Builder()
                        .setSolidColor(Color.parseColor(labColor))
                        .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_10))
                        .build();
                blTvStatus.setBackground(drawable);
                if (item.getDisposeStatdesc()!=null && item.getDisposeStatdesc().contains("^")){
                        String[] strExe=item.getDisposeStatdesc().split("\\^");
                        Drawable drawableExe = new DrawableCreator.Builder()
                                .setSolidColor(Color.parseColor(strExe[1]))
                                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_10))
                                .build();

                        tvExec.setText(strExe[0]);
                        tvExec.setBackground(drawableExe);
                }



//        helper.setGone(R.id.tv_time, !TextUtils.isEmpty(item.getOrderSttDate()));
//        helper.setGone(R.id.bl_tv_status, !TextUtils.isEmpty(item.getOrderStatusCode()));
                helper.setText(R.id.tv_name, item.getArcimDesc());

                LinearLayout llSel = helper.getView(R.id.ll_exec_orderselect);
                if (item.getSelect().equals("1")){
                        llSel.setSelected(true);
                }else {
                        llSel.setSelected(false);
                }
        }
}