package com.dhcc.res.nurse;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.utils.CommRes;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.nurse.bean.ConfigWorkArea;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 主页模块
 * @author:gaoruishan
 * @date:202019-10-23/16:01
 * @email:grs0515@163.com
 */
public class CustomWorkAreaLayout extends LinearLayout {

    private static final String TAG = CustomWorkAreaLayout.class.getSimpleName();
    public RecyclerView rvWorkArea;
    public WorkAreaAdapter patEventsAdapter;

    public CustomWorkAreaLayout(Context context) {
        this(context, null);
    }

    public CustomWorkAreaLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomWorkAreaLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.custom_work_area_layout, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(layoutParams);
        addView(view);
        setOrientation(VERTICAL);
        rvWorkArea = view.findViewById(R.id.rv_work_area);
        rvWorkArea.setHasFixedSize(true);
    }

    public void initData(final BaseFragment context, List<ConfigWorkArea.ListBean> list) {

        rvWorkArea.setLayoutManager(new GridLayoutManager(context.getActivity(), 3));
        patEventsAdapter = new WorkAreaAdapter(list);
        rvWorkArea.setAdapter(patEventsAdapter);
        patEventsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ConfigWorkArea.ListBean item = patEventsAdapter.getItem(position);
                try {
                    //fragment 不为空, 且必须继承BaseFragment
                    if (item != null && !TextUtils.isEmpty(item.getFragment())) {
                        Class<? extends BaseFragment> aClass = (Class<? extends BaseFragment>) Class.forName(item.getFragment());
                        context.startFragment(aClass);
                    }
                } catch (Exception e) {
                    Log.e(TAG,e.toString());
                }
            }
        });
    }

    public class WorkAreaAdapter extends BaseQuickAdapter<ConfigWorkArea.ListBean, BaseViewHolder> {
        WorkAreaAdapter(@Nullable List<ConfigWorkArea.ListBean> data) {
            super(R.layout.dhcc_item_workarea, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ConfigWorkArea.ListBean item) {
            TextView tvItem = helper.getView(R.id.tv_workarea);
            ImageView imageView = helper.getView(R.id.icon_workarea);
            tvItem.setText(item.getName());
            int drawableRes = CommRes.getDrawableRes(mContext, item.getImage());
            if (drawableRes != 0) {
                imageView.setImageResource(drawableRes);
            }
        }
    }
}
