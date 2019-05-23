package com.dhcc.nursepro.workarea.drugloopsystem.drughandover;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.workarea.bedmap.api.BedMapApiManager;
import com.dhcc.nursepro.workarea.bedmap.bean.ScanResultBean;

import java.util.Objects;

/**
 * DrugHandoverFragment
 * 药品交接
 *
 * @author Devlix126
 * created at 2019/5/22 11:47
 */
public class DrugHandoverFragment extends BaseFragment {

    private RecyclerView recyHandoverList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_drughandover), 0xffffffff, 17);

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
        recyHandoverList = view.findViewById(R.id.recy_drughandover_list);
        recyHandoverList.setHasFixedSize(true);
        recyHandoverList.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        return inflater.inflate(R.layout.fragment_drughandover, container, false);
    }

}
