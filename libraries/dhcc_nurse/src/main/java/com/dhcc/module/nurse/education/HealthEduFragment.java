package com.dhcc.module.nurse.education;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.bean.PatientListBean;
import com.base.commlibs.http.CommHttp;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.adapter.HealthEduAdapter;
import com.dhcc.module.nurse.education.bean.EduSheetListBean;
import com.dhcc.module.nurse.education.bean.EducationListBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.module.nurse.education.fragment.HealthEduAddFragment;
import com.dhcc.module.nurse.education.fragment.HealthEduContentFragment;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.List;

/**
 * 健康宣教
 * @author:gaoruishan
 * @date:202020-07-17/10:31
 * @email:grs0515@163.com
 */
public class HealthEduFragment extends BaseNurseFragment {

    private HealthEduAdapter healthEducationAdapter;
    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private HealthEduBean mBean;
    private TextView tvNeed, tvNeeded;
    private List<EduSheetListBean> eduSheetList;
    private String episodeId;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("健康宣教");

        getInWardPatList();

    }

    private void getInWardPatList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        CommHttp.getInWardPatList(new CommonCallBack<PatientListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                onFailThings();
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
                onFailThings();
            }

            @Override
            public void onSuccess(HealthEduBean bean, String type) {
                mBean = bean;
                healthEducationAdapter.setNewData(bean.getEducationList().getData());
                DataCache.saveJson(bean,HealthEduBean.class.getName());
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
        tvNeed = f(R.id.tv_need, TextView.class);
        tvNeed.setOnClickListener(this);
        tvNeeded = f(R.id.tv_need_ed, TextView.class);
        tvNeeded.setOnClickListener(this);

        tvNeed.callOnClick();

        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                episodeId = eduSheetList.get(position).getCode();
                getEducationList();
            }
        });

        customDate = f(R.id.custom_date, CustomDateTimeView.class);
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

        RecyclerView rvList = RecyclerViewHelper.get(mContext, R.id.rv_list);
        healthEducationAdapter = AdapterFactory.getHealthEducationAdapter();
        rvList.setAdapter(healthEducationAdapter);
        healthEducationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EducationListBean.DataBean ordbean = healthEducationAdapter.getData().get(position);
                String desc = EduSheetListBean.getCurrentSheetDesc(eduSheetList, episodeId);
                BundleData instance = new BundleData()
                        .setDesc(desc)
                        .setDateTime(ordbean.getEduDateTime())
                        .setSubjectIds(ordbean.getSubjectIds());
                startFragment(HealthEduContentFragment.class, instance.build());
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.img_toolbar_right2) {
            if (mBean == null) return;
            String desc = EduSheetListBean.getCurrentSheetDesc(eduSheetList, episodeId);
            BundleData instance = new BundleData()
                    .setDesc(desc)
                    .setEpisodeId(episodeId)
                    .setEduSubjectList(mBean.getEduSubjectList());
            startFragment(HealthEduAddFragment.class, instance.build());
        }
        if (v.getId() == R.id.tv_need || v.getId() == R.id.tv_need_ed) {
            boolean color = v.getId() == R.id.tv_need;
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
