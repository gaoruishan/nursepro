package com.dhcc.res.item;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dhcc.res.util.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.res.item.adapter.CustomOrdAdapter;
import com.dhcc.res.item.adapter.CustomPatAdapter;
import com.dhcc.res.item.bean.CustomOrdItem;
import com.dhcc.res.item.bean.CustomPatItem;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/3/8/14:41
 * @email:grs0515@163.com
 */
public class CustomAdapterManager {
    /**
     * 医嘱Item
     * @param recyclerView
     * @param list
     */
    public static void setCustomOrdLayoutData(Context mContext, RecyclerView recyclerView, List<? extends CustomOrdItem> list, BaseQuickAdapter.OnItemClickListener mOnItemClickListener) {
        if (list == null) {
            return;
        }
        CustomOrdAdapter adapter = new CustomOrdAdapter((List<CustomOrdItem>) list);
        setAdapters(mContext, recyclerView, mOnItemClickListener, adapter);
    }

    /**
     * 病人Item
     * @param recyclerView
     * @param list
     */
    public static void setCustomPatLayoutData(Context mContext, RecyclerView recyclerView, List<? extends CustomPatItem> list, BaseQuickAdapter.OnItemClickListener mOnItemClickListener) {
        if (list == null) {
            return;
        }
        CustomPatAdapter adapter = new CustomPatAdapter((List<CustomPatItem>) list);
        setAdapters(mContext, recyclerView, mOnItemClickListener, adapter);
    }



    /**
     * 设置适配器
     * @param mContext
     * @param recyclerView
     * @param mOnItemClickListener
     * @param adapter
     */
    private static void setAdapters(Context mContext, RecyclerView recyclerView, BaseQuickAdapter.OnItemClickListener mOnItemClickListener, BaseQuickAdapter adapter) {
        RecyclerViewHelper.setDefaultRecyclerView(mContext, recyclerView, 0, LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(adapter, view, position);
                }
            }
        });
    }

}
