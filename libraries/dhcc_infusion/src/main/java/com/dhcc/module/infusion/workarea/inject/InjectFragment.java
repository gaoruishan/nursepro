package com.dhcc.module.infusion.workarea.inject;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.SimpleCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
    protected InjectListBean injectListBean;
    protected TextView tvSure;
    protected String ordId = "";

    @Override
    protected void initViews() {
        super.initViews();
        recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        customPat = f(R.id.custom_pat, CustomPatView.class);
        customOnOff = f(R.id.custom_on_off, CustomOnOffView.class);
        tvSure = f(R.id.tv_inject_sure);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordId.equals("")){
                    showToast("请选中医嘱");
                }else {
                    exeInjectOrd(ordId);
                }

            }
        });
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
                if (view.getId() == R.id.ll_orderselect) {
                    for (int i = 0; i <injectListBean.getOrdList().size() ; i++) {
                        injectListBean.getOrdList().get(i).setSelect("0");
                    }
                    injectListBean.getOrdList().get(position).setSelect("1");
                    injectAdapter.setNewData(injectListBean.getOrdList());
                    ordId = injectListBean.getOrdList().get(position).getOeoriId();
//                    showToast(ordId);
                }
            }
        });
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
                    getInjectOrdList();
                }
                //ORD 扫医嘱条码返回医嘱信息
                if (ORD.equals(bean.getFlag())) {
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

    private void exeInjectOrd(String injectOrdId) {
        InjectApiManager.exeInjectOrd(injectOrdId, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                getInjectOrdList();
                ordId = "";
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
                tvSure.setVisibility(View.VISIBLE);
                injectListBean = bean;
                if (injectListBean.getOrdList() != null) {
                    for (int i = 0; i <injectListBean.getOrdList().size() ; i++) {
                        injectListBean.getOrdList().get(i).setSelect("0");
                    }
                    injectAdapter.setIfSelShow(true);
                    injectAdapter.setNewData(injectListBean.getOrdList());
                }
                regNo = injectListBean.getPatInfo().getPatRegNo();
                setCustomPatViewData(customPat,injectListBean.getPatInfo());
            }
        });
    }
}
