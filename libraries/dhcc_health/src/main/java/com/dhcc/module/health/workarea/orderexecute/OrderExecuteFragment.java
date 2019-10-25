package com.dhcc.module.health.workarea.orderexecute;

import com.base.commlibs.comm.BaseCommFragment;
import com.dhcc.module.health.R;

/**
 * 医嘱执行
 * @author:gaoruishan
 * @date:202019-10-23/16:58
 * @email:grs0515@163.com
 */
public class OrderExecuteFragment extends BaseCommFragment {
    @Override
    protected void initDatas() {
        setToolbarCenterTitle("医嘱执行");
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_order_execute;
    }
}
