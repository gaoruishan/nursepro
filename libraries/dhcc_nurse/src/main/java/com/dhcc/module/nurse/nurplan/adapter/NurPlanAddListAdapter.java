package com.dhcc.module.nurse.nurplan.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.bean.QuestionAddListBean;
import com.dhcc.res.infusion.CustomCheckView;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-18/17:20
 * @email:grs0515@163.com
 */
public class NurPlanAddListAdapter extends BaseQuickAdapter<QuestionAddListBean, BaseViewHolder> {

    public NurPlanAddListAdapter(int layoutResId, @Nullable List<QuestionAddListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionAddListBean item) {
        CustomCheckView checkView = helper.getView(R.id.custom_check);
        checkView.setShowText(item.getQueName());
        checkView.setSelect(item.isSelect());
        List<QuestionAddListBean.ChildsBean> childs = item.getChilds();
        boolean hasChild = childs != null && childs.size() > 0;
        helper.getView(R.id.iv_arrow_switch).setSelected(item.isOpen());
        helper.setGone(R.id.rv_child, item.isOpen())
                .setGone(R.id.ll_child, hasChild)
                .setText(R.id.tv_child_name, childs.size() + "个");

        RecyclerView recyclerView = helper.getView(R.id.rv_child);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, recyclerView, 0, LinearLayoutManager.VERTICAL);
        NurPlanAddListChildAdapter childAdapter = new NurPlanAddListChildAdapter(item.getChilds());
        recyclerView.setAdapter(childAdapter);
        helper.setOnClickListener(R.id.ll_child, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean visible = recyclerView.getVisibility() == View.VISIBLE;
                item.setOpen(!visible);
                helper.setGone(R.id.rv_child, item.isOpen());
                helper.getView(R.id.iv_arrow_switch).setSelected(item.isOpen());
            }
        });
        checkView.setOnSelectListener(new SimpleCallBack<Boolean>() {
            @Override
            public void call(Boolean result, int type) {
                item.setSelect(result);
                if (hasChild) {
                    for (QuestionAddListBean.ChildsBean child : childs) {
                        child.setSelect(result);
                    }
                    childAdapter.notifyDataSetChanged();
                }
            }
        });
        childAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "(NurPlanAddListAdapter.java:72) " + position);
                boolean b = false;
                for (QuestionAddListBean.ChildsBean datum : childAdapter.getData()) {
                    if (!datum.isSelect()) {
                        b = false;
                        break;
                    }
                    b = true;
                }
                boolean b1 = item.isSelect() != b;
                item.setSelect(b);
                if (b1) {
                    notifyItemChanged(helper.getAdapterPosition());
                }
            }
        });
    }

    static class NurPlanAddListChildAdapter extends BaseQuickAdapter<QuestionAddListBean.ChildsBean, BaseViewHolder> {

        public NurPlanAddListChildAdapter(@Nullable List<QuestionAddListBean.ChildsBean> data) {
            super(R.layout.item_nur_plan_question_add, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, QuestionAddListBean.ChildsBean item) {
            CustomCheckView checkView = helper.getView(R.id.custom_check);
            checkView.setShowText(item.getQueName());
            checkView.setSelect(item.isSelect());
            helper.setGone(R.id.rv_child, false)
                    .setGone(R.id.ll_child, false)
                    .addOnClickListener(R.id.ll_item);
            checkView.setOnSelectListener(new SimpleCallBack<Boolean>() {
                @Override
                public void call(Boolean result, int type) {
                    item.setSelect(result);
                    getOnItemChildClickListener().onItemChildClick(NurPlanAddListChildAdapter.this,checkView,helper.getAdapterPosition());
                }
            });
        }
    }
}
