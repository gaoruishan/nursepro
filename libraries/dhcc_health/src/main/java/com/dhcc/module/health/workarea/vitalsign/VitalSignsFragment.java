package com.dhcc.module.health.workarea.vitalsign;

import com.base.commlibs.comm.BaseCommFragment;
import com.dhcc.module.health.R;

/**
 * 生命体征
 * @author:gaoruishan
 * @date:202019-10-23/16:52
 * @email:grs0515@163.com
 */
public class VitalSignsFragment extends BaseCommFragment {

    @Override
    protected void initDatas() {
        setToolbarCenterTitle("生命体征");
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_vital_signs;
    }
}
