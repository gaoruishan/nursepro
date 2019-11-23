package com.dhcc.module.infusion.workarea.blood;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.SimpleCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.blood.adapter.BaseBloodQuickAdapter;
import com.dhcc.module.infusion.workarea.blood.bean.BloodCollectBean;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.bean.ScanInfoBean;
import com.dhcc.module.infusion.workarea.inject.InjectApiManager;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOnOffView;
import com.dhcc.res.infusion.CustomPatView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * 采血模块
 * @author:gaoruishan
 * @date:202019-11-07/15:03
 * @email:grs0515@163.com
 */
public class BloodCollectionFragment extends BaseInfusionFragment {

    protected RecyclerView recyclerView;
    protected CustomDateTimeView customDate;
    protected BaseBloodQuickAdapter collectionAdapter;
    protected CustomPatView customPat;
    protected CustomOnOffView customOnOff;

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
        showScanPatHand();
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
    protected void initDatas() {
        super.initDatas();

        collectionAdapter = AdapterFactory.getBloodAdapter();
        recyclerView.setAdapter(collectionAdapter);
    }

    @Override
    protected void getScanOrdList() {

        InjectApiManager.getScanInfo(regNo, scanInfo, new CommonCallBack<ScanInfoBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(ScanInfoBean bean, String type) {
                //PAT 扫腕带返回患者信息
                if (PAT.equals(bean.getFlag())) {
                    regNo = scanInfo;
                    getLabOrdList();
                }
                //ORD 扫医嘱条码返回医嘱信息
                if (ORD.equals(bean.getFlag())) {
                    //弹框
                    if ("1".equals(bean.getDiagFlag())) {
                        DialogFactory.showPatInfo(mContext, bean, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                exeLabOrd();
                            }
                        });
                    } else {
                        exeLabOrd();
                    }
                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_blood_collection;
    }

    protected void exeLabOrd() {
        BloodCollectApiManager.exeLabOrd(scanInfo, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings();
                DialogFactory.showCommDialog(getActivity(), bean.getMsg(), null, 0, null, true);
                getLabOrdList();
            }
        });
    }

    protected void getLabOrdList() {
        exeFlag = customOnOff.isSelect() ? "0" : "1";
        BloodCollectApiManager.getLabOrdList(regNo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), exeFlag, new CommonCallBack<BloodCollectBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(BloodCollectBean bean, String type) {
                helper.setVisible(R.id.custom_scan, false);
                if (bean.getOrdList() != null) {
                    collectionAdapter.setNewData(bean.getOrdList());
                }
                setCustomPatViewData(customPat, bean.getPatInfo());
            }
        });
    }
}
