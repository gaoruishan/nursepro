package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 自定义tag标签
 * @author:gaoruishan
 * @date:202019-10-25/14:58
 * @email:grs0515@163.com
 */
public class CustomTagSelectLayout extends LinearLayout {

    private SheetListAdapter sheetListAdapter;
    private RecyclerView rvTag;
    private TextView tvName;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;

    public CustomTagSelectLayout(Context context) {
        this(context, null);
    }

    public CustomTagSelectLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTagSelectLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.custom_tag_select_layout, null);
        addView(view);
        tvName = view.findViewById(R.id.tv_name);
        rvTag = view.findViewById(R.id.rv_tag);
        RecyclerViewHelper.setGridRecyclerView(context, rvTag, 4, 0, false);
        sheetListAdapter = new SheetListAdapter(null);
        sheetListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String sheetCode = ((SheetListBean) adapter.getItem(position)).getCode();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(adapter, view, position);
                }
                //左侧刷新分类选中状态显示
                sheetListAdapter.setSelectedCode(sheetCode);
            }
        });
        rvTag.setAdapter(sheetListAdapter);
    }

    public  <T extends SheetListBean> CustomTagSelectLayout setDatas(List<T> list) {
        sheetListAdapter.setNewData((List<SheetListBean>) list);
        return this;
    }

    public CustomTagSelectLayout setTagTitle(String name) {
        if (!TextUtils.isEmpty(name)) {
            tvName.setText(name);
        }
        return this;
    }

    /**
     * 左侧刷新分类选中状态显示
     * @param sheetCode
     * @return
     */
    public CustomTagSelectLayout setSelectedCode(String sheetCode) {
        sheetListAdapter.setSelectedCode(sheetCode);
        return this;
    }

    public CustomTagSelectLayout setOnItemClickListener(@Nullable BaseQuickAdapter.OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    public class SheetListAdapter extends BaseQuickAdapter<SheetListBean, BaseViewHolder> {
        private String selectedCode;

        SheetListAdapter(@Nullable List<SheetListBean> data) {
            super(R.layout.dhcc_item_tag_list, data);
        }

        void setSelectedCode(String selectedCode) {
            this.selectedCode = selectedCode;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(BaseViewHolder helper, SheetListBean item) {
            TextView tvOrderType = helper.getView(R.id.tv_tag);

            if (selectedCode == null || "".equals(selectedCode)) {
                tvOrderType.setSelected(0 == helper.getAdapterPosition());
            } else {
                tvOrderType.setSelected(selectedCode.equals(item.getCode()));
            }
            helper.setText(R.id.tv_tag, item.getDesc());

        }
    }
}
