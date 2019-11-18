package com.dhcc.module.infusion.workarea.inject;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.SimpleCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.blood.adapter.BaseBloodQuickAdapter;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.bean.ScanInfoBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOnOffView;
import com.dhcc.res.infusion.CustomPatView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * 注射
 * @author:gaoruishan
 * @date:202019-11-07/15:35
 * @email:grs0515@163.com
 */
public class InjectFragment extends BaseInfusionFragment {

    protected RecyclerView recyclerView;
    protected CustomDateTimeView customDate;
    protected CustomPatView customPat;
    protected CustomOnOffView customOnOff;
    protected BaseBloodQuickAdapter injectAdapter;

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
                        getInjectOrdList();
                    }
                });
        showScanPatHand();
        customDate.setEndDateTime(System.currentTimeMillis())
                .setStartDateTime(System.currentTimeMillis())
                .setOnDateSetListener(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getInjectOrdList();
                    }
                }, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getInjectOrdList();
                    }
                });
    }


    @Override
    protected void initDatas() {
        super.initDatas();

        injectAdapter = AdapterFactory.getInjectAdapter();
        recyclerView.setAdapter(injectAdapter);
    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        InjectApiManager.getScanInfo(regNo, scanInfo, new CommonCallBack<ScanInfoBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(ScanInfoBean bean, String type) {
                //PAT 扫腕带返回患者信息
                if (PAT.equals(bean.getFlag())) {
                    getInjectOrdList();
                }
                //ORD 扫医嘱条码返回医嘱信息
                if (ORD.equals(bean.getFlag())) {
                    //弹框
                    if ("1".equals(bean.getDiagFlag())) {
                        DialogFactory.showPatInfo(mContext, bean, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                exeInjectOrd();
                            }
                        });
                    }else {
                        exeInjectOrd();
                    }
                }
            }
        });
    }

    private void exeInjectOrd() {
        InjectApiManager.exeInjectOrd(scanInfo, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                scanInfo = scanInfoTemp;
                getInjectOrdList();
                scanInfoTemp = null;
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_inject;
    }

    protected void getInjectOrdList() {
        exeFlag = customOnOff.isSelect() ? "0" : "1";
        String scanInfo = this.scanInfo;
        if(!TextUtils.isEmpty(regNo)){
            // 防止标签重新赋值
            scanInfo = regNo;
        }
        InjectApiManager.getInjectOrdList(scanInfo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), exeFlag,"", new CommonCallBack<InjectListBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(InjectListBean bean, String type) {
                helper.setVisible(R.id.custom_scan, false);
                if (bean.getOrdList() != null) {
                    injectAdapter.setNewData(bean.getOrdList());
                }
                regNo = bean.getPatInfo().getPatRegNo();
                setCustomPatViewData(customPat,bean.getPatInfo());
            }
        });
    }
}
