package com.dhcc.nursepro.workarea.drugloopsystem.drugreview;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.dhcc.nursepro.R;

/**
 * DrugReviewFragment
 * 取药复核
 *
 * @author Devlix126
 * created at 2019/5/22 16:43
 */
public class DrugReviewFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_drugreview), 0xffffffff, 17);

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
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {

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

        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drug_review, container, false);
    }
}
