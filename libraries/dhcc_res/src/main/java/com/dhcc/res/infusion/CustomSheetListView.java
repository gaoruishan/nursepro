package com.dhcc.res.infusion;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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
 * 封装左侧选择栏
 * @author:gaoruishan
 * @date:202019-07-05/15:50
 * @email:grs0515@163.com
 */
public class CustomSheetListView extends LinearLayout {

    private RecyclerView rvSheet;
    private SheetListAdapter sheetListAdapter;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;

    public CustomSheetListView(Context context) {
        this(context, null);
    }

    public CustomSheetListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSheetListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LinearLayout view = (LinearLayout) View.inflate(context, R.layout.custom_sheet_list_view, null);
        addView(view);
        rvSheet = view.findViewById(R.id.rv_sheet);
        RecyclerViewHelper.setDefaultRecyclerView(getContext(), rvSheet, 0);
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
        rvSheet.setAdapter(sheetListAdapter);
    }

    public void setDatas(List<SheetListBean> list) {
        sheetListAdapter.setNewData(list);
    }

    public void setOnItemClickListener(@Nullable BaseQuickAdapter.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 默认滚动
     * @param defCode
     */
    public void setSheetDefCode(String defCode) {
        if (sheetListAdapter.getData().size() > 0) {
            List<SheetListBean> sheetList = sheetListAdapter.getData();
            for (int i = 0; i < sheetList.size(); i++) {
                if (sheetList.get(i).getCode().equals(defCode)) {
                    rvSheet.scrollToPosition(i);
                    sheetListAdapter.setSelectedCode(defCode);
                    break;
                }
            }
        }
    }

    public class SheetListAdapter extends BaseQuickAdapter<SheetListBean, BaseViewHolder> {
        private String selectedCode;

        SheetListAdapter(@Nullable List<SheetListBean> data) {
            super(R.layout.dhcc_item_sheet_list, data);
        }

        void setSelectedCode(String selectedCode) {
            this.selectedCode = selectedCode;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(BaseViewHolder helper, SheetListBean item) {
            LinearLayout llOrderType = helper.getView(R.id.ll_ordersearch_ordertype);
            View viewOrderType = helper.getView(R.id.view_ordersearch_ordertype);
            TextView tvOrderType = helper.getView(R.id.tv_ordersearch_ordertype);

            if (selectedCode == null || "".equals(selectedCode)) {
                if (0 == helper.getAdapterPosition()) {
                    setSheetType(llOrderType, viewOrderType, tvOrderType, true, R.color.dhcc_ordersearch_left_text_selected_color, Typeface.DEFAULT_BOLD, View.VISIBLE);
                } else {
                    setSheetType(llOrderType, viewOrderType, tvOrderType, false, R.color.dhcc_ordersearch_left_text_normal_color, Typeface.DEFAULT, View.INVISIBLE);
                }
            } else {
                if (selectedCode.equals(item.getCode())) {
                    setSheetType(llOrderType, viewOrderType, tvOrderType, true, R.color.dhcc_ordersearch_left_text_selected_color, Typeface.DEFAULT_BOLD, View.VISIBLE);
                } else {
                    setSheetType(llOrderType, viewOrderType, tvOrderType, false, R.color.dhcc_ordersearch_left_text_normal_color, Typeface.DEFAULT, View.INVISIBLE);
                }
            }
            helper.setText(R.id.tv_ordersearch_ordertype, item.getDesc());

        }

        private void setSheetType(LinearLayout llOrderType, View viewOrderType, TextView tvOrderType, boolean b, int p, Typeface defaultBold, int visible) {
            llOrderType.setSelected(b);
            tvOrderType.setTextColor(mContext.getResources().getColor(p));
            tvOrderType.setTypeface(defaultBold);
            viewOrderType.setVisibility(visible);
        }
    }
}