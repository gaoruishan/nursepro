package com.dhcc.nursepro.setting.adapter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.setting.bean.SettingBedListBean;
import com.dhcc.nursepro.utils.GridSpacingItemDecoration;

import java.util.List;

/**
 * com.dhcc.nursepro.setting.adapter
 * <p>
 * author Q
 * Date: 2018/9/14
 * Time:9:57
 */
public class SettingBedsGroupAdapter extends BaseQuickAdapter<SettingBedListBean.BedListBean, BaseViewHolder> {

    private LinearLayout llBedSelectGroup;
    private Boolean bFirst = false;
    private int postionSel=-1;

    private Intent intent = new Intent();

    public SettingBedsGroupAdapter(List<SettingBedListBean.BedListBean> data) {
        super(R.layout.item_settingbeds_group, data);
    }
    public void setFirst(Boolean bFirst){
        this.bFirst = bFirst;
    }

    public void setPostion(int postionSel){
        this.postionSel = postionSel;
    }
    @Override
    protected void convert(final BaseViewHolder helper, final SettingBedListBean.BedListBean item) {
        llBedSelectGroup = helper.getView(R.id.ll_settingbeds_group);
//  判断是是否点击选组进入，选组进入按组处理，第一次进入按后台返回处理

//        if (postionSel == helper.getAdapterPosition()){
//            llBedSelectGroup.setSelected(true);
//            postionSel = -1;
//        }else {
//            llBedSelectGroup.setSelected(false);
//            postionSel = -1;
//        }

        if (bFirst){
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
        }


        helper.setText(R.id.tv_settingbeds_group, item.getGroupCode() + "区")
                .addOnClickListener(R.id.ll_settingbeds_group);

        RecyclerView rectBedSelectBed = helper.getView(R.id.recy_settingbeds_group);

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

        final SettingBedsAdapter settingBedsAdapter = new SettingBedsAdapter(item.getGroupList());
        settingBedsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SettingBedListBean.BedListBean.GroupListBean groupListBean = (SettingBedListBean.BedListBean.GroupListBean) adapter.getItem(position);
                if ("0".equals(groupListBean.getSelect())) {
                    groupListBean.setSelect("1");
                } else {
                    groupListBean.setSelect("0");
                }
                settingBedsAdapter.notifyItemChanged(position);

//
//                int selectNum = 0;
//                for (int i = 0;i<item.getGroupList().size();i++){
//                    if (item.getGroupList().get(i).getSelect().equals("1")){
//                        selectNum++;
//                    }
//                }
//                if (selectNum == item.getGroupList().size()){
//                    intent.setAction("SURETHIS");
//                    intent.putExtra("postion",helper.getAdapterPosition());
//                    mContext.sendBroadcast(intent);
//                }else {
//                    intent.setAction("SURETHIS");
//                    intent.putExtra("postion",helper.getAdapterPosition());
//                    mContext.sendBroadcast(intent);
//                }
            }
        });
        rectBedSelectBed.setAdapter(settingBedsAdapter);
    }
}