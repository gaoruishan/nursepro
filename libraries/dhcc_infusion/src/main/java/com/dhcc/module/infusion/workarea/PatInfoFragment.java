package com.dhcc.module.infusion.workarea;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.adapter.PatInfoAdapter;
import com.dhcc.module.infusion.workarea.comm.api.WorkAreaApiManager;
import com.dhcc.module.infusion.workarea.comm.bean.PatDetailBean;
import com.dhcc.res.infusion.CustomPatView;

/**
 * 患者信息
 * @author:gaoruishan
 * @date:202019-05-05/16:22
 * @email:grs0515@163.com
 */
public class PatInfoFragment extends BaseInfusionFragment {

    private String regNo;
    private PatInfoAdapter patInfoAdapter;
    private CustomPatView cpvPat;


    @Override
    protected int setLayout() {
        return R.layout.fragment_pat_info;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("患者信息");
        Bundle bundle = getArguments();

        if (bundle != null) {
            regNo = bundle.getString("id");
        }
        RecyclerView rvPatInfo = RecyclerViewHelper.get(mContext, R.id.rv_pat_info);
        cpvPat = f(R.id.cpv_pat);
        patInfoAdapter = AdapterFactory.getPatInfoAdapter();
        rvPatInfo.setAdapter(patInfoAdapter);
        WorkAreaApiManager.getPatInfo(regNo, new CommonCallBack<PatDetailBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(PatDetailBean bean, String type) {
                cpvPat.setPatName(bean.getPatInfo().getPatName()).setAge(bean.getPatInfo().getPatAge())
                        .setRegNo(bean.getPatInfo().getPatRegNo()).setSeat(bean.getPatInfo().getPatSeat());
                cpvPat.setImgSexResource(CustomPatView.getPatSexDrawable(bean.getPatInfo().getPatSex()));
                helper.setTextData(R.id.tv_last_num, bean.getLeftTreatNum());
                patInfoAdapter.replaceData(bean.getRecOrdList());
            }
        });
    }


}
