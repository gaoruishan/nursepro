package com.dhcc.module.infusion.workarea.inject;

import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;

/**
 * 注射-配液
 * @author:gaoruishan
 * @date:202019-11-16/14:20
 * @email:grs0515@163.com
 */
public class InjectDosingFragment extends InjectFragment {

    private InjectListBean mBean;

    @Override
    protected void initViews() {
        super.initViews();
        showScanLabel();
        //隐藏 执行开关
        customOnOff.setVisibility(View.GONE);
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

        InjectApiManager.injectDespensing(scanInfo, mBean.getBtnType(), "", "", new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                getInjectOrdList();
            }
        });
    }

    /**
     * 重写-扫码
     */
    @Override
    protected void getScanOrdList() {
        getInjectOrdList();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_inject_dosing;
    }

    /**
     * 重写-获取注射列表
     */
    @Override
    protected void getInjectOrdList() {
        exeFlag = customOnOff.isSelect() ? "0" : "1";
        InjectApiManager.getInjectOrdList("", customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), exeFlag, scanInfo, new CommonCallBack<InjectListBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(InjectListBean bean, String type) {
                hideScanView();
                mBean = bean;
                if (bean.getOrdList() != null) {
                    injectAdapter.setCurrentScanInfo(bean.getCurOeoreId());
                    injectAdapter.setNewData(bean.getOrdList());
                }
                helper.setTextData(R.id.tv_ok, "确认");
                if (!"err".equals(bean.getBtnType())) {
                    helper.setTextData(R.id.tv_ok, bean.getBtnDesc());
                }
                setCustomPatViewData(customPat, bean.getPatInfo());
            }
        });
    }
}
