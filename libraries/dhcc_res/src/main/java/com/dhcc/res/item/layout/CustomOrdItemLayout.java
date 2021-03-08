package com.dhcc.res.item.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.BaseView;
import com.dhcc.res.item.bean.CustomOrdItem;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020/12/9/17:47
 * @email:grs0515@163.com
 */
public class CustomOrdItemLayout extends BaseView {


    private TextView tvTag;
    private TextView tvNote;
    private RecyclerView rvList;

    public CustomOrdItemLayout(Context context) {
        this(context, null);
    }

    public CustomOrdItemLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomOrdItemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvTag = findViewById(R.id.tv_tag);
        rvList = findViewById(R.id.rv_list);
        tvNote = findViewById(R.id.tv_note);
    }

    public CustomOrdItemLayout setTvNote(String s) {
        setText(this.tvNote, s);
        return this;
    }

    public CustomOrdItemLayout setTvTag(String s) {
        setText(this.tvTag, s);
        return this;
    }


    /**
     * 简单适配器
     * @param list
     */
    public CustomOrdItemLayout setRecycleList(List<CustomOrdItem.OeoreGroupBean> list) {
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvList, 0, LinearLayoutManager.VERTICAL);
        rvList.setAdapter(new BaseQuickAdapter<CustomOrdItem.OeoreGroupBean,BaseViewHolder>(R.layout.custom_ord_item_child,list) {

            @Override
            protected void convert(BaseViewHolder helper, CustomOrdItem.OeoreGroupBean item) {
                helper.setText(R.id.tv_title, item.getArcimDesc())
                        .setText(R.id.tv_content, item.getDoseQtyUnit()+"   "+item.getPhOrdQtyUnit());
            }
        });
        return this;
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_ord_item_layout;
    }
}
