package com.dhcc.module.infusion.workarea.inject;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.SimpleCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.bean.ScanInfoBean;
import com.dhcc.module.infusion.workarea.inject.adapter.InjectAdapter;
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
public class InjectFragment extends BaseInfusionFragment implements View.OnClickListener {

    protected RecyclerView recyclerView;
    protected CustomDateTimeView customDate;
    protected CustomPatView customPat;
    protected CustomOnOffView customOnOff;
    protected InjectAdapter injectAdapter;
    protected InjectListBean injectListBean;

    @Override
    protected void initViews() {
        super.initViews();
        recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        customPat = f(R.id.custom_pat, CustomPatView.class);
        customOnOff = f(R.id.custom_on_off, CustomOnOffView.class);
        f(R.id.tv_inject_sure).setOnClickListener(this);
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
        injectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                injectAdapter.refreshData(injectListBean, position);
            }
        });
    }

    @Override
    protected void getScanOrdList() {

        InjectApiManager.getScanInfo(regNo, scanInfo, new CommonCallBack<ScanInfoBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(ScanInfoBean bean, String type) {
                //PAT 扫腕带返回患者信息
                if (PAT.equals(bean.getFlag())) {
                    regNo = bean.getPatInfo().getPatRegNo();
                    getInjectOrdList();
                }
                //ORD 扫医嘱条码返回医嘱信息
                if (ORD.equals(bean.getFlag())) {
                    //选中扫码的
                    for (BloodOrdListBean bean1 : injectAdapter.getData()) {
                        if (scanInfo.equals(bean1.getOeoriId()) ) {
                            f(R.id.tv_inject_sure).setVisibility(View.VISIBLE);
                        }
                        bean1.setSelect(scanInfo.equals(bean1.getOeoriId()) ? "1" : "0");
                    }
                    injectAdapter.notifyDataSetChanged();
                    //弹框
//                    if ("1".equals(bean.getDiagFlag())) {
//                        DialogFactory.showPatInfo(mContext, bean, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                exeInjectOrd();
//                            }
//                        });
//                    }else {
//                        exeInjectOrd();
//                    }
//                    Toast.makeText(getContext(),"执行注射操作请点击确定执行",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_inject;
    }

    private void exeInjectOrd(String injectOrdId) {
        InjectApiManager.exeInjectOrd(injectOrdId, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                regNo = null;//置空
                onSuccessThings(bean);
                getInjectOrdList();
            }
        });
    }

    protected void getInjectOrdList() {
        exeFlag = customOnOff.isSelect() ? "0" : "1";
        InjectApiManager.getInjectOrdList(regNo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), exeFlag, scanInfo, new CommonCallBack<InjectListBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(InjectListBean bean, String type) {
                helper.setVisible(R.id.custom_scan, false);
                f(R.id.tv_inject_sure).setVisibility(View.VISIBLE);
                if ("1".equals(bean.getInjectFlag())) {
                    f(R.id.tv_inject_sure).setVisibility(View.GONE);
                }
                injectListBean = bean;
                injectAdapter.setInitData(bean);
//                regNo = injectListBean.getPatInfo().getPatRegNo();
                setCustomPatViewData(customPat, injectListBean.getPatInfo());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_inject_sure) {
            String ordId = "";
            for (BloodOrdListBean bean : injectAdapter.getData()) {
                if ("1".equals(bean.getSelect())) {
                    ordId = bean.getOeoriId();
                }
            }
            if (ordId.equals("")) {
                showToast("请选中医嘱");
            } else {
                exeInjectOrd(ordId);
            }
        }
    }
}
