package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.grs.dhcc_res.R;

import java.util.List;


/**
 * 滴速界面
 * @author:gaoruishan
 * @date:202019-04-26/16:04
 * @email:grs0515@163.com
 */
public class CustomSpeedView extends LinearLayout implements View.OnClickListener {


    private View view;
    private LinearLayout llRemove, llAdd;
    private TextView tvSpeed;
    private View llChannel;
    private RecyclerView rvList;
    private String selectChannel;

    public CustomSpeedView(Context context) {
        this(context, null);
    }

    public CustomSpeedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSpeedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(context).inflate(R.layout.custom_speed_view, this, false);
        addView(view);
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        llRemove = view.findViewById(R.id.ll_remove);
        llRemove.setOnClickListener(this);
        llAdd = view.findViewById(R.id.ll_add);
        llAdd.setOnClickListener(this);
        tvSpeed = view.findViewById(R.id.tv_speed);
        llChannel = view.findViewById(R.id.ll_channel);
        rvList = view.findViewById(R.id.rv_list);
    }

    /**
     * 设置通道数据
     * @param list
     * @param callBack
     * @return
     */
    public CustomSpeedView setChannelList(final List<SheetListBean> list, final SimpleCallBack<SheetListBean> callBack) {
        llChannel.setVisibility(GONE);
        if (list != null && list.size() > 0) {
            llChannel.setVisibility(VISIBLE);
            //默认第一个选中
            list.get(0).setSelect(true);
            RecyclerViewHelper.setGridRecyclerView(getContext(), rvList, 4, 0, false);
            final ChannelAdapter channelAdapter = new ChannelAdapter(list);
            rvList.setAdapter(channelAdapter);
//            channelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                }
//            });
        }
        return this;
    }

    public String getSelectChannel() {
        return selectChannel == null ? "" : selectChannel;
    }

    @Override
    public void onClick(View v) {
        int speed = getSpeed();
        if (v.getId() == R.id.ll_remove) {
            speed = speed > 0 ? speed - 1 : 0;
        }
        if (v.getId() == R.id.ll_add) {
            speed = speed + 1;
        }
        setSpeed(String.valueOf(speed));
    }

    /**
     * 获取速度
     * @return
     */
    public int getSpeed() {
        String s = tvSpeed.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            int integer = Integer.valueOf(s);
            return integer > 0 ? integer : 0;
        }
        return 0;
    }

    /**
     * 设置滴速
     * @param speed
     */
    public CustomSpeedView setSpeed(String speed) {
        if (!TextUtils.isEmpty(speed)) {
            tvSpeed.setText(speed);
        }
        return this;
    }

    private class ChannelAdapter extends BaseQuickAdapter<SheetListBean, BaseViewHolder> {

        public ChannelAdapter(@Nullable List<SheetListBean> data) {
            super(R.layout.dhcc_item_speed_channel, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SheetListBean item) {
            helper.getView(R.id.iv_icon).setSelected(item.isSelect());
            helper.setText(R.id.tv_name, item.getDesc());
            final int position = helper.getAdapterPosition();
            helper.getView(R.id.ll_item).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < getData().size(); i++) {
                        getData().get(i).setSelect(i == position);
                    }
                    SheetListBean bean = getData().get(position);
                    selectChannel = bean.getDesc();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
