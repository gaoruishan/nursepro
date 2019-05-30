package com.dhcc.module.infusion.workarea.needles;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.workarea.patrol.adapter.PatrolOrdListAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.view.CustomPatView;
import com.dhcc.module.infusion.view.CustomScanView;
import com.dhcc.module.infusion.workarea.needles.api.NeedlesApiManager;

/**
 * 拔针
 * @author:gaoruishan
 * @date:202019-04-29/11:34
 * @email:grs0515@163.com
 */
public class NeedlesFragment extends BaseFragment implements View.OnClickListener {
    private CustomScanView csvScan;
    private CustomPatView cpvPat;
    private PatrolOrdListAdapter commPatrolAdapter;
    private NeedlesBean mBean;
    private String scanInfo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("拔针");
        csvScan = mContainerChild.findViewById(R.id.csv_scan);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        mContainerChild.findViewById(R.id.tv_ok).setOnClickListener(this);
        csvScan.setTitle("请扫描瓶签/信息卡").setWarning("请您使用扫码设备，扫描药品瓶签/信息卡");
        RecyclerView rvOrdList = RecyclerViewHelper.get(this.getActivity(), R.id.rv_ord_list);
        commPatrolAdapter = AdapterFactory.getCommPatrolOrdList();
        rvOrdList.setAdapter(commPatrolAdapter);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
         scanInfo = doScanInfo(intent);
        if (scanInfo != null) {
            getOrdList(scanInfo);
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_needles, container);
    }

    private void getOrdList(final String scanInfo) {
        NeedlesApiManager.getFinishOrdList("", "", scanInfo, new CommonCallBack<NeedlesBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(NeedlesBean bean, String type) {
                mBean = bean;
                csvScan.setVisibility(View.GONE);
                if (bean.getPatInfo() != null) {
                    cpvPat.setRegNo(bean.getPatInfo().getPatRegNo()).setPatName(bean.getPatInfo().getPatName())
                            .setImgSexResource(CustomPatView.getPatSexDrawable(bean.getPatInfo().getPatSex()));
                }
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
                    ToastUtils.showShort(msg);
                }

                @Override
                public void onSuccess(CommResult bean, String type) {
//                    csvScan.setVisibility(View.VISIBLE);
                    if (scanInfo != null) {
                        getOrdList(scanInfo);
                    }
                    DialogFactory.showCommDialog(getActivity(), "拔针成功", "", 0, null, true);
                }
            });
        }
    }
}
