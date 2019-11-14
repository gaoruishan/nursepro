package com.dhcc.module.infusion.workarea.blood;

import android.support.v7.widget.RecyclerView;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.SimpleCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.blood.adapter.BaseBloodQuickAdapter;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOnOffView;
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
    private BaseBloodQuickAdapter collectionAdapter;
    private CustomPatView customPat;
    private CustomOnOffView customOnOff;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("采血");

        collectionAdapter = AdapterFactory.getBloodAdapter();
        recyclerView.setAdapter(collectionAdapter);
    }


    @Override
    protected void initViews() {
        super.initViews();
        recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        customPat = f(R.id.custom_pat, CustomPatView.class);
        customOnOff = f(R.id.custom_on_off, CustomOnOffView.class);
        customOnOff.setShowSelectText("未执行", "已执行")
                .setOnSelectListener(new SimpleCallBack<Boolean>() {
                    @Override
                    public void call(Boolean result, int type) {
                        getLabOrdList();
                    }
                });
        f(R.id.custom_scan, CustomScanView.class).setTitle("请扫描腕带")
                .setWarning("请您使用扫码设备，扫描病人腕带");
        customDate.setEndDateTime(System.currentTimeMillis())
                .setStartDateTime(System.currentTimeMillis())
                .setOnDateSetListener(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getLabOrdList();
                    }
                }, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getLabOrdList();
                    }
                });

    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();

        //第一次扫码
        if (scanInfoTemp == null) {
            getLabOrdList();
        } else {
            exeLabOrd();
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_blood_collection;
    }

    private void exeLabOrd() {
        BloodCollectApiManager.exeLabOrd(scanInfo, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings();
                scanInfoTemp = null;
                refresh(bean);
            }
        });
    }

    private void getLabOrdList() {
        exeFlag = customOnOff.isSelect() ? "0" : "1";
        BloodCollectApiManager.getLabOrdList(scanInfo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), exeFlag, new CommonCallBack<BloodCollectBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(BloodCollectBean bean, String type) {
                helper.setVisible(R.id.custom_scan, false);
                if (bean.getOrdList() != null) {
                    scanInfoTemp = scanInfo;
                    collectionAdapter.setNewData(bean.getOrdList());
                }
                if (bean.getPatInfo() != null) {
                    customPat.setAge(bean.getPatInfo().getPatAge()).setPatName(bean.getPatInfo().getPatName())
                            .setRegNo(bean.getPatInfo().getPatRegNo()).setPatSex(bean.getPatInfo().getPatSex());
                }
            }
        });
    }
}
