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

    private CustomScanView customScan;

    @Override
    protected void initDatas() {
        setToolbarCenterTitle("医嘱执行");
    }

    @Override
    protected void initViews() {
        customScan = f(R.id.custom_scan, CustomScanView.class);
        customScan.setTitle("请扫描腕带").setWarning("请使用扫描设备,扫描病人腕带");
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
