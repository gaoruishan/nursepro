package com.dhcc.module.infusion.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.setting.adapter.PatListAdapter;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;

/**
 * 患者列表
 * @author:gaoruishan
 * @date:202019-06-20/16:22
 * @email:grs0515@163.com
 */
public class PatListFragment extends BaseInfusionFragment {
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("患者列表");
        RecyclerView rvPatList = RecyclerViewHelper.get(this.getActivity(), R.id.rv_pat_list);
        PatListAdapter patListAdapter = AdapterFactory.getPatListAdapter();
        rvPatList.setAdapter(patListAdapter);

    }
}
