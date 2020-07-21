package com.dhcc.module.nurse.education.fragment;

import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;

/**
 * @author:gaoruishan
 * @date:202020-07-17/17:41
 * @email:grs0515@163.com
 */
public class HealthEduDrugFragment extends BaseNurseFragment {

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("用药情况");
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_drug;
    }
}
