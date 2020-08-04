package com.dhcc.nursepro.workarea.bedselect.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.GridSpacingItemDecoration;
import com.dhcc.nursepro.workarea.bedselect.bean.BedSelectListBean;

import java.util.List;

/**
 * BedListAdapter
 *
 * @author DevLix126
 * @date 2018/8/29
 */
public class BedGroupListAdapter extends BaseQuickAdapter<BedSelectListBean.BedListBean, BaseViewHolder> {
    private boolean ifGroupClick = false;

    public BedGroupListAdapter(List<BedSelectListBean.BedListBean> data) {
        super(R.layout.item_bedselect_group, data);
    }

    public void setIfGroupClick(boolean ifGroupClick) {
        this.ifGroupClick = ifGroupClick;
    }

    @Override
    protected void convert(BaseViewHolder helper, BedSelectListBean.BedListBean item) {
        LinearLayout llBedSelectGroup = helper.getView(R.id.ll_bedselect_group);

        if ("0".equals(item.getSelect())) {
            llBedSelectGroup.setSelected(false);
            for (int i = 0; i < item.getGroupList().size(); i++) {
                item.getGroupList().get(i).setSelect("0");
            }
        } else {
            llBedSelectGroup.setSelected(true);
            for (int i = 0; i < item.getGroupList().size(); i++) {
                item.getGroupList().get(i).setSelect("1");
            }
        }

        if (!ifGroupClick) {
            String bedsSelected = "";
            bedsSelected = SPUtils.getInstance().getString(SharedPreference.ORDERSEARCHE_BEDSELECTED) + "";
            String[] bedSelected = bedsSelected.split(";");
            for (int i = 0; i < item.getGroupList().size(); i++) {
                for (int k = 0; k < bedSelected.length; k++) {
                    if (item.getGroupList().get(i).getBedId().equals(bedSelected[k])) {
                        item.getGroupList().get(i).setSelect("1");
                    }
                }

            }
        }
        helper.setText(R.id.tv_bedselect_group, item.getGroupCode() + "区")
                .addOnClickListener(R.id.ll_bedselect_group);

        RecyclerView rectBedSelectBed = helper.getView(R.id.recy_bedselect_bed);

        //提高展示效率
        rectBedSelectBed.setHasFixedSize(true);
        //设置的布局管理
        rectBedSelectBed.setLayoutManager(new GridLayoutManager(mContext, 3));

        /**
         * 判断recyclerview是否存在ItemDecoration,若存在则不必重复添加
         */
        RecyclerView.ItemDecoration itemDecoration;
        if (rectBedSelectBed.getItemDecorationCount() == 0) {
            itemDecoration = null;
        } else {
            itemDecoration = rectBedSelectBed.getItemDecorationAt(0);
        }
        if (itemDecoration == null) {
            int spanCount = 3;
            int spacing = 20;
            boolean includeEdge = true;
            rectBedSelectBed.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        }

        final BedListAdapter bedListAdapter = new BedListAdapter(item.getGroupList());
        bedListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BedSelectListBean.BedListBean.GroupListBean groupListBean = (BedSelectListBean.BedListBean.GroupListBean) adapter.getItem(position);
                if ("0".equals(groupListBean.getSelect())) {
                    groupListBean.setSelect("1");
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        String bedcode = ((BedSelectListBean.BedListBean.GroupListBean) adapter.getData().get(i)).getBedCode();
                        if (bedcode.startsWith(groupListBean.getBedCode() + "婴儿") ||
                                (groupListBean.getBedCode().contains("婴儿") && bedcode.equals(groupListBean.getBedCode().substring(0, groupListBean.getBedCode().indexOf("婴儿")))) ||
                                (groupListBean.getBedCode().contains("婴儿") && bedcode.contains("婴儿") && bedcode.substring(0, groupListBean.getBedCode().indexOf("婴儿")).equals(groupListBean.getBedCode().substring(0, groupListBean.getBedCode().indexOf("婴儿"))))
                        ) {
                            ((BedSelectListBean.BedListBean.GroupListBean) adapter.getData().get(i)).setSelect("1");
                        }
                    }
                } else {
                    groupListBean.setSelect("0");
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        String bedcode = ((BedSelectListBean.BedListBean.GroupListBean) adapter.getData().get(i)).getBedCode();
                        if (bedcode.startsWith(groupListBean.getBedCode() + "婴儿") ||
                                (groupListBean.getBedCode().contains("婴儿") && bedcode.equals(groupListBean.getBedCode().substring(0, groupListBean.getBedCode().indexOf("婴儿")))) ||
                                (groupListBean.getBedCode().contains("婴儿") && bedcode.contains("婴儿") && bedcode.substring(0, groupListBean.getBedCode().indexOf("婴儿")).equals(groupListBean.getBedCode().substring(0, groupListBean.getBedCode().indexOf("婴儿"))))
                        ) {
                            ((BedSelectListBean.BedListBean.GroupListBean) adapter.getData().get(i)).setSelect("0");
                        }
                    }
                }
                bedListAdapter.notifyDataSetChanged();
            }
        });
        rectBedSelectBed.setAdapter(bedListAdapter);
    }
}
