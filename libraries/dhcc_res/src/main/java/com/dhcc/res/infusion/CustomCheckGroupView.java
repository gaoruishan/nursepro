package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.BaseView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 一组复选框
 * @author:gaoruishan
 * @date:202020-07-22/15:18
 * @email:grs0515@163.com
 */
public class CustomCheckGroupView extends BaseView {

    private CheckGroupAdapter checkGroupAdapter;

    public CustomCheckGroupView(Context context) {
        this(context, null);
    }

    public CustomCheckGroupView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCheckGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        RecyclerView recyclerView = findViewById(R.id.rv_check_group);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, recyclerView, 0, LinearLayoutManager.VERTICAL);
        checkGroupAdapter = new CheckGroupAdapter(null);
        recyclerView.setAdapter(checkGroupAdapter);

    }

    public void setGroupData(List<? extends SheetListBean> data) {
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
            CustomCheckView customCheck = helper.getView(R.id.custom_check);
            customCheck.setShowText(item.getDesc());
            customCheck.setOnSelectListener(new SimpleCallBack<Boolean>() {
                @Override
                public void call(Boolean result, int type) {
                    helper.getView(R.id.ll_item_check_group).requestFocus();
                    item.setSelect(result);
                }
            });
        }
    }

}
