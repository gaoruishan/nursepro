package com.dhcc.module.health.workarea.orderexecute;

import android.content.Intent;

import com.base.commlibs.comm.BaseCommFragment;
import com.dhcc.module.health.R;
import com.dhcc.res.infusion.CustomScanView;

/**
 * 医嘱执行
 * @author:gaoruishan
 * @date:202019-10-23/16:58
 * @email:grs0515@163.com
 */
public class OrderExecuteFragment extends BaseCommFragment {

    private CustomScanView csv;

    @Override
    protected void initDatas() {
        setToolbarCenterTitle("医嘱执行");
    }

    @Override
    protected void initViews() {
        csv = f(R.id.csv, CustomScanView.class);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (scanInfo != null) {
            getScanOrdList();
        }
    }

    private void getScanOrdList() {

    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_order_execute;
    }
}
