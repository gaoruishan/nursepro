package com.dhcc.module.infusion.workarea.continues;

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
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.view.CustomPatView;
import com.dhcc.module.infusion.view.CustomScanView;
import com.dhcc.module.infusion.view.CustomSpeedView;
import com.dhcc.module.infusion.workarea.continues.api.ContinueApiManager;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommDosingAdapter;

/**
 * 续液
 * @author:gaoruishan
 * @date:202019-04-29/09:18
 * @email:grs0515@163.com
 */
public class ContinueFragment extends BaseFragment implements View.OnClickListener {

    private CustomScanView csvScan;
    private CustomPatView cpvPat;
    private CustomSpeedView csvSpeed;
    private CommDosingAdapter commDosingAdapter;
    private ContinueBean mBean;
    private String scanInfo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("续液");
        csvScan = mContainerChild.findViewById(R.id.csv_scan);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        csvSpeed = mContainerChild.findViewById(R.id.csv_speed);
        mContainerChild.findViewById(R.id.tv_ok).setOnClickListener(this);
        csvScan.setTitle("请扫描瓶签/信息卡").setWarning("请您使用扫码设备，扫描药品瓶签/信息卡");
        RecyclerView rvOrdList = RecyclerViewHelper.get(this.getActivity(), R.id.rv_ord_list);
        commDosingAdapter = AdapterFactory.getCommDosingOrdList();
        rvOrdList.setAdapter(commDosingAdapter);
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
        return inflater.inflate(R.layout.fragment_continue, container);
    }

    private void getOrdList(final String scanInfo) {
        ContinueApiManager.getChangeOrdList("", "", scanInfo, new CommonCallBack<ContinueBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(ContinueBean bean, String type) {
                mBean = bean;
                csvScan.setVisibility(View.GONE);
                if (bean.getPatInfo() != null) {
                    cpvPat.setRegNo(bean.getPatInfo().getPatRegNo()).setPatName(bean.getPatInfo().getPatName())
                            .setImgSexResource(CustomPatView.getPatSexDrawable(bean.getPatInfo().getPatSex()));
                }
                csvSpeed.setSpeed(bean.getDefautSpeed());
                commDosingAdapter.setCurrentScanInfo(scanInfo);
                commDosingAdapter.replaceData(bean.getOrdList());
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
                distantTime = mBean.getDistantDate()+" "+mBean.getDistantTime();
            }
            int speed = csvSpeed.getSpeed();
            if (speed <= 0) {
                ToastUtils.showShort("请调节滴速");
                return;
            }
            ContinueApiManager.changeOrd(oeoreId, distantTime, speed + "", "", new CommonCallBack<CommResult>() {
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
                    DialogFactory.showCommDialog(getActivity(), "续液成功", "", 0, null, true);
                }
            });
        }
    }
}
