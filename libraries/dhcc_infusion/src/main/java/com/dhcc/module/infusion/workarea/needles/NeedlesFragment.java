package com.dhcc.module.infusion.workarea.needles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.needles.api.NeedlesApiManager;
import com.dhcc.module.infusion.workarea.patrol.adapter.PatrolOrdListAdapter;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;

/**
 * 拔针
 * @author:gaoruishan
 * @date:202019-04-29/11:34
 * @email:grs0515@163.com
 */
public class NeedlesFragment extends BaseInfusionFragment implements View.OnClickListener {
    private CustomScanView csvScan;
    private CustomPatView cpvPat;
    private PatrolOrdListAdapter commPatrolAdapter;
    private NeedlesBean mBean;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        csvScan = mContainerChild.findViewById(R.id.custom_scan);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        mContainerChild.findViewById(R.id.tv_ok).setOnClickListener(this);
        showScanLabel();
        RecyclerView rvOrdList = RecyclerViewHelper.get(mContext, R.id.rv_ord_list);
        commPatrolAdapter = AdapterFactory.getCommPatrolOrdList();
        rvOrdList.setAdapter(commPatrolAdapter);
    }

    @Override
    protected void getScanOrdList() {

        getOrdList(scanInfo);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_needles;
    }

    private void getOrdList(final String scanInfo) {
        String regNo = "";
        String curOeoreId = "";
        if (mBean != null) {
            regNo = mBean.getCurRegNo();
            curOeoreId = mBean.getCurOeoreId();
        }
        NeedlesApiManager.getFinishOrdList(regNo, curOeoreId, scanInfo, new CommonCallBack<NeedlesBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(NeedlesBean bean, String type) {
                if (checkListOeoreId(bean.getOrdList(), PROMPT_NO_ORD)) {
//                    return;
                }
                mBean = bean;
                csvScan.setVisibility(View.GONE);
                //两次验证
                auditOrdInfo(bean.getOrdList(),bean.getCurRegNo(),bean.getCurOeoreId());
                setCustomPatViewData(cpvPat, bean.getPatInfo());
                commPatrolAdapter.setCurrentScanInfo(scanInfo);
                commPatrolAdapter.replaceData(bean.getOrdList());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            String oeoreId = "";
            String distantTime = "";
            if (mBean != null) {
                oeoreId = mBean.getCurOeoreId();
            }
            NeedlesApiManager.extractOrd(oeoreId, new CommonCallBack<CommResult>() {
                @Override
                public void onFail(String code, String msg) {
                    onFailThings();
                }

                @Override
                public void onSuccess(CommResult bean, String type) {
//                    csvScan.setVisibility(View.VISIBLE);
                    if (scanInfo != null) {
                        getOrdList(scanInfo);
                    }
                    DialogFactory.showCommDialog(getActivity(), "拔针成功", "", 0, null, true);
                    onSuccessThings();
                }
            });
        }
    }
}
