package com.dhcc.module.nurse.task.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.TempTaskBean;
import com.nex3z.flowlayout.FlowLayout;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/11
 * Time:14:49
 */
public class TaskTempAdapter extends BaseQuickAdapter<TempTaskBean.TempDateListBean, BaseViewHolder> {

    public TaskTempAdapter(int layoutResId, @Nullable List<TempTaskBean.TempDateListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TempTaskBean.TempDateListBean item) {
        helper.setText(R.id.tv_patinfo,item.getBedCode()+" "+item.getName()+" 性别："+item.getSex())
                .setText(R.id.tv_needtime,"需测时间："+item.getTimeKey());
        FlowLayout flowLayout = helper.getView(R.id.flow_need);
        flowLayout.removeAllViews();
        for (int i = 0; i < item.getObsDataList().size(); i++) {
            TextView tvNeed = new TextView(mContext);
            String tvValue = item.getObsDataList().get(i).getObsValue().equals("")?"":"："+item.getObsDataList().get(i).getObsValue();
            tvNeed.setText(item.getObsDataList().get(i).getObsDesc()+tvValue);
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            titleParams.setMargins(0, 0, 5, 0);//4个参数按顺序分别是左上右下
            tvNeed.setLayoutParams(titleParams);
            tvNeed.setBackgroundResource(R.color.white);
            tvNeed.setTextSize(11);
            tvNeed.setTextColor(mContext.getResources().getColor(R.color.blue_dark));
            flowLayout.addView(tvNeed);
        }
    }
}
