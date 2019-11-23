package com.dhcc.module.health.workarea.vitalsign;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.ViewUtil;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.vitalsign.adapter.VitalListAdapter;
import com.dhcc.module.health.workarea.vitalsign.api.VitalSignsApiManager;
import com.dhcc.module.health.workarea.vitalsign.bean.VitalSignDetailBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.nurse.CustomEmptyLayout;
import com.dhcc.res.nurse.CustomLinearLayout;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 生命体征
 * @author:gaoruishan
 * @date:202019-10-23/16:52
 * @email:grs0515@163.com
 */
public class VitalSignsFragment extends BaseCommFragment {

    private CustomScanView customScan;
    private CustomDateTimeView customDate;
    private CustomLinearLayout customLinear;
    private VitalListAdapter vitalListAdapter;

    @Override
    protected void initDatas() {
        setToolbarCenterTitle("生命体征");
        customScan.setTitle("请扫描腕带").setWarning("请使用扫描设备,扫描病人腕带");
    }

    @Override
    protected void initViews() {
        customScan = f(R.id.custom_scan, CustomScanView.class);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        customLinear = f(R.id.custom_linear, CustomLinearLayout.class);
        RecyclerView rvVitalList = f(R.id.rv_vital_list, RecyclerView.class);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvVitalList, R.drawable.dhcc_line, LinearLayoutManager.VERTICAL);
        vitalListAdapter = new VitalListAdapter(null);
        rvVitalList.setAdapter(vitalListAdapter);
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
    protected int setLayout() {
        return R.layout.health_fragment_vital_signs;
    }

    @Override
    protected void getScanOrdList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        VitalSignsApiManager.getPatTempDetail(scanInfo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), new CommonCallBack<VitalSignDetailBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(VitalSignDetailBean bean, String type) {
                hideLoadingTip();
                customScan.setVisibility(View.GONE);
                boolean isEmpty = bean.getTempDataList() == null || bean.getTempDataList().size() == 0;
                CustomEmptyLayout emptyLayout = f(R.id.custom_empty, CustomEmptyLayout.class);
                if (emptyLayout != null) {
                    emptyLayout.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
                }
                View horSv = f(R.id.hor_sv);
                if (horSv != null) {
                    horSv.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
                }
                List<String> titles = new ArrayList<>();
                titles.add("序号");//添加序号
                List<String> codes = new ArrayList<>();
                for (VitalSignDetailBean.TempConfigBean tempConfigBean : bean.getTempConfig()) {
                    titles.add(tempConfigBean.getDesc());
                    codes.add(tempConfigBean.getCode());
                }
                List<Map<String, Object>> newList = new ArrayList<>();
                for (VitalSignDetailBean.TempDataListBean listBean : bean.getTempDataList()) {
                    Map<String, Object> map = ViewUtil.objectToMap(listBean);
                    newList.add(map);
                }
                customLinear.setFirstSpecial(true).setNewData(titles);
                vitalListAdapter.setCustomData(codes, newList);
            }
        });
    }
}
