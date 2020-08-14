package com.dhcc.module.nurse.task.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.NormalOrdTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdTaskBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/12
 * Time:14:54
 */
public class TaskNurOrdAdapter extends BaseQuickAdapter<NurOrdTaskBean.TaskDataListBean, BaseViewHolder> {

    public TaskNurOrdAdapter(int layoutResId, @Nullable List<NurOrdTaskBean.TaskDataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NurOrdTaskBean.TaskDataListBean item) {
//        setTextStatus(helper, orderInfoBean);
        helper.setText(R.id.tv_patinfo,"患者信息："+item.getEpisodeID()+item.getBedCode()+item.getName())
                .setText(R.id.tv_1,item.getInterventionTypeName())
                .setText(R.id.tv_2,item.getExecuteItemName())
                .setText(R.id.bl_tv_status,item.getTkStatusName());

//        helper.setText(R.id.tv_oeporderinfo_orderdatetime, orderInfoBean.getSttDateTime())
//                .setText(R.id.tv_oeporderinfo_orderoperate, orderInfoBean.getPhcinDesc())
//                .setText(R.id.tv_oeporderinfo_orderdose, orderInfoBean.getDoseQtyUnit())
//                .setText(R.id.tv_oeporderinfo_orderfrequency, orderInfoBean.getPhcfrCode())
//                .setText(R.id.tv_oeporderinfo_ordercreator, orderInfoBean.getCtcpDesc());
//        LinearLayout llSelect = helper.getView(R.id.ll_select);
//        LinearLayout llOrds = helper.getView(R.id.ll_ords);
//        llOrds.removeAllViews();
//        for (int i = 0; i < item.size(); i++) {
//
//            TextView tvButton = new TextView(mContext);
//            tvButton.setText(item.get(i).getOrderInfo().getArcimDesc());
//            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            titleParams.setMargins(0, 0, 0, 0);//4个参数按顺序分别是左上右下
//            tvButton.setLayoutParams(titleParams);
//            tvButton.setBackgroundResource(R.color.white);
//
//            tvButton.setTextColor(mContext.getResources().getColor(R.color.black));
//            llOrds.addView(tvButton);
//        }
//
//        llSelect.setSelected(item.get(0).getSelect());
    }
    private void setTextStatus(BaseViewHolder helper, NormalOrdTaskBean.BodyUnExecBean.OrdersBean.OrderInfoBean  orderInfoBean) {
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        blTvStatus.setMinWidth((int) mContext.getResources().getDimension(R.dimen.dp_40));
        helper.setGone(R.id.bl_tv_status, !TextUtils.isEmpty(orderInfoBean.getPhcinDesc()));
        String labColor = "#62ABFF";
        blTvStatus.setText(orderInfoBean.getPhcinDesc());
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor(labColor))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_20))
                .build();
        blTvStatus.setBackground(drawable);
    }
}
