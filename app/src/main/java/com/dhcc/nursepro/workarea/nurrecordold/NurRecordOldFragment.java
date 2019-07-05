package com.dhcc.nursepro.workarea.nurrecordold;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;

/**
 * NurRecordOldFragment
 * 护理病历老版XML解析样式
 *
 * @author Devlix126
 * created at 2019/7/4 11:22
 */
public class NurRecordOldFragment extends BaseFragment {

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_old, container, false);
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

    }

    private void initAdapter() {

    }

    private void initData() {

    }

}
