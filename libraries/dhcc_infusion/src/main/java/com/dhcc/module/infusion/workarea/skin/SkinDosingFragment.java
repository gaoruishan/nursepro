package com.dhcc.module.infusion.workarea.skin;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.skin.adapter.SkinAdapter;
import com.dhcc.module.infusion.workarea.skin.api.SkinApiManager;
import com.dhcc.module.infusion.workarea.skin.bean.SkinListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomPatView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * 皮试配液
 * @author:gaoruishan
 * @date:202019-11-17/14:35
 * @email:grs0515@163.com
 */
public class SkinDosingFragment extends BaseInfusionFragment {

    private RecyclerView recyclerView;
    private CustomDateTimeView customDate;
    private SkinAdapter skinAdapter;
    private CustomPatView customPat;
    private SkinListBean mBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_skin_dosing;
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        skinAdapter = AdapterFactory.getSkinAdapter();
        recyclerView.setAdapter(skinAdapter);
        helper.setOnClickListener(R.id.tv_ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                despensingOrd();
            }
        });
    }
    private void despensingOrd() {
        if (scanInfo == null || mBean == null) {
            return;
        }
        if ("err".equals(mBean.getBtnType())) {
            showToast(mBean.getBtnDesc());
            return;
        }

        SkinApiManager.skinDespensingOrd(scanInfo, mBean.getBtnType(), "", "", new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                getScanOrdList();
            }
        });
    }
    @Override
    protected void initViews() {
        super.initViews();
        recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        customPat = f(R.id.custom_pat, CustomPatView.class);
        showScanLabel();
        customDate.setEndDateTime(System.currentTimeMillis())
                .setStartDateTime(System.currentTimeMillis())
                .setOnDateSetListener(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getScanOrdList();
                    }
                }, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getScanOrdList();
                    }
                });
    }

    @Override
    protected void getScanOrdList() {

        SkinApiManager.getSkinList("", customDate.getStartDateTimeText(), customDate.getEndDateTimeText(),scanInfo, new CommonCallBack<SkinListBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(SkinListBean bean, String type) {
                hideScanView();
                mBean = bean;
                skinAdapter.setNewData(bean.getOrdList());
                skinAdapter.setHideSelectButton(true);
                skinAdapter.setCurrentScanInfo(bean.getCurOeoreId());
                helper.setTextData(R.id.tv_ok, "确认");
                if (!"err".equals(bean.getBtnType())) {
                    helper.setTextData(R.id.tv_ok, bean.getBtnDesc());
                }
                setCustomPatViewData(customPat, bean.getPatInfo());
            }
        });
    }
}
