package com.dhcc.module.infusion.workarea.transblood.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.UniversalActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 输血公共适配器
 * @author:gaoruishan
 * @date:202020-03-06/14:33
 * @email:grs0515@163.com
 */
public abstract class BaseTransBloodAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    protected String scanCode;

    public BaseTransBloodAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(K helper, T item) {

    }

    /**
     * 使用UniversalActivity启动给定的Fragment
     * @param fragCls 待启动Fragment
     * @param args    传递给Fragment的参数,可空
     */
    public void startFragment(@NonNull Class<? extends BaseFragment> fragCls, @Nullable Bundle args) {
        if (mContext != null) {
            Intent intent = new Intent(mContext, UniversalActivity.class);
            intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
            intent.putExtra(UniversalActivity.RootFragmentArgs, args);
            mContext.startActivity(intent);
        }
    }
    /**
     * 需要 重写@setCommItemData
     * @param helper
     * @param itemId
     */
    protected void setCommItemClick(final BaseViewHolder helper, View  itemId) {
        itemId.setClickable(true);
        itemId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 医嘱详情
                Bundle bundle = new Bundle();
                T t = mData.get(helper.getLayoutPosition());
                setJumpDetailData(bundle, t);
                //TODO 详情去掉
                //startFragment(TransBloodDetailFragment.class, bundle);
            }
        });
    }

    /**
     * 子类填充数据
     * @param bundle
     * @param t
     */
    protected  void setJumpDetailData(Bundle bundle, T t){

    };

    public void setCurrentScanCode(String code) {
        this.scanCode = code;
    }
}
