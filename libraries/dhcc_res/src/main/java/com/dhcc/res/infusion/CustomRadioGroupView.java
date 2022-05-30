package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.dhcc.res.util.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.BaseView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 一组单选框
 * @author:gaoruishan
 * @date:202020-07-22/15:18
 * @email:grs0515@163.com
 */
public class CustomRadioGroupView extends BaseView {

    private RecyclerView recyclerView;
    private CheckGroupAdapter checkGroupAdapter;

    public CustomRadioGroupView(Context context) {
        this(context, null);
    }

    public CustomRadioGroupView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRadioGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        recyclerView = findViewById(R.id.rv_check_group);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, recyclerView, 0, LinearLayoutManager.VERTICAL);
        checkGroupAdapter = new CheckGroupAdapter(null);
        recyclerView.setAdapter(checkGroupAdapter);
    }

    public void setGroupData(List<? extends SheetListBean> data) {
        checkGroupAdapter.setNewData((List<SheetListBean>) data);
    }

    public void setGroupData(List<? extends SheetListBean> data, @RecyclerView.Orientation int orientation) {
        RecyclerViewHelper.setDefaultRecyclerView(mContext, recyclerView, 0, orientation);
        checkGroupAdapter.setNewData((List<SheetListBean>) data);
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_check_group_view;
    }

    static class CheckGroupAdapter extends BaseQuickAdapter<SheetListBean, BaseViewHolder> {

        public CheckGroupAdapter(@Nullable List<SheetListBean> data) {
            super(R.layout.dhcc_item_check_group_view, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SheetListBean item) {
            Log.e(TAG,"(CheckGroupAdapter.java:64) ");
            CustomCheckView customCheck = helper.getView(R.id.custom_check);
            customCheck.setSelectIcon(R.drawable.dhcc_icon_popup_selected, R.drawable.dhcc_icon_popup_normal_1);
            customCheck.setShowText(item.getDesc());
            customCheck.setSelect(item.isSelect());
            customCheck.setOnSelectListener(new SimpleCallBack<Boolean>() {
                @Override
                public void call(Boolean result, int type) {
                    for (SheetListBean datum : getData()) {
                        datum.setSelect(datum.getDesc().equals(item.getDesc()));
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }

}
