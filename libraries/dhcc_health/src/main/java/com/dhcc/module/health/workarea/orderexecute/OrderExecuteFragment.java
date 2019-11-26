package com.dhcc.module.health.workarea.orderexecute;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderexecute.adapter.OrderExecuteAdapter;
import com.dhcc.module.health.workarea.orderexecute.bean.OrdExecuteBean;
import com.dhcc.module.health.workarea.orderexecute.bean.OrdListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

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
    private List<OrdListBean.CureInfoListBean> ordersList = new ArrayList<>();
    private TextView tvOrderNum,tvSure;
    @Override
    protected void initDatas() {
        setToolbarCenterTitle("医嘱执行");
        customScan.setTitle("请扫描腕带").setWarning("请使用扫描设备,扫描病人腕带");
        initAdapter();
    }
    private void initAdapter(){
        orderExecuteAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ordersList.get(position).getSelect().equals("1")){
                    ordersList.get(position).setSelect("0");
                }else {
                    ordersList.get(position).setSelect("1");
                }
                orderExecuteAdapter.setNewData(ordersList);
                int num = 0;
                for (int i = 0; i <ordersList.size() ; i++) {
                    if (ordersList.get(i).getSelect().equals("1")){
                        num++;
                    }
                }
                tvOrderNum.setText("已选择"+num+"条医嘱");
            }
        });
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

        tvOrderNum = f(R.id.tv_sel_num,TextView.class);
        tvSure = f(R.id.tv_execu_sure,TextView.class);
        tvSure.setOnClickListener(this);
    }

    private void getOrders(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderExecuteApiManager.getHealthOrdInfo(scanInfo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), "", new CommonCallBack<OrdListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(OrdListBean bean, String type) {
                hideLoadingTip();
                customScan.setVisibility(View.GONE);
                ordersList = bean.getCureInfoList();
                orderExecuteAdapter.setNewData(ordersList);
                if (bean.getPatInfoList() != null && customPat != null) {
                    customPat.setRegNo(bean.getPatInfoList().get(0).getPapmiNo()).setPatName(bean.getPatInfoList().get(0).getName())
                            .setAge(bean.getPatInfoList().get(0).getAgeDesc()).setPatSex(bean.getPatInfoList().get(0).getSex());
                }
            }
        });

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_execu_sure) {
            String appStrs = null;
            for (int i = 0; i <ordersList.size() ; i++) {
                if (ordersList.get(i).getSelect().equals("1")){
                    if (appStrs == null){
                        appStrs = ordersList.get(i).getDCAOEORIDR();
                    }else {
                        appStrs = appStrs+"^"+ordersList.get(i).getDCAOEORIDR();
                    }
                }
            }
            if (appStrs ==null){
                showToast("未选择医嘱，无法执行医嘱");
            }else {
//                showToast(appStrs);
                showLoadingTip(BaseActivity.LoadingType.FULL);
                OrderExecuteApiManager.getHealthExecuResult(appStrs, new CommonCallBack<CommResult>() {
                    @Override
                    public void onFail(String code, String msg) {
                        hideLoadingTip();
                    }

                    @Override
                    public void onSuccess(CommResult bean, String type) {
                        hideLoadingTip();
                        showToast(bean.getMsg());
                        getOrders();
                    }
                });

            }

        }
    }
    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        getOrders();
    }
    @Override
    protected int setLayout() {
        return R.layout.health_fragment_order_execute;
    }

}
