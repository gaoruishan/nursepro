package com.dhcc.module.infusion.workarea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.utils.CommRes;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.config.ConfigWorkArea;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * @author:gaoruishan
 * @date:202019-05-22/15:16
 * @email:grs0515@163.com
 */
public class WorkAreaFragment extends BaseFragment {
    public static final String DOSING = "DOSING";
    public static final String PUNCTURE = "PUNCTURE";
    public static final String PATRAL = "PATRAL";
    public static final String CONTINUE = "CONTINUE";
    public static final String NEEDLES = "WITHDRAWALOFNEEDLES";
    private RecyclerView recConfig;
    private List<ConfigWorkArea.ListBean> ItemNameList = new ArrayList<>();
    private WorkAreaAdapter patEventsAdapter;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea_infusion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        initView(view);
        CommRes.readJson("config_work_area.json", ConfigWorkArea.class, new CommRes.CallRes<ConfigWorkArea>() {
            @Override
            public void call(ConfigWorkArea conf, String s) {
                patEventsAdapter.setNewData(conf.getList());
            }
        });

    }

    private void initView(View view) {
        recConfig = view.findViewById(R.id.recy_workarea);
        recConfig.setHasFixedSize(true);
        recConfig.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        patEventsAdapter = new WorkAreaAdapter(null);
        recConfig.setAdapter(patEventsAdapter);
        patEventsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ConfigWorkArea.ListBean item = patEventsAdapter.getItem(position);
                try {
                    //fragment 不为空, 且必须继承BaseFragment
                    if (item != null && !TextUtils.isEmpty(item.getFragment())) {
                        Class<? extends BaseFragment> aClass = (Class<? extends BaseFragment>) Class.forName(item.getFragment());
                        startFragment(aClass);
                    }
                } catch (Exception e) {
                    Log.e(TAG,e.toString());
                }
            }
        });
    }

    public class WorkAreaAdapter extends BaseQuickAdapter<ConfigWorkArea.ListBean, BaseViewHolder> {
        WorkAreaAdapter(@Nullable List<ConfigWorkArea.ListBean> data) {
            super(R.layout.item_workarea, data);
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
