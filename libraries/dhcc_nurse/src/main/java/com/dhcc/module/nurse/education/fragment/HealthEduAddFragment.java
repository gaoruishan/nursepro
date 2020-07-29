package com.dhcc.module.nurse.education.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.MessageEvent;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.education.adapter.HealthEduAddAdapter;
import com.dhcc.module.nurse.education.bean.EduSubjectListBean;
import com.dhcc.res.infusion.CustomCheckView;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.OnGroupClickListener;
import com.gavin.com.library.listener.PowerGroupListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
    private List<EduSubjectListBean> eduSubjectList;

    @Override
    protected void initDatas() {
        super.initDatas();
        //注册事件总线
        EventBus.getDefault().register(this);

        setToolbarCenterTitle(this.desc + "");

        healthEduAddAdapter.setNewData(eduSubjectList);
    }

    @Override
    protected void initViews() {
        super.initViews();
        desc = new BundleData(bundle).getDesc();
        episodeId = new BundleData(bundle).getEpisodeId();
        eduSubjectList = new BundleData(bundle).getEduSubjectList();

        addToolBarRightTextView("确定", R.color.white);
        mRecyclerView = f(R.id.rv, RecyclerView.class);
        healthEduAddAdapter = AdapterFactory.getHealthEduAddAdapter();
        mRecyclerView.setAdapter(healthEduAddAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        decoration = PowerfulStickyDecoration.Builder
                .init(new PowerGroupListener() {
                    @Override
                    public View getGroupView(int position) {
                        return HealthEduAddFragment.this.getGroupView(position);
                    }

                    @Override
                    public String getGroupName(int position) {
                        return HealthEduAddFragment.this.getGroupName(position);
                    }
                })
                .setGroupHeight(dip2px(mContext, 40))
                .setGroupBackground(Color.parseColor("#FFFFFF"))
                .setDivideColor(Color.parseColor("#00ffffff"))
                .setDivideHeight(dip2px(mContext, 0))
                .setCacheEnable(false)
//                .setHeaderCount(1) // 设置头
                .setOnClickListener(new OnGroupClickListener() {
                    @Override
                    public void onClick(int position, int viewId) {                                 //Group点击事件
                        setOnGroupClick(position, viewId);

                    }
                })
                .build();

        mRecyclerView.addItemDecoration(decoration);

    }

    private View getGroupView(int position) {
        //获取自定定义的组View
        if (healthEduAddAdapter.getData().size() > position) {
            View view = getLayoutInflater().inflate(R.layout.item_health_add_group, null, false);
            EduSubjectListBean bean = healthEduAddAdapter.getData().get(position);
            view.setSelected(bean.isHide());
            CustomCheckView customCheckView = view.findViewById(R.id.custom_check);
            customCheckView.setShowText(bean.getDesc());
            customCheckView.setSelect(bean.isGroupSelect());
            return view;
        }
        return null;

    }

    private String getGroupName(int position) {
        //获取组名，用于判断是否是同一组
        if (healthEduAddAdapter.getData().size() > position) {
            EduSubjectListBean bean = healthEduAddAdapter.getData().get(position);
            if ("0".equals(bean.getPid())) {
                return bean.getId();
            }
            return bean.getPid();
        }
        return null;
    }

    private void setOnGroupClick(int position, int viewId) {
        List<EduSubjectListBean> data = healthEduAddAdapter.getData();
        if (viewId == R.id.iv_arrow_switch) {
            String type = data.get(position).getPid();
            if ("0".equals(data.get(position).getPid())) {
                type = data.get(position).getId();
            }
            for (EduSubjectListBean datum : data) {
                if (datum.getPid().equals(type)) {
                    datum.setHide(!datum.isHide());
                }
            }
            healthEduAddAdapter.replaceData(data);
        }
        if (viewId == R.id.custom_check) {
            EduSubjectListBean bean = data.get(position);
            boolean groupSelect = bean.isGroupSelect();
            bean.setGroupSelect(!groupSelect);
            for (EduSubjectListBean datum : data) {
                if (datum.getPid().equals(bean.getId())) {
                    datum.setSelect(!groupSelect);
                }
            }
            healthEduAddAdapter.replaceData(data);
            decoration.clearCache();
        }
    }

    /**
     * 接收事件- 更新数据
     */
    @Override
    public void updateMessageEvent(MessageEvent event) {
        super.updateMessageEvent(event);
        if (event.getType() == MessageEvent.MessageType.HEALTH_EDU_ADD_SELECT) {
            String id = event.getMessage();
            boolean groupSelect = event.isSelect();
            List<EduSubjectListBean> data = healthEduAddAdapter.getData();
            for (EduSubjectListBean datum : data) {
                if (datum.getId().equals(id)) {
                    datum.setGroupSelect(groupSelect);
                }
            }
            healthEduAddAdapter.replaceData(data);
            decoration.clearCache();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //确定
        if (v.getId() == R.id.tv_toolbar_right) {
            String subjectIds = getSelectIds();
            if (TextUtils.isEmpty(subjectIds) || "[]".equals(subjectIds)) {
                ToastUtils.showShort("请选择主题");
                return;
            }
            BundleData instance = new BundleData()
                    .setDesc(desc)
                    .setEpisodeId(episodeId)
                    .setSubjectIds(subjectIds);
            startFragment(HealthEduContentFragment.class, instance.build());
        }
    }

    private String getSelectIds() {
        List<EduSubjectListBean> data = healthEduAddAdapter.getData();
        List<String> mStringList = new ArrayList<>();
        for (EduSubjectListBean datum : data) {
            String id = datum.getId();
            if (datum.isSelect()) {
                if (!mStringList.contains(id)) {
                    mStringList.add(id);
                }
            }
        }
//        return replaceBrackets(mStringList.toString());
        return mStringList.toString().replaceAll(" ", "");
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_add;
    }
}
