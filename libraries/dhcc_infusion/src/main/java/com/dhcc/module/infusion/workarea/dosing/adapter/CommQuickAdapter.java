package com.dhcc.module.infusion.workarea.dosing.adapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.UniversalActivity;
import com.base.commlibs.utils.ViewUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.view.SelectTextView;
import com.dhcc.module.infusion.workarea.MedicalDetailFragment;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.dosing.bean.OeoreGroupBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共适配器
 * @author:gaoruishan
 * @date:202019-04-26/14:20
 * @email:grs0515@163.com
 */
public abstract class CommQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    public static final String ID = "id";

    protected String scanInfo;

    public CommQuickAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    /**
     * 当前扫码信息
     * @param scanInfo
     */
    public void setCurrentScanInfo(String scanInfo) {
        this.scanInfo = scanInfo;
    }

    /**
     * 设置R.layout.item_comm_posing 公共数据
     * @param helper
     * @param item
     */
    protected void setCommData(BaseViewHolder helper, OrdListBean item) {
        int position = helper.getAdapterPosition();
        // 显示状态
        helper.setText(R.id.tv_state, item.getOrdState());
        if ("异常结束".equals(item.getOrdState())) {
            helper.setTextColor(R.id.tv_state, ContextCompat.getColor(mContext, R.color.ic_ord_state_unexpect));
        }
        // 医生说明
        boolean empty = TextUtils.isEmpty(item.getNotes()) && TextUtils.isEmpty(item.getWayNo());
        helper.setGone(R.id.ll_notes, !empty);
        helper.setText(R.id.tv_notes, "备注：" + item.getNotes() + " 通道" + item.getWayNo());
        // 选中状态
        setSelectTextView(helper, item, position);
        // 去掉最后一分割线
        helper.setGone(R.id.v_line, position + 1 != getItemCount());
        // 子适配器
        RecyclerView rvChild = helper.getView(R.id.rv_child);
        //创建布局管理
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvChild, 0);

        ChildAdapter childAdapter = new ChildAdapter(R.layout.item_posing_child, item.getOeoreSubList(), item.getOeoreId().equals(scanInfo));
        childAdapter.setOrdState(item.getOrdState());
        rvChild.setAdapter(childAdapter);

        //父item获取事件
        preventChildRecyclerViewClick(rvChild, helper.itemView);
    }

    /**
     * 设置圆圈选中
     * @param helper
     * @param item
     * @param position
     */
    protected void setSelectTextView(BaseViewHolder helper, OrdListBean item, int position) {
        SelectTextView stv = helper.getView(R.id.stv);
        setCustomSelectBg(stv,item);
        stv.unSelect();
        if (scanInfo != null) {
            scanInfo = scanInfo.replaceAll("-", "||");
            if (scanInfo.equals(item.getOeoreId())) {
                stv.onSelected();
            }
        }
        // 一直加粗
        stv.setAlwaysBold(true);
        stv.setEnabled(false);
        stv.setClickable(false);
        // 变大
        String s = (position + 1) + "/" + getItemCount();
        ViewUtil.setTextRelativeSizeSpan(stv, s, 0, s.indexOf("/"));
    }

    protected void setCustomSelectBg(SelectTextView stv, OrdListBean item) {

    }

    /**
     * 阻止子RecyclerView覆盖点击事件
     * @param rvChild
     * @param parent
     */
    protected void preventChildRecyclerViewClick(RecyclerView rvChild, final View parent) {
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //模拟父控件的点击
                    parent.performClick();
                }
                return false;
            }
        };
        rvChild.setOnTouchListener(onTouchListener);
    }

    /**
     * 需要 重写@setCommItemData
     * @param helper
     * @param itemId
     */
    protected void setCommItemClick(BaseViewHolder helper, @IdRes int itemId) {
        helper.addOnClickListener(itemId);
        setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                // 医嘱详情
                Bundle bundle = new Bundle();
                T t = mData.get(position);
                setCommItemData(bundle, t);
                startFragment(MedicalDetailFragment.class, bundle);
            }
        });
    }

    /**
     * 子类填充数据
     * @param bundle
     * @param t
     */
    protected void setCommItemData(Bundle bundle, T t) {

    }

    /**
     * 使用UniversalActivity启动给定的Fragment
     * @param fragCls 待启动Fragment
     * @param args    传递给Fragment的参数,可空
     */
    public void startFragment(@NonNull Class<? extends BaseFragment> fragCls, @Nullable Bundle args) {
        if (mContext != null) {
            Intent intent = new Intent(mContext, UniversalActivity.class);
            intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
            intent.putExtra(UniversalActivity.RootFragmentArgs, args);
            mContext.startActivity(intent);
        }
    }

    /**
     * 获取空集合
     * @return
     */
    public List<T> getList() {
        return new ArrayList<T>();
    }

    public static class ChildAdapter extends BaseQuickAdapter<OeoreGroupBean, BaseViewHolder> {

        private boolean select;
        private @ColorRes
        int color;

        public ChildAdapter(int layoutResId, @Nullable List<OeoreGroupBean> data, boolean select) {
            super(layoutResId, data);
            this.select = select;
        }

        @Override
        protected void convert(BaseViewHolder helper, OeoreGroupBean item) {
            TextView tvTitle = helper.getView(R.id.tv_title);
            tvTitle.setTypeface(select ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
            helper.setText(R.id.tv_title, item.getArcimDesc()).setText(R.id.tv_content, item.getDoseQtyUnit()
                    + "   " + item.getPhOrdQtyUnit());
            tvTitle.setEnabled(false);
            tvTitle.setClickable(false);
            if (color != 0) {
                helper.setBackgroundColor(R.id.ll_item_child, ContextCompat.getColor(mContext, color));
            } else {
                //选中的背景变色
                if (select) {
                    helper.setBackgroundColor(R.id.ll_item_child, ContextCompat.getColor(mContext, R.color.dhcc_blue_dark2));
                }
            }
        }

        public void setOrdState(String ordState) {
            this.color = OrdState.getOrdStateColor(ordState);
        }
    }

}
