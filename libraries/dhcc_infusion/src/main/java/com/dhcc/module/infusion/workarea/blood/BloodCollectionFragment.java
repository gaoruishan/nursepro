package com.dhcc.module.infusion.workarea.blood;

import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;

/**
 * 采血模块
 * @author:gaoruishan
 * @date:202019-11-07/15:03
 * @email:grs0515@163.com
 */
public class BloodCollectionFragment extends BaseInfusionFragment {

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("采血");
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void getOrdList() {
        super.getOrdList();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_blood_collection;
    }
}
