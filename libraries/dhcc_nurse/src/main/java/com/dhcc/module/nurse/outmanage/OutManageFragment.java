package com.dhcc.module.nurse.outmanage;

import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;

/**
 * 外出管理
 * @author:gaoruishan
 * @date:202021/8/16/10:51
 * @email:grs0515@163.com
 */
public class OutManageFragment extends BaseNurseFragment {

    @Override
    protected int setLayout() {
        return R.layout.fragment_out_manage;
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("外出管理");
    }
}
