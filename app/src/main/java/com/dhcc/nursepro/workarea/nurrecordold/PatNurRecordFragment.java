package com.dhcc.nursepro.workarea.nurrecordold;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordold.adapter.NurRecordMenuListAdapter;
import com.dhcc.nursepro.workarea.nurrecordold.adapter.NurRecordPatListAdapter;
import com.dhcc.nursepro.workarea.nurrecordold.api.NurRecordOldApiManager;
import com.dhcc.nursepro.workarea.nurrecordold.bean.InWardPatListBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecModelListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * PatNurRecordFragment
 * 患者及对应护理病历列表
 *
 * @author Devlix126
 * created at 2019/7/4 11:22
 */
public class PatNurRecordFragment extends BaseFragment {
    private RecyclerView recyPatnurrecordPat;
    private RecyclerView recyPatnurrecordRecord;

    private List<InWardPatListBean.PatInfoListBean> patInfoList = new ArrayList<>();
    private NurRecordPatListAdapter patListAdapter;
    private InWardPatListBean.PatInfoListBean patInfoListBean;
    private NurRecordMenuListAdapter menuListAdapter;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_nur_record, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle(getString(R.string.title_nurrecord), 0xffffffff, 17);

        initview(view);
        initAdapter();
        initData();

    }

    private void initview(View view) {
        recyPatnurrecordPat = view.findViewById(R.id.recy_patnurrecord_pat);
        recyPatnurrecordRecord = view.findViewById(R.id.recy_patnurrecord_record);

        //提高展示效率
        recyPatnurrecordPat.setHasFixedSize(true);
        //设置的布局管理
        recyPatnurrecordPat.setLayoutManager(new LinearLayoutManager(getActivity()));

        //提高展示效率
        recyPatnurrecordRecord.setHasFixedSize(true);
        //设置的布局管理
        recyPatnurrecordRecord.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        patListAdapter = new NurRecordPatListAdapter(new ArrayList<>());
        patListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                patListAdapter.setSelectItem(position);
                patListAdapter.notifyDataSetChanged();
                patInfoListBean = (InWardPatListBean.PatInfoListBean) adapter.getItem(position);
                if (patInfoListBean != null) {
                    getModelList(patInfoListBean);
                }
            }
        });
        recyPatnurrecordPat.setAdapter(patListAdapter);

        menuListAdapter = new NurRecordMenuListAdapter(new ArrayList<>(), this, patInfoListBean);
        recyPatnurrecordRecord.setAdapter(menuListAdapter);


    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        NurRecordOldApiManager.getInWardPatList(new NurRecordOldApiManager.InWardPatListCallback() {
            @Override
            public void onSuccess(InWardPatListBean inWardPatListBean) {
                hideLoadingTip();
                patInfoList = inWardPatListBean.getPatInfoList();
                patListAdapter.setNewData(patInfoList);
                if (patInfoList.size() > 0) {
                    getModelList(patInfoList.get(0));
                }
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error" + code + ":" + msg);
            }
        });

    }


    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String scanInfo = bundle.getString("data");

            for (int i = 0; i < patInfoList.size(); i++) {
                patInfoListBean = patInfoList.get(i);
                if (patInfoListBean.getRegNo().equals(scanInfo)) {
                    patListAdapter.setSelectItem(i);
                    patListAdapter.notifyDataSetChanged();
                    recyPatnurrecordPat.scrollToPosition(i);
                    getModelList(patInfoListBean);
                    break;
                }
            }

        }
    }

    private void getModelList(InWardPatListBean.PatInfoListBean patInfoListBean) {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        NurRecordOldApiManager.getModelList(patInfoListBean.getEpisodeId(), new NurRecordOldApiManager.RecModelListCallback() {
            @Override
            public void onSuccess(RecModelListBean recModelListBean) {
                hideLoadFailTip();
                menuListAdapter.setNewData(recModelListBean.getMenuList());
                menuListAdapter.setPatInfoListBean(patInfoListBean);
                menuListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

}
