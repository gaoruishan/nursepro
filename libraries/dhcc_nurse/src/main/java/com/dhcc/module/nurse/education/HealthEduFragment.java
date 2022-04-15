package com.dhcc.module.nurse.education;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.bean.PatientListBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommHttp;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.adapter.HealthEduEndAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduNeedAdapter;
import com.dhcc.module.nurse.education.bean.EduSheetListBean;
import com.dhcc.module.nurse.education.bean.EduTaskListBean;
import com.dhcc.module.nurse.education.bean.EducationListBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.module.nurse.education.fragment.HealthEduAddFragment;
import com.dhcc.module.nurse.education.fragment.HealthEduContentFragment;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOrdExeBottomView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.ClickBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康宣教
 * @author:gaoruishan
 * @date:202020-07-17/10:31
 * @email:grs0515@163.com
 */
public class HealthEduFragment extends BaseNurseFragment {

    private HealthEduEndAdapter healthEducationEndAdapter;
    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private HealthEduBean mBean;
    private TextView tvNeed, tvNeeded;
    private List<EduSheetListBean> eduSheetList;
    private String episodeId;
    private HealthEduNeedAdapter healthEducationNeedAdapter;
    private RecyclerView rvEndList, rvNeedList;
    private CustomOrdExeBottomView customSelectBottom;

    @Override
    protected void initDatas() {
        super.initDatas();
        setHindBottm(50);
        setToolbarCenterTitle("健康宣教");
        //调用点击
        tvNeed.callOnClick();

        getInWardPatList();

    }

    @Override
    public void onResume() {
        super.onResume();
        //每次都刷新一下
        if (mBean != null) {
            getEducationList();
        }
    }

    private void getInWardPatList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        CommHttp.getInWardPatList(new CommonCallBack<PatientListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                onFailThings(msg);
            }

            @Override
            public void onSuccess(PatientListBean bean, String type) {
                hideLoadingTip();
                //获取第一个
                if (bean.getPatInfoList() != null) {
                    //默认第一个
                    episodeId = bean.getPatInfoList().get(0).getEpisodeId();
                    getEducationList();
                    eduSheetList = EduSheetListBean.getSheetList(bean.getPatInfoList());
                    customSheet.setDatas(eduSheetList);
                }
            }
        });
    }


    private void getEducationList() {

        HealthEduApiManager.getEducationList(episodeId, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), new CommonCallBack<HealthEduBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(HealthEduBean bean, String type) {
                mBean = bean;
                hideFlag = "".equals(bean.getHideFlag());
                healthEducationEndAdapter.setNewData(bean.getEducationList().getData());
                healthEducationNeedAdapter.setCustomDatas(bean.getEduTaskList(),bean.getEduSubjectList());
                DataCache.saveJson(bean, HealthEduBean.class.getName());
                customSelectBottom.setSelectText("已选 0 个");
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSelectBottom = f(R.id.custom_select_bottom, CustomOrdExeBottomView.class);
        List<ClickBean> beans = new ArrayList<>();
        beans.add(new ClickBean("宣教", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execEdu();
            }
        }));
        customSelectBottom.addBottomView(beans);

        tvNeed = f(R.id.tv_need, TextView.class);
        tvNeed.setOnClickListener(this);
        tvNeeded = f(R.id.tv_need_ed, TextView.class);
        tvNeeded.setOnClickListener(this);


        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                episodeId = eduSheetList.get(position).getCode();
                getEducationList();
            }
        });

        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME).substring(0, 10);
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD));
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD) - CustomDateTimeView.ONE_DAY * 6);
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getEducationList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getEducationList();
            }
        });


        addToolBarRightImageView(0, R.drawable.dhcc_icon_add);

        rvEndList = RecyclerViewHelper.get(mContext, R.id.rv_list_end);
        healthEducationEndAdapter = AdapterFactory.getHealthEducationAdapter();
        rvEndList.setAdapter(healthEducationEndAdapter);

        rvNeedList = RecyclerViewHelper.get(mContext, R.id.rv_list_need);
        healthEducationNeedAdapter = AdapterFactory.getHealthEducationNeedAdapter();
        rvNeedList.setAdapter(healthEducationNeedAdapter);

        healthEducationNeedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                refreshNeedData(adapter, position);
            }
        });
        healthEducationNeedAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                refreshNeedData(adapter, position);
            }
        });

        healthEducationEndAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EducationListBean.DataBean ordbean = healthEducationEndAdapter.getData().get(position);
                String desc = EduSheetListBean.getCurrentSheetDesc(eduSheetList, episodeId);
                BundleData instance = new BundleData()
                        .setDesc(desc)
                        .setEpisodeId(episodeId)
                        .setDateTime(ordbean.getEduDateTime())
                        .setEduRecordId(ordbean.getEduRecordId())
                        .setSubjectIds(ordbean.getSubjectIds());
                startFragment(HealthEduContentFragment.class, instance.build());
            }
        });

    }

    private void refreshNeedData(BaseQuickAdapter adapter, int position) {
        List<EduTaskListBean> data = adapter.getData();
        int num = 0;
        for (int i = 0; i < data.size(); i++) {
            EduTaskListBean bean = data.get(i);
            if (i == position) {
                bean.setSelect(!bean.isSelect());
            }
            if (bean.isSelect()) {
                num++;
            }
        }
        customSelectBottom.setSelectText("已选 " + num + " 个");
        healthEducationNeedAdapter.notifyDataSetChanged();
    }

    private void execEdu() {
        String desc = EduSheetListBean.getCurrentSheetDesc(eduSheetList, episodeId);

        List<String> subjectIdsList = new ArrayList<>();
        List<String> taskIds = new ArrayList<>();
        List<String> eduRecordIds = new ArrayList<>();
        for (EduTaskListBean datum : healthEducationNeedAdapter.getData()) {
            if (datum.isSelect()) {
                subjectIdsList.add(datum.getSubjectId());
                taskIds.add(datum.getTaskId());
                eduRecordIds.add(datum.getEduRecordId());
            }
        }
        String subjectIds = subjectIdsList.toString();
        if (TextUtils.isEmpty(subjectIds) || "[]".equals(subjectIds)) {
            ToastUtils.showShort("请选择宣教");
            return;
        }
        BundleData instance = new BundleData()
                .setDesc(desc)
                .setEpisodeId(episodeId)
//                .setEduRecordId(replaceBrackets(eduRecordIds.toString()))
                .setTaskIds(taskIds.toString())
                .setSubjectIds(subjectIds);
        startFragment(HealthEduContentFragment.class, instance.build());
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        //添加
        if (v.getId() == R.id.img_toolbar_right2) {
            if (mBean == null) return;
            String desc = EduSheetListBean.getCurrentSheetDesc(eduSheetList, episodeId);
            BundleData instance = new BundleData()
                    .setDesc(desc)
                    .setEpisodeId(episodeId)
                    .setEduSubjectList(mBean.getEduSubjectList());
            startFragment(HealthEduAddFragment.class, instance.build());
        }
        //需宣教/已宣教
        if (v.getId() == R.id.tv_need || v.getId() == R.id.tv_need_ed) {
            boolean color = v.getId() == R.id.tv_need;
            rvNeedList.setVisibility(color ? View.VISIBLE : View.GONE);
            rvEndList.setVisibility(color ? View.GONE : View.VISIBLE);
            tvNeed.setTextColor(ContextCompat.getColor(mContext, color ? R.color.blue : R.color.color_4a));
            tvNeeded.setTextColor(ContextCompat.getColor(mContext, !color ? R.color.blue : R.color.color_4a));
        }
    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        //腕带
        filterRegNoSheetList();
    }

    private void filterRegNoSheetList() {
        if (eduSheetList == null) return;
        String code = "";
        for (EduSheetListBean b : eduSheetList) {
            if (b.getRegNo().equals(scanInfo)) {
                code = b.getCode();
                break;
            }
        }
        if (!TextUtils.isEmpty(code)) {
            customSheet.setSheetDefCode(code);
            episodeId = code;
            getEducationList();
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education;
    }
}
