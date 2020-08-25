package com.dhcc.module.nurse.nurplan.fragment;

import android.widget.TextView;

import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.PlanBundleData;
import com.dhcc.res.infusion.CustomSelectView;

/**
 * 护理措施新增
 * @author:gaoruishan
 * @date:202020-08-21/17:35
 * @email:grs0515@163.com
 */
public class NurPlanAddIntervFragment extends BaseNurseFragment {

    private String planId;
    private String title;
    private TextView tvTitle;
    private CustomSelectView customSelectDate;

    @Override
    protected void initViews() {
        super.initViews();
        planId = new PlanBundleData(bundle).getPlanId();
        title = new PlanBundleData(bundle).getTitle();

        tvTitle = f(R.id.tv_title_txt, TextView.class);
        customSelectDate = f(R.id.custom_select_date, CustomSelectView.class);
        customSelectDate.setTitle("时间选择");
        customSelectDate.setSelectTime(this.getFragmentManager(), null);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("新增护理措施");
        tvTitle.setText(title+"");
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_plan_add_interve;
    }
}
