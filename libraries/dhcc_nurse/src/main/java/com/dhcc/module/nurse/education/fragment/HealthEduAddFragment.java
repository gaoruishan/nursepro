package com.dhcc.module.nurse.education.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.http.CommonCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.HealthEduApiManager;
import com.dhcc.module.nurse.education.adapter.HealthEduAddAdapter;
import com.dhcc.module.nurse.education.bean.HealthEduAddBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.OnGroupClickListener;
import com.gavin.com.library.listener.PowerGroupListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-17/17:35
 * @email:grs0515@163.com
 */
public class HealthEduAddFragment extends BaseNurseFragment {

    private RecyclerView mRecyclerView;
    private HealthEduAddAdapter healthEduAddAdapter;
    private PowerfulStickyDecoration decoration;

    @Override
    protected void initDatas() {
        super.initDatas();
        assert getArguments() != null;
        String str = getArguments().getString(PUT_BEAN_STR);
        String desc = getArguments().getString(PUT_STR);
        setToolbarCenterTitle(desc + "");
        Type type = new TypeToken<List<HealthEduBean.EduSubjectListBean>>() {}.getType();
        List<HealthEduBean.EduSubjectListBean> eduSubjectList = new Gson().fromJson(str, type);
        for (HealthEduBean.EduSubjectListBean eduSubjectListBean : eduSubjectList) {
            Log.e(TAG,"(HealthEduAddFragment.java:49) "+eduSubjectListBean.getDesc());
        }
    }

    @Override
    protected void initViews() {
        super.initViews();

        mRecyclerView = f(R.id.rv, RecyclerView.class);
        healthEduAddAdapter = AdapterFactory.getHealthEduAddAdapter();
        mRecyclerView.setAdapter(healthEduAddAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        decoration = PowerfulStickyDecoration.Builder
                .init(new PowerGroupListener() {
                    @Override
                    public View getGroupView(int position) {
                        //获取自定定义的组View
                        if (healthEduAddAdapter.getData().size() > position) {
                            View view = getLayoutInflater().inflate(R.layout.item_health_add_group, null, false);
                            HealthEduAddBean.OrdListBean bean = healthEduAddAdapter.getData().get(position);
                            view.setSelected(bean.isHide());
                            ((TextView) view.findViewById(R.id.tv_group_title)).setText(bean.getType());
                            return view;
                        } else {
                            return null;
                        }
                    }

                    @Override
                    public String getGroupName(int position) {
                        //获取组名，用于判断是否是同一组
                        if (healthEduAddAdapter.getData().size() > position) {
                            String type = healthEduAddAdapter.getData().get(position).getType();
                            return type;
                        }
                        return null;
                    }
                })
                .setGroupHeight(dip2px(mContext, 40))
//                .setGroupBackground(Color.parseColor("#48BDFF"))
                .setDivideColor(Color.parseColor("#00ffffff"))
                .setDivideHeight(dip2px(mContext, 0))
                .setCacheEnable(true)
//                .setHeaderCount(1) // 设置头
                .setOnClickListener(new OnGroupClickListener() {
                    @Override
                    public void onClick(int position, int id) {                                 //Group点击事件
                        String type = healthEduAddAdapter.getData().get(position).getType();
                        for (HealthEduAddBean.OrdListBean datum : healthEduAddAdapter.getData()) {
                            if (datum.getType().equals(type)) {
                                datum.setHide(!datum.isHide());
                            }
                        }
                        decoration.clearCache();
                        healthEduAddAdapter.notifyDataSetChanged();
                    }
                })
                .build();

        mRecyclerView.addItemDecoration(decoration);
        healthEduAddAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void getHealthAddList() {
        HealthEduApiManager.getHealthAddList(new CommonCallBack<HealthEduAddBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(HealthEduAddBean bean, String type) {

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_add;
    }
}
