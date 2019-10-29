package com.dhcc.module.health.workarea.vitalsign;

import android.util.Log;

import com.base.commlibs.comm.BaseCommFragment;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.health.R;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomScanView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * 生命体征
 * @author:gaoruishan
 * @date:202019-10-23/16:52
 * @email:grs0515@163.com
 */
public class VitalSignsFragment extends BaseCommFragment {

    private CustomScanView customCsv;
    private CustomDateTimeView customCdtv;

    @Override
    protected void initDatas() {
        setToolbarCenterTitle("生命体征");
        customCsv.setTitle("请扫描腕带").setWarning("请使用扫描设备,扫描病人腕带");
    }

    @Override
    protected void initViews() {
        customCsv = f(R.id.custom_csv, CustomScanView.class);
        customCdtv = f(R.id.custom_cdtv, CustomDateTimeView.class);
        customCdtv.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                Log.e(TAG,"(VitalSignsFragment.java:35) "+TimeUtils.millis2String(millseconds));
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                Log.e(TAG,"(VitalSignsFragment.java:42) "+TimeUtils.millis2String(millseconds));
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_vital_signs;
    }
}
