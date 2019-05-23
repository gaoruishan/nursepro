package com.dhcc.nursepro.workarea.drugloopsystem.ampoulebottleregistration;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;

import java.util.Objects;

/**
 * ABRegFragment
 * 安瓿瓶登记
 *
 * @author Devlix126
 * created at 2019/5/22 16:44
 */
public class ABRegFragment extends BaseFragment {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_ampoulebottleregistration), 0xffffffff, 17);

        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);

    }

    private void initView(View view) {

    }

    private void initAdapter() {

    }

    private void asyncInitData() {

    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        switch (Objects.requireNonNull(intent.getAction())) {
            case Action.DEVICE_SCAN_CODE:
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                String scanInfo = bundle.getString("data");
                showToast(scanInfo);
                //                BedMapApiManager.getScanInfo(scanInfo, new BedMapApiManager.GetScanInfoCallback() {
                //                    @Override
                //                    public void onSuccess(ScanResultBean scanResultBean) {
                //                        bedCode = scanResultBean.getPatInfo().getBedCode();
                //                        setData(bedCode, topFilterStr, leftFilterStr);
                //                    }
                //
                //                    @Override
                //                    public void onFail(String code, String msg) {
                //                        showToast("error" + code + ":" + msg);
                //                    }
                //                });
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_abreg, container, false);
    }

}
