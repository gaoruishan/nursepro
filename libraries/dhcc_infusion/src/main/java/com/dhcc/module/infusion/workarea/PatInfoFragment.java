package com.dhcc.module.infusion.workarea;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BaseHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.view.CustomPatView;
import com.dhcc.module.infusion.workarea.comm.adapter.PatInfoAdapter;
import com.dhcc.module.infusion.workarea.comm.api.WorkAreaApiManager;
import com.dhcc.module.infusion.workarea.comm.bean.PatDetailBean;

/**
 * 患者信息
 * @author:gaoruishan
 * @date:202019-05-05/16:22
 * @email:grs0515@163.com
 */
public class PatInfoFragment extends BaseFragment {

    private String regNo;
    private BaseHelper helper;
    private PatInfoAdapter patInfoAdapter;
//    private int resId = R.id.cpv_pat;
//    @FindView(R.id.cpv_pat)
    CustomPatView cpvPat;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_info, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("患者信息");
        helper = new BaseHelper(this.getActivity());
        Bundle bundle = getArguments();

        if (bundle != null) {
            regNo = bundle.getString("id");
        }
        RecyclerView rvPatInfo = RecyclerViewHelper.get(this.getActivity(), R.id.rv_pat_info);
        cpvPat = f(R.id.cpv_pat);
        patInfoAdapter = AdapterFactory.getPatInfoAdapter();
        rvPatInfo.setAdapter(patInfoAdapter);
        WorkAreaApiManager.getPatInfo(regNo, new CommonCallBack<PatDetailBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(PatDetailBean bean, String type) {
                cpvPat.setPatName(bean.getPatInfo().getPatName())
                        .setAge(bean.getPatInfo().getPatAge())
                        .setRegNo(bean.getPatInfo().getPatRegNo());
                cpvPat.setImgSexResource(CustomPatView.getPatSexDrawable(bean.getPatInfo().getPatSex()));
                helper.setTextData(R.id.tv_last_num, bean.getLeftTreatNum());
                patInfoAdapter.replaceData(bean.getRecOrdList());
            }
        });
    }


}
