package com.dhcc.module.health.workarea.orderexecute;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderexecute.adapter.OrderExecuteAdapter;
import com.dhcc.module.health.workarea.orderexecute.bean.OrdExecuteBean;
import com.dhcc.module.health.workarea.orderexecute.bean.OrdListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * 医嘱执行
 * @author:gaoruishan
 * @date:202019-10-23/16:58
 * @email:grs0515@163.com
 */
public class OrderExecuteFragment extends BaseCommFragment {

    private CustomScanView customScan;
    private CustomPatView customPat;
    private CustomDateTimeView customDate;
    private OrderExecuteAdapter orderExecuteAdapter;

    @Override
    protected void initDatas() {
        setToolbarCenterTitle("医嘱执行");
        customScan.setTitle("请扫描腕带").setWarning("请使用扫描设备,扫描病人腕带");
    }

    @Override
    protected void initViews() {
        customScan = f(R.id.custom_scan, CustomScanView.class);
        customPat = f(R.id.custom_pat, CustomPatView.class);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        RecyclerView rvList = f(R.id.rv_list, RecyclerView.class);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvList, R.drawable.dhcc_line, LinearLayoutManager.VERTICAL);
        orderExecuteAdapter = new OrderExecuteAdapter(null);
        rvList.setAdapter(orderExecuteAdapter);
        customDate.setEndDateTime(System.currentTimeMillis())
                .setStartDateTime(System.currentTimeMillis())
                .setOnDateSetListener(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getScanOrdList();
                        Log.e(TAG, "(VitalSignsFragment.java:35) " + TimeUtils.millis2String(millseconds));
                    }
                }, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getScanOrdList();
                        Log.e(TAG, "(VitalSignsFragment.java:42) " + TimeUtils.millis2String(millseconds));
                    }
                });
    }


    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        showToast(scanInfo);
        OrderExecuteApiManager.getHealthOrdInfo(scanInfo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), "", new CommonCallBack<OrdListBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(OrdListBean bean, String type) {
                customScan.setVisibility(View.GONE);
                orderExecuteAdapter.setNewData(bean.getCureInfoList());
                if (bean.getPatInfoList() != null && customPat != null) {
                    customPat.setRegNo(bean.getPatInfoList().get(0).getPapmiNo()).setPatName(bean.getPatInfoList().get(0).getName())
                            .setAge(bean.getPatInfoList().get(0).getAgeDesc()).setPatSex(bean.getPatInfoList().get(0).getSex());
                }
            }
        });

    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_order_execute;
    }

}
