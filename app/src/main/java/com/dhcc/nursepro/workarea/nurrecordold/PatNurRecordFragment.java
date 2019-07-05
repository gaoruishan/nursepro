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
import com.dhcc.nursepro.R;

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

//      initView();
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

    }

    private void initData() {


    }


    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String scanInfo = bundle.getString("data");

        }
    }

}
