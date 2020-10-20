package com.dhcc.nursepro.workarea.nurrecordnew.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.constant.Action;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.TreeBean;

import java.util.List;

public class KnowledgeTreeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    public static final int TYPE_LEVEL_3 = 3;
    public static final int TYPE_LEVEL_4 = 4;
    public static final int TYPE_LEVEL_5 = 5;
    public static final int TYPE_LEVEL_6 = 6;
    public static final int TYPE_LEVEL_7 = 7;
    public static final int TYPE_LEVEL_8 = 8;
    public static final int TYPE_LEVEL_9 = 9;
    public static final int TYPE_LEVEL_100 = 100;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public KnowledgeTreeAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expandable_lv2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expandable_lv3);
        addItemType(TYPE_LEVEL_4, R.layout.item_expandable_lv4);
        addItemType(TYPE_LEVEL_5, R.layout.item_expandable_lv5);
        addItemType(TYPE_LEVEL_6, R.layout.item_expandable_lv6);
        addItemType(TYPE_LEVEL_7, R.layout.item_expandable_lv7);
        addItemType(TYPE_LEVEL_8, R.layout.item_expandable_lv8);
        addItemType(TYPE_LEVEL_9, R.layout.item_expandable_lv9);
        //        addItemType(TYPE_LEVEL_100, R.layout.item_expandable_lv100);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        TextView textView = helper.getView(R.id.title);
        ImageView imageView = helper.getView(R.id.iv);
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
            case TYPE_LEVEL_1:
            case TYPE_LEVEL_2:
            case TYPE_LEVEL_3:
            case TYPE_LEVEL_4:
            case TYPE_LEVEL_5:
            case TYPE_LEVEL_6:
            case TYPE_LEVEL_7:
            case TYPE_LEVEL_8:
            case TYPE_LEVEL_9:
                final TreeBean treeBean = (TreeBean) item;
                if (((TreeBean) item).getSubItems() != null) {
                    textView.setTextColor(Color.parseColor("#4A4A4A"));
                    imageView.setVisibility(View.VISIBLE);
                    helper.setText(R.id.title, ((TreeBean) item).getText())
                            .setImageResource(R.id.iv, treeBean.isExpanded() ? R.drawable.arrow_b : R.drawable.arrow_r);
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = helper.getAdapterPosition();
                            if (treeBean.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                            }
                        }
                    });
                } else {
                    imageView.setVisibility(View.GONE);
                    textView.setTextColor(Color.parseColor("#62ABFF"));
                    helper.setText(R.id.title, ((TreeBean) item).getText());
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String knowledgeTreeId = ((TreeBean) item).getId();
                            Intent intent = new Intent().setAction(Action.NURRECORD_KONWLEDGETREEID);
                            intent.putExtra("knowledgeTreeId", knowledgeTreeId);
                            mContext.sendBroadcast(intent);
//                            Toast.makeText(mContext, ((TreeBean) item).getId(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;


            case TYPE_LEVEL_100:
//                TreeBean treeBean100 = (TreeBean) item;
//                helper.setText(R.id.tv, ((TreeBean) item).getText());
//                helper.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(mContext, ((TreeBean) item).getId(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                break;
            default:
                break;
        }

    }
}
