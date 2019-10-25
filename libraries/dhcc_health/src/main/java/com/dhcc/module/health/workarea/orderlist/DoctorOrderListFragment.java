package com.dhcc.module.health.workarea.orderlist;

import com.base.commlibs.comm.BaseCommFragment;
import com.dhcc.module.health.R;

/**
 * 医嘱单
 * @author:gaoruishan
 * @date:202019-10-23/16:43
 * @email:grs0515@163.com
 */
public class DoctorOrderListFragment extends BaseCommFragment {
    @Override
    protected void initDatas() {
        setToolbarCenterTitle("医嘱单");
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_docorder_list;
    }
}
