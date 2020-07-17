package com.dhcc.module.nurse.education;

import com.base.commlibs.comm.BaseCommFragment;
import com.dhcc.module.nurse.R;

/**
 * 健康宣教
 * @author:gaoruishan
 * @date:202020-07-17/10:31
 * @email:grs0515@163.com
 */
public class HealthEducationFragment extends BaseCommFragment {
    @Override
    protected void initDatas() {
        setToolbarCenterTitle("健康宣教");
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education;
    }
}
