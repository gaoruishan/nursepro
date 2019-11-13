package com.dhcc.module.infusion.workarea.blood;

import android.support.v7.widget.RecyclerView;

import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOrdExeBottomView;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * 采血模块
 * @author:gaoruishan
 * @date:202019-11-07/15:03
 * @email:grs0515@163.com
 */
public class BloodCollectionFragment extends BaseInfusionFragment {

    private RecyclerView recyclerView;
    private CustomDateTimeView customDate;
    private CustomOrdExeBottomView bottomView;
    private BloodCollectionAdapter collectionAdapter;
    private CustomPatView customPat;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("采血");
        collectionAdapter = new BloodCollectionAdapter(null);
        recyclerView.setAdapter(collectionAdapter);
    }


    @Override
    protected void initViews() {
        super.initViews();
        recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        bottomView = f(R.id.custom_bottom, CustomOrdExeBottomView.class);
        customPat = f(R.id.custom_pat, CustomPatView.class);
        f(R.id.custom_scan, CustomScanView.class).setTitle("请扫描腕带")
                .setWarning("请您使用扫码设备，扫描病人腕带");
        customDate.setEndDateTime(System.currentTimeMillis())
                .setStartDateTime(System.currentTimeMillis())
                .setOnDateSetListener(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getOrdList();
                    }
                }, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getOrdList();
                    }
                });
    }

    @Override
    protected void getOrdList() {
        super.getOrdList();
        BloodCollectManager.getLabOrdList(scanInfo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), new CommonCallBack<BloodCollectBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(BloodCollectBean bean, String type) {
                helper.setVisible(R.id.custom_scan, false);
                collectionAdapter.setNewData(bean.getOrdList());
                if (bean.getPatInfo() != null) {
                    customPat.setAge(bean.getPatInfo().getPatAge()).setPatName(bean.getPatInfo().getPatName())
                            .setRegNo(bean.getPatInfo().getPatRegNo()).setPatSex(bean.getPatInfo().getPatSex());
                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_blood_collection;
    }
}
