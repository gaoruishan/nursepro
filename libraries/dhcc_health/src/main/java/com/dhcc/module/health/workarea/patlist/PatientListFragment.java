package com.dhcc.module.health.workarea.patlist;

import com.base.commlibs.comm.BaseCommFragment;
import com.dhcc.module.health.R;

/**
 * 患者列表
 * @author:gaoruishan
 * @date:202019-10-23/17:00
 * @email:grs0515@163.com
 */
public class PatientListFragment extends BaseCommFragment {

    @Override
    protected void initDatas() {
        setToolbarCenterTitle("患者列表");
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_patient_list;
    }
}
