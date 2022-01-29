package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.BaseView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 封装顶部选择栏
 * @author:gaoruishan
 * @date:202019-07-05/15:50
 * @email:grs0515@163.com
 */
public class CustomSheetTabView extends BaseView {

    private RecyclerView rvSheet;
    private SheetListAdapter sheetListAdapter;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;
    private Boolean ifSelectOne = true;

    public void setIfSelectOne(Boolean ifSelectOne) {
        this.ifSelectOne = ifSelectOne;
    }

    public SheetListAdapter getSheetListAdapter() {
        return sheetListAdapter;
    }

    public CustomSheetTabView(Context context) {
        this(context, null);
    }

    public CustomSheetTabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSheetTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        rvSheet = view.findViewById(R.id.rv_sheet);
        sheetListAdapter = new SheetListAdapter(null);
        sheetListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String sheetCode = ((SheetListBean) adapter.getItem(position)).getCode();
                //左侧刷新分类选中状态显示
                sheetListAdapter.setSelectedCode(sheetCode);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(adapter, view, position);
                }
            }
        });
        rvSheet.setAdapter(sheetListAdapter);
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_sheet_tab_view;
    }

    public void setDatas(List<? extends SheetListBean> list) {
        if (list == null) {
            return;
        }
        setVisibility(VISIBLE);
        if (list.size() <= 4) {
            RecyclerViewHelper.setGridRecyclerView(getContext(), rvSheet, list.size(), false);
        } else {
            RecyclerViewHelper.setDefaultRecyclerView(getContext(), rvSheet, 0, LinearLayoutManager.HORIZONTAL);
        }
        //默认第一个
        sheetListAdapter.setSelectedCode(list.get(0).getCode());
        sheetListAdapter.setNewData((List<SheetListBean>) list);
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
            super(R.layout.custom_sheet_tab_item, data);
        }

        public String getSelectedCode() {
            return selectedCode == null ? "" : selectedCode;
        }

        void setSelectedCode(String selectedCode) {
            this.selectedCode = selectedCode;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(BaseViewHolder helper, SheetListBean item) {
            TextView tab = helper.getView(R.id.tv_tab);
            boolean select = item.getCode().equals(selectedCode);
            tab.setSelected(select);
            helper.setVisible(R.id.v_line, select)
                    .setText(R.id.tv_tab, item.getDesc());

        }
    }
}
